package com.scipionyx.butterflyeffect.api.stocks.model.market;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;

/**
 * 
 * @author Renato Mendes
 *
 */
@Entity
@Table(name = "S_STOCKS_EXCHANGE", uniqueConstraints = { @UniqueConstraint(columnNames = { "CODE" }) })
@Cacheable(value = true)
public class Exchange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "CODE", length = 25)
	private String code;

	@Column(name = "NAME", length = 400)
	private String name;

	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	@Column(name = "COUNTRY", length = 5)
	@Enumerated(EnumType.STRING)
	private CountryCode country;

	@Column(name = "CURRENCY", length = 5)
	@Enumerated(EnumType.STRING)
	private CurrencyCode currency;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the country
	 */
	public CountryCode getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(CountryCode country) {
		this.country = country;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the currency
	 */
	public CurrencyCode getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(CurrencyCode currency) {
		this.currency = currency;
	}

}
