package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Renato Mendes
 *
 */
@Entity
@Table(name = "S_STOCKS_PORTIFOLIO", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER", "NAME" }) })
@Cacheable(value = true)
public class Portfolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private long id;

	@Column(name = "USER", length = 250)
	private String user;

	@Column(name = "NAME", length = 500)
	private String name;

	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Position.class, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Position> positions;

	@Column(name = "PORTFOLIO_ORDER")
	private int order;

	@Column(name = "DEFAULT_PORTFOLIO")
	private Boolean defaultPortfolio;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean isDefaultPortfolio() {
		return defaultPortfolio;
	}

	public void setDefaultPortfolio(Boolean defaultPortifolio) {
		this.defaultPortfolio = defaultPortifolio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Portfolio)) {
			return false;
		}
		Portfolio other = (Portfolio) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
