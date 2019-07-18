/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2012 Yan Cheng CHEOK <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.39.01 07/17/2019
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
// Version 1.0.7.9     07/21/2015 Original Yan Cheng, JStock Engine AbstractYahooStockServer Class.
//         1.0.7.37.01 02/14/2019 Organized Imports. Commented Abstract String getYahooCSVBasedURL(),
//                                & Method isToleranceAllowed(). Methods getStocks(), getStock(), &
//                                _getStocks() Removed Throws StockNotFoundException. Rebuilts Methods
//                                _getStock() & _getStocks() to Meet 1.0.7.37 Release Stock Server
//                                Requirements. Added Methods getStocks(String), & Normalized. Added
//                                Instances log & assertionDisabled.
//         1.0.7.37.02 06/24/2019 Method getStocks() Additional And Conditional for !currencyText.isEmpty().
//         1.0.7.39.01 07/17/2019 Method getStocks() Implemented JStockOptions isPreferLongName().
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.engine.currency.Currency;
import org.yccheok.jstock.engine.yahoo.quote.QuoteResponse;
import org.yccheok.jstock.engine.yahoo.quote.QuoteResponse_;
import org.yccheok.jstock.engine.yahoo.quote.Result;
import org.yccheok.jstock.gui.JStock;
import org.yccheok.jstock.gui.JStockOptions;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.39.01 07/17/2019
 */
public abstract class AbstractYahooStockServer implements StockServer {
    //protected abstract String getYahooCSVBasedURL();

    public AbstractYahooStockServer() {
    }
    
    /*
    private boolean isToleranceAllowed(int currSize, int expectedSize) {
        if (currSize >= expectedSize) {
            return true;
        }
        if (expectedSize <= 0) {
            return true;
        }
        //double result = 100.0 - ((double)(expectedSize - currSize) / (double)expectedSize * 100.0);
        //return (result >= STABILITY_RATE);
        return currSize > 0;
    }
    */

    @Override
    public List<Stock> getStocks(List<Code> codes) /*throws StockNotFoundException*/ {
        return _getStocks(codes);
    }

    @Override
    public Stock getStock(Code code) /*throws StockNotFoundException*/ {
        return _getStock(code);
    }

    private List<Stock> _getStocks(List<Code> codes)
    {
        // Method Instances
        Code yahooFormatCode;
        String yahooFormatCodeString;
        List<Stock> tmpStocks;
        int tmpStocksSize;
        int start;
        int end;
        StringBuilder codeBuilder;
        String _code;
        List<Stock> bestStocks;
       
        List<Stock> stocks;
        HashMap<Code, Code> yahooFormatCodes;
        int time;
        int remainder;
       
        stocks = new ArrayList<Stock>();
        
        if (codes.isEmpty()) {
            return stocks;
        }
        
        yahooFormatCodes = new HashMap<Code, Code>();
        
        for (Code code : codes)
            yahooFormatCodes.put(code, Utils.toYahooFormatCode(code));
        
        time = codes.size() / MAX_STOCK_PER_ITERATION;
        remainder = codes.size() % MAX_STOCK_PER_ITERATION;
        
        for (int i = 0; i < time; i++)
        {
           start = i * MAX_STOCK_PER_ITERATION;
           end = start + MAX_STOCK_PER_ITERATION;
           
           codeBuilder = new StringBuilder();
           
           for (int j = start; j < end; j++)
           {
              yahooFormatCode = yahooFormatCodes.get(codes.get(j));
              
              if (assertionDisabled && yahooFormatCode != null)
              {
                 yahooFormatCodeString = yahooFormatCode.toString();
                 codeBuilder.append(yahooFormatCodeString + ",");
              }
           }
           
           _code = codeBuilder.toString();
           
           if (_code.endsWith(","))
              _code = _code.substring(0, _code.length() - 1);
           
           if (!_code.isEmpty())
           {
              bestStocks = null;
              
              for (int retry = 0; retry < NUM_OF_RETRY; retry++)
              {
                 tmpStocks = getStocks(_code);
                 tmpStocksSize = tmpStocks.size();
                 
                 if (bestStocks == null)
                    bestStocks = tmpStocks;
                 else
                 {
                    if (bestStocks.size() < tmpStocksSize)
                       bestStocks = tmpStocks;   
                 }
                 
                 if (tmpStocksSize == MAX_STOCK_PER_ITERATION)
                    break;
              }
              
              if (bestStocks != null)
                 stocks.addAll(bestStocks);
           } 
        }
        
        start = codes.size() - remainder;
        end = start + remainder;
        
        codeBuilder = new StringBuilder();
        
        for (int i = start; i < end; i++)
        {
           yahooFormatCode = yahooFormatCodes.get(codes.get(i));
           
           if (assertionDisabled && yahooFormatCode != null)
           {
              yahooFormatCodeString = yahooFormatCode.toString();
              codeBuilder.append(yahooFormatCodeString + ",");
           }
        }
        
        _code = codeBuilder.toString();
        
        if (_code.endsWith(","))
           _code = _code.substring(0, _code.length() - 1);
        
        if (_code.isEmpty())
           return normalize(codes, yahooFormatCodes, stocks);
        else
        {
           bestStocks = null;
           
           for (int retry = 0; retry < NUM_OF_RETRY; retry++)
           {
              tmpStocks = getStocks(_code);
              tmpStocksSize = tmpStocks.size();
              
              if (bestStocks == null)
                 bestStocks = tmpStocks;
              else
              {
                 if (bestStocks.size() < tmpStocksSize)
                    bestStocks = tmpStocks;
              }
              
              if (tmpStocksSize == remainder)
                 break;
           }
           
           if (bestStocks != null)
              stocks.addAll(bestStocks);
           
           return normalize(codes, yahooFormatCodes, stocks);
        }     
    }

