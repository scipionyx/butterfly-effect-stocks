package com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 
 * 
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
@Document(indexName = "s_crypto_currency_data", createIndex = true)
public class CryptoCurrencyPrice extends HistoricalPrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
