package com.scipionyx.butterflyeffect.api.stocks.model.valuable;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.scipionyx.butterflyeffect.api.stocks.model.market.Exchange;

/**
 * 
 * @author Renato Mendes
 *
 */
@Entity
@Table(name = "S_VALUABLE_STOCK", uniqueConstraints = { @UniqueConstraint(columnNames = { "SYMBOL" }) })
@Cacheable(value = true)
public class Stock extends Valuable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Exchange.class)
	private Exchange exchange;

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

	public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	@Override
	public String toString() {
		return getSymbol() + "-" + getName();
	}

}
