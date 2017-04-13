package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.bitcoin.coinbase.getHistoricalPrice;

import java.beans.PropertyEditor;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.util.UriComponentsBuilder;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.Source;
import com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common.SimpleElasticsearchWriter;

/**
 *
 * Load the historical price for the Bitcoin from the coinbase system.
 * 
 * @author Renato Mendes
 * 
 */
@Configuration
public class GetHistoricalPriceJob_Step01 extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin_Step01")
	public Step step01(
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step01_Reader") ItemReader<CryptoCurrencyPrice> reader,
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step01_Processor") ItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice> processor,
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step01_Writer") ItemWriter<CryptoCurrencyPrice> writer) {

		return stepBuilderFactory. //
				get("step1").<CryptoCurrencyPrice, CryptoCurrencyPrice>chunk(1000).//
				reader(reader).//
				processor(processor).//
				writer(writer).//
				build();

	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin_Step01_Reader")
	@StepScope
	public ItemReader<CryptoCurrencyPrice> reader01() throws MalformedURLException, URISyntaxException {

		// Defining the Mapper
		// 2014-01-06T00:25:24-08:00,10.0
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer() {
			{
				setStrict(false);
				setDelimiter(",");
				setIncludedFields(new int[] { 0, 1 });
				setNames(new String[] { "date", "price" });
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
		customEditors.put(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"), false));

		//
		BeanWrapperFieldSetMapper<CryptoCurrencyPrice> fieldSet = new BeanWrapperFieldSetMapper<CryptoCurrencyPrice>() {
			{
				setTargetType(CryptoCurrencyPrice.class);
				setStrict(true);
				setCustomEditors(customEditors);
			}
		};

		//
		DefaultLineMapper<CryptoCurrencyPrice> mapper = new DefaultLineMapper<CryptoCurrencyPrice>() {
			{
				setLineTokenizer(tokenizer);
				setFieldSetMapper(fieldSet);
			}
		};

		// Defining the reader
		FlatFileItemReader<CryptoCurrencyPrice> reader = new FlatFileItemReader<CryptoCurrencyPrice>();
		reader.setLinesToSkip(0);
		reader.setLineMapper(mapper);

		// multiple pages
		MultiResourceItemReader<CryptoCurrencyPrice> itemReader = new MultiResourceItemReader<>();
		List<Resource> resources = new ArrayList<>();
		URI uri = new URI("https://api.coinbase.com/v1/prices/historical");
		for (int i = 1; i < 238; i++) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri).queryParam("page", i);
			Resource resource = new UrlResource(builder.build().toUri());
			resources.add(resource);
		}
		itemReader.setResources(resources.toArray(new Resource[resources.size()]));
		itemReader.setDelegate(reader);

		return itemReader;
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobGetHistoricalPrice_Coinbase_bitcoin_Step01_Processor")
	@StepScope
	public ItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice> getProcessor01() {

		//
		ItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice> itemProcessor = new ItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice>() {

			private final Calendar now = Calendar.getInstance();

			private final long initial = now.getTimeInMillis() * 1000000;

			private final AtomicLong ATOMIC_INTEGER = new AtomicLong(initial);

			@Override
			public CryptoCurrencyPrice process(CryptoCurrencyPrice cryptoCurrencyPrice) throws Exception {
				cryptoCurrencyPrice.setId(ATOMIC_INTEGER.incrementAndGet());
				cryptoCurrencyPrice.setSource(Source.Coinbase);
				cryptoCurrencyPrice.setRead(Calendar.getInstance().getTime());
				// cryptoCurrencyPrice.setValuable(valuable);
				return cryptoCurrencyPrice;
			}
		};

		//
		CompositeItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(Arrays.asList(itemProcessor));

		return compositeItemProcessor;

	}

	/**
	 * 
	 * @param repository
	 * @return
	 */
	@Bean(name = "jobGetHistoricalPrice_Coinbase_bitcoin_Step01_Writer")
	public ItemWriter<CryptoCurrencyPrice> writer01(
			@Autowired(required = true) ElasticsearchRepository<CryptoCurrencyPrice, Long> repository) {
		SimpleElasticsearchWriter<CryptoCurrencyPrice> writer = new SimpleElasticsearchWriter<>(repository);
		return writer;
	}

}
