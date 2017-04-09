package com.scipionyx.butterflyeffect.backend.stocks.main.services.stocks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractJpaRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/stock/v1.0")
public class StocksRestController extends AbstractJpaRestController<StocksService, Stock> {

}
