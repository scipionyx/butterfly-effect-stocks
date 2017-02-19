package com.scipionyx.butterflyeffect.api.stocks.services;

import org.springframework.stereotype.Component;

import com.scipionyx.butterflyeffect.api.infrastructure.services.RESTService;
import com.scipionyx.butterflyeffect.api.infrastructure.services.client.AbstractRESTClientWithCrudService;
import com.scipionyx.butterflyeffect.api.stocks.model.Stock;

/**
 * 
 * 
 * 
 * @author Renato Mendes
 *
 */
@Component
@RESTService(module = "jobmanagement", subModule = "job", version = "v1.0")
public class StocksRESTClientService extends AbstractRESTClientWithCrudService<Stock> implements IStocksService {

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Stock arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Stock> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Stock> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Stock> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock findOne(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Stock> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Stock> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
