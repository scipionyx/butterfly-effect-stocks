package com.scipionyx.butterflyeffect.backend.stocks.main.services.exchange;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.AbstractDataAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.Exchange;
import com.scipionyx.butterflyeffect.api.stocks.services.exchange.ExchangeCrudRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class ExchangeService extends AbstractDataAccessService<Exchange, ExchangeCrudRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
