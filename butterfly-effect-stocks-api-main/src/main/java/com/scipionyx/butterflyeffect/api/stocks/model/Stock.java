package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.vaadin.data.fieldgroup.PropertyId;

/**
 * 
 * @author rmendes
 *
 */
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@PropertyId("stock.code")
	@NotNull
	@NotBlank
	@NotEmpty
	private String code;

	private String name;

	public Stock() {
	}

	public Stock(String code) {
		this.code = code;
	}

	public Stock(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return code + "-" + name;
	}

}
