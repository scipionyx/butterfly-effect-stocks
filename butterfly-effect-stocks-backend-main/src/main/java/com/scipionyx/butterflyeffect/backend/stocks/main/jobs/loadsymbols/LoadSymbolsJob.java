package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.loadsymbols;

import java.beans.PropertyEditor;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition.Parameter;
import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;
import com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common.LookupItemProcessor;
import com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common.UpdateItemProcessor;

/**
 * 
 * @author Renato Mendes
 * 
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NYSE&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=AMEX&render=download
 */
@Configuration
@Definition(name = "LoadSymbols", //
		description = "This job will load all the symbols from the internet for a specific market", //
		instuctions = "provide the name of the markert", //
		category = "Stocks", service = "LoadStocksJob", //
		restController = LoadSymbolsController.class, //
		parameters = { @Parameter(name = "exchange", type = Exchange.class, description = "") })
public class LoadSymbolsJob extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobStockImportSymbols")
	public Job jobStockImportSymbols(@Qualifier("jobStockImportSymbols_Step01") Step step) {
		return jobBuilderFactory. //
				get("STOCK_IMPORT_SYMBOL"). //
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
	@Bean("jobStockImportSymbols_Step01")
	public Step step1(@Qualifier("jobStockImportSymbols_Step01_Reader") ItemReader<Stock> reader,
			@Qualifier("jobStockImportSymbols_Step01_Processor") ItemProcessor<Stock, Stock> processor,
			@Qualifier("SimpleJpaWriter") ItemWriter<Stock> itemWriter) throws MalformedURLException {

		return stepBuilderFactory. //
				get("step1").<Stock, Stock>chunk(1000).//
				reader(reader).//
				processor(processor).//
				writer(itemWriter).//
				build();

	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	@Bean("jobStockImportSymbols_Step01_Reader")
	@StepScope
	public FlatFileItemReader<Stock> reader(@Value("#{jobParameters['exchange']}") String exchange)
			throws MalformedURLException, URISyntaxException {

		// Defining the Mapper
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer() {
			{
				setStrict(false);
				setDelimiter(",");
				setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
				setNames(new String[] { "symbol", "name", "lastSale", "marketCap", "adr", "ipoYear", "sector",
						"industry", "summaryQuote" });
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
		BeanWrapperFieldSetMapper<Stock> fieldSet = new BeanWrapperFieldSetMapper<Stock>() {
			{
				setTargetType(Stock.class);
				setStrict(true);
				setCustomEditors(customEditors);
			}
		};

		//
		DefaultLineMapper<Stock> mapper = new DefaultLineMapper<Stock>() {
			{
				setLineTokenizer(tokenizer);
				setFieldSetMapper(fieldSet);
			}
		};

		// Defining the reader

		FlatFileItemReader<Stock> reader = new FlatFileItemReader<Stock>();
		URI uri = new URI("http://www.nasdaq.com/screening/companies-by-industry.aspx");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri).queryParam("exchange", exchange)
				.queryParam("render", "download");
		reader.setResource(new UrlResource(builder.build().toUri()));
		reader.setLinesToSkip(1);
		reader.setLineMapper(mapper);

		return reader;
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobStockImportSymbols_Step01_Processor")
	@StepScope
	public ItemProcessor<Stock, Stock> getProcessor(
			@Qualifier("jobStockImportSymbols_Step01_Processor_lookup") LookupItemProcessor<Stock> lookupProcessor,
			@Qualifier("jobStockImportSymbols_Step01_Processor_update") UpdateItemProcessor<Stock> updateProcessor,
			@Value("#{jobParameters['exchange']}") final String exchange) {

		//
		ItemProcessor<Stock, Stock> itemProcessor = new ItemProcessor<Stock, Stock>() {

			@Override
			public Stock process(Stock stock) throws Exception {
				if (stock.getExchange() == null) {
					Exchange exchange_ = new Exchange();
					exchange_.setCode(exchange);
					stock.setExchange(exchange_);
				}
				return stock;
			}
		};

		// Lookup Exchange

		//
		CompositeItemProcessor<Stock, Stock> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(Arrays.asList(updateProcessor, itemProcessor, lookupProcessor));

		return compositeItemProcessor;

	}

	/**
	 * 
	 * TODO - I'm not sure if the Entity Manager definition is correct, meaning
	 * Spring will inject the proper EM for the current thread.
	 * 
	 * @param entityManagerFactory
	 * @return
	 * @throws ClassNotFoundException
	 */
	@Bean(name = "jobStockImportSymbols_Step01_Processor_update")
	@StepScope
	public UpdateItemProcessor<Stock> updateProcessor(EntityManagerFactory entityManagerFactory)
			throws ClassNotFoundException {

		Assert.notNull(entityManagerFactory, "Entity Manager should not be null");

		UpdateItemProcessor<Stock> updateProcessor = new UpdateItemProcessor<>(entityManagerFactory);
		updateProcessor.addExpression("from Stock a where a.symbol = :symbol", "symbol=symbol");
		String[] pi = { "id", "exchange" };
		updateProcessor.setPropertiesToIgnore(pi);
		return updateProcessor;

	}

	/**
	 * 
	 * TODO - I'm not sure if the Entity Manager definition is correct, meaning
	 * Spring will inject the proper EM for the current thread.
	 * 
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean(name = "jobStockImportSymbols_Step01_Processor_lookup")
	@StepScope
	public LookupItemProcessor<Stock> lookupProcessor(EntityManagerFactory entityManagerFactory) {

		Assert.notNull(entityManagerFactory, "Entity Manager should not be null");

		LookupItemProcessor<Stock> updateProcessor = new LookupItemProcessor<>(entityManagerFactory);
		Map<String, String> hqlExpressions = new HashMap<>();
		hqlExpressions.put("code", "exchange.code");
		updateProcessor.addExpression("01", "exchange", "from Exchange a where a.code = :code", hqlExpressions);
		return updateProcessor;

	}

}
