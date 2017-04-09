package com.scipionyx.butterflyeffect.api.stocks.model.valuable.data.historicalprice;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

import com.scipionyx.butterflyeffect.api.stocks.model.YahooData;

/**
 * 
 * @author Renato Mendes
 *
 */
@Document(indexName = "s_stocks_data", shards = 3, createIndex = true, replicas = 2)
public class StockPrice extends HistoricalPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@YahooData(option = "j1")
	private String marketCapitalization;

	// j3 Market Cap (Real-time)
	@YahooData(option = "j3")
	private String marketCapitalizationRealTime;

	// j4 EBITDA
	@YahooData(option = "j4")
	private String ebitda;

	// j5 Change From 52-week Low
	@YahooData(option = "j5")
	private String changeFrom52wLow;

	// j6 Percent Change From 52-week Low
	@YahooData(option = "j6")
	private String percentChangeFrom52wLow;

	// k1 Last Trade (Real-time) With Time
	@YahooData(option = "k1")
	private String lastTradeRealTimeWithTime;

	// k2 Change Percent (Real-time)
	@YahooData(option = "k2")
	private String changePercentRealTime;

	// k3 Last Trade Size
	@YahooData(option = "k3")
	private String lastTradeSize;

	// k4 Change From 52-week High
	// k5 Percebt Change From 52-week High
	// l Last Trade (With Time)
	@YahooData(option = "l")
	private String lastTrade;

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

	// p Previous Close //
	@YahooData(option = "p")
	private String previousClose;

	// p1 Price Paid
	@YahooData(option = "p1")
	private String pricePaid;

	// p2 Change in Percent
	@YahooData(option = "p2")
	private String changeInPercent;

	// p5 Price/Sales
	@YahooData(option = "p5")
	private String pricePerSales;

	// p6 Price/Book
	@YahooData(option = "p6")
	private String pricePerBook;

	// q Ex-Dividend Date
	@YahooData(option = "q")
	private String exDividendDate;

	// r P/E Ratio
	@YahooData(option = "r")
	private String peRatio;

	// r1 Dividend Pay Date
	@YahooData(option = "r1")
	private String dividendPayDate;

	// r2 P/E Ratio (Real-time)
	@YahooData(option = "r2")
	private String peRatioRealTime;

	// r5 PEG Ratio
	@YahooData(option = "r5")
	private String pegRatio;

	// r6 Price/EPS Estimate Current Year
	@YahooData(option = "r6")
	private String pricePerepsEstimateCurrentYear;

	// r7 Price/EPS Estimate Next Year
	@YahooData(option = "r7")
	private String pricePerepsEstimateNextYear;

	// s Symbol
	@YahooData(option = "s")
	private String symbol;

	// s1 Shares Owned
	@YahooData(option = "s1")
	private String sharesOwned;

	// s7 Short Ratio
	@YahooData(option = "s7")
	private String shortRatio;

	// t1 Last Trade Time
	@YahooData(option = "t1")
	private String lastTradeTime;

	// t6 Trade Links
	@YahooData(option = "t6")
	private String tradeLink;

	// t7 Ticker Trend
	@YahooData(option = "t7")
	private String tickerTrend;

	// t8 1 yr Target Price
	@YahooData(option = "t8")
	private String targetPrice1y;

	// v Volume
	@YahooData(option = "v")
	private String volume;

	// v1 Holdings Value
	@YahooData(option = "v1")
	private String holdingsValue;

	// v7 Holdings Value (Real-time)
	@YahooData(option = "v7")
	private String holdingsValueRealTime;

	// w 52-week Range
	@YahooData(option = "w")
	private String weekRange52;

	// w1 Day’s Value Change
	@YahooData(option = "w1")
	private String daysValueChange;

	// w4 Day’s Value Change (Real-time)
	@YahooData(option = "w4")
	private String daysValueChangeRealTime;

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

	/**
	 * @return the holdingsGain
	 */
	public String getHoldingsGain() {
		return holdingsGain;
	}

	/**
	 * @param holdingsGain
	 *            the holdingsGain to set
	 */
	public void setHoldingsGain(String holdingsGain) {
		this.holdingsGain = holdingsGain;
	}

	/**
	 * @return the holdingsGainPercentRealTime
	 */
	public String getHoldingsGainPercentRealTime() {
		return holdingsGainPercentRealTime;
	}

	/**
	 * @param holdingsGainPercentRealTime
	 *            the holdingsGainPercentRealTime to set
	 */
	public void setHoldingsGainPercentRealTime(String holdingsGainPercentRealTime) {
		this.holdingsGainPercentRealTime = holdingsGainPercentRealTime;
	}

	/**
	 * @return the holdingsGainRealTime
	 */
	public String getHoldingsGainRealTime() {
		return holdingsGainRealTime;
	}

	/**
	 * @param holdingsGainRealTime
	 *            the holdingsGainRealTime to set
	 */
	public void setHoldingsGainRealTime(String holdingsGainRealTime) {
		this.holdingsGainRealTime = holdingsGainRealTime;
	}

	/**
	 * @return the moreInfo
	 */
	public String getMoreInfo() {
		return moreInfo;
	}

	/**
	 * @param moreInfo
	 *            the moreInfo to set
	 */
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	/**
	 * @return the orderBookRealTime
	 */
	public String getOrderBookRealTime() {
		return orderBookRealTime;
	}

	/**
	 * @param orderBookRealTime
	 *            the orderBookRealTime to set
	 */
	public void setOrderBookRealTime(String orderBookRealTime) {
		this.orderBookRealTime = orderBookRealTime;
	}

	/**
	 * @return the percentChangeFrom50DayMovingAverage
	 */
	public String getPercentChangeFrom50DayMovingAverage() {
		return percentChangeFrom50DayMovingAverage;
	}

	/**
	 * @param percentChangeFrom50DayMovingAverage
	 *            the percentChangeFrom50DayMovingAverage to set
	 */
	public void setPercentChangeFrom50DayMovingAverage(String percentChangeFrom50DayMovingAverage) {
		this.percentChangeFrom50DayMovingAverage = percentChangeFrom50DayMovingAverage;
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
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * @return the stockExchange
	 */
	public String getStockExchange() {
		return stockExchange;
	}

	/**
	 * @param stockExchange
	 *            the stockExchange to set
	 */
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	/**
	 * @return the dividendYield
	 */
	public String getDividendYield() {
		return dividendYield;
	}

	/**
	 * @param dividendYield
	 *            the dividendYield to set
	 */
	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}

	/**
	 * @return the lastTradeSize
	 */
	public String getLastTradeSize() {
		return lastTradeSize;
	}

	/**
	 * @param lastTradeSize
	 *            the lastTradeSize to set
	 */
	public void setLastTradeSize(String lastTradeSize) {
		this.lastTradeSize = lastTradeSize;
	}

	/**
	 * @return the lastTrade
	 */
	public String getLastTrade() {
		return lastTrade;
	}

	/**
	 * @param lastTrade
	 *            the lastTrade to set
	 */
	public void setLastTrade(String lastTrade) {
		this.lastTrade = lastTrade;
	}

	/**
	 * @return the sharesOwned
	 */
	public String getSharesOwned() {
		return sharesOwned;
	}

	/**
	 * @param sharesOwned
	 *            the sharesOwned to set
	 */
	public void setSharesOwned(String sharesOwned) {
		this.sharesOwned = sharesOwned;
	}

	/**
	 * @return the shortRatio
	 */
	public String getShortRatio() {
		return shortRatio;
	}

	/**
	 * @param shortRatio
	 *            the shortRatio to set
	 */
	public void setShortRatio(String shortRatio) {
		this.shortRatio = shortRatio;
	}

	/**
	 * @return the lastTradeTime
	 */
	public String getLastTradeTime() {
		return lastTradeTime;
	}

	/**
	 * @param lastTradeTime
	 *            the lastTradeTime to set
	 */
	public void setLastTradeTime(String lastTradeTime) {
		this.lastTradeTime = lastTradeTime;
	}

	/**
	 * @return the tradeLink
	 */
	public String getTradeLink() {
		return tradeLink;
	}

	/**
	 * @param tradeLink
	 *            the tradeLink to set
	 */
	public void setTradeLink(String tradeLink) {
		this.tradeLink = tradeLink;
	}

	/**
	 * @return the tickerTrend
	 */
	public String getTickerTrend() {
		return tickerTrend;
	}

	/**
	 * @param tickerTrend
	 *            the tickerTrend to set
	 */
	public void setTickerTrend(String tickerTrend) {
		this.tickerTrend = tickerTrend;
	}

	/**
	 * @return the targetPrice1y
	 */
	public String getTargetPrice1y() {
		return targetPrice1y;
	}

	/**
	 * @param targetPrice1y
	 *            the targetPrice1y to set
	 */
	public void setTargetPrice1y(String targetPrice1y) {
		this.targetPrice1y = targetPrice1y;
	}

	/**
	 * @return the holdingsValue
	 */
	public String getHoldingsValue() {
		return holdingsValue;
	}

	/**
	 * @param holdingsValue
	 *            the holdingsValue to set
	 */
	public void setHoldingsValue(String holdingsValue) {
		this.holdingsValue = holdingsValue;
	}

	/**
	 * @return the holdingsValueRealTime
	 */
	public String getHoldingsValueRealTime() {
		return holdingsValueRealTime;
	}

	/**
	 * @param holdingsValueRealTime
	 *            the holdingsValueRealTime to set
	 */
	public void setHoldingsValueRealTime(String holdingsValueRealTime) {
		this.holdingsValueRealTime = holdingsValueRealTime;
	}

	/**
	 * @return the weekRange52
	 */
	public String getWeekRange52() {
		return weekRange52;
	}

	/**
	 * @param weekRange52
	 *            the weekRange52 to set
	 */
	public void setWeekRange52(String weekRange52) {
		this.weekRange52 = weekRange52;
	}

	/**
	 * @return the daysValueChange
	 */
	public String getDaysValueChange() {
		return daysValueChange;
	}

	/**
	 * @param daysValueChange
	 *            the daysValueChange to set
	 */
	public void setDaysValueChange(String daysValueChange) {
		this.daysValueChange = daysValueChange;
	}

	/**
	 * @return the daysValueChangeRealTime
	 */
	public String getDaysValueChangeRealTime() {
		return daysValueChangeRealTime;
	}

	/**
	 * @param daysValueChangeRealTime
	 *            the daysValueChangeRealTime to set
	 */
	public void setDaysValueChangeRealTime(String daysValueChangeRealTime) {
		this.daysValueChangeRealTime = daysValueChangeRealTime;
	}

	/**
	 * @return the marketCapitalization
	 */
	public String getMarketCapitalization() {
		return marketCapitalization;
	}

	/**
	 * @param marketCapitalization
	 *            the marketCapitalization to set
	 */
	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}

	/**
	 * @return the marketCapitalizationRealTime
	 */
	public String getMarketCapitalizationRealTime() {
		return marketCapitalizationRealTime;
	}

	/**
	 * @param marketCapitalizationRealTime
	 *            the marketCapitalizationRealTime to set
	 */
	public void setMarketCapitalizationRealTime(String marketCapitalizationRealTime) {
		this.marketCapitalizationRealTime = marketCapitalizationRealTime;
	}

	/**
	 * @return the ebitda
	 */
	public String getEbitda() {
		return ebitda;
	}

	/**
	 * @param ebitda
	 *            the ebitda to set
	 */
	public void setEbitda(String ebitda) {
		this.ebitda = ebitda;
	}

	/**
	 * @return the changeFrom52wLow
	 */
	public String getChangeFrom52wLow() {
		return changeFrom52wLow;
	}

	/**
	 * @param changeFrom52wLow
	 *            the changeFrom52wLow to set
	 */
	public void setChangeFrom52wLow(String changeFrom52wLow) {
		this.changeFrom52wLow = changeFrom52wLow;
	}

	/**
	 * @return the percentChangeFrom52wLow
	 */
	public String getPercentChangeFrom52wLow() {
		return percentChangeFrom52wLow;
	}

	/**
	 * @param percentChangeFrom52wLow
	 *            the percentChangeFrom52wLow to set
	 */
	public void setPercentChangeFrom52wLow(String percentChangeFrom52wLow) {
		this.percentChangeFrom52wLow = percentChangeFrom52wLow;
	}

	/**
	 * @return the lastTradeRealTimeWithTime
	 */
	public String getLastTradeRealTimeWithTime() {
		return lastTradeRealTimeWithTime;
	}

	/**
	 * @param lastTradeRealTimeWithTime
	 *            the lastTradeRealTimeWithTime to set
	 */
	public void setLastTradeRealTimeWithTime(String lastTradeRealTimeWithTime) {
		this.lastTradeRealTimeWithTime = lastTradeRealTimeWithTime;
	}

	/**
	 * @return the changePercentRealTime
	 */
	public String getChangePercentRealTime() {
		return changePercentRealTime;
	}

	/**
	 * @param changePercentRealTime
	 *            the changePercentRealTime to set
	 */
	public void setChangePercentRealTime(String changePercentRealTime) {
		this.changePercentRealTime = changePercentRealTime;
	}

	/**
	 * @return the previousClose
	 */
	public String getPreviousClose() {
		return previousClose;
	}

	/**
	 * @param previousClose
	 *            the previousClose to set
	 */
	public void setPreviousClose(String previousClose) {
		this.previousClose = previousClose;
	}

	/**
	 * @return the pricePaid
	 */
	public String getPricePaid() {
		return pricePaid;
	}

	/**
	 * @param pricePaid
	 *            the pricePaid to set
	 */
	public void setPricePaid(String pricePaid) {
		this.pricePaid = pricePaid;
	}

	/**
	 * @return the changeInPercent
	 */
	public String getChangeInPercent() {
		return changeInPercent;
	}

	/**
	 * @param changeInPercent
	 *            the changeInPercent to set
	 */
	public void setChangeInPercent(String changeInPercent) {
		this.changeInPercent = changeInPercent;
	}

	/**
	 * @return the pricePerSales
	 */
	public String getPricePerSales() {
		return pricePerSales;
	}

	/**
	 * @param pricePerSales
	 *            the pricePerSales to set
	 */
	public void setPricePerSales(String pricePerSales) {
		this.pricePerSales = pricePerSales;
	}

	/**
	 * @return the pricePerBook
	 */
	public String getPricePerBook() {
		return pricePerBook;
	}

	/**
	 * @param pricePerBook
	 *            the pricePerBook to set
	 */
	public void setPricePerBook(String pricePerBook) {
		this.pricePerBook = pricePerBook;
	}

	/**
	 * @return the exDividendDate
	 */
	public String getExDividendDate() {
		return exDividendDate;
	}

	/**
	 * @param exDividendDate
	 *            the exDividendDate to set
	 */
	public void setExDividendDate(String exDividendDate) {
		this.exDividendDate = exDividendDate;
	}

	/**
	 * @return the peRatio
	 */
	public String getPeRatio() {
		return peRatio;
	}

	/**
	 * @param peRatio
	 *            the peRatio to set
	 */
	public void setPeRatio(String peRatio) {
		this.peRatio = peRatio;
	}

	/**
	 * @return the dividendPayDate
	 */
	public String getDividendPayDate() {
		return dividendPayDate;
	}

	/**
	 * @param dividendPayDate
	 *            the dividendPayDate to set
	 */
	public void setDividendPayDate(String dividendPayDate) {
		this.dividendPayDate = dividendPayDate;
	}

	/**
	 * @return the peRatioRealTime
	 */
	public String getPeRatioRealTime() {
		return peRatioRealTime;
	}

	/**
	 * @param peRatioRealTime
	 *            the peRatioRealTime to set
	 */
	public void setPeRatioRealTime(String peRatioRealTime) {
		this.peRatioRealTime = peRatioRealTime;
	}

	/**
	 * @return the pegRatio
	 */
	public String getPegRatio() {
		return pegRatio;
	}

	/**
	 * @param pegRatio
	 *            the pegRatio to set
	 */
	public void setPegRatio(String pegRatio) {
		this.pegRatio = pegRatio;
	}

	/**
	 * @return the pricePerepsEstimateCurrentYear
	 */
	public String getPricePerepsEstimateCurrentYear() {
		return pricePerepsEstimateCurrentYear;
	}

	/**
	 * @param pricePerepsEstimateCurrentYear
	 *            the pricePerepsEstimateCurrentYear to set
	 */
	public void setPricePerepsEstimateCurrentYear(String pricePerepsEstimateCurrentYear) {
		this.pricePerepsEstimateCurrentYear = pricePerepsEstimateCurrentYear;
	}

	/**
	 * @return the pricePerepsEstimateNextYear
	 */
	public String getPricePerepsEstimateNextYear() {
		return pricePerepsEstimateNextYear;
	}

	/**
	 * @param pricePerepsEstimateNextYear
	 *            the pricePerepsEstimateNextYear to set
	 */
	public void setPricePerepsEstimateNextYear(String pricePerepsEstimateNextYear) {
		this.pricePerepsEstimateNextYear = pricePerepsEstimateNextYear;
	}

}
