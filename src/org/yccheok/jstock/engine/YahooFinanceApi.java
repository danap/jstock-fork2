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
// Version 1.0.7.37.01 06/25/2019 Original JStock Engine YahooFinanceApi Class.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import org.yccheok.jstock.engine.yahoo.quote.QuoteResponse;

/**
 *
 * @author Dana M. Proctor
 * @version 1.0.7.9.01 06/25/2019
 */
public abstract interface YahooFinanceApi
{
    @retrofit2.http.GET(value="chart/{code}?interval=1d&indicators=quote&includeTimestamps=true&includePrePost=false&events=div|split|earn&corsDomain=finance.yahoo.com")
    public abstract retrofit2.Call<QuoteResponse> chart(@retrofit2.http.Path(value="code") java.lang.String arg0, @retrofit2.http.Query(value="period1") long arg1, @retrofit2.http.Query(value="period2") long arg2);
    
    @retrofit2.http.GET(value="quote?lang=en-US&region=US&corsDomain=finance.yahoo.com")
    public abstract retrofit2.Call<QuoteResponse> quote(@retrofit2.http.Query(value="symbols") java.lang.String quote);
}
