/*
 * StockHistoryServer.java
 *
 * Created on April 22, 2007, 12:50 AM
 * Stock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.9.01 04/14/2019
 *
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2007 Cheok YanCheng <yccheok@yahoo.com>
 */
//=================================================================
// Revision History
// Changes to the code should be documented here and reflected
// in the present version number. Author information should
// also be included with the original copyright author.
//=================================================================
//
// Version 1.0.7.9    07/21/2015 Original Yan Cheng, JStock Engine StockHistoryServer Class.
//         1.0.7.9.01 04/14/2019 Added Interfaces getIntraday() & getTimeZone().
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.util.TimeZone;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.9.01 04/14/2019
 */
public interface StockHistoryServer {
    public Stock getStock(long timestamp);
    public long getTimestamp(int index);
    public int size();
    
    // Missing from 1.0.7.9, New in 1.0.7.37
    public Intraday getIntraday();
    public TimeZone getTimeZone();
    
    // Currently, we didn't see the need for sharesIssued and marketCapital to
    // become the member variables of Stock. In order to avoid memory hungry
    // monster and difficulty in constructing Stock, we will move the
    // share issued and market capital information into here.
    //
    // Currently, I am still not sure whether this is a good idea. We need
    // to experiment it out.
    public long getSharesIssued();
    public long getMarketCapital();
}
