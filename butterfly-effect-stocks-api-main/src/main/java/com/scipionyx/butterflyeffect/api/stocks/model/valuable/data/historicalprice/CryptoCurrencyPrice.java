package com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "s_crypto_currency_data", shards = 3, createIndex = true, replicas = 2)
public class CryptoCurrencyPrice extends HistoricalPrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
