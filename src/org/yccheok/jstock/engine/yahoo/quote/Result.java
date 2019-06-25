/*
 * JStock-Min - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 06/25/2019
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
//=================================================================
// Revision History
// Changes to the code should be documented here and reflected
// in the present version number. Author information should
// also be included with the original copyright author.
//=================================================================
//
// Version 1.0.7.37.01 06/25/2019 Original JStock Engine Result Class.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine.yahoo.quote;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 06/25/2019
 */

public class Result
{
   private String language;
   private String quoteType;
   private String quoteSourceName;
   private double regularMarketChangePercent;
   private double forwardPE;
   private double priceToBook;
   private int sourceInterval;
   private String exchangeTimezoneName;
   private String exchangeTimezoneShortName;
   private int gmtOffSetMilliseconds;
   private int priceHint;
   private String longName;
   private double regularMarketPrice;
   private long regularMarketTime;
   private double regularMarketChange;
   private double regularMarketOpen;
   private double regularMarketDayHigh;
   private double regularMarketDayLow;
   private long regularMarketVolume;
   private double twoHundredDayAverage;
   private double fiftyTwoWeekHighChange;
   private double fiftyTwoWeekHighChangePercent;
   private double fiftyTwoWeekLow;
   private double fiftyTwoWeekHigh;
   private long dividendDate;
   private long earningsTimestamp;
   private long earningsTimestampStart;
   private long earningsTimestampEnd;
   private double trailingAnnualDividendRate;
   private double trailingPE;
   private String market;
   private double ask;
   private int bidSize;
   private int askSize;
   private String shortName;
   private String exchange;
   private double regularMarketPreviousClose;
   private double bid;
   private String messageBoardId;
   private String fullExchangeName;
   private String financialCurrency;
   private long averageDailyVolume3Month;
   private long averageDailyVolume10Day;
   private double fiftyTwoWeekLowChange;
   private double fiftyTwoWeekLowChangePercent;
   private String marketState;
   private long sharesOutstanding;
   private double bookValue;
   private double fiftyDayAverage;
   private double fiftyDayAverageChange;
   private double twoHundredDayAverageChange;
   private double twoHundredDayAverageChangePercent;
   private long marketCap;
   boolean tradeable;
   private double trailingAnnualDividendYield;
   private double epsTrailingTwelveMonths;
   private double epsForward;
   private double fiftyDayAverageChangePercent;
   private String symbol;
   private String underlyingSymbol;
   private String currency;
   
   public Result()
   {
      // No assignment?
   }
   
   public void setLanguage(String value)
   {
      language = value;
   }
   
   public String getLanguage()
   {
      return language;
   }
   
   public String getQuoteType()
   {
      return quoteType;
   }
   
   public void setQuoteType(String value)
   {
      quoteType = value;
   }
   
   public String getQuoteSourceName()
   {
      return quoteSourceName;
   }
   
   public void setQuoteSourceName(String value)
   {
      quoteSourceName = value;
   }
   
   public double getRegularMarketChangePercent()
   {
      return regularMarketChangePercent;
   }
   
   public void setRegularMarketChangePercent(double value)
   {
      regularMarketChangePercent = value;
   }
   
   public double getForwardPE()
   {
      return forwardPE;
   }
   
   public void setForwardPE(double value)
   {
      forwardPE = value;
   }
   
   public double getPriceToBook()
   {
      return priceToBook;
   }
   
   public void setPriceToBook(double value)
   {
      priceToBook = value;
   }
   
   public int getSourceInterval()
   {
      return sourceInterval;
   }
   
   public void setSourceInterval(int value)
   {
      sourceInterval = value;
   }
   
   public String getExchangeTimezoneName()
   {
      return exchangeTimezoneName;
   }
   
   public void setExchangeTimezoneName(String value)
   {
      exchangeTimezoneName = value;
   }
   
   public String getExchangeTimezoneShortName()
   {
      return exchangeTimezoneShortName;
   }
   
   public void setExchangeTimezoneShortName(String value)
   {
      exchangeTimezoneShortName = value;
   }
   
   public int getGmtOffSetMilliseconds()
   {
      return gmtOffSetMilliseconds;
   }
   
   public void setGmtOffSetMilliseconds(int value)
   {
      gmtOffSetMilliseconds = value;
   }
   
   public int getPriceHint()
   {
      return priceHint;
   }
   
   public void setPriceHint(int value)
   {
      priceHint = value;
   }
   
   public String getLongName()
   {
      return longName;
   }
   
   public void setLongName(String value)
   {
      longName = value;
   }
   
