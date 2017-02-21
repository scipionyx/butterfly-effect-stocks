package com.scipionyx.butterflyeffect.backend.stocks.main.services.portfolio;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.AbstractDataAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;
import com.scipionyx.butterflyeffect.api.stocks.services.portfolio.PortfolioCrudRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class PortfolioService extends AbstractDataAccessService<Portfolio, PortfolioCrudRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
