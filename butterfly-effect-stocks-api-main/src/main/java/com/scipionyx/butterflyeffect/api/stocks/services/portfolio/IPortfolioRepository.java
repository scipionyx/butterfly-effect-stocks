package com.scipionyx.butterflyeffect.api.stocks.services.portfolio;

import org.springframework.data.repository.CrudRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.portfolio.Portfolio;

/**
 *
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IPortfolioRepository extends CrudRepository<Portfolio, Long> {

}
