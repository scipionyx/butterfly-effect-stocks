package com.scipionyx.butterflyeffect.backend.stocks.main.services.stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.server.IHasRepository;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class StocksService implements IService, IHasRepository<Stock> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired(required = true)
	private StocksRepository repository;

	/**
	 * 
	 */
	@Override
	public String ping() throws RestClientException, Exception {
		return "I'm good, and what about u ? " + (repository != null);
	}

	/**
	 * 
	 */
	@Override
	public String health() throws RestClientException, Exception {
		return "ok";
	}

	public StocksRepository getRepository() {
		return repository;
	}

}
