package com.scipionyx.butterflyeffect.frontend.stocks.main.services;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientWithCrudService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.RESTService;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "stocks", subModule = "cryptocurrencyprice", version = "v1.0")
public class CryptoCurrencyPriceClientService extends AbstractRESTClientWithCrudService<CryptoCurrencyPrice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
