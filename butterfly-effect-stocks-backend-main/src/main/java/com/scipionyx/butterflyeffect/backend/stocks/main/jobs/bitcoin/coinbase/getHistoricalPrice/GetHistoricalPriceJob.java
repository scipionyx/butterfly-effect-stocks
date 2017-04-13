package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.bitcoin.coinbase.getHistoricalPrice;

import java.net.MalformedURLException;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition;
import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.Definition.Parameter;

/**
 *
 * Load the historical price for the Bitcoin from the coinbase system.
 * 
 * @author Renato Mendes
 * 
 */
@Configuration
@Definition(name = "GetHistoricalPrice_Coinbase_Bitcoin", //
		description = "This job will load all the prices for the Bitcoin from the Coinbase system", //
		instuctions = "provide the initial date", //
		category = "BitCoin", service = "Coinbase", //
		restController = GetHistoricalPriceController.class, //
		parameters = {
				@Parameter(name = "dateFrom", type = Date.class, description = "This is the inital date for the historicla load") })
public class GetHistoricalPriceJob extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin")
	public Job jobGetHistoricalPrice(@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step01") Step step1,
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step02") Step step2) {
		return jobBuilderFactory. //
				get("GetHistoricalPrice_Coinbase_bitcoin"). //
				incrementer(new RunIdIncrementer()). //
				flow(step1).next(step2). //
				end(). //
				build();

	}

}
