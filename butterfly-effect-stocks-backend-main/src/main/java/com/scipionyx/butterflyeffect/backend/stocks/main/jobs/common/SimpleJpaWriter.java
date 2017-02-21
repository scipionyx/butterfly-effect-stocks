package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * This JPA Writer only write the entities, no lookup happens.
 * 
 * @author Renato mendes
 *
 * @param <T>
 */
@Component
public class SimpleJpaWriter<T> extends JpaItemWriter<T> {

	@Override
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		super.setEntityManagerFactory(entityManagerFactory);
	}

}
