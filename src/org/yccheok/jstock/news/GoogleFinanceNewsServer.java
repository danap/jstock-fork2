/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock News GoogleFinanceNewsServer Class.
//         1.0.7.37.01 Method googleFinanceNewsFeedUrl() Changed Use of Code for Query to Symbol,
//                     the Name of the Company.
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.news;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.net.URL;
import java.net.MalformedURLException;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;
import it.sauronsoftware.feed4j.FeedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.yccheok.jstock.engine.StockInfo;

/**
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 07/04/2019
 */
public class GoogleFinanceNewsServer implements NewsServer {

    @Override
    public List<FeedItem> getMessages(StockInfo stockInfo) {
        final String feedUrl = googleFinanceNewsFeedUrl(stockInfo);
        final List<FeedItem> messages = new ArrayList<>();
        
        if (feedUrl == null) {
            return messages; 
        }
        
        final Set<String> titles = new HashSet<>();
        
        try {
            final URL url = new URL(feedUrl);
            final Feed feed = FeedParser.parse(url);
            final int items = feed.getItemCount();
        
            messages:
            for (int i = 0; i < items; i++) {
                FeedItem message = feed.getItem(i);
                message.setPubDate(Utils.getProperPubDate(message));
                
                if (message.getLink() == null || message.getTitle() == null || message.getTitle().isEmpty() || message.getPubDate() == null) {
                    continue;
                }

                for (String paidNewsUrl : Utils.getPaidNewsUrls()) {
                    if (message.getLink().toString().contains(paidNewsUrl)) {
                        // We only want free news.
                        continue messages;
                    }
                }
                
                final String title = message.getTitle(); 
                if (false == titles.add(title)) {
                    continue;
                }
                
                messages.add(message);
            }
        } catch (MalformedURLException | FeedException ex) {
            log.error(null, ex);
        }
        
        return messages;
    }

    // Possible return null.
    private String googleFinanceNewsFeedUrl(StockInfo stockInfo) {
        // https://www.google.com/finance/company_news?q=NYSE:SAN&output=rss
        
        //final String googleFormat = org.yccheok.jstock.engine.Utils.toGoogleFormat(stockInfo.code);
        final String googleFormat = stockInfo.symbol.toString();
        
        try {
            String query = java.net.URLEncoder.encode(googleFormat, "UTF-8");
            String url = "https://www.google.com/finance/company_news?output=rss&q=" + query;
            //String url = "https://duckduckgo.com/?output=rss&q=" + query;
            //String url = "https://duckduckgo.com/?q=" + query;
            return url;
        } catch (UnsupportedEncodingException ex) {
            log.error(null, ex);
        }                
        return null;
    }    
    
    private static final String TAG = GoogleFinanceNewsServer.class.getSimpleName();
    private static final Log log = LogFactory.getLog(GoogleFinanceNewsServer.class);
}
