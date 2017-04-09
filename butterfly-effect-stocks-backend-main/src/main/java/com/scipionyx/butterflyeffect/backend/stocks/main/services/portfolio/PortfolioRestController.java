package com.scipionyx.butterflyeffect.backend.stocks.main.services.portfolio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractJpaRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.portfolio.Portfolio;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/portfolio/v1.0")
public class PortfolioRestController extends AbstractJpaRestController<PortfolioService, Portfolio> {

}
