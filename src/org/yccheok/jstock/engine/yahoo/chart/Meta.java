/*
 * JStock-Min - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 06/26/2019
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
// Version 1.0.7.37.01 06/26/2019 Original JStock Engine Meta Class.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine.yahoo.chart;

import java.util.List;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 06/26/2019
 */
public class Meta
{ 
   @com.google.gson.annotations.SerializedName(value="currency")
   @com.google.gson.annotations.Expose
   private String currency;
  
   @com.google.gson.annotations.SerializedName(value="symbol")
   @com.google.gson.annotations.Expose
   private String symbol;
  
   @com.google.gson.annotations.SerializedName(value="exchangeName")
   @com.google.gson.annotations.Expose
   private String exchangeName;
  
   @com.google.gson.annotations.SerializedName(value="instrumentType")
   @com.google.gson.annotations.Expose
   private String instrumentType;
  
   @com.google.gson.annotations.SerializedName(value="firstTradeDate")
   @com.google.gson.annotations.Expose
   private long firstTradeDate;
  
   @com.google.gson.annotations.SerializedName(value="gmtoffset")
   @com.google.gson.annotations.Expose
   private long gmtoffset;
  
   @com.google.gson.annotations.SerializedName(value="timezone")
   @com.google.gson.annotations.Expose
   private String timezone;
  
   @com.google.gson.annotations.SerializedName(value="exchangeTimezoneName")
   @com.google.gson.annotations.Expose
   private String exchangeTimezoneName;
  
   @com.google.gson.annotations.SerializedName(value="previousClose")
   @com.google.gson.annotations.Expose
   private double previousClose;
  
   @com.google.gson.annotations.SerializedName(value="scale")
   @com.google.gson.annotations.Expose
   private long scale;
  
   @com.google.gson.annotations.SerializedName(value="currentTradingPeriod")
   @com.google.gson.annotations.Expose
   private CurrentTradingPeriod currentTradingPeriod;
  
   @com.google.gson.annotations.SerializedName(value="tradingPeriods")
   @com.google.gson.annotations.Expose
   private List<List<TradingPeriod>> tradingPeriods;
  
   @com.google.gson.annotations.SerializedName(value="dataGranularity")
   @com.google.gson.annotations.Expose
   private String dataGranularity;
  
   @com.google.gson.annotations.SerializedName(value="validRanges")
   @com.google.gson.annotations.Expose
   private List<String> validRanges;
   
   public Meta()
   {
      tradingPeriods = null;
      validRanges = null;
   }
   
   public Meta(String currency, String symbol, String exchangeName, String instrumentType, long firstTradeDate,
               long gmtoffset, String timezone, String exchangeTimezoneName, double previousClose, long scale,
               CurrentTradingPeriod currentTradingPeriod, List<List<TradingPeriod>> tradingPeriods,
               String dataGranularity, List<String> validRanges)
   {
      this.currency = currency;
      this.symbol = symbol;
      this.exchangeName = exchangeName;
      this.instrumentType = instrumentType;
      this.firstTradeDate = firstTradeDate;
      this.gmtoffset = gmtoffset;
      this.timezone = timezone;
      this.exchangeTimezoneName = exchangeTimezoneName;
      this.previousClose = previousClose;
      this.scale = scale;
      this.currentTradingPeriod = currentTradingPeriod;
      this.tradingPeriods = tradingPeriods;
      this.dataGranularity = dataGranularity;
      this.validRanges = validRanges;
   }
   
   public long getScale()
   {
      return scale;
   }
   
   public void setScale(long value)
   {
      scale = value;
   }
   
   public String getSymbol()
   {
      return symbol;
   }
   
   public void setSymbol(String value)
   {
      symbol = value;
   }
   
   public String getExchangeName()
   {
      return exchangeName;
   }
   
   public void setExchangeName(String value)
   {
      exchangeName = value;
   }
   
   public String getInstrumentType()
   {
      return instrumentType;
   }
   
   public void setInstrumentType(String value)
   {
      instrumentType = value;
   }
   
   public long getFirstTradeDate()
   {
      return firstTradeDate;
   }
   
   public void setFirstTradeDate(long value)
   {
      firstTradeDate = value;
   }
   
   public long getGmtoffset()
   {
      return gmtoffset;
   }
   
   public void setGmtoffset(long value)
   {
      gmtoffset = value;
   }
   
   public String getExchangeTimezoneName()
   {
      return exchangeTimezoneName;
   }
   
   public void setExchangeTimezoneName(String value)
   {
      exchangeTimezoneName = value;
   }
   
   public double getPreviousClose()
   {
      return previousClose;
   }
   
   public void setPreviousClose(double value)
   {
      previousClose = value;
   }
   
   public CurrentTradingPeriod getCurrentTradingPeriod()
   {
      return currentTradingPeriod;
   }
   
   public void setCurrentTradingPeriod(CurrentTradingPeriod value)
   {
      currentTradingPeriod = value;
   }
   
   public List<List<TradingPeriod>> getTradingPeriods()
   {
      return tradingPeriods;
   }
   
   public void setTradingPeriods(List<List<TradingPeriod>> value)
   {
      tradingPeriods = value;
   }
   
   public String getDataGranularity()
   {
      return dataGranularity;
   }
   
   public void setDataGranularity(String value)
   {
      dataGranularity = value;
   }
   
   public List<String> getValidRanges()
   {
      return validRanges;
   }
   
   public void setValidRanges(List<String> value)
   {
      validRanges = value;
   }
   
   public String getCurrency()
   {
      return currency;
   }
   
   public void setCurrency(String value)
   {
      currency = value;
   }
   
   public String getTimezone()
   {
      return timezone;
   }
   
   public void setTimezone(String value)
   {
      timezone = value;
   }
}