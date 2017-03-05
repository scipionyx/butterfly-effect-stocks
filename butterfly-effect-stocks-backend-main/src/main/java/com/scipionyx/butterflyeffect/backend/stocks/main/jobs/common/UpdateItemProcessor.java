package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.annotations.QueryHints;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Renato Mendes
 *
 * @param <T>
 * @param <T>
 */
@Component
@StepScope
public class UpdateItemProcessor<T> implements ItemProcessor<T, T> {

	private Map<String, Map<String, Expression>> lookupExpressions;

	private ExpressionParser parser;

	@PersistenceContext
	private EntityManager entityManager;

	private EntityManagerFactory entityManagerFactory;

	private String[] propertiesToIgnore;

	/**
	 * 
	 * @param entityManager
	 */
	public UpdateItemProcessor() {

	}

	/**
	 * 
	 * @param entityManager
	 */
	public UpdateItemProcessor(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * 
	 */
	@Override
	public T process(T item) throws Exception {

		if (entityManager == null) {
			entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
		}

		for (String query : lookupExpressions.keySet()) {

			Query createQuery = entityManager.createQuery(query);
			createQuery.setMaxResults(1);
			createQuery.setHint(QueryHints.CACHEABLE, Boolean.TRUE);
			createQuery.setHint(QueryHints.READ_ONLY, Boolean.TRUE);

			Map<String, Expression> map = lookupExpressions.get(query);

			for (String field : map.keySet()) {
				Expression expression = map.get(field);
				createQuery.setParameter(field, expression.getValue(item));
			}

			@SuppressWarnings("unchecked")
			List<T> resultList = createQuery.getResultList();

			if (resultList.size() == 1) {
				// Update
				T target = resultList.get(0);
				if (propertiesToIgnore != null) {
					BeanUtils.copyProperties(item, target, propertiesToIgnore);
				} else {
					BeanUtils.copyProperties(item, target);
				}
				return target;
			} else
				return item;

		}

		return item;
	}

	/**
	 * 
	 * 
	 * @param hql
	 * @param expressions
	 */
	public void addExpression(String hql, String... expressions) {

		if (lookupExpressions == null) {
			lookupExpressions = new HashMap<>();
		}

		if (parser == null) {
			parser = new SpelExpressionParser();
		}

		// tokenize expressions
		Map<String, Expression> expressionMap = new HashMap<>();
		for (String rawExpression : expressions) {
			int indexOf = rawExpression.indexOf("=");
			if (indexOf > 0) {
				expressionMap.put(rawExpression.substring(0, indexOf),
						parser.parseExpression(rawExpression.substring(indexOf + 1)));
			}
		}

		lookupExpressions.put(hql, expressionMap);

	}

	/**
	 * @return the propertiesToIgnore
	 */
	public String[] getPropertiesToIgnore() {
		return propertiesToIgnore;
	}

	/**
	 * @param propertiesToIgnore
	 *            the propertiesToIgnore to set
	 */
	public void setPropertiesToIgnore(String[] propertiesToIgnore) {
		this.propertiesToIgnore = propertiesToIgnore;
	}

}
