package com.scipionyx.butterflyeffect.api.stocks.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author rmendes
 *
 */
public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//
	private Date read;

	//
	private String source;

	// a
	@YahooData(option = "a")
	private String ask;

	// a2
	@YahooData(option = "a2")
	private String averageDailyVolume;

	// a5 Ask Size
	@YahooData(option = "a5")
	private String askSize;

	// b Bid
	@YahooData(option = "b")
	private String bid;

	// b2 Ask (Real-time)
	@YahooData(option = "b2")
	private String askRealTime;

	// b3 Bid (Real-time)
	@YahooData(option = "b3")
	private String bidRealTime;

	// b4 Book Value
	@YahooData(option = "b4")
	private String bookValue;

	// b6 Bid Size
	@YahooData(option = "b6")
	private String bidSize;

	// c Change & Percent Change
	@YahooData(option = "c")
	private String changeAndPercentChange;

	// c1 Change
	@YahooData(option = "c1")
	private String change;

	// c3 Commission
	@YahooData(option = "c3")
	private String commission;

	// c6 Change (Real-time)
	@YahooData(option = "c6")
	private String changeRealTime;

	// c8 After Hours Change (Real-time)
	@YahooData(option = "c8")
	private String afterHoursChangeRealTime;

	// d Dividend/Share
	@YahooData(option = "d")
	private String dividendPerShare;

	// d1 Last Trade Date
	@YahooData(option = "d1")
	private String lastTradeDate;

	// d2 Trade Date
	@YahooData(option = "d2")
	private String tradeDate;

	// e Earnings/Share
	@YahooData(option = "e")
	private String earningsPerShare;

	// e1 Error Indication (returned for symbol changed / invalid)
	@YahooData(option = "e1")
	private String errorIndication;

	// e7 EPS Estimate Current Year
	@YahooData(option = "e7")
	private String epsEstimateCurrentYear;

	// e8 EPS Estimate Next Year
	@YahooData(option = "e8")
	private String epsEstimateNextYear;

	// e9 EPS Estimate Next Quarter
	@YahooData(option = "e9")
	private String epsEstimateNextQuarter;

	// f6 Float Shares
	@YahooData(option = "f6")
	private String floatShares;

	// g Day’s Low
	@YahooData(option = "g")
	private String daysLow;

	// h Day’s High
	@YahooData(option = "h")
	private String daysHigh;

	// j 52-week Low
	@YahooData(option = "j")
	private String weekLow52;

	// k 52-week High
	@YahooData(option = "k")
	private String weekHigh52;

	// g1 Holdings Gain Percent
	@YahooData(option = "g1")
	private String holdingsGainPercent;

	// g3 Annualized Gain
	@YahooData(option = "g3")
	private String annualizedGain;

	// g4 Holdings Gain
	@YahooData(option = "g4")
	private String holdingsGain;

	// g5 Holdings Gain Percent (Real-time)
	@YahooData(option = "g5")
	private String holdingsGainPercentRealTime;

	// g6 Holdings Gain (Real-time)
	@YahooData(option = "g6")
	private String holdingsGainRealTime;

	// i More Info
	@YahooData(option = "i")
	private String moreInfo;

	// i5 Order Book (Real-time)
	@YahooData(option = "i5")
	private String orderBookRealTime;

	// j1 Market Capitalization
	// j3 Market Cap (Real-time)
	// j4 EBITDA
	// j5 Change From 52-week Low
	// j6 Percent Change From 52-week Low
	// k1 Last Trade (Real-time) With Time
	// k2 Change Percent (Real-time)
	// k3 Last Trade Size
	// k4 Change From 52-week High
	// k5 Percebt Change From 52-week High
	// l Last Trade (With Time)
	// l1 Last Trade (Price Only)
	// l2 High Limit
	// l3 Low Limit m Day’s Range
	// m2 Day’s Range (Real-time)
	// m3 50-day Moving Average
	// m4 200-day Moving Average
	// m5 Change From 200-day Moving Average
	// m6 Percent Change From 200-day Moving Average
	// m7 Change From 50-day Moving Average

	// m8 Percent Change From 50-day Moving Average
	@YahooData(option = "m8")
	private String percentChangeFrom50DayMovingAverage;

	// n Name
	@YahooData(option = "n")
	private String name;

	// n4 Notes
	@YahooData(option = "n4")
	private String notes;

	// o Open
	@YahooData(option = "o")
	private String open;

	// p Previous Close //
	// p1 Price Paid
	// p2 Change in Percent
	// p5 Price/Sales
	// p6 Price/Book
	// q Ex-Dividend Date
	// r P/E Ratio
	// r1 Dividend Pay Date
	// r2 P/E Ratio (Real-time)
	// r5 PEG Ratio
	// r6 Price/EPS Estimate Current Year
	// r7 Price/EPS Estimate Next Year

	// s Symbol
	@YahooData(option = "s")
	private String symbol;

	// s1 Shares Owned
	// s7 Short Ratio
	// t1 Last Trade Time
	// t6 Trade Links
	// t7 Ticker Trend
	// t8 1 yr Target Price
	// v Volume
	@YahooData(option = "v")
	private String volume;

	// v1 Holdings Value
	// v7 Holdings Value (Real-time)
	// w 52-week Range
	// w1 Day’s Value Change
	// w4 Day’s Value Change (Real-time)

	// x Stock Exchange
	@YahooData(option = "x")
	private String stockExchange;

	// y Dividend Yield
	@YahooData(option = "y")
	private String dividendYield;

	/**
	 * @return the ask
	 */
	public String getAsk() {
		return ask;
	}

	/**
	 * @param ask
	 *            the ask to set
	 */
	public void setAsk(String ask) {
		this.ask = ask;
	}

	/**
	 * @return the read
	 */
	public Date getRead() {
		return read;
	}

	/**
	 * @param read
	 *            the read to set
	 */
	public void setRead(Date read) {
		this.read = read;
	}

	/**
	 * @return the averageDailyVolume
	 */
	public String getAverageDailyVolume() {
		return averageDailyVolume;
	}

	/**
	 * @param averageDailyVolume
	 *            the averageDailyVolume to set
	 */
	public void setAverageDailyVolume(String averageDailyVolume) {
		this.averageDailyVolume = averageDailyVolume;
	}

	/**
	 * @return the askSize
	 */
	public String getAskSize() {
		return askSize;
	}

	/**
	 * @param askSize
	 *            the askSize to set
	 */
	public void setAskSize(String askSize) {
		this.askSize = askSize;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param bid
	 *            the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the askRealTime
	 */
	public String getAskRealTime() {
		return askRealTime;
	}

	/**
	 * @param askRealTime
	 *            the askRealTime to set
	 */
	public void setAskRealTime(String askRealTime) {
		this.askRealTime = askRealTime;
	}

	/**
	 * @return the bidRealTime
	 */
	public String getBidRealTime() {
		return bidRealTime;
	}

	/**
	 * @param bidRealTime
	 *            the bidRealTime to set
	 */
	public void setBidRealTime(String bidRealTime) {
		this.bidRealTime = bidRealTime;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the bookValue
	 */
	public String getBookValue() {
		return bookValue;
	}

	/**
	 * @param bookValue
	 *            the bookValue to set
	 */
	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}

	/**
	 * @return the bidSize
	 */
	public String getBidSize() {
		return bidSize;
	}

	/**
	 * @param bidSize
	 *            the bidSize to set
	 */
	public void setBidSize(String bidSize) {
		this.bidSize = bidSize;
	}

	/**
	 * @return the changeAndPercentChange
	 */
	public String getChangeAndPercentChange() {
		return changeAndPercentChange;
	}

	/**
	 * @param changeAndPercentChange
	 *            the changeAndPercentChange to set
	 */
	public void setChangeAndPercentChange(String changeAndPercentChange) {
		this.changeAndPercentChange = changeAndPercentChange;
	}

	/**
	 * @return the change
	 */
	public String getChange() {
		return change;
	}

	/**
	 * @param change
	 *            the change to set
	 */
	public void setChange(String change) {
		this.change = change;
	}

	/**
	 * @return the commission
	 */
	public String getCommission() {
		return commission;
	}

	/**
	 * @param commission
	 *            the commission to set
	 */
	public void setCommission(String commission) {
		this.commission = commission;
	}

	/**
	 * @return the changeRealTime
	 */
	public String getChangeRealTime() {
		return changeRealTime;
	}

	/**
	 * @param changeRealTime
	 *            the changeRealTime to set
	 */
	public void setChangeRealTime(String changeRealTime) {
		this.changeRealTime = changeRealTime;
	}

	/**
	 * @return the dividendPerShare
	 */
	public String getDividendPerShare() {
		return dividendPerShare;
	}

	/**
	 * @param dividendPerShare
	 *            the dividendPerShare to set
	 */
	public void setDividendPerShare(String dividendPerShare) {
		this.dividendPerShare = dividendPerShare;
	}

	/**
	 * @return the lastTradeDate
	 */
	public String getLastTradeDate() {
		return lastTradeDate;
	}

	/**
	 * @param lastTradeDate
	 *            the lastTradeDate to set
	 */
	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}

	/**
	 * @return the tradeDate
	 */
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * @param tradeDate
	 *            the tradeDate to set
	 */
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * @return the earningsPerShare
	 */
	public String getEarningsPerShare() {
		return earningsPerShare;
	}

	/**
	 * @param earningsPerShare
	 *            the earningsPerShare to set
	 */
	public void setEarningsPerShare(String earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}

	/**
	 * @return the errorIndication
	 */
	public String getErrorIndication() {
		return errorIndication;
	}

	/**
	 * @param errorIndication
	 *            the errorIndication to set
	 */
	public void setErrorIndication(String errorIndication) {
		this.errorIndication = errorIndication;
	}

	/**
	 * @return the epsEstimateCurrentYear
	 */
	public String getEpsEstimateCurrentYear() {
		return epsEstimateCurrentYear;
	}

	/**
	 * @param epsEstimateCurrentYear
	 *            the epsEstimateCurrentYear to set
	 */
	public void setEpsEstimateCurrentYear(String epsEstimateCurrentYear) {
		this.epsEstimateCurrentYear = epsEstimateCurrentYear;
	}

	/**
	 * @return the epsEstimateNextYear
	 */
	public String getEpsEstimateNextYear() {
		return epsEstimateNextYear;
	}

	/**
	 * @param epsEstimateNextYear
	 *            the epsEstimateNextYear to set
	 */
	public void setEpsEstimateNextYear(String epsEstimateNextYear) {
		this.epsEstimateNextYear = epsEstimateNextYear;
	}

	/**
	 * @return the epsEstimateNextQuarter
	 */
	public String getEpsEstimateNextQuarter() {
		return epsEstimateNextQuarter;
	}

	/**
	 * @param epsEstimateNextQuarter
	 *            the epsEstimateNextQuarter to set
	 */
	public void setEpsEstimateNextQuarter(String epsEstimateNextQuarter) {
		this.epsEstimateNextQuarter = epsEstimateNextQuarter;
	}

	/**
	 * @return the floatShares
	 */
	public String getFloatShares() {
		return floatShares;
	}

	/**
	 * @param floatShares
	 *            the floatShares to set
	 */
	public void setFloatShares(String floatShares) {
		this.floatShares = floatShares;
	}

	/**
	 * @return the daysLow
	 */
	public String getDaysLow() {
		return daysLow;
	}

	/**
	 * @param daysLow
	 *            the daysLow to set
	 */
	public void setDaysLow(String daysLow) {
		this.daysLow = daysLow;
	}

	/**
	 * @return the daysHigh
	 */
	public String getDaysHigh() {
		return daysHigh;
	}

	/**
	 * @param daysHigh
	 *            the daysHigh to set
	 */
	public void setDaysHigh(String daysHigh) {
		this.daysHigh = daysHigh;
	}

	/**
	 * @return the weekLow52
	 */
	public String getWeekLow52() {
		return weekLow52;
	}

	/**
	 * @param weekLow52
	 *            the weekLow52 to set
	 */
	public void setWeekLow52(String weekLow52) {
		this.weekLow52 = weekLow52;
	}

	/**
	 * @return the weekHigh52
	 */
	public String getWeekHigh52() {
		return weekHigh52;
	}

	/**
	 * @param weekHigh52
	 *            the weekHigh52 to set
	 */
	public void setWeekHigh52(String weekHigh52) {
		this.weekHigh52 = weekHigh52;
	}

	/**
	 * @return the holdingsGainPercent
	 */
	public String getHoldingsGainPercent() {
		return holdingsGainPercent;
	}

	/**
	 * @param holdingsGainPercent
	 *            the holdingsGainPercent to set
	 */
	public void setHoldingsGainPercent(String holdingsGainPercent) {
		this.holdingsGainPercent = holdingsGainPercent;
	}

	/**
	 * @return the afterHoursChangeRealTime
	 */
	public String getAfterHoursChangeRealTime() {
		return afterHoursChangeRealTime;
	}

	/**
	 * @param afterHoursChangeRealTime
	 *            the afterHoursChangeRealTime to set
	 */
	public void setAfterHoursChangeRealTime(String afterHoursChangeRealTime) {
		this.afterHoursChangeRealTime = afterHoursChangeRealTime;
	}

	/**
	 * @return the annualizedGain
	 */
	public String getAnnualizedGain() {
		return annualizedGain;
	}

	/**
	 * @param annualizedGain
	 *            the annualizedGain to set
	 */
	public void setAnnualizedGain(String annualizedGain) {
		this.annualizedGain = annualizedGain;
	}

}
