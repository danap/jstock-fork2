/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2014 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 04/08/2019
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
// Version 1.0.7.9     08/26/2018 Original Yan Cheng, JStock Engine RealTimeIndexMonitor Class.
//         1.0.7.37.01 04/08/2019 Method getRealTimeStockMonitorObserver() Changed Return
//                                Type From Observer<RealTimeStockMonitor, List<Stock>> to
//                                Observer<RealTimeStockMonitor, Result>>. Same Method Changed
//                                Throughout the Use of Result Rather Than List<Stock>. Commented
//                                Methods getDelay() & getTotalScanned().
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.yccheok.jstock.engine.RealTimeStockMonitor.Result;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 04/08/2019
 */
public class RealTimeIndexMonitor extends Subject<RealTimeIndexMonitor, java.util.List<Market>> {
    /** Creates a new instance of RealTimeIndexMonitor */
    public RealTimeIndexMonitor(int maxThread, int maxBucketSize, long delay) {
        realTimeStockMonitor = new RealTimeStockMonitor(maxThread, maxBucketSize, delay);
        
        realTimeStockMonitor.attach(getRealTimeStockMonitorObserver());
    }
    
    private Observer<RealTimeStockMonitor, Result> getRealTimeStockMonitorObserver() {
       return new Observer<RealTimeStockMonitor, Result>() {
            
            @Override
            public void update(RealTimeStockMonitor subject, Result result) {
                
                List<Market> markets = new ArrayList<Market>();
                
                for (Stock stock : result.stocks) {
                    Index index = indexMapping.get(stock.code);
                    if (index != null) {
                        markets.add(Market.newInstance(stock, index));
                    }
                }
                
                if (false == markets.isEmpty()) {
                    RealTimeIndexMonitor.this.notify(RealTimeIndexMonitor.this, markets);
                }
            }
            
        };
    }
    
    public synchronized boolean addIndex(Index index) {
        if (realTimeStockMonitor.addStockCode(index.code)) {
            indexMapping.put(index.code, index);
            return true;
        }
        return false;
    }
    
    public synchronized boolean isEmpty() {
        return realTimeStockMonitor.isEmpty();
    }
    
    public synchronized boolean clearIndices() {
        indexMapping.clear();        
        return realTimeStockMonitor.clearStockCodes();
    }
    
    public synchronized boolean removeIndex(Index index) {
        indexMapping.remove(index.code);
        return realTimeStockMonitor.removeStockCode(index.code);
    } 
    
    public synchronized void resume() {
        realTimeStockMonitor.resume();
    }

    public synchronized void suspend() {
        realTimeStockMonitor.suspend();
    } 
    
    public synchronized void startNewThreadsIfNecessary() {
        realTimeStockMonitor.startNewThreadsIfNecessary();
    }
    
    public synchronized void rebuild() {
        realTimeStockMonitor.rebuild();
    }
    
    public synchronized void stop() {
        realTimeStockMonitor.stop();
    }
    
    public synchronized void refresh() {
        realTimeStockMonitor.refresh();
    }
    
    // Not in 1.0.7.37 or private.
    /*
    public synchronized long getDelay() {
        return realTimeStockMonitor.getDelay();
    }
    */
    
    public synchronized void setDelay(int delay) {
        realTimeStockMonitor.setDelay(delay);
    }
    
    // Not in 1.0.7.37 or private.
    /*
    public int getTotalScanned() {
        return realTimeStockMonitor.getTotalScanned();
    }
    */
    
    private final Map<Code, Index> indexMapping = new ConcurrentHashMap<Code, Index>();
    private final RealTimeStockMonitor realTimeStockMonitor;
}
