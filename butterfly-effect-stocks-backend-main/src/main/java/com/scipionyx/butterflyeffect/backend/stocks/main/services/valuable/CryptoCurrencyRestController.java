package com.scipionyx.butterflyeffect.backend.stocks.main.services.valuable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractJpaRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.CryptoCurrency;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/cryptocurrency/v1.0")
public class CryptoCurrencyRestController extends AbstractJpaRestController<CryptoCurrencyService, CryptoCurrency> {

}