   public double getRegularMarketPrice()
   {
      return regularMarketPrice;
   }
   
   public void setRegularMarketPrice(double value)
   {
      regularMarketPrice = value;
   }
   
   public long getRegularMarketTime()
   {
      return regularMarketTime;
   }
   
   public void setRegularMarketTime(long value)
   {
      regularMarketTime = value;
   }
   
   public double getRegularMarketChange()
   {
      return regularMarketChange;
   }
   
   public void setRegularMarketChange(double value)
   {
      regularMarketChange = value;
   }
   
   public double getRegularMarketOpen()
   {
      return regularMarketOpen;
   }
   
   public void setRegularMarketOpen(double value)
   {
      regularMarketOpen = value;
   }
   
   public double getRegularMarketDayHigh()
   {
      return regularMarketDayHigh;
   }
   
   public void setRegularMarketDayHigh(double value)
   {
      regularMarketDayHigh = value;
   }
   
   public double getRegularMarketDayLow()
   {
      return regularMarketDayLow;
   }
   
   public void setRegularMarketDayLow(double value)
   {
      regularMarketDayLow = value;
   }
   
   public long getRegularMarketVolume()
   {
      return regularMarketVolume;
   }
   
   public void setRegularMarketVolume(long value)
   {
      regularMarketVolume = value;
   }
   
   public double getTwoHundredDayAverage()
   {
      return twoHundredDayAverage;
   }
   
   public void setTwoHundredDayAverage(double value)
   {
      twoHundredDayAverage = value;
   }
   
   public double getFiftyTwoWeekHighChange()
   {
      return fiftyTwoWeekHighChange;
   }
   
   public void setFiftyTwoWeekHighChange(double value)
   {
      fiftyTwoWeekHighChange = value;
   }
   
   public double getFiftyTwoWeekHighChangePercent()
   {
      return fiftyTwoWeekHighChangePercent;
   }
   
   public void setFiftyTwoWeekHighChangePercent(double value)
   {
      fiftyTwoWeekHighChangePercent = value;
   }
   
   public double getFiftyTwoWeekLow()
   {
      return fiftyTwoWeekLow;
   }
   
   public void setFiftyTwoWeekLow(double value)
   {
      fiftyTwoWeekLow = value;
   }
   
   public double getFiftyTwoWeekHigh()
   {
      return fiftyTwoWeekHigh;
   }
   
   public void setFiftyTwoWeekHigh(double value)
   {
      fiftyTwoWeekHigh = value;
   }
   
   public long getDividendDate()
   {
      return dividendDate;
   }
   
   public void setDividendDate(long value)
   {
      dividendDate = value;
   }
   
   public long getEarningsTimestamp()
   {
      return earningsTimestamp;
   }
   
   public void setEarningsTimestamp(long value)
   {
      earningsTimestamp = value;
   }
   
   public long getEarningsTimestampStart()
   {
      return earningsTimestampStart;
   }
   
   public void setEarningsTimestampStart(long value)
   {
      earningsTimestampStart = value;
   }
   
   public long getEarningsTimestampEnd()
   {
      return earningsTimestampEnd;
   }
   
   public void setEarningsTimestampEnd(long value)
   {
      earningsTimestampEnd = value;
   }
   
   public double getTrailingAnnualDividendRate()
   {
      return trailingAnnualDividendRate;
   }
   
   public void setTrailingAnnualDividendRate(double value)
   {
      trailingAnnualDividendRate = value;
   }
   
   public double getTrailingPE()
   {
      return trailingPE;
   }
   
   public void setTrailingPE(double value)
   {
      trailingPE = value;
   }
   
   public String getMarket()
   {
      return market;
   }
   
   public void setMarket(String value)
   {
      market = value;
   }
   
   public double getAsk()
   {
      return ask;
   }
   
   public void setAsk(double value)
   {
      ask = value;
   }
   
   public int getBidSize()
   {
      return bidSize;
   }
   
   public void setBidSize(int value)
   {
      bidSize = value;
   }
   
   public int getAskSize()
   {
      return askSize;
   }
   
   public void setAskSize(int value)
   {
      askSize = value;
   }
   
   public String getShortName()
   {
      return shortName;
   }
   
   public void setShortName(String value)
   {
      shortName = value;
   }
   
   public String getExchange()
   {
      return exchange;
   }
   
   public void setExchange(String value)
   {
      exchange = value;
   }
   
   public double getRegularMarketPreviousClose()
   {
      return regularMarketPreviousClose;
   }
   
   public void setRegularMarketPreviousClose(double value)
   {
      regularMarketPreviousClose = value;
   }
   
