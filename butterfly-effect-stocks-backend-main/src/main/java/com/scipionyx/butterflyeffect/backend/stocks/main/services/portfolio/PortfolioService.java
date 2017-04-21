package com.scipionyx.butterflyeffect.backend.stocks.main.services.portfolio;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractJpaAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.portfolio.Portfolio;
import com.scipionyx.butterflyeffect.api.stocks.services.portfolio.IPortfolioRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class PortfolioService extends AbstractJpaAccessService<Portfolio, IPortfolioRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
