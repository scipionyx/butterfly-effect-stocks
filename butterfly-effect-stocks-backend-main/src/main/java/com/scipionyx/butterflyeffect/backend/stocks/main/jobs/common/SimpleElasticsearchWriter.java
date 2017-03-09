package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Renato Mendes
 *
 * @param <T>
 */
@Component(value = "SimpleElasticsearchWriter")
public class SimpleElasticsearchWriter<ENTITY> implements ItemWriter<ENTITY> {

	@Autowired(required = true)
	private ElasticsearchRepository<ENTITY, Long> service;

	/**
	 * 
	 */
	@Override
	public void write(List<? extends ENTITY> items) throws Exception {
		service.save(items);
	}

}
