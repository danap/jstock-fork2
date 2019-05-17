/*
 * JStock - Free Stock Market Software
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.9.01 05/17/2019
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
// Version 1.0.7.9    07/21/2015 Original Yan Cheng, JStock Engine StatementsStockHistoryServer Class.
//         1.0.7.9.01 05/17/2019 Added Class Instances timeZone & intraday Along With Getter Methods
//                               to Meet Interface StockHistoryServer Requirements. Constructor Derived
//                               timestamp via Parsing Long Rather Than DateFormatting. Also Changed
//                               stock Instantiation Constructor Arguments, Removed second/third/BuySell
//                               Quantity/Price. No Longer Defined Instances for That Class. In Constructor
//                               Also Derivation of timeZone & intraday.
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

//import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.file.Statement;
import org.yccheok.jstock.file.Statements;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.9.01 05/17/2019
 */
public class StatementsStockHistoryServer implements StockHistoryServer {
    private final java.util.Map<Long, Stock> historyDatabase = new HashMap<Long, Stock>();
    private final java.util.List<Long> timestamps = new ArrayList<Long>();
    private final TimeZone timeZone = TimeZone.getDefault();
    private Intraday intraday;
    
    private StatementsStockHistoryServer(Statements statements) throws ParseException {
        assert(statements.getType() == Statement.Type.StockHistory);
        
        //DateFormat dateFormat = org.yccheok.jstock.gui.Utils.getCommonDateFormat();
        Map<String, String> metadatas = statements.getMetadatas();
        double previousClosePrice = Double.MAX_VALUE;
        
        for (int i = 0, ei = statements.size(); i < ei; i++) {
            Statement statement = statements.get(i);
            assert(statement.getType() == Statement.Type.StockHistory);
            //final long timestamp = dateFormat.parse(statement.getAtom(0).getValue().toString()).getTime();
            final long timestamp = Long.parseLong(statement.getAtom(0).getValue().toString());
            double openPrice = Double.parseDouble(statement.getAtom(1).getValue().toString());
            double highPrice = Double.parseDouble(statement.getAtom(2).getValue().toString());
            double lowPrice = Double.parseDouble(statement.getAtom(3).getValue().toString());
            double closePrice = Double.parseDouble(statement.getAtom(4).getValue().toString());
            double prevPrice = (previousClosePrice == Double.MAX_VALUE) ? 0 : previousClosePrice;
            long volume = Long.parseLong(statement.getAtom(5).getValue().toString());
            double changePrice = (previousClosePrice == Double.MAX_VALUE) ? 0 : closePrice - previousClosePrice;
            double changePricePercentage = ((previousClosePrice == Double.MAX_VALUE) || (previousClosePrice == 0.0)) ? 0 : changePrice / previousClosePrice * 100.0;
            
            Code code = Code.newInstance(metadatas.get("code"));
            Symbol symbol = Symbol.newInstance(metadatas.get("symbol"));
            String name = metadatas.get("name");
            Board board = Board.valueOf(metadatas.get("board"));
            Industry industry = Industry.valueOf(metadatas.get("industry"));
            
            Stock stock = new Stock(
                    code,
                    symbol,
                    name,
                    null,
                    board,
                    industry,
                    prevPrice,
                    openPrice,
                    closePrice, /* Last Price. */
                    highPrice,
                    lowPrice,
                    volume,
                    changePrice,
                    changePricePercentage,
                    0,
                    0.0,
                    0,
                    0.0,
                    0,
                    //0.0,
                    //0,
                    //0.0,
                    //0,
                    //0.0,
                    //0,
                    //0.0,
                    //0,
                    timestamp
                    );

            historyDatabase.put(timestamp, stock);
            timestamps.add(timestamp);
            previousClosePrice = closePrice;
        }
        
        String timeZoneId = metadatas.get("timeZone");
        
        if (timeZoneId != null && !timeZoneId.isEmpty())
           timeZone.setID(timeZoneId);
        
        String open = metadatas.get("intradayOpen");
        String close = metadatas.get("intradayClose");
        
        // Not the way I would of handled. Would
        // not left null.
        if (open != null && !open.isEmpty()
            && close != null & !close.isEmpty())
        {
           try
           {
              intraday = new Intraday(Long.parseLong(open), Long.parseLong(close));
           }
           catch (NumberFormatException e)
           {
              log.error(null, e);
              intraday = null;
           }  
        }
        else
           intraday = null; 
    }
    
    public static StatementsStockHistoryServer newInstance(Statements statements) {
        assert(statements != null);
        
        if (statements.getType() != Statement.Type.StockHistory) {
            return null;
        }
        
        try {
            return new StatementsStockHistoryServer(statements);
        } catch (Exception ex) {
            log.error(null, ex);
            return null;
        }
    }
    
    @Override
    public Stock getStock(long timestamp) {
        return historyDatabase.get(timestamp);
    }

    @Override
    public long getTimestamp(int index) {
        return timestamps.get(index);
    }

    @Override
    public int size() {
        return timestamps.size();
    }

    @Override
    public long getSharesIssued() {
        return 0;
    }

    @Override
    public long getMarketCapital() {
        return 0;
    }
    
    @Override
    public Intraday getIntraday()
    {
       return intraday;
    }

    @Override
    public TimeZone getTimeZone()
    {
       return timeZone;
    }
    
    private static final Log log = LogFactory.getLog(StatementsStockHistoryServer.class);
}
