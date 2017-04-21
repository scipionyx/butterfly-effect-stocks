package com.scipionyx.butterflyeffect.backend.stocks.main.services.valuable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractJpaRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Valuable;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/valuable/v1.0")
public class ValuableRestController extends AbstractJpaRestController<ValuableService, Valuable> {

}
