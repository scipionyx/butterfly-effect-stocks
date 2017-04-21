package com.scipionyx.butterflyeffect.backend.stocks.main.services.valuable;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractJpaAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Valuable;
import com.scipionyx.butterflyeffect.api.stocks.services.valuable.IValuableRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class ValuableService extends AbstractJpaAccessService<Valuable, IValuableRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
