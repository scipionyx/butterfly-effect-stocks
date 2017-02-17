package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author rmendes
 *
 */
public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@NotNull
	private Stock stock;

	private Date buy;

	private Date sell;

	private Double price;

	private Long quantity;

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
