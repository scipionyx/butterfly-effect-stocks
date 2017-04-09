package com.scipionyx.butterflyeffect.api.stocks.services.valuable.data.historicalprice;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.StockPrice;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IStockPriceRepository extends ElasticsearchRepository<StockPrice, Long> {

}
