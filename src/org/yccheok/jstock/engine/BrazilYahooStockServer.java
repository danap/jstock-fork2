/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2009 Yan Cheng CHEOK <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.9.01 05/07/2019
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
// Version 1.0.7.9    07/21/2015 Original Yan Cheng, JStock Engine BrazilYahooStockServer Class.
//         1.0.7.9.01 05/07/2019 Commented No Longer Used Method getYahooCSVBasedURL().
//                               Methods getStocks() & getStock() Removed throws, Class Stock
//                               NotFoundException No Longer Used. Same Methods Commented
//                               Assignment getSecond/Third Stock Instances Since No Longer
//                               Used.
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.9.01 05/07/2019
 */
public class BrazilYahooStockServer extends AbstractYahooStockServer {
    // In 1.0.7.9, removed a some point after.
    /*
    @Override
    protected String getYahooCSVBasedURL() {
        return "http://download.finance.yahoo.com/d/quotes.csv?s=";
    }
    */

    @Override
    public List<Stock> getStocks(List<Code> codes) /*throws StockNotFoundException*/
    {
        final List<Stock> tmp = super.getStocks(codes);
        final List<Stock> stocks = new ArrayList<Stock>();
        for (Stock stock : tmp) {
            // For Brazil Stock Market, change "Stock    Name" to "Stock Name".
            final String name = longSpacePattern.matcher(stock.getName()).replaceAll("").trim();
            final String symbol = longSpacePattern.matcher(stock.symbol.toString()).replaceAll("").trim();
            final Stock s = new Stock(
                    stock.code,
                    Symbol.newInstance(symbol),
                    name,
                    stock.getCurrency(),
                    stock.getBoard(),
                    stock.getIndustry(),
                    stock.getPrevPrice(),
                    stock.getOpenPrice(),
                    stock.getLastPrice(),
                    stock.getHighPrice(),
                    stock.getLowPrice(),
                    stock.getVolume(),
                    stock.getChangePrice(),
                    stock.getChangePricePercentage(),
                    stock.getLastVolume(),
                    stock.getBuyPrice(),
                    stock.getBuyQuantity(),
                    stock.getSellPrice(),
                    stock.getSellQuantity(),
                    //stock.getSecondBuyPrice(),
                    //stock.getSecondBuyQuantity(),
                    //stock.getSecondSellPrice(),
                    //stock.getSecondSellQuantity(),
                    //stock.getThirdBuyPrice(),
                    //stock.getThirdBuyQuantity(),
                    //stock.getThirdSellPrice(),
                    //stock.getThirdSellQuantity(),
                    stock.getTimestamp()
                );
            stocks.add(s);
        }
        return stocks;
    }

    @Override
    public Stock getStock(Code code) /*throws StockNotFoundException*/
    {
        final Stock tmp = super.getStock(code);
        // For Brazil Stock Market, change "Stock    Name" to "Stock Name".
        final String name = longSpacePattern.matcher(tmp.getName()).replaceAll("").trim();
        final String _symbol = longSpacePattern.matcher(tmp.symbol.toString()).replaceAll("").trim();
        final Stock stock = new Stock(
                tmp.code,
                Symbol.newInstance(_symbol),
                name,
                tmp.getCurrency(),
                tmp.getBoard(),
                tmp.getIndustry(),
                tmp.getPrevPrice(),
                tmp.getOpenPrice(),
                tmp.getLastPrice(),
                tmp.getHighPrice(),
                tmp.getLowPrice(),
                tmp.getVolume(),
                tmp.getChangePrice(),
                tmp.getChangePricePercentage(),
                tmp.getLastVolume(),
                tmp.getBuyPrice(),
                tmp.getBuyQuantity(),
                tmp.getSellPrice(),
                tmp.getSellQuantity(),
                //tmp.getSecondBuyPrice(),
                //tmp.getSecondBuyQuantity(),
                //tmp.getSecondSellPrice(),
                //tmp.getSecondSellQuantity(),
                //tmp.getThirdBuyPrice(),
                //tmp.getThirdBuyQuantity(),
                //tmp.getThirdSellPrice(),
                //tmp.getThirdSellQuantity(),
                tmp.getTimestamp()
            );
        return stock;
    }

    public BrazilYahooStockServer() {
        super();
    }

    private static final Pattern longSpacePattern = Pattern.compile("\\s\\s+");
}
