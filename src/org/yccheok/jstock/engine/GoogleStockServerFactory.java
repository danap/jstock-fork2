/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2013 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
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
// Version 1.0.7.9     08/26/2018 Original Yan Cheng, JStock Engine GoogleStockServerFactory Class.
//         1.0.7.37.01 06/27/2019 Rebuilt. Removed Class Instance stockServer. All Methods Return
//                                Null. Class Appears to Not Be Used. StockMonitor Registers Null.
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Provides all Google servers, by using abstract factory pattern. Currently,
 * we only support MarketServer. For rest of the servers, we will either return
 * null or empty server.
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 06/27/2019
 */
public class GoogleStockServerFactory implements StockServerFactory {
    
    @Override
    public char getId() {
        return 'b';
    }
    
    private GoogleStockServerFactory() {
    }
    
    public static StockServerFactory newInstance() {
        return new GoogleStockServerFactory();
    }

    /**
     * Returns stock server for this factory.
     *
     * @return stock server for this factory
     */
    @Override
    public StockServer getStockServer() {
        return null;
    }

    /**
     * Returns stock history server for this factory based on given code. <code>
     * null</code> will be returned if fail.
     *
     * @param code the code
     * @return stock history server for this factory based on given code. <code>
     * null</code> will be returned if fail
     */
    @Override
    public StockHistoryServer getStockHistoryServer(Code code) {
        return null;
    }

    /**
     * Returns stock history server for this factory based on given code and 
     * duration. <code>null</code> will be returned if fail.
     * 
     * @param code the code
     * @param duration the duration
     * @return stock history server for this factory based on given code and 
     * duration. <code>null</code> will be returned if fail
     */
    @Override
    public StockHistoryServer getStockHistoryServer(Code code, Duration duration) {
        return null;
    }

    @Override
    public StockHistoryServer getStockHistoryServer(Code code, Period period) {
        return null;
    }

    @Override
    public DividendServer getDividendServer() {
        return null;
    }
    
    private static final Log log = LogFactory.getLog(GoogleStockServerFactory.class);
}
