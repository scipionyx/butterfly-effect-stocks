package com.scipionyx.butterflyeffect.backend.stocks.main.services.valuable.data.historicalprice;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractElasticsearchAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;
import com.scipionyx.butterflyeffect.api.stocks.services.valuable.data.historicalprice.ICryptoCurrencyPriceRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class CryptoCurrencyPriceService
		extends AbstractElasticsearchAccessService<CryptoCurrencyPrice, ICryptoCurrencyPriceRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
