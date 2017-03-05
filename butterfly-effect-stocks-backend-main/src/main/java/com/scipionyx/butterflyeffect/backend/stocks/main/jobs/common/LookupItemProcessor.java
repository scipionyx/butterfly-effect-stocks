package com.scipionyx.butterflyeffect.backend.stocks.main.jobs.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.annotations.QueryHints;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

/**
 * 
 * 
 * 
 * 
 * @author Renato Mendes
 *
 * @param <T>
 * @param <T>
 */
public class LookupItemProcessor<T> implements ItemProcessor<T, T> {

	private List<CompiledExpression> compiledExpressions;

	private ExpressionParser parser;

	private EntityManagerFactory entityManagerFactory;

	/**
	 * 
	 * 
	 * 
	 * @param entityManager
	 */
	public LookupItemProcessor(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * 
	 */
	@Override
	public T process(T item) throws Exception {

		EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);

		for (CompiledExpression compiledExpression : compiledExpressions) {

			// Find the Value
			Query query = entityManager.createQuery(compiledExpression.getHql());
			query.setMaxResults(1);
			query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);
			query.setHint(QueryHints.READ_ONLY, Boolean.TRUE);

			Map<String, Expression> map = compiledExpression.getExpressions();

			for (String field : map.keySet()) {
				Expression expression = map.get(field);
				query.setParameter(field, expression.getValue(item));
			}
			
			List<?> resultList = query.getResultList();

			if (resultList.size() == 1) {
				compiledExpression.getTarget().setValue(item, resultList.get(0));
			} else {
				throw new Exception("Lookup failed hql: " + compiledExpression.getHql());
			}

		}

		return item;
	}

	/**
	 * 
	 * 
	 * @param expression
	 */
	public void addExpression(RawExpression expression) {

		if (compiledExpressions == null) {
			compiledExpressions = new ArrayList<>();
		}

		if (parser == null) {
			parser = new SpelExpressionParser();
		}

		//
		CompiledExpression compiledExpression = new CompiledExpression();
		compiledExpression.setId(expression.getId());
		compiledExpression.setTarget(parser.parseExpression(expression.getTarget()));
		compiledExpression.setHql(expression.hql);
		compiledExpression.setExpressions(new HashMap<>());

		//
		for (String hqlField : expression.getHqlExpressions().keySet()) {
			compiledExpression.getExpressions().put(hqlField,
					parser.parseExpression(expression.getHqlExpressions().get(hqlField)));
		}

		compiledExpressions.add(compiledExpression);

	}

	/**
	 * 
	 * @param expression
	 */
	public void addExpression(String id, String target, String hql, Map<String, String> hqlExpressions) {
		addExpression(new RawExpression(id, target, hql, hqlExpressions));
	}

	/**
	 * 
	 * 
	 * 
	 * @author Renato Mendes
	 *
	 */
	private class CompiledExpression {

		private String id;
		private Expression target;
		private String hql;
		private Map<String, Expression> expressions;

		/**
		 * @return the id
		 */
		@SuppressWarnings("unused")
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the target
		 */
		public Expression getTarget() {
			return target;
		}

		/**
		 * @param target
		 *            the target to set
		 */
		public void setTarget(Expression target) {
			this.target = target;
		}

		/**
		 * @return the hql
		 */
		public String getHql() {
			return hql;
		}

		/**
		 * @param hql
		 *            the hql to set
		 */
		public void setHql(String hql) {
			this.hql = hql;
		}

		/**
		 * @return the expressions
		 */
		public Map<String, Expression> getExpressions() {
			return expressions;
		}

		/**
		 * @param expressions
		 *            the expressions to set
		 */
		public void setExpressions(Map<String, Expression> expressions) {
			this.expressions = expressions;
		}

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @author Renato Mendes
	 *
	 */
	private class RawExpression {

		private String id;
		private String target;
		private String hql;
		private Map<String, String> hqlExpressions;

		/**
		 * 
		 * @param id
		 * @param target
		 * @param hql
		 * @param hqlExpressions
		 */
		public RawExpression(String id, String target, String hql, Map<String, String> hqlExpressions) {
			super();
			this.id = id;
			this.target = target;
			this.hql = hql;
			this.hqlExpressions = hqlExpressions;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		@SuppressWarnings("unused")
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the target
		 */
		public String getTarget() {
			return target;
		}

		/**
		 * @param target
		 *            the target to set
		 */
		@SuppressWarnings("unused")
		public void setTarget(String target) {
			this.target = target;
		}

		/**
		 * @return the hql
		 */
		@SuppressWarnings("unused")
		public String getHql() {
			return hql;
		}

		/**
		 * @param hql
		 *            the hql to set
		 */
		@SuppressWarnings("unused")
		public void setHql(String hql) {
			this.hql = hql;
		}

		/**
		 * @return the hqlExpressions
		 */
		public Map<String, String> getHqlExpressions() {
			return hqlExpressions;
		}

		/**
		 * @param hqlExpressions
		 *            the hqlExpressions to set
		 */
		@SuppressWarnings("unused")
		public void setHqlExpressions(Map<String, String> hqlExpressions) {
			this.hqlExpressions = hqlExpressions;
		}

	}

}
