/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2009 Yan Cheng CHEOK <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 06/02/2019
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
// Version 1.0.7.9     08/26/2018 Original Yan Cheng, JStock Engine YahooStockHistoryServer Class.
//         1.0.7.37.01 06/02/2019 Complete Rebuild of Class to Meet JStock 1.0.7.37 Release
//                                Changes.
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.engine.Stock;
import org.yccheok.jstock.engine.yahoo.chart.ChartResponse;
import org.yccheok.jstock.engine.yahoo.chart.Quote;
import org.yccheok.jstock.engine.yahoo.quote.QuoteResponse;
import org.yccheok.jstock.engine.yahoo.quote.QuoteResponse_;

/**
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 06/02/2019
 * 
 */
public class YahooStockHistoryServer implements StockHistoryServer
{
   // Class Instances.
   private java.util.Map<Long, Stock> historyDatabase;
   private java.util.List<Long> timestamps;

   private Code code;
   private Duration duration;
   private Period period;
   private TimeZone timeZone;
   private Intraday intraday;
   
   private String query;
   private Stock s;
   
   private static final Period DEFAULT_HISTORY_PERIOD = Period.Years10;
   private static final long DAYS30_IN_MILISECONDS = 30L * 24L * 60L * 60L * 1000L; // Long 2592000000
   private static final long HRS12_IN_MILISECONDS = (1L * 24L * 60L * 60L * 1000L) / 2L; // Long 43200000
   
   private static final Log log = LogFactory.getLog(YahooStockHistoryServer.class);

   //==============================================================
   // Constructors.
   //==============================================================

   public YahooStockHistoryServer(Code code) throws StockHistoryNotFoundException
   {
      this(code, DEFAULT_HISTORY_PERIOD);
   }

   public YahooStockHistoryServer(Code code, Period _period) throws StockHistoryNotFoundException
   {
      this.code = code;
      this.period = _period;
      
      historyDatabase = new HashMap<Long, Stock>();
      timestamps = new ArrayList<Long>();
      
      timeZone = null;
      intraday = null;
      duration = null;
      
      if (code == null)
         throw new IllegalArgumentException("Code cannot be null");
      
      if (period == null)
         period = DEFAULT_HISTORY_PERIOD;
      
      query = getYahooCode(code);
      initBasedOnPeriodUsingV8(code, query);
   }

   public YahooStockHistoryServer(Code code, Duration duration) throws StockHistoryNotFoundException
   {
      // Method Instances.
      long startTimestamp;
      long endTimestamp;
      long timestamp;

      Period bestPeriod;
      YahooStockHistoryServer y;

      this.code = code;
      this.duration = duration;

      historyDatabase = new HashMap<Long, Stock>();
      timestamps = new ArrayList<Long>();

      period = null;
      timeZone = null;
      intraday = null;
      
      if (code == null || duration == null)
         throw new IllegalArgumentException("Code or duration cannot be null");

      query = getYahooCode(code);

      bestPeriod = Utils.toBestPeriod(duration, timeZone);

      y = new YahooStockHistoryServer(code, bestPeriod);
      this.s = y.s;
      this.timeZone = y.timeZone;
      this.intraday = y.intraday;
      
      if (timeZone == null)
      {
         startTimestamp = duration.getStartDate().getCalendar().getTimeInMillis();
         endTimestamp = duration.getEndDate().getCalendar().getTimeInMillis();
      }
      else
      {
         startTimestamp = duration.getStartDate().getCalendar(timeZone).getTimeInMillis();
         endTimestamp = duration.getEndDate().getCalendar(timeZone).getTimeInMillis();
      }
      
      for (int i = 0; i < y.timestamps.size(); i++)
      {
         timestamp = y.timestamps.get(i);
         
         if (timestamp >= startTimestamp && timestamp <= endTimestamp)
         {
            timestamps.add(timestamp);
            historyDatabase.put(timestamp, y.historyDatabase.get(timestamp));
         }
      }
      
      if (historyDatabase.isEmpty())
         throw new StockHistoryNotFoundException("Code Not Found: " + code.toString());
   }
   
   //==============================================================
   // Method to check the code symbol for an underlining name
   // with Yahoo.
   //==============================================================
   
