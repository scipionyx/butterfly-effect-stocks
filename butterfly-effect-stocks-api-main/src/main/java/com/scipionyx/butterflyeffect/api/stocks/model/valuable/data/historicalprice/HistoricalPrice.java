package com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.scipionyx.butterflyeffect.api.stocks.model.YahooData;
import com.scipionyx.butterflyeffect.api.stocks.model.valuable.Valuable;

/**
 * 
 * @author Renato Mendes - rmendes@bottomline.com / renato.mendes.1123@gmail.com
 *
 */
public class HistoricalPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	/**
	 * Source of the information such as Yahoo, Google
	 */
	private Source source;

	/**
	 * Date when the information was collected
	 */
	private Date read;

	/**
	 * 
	 */
	private Date date;

	/**
	 * 
	 */
	private Valuable valuable;

	/**
	 * 
	 */
	private Double price;

	/**
	 * 
	 */
	@YahooData(option = "o")
	private Double open;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Date getRead() {
		return read;
	}

	public void setRead(Date read) {
		this.read = read;
	}

	public Valuable getValuable() {
		return valuable;
	}

	public void setValuable(Valuable valuable) {
		this.valuable = valuable;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
