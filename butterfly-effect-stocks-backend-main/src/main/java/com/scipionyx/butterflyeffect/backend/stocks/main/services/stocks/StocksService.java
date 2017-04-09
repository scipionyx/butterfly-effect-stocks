package com.scipionyx.butterflyeffect.backend.stocks.main.services.stocks;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractDataAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;
import com.scipionyx.butterflyeffect.api.stocks.services.valuable.information.stock.IStocksRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class StocksService extends AbstractDataAccessService<Stock, IStocksRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
