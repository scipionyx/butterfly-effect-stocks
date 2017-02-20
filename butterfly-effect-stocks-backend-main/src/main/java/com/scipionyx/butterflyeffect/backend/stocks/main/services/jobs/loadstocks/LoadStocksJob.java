package com.scipionyx.butterflyeffect.backend.stocks.main.services.jobs.loadstocks;

import java.beans.PropertyEditor;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;
import com.scipionyx.butterflyeffect.backend.stocks.main.services.jobs.common.SimpleLookupItemProcessor;

/**
 * 
 * @author Renato Mendes
 * 
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NYSE&render=download
 *         http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=AMEX&render=download
 */
@Component
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

	private SimpleLookupItemProcessor<Stock> processor;

	@PostConstruct
	public void init() {
		processor = new SimpleLookupItemProcessor<Stock>(entityManager);
		processor.addLookupExpressions("from Stock a where a.symbol = :symbol", "symbol=symbol");
	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public Job importUserJob() throws MalformedURLException {
		return jobBuilderFactory. //
				get("importUserJob"). //
				incrementer(new RunIdIncrementer()). //
				flow(step1()). //
				end(). //
				build();
	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public Step step1() throws MalformedURLException {
		return stepBuilderFactory. //
				get("step1").<Stock, Stock>chunk(1000).//
				reader(reader()).//
				processor(getProcessor()).//
				writer(itemWriter).//
				build();
	}

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public FlatFileItemReader<Stock> reader() throws MalformedURLException {

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
		reader.setResource(new UrlResource(
				"http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=NASDAQ&render=download"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(mapper);

		return reader;
	}

	/**
	 * 
	 */
	@Override
	public Job execute(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemProcessor<Stock, Stock> getProcessor() {
		return processor;
	}

}