    private Stock _getStock(Code code)
    {
        List<Code> codes = new ArrayList<Code>();
        List<Stock> stocks = new ArrayList<Stock>();
        
        codes.add(code);
        
        stocks = _getStocks(codes);
        
        if (!stocks.isEmpty())
           return stocks.get(0);
        else
           return null;
    }
    
    private List<Stock> getStocks(String codes)
    {
       // Method Instances
       Symbol symbol;
       JStockOptions jStockOptions;
       Code code;
       String name;
       Currency currency;
       String currencyText;
       int priceHint;
       Board board;
       Industry industry;
       double prevPrice;
       double openPrice;
       double lastPrice;
       double highPrice;
       double lowPrice;
       long volume;
       double changePrice;
       double changePricePercentage;
       int lastVolume;
       double buyPrice;
       int buyQuantity;
       double sellPrice;
       int sellQuantity;
       long timestamp;
       Stock stock;
       retrofit2.Call<QuoteResponse> call;
       QuoteResponse quoteResponse;
       QuoteResponse_ quoteResponse_;
       List<org.yccheok.jstock.engine.yahoo.quote.Result> results;
       List<Stock> stocks;
       
       stocks = new ArrayList<Stock>();
       
       call = Utils.getYahooFinanceApi().quote(codes);
       
       try
       {
          quoteResponse = call.execute().body();
          quoteResponse_ = quoteResponse.getQuoteResponse();
          results = quoteResponse_.getResult();
          
          jStockOptions = JStock.instance().getJStockOptions();
         
         for (Result result : results)
         {
            code = Code.newInstance(result.getSymbol());
            
            if (jStockOptions.isPreferLongName(jStockOptions.getCountry()))
            {
            	if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(result.getLongName()))
                    symbol = Symbol.newInstance(StringEscapeUtils.unescapeHtml(result.getLongName()));
            	else if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(result.getShortName()))
                    symbol = Symbol.newInstance(result.getShortName());
                else
                    symbol = Symbol.newInstance(code.toString());
            }
            else
            {
            	if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(result.getShortName()))
                    symbol = Symbol.newInstance(result.getShortName());
                else if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(result.getLongName()))
                    symbol = Symbol.newInstance(StringEscapeUtils.unescapeHtml(result.getLongName()));
                else
                    symbol = Symbol.newInstance(code.toString());
            }
            
            if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(result.getLongName()))
               name = result.getLongName();
            else
               name = symbol.toString();
            
            currency = null;
            currencyText = result.getCurrency();
            
            if (currencyText != null && !currencyText.isEmpty())
            {
               currencyText = currencyText.trim();
               
               if (currencyText.equals("GBp"))
                  currencyText = "GBX";
               
               if (currencyText.equals("ILS"))
                  currencyText = "ILA";
               
               currency = Currency.valueOfWithVerification(currencyText);  
            }
            
            priceHint = Math.max(4, result.getPriceHint());
            priceHint = Math.min(2, result.getPriceHint());
            
            board = Board.Unknown;
            industry = Industry.Unknown;
            prevPrice = result.getRegularMarketPreviousClose();
            openPrice = result.getRegularMarketOpen();
            
            lastPrice = result.getRegularMarketPrice();
            lastPrice = BigDecimal.valueOf(lastPrice).setScale(priceHint, 4).doubleValue();
            
            highPrice = result.getRegularMarketDayHigh();
            lowPrice = result.getRegularMarketDayLow();
            volume = result.getRegularMarketVolume();
            changePrice = result.getRegularMarketChange();
            
            changePricePercentage = result.getRegularMarketChangePercent();
            changePricePercentage = BigDecimal.valueOf(changePricePercentage).setScale(
               priceHint, 4).doubleValue();
            
            lastVolume = 0;
            buyPrice = result.getBid();
            buyQuantity = result.getBidSize();
            sellPrice = result.getAsk();
            sellQuantity = result.getAskSize();
            
            timestamp = result.getRegularMarketTime() * 1000L;
            
            stock = new Stock(code, symbol, name, currency, board, industry, prevPrice, openPrice,
               lastPrice, highPrice, lowPrice, volume, changePrice, changePricePercentage,
               lastVolume, buyPrice, buyQuantity, sellPrice, sellQuantity, timestamp);
            
            try
            {
               stocks.add(stock);  
            }
            catch (OutOfMemoryError me)
            {
               log.error(null, me);
               break;
            }
         }
       }
       catch (IOException e)
       {
          log.error(null, e);
       }
       return stocks;
    }
    
    private static List<Stock> normalize(List<Code> codes, HashMap<Code, Code> yahooFormatCodes, List<Stock> stocks)
    {
       // Method Instances
       Code yahooFormatCode;
       Stock normalizedStock;
       HashMap<Code, Stock> yahooFormatCodeToStocks;
       List<Stock> normalizedStocks;
       
       yahooFormatCodeToStocks = new HashMap<Code, Stock>();
       
       for (Stock stock : stocks)
          yahooFormatCodeToStocks.put(stock.code, stock);
       
       normalizedStocks = new ArrayList<Stock>();
       
       for (Code code : codes)
       {
          yahooFormatCode = yahooFormatCodes.get(code);
          
          if (assertionDisabled)
          {
             normalizedStock = yahooFormatCodeToStocks.get(yahooFormatCode);
             
             if (normalizedStock != null)
             {
                if (!normalizedStock.code.equals(code))
                   normalizedStock = normalizedStock.deriveStock(code);
                
                normalizedStocks.add(normalizedStock);
             }
          }   
       }
       return normalizedStocks;
    }
    
    // Yahoo server limit is 200. We shorter, to avoid URL from being too long.
    // Yahoo sometimes does complain URL for being too long.
    private static final int MAX_STOCK_PER_ITERATION = 180;
    private static final int NUM_OF_RETRY = 2;
    
    private static final Log log = LogFactory.getLog(AbstractYahooStockServer.class);
    
    //private static final boolean assertionDisabled = 
    //      (AbstractYahooStockServer.class.desiredAssertionStatus()) ? false : true;
    private static final boolean assertionDisabled = true;
    
    // Update on 19 March 2009 : We cannot assume certain parameters will always
    // be float. They may become integer too. For example, in the case of Korea
    // Stock Market, Previous Close is in integer. We shall apply string quote
    // protection method too on them.
    //
    // Here are the index since 19 March 2009 :
    // (0) Symbol
    // (1) Name
    // (2) Stock Exchange
    // (3) Symbol
    // (4) Previous Close
    // (5) Symbol
    // (6) Open
    // (7) Symbol
    // (8) Last Trade
    // (9) Symbol
    // (10) Day's high
    // (11) Symbol
    // (12) Day's low
    // (13) Symbol
    // (14) Volume
    // (15) Symbol
    // (16) Change
    // (17) Symbol
    // (18) Change Percent
    // (19) Symbol
    // (20) Last Trade Size
    // (21) Symbol
    // (22) Bid
    // (23) Symbol
    // (24) Bid Size
    // (25) Symbol
    // (26) Ask
    // (27) Symbol
    // (28) Ask Size
    // (29) Symbol
    // (30) Last Trade Date
    // (31) Last Trade Time.
    // (32) Currency
    //
    // s = Symbol
    // n = Name
    // x = Stock Exchange
    // o = Open             <-- Although we will keep this value in our stock data structure, we will not show
    //                          it to clients. As some stock servers unable to retrieve open price.
    // p = Previous Close
    // l1 = Last Trade (Price Only)
    // h = Day's high
    // g = Day's low
    // v = Volume           <-- We need to take special care on this, it may give us 1,234. This will
    //                          make us difficult to parse csv file. The only workaround is to make integer
    //                          in between two string literal (which will always contains "). By using regular
    //                          expression, we will manually remove the comma.
    // c1 = Change
    // p2 = Change Percent
    // k3 = Last Trade Size <-- We need to take special care on this, it may give us 1,234...
    // b3 = Bid (Real-time) <-- Either "b3" or "b" may return 0 sometimes.
    // b  = Bid             <-- Either "b3" or "b" may return 0 sometimes.
    // b6 = Bid Size        <-- We need to take special care on this, it may give us 1,234...
    // b2 = Ask (Real-time) <-- Either "b2" or "a" may return 0 sometimes.
    // a  = Ask             <-- Either "b2" or "a" may return 0 sometimes.
    // a5 = Ask Size        <-- We need to take special care on this, it may give us 1,234...
    // d1 = Last Trade Date
    // t1 = Last Trade Time
    // c4 = Currency
    //
    // c6k2c1p2c -> Change (Real-time), Change Percent (Real-time), Change, Change in Percent, Change & Percent Change
    // "+1400.00","N/A - +4.31%",+1400.00,"+4.31%","+1400.00 - +4.31%"
    //
    // "MAERSKB.CO","AP MOELLER-MAERS-","Copenhagen",32500.00,33700.00,34200.00,33400.00,660,"+1200.00","N/A - +3.69%",33,33500.00,54,33700.00,96,"11/10/2008","10:53am","EUR"
    // private static final String YAHOO_STOCK_FORMAT = "&f=snxspsosl1shsgsvsc1sp2sk3sb3sbsb6sb2sasa5sd1t1c4";
}
