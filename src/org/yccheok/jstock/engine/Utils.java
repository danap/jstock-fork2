/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2014 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.02 06/17/2019
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
// Version 1.0.7.9     07/21/2015 Original Yan Cheng, JStock Engine Utils Class.
//         1.0.7.37.01 06/14/2019 Organized Imports. Added Methods essentiallyEqual(), getYahooFinanceApi(),
//                                getYahooFinanceChartApiV8(), getIEXApi(), getRetroFit(), rectifyResult(),
//                                getStockInfoDatabaseFileDirectory(), toUniveralFormat(), toYahooFormat(),
//                                toYahooFormatCode(), isIEXUSClassShare(), toIEXFormatCode(), toIEXFormat(),
//                                needToResolveUnderlyingCode(), clearGoogleCodeDatabaseCache(), clearAll
//                                IEXStockInfoDatabaseCache(), getTimeZone(), & toBestPeriod(). Added Class
//                                Instances EPSILON, IEX_US_CLASS_SHARE_CACHE_OPTIMAL_SIZE, GOOGLE_CODE_DATABASE_
//                                CACHE_OPTIMAL_SIZE, googleSupportedExchs, googleExchToYahooSuffix, iexUSClass
//                                ShareCache, googleCodeDatabaseCache, betterLocalTimeZones, & localTimeZones.
//                                Updated one/twoLetterSuffixes, countries, toGoogleIndex, defaultPriceSource,
//                                & createPriceSourceMap. Method getEmptyStock() Updated Instantiation of Stock
//                                Arguments. Method getStocks() Commented try/catch for StockNotFoundException.
//                                Method getStockFromCSVFile() Changed Close Call From gui.Utils to file.Utils.
//         1.0.7.37.02 06/17/2019 Updated Methods toCountry(), & isUSStock(). Added Method toGoogleIndexSubset().
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.file.Statements;
import org.yccheok.jstock.gui.Constants;
import org.yccheok.jstock.gui.JStock;
import org.yccheok.jstock.gui.JStockOptions;

import au.com.bytecode.opencsv.CSVReader;

import com.google.gson.Gson;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.02 06/17/2019
 */
public class Utils {
    
    public static interface GetStocksCallback {
        public void update(List<Stock> stocks);
    }
    
    /** Creates a new instance of Utils */
    private Utils() {
    }
    
    public static boolean essentiallyEqual(double a, double b)
    {
       if (Math.abs(a - b) < EPSILON)
          return true;
       else
          return false;  
    }
    
    public static org.yccheok.jstock.engine.YahooFinanceApi getYahooFinanceApi()
    {
       /* Works
       Proxy proxy;
       okhttp3.OkHttpClient client;
       retrofit2.Retrofit.Builder builder;
       JStockOptions jStockOptions;
       String proxyServer;
       int proxyPort;
       retrofit2.Retrofit retrofit;
       YahooFinanceApi yahooFinanceApi;
       
       builder = new retrofit2.Retrofit.Builder();
       builder.baseUrl(org.yccheok.jstock.network.Utils.getURL(
          org.yccheok.jstock.network.Utils.Type.YAHOO_FINANCE_API));
       builder.addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create());
       
       jStockOptions = JStock.instance().getJStockOptions();
       proxyServer = jStockOptions.getProxyServer();
       proxyPort = jStockOptions.getProxyPort();
       
       if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(proxyServer)
           && isValidPortNumber(proxyPort))
       {
          proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyServer, proxyPort));
          client = new okhttp3.OkHttpClient.Builder().proxy(proxy).build();
          builder.client(client);
       }
       
       retrofit = builder.build();
       yahooFinanceApi = retrofit.create(YahooFinanceApi.class);
       */
       
       YahooFinanceApi yahooFinanceApi;
       yahooFinanceApi = (getRetroFit(
          org.yccheok.jstock.network.Utils.Type.YAHOO_FINANCE_API)).create(YahooFinanceApi.class);
       return yahooFinanceApi;
    }
    
    // Does not use proxy, routed to getRetroFit() which does.
    public static org.yccheok.jstock.engine.YahooFinanceApiV8 getYahooFinanceChartApiV8()
    {
       /* Works, but no proxy.
       okhttp3.OkHttpClient.Builder builder;
       retrofit2.Retrofit retrofit;
       YahooFinanceApiV8 yahooFinanceApiV8;
       
       builder = new okhttp3.OkHttpClient.Builder();
       retrofit = (new retrofit2.Retrofit.Builder().baseUrl(org.yccheok.jstock.network.Utils.getURL(
          org.yccheok.jstock.network.Utils.Type.YAHOO_FINANCE_CHART_API_V8)).addConverterFactory(
             retrofit2.converter.gson.GsonConverterFactory.create())).build();
       yahooFinanceApiV8 = retrofit.create(YahooFinanceApiV8.class);
       */
       
       YahooFinanceApiV8 yahooFinanceApiV8;
       yahooFinanceApiV8 = (getRetroFit(
          org.yccheok.jstock.network.Utils.Type.YAHOO_FINANCE_CHART_API_V8)).create(YahooFinanceApiV8.class);
       return yahooFinanceApiV8;
    }
    
    public static org.yccheok.jstock.engine.IEXApi getIEXApi()
    {
       
       okhttp3.OkHttpClient.Builder builder;
       retrofit2.Retrofit retrofit;
       IEXApi iexApi;
       
       builder = new okhttp3.OkHttpClient.Builder();
       retrofit = (new retrofit2.Retrofit.Builder().baseUrl(org.yccheok.jstock.network.Utils.getURL(
          org.yccheok.jstock.network.Utils.Type.IEX_API)).addConverterFactory(
             retrofit2.converter.gson.GsonConverterFactory.create())).build();
       iexApi = retrofit.create(IEXApi.class);
       
       //IEXApi iexApi;
       //iexApi = (getRetroFit(org.yccheok.jstock.network.Utils.Type.IEX_API)).create(IEXApi.class);
       return iexApi;
    }
    
    // Uses proxy.
    private static retrofit2.Retrofit getRetroFit(org.yccheok.jstock.network.Utils.Type type)
    {
       Proxy proxy;
       okhttp3.OkHttpClient client;
       retrofit2.Retrofit.Builder builder;
       JStockOptions jStockOptions;
       String proxyServer;
       int proxyPort;
       retrofit2.Retrofit retrofit;
       
       builder = new retrofit2.Retrofit.Builder();
       builder.baseUrl(org.yccheok.jstock.network.Utils.getURL(type));
       builder.addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create());
       
       jStockOptions = JStock.instance().getJStockOptions();
       proxyServer = jStockOptions.getProxyServer();
       proxyPort = jStockOptions.getProxyPort();
       