   public double getBid()
   {
      return bid;
   }
   
   public void setBid(double value)
   {
      bid = value;
   }
   
   public String getMessageBoardId()
   {
      return messageBoardId;
   }
   
   public void setMessageBoardId(String value)
   {
      messageBoardId = value;
   }
   
   public String getFullExchangeName()
   {
      return fullExchangeName;
   }
   
   public void setFullExchangeName(String value)
   {
      fullExchangeName = value;
   }
   
   public String getFinancialCurrency()
   {
      return financialCurrency;
   }
   
   public void setFinancialCurrency(String value)
   {
      financialCurrency = value;
   }
   
   public long getAverageDailyVolume3Month()
   {
      return averageDailyVolume3Month;
   }
   
   public void setAverageDailyVolume3Month(long value)
   {
      averageDailyVolume3Month = value;
   }
   
   public long getAverageDailyVolume10Day()
   {
      return averageDailyVolume10Day;
   }
   
   public void setAverageDailyVolume10Day(long value)
   {
      averageDailyVolume10Day = value;
   }
   
   public double getFiftyTwoWeekLowChange()
   {
      return fiftyTwoWeekLowChange;
   }
   
   public void setFiftyTwoWeekLowChange(double value)
   {
      fiftyTwoWeekLowChange = value;
   }
   
   public double getFiftyTwoWeekLowChangePercent()
   {
      return fiftyTwoWeekLowChangePercent;
   }
   
   public void setFiftyTwoWeekLowChangePercent(double value)
   {
      fiftyTwoWeekLowChangePercent = value;
   }
   
   public String getMarketState()
   {
      return marketState;
   }
   
   public void setMarketState(String value)
   {
      marketState = value;
   }
   
   public long getSharesOutstanding()
   {
      return sharesOutstanding;
   }
   
   public void setSharesOutstanding(long value)
   {
      sharesOutstanding = value;
   }
   
   public double getBookValue()
   {
      return bookValue;
   }
   
   public void setBookValue(double value)
   {
      bookValue = value;
   }
   
   public double getFiftyDayAverage()
   {
      return fiftyDayAverage;
   }
   
   public void setFiftyDayAverage(double value)
   {
      fiftyDayAverage = value;
   }
   
   public double getFiftyDayAverageChange()
   {
      return fiftyDayAverageChange;
   }
   
   public void setFiftyDayAverageChange(double value)
   {
      fiftyDayAverageChange = value;
   }
   
   public double getTwoHundredDayAverageChange()
   {
      return twoHundredDayAverageChange;
   }
   
   public void setTwoHundredDayAverageChange(double value)
   {
      twoHundredDayAverageChange = value;
   }
   
   public double getTwoHundredDayAverageChangePercent()
   {
      return twoHundredDayAverageChangePercent;
   }
   
   public void setTwoHundredDayAverageChangePercent(double value)
   {
      twoHundredDayAverageChangePercent = value;
   }
   
   public long getMarketCap()
   {
      return marketCap;
   }
   
   public void setMarketCap(long value)
   {
      marketCap = value;
   }
   
   boolean isTradeable()
   {
      return tradeable;
   }
   
   public void setTradeable(boolean value)
   {
      tradeable = value;
   }
   
   public double getTrailingAnnualDividendYield()
   {
      return trailingAnnualDividendYield;
   }
   
   public void setTrailingAnnualDividendYield(double value)
   {
      trailingAnnualDividendYield = value;
   }
   
   public double getEpsTrailingTwelveMonths()
   {
      return epsTrailingTwelveMonths;
   }
   
   public void setEpsTrailingTwelveMonths(double value)
   {
      epsTrailingTwelveMonths = value;
   }
   
   public double getEpsForward()
   {
      return epsForward;
   }
   
   public void setEpsForward(double value)
   {
      epsForward = value;
   }
   
   public double getFiftyDayAverageChangePercent()
   {
      return fiftyDayAverageChangePercent;
   }
   
   public void setFiftyDayAverageChangePercent(double value)
   {
      fiftyDayAverageChangePercent = value;
   }
   
   public String getSymbol()
   {
      return symbol;
   }
   
   public void setSymbol(String value)
   {
      symbol = value;
   }
   
   public String getUnderlyingSymbol()
   {
      return underlyingSymbol;
   }
   
   public void setUnderlyingSymbol(String value)
   {
      underlyingSymbol = value;
   }
   
   public String getCurrency()
   {
      return currency;
   }
   
   public void setCurrency(String value)
   {
      currency = value;
   }
}