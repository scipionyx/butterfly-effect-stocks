package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.loadexchange;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.net.MalformedURLException;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;
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
@Definition(name = "ExchangeImport", //
		description = "This job will load all the symbols from the internet for a specific market", //
		instuctions = "provide the name of the markert", //
		category = "Stocks", service = "LoadExchange", //
		restController = LoadExchangeController.class, //
		parameters = {})
public class LoadExchangeJob extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobExchangeImport")
	public Job jobExchangeImport(@Qualifier("jobExchangeImport_Step01") Step step) {
		return jobBuilderFactory. //
				get("STOCK_IMPORT_EXCHANGE"). //
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
	@Bean("jobExchangeImport_Step01")
	public Step step1(@Qualifier("jobExchangeImport_Step01_Reader") ItemReader<Exchange> reader,
			@Qualifier("jobExchangeImport_Step01_Processor") ItemProcessor<Exchange, Exchange> processor,
			@Qualifier("SimpleJpaWriter") ItemWriter<Exchange> itemWriter) throws MalformedURLException {

		return stepBuilderFactory. //
				get("step1").<Exchange, Exchange>chunk(1000).//
				reader(reader).//
				processor(processor).//
				writer(itemWriter).//
				build();

	}

	/**
	 * 
	 * @return
	 */
	@Bean("jobExchangeImport_Step01_Reader")
	@StepScope
	public FlatFileItemReader<Exchange> reader() {

		// Defining the Mapping
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer() {
			{
				setStrict(false);
				setDelimiter(",");
				setIncludedFields(new int[] { 0, 1, 2, 3 });
				setNames(new String[] { "code", "name", "country", "currency" });
			}
		};

		// Custom editors
		Map<Object, PropertyEditor> customEditors = new HashMap<>();
		customEditors.put(CountryCode.class, new PropertyEditorSupport() {

			private CountryCode countryCode;

			@Override
			public void setValue(Object value) {
				this.countryCode = (CountryCode) value;
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				CountryCode byCode = CountryCode.getByCode(text);
				setValue(byCode);
			}

			@Override
			public Object getValue() {
				return countryCode;
			}

			@Override
			public String getAsText() {
				return countryCode.getAlpha2();
			}

		});
		customEditors.put(CurrencyCode.class, new PropertyEditorSupport() {

			private CurrencyCode currencyCode;

			@Override
			public void setValue(Object value) {
				this.currencyCode = (CurrencyCode) value;
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				CurrencyCode byCode = CurrencyCode.getByCode(text);
				setValue(byCode);
			}

			@Override
			public Object getValue() {
				return currencyCode;
			}

			@Override
			public String getAsText() {
				return currencyCode.getCurrency().getCurrencyCode();
			}

		});

		//
		BeanWrapperFieldSetMapper<Exchange> fieldSet = new BeanWrapperFieldSetMapper<Exchange>() {
			{
				setTargetType(Exchange.class);
				setStrict(true);
				setCustomEditors(customEditors);
			}
		};

		//
		DefaultLineMapper<Exchange> mapper = new DefaultLineMapper<Exchange>() {
			{
				setLineTokenizer(tokenizer);
				setFieldSetMapper(fieldSet);

			}
		};

		// Defining the reader
		FlatFileItemReader<Exchange> reader = new FlatFileItemReader<Exchange>();
		reader.setResource(new ClassPathResource("exchange.csv"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(mapper);

		return reader;
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobExchangeImport_Step01_Processor")
	@StepScope
	public ItemProcessor<Exchange, Exchange> getProcessor(
			@Qualifier("jobExchangeImport_Step01_Processor_update") UpdateItemProcessor<Exchange> processor) {

		//
		processor.addExpression("from Exchange a where a.code = :code", "code=code");

		//
		ItemProcessor<Exchange, Exchange> itemProcessor = new ItemProcessor<Exchange, Exchange>() {

			@Override
			public Exchange process(Exchange exchange) throws Exception {
				return exchange;
			}
		};

		//
		CompositeItemProcessor<Exchange, Exchange> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(Arrays.asList(processor, itemProcessor));

		return compositeItemProcessor;
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobExchangeImport_Step01_Processor_update")
	@StepScope
	public UpdateItemProcessor<Exchange> getUpdateItemProcessor(EntityManagerFactory entityManagerFactory) {
		UpdateItemProcessor<Exchange> updateItemProcessor = new UpdateItemProcessor<>(entityManagerFactory);
		String[] pti = { "id" };
		updateItemProcessor.setPropertiesToIgnore(pti);
		return updateItemProcessor;
	}

}
