package com.scipionyx.butterflyeffect.backend.stocks.main.services.data;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.server.data.AbstractElasticsearchAccessService;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.StockPrice;
import com.scipionyx.butterflyeffect.api.stocks.services.valuable.data.historicalprice.IStockPriceRepository;

/**
 *
 * 
 * @author Renato Mendes
 *
 */
@Component
public class DataService extends AbstractElasticsearchAccessService<StockPrice, IStockPriceRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
