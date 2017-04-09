package com.scipionyx.butterflyeffect.backend.stocks.main.services.exchange;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractJpaRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/exchange/v1.0")
public class ExchangeRestController extends AbstractJpaRestController<ExchangeService, Exchange> {

}
