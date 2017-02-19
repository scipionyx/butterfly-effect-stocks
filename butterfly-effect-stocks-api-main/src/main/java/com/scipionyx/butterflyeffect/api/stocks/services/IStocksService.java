package com.scipionyx.butterflyeffect.api.stocks.services;

import org.springframework.data.repository.CrudRepository;

import com.scipionyx.butterflyeffect.api.infrastructure.services.IService;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IStocksService extends IService, CrudRepository<Stock, Long> {

}
