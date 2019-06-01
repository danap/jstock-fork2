/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2009 Yan Cheng CHEOK <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.9.02 06/01/2019
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
// Version 1.0.7.9    07/21/2015 Original Yan Cheng, JStock Engine BrazilYahooStockHistoryServer
//                               Class.
//         1.0.7.9.01 05/09/2019 Changed extends AbstractYahooStockHistoryServer too Yahoo
//                               StockHistoryServer2.
//         1.0.7.9.02 06/09/2019 Changed extends to YahooStockHistoryServer.
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.9.02 06/01/2019
 */
public class BrazilYahooStockHistoryServer extends YahooStockHistoryServer {

    public BrazilYahooStockHistoryServer(Code code) throws StockHistoryNotFoundException
    {
        super(code);
    }

    public BrazilYahooStockHistoryServer(Code code, Period period) throws StockHistoryNotFoundException
    {
        super(code, period);
    }

    public BrazilYahooStockHistoryServer(Code code, Duration duration) throws StockHistoryNotFoundException
    {
        super(code, duration);
    }

    @Override
    protected StockServer getStockServer() {
        // Don't return member variable, as NPE might occur. We do have case 
        // where constructor calls abstract method.
        // http://stackoverflow.com/questions/15327417/is-it-ok-to-call-abstract-method-from-constructor-in-java
        return new BrazilYahooStockServer();
    }
}
