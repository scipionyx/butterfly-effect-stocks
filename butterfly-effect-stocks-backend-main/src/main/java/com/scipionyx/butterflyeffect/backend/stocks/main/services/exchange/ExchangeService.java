package com.scipionyx.butterflyeffect.backend.stocks.main.services.exchange;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractJpaAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;
import com.scipionyx.butterflyeffect.api.stocks.services.exchange.IExchangeRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class ExchangeService extends AbstractJpaAccessService<Exchange, IExchangeRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
