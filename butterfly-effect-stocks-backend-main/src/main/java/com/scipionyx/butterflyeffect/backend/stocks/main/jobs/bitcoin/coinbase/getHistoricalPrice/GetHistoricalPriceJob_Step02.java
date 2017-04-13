package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.bitcoin.coinbase.getHistoricalPrice;

import java.net.MalformedURLException;

import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scipionyx.butterflyeffect.api.jobmanagement.api.model.definition.AbstractJobDefinition;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;

/**
 *
 * Load the historical price for the Bitcoin from the coinbase system. <br>
 * Step 02
 * 
 * @author Renato Mendes
 * 
 */
@Configuration
public class GetHistoricalPriceJob_Step02 extends AbstractJobDefinition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin_Step02")
	public Step step02(
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step02_Reader") ItemReader<CryptoCurrencyPrice> reader,
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step02_Processor") ItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice> processor,
			@Qualifier("jobGetHistoricalPrice_Coinbase_bitcoin_Step02_Writer") ItemWriter<CryptoCurrencyPrice> writer) {

		return stepBuilderFactory. //
				get("step2").<CryptoCurrencyPrice, CryptoCurrencyPrice>chunk(1000).//
				reader(reader).//
				processor(processor).//
				writer(writer).//
				build();

	}

	/**
	 * 
	 * @return
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin_Step02_Reader")
	public ItemReader<CryptoCurrencyPrice> readerStep02() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin_Step02_Writer")
	public ItemWriter<CryptoCurrencyPrice> writerStep02() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@Bean("jobGetHistoricalPrice_Coinbase_bitcoin_Step02_Processor")
	public ItemProcessor<CryptoCurrencyPrice, CryptoCurrencyPrice> processorStep02() {
		return null;
	}

}