       if (!org.yccheok.jstock.gui.Utils.isNullOrEmpty(proxyServer)
           && isValidPortNumber(proxyPort))
       {
          proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyServer, proxyPort));
          client = new okhttp3.OkHttpClient.Builder().proxy(proxy).build();
          builder.client(client);
       }
       
       retrofit = builder.build();
       return retrofit;
    }
    
    /**
     * Returns empty stock based on given stock info.
     *
     * @param stockInfo the stock info
     * @return empty stock based on given stock info
     */
    public static Stock getEmptyStock(StockInfo stockInfo) {
        return getEmptyStock(stockInfo.code, stockInfo.symbol);
    }

    /**
     * Returns empty stock based on given code and symbol.
     *
     * @param code the code
     * @param symbol the symbol
     * @return empty stock based on given code and symbol
     */
    public static Stock getEmptyStock(Code code, Symbol symbol) {
        return new Stock(   code,
                            symbol,
                            "",
                            null,
                            Board.Unknown,
                            Industry.Unknown,
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            0.0,
                            0,
                            0.0,
                            0.0,
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
                            System.currentTimeMillis()                              
                            );                
    } 
    
    public static Stock getStock(Code code) {
        List<Code> codes = new ArrayList<>();
        codes.add(code);
        List<Stock> stocks = getStocks(codes, new GetStocksCallback() {
            @Override
            public void update(List<Stock> stocks) {
            }            
        });
        if (stocks.size() == 1) {
            return stocks.get(0);
        }
        return null;
    }
    
    private static List<Code> getZeroPriceCodes(List<Stock> stocks, List<Stock> nonZeroPriceStocks, Set<Code> nonZeroPriceCodes) {
        assert(stocks != null);
        assert(nonZeroPriceStocks != null);
        assert(nonZeroPriceCodes != null);
        
        List<Code> zeroPriceCodes = null;
        
        for (Stock stock : stocks) {
            if (nonZeroPriceCodes.contains(stock.code)) {
                continue;
            }
                
            if (stock.getLastPrice() == 0.0 && stock.getOpenPrice() == 0.0) {
                if (zeroPriceCodes == null) {
                    zeroPriceCodes = new ArrayList<>();
                }
                
                zeroPriceCodes.add(stock.code);
            } else {
                nonZeroPriceCodes.add(stock.code);
                nonZeroPriceStocks.add(stock);
            }
        }
        
        if (zeroPriceCodes == null) {
            return java.util.Collections.emptyList();
        }
        
        return zeroPriceCodes;
    }

    public static List<Stock> getStocks(List<Code> codes, GetStocksCallback getStockCallback) {
        final List<Stock> s = new ArrayList<>();
        final Set<Code> nonZeroPriceCodes = new HashSet<>();        
        final CodeBucketLists codeBucketLists = new CodeBucketLists(Constants.REAL_TIME_STOCK_MONITOR_MAX_STOCK_SIZE_PER_SCAN);
        
        for (Code code : codes) {
            codeBucketLists.add(code);
        }
        
        final int size = codeBucketLists.size();
        for (int i = 0; i < size; i++) {
            List<Code> _codes = codeBucketLists.get(i);
            
            List<Code> zeroPriceCodes = _codes;
            final List<Stock> stocks = new ArrayList<>();
            
            for (StockServerFactory factory : Factories.INSTANCE.getStockServerFactories(_codes.get(0)))
            {
                final StockServer stockServer = factory.getStockServer();

                if (stockServer == null) {
                    continue;
                }
                
                List<Stock> tmpStocks = null;
                //try {
                    tmpStocks = stockServer.getStocks(zeroPriceCodes);
                //} catch (StockNotFoundException exp) {
                //    log.error("" + zeroPriceCodes, exp);
                //    // Try with another server.
                //    continue;
                //}

                zeroPriceCodes = getZeroPriceCodes(tmpStocks, stocks, nonZeroPriceCodes);
                if (zeroPriceCodes.isEmpty()) {
                    break;
                }
            }   /* for (StockServerFactory factory : Factories.INSTANCE.getStockServerFactories(codes.get(0))) */
            
            // Avoid multiple empty stocks callback.
            if (!stocks.isEmpty()) {
                getStockCallback.update(stocks);
                s.addAll(stocks);
            }
        }
        
        // Even the final result is empty, ensure we trigger callback at least
        // once.
        if (s.isEmpty()) {
            getStockCallback.update(s);
        }
        
        return s;
    }

    public static Country toCountry(Code code) {
        //assert(countries.keySet().size() == 46);
        
        String string = code.toString();
        int index = string.lastIndexOf(".");
        if (index == -1)
        {
            if (isYahooIndexSubset(code) || isGoogleIndexSubset(code))
            {
                // Fix to convert to Yahoo code, which appears
                // Index countries, code are stored.
                if (isGoogleIndexSubset(code) && toGoogleIndex.containsValue(string))
                {  
                   Set<Entry<String, String>> googleIndex = toGoogleIndex.entrySet();
                   Iterator<Entry<String, String>> iterator = googleIndex.iterator();
        
                   while (iterator.hasNext())
                   {
                      Entry<String, String> entry = iterator.next();
                      String key = entry.getKey();
                      String value = entry.getValue();
                      
                      if (value.equals(string))
                      {
                         string = key;
                         break;
                      }
                   }
                }
                
                Country country = indices.get(string.toUpperCase());
                if (country == null) {
                    return Country.UnitedState;
                }
                return country;
            }
            
            return Country.UnitedState;
        }
        String key = string.substring(index + 1, string.length());
        Country country = countries.get(key.toUpperCase());
        if (country == null) {
            return Country.UnitedState;
        }
        return country;
    }
    
    /**
     * Generate the best online database result if possible so that it is
     * acceptable by JStock application.
     *
     * @param result result from online database
     * @return best result after rectified. null if result cannot be rectified
     */
    public static ResultType rectifyResult(ResultType result) {
        String symbolStr = result.symbol;
        String nameStr = result.name;
        if (symbolStr == null) {
            return null;
        }
        if (symbolStr.trim().isEmpty()) {
            return null;
        }
        symbolStr = symbolStr.trim().toUpperCase();
        if (nameStr == null) {
            // If name is not available, we will make it same as symbol.
            nameStr = symbolStr;
        }
        if (nameStr.trim().isEmpty()) {
            // If name is not available, we will make it same as symbol.
            nameStr = symbolStr;
        }
        nameStr = nameStr.trim();
        return result.deriveWithSymbol(symbolStr).deriveWithName(nameStr);
    }
    
    public static DispType rectifyDisp(DispType result)
    {
       String symbolStr;
       String nameStr;
       
       symbolStr = result.getDispCode();
       nameStr = result.getDispName();
       
       if (symbolStr == null)
          return null;
       
       if (symbolStr.trim().isEmpty())
          return null;
       
       symbolStr = symbolStr.trim().toUpperCase();
       
       if (nameStr == null)
          nameStr = symbolStr;
       
       if (nameStr.trim().isEmpty())
          nameStr = symbolStr;
       
       nameStr = nameStr.trim();
       
       return new ResultType(symbolStr, nameStr); 
    }

    /**
     * Initialize HttpClient with information from system properties.
     *
     * @param httpClient HttpClient to be initialized
     */
    public static void setHttpClientProxyFromSystemProperties(HttpClient httpClient) {
        final String httpproxyHost = System.getProperties().getProperty("http.proxyHost");
        final String httpproxyPort = System.getProperties().getProperty("http.proxyPort");
        
        if (null == httpproxyHost || null == httpproxyPort) {
            HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
            hostConfiguration.setProxyHost(null);
        }
        else {
            int port = -1;
            try {
                port = Integer.parseInt(httpproxyPort);
            }
            catch (NumberFormatException exp) {
            }     
            
            if (isValidPortNumber(port)) {
                HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
                hostConfiguration.setProxy(httpproxyHost, port);                
            }
            else {
                HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
                hostConfiguration.setProxyHost(null);
            }
        }
    } 
    
    // Refer to http://www.exampledepot.com/egs/java.util/CompDates.html
    public static long getDifferenceInDays(long timeInMillis0, long timeInMillis1) {
        long diffMillis = Math.abs(timeInMillis0 - timeInMillis1);
        // Get difference in days
        long diffDays = diffMillis/(24*60*60*1000);
        return diffDays;        
    }
    
    // Refer to http://www.exampledepot.com/egs/java.util/CompDates.html
    public static long getDifferenceInDays(Date date0, Date date1) {
        return getDifferenceInDays(date0.getTime(), date1.getTime());
    }

    // Refer to http://www.exampledepot.com/egs/java.util/CompDates.html
    public static long getDifferenceInDays(Calendar calendar0, Calendar calendar1) {
        return getDifferenceInDays(calendar0.getTimeInMillis(), calendar1.getTimeInMillis());
    }
    
    public static void resetCalendarTime(Calendar calendar) {
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int date = calendar.get(Calendar.DATE);
        calendar.set(year, month, date, 0, 0, 0);
        // Reset milli second as well.
        calendar.set(Calendar.MILLISECOND, 0);        
    }
    
    public static boolean isValidPortNumber(int portNumber) {
        return (portNumber >= 0) && (portNumber <= 65534);
    }
    
    public static boolean isValidPortNumber(String portNumber) {
        int port = -1;
        try {
            port = Integer.parseInt(portNumber);
        }
        catch(NumberFormatException exp) {
        }
        
        return isValidPortNumber(port);
    }
    
    public static String getStockInfoDatabaseFileDirectory(Country country)
    {
       return new StringBuilder(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                + country + File.separator + "database" + File.separator).toString();  
    }
    
    public static File getStockInfoDatabaseFile(Country country) {
        return new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database" + File.separator + "stock-info-database.csv");
    }
    
    /**
     * Gets the CSV file, which will be used to construct 
     * {@code StockCodeAndSymbolDatabase} object.
     *
     * @param country The country of the stock market
     * @return Location of the stocks CSV file.
     */
    public static String getStocksCSVZipFileLocation(Country country) {
        // Must use lower case, as Google App Engine only support URL in lower
        // case.
        return org.yccheok.jstock.network.Utils.getJStockStaticServer() + "stocks_information/" + country.toString().toLowerCase() + "/" + "stocks.zip";
    }

    /**
     * One of the shortcomings of JStock is that, it is very difficult to get a
     * complete list of available stocks in a market. Most stock servers do not
     * provide information on complete list of available stocks. We can overcome
     * this, by reading the stock list information from a CSV file.
     *
     * @param file The CSV file
     * @return List of stocks carried by the CSV file.
     */
    public static List<Stock> getStocksFromCSVFile(File file) {
        List<Stock> stocks = new ArrayList<>();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        CSVReader csvreader = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream,  Charset.forName("UTF-8"));
            csvreader = new CSVReader(inputStreamReader);
            final String[] types = csvreader.readNext();
            if (types == null) {
                // Fail. Returns empty stock list.
                return stocks;
            }
            int code_index = -1;
            int symbol_index = -1;
            // Name, board and industry information is optional.
            int name_index = -1;            
            int board_index = -1;
            int industry_index = -1;
            
            boolean success_index = false;
            // Search for the indecies for code, symbol and name.
            for (int index = 0; index < types.length; index++) {
                final String type = types[index];
                if (0 == type.compareToIgnoreCase("code")) {
                    code_index = index;
                } else if (0 == type.compareToIgnoreCase("symbol")) {
                    symbol_index = index;
                } else if (0 == type.compareToIgnoreCase("name")) {
                    name_index = index;
                } else if (0 == type.compareToIgnoreCase("board")) {
                    board_index = index;
                } else if (0 == type.compareToIgnoreCase("industry")) {
                    industry_index = index;
                }

                if (code_index != -1 && symbol_index != -1 && name_index != -1 && board_index != -1 && industry_index != -1) {
                    // All found. Early quit.
                    break;
                }
            }

            // Ignore board_index, as it is optional.
            success_index = (code_index != -1 && symbol_index != -1);

            // Are we having all the indecies?
            if (false == success_index) {
                // Nope. Returns empty stock list.
                return stocks;
            }

            String [] nextLine;
            while ((nextLine = csvreader.readNext()) != null) {
                // Shall we continue to ignore, or shall we just return early to
                // flag an error?
                if (nextLine.length != types.length) {
                    // Give a warning message.
                    log.error("Incorrect CSV format. There should be exactly " + types.length + " item(s)");
                    continue;
                }
                final String code = nextLine[code_index];
                final String symbol = nextLine[symbol_index];
                final String name = name_index == -1 ? "" : nextLine[name_index];
                final String _board = board_index == -1 ? "Unknown" : nextLine[board_index];
                final String _industry = industry_index == -1 ? "Unknown" : nextLine[industry_index];
                Board board;
                Industry industry;
                try {
                    board = Board.valueOf(_board);
                } catch (IllegalArgumentException exp) {
                    log.error(null, exp);
                    board = Board.Unknown;
                }
                try {
                    industry = Industry.valueOf(_industry);
                } catch (IllegalArgumentException exp) {
                    log.error(null, exp);
                    industry = Industry.Unknown;
                }
                
                final Stock stock = new Stock.Builder(Code.newInstance(code), Symbol.newInstance(symbol)).name(name).board(board).industry(industry).build();
                stocks.add(stock);
            }
        } catch (IOException ex) {
            log.error(null, ex);
        } finally {
            if (csvreader != null) {
                try {
                    csvreader.close();
                } catch (IOException ex) {
                    log.error(null, ex);
                }
            }
            org.yccheok.jstock.file.Utils.close(inputStreamReader);
            org.yccheok.jstock.file.Utils.close(fileInputStream);
        }
        return stocks;
    }
    
    public static Pair<StockInfoDatabase, StockNameDatabase> toStockDatabase(List<Stock> stocks, Country country) {
        assert(false == stocks.isEmpty());
        
        // Let's make our database since we get a list of good stocks.
        StockInfoDatabase tmp_stock_info_database = new StockInfoDatabase(stocks);
        
        // StockNameDatabase is an optional item.
        StockNameDatabase tmp_name_database = null;
        if (org.yccheok.jstock.engine.Utils.isNameImmutable(country)) {
            tmp_name_database = new StockNameDatabase(stocks);
        }
        
        return Pair.create(tmp_stock_info_database, tmp_name_database);
    }
    
    public static boolean migrateXMLToCSVDatabases(String srcBaseDirectory, String destBaseDirectory) {
        boolean result = true;
        for (Country country : Country.values()) {
            final File userDefinedDatabaseXMLFile = new File(srcBaseDirectory + country + File.separator + "database" + File.separator + "user-defined-database.xml");
            final File userDefinedDatabaseCSVFile = new File(destBaseDirectory + country + File.separator + "database" + File.separator + "user-defined-database.csv");
            
            final java.util.List<Pair<Code, Symbol>> pairs = org.yccheok.jstock.gui.Utils.fromXML(java.util.List.class, userDefinedDatabaseXMLFile);            
            if (pairs != null && !pairs.isEmpty()) {
                final Statements statements = Statements.newInstanceFromUserDefinedDatabase(pairs);
                boolean r = statements.saveAsCSVFile(userDefinedDatabaseCSVFile);
                if (r) {
                    userDefinedDatabaseXMLFile.delete();
                }  
                result = r & result;
            } else {
                userDefinedDatabaseXMLFile.delete();
            }

            // Delete these old XML files. We can re-generate new CSV from database.zip.
            new File(srcBaseDirectory + country + File.separator + "database" + File.separator + "stock-name-database.xml").delete();
            new File(destBaseDirectory + country + File.separator + "database" + File.separator + "stock-info-database.xml").delete();
            new File(destBaseDirectory + country + File.separator + "database" + File.separator + "stockcodeandsymboldatabase.xml").delete();
        }
        return result;
    }
    
    public static String toUniversalFormat(java.lang.String string)
    {
       if (string.endsWith(".NS") || string.endsWith(".BO"))
          return string.substring(0, string.length() - 1);
       else
          return string;
    }
    
    public static Code toYahooFormatCode(Code code)
    {
       return Code.newInstance(toYahooFormat(code));
    }
    
    // Needs finished Checked.
    public static java.lang.String toYahooFormat(Code code)
    {
       String codeString;
       
       codeString = code.toString();
       
       if (codeString.endsWith(".N"))
          return new StringBuilder(codeString + "S").toString();
       
       if (code.toString().endsWith(".B"))
       {
          if (Character.isDigit(codeString.charAt(0)))
          {
             if (isIEXUSClassShare(code))
                return codeString.replaceAll("\\.", "-");
             else
                return new StringBuilder(codeString + "O").toString();
          }
          else
             return new StringBuilder(codeString + "O").toString();
             
       }
       else
       {
          if (isIEXUSClassShare(code))
             return codeString.replaceAll("\\.", "-");
          
          if (codeString.endsWith("*"))
             codeString = codeString.substring(0, codeString.length() - 1);
             
         //return codeString.replaceAll("(\\S+-)(\\S?$)", "$1P$2");
         return codeString.replaceAll("(\\S+-)", "(\\S?$)");     
       }
    }
    
    // Needs finished, checked.
    private static boolean isIEXUSClassShare(Code code)
    {
       String codeString;
       int beginIndex;
       String replacedCodeSubString;
       String replacedCodeString;
       Boolean result;
       
       codeString = code.toString();
       beginIndex = codeString.length() - 2;
       
       if (beginIndex < 0)
          return false;
       
       replacedCodeString = codeString.substring(beginIndex).replaceAll("-", ".");
       
       if (replacedCodeString.equals(".A") || replacedCodeString.equals(".B")
           || replacedCodeString.equals(".C"))
       {
          replacedCodeString = codeString.substring(beginIndex).replaceAll("-", ".");
          result = iexUSClassShareCache.get(replacedCodeString);
          
          if (result == null)
             return false;
          
          result = IEXStockInfoSQLiteOpenHelper.INSTANCE.find(replacedCodeString);
          
          if (result == null)
             return false;
          
          if (iexUSClassShareCache.size() <= IEX_US_CLASS_SHARE_CACHE_OPTIMAL_SIZE)
             iexUSClassShareCache.put(replacedCodeString, result);
          
          return result.booleanValue(); 
       }
       else
          return false;
    }
    
    public static Code toIEXFormatCode(org.yccheok.jstock.engine.Code code)
    {
       return Code.newInstance(toIEXFormat(code));
    }
    
    // Needs finished, checked.
    public static java.lang.String toIEXFormat(org.yccheok.jstock.engine.Code code)
    {
       String originalCodeString;
       String replacedCodeString;
       
       originalCodeString = code.toString();
       replacedCodeString = originalCodeString.replaceAll("(\\S+-)P(\\S?$)", "$1$2");
       //replacedCodeString = originalCodeString.replaceAll("(\\S+-)P", "(\\S?$)");
       
       if (!originalCodeString.equals(replacedCodeString))
          return replacedCodeString;
       else
       {
          if (isIEXUSClassShare(code))
             return originalCodeString.replaceAll("-", ".");
          else
             return originalCodeString;
       }  
    }
    
    
    public static boolean needToResolveUnderlyingCode(Code code)
    {
       if (code.toString().endsWith(".B") && Character.isDigit(code.toString().charAt(0)))
          return true;
       else
          return false;
    }

    /**
     * Returns code in Google's format.
     * 
     * @param code the code
     * @return code in Google's format
     */
    public static String toGoogleFormat(Code code) {
        if (isYahooIndexSubset(code)) {
            return toGoogleIndex(code);
        } else if (isYahooCurrency(code)) {
            return toGoogleCurrency(code);
        }

        String string = code.toString().trim().toUpperCase();

        // WTF?! Handle case for RDS-B (Yahoo Finance) & RDS.B (Google Finance)
        if (toCountry(code) == Country.UnitedState) {
            String googleFormat = UnitedStatesGoogleFormatCodeLookup.INSTANCE.get(code);
            if (googleFormat != null) {
                return googleFormat;
            }
            return string.replaceAll("-", ".");
        }
        
        final int string_length = string.length();
        if (string_length > 3) {
            String suffix = string.substring(string_length-3, string_length);
            String prefix = twoLetterSuffixes.get(suffix);
            if (prefix != null) {
                return prefix + string.substring(0, string_length - 3);
            }
        } 
        
        if (string_length > 2) {
            String suffix = string.substring(string_length-2, string_length);
            String prefix = oneLetterSuffixes.get(suffix);
            if (prefix != null) {
                return prefix + string.substring(0, string_length - 2);
            }
        }

        if (string_length > ".NS".length() && string.endsWith(".NS")) {
            // Special case.

            // Resolving Yahoo server down for India NSE stock market. Note, we
            // do not support Bombay stock market at this moment, due to the
            // difficulty in converting "TATACHEM.BO" (Yahoo Finance) to 
            // "BOM:500770" (Google Finance)
            string = string.substring(0, string_length - ".NS".length());
            String googleFormat = toGoogleFormatThroughAutoComplete(string, "NSE");
            if (googleFormat != null) {
                return "NSE:" + googleFormat;
            }
        }

        return string;
    }
    
    private static boolean isGoogleIndexSubset(org.yccheok.jstock.engine.Code code)
    {
       String string;
       int index;
       
       string = code.toString();
       index = string.indexOf(":");
       
       if (index > 0 && index < (string.length() - 1))
          return true;
       else
          return false;
    }

    private static boolean isYahooIndexSubset(Code code) {
        return code.toString().startsWith("^");
    }
    
    public static boolean isUSStock(Code code) {
        return Utils.toCountry(code) == Country.UnitedState
               && !Utils.isYahooCurrency(code) && !Utils.isYahooIndexSubset(code)
               && !Utils.isGoogleIndexSubset(code);
    }
    
    public static boolean isYahooCurrency(Code code) {
        return code.toString().toUpperCase().endsWith("=X");
    }
    
    private static String toGoogleIndex(Code code) {
        String string = code.toString().trim().toUpperCase();
        String googleIndex = toGoogleIndex.get(string);
        if (googleIndex != null) {
            return googleIndex;
        }
        return string;
    }
    
    private static String toGoogleCurrency(Code code) {
        String string = code.toString().trim().toUpperCase();
        int index = string.indexOf("=X");
        if (index > 0) {
            return "CURRENCY:" + string.substring(0, index);
        }
        return string;
    }
    
    // FIXME : Make it private.
    public static String toGoogleFormatThroughAutoComplete(String code, String exchange) {
        final StringBuilder builder = new StringBuilder("https://www.google.com/finance/match?matchtype=matchall&q=");
        try {
            // Exception will be thrown from apache httpclient, if we do not
            // perform URL encoding.
            builder.append(java.net.URLEncoder.encode(code, "UTF-8"));

            final String location = builder.toString();
            final String _respond = org.yccheok.jstock.gui.Utils.getResponseBodyAsStringBasedOnProxyAuthOption(location);
            if (_respond == null) {
                return null;
            }
            final String respond = Utils.GoogleRespondToJSON(_respond);
            // Google returns "// [ { "id": ... } ]".
            // We need to turn them into "[ { "id": ... } ]".
            final List<Map> jsonArray = gson.fromJson(respond, List.class);
            
            if (jsonArray == null) {
                return null;
            }
            
            for (int i = 0, size = jsonArray.size(); i < size; i++) {
                final Map<String, String> jsonObject = jsonArray.get(i);
                if (jsonObject.containsKey("e") && jsonObject.get("e").equalsIgnoreCase(exchange)) {
                    if (jsonObject.containsKey("t")) {
                        return jsonObject.get("t");
                    }
                }
            }           
        } catch (UnsupportedEncodingException ex) {
            log.error(null, ex);
        } catch (Exception ex) {
            // Jackson library may cause runtime exception if there is error
            // in the JSON string.
            log.error(null, ex);
        }        
        return null;
    }
    
    /**
     * Returns code in non Yahoo! format, by stripping off ".KL" suffix.
     * 
     * @param code the code
     * @return code in non Yahoo! format, by stripping off ".KL" suffix.
     */
    public static Code toNonYahooFormat(Code code)
    {
        final String tmp = code.toString();
        final String TMP = tmp.toUpperCase();
        int endIndex = TMP.lastIndexOf(".KL");
        if (endIndex < 0) {
            return code;
        }
        return Code.newInstance(tmp.substring(0, endIndex));
    }
    
    /**
     * Returns best search engine based on current selected country.
     * 
     * @return Best search engine based on current selected country.
     */
    public static boolean isPinyinTSTSearchEngineRequiredForSymbol() {
        final Country country = JStock.instance().getJStockOptions().getCountry();
        return (country == Country.China || country == Country.Taiwan);
    }

    /**
     * Returns <code>true</code> if we should maintain the symbol as database's,
     * even the symbol provided by stock server is different from our database.
     * This happens when our symbol in database is Chinese, but the symbol
     * returned by stock server is in English.
     * 
     * @return <code>true</code> if we should maintain the symbol as database's.
     */
    public static boolean isSymbolImmutable() {
        final Country country = JStock.instance().getJStockOptions().getCountry();
        return (country == Country.China || country == Country.Taiwan);
    }

    /**
     * Returns <code>true</code> if we should maintain the name as database's,
     * even the name provided by stock server is different from our database.
     * This happens when our name in database is Chinese, but the name returned
     * by stock server is in English.
     *
     * @return <code>true</code> if we should maintain the name as database's.
     */
    public static boolean isNameImmutable() {
        final Country country = JStock.instance().getJStockOptions().getCountry();
        return isNameImmutable(country);
    }

    private static boolean isNameImmutable(Country country) {
        return (country == Country.China || country == Country.Taiwan);
    }
    
    /**
     * Returns <code>true</code> if we need to use red color to indicate "rise
     * above". Green color to indicate "fall below".
     * 
     * @return <code>true</code> if we need to use red color to indicate "rise
     * above". Green color to indicate "fall below".
     */
    public static boolean isFallBelowAndRiseAboveColorReverse() {
        final Country country = JStock.instance().getJStockOptions().getCountry();
        return (country == Country.China || country == Country.Taiwan);
    }

    public static List<Index> getStockIndices(Country country) {
        List<Index> indices = country2Indices.get(country);
        if (indices != null) {
            return java.util.Collections.unmodifiableList(indices);
        }
        return java.util.Collections.emptyList();
    }

    /**
     * Returns JSON string, by parsing respond from Google server.
     *
     * @param respond string returned from Google server directly
     * @return JSON string, by parsing respond from Google server
     */
    public static String GoogleRespondToJSON(String respond) {
        final int beginIndex = respond.indexOf("[");
        final int endIndex = respond.lastIndexOf("]");
        if (beginIndex < 0) {
            return "";
        }
        if (beginIndex > endIndex) {
            return "";
        }
        String string = respond.substring(beginIndex, endIndex + 1);
        // http://stackoverflow.com/questions/6067673/urldecoder-illegal-hex-characters-in-escape-pattern-for-input-string
        string = string.replaceAll("%", "%25");
        
        // http://stackoverflow.com/questions/15518340/json-returned-by-google-maps-query-contains-encoded-characters-like-x26-how-to
        // JSON returned by Google Maps Query contains encoded characters like \x26 (how to decode?)
        try {
            string = URLDecoder.decode(string.replace("\\x", "%"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            log.error(null, ex);
        }
        
        return string;
    }

    /**
     * Returns JSON string, by parsing respond from Yahoo server.
     *
     * @param respond string returned from Yahoo server directly
     * @return JSON string, by parsing respond from Yahoo server
     */
    public static String YahooRespondToJSON(String respond) {
        final int beginIndex = respond.indexOf("{");
        final int endIndex = respond.lastIndexOf("}");
        if (beginIndex < 0) {
            return "";
        }
        if (beginIndex > endIndex) {
            return "";
        }
        return respond.substring(beginIndex, endIndex + 1);
    }

    /**
     * Returns a new double initialized to the value represented by the
     * specified String, as performed by the valueOf method of class Double.
     * If failed, 0.0 will be returned.
     *
     * @return the double value represented by the string argument.
     */
    public static double parseDouble(String value) {
        if (value == null) {
            // This is an invalid value.
            return 0.0;
        }

        try {
            // Use String.replace, in order to turn "1,234,567%" into "1234567".
            return Double.parseDouble(value.replace(",", "").replace("%", ""));
        } catch (NumberFormatException ex) {
            log.error(null, ex);
        }
        // This is an invalid value.
        return 0.0;
    }

    /**
     * Returns a new long initialized to the value represented by the
     * specified String, as performed by the valueOf method of class Long.
     * If failed, 0L will be returned.
     *
     * @return the long value represented by the string argument.
     */
    public static long parseLong(String value) {
        if (value == null) {
            // This is an invalid value.
            return 0L;
        }
        
        try {
            // Use String.replace, in order to turn "1,234,567%" into "1234567".
            return Long.parseLong(value.replace(",", "").replace("%", ""));
        } catch (NumberFormatException ex) {
            log.error(null, ex);
        }
        // This is an invalid value.
        return 0L;
    }
    
    public static int getGoogleUnitedStatesStockExchangePriority(String e) {
        Integer priority = googleUnitedStatesStockExchanges.get(e);
        if (priority == null) {
            return Integer.MAX_VALUE;
        }
        return priority;
    }
    
    public static String toCompleteUnitedStatesGoogleFormat(Code code) {
        if (false == Utils.isUSStock(code)) {
            return null;
        }
        
        // Use toGoogleFormat, as it will handle case for RDS-B (Yahoo Finance) 
        // & RDS.B (Google Finance)
        final String googleFormat = toGoogleFormat(code);
        if (googleFormat.contains(":")) {
            return googleFormat;
        }
        
        String[] exchanges = {"NYSE:", "NASDAQ:", "NYSEARCA:", "NYSEMKT:", "OPRA:", "OTCBB:", "OTCMKTS:"};
        final StringBuilder builder = new StringBuilder("https://www.google.com/finance/info?infotype=infoquoteall&q=");
        
        try {
            
            builder.append(java.net.URLEncoder.encode(exchanges[0] + code, "UTF-8"));
            for (int i = 1, ei = exchanges.length; i < ei; i++) {
                builder.append(",");
                builder.append(java.net.URLEncoder.encode(exchanges[i] + code, "UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
            log.error(null, ex);
            return null;
        } 
        
        final String location = builder.toString();
        final String _respond = org.yccheok.jstock.gui.Utils.getResponseBodyAsStringBasedOnProxyAuthOption(location);
        if (_respond == null) {
            return null;
        }

        final String respond = Utils.GoogleRespondToJSON(_respond);
        // Google returns "// [ { "id": ... } ]".
        // We need to turn them into "[ { "id": ... } ]".
        final List<Map> jsonArray = gson.fromJson(respond, List.class);

        if (jsonArray == null) {
            return null;
        }
        
        List<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();
        
        for (Map<String, String> jsonObject : jsonArray) {
            try {
                String ticker = jsonObject.get("t").toUpperCase();
                String exchange = jsonObject.get("e").toUpperCase();
                pairs.add(Pair.create(ticker, exchange));
            } catch (Exception ex) {
                log.error(null, ex);
            }
        }
        
        if (pairs.isEmpty()) {
            return null;
        }
        
        Collections.sort(pairs, new Comparator<Pair<String, String>>() {

            @Override
            public int compare(Pair<String, String> o0, Pair<String, String> o1) {
                String e0 = o0.second;
                String e1 = o1.second;                
                return Integer.compare(getGoogleUnitedStatesStockExchangePriority(e0), getGoogleUnitedStatesStockExchangePriority(e1));
            }            
        });
        
        Pair<String, String> pair = pairs.get(0);
        final String result = pair.second + ":" + pair.first;
        UnitedStatesGoogleFormatCodeLookup.INSTANCE.put(code, result);
        
        return result; 
    }
    
    // https://www.google.com/intl/en/googlefinance/disclaimer/
    public static boolean isGoogleUnitedStatesStockExchange(String e) {
        return googleUnitedStatesStockExchanges.containsKey(e);
    }
    
    public static PriceSource getDefaultPriceSource(Country country) {
        //assert(defaultPriceSources.containsKey(country));
        if (defaultPriceSources.containsKey(country))
           return defaultPriceSources.get(country);
        else
           return PriceSource.Yahoo;
    }
    
    public static Set<PriceSource> getSupportedPriceSources(Country country) {
        List<StockServerFactory> stockServerFactories = Factories.INSTANCE.getStockServerFactories(country);
        Set<PriceSource> set = EnumSet.noneOf(PriceSource.class);
        for (StockServerFactory stockServerFactory : stockServerFactories) {
            PriceSource priceSource = classToPriceSourceMap.get(stockServerFactory.getClass());
            if (priceSource != null) {
                set.add(priceSource);
            }
        }
        return set;
    }
    
    //public static java.lang.String toYahooSuffix(java.lang.String googleExch);
    //public static java.util.List getGoogleSupportedExchs();
    
    public static void clearGoogleCodeDatabaseCache()
    {
       googleCodeDatabaseCache.clear();
    }
    
    public static void clearAllIEXStockInfoDatabaseCaches()
    {
       iexUSClassShareCache.clear();
    }
    
    // Needs finished, checked.
    public static TimeZone getTimeZone(Country country)
    {
       TimeZone timeZone;
       Set<String> set;
       TimeZone betterTimeZone;
       
       betterTimeZone = betterLocalTimeZones.get(country);
       
       assert (betterTimeZone != null);
       
       if (betterTimeZone.getRawOffset() == 0L)
          return betterTimeZone;
       else
       {
          timeZone = localTimeZones.get(country);
          
          if (betterTimeZone.getRawOffset() == timeZone.getRawOffset())
             return betterTimeZone;
          
          set = new HashSet<String>(Arrays.asList(TimeZone.getAvailableIDs()));
          
          if (set.contains(timeZone.getID()))
             return timeZone;
          else
             return betterTimeZone;
       }
    }
    
    public static Period toBestPeriod(Duration duration, TimeZone timeZone)
    {
       Calendar calendar;
       long startTimestamp;
       Period period;
       Period[] periods;
       
       if (timeZone != null)
          startTimestamp = duration.getStartDate().getCalendar(timeZone).getTimeInMillis();
       else
          startTimestamp = duration.getStartDate().getCalendar().getTimeInMillis();
       
       periods = Period.values();
       
       for (int i = 0; i < periods.length; i++)
       {
          period = periods[i];
          
          if (timeZone != null)
             calendar = Calendar.getInstance(timeZone);
          else
             calendar = Calendar.getInstance();
          
          calendar.add(period.calendar, -period.count);
          resetCalendarTime(calendar);
          
          // period.time < duration.start
          if (calendar.getTimeInMillis() <= startTimestamp)
          {
             if (i != (periods.length - 1))
                return periods[i + 1];
             else
                return period;  
          }
       }
       return Period.Years10;
    }
    
    // Class Instances
    private static final Map<Country, List<Index>> country2Indices = new EnumMap<Country, List<Index>>(Country.class);
    private static final Map<String, Country> countries = new HashMap<>();
    private static final Map<String, Country> indices = new HashMap<>();
    private static final Map<String, String> toGoogleIndex = new HashMap<>();
    private static final Map<Country, PriceSource> defaultPriceSources = new EnumMap<>(Country.class);
    private static final Map<Class<? extends StockServerFactory>, PriceSource> classToPriceSourceMap = new HashMap<>();
    private static final Map<String, Integer> googleUnitedStatesStockExchanges = new HashMap<>();
    
    private static final Map<String, String> oneLetterSuffixes = new HashMap<>();
    private static final Map<String, String> twoLetterSuffixes = new HashMap<>();
    
    private static final Gson gson = new Gson();
    private static final Log log = LogFactory.getLog(Utils.class);
    
    // New Instances after 1.0.7.9 sometime and in 1.0.7.37
    private static final double EPSILON = 1.0E-8;
    private static final int IEX_US_CLASS_SHARE_CACHE_OPTIMAL_SIZE = 512;
    private static final int GOOGLE_CODE_DATABASE_CACHE_OPTIMAL_SIZE = 128;
    
    private static final List<String> googleSupportedExchs = new ArrayList<>();
    private static final Map<String, String> googleExchToYahooSuffix = new HashMap<>();
    private static final Map<String, Boolean> iexUSClassShareCache = new ConcurrentHashMap<>();
    private static final Map<String, String> googleCodeDatabaseCache = new ConcurrentHashMap<>();
    
    private static final java.util.Map<Country, TimeZone> betterLocalTimeZones = new EnumMap<>(Country.class);
    private static final java.util.Map<Country, TimeZone> localTimeZones = new EnumMap<>(Country.class);
    
    // Intialize
    static {  
        for (Index index : Index.values()) {
          List<Index> indices = country2Indices.get(index.country);
          if (indices == null) {
              indices = new ArrayList<Index>();
              country2Indices.put(index.country, indices);
          }
          indices.add(index);
        }
        
        for (Index index : Index.values()) {
          indices.put(index.code.toString(), index.country);
        }
        
        oneLetterSuffixes.put(".F", "FRA:");
        oneLetterSuffixes.put(".N", "NSE:");
        oneLetterSuffixes.put(".B", "BOM:");
        oneLetterSuffixes.put(".L", "LON:");
        oneLetterSuffixes.put(".T", "TYO:");
        oneLetterSuffixes.put(".V", "CVE:");
        oneLetterSuffixes.put(".S", "TADAWUL:");

        twoLetterSuffixes.put(".DE", "ETR:");
        twoLetterSuffixes.put(".SS", "SHA:");
        twoLetterSuffixes.put(".SZ", "SHE:");
        twoLetterSuffixes.put(".SA", "BVMF:");
        twoLetterSuffixes.put(".VI", "VIE:");
        twoLetterSuffixes.put(".SI", "SGX:");
        twoLetterSuffixes.put(".TW", "TPE:");
        twoLetterSuffixes.put(".NZ", "NZE:");
        twoLetterSuffixes.put(".ST", "STO:");
        twoLetterSuffixes.put(".AX", "ASX:");
        twoLetterSuffixes.put(".BR", "EBR:");
        twoLetterSuffixes.put(".AS", "AMS:");
        twoLetterSuffixes.put(".CO", "CPH:");
        twoLetterSuffixes.put(".HK", "HKG:");
        twoLetterSuffixes.put(".CN", "CNSX:");
        twoLetterSuffixes.put(".TO", "TSE:");
        twoLetterSuffixes.put(".PA", "EPA:");
        twoLetterSuffixes.put(".TA", "TLV:");
        twoLetterSuffixes.put(".MI", "BIT:");
        twoLetterSuffixes.put(".KQ", "KOSDAQ:");
        twoLetterSuffixes.put(".KX", "KRX:");
        twoLetterSuffixes.put(".LS", "ELI:");
        twoLetterSuffixes.put(".BA", "BCBA:");
        twoLetterSuffixes.put(".HE", "HEL:");
        twoLetterSuffixes.put(".ME", "MCX:");
        twoLetterSuffixes.put(".ZA", "JSE:");
        twoLetterSuffixes.put(".TH", "BKK:");
        twoLetterSuffixes.put(".TR", "IST:");
        twoLetterSuffixes.put(".ES", "BME:");
        twoLetterSuffixes.put(".PL", "WSE:");
        twoLetterSuffixes.put(".JK", "IDX:");
        twoLetterSuffixes.put(".SW", "SWX:");
        twoLetterSuffixes.put(".VX", "VTX:");
        
        // key matches standard?
        countries.put("BA", Country.Argentina);
        countries.put("AX", Country.Australia);
        countries.put("VI", Country.Austria);
        countries.put("BR", Country.Belgium);
        countries.put("SA", Country.Brazil);
        countries.put("CN", Country.Canada);
        countries.put("TO", Country.Canada);
        countries.put("V", Country.Canada); // TSXV
        
        countries.put("SS", Country.China);
        countries.put("SZ", Country.China);
        
        countries.put("CO", Country.Denmark);
        countries.put("PA", Country.France);

        countries.put("HE", Country.Finland);
        countries.put("BE", Country.Germany);
        countries.put("DE", Country.Germany);
        countries.put("DU", Country.Germany);
        countries.put("EX", Country.Germany);
        countries.put("F", Country.Germany);
        countries.put("HA", Country.Germany);
        countries.put("HM", Country.Germany);
        countries.put("MU", Country.Germany);
        countries.put("SG", Country.Germany);
        
        countries.put("HK", Country.HongKong);
        
        countries.put("NS", Country.India);
        countries.put("N", Country.India);
        countries.put("B", Country.India);
        
        countries.put("JK", Country.Indonesia);
        countries.put("TA", Country.Israel);
        countries.put("MI", Country.Italy);
        countries.put("T", Country.Japan);
        countries.put("KQ", Country.Korea);
        countries.put("KS", Country.Korea);
        countries.put("KL", Country.Malaysia);
        countries.put("AS", Country.Netherlands);
        countries.put("NZ", Country.NewZealand);
        countries.put("OL", Country.Norway);
        countries.put("PL", Country.Poland);
        countries.put("LS", Country.Portugal);
        countries.put("ME", Country.Russia);
        countries.put("S", Country.SaudiArabia);
        countries.put("SI", Country.Singapore);
        countries.put("ZA", Country.SouthAfrica);
        
        countries.put("ES", Country.Spain);
        /*
        countries.put("BI", Country.Spain);
        countries.put("BC", Country.Spain);
        countries.put("MA", Country.Spain);
        countries.put("MC", Country.Spain);
        countries.put("VA", Country.Spain);
        */
        countries.put("ST", Country.Sweden);

        countries.put("SW", Country.Switzerland);
        countries.put("VX", Country.Switzerland);
        
        countries.put("TW", Country.Taiwan);
        countries.put("TWO", Country.Taiwan);
        countries.put("TH", Country.Thailand);
        countries.put("TR", Country.Turkey);
        
        countries.put("L", Country.UnitedKingdom);
        
        toGoogleIndex.put("^DJI", "INDEXDJX:.DJI");
        toGoogleIndex.put("^IXIC", "INDEXNASDAQ:.IXIC");
        toGoogleIndex.put("^GSPC", "INDEXSP:.INX");
        toGoogleIndex.put("^BSESN", "INDEXBOM:SENSEX");
        toGoogleIndex.put("^NSEI", "NSE:NIFTY");
        toGoogleIndex.put("^NSEBANK", "NSE:BANKNIFTY");
        toGoogleIndex.put("^BVSP", "INDEXBVMF:IBOV");
        toGoogleIndex.put("^ATX", "INDEXVIE:ATX");
        toGoogleIndex.put("^FTSE", "INDEXFTSE:UKX");
        toGoogleIndex.put("^TWII", "TPE:TAIEX");
        toGoogleIndex.put("^NZ50", "NZE:NZ50G");
        toGoogleIndex.put("^OMX", "INDEXNASDAQ:OMXS30");
        toGoogleIndex.put("^AXJO", "INDEXASX:XJO");
        toGoogleIndex.put("^AORD", "INDEXASX:XAO");
        toGoogleIndex.put("^BFX", "INDEXEURO:BEL20");
        toGoogleIndex.put("^AEX", "INDEXEURO:AEX");
        toGoogleIndex.put("^OMXC20", "INDEXNASDAQ:OMXC20");
        toGoogleIndex.put("^N225", "INDEXNIKKEI:NI225");
        toGoogleIndex.put("^HSI", "INDEXHANGSENG:HSI");
        toGoogleIndex.put("^GSPTSE", "INDEXTSI:OSPTX");
        toGoogleIndex.put("^FCHI", "INDEXEURO:PX1");
        toGoogleIndex.put("^TA100", "TLV:TA100");
        toGoogleIndex.put("FTSEMIB.MI", "INDEXFTSE:FTSEMIB");
        toGoogleIndex.put("^KS11", "KRX:KOSPI");
        toGoogleIndex.put("PSI20.LS", "INDEXEURO:PSI20");
        toGoogleIndex.put("^MERV", "BCBA:IMV");
        toGoogleIndex.put("^OMXH25", "INDEXNASDAQ:OMXH25");
        toGoogleIndex.put("^MICEX", "MCX:MICEXINDEXCF");
        toGoogleIndex.put("^TASI", "TADAWUL:TASI");
        toGoogleIndex.put("^SET", "INDEXBKK:SET");
        toGoogleIndex.put("^XU100", "INDEXIST:XU100");
        toGoogleIndex.put("^IBEX", "INDEXBME:IB");
        toGoogleIndex.put("^JKSE", "IDX:COMPOSITE");
        toGoogleIndex.put("^SSMI", "INDEXSWX:SMI");
        
        // TODO : Need revision. We no longer have primaryStockServerFactoryClasses
        // concept. Going to replace with PriceSource.
        defaultPriceSources.put(Country.Argentina, PriceSource.Google);
        defaultPriceSources.put(Country.Australia, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Austria, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Belgium, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Brazil, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Canada, PriceSource.Yahoo);
        defaultPriceSources.put(Country.China, PriceSource.Google);
        defaultPriceSources.put(Country.Czech, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Denmark, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Finland, PriceSource.Google);
        defaultPriceSources.put(Country.France, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Germany, PriceSource.Yahoo);
        defaultPriceSources.put(Country.HongKong, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Hungary, PriceSource.Yahoo);
        defaultPriceSources.put(Country.India, PriceSource.Google);
        defaultPriceSources.put(Country.Indonesia, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Israel, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Italy, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Japan, PriceSource.Google);
        defaultPriceSources.put(Country.Korea, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Malaysia, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Netherlands, PriceSource.Yahoo);
        defaultPriceSources.put(Country.NewZealand, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Norway, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Poland, PriceSource.Google);
        defaultPriceSources.put(Country.Portugal, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Russia, PriceSource.Google);
        defaultPriceSources.put(Country.SaudiArabia, PriceSource.Google);
        defaultPriceSources.put(Country.Singapore, PriceSource.Google);
        defaultPriceSources.put(Country.SouthAfrica, PriceSource.Google);
        defaultPriceSources.put(Country.Spain, PriceSource.Google);
        defaultPriceSources.put(Country.Sweden, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Switzerland, PriceSource.Yahoo);
        defaultPriceSources.put(Country.Taiwan, PriceSource.Google);
        defaultPriceSources.put(Country.Thailand, PriceSource.Google);
        defaultPriceSources.put(Country.Turkey, PriceSource.Google);
        defaultPriceSources.put(Country.UnitedKingdom, PriceSource.Google);
        defaultPriceSources.put(Country.UnitedState, PriceSource.Google);
        
        classToPriceSourceMap.put(GoogleStockServerFactory.class, PriceSource.Google);
        classToPriceSourceMap.put(YahooStockServerFactory.class, PriceSource.Yahoo);
        classToPriceSourceMap.put(BrazilYahooStockServerFactory.class, PriceSource.Yahoo);
        classToPriceSourceMap.put(IEXStockServerFactory.class, PriceSource.IEX);
        
        googleUnitedStatesStockExchanges.put("NYSE", 0);
        googleUnitedStatesStockExchanges.put("NASDAQ", 1);
        googleUnitedStatesStockExchanges.put("NYSEARCA", 2);
        googleUnitedStatesStockExchanges.put("NYSEMKT", 3);
        googleUnitedStatesStockExchanges.put("OPRA", 4);
        googleUnitedStatesStockExchanges.put("OTCBB", 5);
        googleUnitedStatesStockExchanges.put("OTCMKTS", 6);
        
        betterLocalTimeZones.put(Country.Argentina, TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
        betterLocalTimeZones.put(Country.Australia, TimeZone.getTimeZone("Australia/Canberra"));
        betterLocalTimeZones.put(Country.Austria, TimeZone.getTimeZone("Europe/Vienna"));
        betterLocalTimeZones.put(Country.Belgium, TimeZone.getTimeZone("Europe/Brussels"));
        betterLocalTimeZones.put(Country.Brazil, TimeZone.getTimeZone("America/Sao_Paulo"));
        betterLocalTimeZones.put(Country.Canada, TimeZone.getTimeZone("America/Montreal"));
        betterLocalTimeZones.put(Country.China, TimeZone.getTimeZone("Asia/Shanghai"));
        betterLocalTimeZones.put(Country.Denmark, TimeZone.getTimeZone("Europe/Copenhagen"));
        betterLocalTimeZones.put(Country.Finland, TimeZone.getTimeZone("Europe/Helsinki"));
        betterLocalTimeZones.put(Country.France, TimeZone.getTimeZone("Europe/Paris"));
        betterLocalTimeZones.put(Country.Germany, TimeZone.getTimeZone("Europe/Berlin"));
        betterLocalTimeZones.put(Country.HongKong, TimeZone.getTimeZone("Asia/Hong_Kong"));
        betterLocalTimeZones.put(Country.India, TimeZone.getTimeZone("Asia/Calcutta"));  
        betterLocalTimeZones.put(Country.Indonesia, TimeZone.getTimeZone("Asia/Jakarta"));
        betterLocalTimeZones.put(Country.Israel, TimeZone.getTimeZone("Asia/Jerusalem"));
        betterLocalTimeZones.put(Country.Italy, TimeZone.getTimeZone("Europe/Rome"));
        betterLocalTimeZones.put(Country.Japan, TimeZone.getTimeZone("Japan"));
        betterLocalTimeZones.put(Country.Korea, TimeZone.getTimeZone("Asia/Seoul"));
        betterLocalTimeZones.put(Country.Malaysia, TimeZone.getTimeZone("Asia/Kuala_Lumpur")); 
        betterLocalTimeZones.put(Country.Netherlands, TimeZone.getTimeZone("Europe/Amsterdam"));  
        betterLocalTimeZones.put(Country.NewZealand, TimeZone.getTimeZone("Pacific/Auckland"));  
        betterLocalTimeZones.put(Country.Norway, TimeZone.getTimeZone("Europe/Oslo")); 
        betterLocalTimeZones.put(Country.Poland, TimeZone.getTimeZone("Europe/Warsaw"));  
        betterLocalTimeZones.put(Country.Portugal, TimeZone.getTimeZone("Europe/Lisbon"));  
        betterLocalTimeZones.put(Country.Russia, TimeZone.getTimeZone("Europe/Moscow"));  
        betterLocalTimeZones.put(Country.SaudiArabia, TimeZone.getTimeZone("Asia/Riyadh")); 
        betterLocalTimeZones.put(Country.Singapore, TimeZone.getTimeZone("Asia/Singapore")); 
        betterLocalTimeZones.put(Country.SouthAfrica, TimeZone.getTimeZone("Africa/Johannesburg"));  
        betterLocalTimeZones.put(Country.Spain, TimeZone.getTimeZone("Europe/Madrid"));  
        betterLocalTimeZones.put(Country.Sweden, TimeZone.getTimeZone("Europe/Stockholm"));  
        betterLocalTimeZones.put(Country.Switzerland, TimeZone.getTimeZone("Europe/Zurich"));  
        betterLocalTimeZones.put(Country.Taiwan, TimeZone.getTimeZone("Asia/Taipei")); 
        betterLocalTimeZones.put(Country.Thailand, TimeZone.getTimeZone("Asia/Bangkok"));   
        betterLocalTimeZones.put(Country.Turkey, TimeZone.getTimeZone("Turkey"));   
        betterLocalTimeZones.put(Country.UnitedKingdom, TimeZone.getTimeZone("Europe/London"));  
        betterLocalTimeZones.put(Country.UnitedState, TimeZone.getTimeZone("America/New_York"));  

        localTimeZones.put(Country.Argentina, TimeZone.getTimeZone("GMT-3"));
        localTimeZones.put(Country.Australia, TimeZone.getTimeZone("GMT+10")); 
        localTimeZones.put(Country.Austria, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Belgium, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Brazil, TimeZone.getTimeZone("GMT-3"));
        localTimeZones.put(Country.Canada, TimeZone.getTimeZone("GMT-5"));
        localTimeZones.put(Country.China, TimeZone.getTimeZone("GMT+8"));
        localTimeZones.put(Country.Denmark, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Finland, TimeZone.getTimeZone("GMT+2"));
        localTimeZones.put(Country.France, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Germany, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.HongKong, TimeZone.getTimeZone("GMT+8"));
        localTimeZones.put(Country.India, TimeZone.getTimeZone("GMT+5:30"));
        localTimeZones.put(Country.Indonesia, TimeZone.getTimeZone("GMT+7"));
        localTimeZones.put(Country.Israel, TimeZone.getTimeZone("GMT+2"));
        localTimeZones.put(Country.Italy, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Japan, TimeZone.getTimeZone("GMT+9"));
        localTimeZones.put(Country.Korea, TimeZone.getTimeZone("GMT+9"));
        localTimeZones.put(Country.Malaysia, TimeZone.getTimeZone("GMT+8"));
        localTimeZones.put(Country.Netherlands, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.NewZealand, TimeZone.getTimeZone("GMT+12"));
        localTimeZones.put(Country.Norway, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Poland, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Portugal, TimeZone.getTimeZone("GMT+0"));
        localTimeZones.put(Country.Russia, TimeZone.getTimeZone("GMT+3"));
        localTimeZones.put(Country.SaudiArabia, TimeZone.getTimeZone("GMT+3"));
        localTimeZones.put(Country.Singapore, TimeZone.getTimeZone("GMT+8"));
        localTimeZones.put(Country.SouthAfrica, TimeZone.getTimeZone("GMT+2"));
        localTimeZones.put(Country.Spain, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Sweden, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Switzerland, TimeZone.getTimeZone("GMT+1"));
        localTimeZones.put(Country.Taiwan, TimeZone.getTimeZone("GMT+8"));
        localTimeZones.put(Country.Thailand, TimeZone.getTimeZone("GMT+7"));
        localTimeZones.put(Country.Turkey, TimeZone.getTimeZone("GMT+2"));
        localTimeZones.put(Country.UnitedKingdom, TimeZone.getTimeZone("GMT+0"));
        localTimeZones.put(Country.UnitedState, TimeZone.getTimeZone("GMT-5"));
        
        assert(betterLocalTimeZones.size() == localTimeZones.size());
        
        Set<Entry<String, String>> one = oneLetterSuffixes.entrySet();
        Iterator<Entry<String, String>> oneIterator = one.iterator();
        
        while (oneIterator.hasNext())
        {
           Entry<String, String> entry = oneIterator.next();
           String key = entry.getKey();
           String value = entry.getValue();
           
           assert(key.startsWith(".") == false);
           assert(value.endsWith(":") == false);
           
           key = key.substring(1);
           value = value.substring(0, value.length() - 1);
           
           //assert(googleExchToYahooSuffix.containsKey(value));
           //assert(googleExchToYahooSuffix.containsValue(key));
           //assert(googleSupportedExchs.contains(value));
           
           if (!googleExchToYahooSuffix.containsKey(key))
              googleExchToYahooSuffix.put(key, value);
           
           if (!googleSupportedExchs.contains(value))
              googleSupportedExchs.add(value);
        }
        
        Set<Entry<String, String>> two = twoLetterSuffixes.entrySet();
        Iterator<Entry<String, String>> twoIterator = two.iterator();
        
        while (twoIterator.hasNext())
        {
           Entry<String, String> entry = twoIterator.next();
           String key = entry.getKey();
           String value = entry.getValue();
           
           assert(key.startsWith(".") == false);
           assert(value.endsWith(":") == false && value.isEmpty());
           
           key = key.substring(1);
           value = value.substring(0, value.length() - 1);
           
           if (!googleExchToYahooSuffix.containsKey(key))
              googleExchToYahooSuffix.put(key, value);
           
           if (!googleSupportedExchs.contains(value))
              googleSupportedExchs.add(value);
        }
        
        Set<Entry<String, Integer>> googleExch = googleUnitedStatesStockExchanges.entrySet();
        Iterator<Entry<String, Integer>> exchIterator = googleExch.iterator();
        
        while (exchIterator.hasNext())
        {
           Entry<String, Integer> entry = exchIterator.next();
           String key = entry.getKey();
           
           if (!googleSupportedExchs.contains(key))
              googleSupportedExchs.add(key);
        }  
    }
}