   private String getYahooCode(Code code)
   {
      // Method Instances
      QuoteResponse quoteResponse;
      QuoteResponse_ quoteResponse_;
      List<org.yccheok.jstock.engine.yahoo.quote.Result> results;
      String underlyingSymbol;
      retrofit2.Call<QuoteResponse> c;

      query = Utils.toYahooFormat(code);

      if (Utils.needToResolveUnderlyingCode(code))
      {
         c = Utils.getYahooFinanceApi().quote(query);

         try
         {
            quoteResponse = c.execute().body();
            quoteResponse_ = quoteResponse.getQuoteResponse();
            results = quoteResponse_.getResult();
            underlyingSymbol = results.get(0).getUnderlyingSymbol();

            if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(underlyingSymbol))
               query = underlyingSymbol;
            else
               throw new IOException("Failed to Resolve Underlying Code.");
         }
         catch (IOException e)
         {
            log.error(null, e);
         }
      }
      return query;
   }
   
   //==============================================================
   // Method to derive the appropriate retrofit2.Call? with Chart
   // Responses based on period, main constructor is based on
   // the Code, Period.
   //==============================================================
   
   private void initBasedOnPeriodUsingV8(Code code, String query) throws StockHistoryNotFoundException
   {
      // Method Instances
      retrofit2.Call<ChartResponse> call;
      String range;

      timeZone = null;
      intraday = null;
      historyDatabase.clear();
      timestamps.clear();

      if (period == Period.Day1)
      {
         range = "1d";
         call = Utils.getYahooFinanceChartApiV8().intradayChart(query);
      }
      else
      {
         if (period == Period.Days7)
            range = "5d";
         else if (period == Period.Month1)
            range = "1mo";
         else if (period == Period.Months3)
            range = "3mo";
         else if (period == Period.Months6)
            range = "6mo";
         else if (period == Period.Year1)
            range = "1y";
         else if (period == Period.Years5)
            range = "5y";
         else
            range = "10y";

         call = Utils.getYahooFinanceChartApiV8().dailyChartByRange(query, range);
      }
      init(call);

      if (historyDatabase.isEmpty())
         throw new StockHistoryNotFoundException("Stock " + code.toString() + " Not Found.");
   }
   
   //==============================================================
   // Method to collect the quotes based on the result derived from
   // the given call chart response. The result holds the timestamp
   // of each quote. Timestamps, time, progress from early to later.
   // Standard time progression.
   //==============================================================
   
   private void init(retrofit2.Call<ChartResponse> call)
   {
      // Method Instances
      long timestamp;
      long currentTimeInMilli;
      double closePrice;
      double highPrice;
      double lowPrice;
      double openPrice;
      long volume;
      double prevPrice;
      double changePrice;
      double changePricePercentage;
      Stock stock;
      
      ChartResponse chartResponse;
      List<org.yccheok.jstock.engine.yahoo.chart.Result> results;
      org.yccheok.jstock.engine.yahoo.chart.Result result;
      List<Long> timestamps;

      String timezoneName;
      long startTimeInMilli;
      long endTimeInMilli;
      Quote quote;
      List<Double> closes;
      List<Double> highs;
      List<Double> lows;
      List<Double> opens;
      List<Long> volumes;
      double previousClosePrice;
      Symbol symbol;
      String name;
      Board board;
      Industry industry;
      Calendar calendar;
      long diff;
      int size;
      
      try
      {
         chartResponse = call.execute().body();

         if (chartResponse == null)
            return;

         results = chartResponse.getChart().getResult();

         if (results.isEmpty())
            return;

         result = results.get(0);

         timestamps = result.getTimestamp();
         int ie = timestamps.size();

         if (ie <= 0)
            return;

         timezoneName = result.getMeta().getExchangeTimezoneName();
         timeZone = TimeZone.getTimeZone(timezoneName);

         // Derive duation and period.
         if (duration == null)
         {
            startTimeInMilli = -1L;
            endTimeInMilli = -1L;
         }
         else
         {
            startTimeInMilli = duration.getStartDate().getTime().getTime();
            endTimeInMilli = duration.getEndDate().getTime().getTime();
         }

         if (period == Period.Day1)
            intraday = new Intraday(result.getMeta().getTradingPeriods().get(0).get(0).getStart() * 1000L,
                                    result.getMeta().getTradingPeriods().get(0).get(0).getEnd() * 1000L);
         else
            intraday = null;

         quote = result.getIndicators().getQuote().get(0);
         closes = quote.getClose();
         highs = quote.getHigh();
         lows = quote.getLow();
         opens = quote.getOpen();
         volumes = quote.getVolume();

         previousClosePrice = Double.MAX_VALUE;

         symbol = Symbol.newInstance(code.toString());
         name = symbol.toString();
         board = Board.Unknown;
         industry = Industry.Unknown;

         s = getStockServer().getStock(code);

         if (s != null)
         {
            symbol = s.symbol;
            name = s.getName();
            board = s.getBoard();
            industry = s.getIndustry();
         }

         calendar = Calendar.getInstance(timeZone);
         
         int i = 0;
         
         do
         {
            timestamp = timestamps.get(i);
            currentTimeInMilli = timestamp * 1000L;

            if (period != Period.Day1)
            {
               calendar.setTimeInMillis(currentTimeInMilli);
               Utils.resetCalendarTime(calendar);

               currentTimeInMilli = calendar.getTimeInMillis();
               
               if (startTimeInMilli > 0 && (currentTimeInMilli < startTimeInMilli))
                  continue;
               
               if (endTimeInMilli > 0 && (currentTimeInMilli > endTimeInMilli))
                  break;
            }
            
            closePrice = toDouble(closes.get(i));

            if (!Utils.essentiallyEqual(closePrice, 0.0))
            {
               highPrice = toDouble(highs.get(i));
               lowPrice = toDouble(lows.get(i));
               openPrice = toDouble(opens.get(i));
               volume = toLong(volumes.get(i));

               prevPrice = (previousClosePrice == Double.MAX_VALUE) ? 0 : previousClosePrice;
               changePrice = (previousClosePrice == Double.MAX_VALUE)
                     ? 0 : closePrice - previousClosePrice;
               changePricePercentage = ((previousClosePrice == Double.MAX_VALUE)
                                         || (previousClosePrice == 0.0))
                     ? 0 : (changePrice/previousClosePrice) * 100.0;

               stock = new Stock(code, symbol, name, null, board, industry, prevPrice, openPrice,
                                 closePrice, highPrice, lowPrice, volume, changePrice,
                                 changePricePercentage, 0, 0.0, 0, 0.0, 0, currentTimeInMilli);

               historyDatabase.put(currentTimeInMilli, stock);
               this.timestamps.add(currentTimeInMilli);
               previousClosePrice = closePrice;
            }
            i++;
         }
         while (i < ie);
         
         if (period == Period.Day1)
            return;

         size = this.timestamps.size();

         // Determine if hourly data shoule
         // be appended.
         if (size > 1)
         {
            diff = this.timestamps.get(size - 1).longValue() - this.timestamps.get(size - 2).longValue();
            
            if (diff > DAYS30_IN_MILISECONDS)
               appendHourlyData();
         }
         else
            appendHourlyData();
      }
      catch (IOException e)
      {
         log.error(null, e);
      }
   }
   
   private void appendHourlyData()
   {
      // Method Instances
      retrofit2.Call<org.yccheok.jstock.engine.yahoo.chart.ChartResponse> call;
      Map<Long, Stock> h;
      List<Long> t;
      
      long startingTimestamp;
      long endingTimestamp;
      long t1;
      long t0;
      boolean ok;
      double closePrice;
      double highPrice;
      double lowPrice;
      double openPrice;
      long volume;
      double prevPrice;
      double changePrice;
      double changePricePercentage;
      Stock stock;
      ChartResponse chartResponse;
      List<org.yccheok.jstock.engine.yahoo.chart.Result> results;
      org.yccheok.jstock.engine.yahoo.chart.Result result;
      List<Long> timestamps;
      int ei;
      org.yccheok.jstock.engine.yahoo.chart.Quote quote;
      List<Double> closes;
      List<Double> highs;
      List<Double> lows;
      List<Double> opens;
      List<Long> volumes;
      double previousClosePrice;
      Symbol symbol;
      String name;
      Board board;
      Industry industry;
      Calendar calendar;
      Stock hh;
      String range;
      int size;
      
      int local;
      
      range = null;
      
      if (period == Period.Days7)
         range = "5d";
      else if (period == Period.Month1)
         range = "1mo";
      else if (period == Period.Months3)
         range = "3mo";
      else if (period == Period.Months6)
         range = "6mo";
      else
         range = "1y";
      
      size = this.timestamps.size();
      
      if (size > 1)
      {
         startingTimestamp = this.timestamps.get(size - 2);
         endingTimestamp = this.timestamps.get(size - 1);
      }
      else
      {
         startingTimestamp = -1L;
         
         if (size == 0)
            endingTimestamp = -1L;
         else
            endingTimestamp = this.timestamps.get(0);           
      }
      
      h = new HashMap<Long, Stock>();
      t = new ArrayList<Long>();
      
      call = Utils.getYahooFinanceChartApiV8().hourlyChartByRange(query, range);
      
      try
      {
         chartResponse = call.execute().body();
         
         if (chartResponse == null)
            return;
         
         results = chartResponse.getChart().getResult();
         
         if (results.isEmpty())
            return;
         
         result = results.get(0);
         timestamps = result.getTimestamp();
         ei = timestamps.size();
         
         if (ei <= 0)
            return;
         
         quote = result.getIndicators().getQuote().get(0);
         closes = quote.getClose();
         highs = quote.getHigh();
         lows = quote.getLow();
         opens = quote.getOpen();
         volumes = quote.getVolume();
         
         previousClosePrice = Double.MAX_VALUE;
         
         symbol = Symbol.newInstance(code.toString());
         name = symbol.toString();
         board = Board.Unknown;
         industry = Industry.Unknown;
         
         if (s != null)
         {
            symbol = s.symbol;
            name = s.getName();
            board = s.getBoard();
            industry = s.getIndustry();
         }
         
         calendar = Calendar.getInstance(timeZone);
         
         int i = 0;
         
         do
         {
            t0 = timestamps.get(i) * 1000L;
            ok = false;
            
            if (i < (ei - 1) && i != (ei -1))
            {
               t1 = timestamps.get(i + 1) * 1000;
               
               if ((t1 - t0) > HRS12_IN_MILISECONDS)
                  ok = true;
            }
            
            if (ok)
            {
               calendar.setTimeInMillis(t0);
               Utils.resetCalendarTime(calendar);
               t0 = calendar.getTimeInMillis();
               
               if ((startingTimestamp > 0 && endingTimestamp > 0)
                    && (t0 >= startingTimestamp && t0 < endingTimestamp))
               {
                  closePrice = toDouble(closes.get(i));

                  if (!Utils.essentiallyEqual(closePrice, 0.0))
                  {
                     highPrice = toDouble(highs.get(i));
                     lowPrice = toDouble(lows.get(i));
                     openPrice = toDouble(opens.get(i));
                     volume = toLong(volumes.get(i));

                     // ????
                     prevPrice = (previousClosePrice == Double.MAX_VALUE) ? 0 : previousClosePrice;
                     changePrice = (previousClosePrice == Double.MAX_VALUE)
                           ? 0 : closePrice - previousClosePrice;
                     changePricePercentage = ((previousClosePrice == Double.MAX_VALUE)
                                               || (previousClosePrice == 0.0))
                           ? 0 : (changePrice/previousClosePrice) * 100.0;

                     stock = new Stock(code, symbol, name, null, board, industry, prevPrice, openPrice,
                                       closePrice, highPrice, lowPrice, volume, changePrice,
                                       changePricePercentage, 0, 0.0, 0, 0.0, 0, t0);

                     h.put(t0, stock);
                     t.add(t0);
                  }
               }
            }
            i++;
         }
         while (i < ei);
         
         local = Math.max(0, (size - 1));
            
         for (Long tt: t)
         {
            hh = h.get(tt);
            this.timestamps.add(local, tt);
            historyDatabase.put(tt, hh);
            local++;      
         }
      }
      catch (IOException e)
      {
         log.error(null, e);
      }  
   }
   
   //==============================================================
   // Methods to insure Null values will be converted to zero.
   //==============================================================
   
   private double toDouble(Double d)
   {
      if (d == null)
         return 0.0;
      else
         return Double.valueOf(d);
   }
   
   private long toLong(Long l)
   {
      if (l == null)
         return 0L;
      else
         return Long.valueOf(l);
   }
   
   //==============================================================
   // 1.0.7.9 and 1.0.7.37 Method. Its not a abstract method its
   // the abstract classes constructor and it automatically
   // calls its super() which is again abstract AbstractYahooStock
   // Server.
   //==============================================================
   
   protected StockServer getStockServer()
   {
      // Don't return member variable, as NPE might occur. We do have case
      // where constructor calls abstract method.
      // http://stackoverflow.com/questions/15327417/is-it-ok-to-call-abstract-method-from-constructor-in-java
      return new YahooStockServer();
   }
   
   //==============================================================
   // StockHistoryServer Interfaces.
   //==============================================================
   
   @Override
   public Intraday getIntraday()
   {
      return intraday;
   }
   
   @Override
   public long getMarketCapital()
   {
      return 0;
   }
   
   @Override
   public long getSharesIssued()
   {
      return 0;
   }
   
   @Override
   public Stock getStock(long timestamp)
   {
      return historyDatabase.get(timestamp);
   }
   
   @Override
   public long getTimestamp(int index)
   {
      return timestamps.get(index);
   }
   
   @Override
   public TimeZone getTimeZone()
   {
      return timeZone;
   }
   
   @Override
   public int size()
   {
      return timestamps.size();
   }
}
