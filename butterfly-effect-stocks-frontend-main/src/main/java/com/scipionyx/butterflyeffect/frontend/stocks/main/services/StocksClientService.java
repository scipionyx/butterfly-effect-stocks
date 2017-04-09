package com.scipionyx.butterflyeffect.frontend.stocks.main.services;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientWithCrudService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.RESTService;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "stocks", subModule = "stock", version = "v1.0")
public class StocksClientService extends AbstractRESTClientWithCrudService<Stock> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
