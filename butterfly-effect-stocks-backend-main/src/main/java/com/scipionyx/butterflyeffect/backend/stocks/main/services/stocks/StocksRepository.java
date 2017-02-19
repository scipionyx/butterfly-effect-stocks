package com.scipionyx.butterflyeffect.backend.stocks.main.services.stocks;

import org.springframework.data.repository.CrudRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.Stock;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface StocksRepository extends CrudRepository<Stock, Long> {

}
