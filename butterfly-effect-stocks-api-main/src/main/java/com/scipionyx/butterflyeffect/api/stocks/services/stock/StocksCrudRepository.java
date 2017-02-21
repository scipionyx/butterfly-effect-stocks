package com.scipionyx.butterflyeffect.api.stocks.services.stock;

import org.springframework.data.repository.CrudRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.Stock;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface StocksCrudRepository extends CrudRepository<Stock, Long> {

}
