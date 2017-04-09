package com.scipionyx.butterflyeffect.backend.stocks.main.services.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractElasticsearchRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.StockPrice;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/data/v1.0")
public class DataRestController extends AbstractElasticsearchRestController<DataService, StockPrice> {

}
