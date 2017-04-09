package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 
 * @author Renato Mendes
 *
 * @param <T>
 */
public class SimpleElasticsearchWriter<ENTITY> implements ItemWriter<ENTITY> {

	private ElasticsearchRepository<ENTITY, Long> service;

	/**
	 * 
	 * @param service
	 */
	public SimpleElasticsearchWriter(ElasticsearchRepository<ENTITY, Long> service) {
		super();
		this.service = service;
	}

	/**
	 * 
	 */
	@Override
	public void write(List<? extends ENTITY> items) throws Exception {
		service.save(items);
	}

}
