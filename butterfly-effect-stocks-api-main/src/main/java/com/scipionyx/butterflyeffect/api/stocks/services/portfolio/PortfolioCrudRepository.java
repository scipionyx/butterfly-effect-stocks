package com.scipionyx.butterflyeffect.api.stocks.services.portfolio;

import org.springframework.data.repository.CrudRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.Portfolio;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface PortfolioCrudRepository extends CrudRepository<Portfolio, Long> {

}