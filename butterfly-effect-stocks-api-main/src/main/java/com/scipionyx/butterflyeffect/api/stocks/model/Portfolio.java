package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author rmendes
 *
 */
public class Portfolio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user;

	private List<Position> positions;
	
	private int order;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
	
	

}
