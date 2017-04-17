package com.scipionyx.butterflyeffect.backend.stocks.main.services.research;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractDataAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.research.Research;
import com.scipionyx.butterflyeffect.api.stocks.services.research.IResearchRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class ResearchService extends AbstractDataAccessService<Research, IResearchRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
