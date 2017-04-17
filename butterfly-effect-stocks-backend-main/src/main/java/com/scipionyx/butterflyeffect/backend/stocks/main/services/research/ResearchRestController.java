package com.scipionyx.butterflyeffect.backend.stocks.main.services.research;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.controller.AbstractJpaRestController;
import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
@RestController()
@RequestMapping("REST_SERVICES/scipionyx/stocks/research/v1.0")
public class ResearchRestController extends AbstractJpaRestController<ResearchService, Research> {

}
