package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common;

import java.util.List;

import javax.persistence.EntityManager;
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
@Component(value = "SimpleJpaWriter")
public class SimpleJpaWriter<T> extends JpaItemWriter<T> {

	@Override
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		super.setEntityManagerFactory(entityManagerFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.batch.item.database.JpaItemWriter#doWrite(javax.
	 * persistence.EntityManager, java.util.List)
	 */
	@Override
	protected void doWrite(EntityManager entityManager, List<? extends T> items) {
		super.doWrite(entityManager, items);
	}

}
