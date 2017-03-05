package com.scipionyx.butterflyeffect.api.stocks.services.data;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.scipionyx.butterflyeffect.api.stocks.model.Data;

/**
 * 
 * 
 * @author Renato Mendes
 *
 */
public interface IDataRepository extends ElasticsearchRepository<Data, Long> {

}
