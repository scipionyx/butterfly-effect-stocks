package com.scipionyx.butterflyeffect.backend.stocks.main.services.valuable.data.historicalprice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractElasticsearchRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/cryptocurrencyprice/v1.0")
public class CryptoCurrencyPriceRestController
		extends AbstractElasticsearchRestController<CryptoCurrencyPriceService, CryptoCurrencyPrice> {

}
