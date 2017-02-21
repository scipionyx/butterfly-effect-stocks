package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.loadstocks;

import java.beans.PropertyEditor;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.stocks.model.Exchange;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;
import com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common.SimpleLookupItemProcessor;

/**
 * 
 * @author Renato Mendes
 * 
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NYSE&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=AMEX&render=download
 */
@Configuration
@Definition(name = "LoadStocks", description = "This job will load all the symbols from the internet for a specific market", instuctions = "provide the name of the markert", category = "Stocks", service = "LoadStocksJob", restController = LoadStocksController.class)
public class LoadStocksJob extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private JpaItemWriter<Stock> itemWriter;

	@Autowired
	private EntityManager entityManager;

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobStockImportSymbols")
	public Job importUserJob() throws MalformedURLException {
		Exchange exchange = Exchange.NASDAQ;
		if (exchange == null) {
			exchange = Exchange.NASDAQ;
		}
		return jobBuilderFactory. //
				get("STOCK_IMPORT_SYMBOL"). //
				incrementer(new RunIdIncrementer()). //
				flow(step1(exchange)). //
				end(). //
				build();
	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	// @Bean("jobStockImportSymbols_Step1")
	@Bean()
	public Step step1(Exchange exchange) throws MalformedURLException {
		return stepBuilderFactory. //
				get("step1").<Stock, Stock>chunk(1000).//
				reader(reader(exchange)).//
				processor(getProcessor()).//
				writer(itemWriter).//
				build();
	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean
	public FlatFileItemReader<Stock> reader(Exchange exchange) throws MalformedURLException {

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
		reader.setResource(new UrlResource("http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange="
				+ exchange + "&render=download"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(mapper);

		return reader;
	}

	/**
	 * 
	 */
	@Override
	public Job execute(Object... args) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public ItemProcessor<Stock, Stock> getProcessor() {
		SimpleLookupItemProcessor<Stock> processor = new SimpleLookupItemProcessor<Stock>(entityManager);
		processor.addLookupExpressions("from Stock a where a.symbol = :symbol", "symbol=symbol");
		return processor;
	}

}
