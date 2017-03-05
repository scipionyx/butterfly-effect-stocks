package com.scipionyx.butterflyeffect.backend.stocks.main.services.data;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractElasticsearchAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.Data;
import com.scipionyx.butterflyeffect.api.stocks.services.data.IDataRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class DataService extends AbstractElasticsearchAccessService<Data, IDataRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
