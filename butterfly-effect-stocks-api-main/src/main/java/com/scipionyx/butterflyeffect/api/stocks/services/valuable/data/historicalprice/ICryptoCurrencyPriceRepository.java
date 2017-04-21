package com.scipionyx.butterflyeffect.api.stocks.services.valuable.data.historicalprice;

import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice.CryptoCurrencyPrice;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface ICryptoCurrencyPriceRepository extends ElasticsearchRepository<CryptoCurrencyPrice, Long> {

	public List<CryptoCurrencyPrice> findByDateGreaterThan(Date date);

}
