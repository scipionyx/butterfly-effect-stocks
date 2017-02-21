package com.scipionyx.butterflyeffect.api.stocks.services.portfolio;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.RESTService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientWithCrudService;
import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "stocks", subModule = "stock", version = "v1.0")
public class PortfolioClientService extends AbstractRESTClientWithCrudService<Portfolio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
