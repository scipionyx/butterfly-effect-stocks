package com.scipionyx.butterflyeffect.frontend.stocks.main.services;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientWithCrudService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.RESTService;
import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "stocks", subModule = "research", version = "v1.0")
public class ResearchClientService extends AbstractRESTClientWithCrudService<Research> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
