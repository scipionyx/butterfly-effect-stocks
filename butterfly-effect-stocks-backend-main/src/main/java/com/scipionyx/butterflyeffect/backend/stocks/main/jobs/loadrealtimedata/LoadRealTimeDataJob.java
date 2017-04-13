package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.loadrealtimedata;

import java.beans.PropertyEditor;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.annotations.QueryHints;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.util.UriComponentsBuilder;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition.Parameter;
import com.scipionyx.butterflyeffect.api.stocks.model.YahooData;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.StockPrice;
import com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common.SimpleElasticsearchWriter;

/**
 * 
 * @author Renato Mendes
 * 
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NYSE&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=AMEX&render=download
 */
@Configuration
@Definition(name = "jobLoadRealTimeData", //
		description = "This job will load all the symbols from the internet for a specific market", //
		instuctions = "provide the name of the markert", //
		category = "Stocks", service = "LoadRealTimeData", //
		restController = LoadRealTimeDataController.class, //
		parameters = { @Parameter(name = "symbol", type = String.class, description = ""),
				@Parameter(name = "exchange", type = String.class, description = "") })
public class LoadRealTimeDataJob extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext()
	private EntityManager entityManager;

	/**
	 * 
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobLoadRealTimeData")
	public Job jobStockImportSymbols(@Qualifier("jobLoadRealTimeData_Step01") Step step) {
		return jobBuilderFactory. //
				get("RT_STOCK_DATA_LOAD"). //
				incrementer(new RunIdIncrementer()). //
				flow(step). //
				end(). //
				build();

	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobLoadRealTimeData_Step01")
	public Step step1(@Qualifier("jobLoadRealTimeData_Step01_Reader") ItemReader<StockPrice> reader,
			@Qualifier("jobLoadRealTimeData_Step01_Processor") ItemProcessor<StockPrice, StockPrice> processor,
			@Qualifier("jobLoadRealTimeData_Step01_Writer") ItemWriter<StockPrice> writer)
			throws MalformedURLException {

		return stepBuilderFactory. //
				get("step1").<StockPrice, StockPrice>chunk(1000).//
				reader(reader).//
				processor(processor).//
				writer(writer).//
				build();

	}

	/**
	 * 
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@Bean("jobLoadRealTimeData_Step01_Reader")
	@StepScope
	public FlatFileItemReader<StockPrice> reader(@Value("#{jobParameters['symbol']}") String symbol,
			@Value("#{jobParameters['exchange']}") String exchange) throws URISyntaxException, IOException {

		// Calculate tokenizers
		Field[] declaredFields = StockPrice.class.getDeclaredFields();
		List<String> fieldNames = new ArrayList<>();
		List<String> queryCodes = new ArrayList<>();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(YahooData.class)) {
				YahooData annotation = field.getAnnotation(YahooData.class);
				fieldNames.add(field.getName());
				queryCodes.add(annotation.option());
			}
		}
		StringBuffer queryCode = new StringBuffer();
		queryCodes.forEach(code -> queryCode.append(code));

		// Defining the maper
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer() {
			{
				setStrict(true);
				setDelimiter(",");
				setQuoteCharacter(DEFAULT_QUOTE_CHARACTER);
				setNames(fieldNames.toArray(new String[0]));
			}
		};

		// Custom editors
		Map<Object, PropertyEditor> customEditors = new HashMap<>();
		customEditors.put(Double.class, new CustomNumberEditor(Double.class, true) {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (text.equalsIgnoreCase("n/a"))
					text = "";
				super.setAsText(text);
			}
		});

		//
		BeanWrapperFieldSetMapper<StockPrice> fieldSet = new BeanWrapperFieldSetMapper<StockPrice>() {
			{
				setTargetType(StockPrice.class);
				setStrict(true);
				setCustomEditors(customEditors);
			}
		};

		//
		DefaultLineMapper<StockPrice> mapper = new DefaultLineMapper<StockPrice>() {
			{
				setLineTokenizer(tokenizer);
				setFieldSetMapper(fieldSet);
			}
		};

		// Calculate the list of symbols that will be loaded
		final List<String> synmbols = new ArrayList<>();
		if (exchange != null) {
			Query query = entityManager.createQuery("from Stock s where s.exchange.code = :code")
					.setHint(QueryHints.READ_ONLY, Boolean.TRUE).setHint(QueryHints.CACHEABLE, Boolean.TRUE)
					.setParameter("code", exchange);
			((List<Stock>) query.getResultList()).forEach(stock -> synmbols.add(stock.getSymbol()));
		} else if (symbol.contains(",")) {
			synmbols.addAll(Arrays.asList(symbol.split(",")));
		}

		int size = 10;
		int i = 0;
		List<String> queries = new ArrayList<>();
		StringBuffer query = null;
		for (String stockSymbol : synmbols) {

			if (query == null) {
				query = new StringBuffer(stockSymbol);
			} else {
				query.append("+").append(stockSymbol);
			}

			i++;
			if (i == size) {
				queries.add(query.toString());
				query = null;
				i = 0;
			}

		}

		if (query != null) {
			queries.add(query.toString());
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		FlatFileItemReader<StockPrice> reader = new FlatFileItemReader<StockPrice>();

		for (String string : queries) {

			URI uri = new URI("http://finance.yahoo.com/d/quotes.csv");
			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri).queryParam("s", string).queryParam("f",
					queryCode.toString());

			// Read
			InputStream openStream = builder.build().toUri().toURL().openStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(openStream);
			byte[] buffer = new byte[bufferedInputStream.available()];
			bufferedInputStream.read(buffer);

			// Write
			byteArrayOutputStream.write(buffer);

		}

		// Set the reader
		reader.setResource(new ByteArrayResource(byteArrayOutputStream.toByteArray()));
		reader.setLineMapper(mapper);

		return reader;
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobLoadRealTimeData_Step01_Processor")
	@StepScope
	public ItemProcessor<StockPrice, StockPrice> getProcessor() {

		final ItemProcessor<StockPrice, StockPrice> itemProcessor = new ItemProcessor<StockPrice, StockPrice>() {

			private final Calendar now = Calendar.getInstance();

			private final long initial = now.getTimeInMillis() * 1000000;

			private final AtomicLong ATOMIC_INTEGER = new AtomicLong(initial);

			/**
			 * 
			 */
			@Override
			public StockPrice process(StockPrice item) throws Exception {
				//
				item.setRead(now.getTime());
				item.setId(ATOMIC_INTEGER.incrementAndGet());

				return item;
			}
		};

		//
		CompositeItemProcessor<StockPrice, StockPrice> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(Arrays.asList(itemProcessor));

		return compositeItemProcessor;

	}

	/**
	 * 
	 * @param repository
	 * @return
	 */
	@Bean(name = "jobLoadRealTimeData_Step01_Writer")
	public ItemWriter<StockPrice> writer(
			@Autowired(required = true) ElasticsearchRepository<StockPrice, Long> repository) {
		SimpleElasticsearchWriter<StockPrice> writer = new SimpleElasticsearchWriter<>(repository);
		return writer;
	}

}
