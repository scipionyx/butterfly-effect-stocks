package com.scipionyx.butterflyeffect.api.stocks.model.portfolio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Stock;

/**
 * 
 * @author Renato Mendes
 *
 */
@Entity
@Table(name = "S_STOCKS_POSITION")
@Cacheable(value = true)
public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private long id;

	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH, optional = false, fetch = FetchType.EAGER, targetEntity = Stock.class)
	private Stock stock;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_BUY", nullable = true)
	private Date buy;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_SELL", nullable = true)
	private Date sell;

	@Column(name = "COST")
	private Double cost;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "QUANTITY")
	private Long quantity;

	@Column(name = "STATUS")
	private Status status;

	@Transient()
	private Date totalInvestiment;

	@Transient()
	private Date update;

	@Transient()
	private Double currentPrice;

	@Transient()
	private Double currentValue;

	/**
	 * 
	 * @param stock
	 */
	public Position() {
	}

	/**
	 * 
	 * @param stock
	 */
	public Position(Stock stock) {
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getBuy() {
		return buy;
	}

	public void setBuy(Date buy) {
		this.buy = buy;
	}

	public Date getSell() {
		return sell;
	}

	public void setSell(Date sell) {
		this.sell = sell;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the totalInvestiment
	 */
	public Date getTotalInvestiment() {
		return totalInvestiment;
	}

	/**
	 * @param totalInvestiment
	 *            the totalInvestiment to set
	 */
	public void setTotalInvestiment(Date totalInvestiment) {
		this.totalInvestiment = totalInvestiment;
	}

	/**
	 * @return the update
	 */
	public Date getUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            the update to set
	 */
	public void setUpdate(Date update) {
		this.update = update;
	}

	/**
	 * @return the currentPrice
	 */
	public Double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * @param currentPrice
	 *            the currentPrice to set
	 */
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	/**
	 * @return the currentValue
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * @param currentValue
	 *            the currentValue to set
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

}
