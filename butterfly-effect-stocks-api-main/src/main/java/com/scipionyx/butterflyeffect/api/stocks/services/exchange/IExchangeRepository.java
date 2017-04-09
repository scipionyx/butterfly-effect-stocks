package com.scipionyx.butterflyeffect.api.stocks.services.exchange;

import org.springframework.data.repository.CrudRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IExchangeRepository extends CrudRepository<Exchange, Long> {

}
