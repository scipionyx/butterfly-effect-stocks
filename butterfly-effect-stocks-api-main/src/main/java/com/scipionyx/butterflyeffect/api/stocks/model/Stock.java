package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author rmendes
 *
 */
@Entity
@Table(name = "S_STOCKS_STOCK", uniqueConstraints = { @UniqueConstraint(columnNames = { "SYMBOL" }) })
public class Stock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private long id;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "SYMBOL", length = 5)
	private String symbol;

	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_SALE")
	private Double lastSale;

	@Column(name = "MARKET_CAP")
	private Double marketCap;

	@Column(name = "IPO_YEAR")
	private String ipoYear;

	@Column(name = "ADR")
	private String adr;

	@Column(name = "SECTOR")
	private String sector;

	@Column(name = "INDUSTRY")
	private String industry;

	@Column(name = "SUMMARY")
	private String summaryQuote;

	public Stock() {
	}

	public Stock(String symbol) {
		this.symbol = symbol;
	}

	public Stock(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLastSale() {
		return lastSale;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public void setLastSale(Double lastSale) {
		this.lastSale = lastSale;
	}

	public String getIpoYear() {
		return ipoYear;
	}

	public void setIpoYear(String ipoYear) {
		this.ipoYear = ipoYear;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSummaryQuote() {
		return summaryQuote;
	}

	public void setSummaryQuote(String summaryQuote) {
		this.summaryQuote = summaryQuote;
	}

	@Override
	public String toString() {
		return symbol + "-" + name;
	}

}
