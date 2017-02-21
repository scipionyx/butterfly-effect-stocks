package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Renato Mendes
 *
 */
@Entity
@Table(name = "S_STOCKS_POSITION")
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
	@Column(name = "STOCK", nullable = false)
	private Stock stock;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_BUY", nullable = true)
	private Date buy;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_SELL", nullable = true)
	private Date sell;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "QUANTITY")
	private Long quantity;

	@Column(name = "STATUS")
	private Status status;

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

}
