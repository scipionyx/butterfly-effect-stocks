package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.loadRealTimeData;

import java.beans.PropertyEditor;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition.Parameter;
import com.scipionyx.butterflyeffect.api.stocks.model.Data;
import com.scipionyx.butterflyeffect.api.stocks.model.YahooData;

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
		restController = LoadRealtimeDataController.class, //
		parameters = { @Parameter(name = "symbol", type = String.class) })
public class LoadRealTimeDataJob extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
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
	public Step step1(@Qualifier("jobLoadRealTimeData_Step01_Reader") ItemReader<Data> reader,
			@Qualifier("jobLoadRealTimeData_Step01_Processor") ItemProcessor<Data, Data> processor,
			@Qualifier("jobLoadRealTimeData_Step01_writer") ItemWriter<Data> itemWriter) throws MalformedURLException {

		return stepBuilderFactory. //
				get("step1").<Data, Data>chunk(1000).//
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
	@Bean("jobLoadRealTimeData_Step01_Reader")
	@StepScope
	public FlatFileItemReader<Data> reader(@Value("#{jobParameters['symbol']}") String symbol)
			throws MalformedURLException, URISyntaxException {

		// Calculate tokenizers
		Field[] declaredFields = Data.class.getDeclaredFields();
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
		BeanWrapperFieldSetMapper<Data> fieldSet = new BeanWrapperFieldSetMapper<Data>() {
			{
				setTargetType(Data.class);
				setStrict(true);
				setCustomEditors(customEditors);
			}
		};

		//
		DefaultLineMapper<Data> mapper = new DefaultLineMapper<Data>() {
			{
				setLineTokenizer(tokenizer);
				setFieldSetMapper(fieldSet);
			}
		};

		// Defining the reader

		FlatFileItemReader<Data> reader = new FlatFileItemReader<Data>();
		URI uri = new URI("http://finance.yahoo.com/d/quotes.csv");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri).queryParam("s", symbol).queryParam("f",
				queryCode.toString());
		reader.setResource(new UrlResource(builder.build().toUri()));
		reader.setLineMapper(mapper);

		return reader;
	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobLoadRealTimeData_Step01_Processor")
	@StepScope
	public ItemProcessor<Data, Data> getProcessor() {

		ItemProcessor<Data, Data> itemProcessor = new ItemProcessor<Data, Data>() {

			@Override
			public Data process(Data item) throws Exception {
				return item;
			}
		};

		//
		CompositeItemProcessor<Data, Data> compositeItemProcessor = new CompositeItemProcessor<>();
		compositeItemProcessor.setDelegates(Arrays.asList(itemProcessor));

		return compositeItemProcessor;

	}

	/**
	 * 
	 * @return
	 */
	@Bean(name = "jobLoadRealTimeData_Step01_writer")
	@StepScope
	public ItemWriter<Data> getWriter() {

		ItemWriter<Data> itemWriter = new ItemWriter<Data>() {

			@Override
			public void write(List<? extends Data> items) throws Exception {
			}
		};

		return itemWriter;

	}

}
