/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.04 04/28/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock Gui JStock Inner Class
//                                DatabaseTask.
//         1.0.7.37.01 03/16/2019 Moved Into Separate Class File. Added Arguments JStock,
//                                & Log to Constructor. Referenced Those Instances Throughout
//                                to Replace Local References in Inner Class to JStock.
//                                Formatted.
//         1.0.7.37.02 03/23/2019 Method doInBackground() Called the Utils.extractZipFile()
//                                Directly for Companies Stock Listing by Country Files
//                                Extraction Rather Than jstock.initPreloadDatabase(). Added
//                                Class Methods loadStockInfo/NameDatabaseFromCSV(). Added
//                                Method loadUserDefinedDatabaseFromCSV(). Added Class Instance
//                                ZIP_DATABASE_FILE_NAME.
//         1.0.7.37.03 04/07/2019 Methods loadStockInfo/NameDatabaseFromCSV() Commented on the
//                                Change From 1.0.7.9 to 1.7.0.37 on Assignment of stock via
//                                Changes in Class Stock.
//         1.0.7.37.04 04/28/2019 Method doInBackground() Changed Reference saveStockInfo/Name
//                                DatabaseAsCSV() JStock.instance() to this, Added Those Methods
//                                Along With saveUserDefinedDatabaseAsCSV().
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

import javax.swing.SwingWorker;

import org.apache.commons.logging.Log;
import org.yccheok.jstock.engine.Board;
import org.yccheok.jstock.engine.Code;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.Industry;
import org.yccheok.jstock.engine.Pair;
import org.yccheok.jstock.engine.Stock;
import org.yccheok.jstock.engine.StockInfo;
import org.yccheok.jstock.engine.StockInfoDatabase;
import org.yccheok.jstock.engine.StockNameDatabase;
import org.yccheok.jstock.engine.Symbol;
import org.yccheok.jstock.file.Atom;
import org.yccheok.jstock.file.Statement;
import org.yccheok.jstock.file.Statements;
import org.yccheok.jstock.internationalization.GUIBundle;

//Task to initialize both stockInfoDatabase and stockNameDatabase.

/**
 * @author doraemon
 * @author Dana M. Proctor
 * @version 1.0.7.37.04 04/28/2019
 */

class DatabaseTask extends SwingWorker<Boolean, Void>
{
   // Class Instances.
   private JStock jstock;
   private Log log;
   private boolean readFromDisk = true;
   
   public final static String ZIP_DATABASE_FILE_NAME = "database" + File.separator + "database.zip";

   protected DatabaseTask(JStock jstock, Log log, boolean readFromDisk)
   {
      this.jstock = jstock;
      this.log = log;
      this.readFromDisk = readFromDisk;
   }

   @Override
   protected void done()
   {
      // The done Method: When you are informed that the SwingWorker
      // is done via a property change or via the SwingWorker object's
      // done method, you need to be aware that the get methods can
      // throw a CancellationException. A CancellationException is a
      // RuntimeException, which means you do not need to declare it
      // thrown and you do not need to catch it. Instead, you should
      // test the SwingWorker using the isCancelled method before you
      // use the get method.
      if (this.isCancelled())
      {
         // Cancelled by user explicitly. Do not perform any GUI update.
         // No pop-up message.
         return;
      }

      boolean success = false;

      try
      {
         success = get();
      }
      catch (InterruptedException exp)
      {
         log.error(null, exp);
      }
      catch (java.util.concurrent.ExecutionException exp)
      {
         log.error(null, exp);
      }
      catch (CancellationException ex)
      {
         // Not sure. Some developers suggest to catch this exception as
         // well instead of checking on isCancelled. I will do it both.
         log.error(null, ex);
      }

      if (success)
      {
         jstock.setStatusBar(false, jstock.getBestStatusBarMessage());
         jstock.statusBar.setImageIcon(
            jstock.getImageIcon("/images/16x16/network-transmit-receive.png"),
            java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
               "MainFrame_Connected"));
      }
      else
      {
         jstock.setStatusBar(false, GUIBundle.getString("MainFrame_NetworkError"));
         jstock.statusBar.setImageIcon(jstock.getImageIcon(
            "/images/16x16/network-error.png"), java.util.ResourceBundle.getBundle(
               "org/yccheok/jstock/data/gui").getString("MainFrame_DoubleClickedToTryAgain"));
      }
   }

   @Override
   public Boolean doInBackground()
   {
      final Country country = jstock.getJStockOptions().getCountry();

      Utils.createCompleteDirectoryHierarchyIfDoesNotExist(org.yccheok.jstock.gui.Utils
            .getUserDataDirectory() + country + File.separator + "database");

      if (this.readFromDisk)
      {
         StockInfoDatabase tmp_stock_info_database = loadStockInfoDatabaseFromCSV(country);
         if (tmp_stock_info_database == null)
         {
            // Perhaps we are having a corrupted database. We will
            // restore from database.zip.
            Utils.extractZipFile(ZIP_DATABASE_FILE_NAME, true);

            tmp_stock_info_database = loadStockInfoDatabaseFromCSV(country);
         }

         // StockNameDatabase is an optional item.
         final StockNameDatabase tmp_name_database;
         if (org.yccheok.jstock.engine.Utils.isNameImmutable())
         {
            tmp_name_database = loadStockNameDatabaseFromCSV(country);
         }
         else
         {
            tmp_name_database = null;
         }

         // After time consuming operation, check whether we should
         // cancel.
         if (this.isCancelled())
         {
            return false;
         }

         if (tmp_stock_info_database != null && false == tmp_stock_info_database.isEmpty())
         {
            // Yes. We need to integrate "user-defined-database.csv" into
            // tmp_stock_info_database
            final java.util.List<Pair<Code, Symbol>> pairs = loadUserDefinedDatabaseFromCSV(country);

            boolean addUserDefinedStockInfoSuccessAtLeastOnce = false;

            if (pairs.isEmpty() == false)
            {
               // Remove the old user defined database. Legacy
               // stockcodeandsymboldatabase.xml
               // may contain user defined codes.
               tmp_stock_info_database.removeAllUserDefinedStockInfos();

               // Insert with new user defined code.
               for (Pair<Code, Symbol> pair : pairs)
               {
                  if (tmp_stock_info_database.addUserDefinedStockInfo(StockInfo.newInstance(pair.first,
                     pair.second)))
                  {
                     addUserDefinedStockInfoSuccessAtLeastOnce = true;
                  }
               }
            }

            if (false == addUserDefinedStockInfoSuccessAtLeastOnce)
            {
               // user-defined-database.csv is no longer needed.
               new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator
                        + "database" + File.separator + "user-defined-database.csv").delete();
            }

            // Prepare proper synchronization for us to change country.
            synchronized (jstock.databaseTaskMonitor)
            {
               if (this.isCancelled())
               {
                  return false;
               }

               jstock.stockInfoDatabase = tmp_stock_info_database;
               jstock.stockNameDatabase = tmp_name_database;
               jstock.watchListPanel.setStockInfoDatabase(jstock.stockInfoDatabase);

               return true;
            }
         } // if (tmp_stock_info_database != null && false ==
           // tmp_stock_info_database.isEmpty())
      } // if(this.readFromDisk)

      // When we fall here, we either fail to read from disk or user
      // explicitly doesn't allow us to read from disk. Let's perform
      // networking stuff.
      //
      // For networking stuff, we will try on JStock static server.

      final String location = org.yccheok.jstock.engine.Utils.getStocksCSVZipFileLocation(country);
      // Try to download the CSV file.
      final File zipFile = Utils.downloadAsTempFile(location);
      // Is download success?
      if (zipFile == null)
      {
         return false;
      }

      File tempZipDirectory = null;

      try
      {
         tempZipDirectory = java.nio.file.Files.createTempDirectory(null).toFile();

         if (false == Utils.extractZipFile(zipFile, tempZipDirectory.getAbsolutePath(), true))
         {
            return false;
         }

         File file = new File(tempZipDirectory, "stocks.csv");

         // Try to parse the CSV file.
         final java.util.List<Stock> stocks = org.yccheok.jstock.engine.Utils.getStocksFromCSVFile(file);
         // Is the stocks good enough?
         if (false == stocks.isEmpty())
         {
            final Pair<StockInfoDatabase, StockNameDatabase> stockDatabase = org.yccheok.jstock.engine.Utils
                  .toStockDatabase(stocks, country);

            // After time consuming operation, check whether we should
            // cancel.
            if (this.isCancelled())
            {
               return false;
            }

            // Save to disk.
            saveStockInfoDatabaseAsCSV(country, stockDatabase.first);
            if (stockDatabase.second != null)
            {
               saveStockNameDatabaseAsCSV(country, stockDatabase.second);
            }

            // Yes. We need to integrate "user-defined-database.csv" into
            // tmp_stock_info_database
            final java.util.List<Pair<Code, Symbol>> pairs = loadUserDefinedDatabaseFromCSV(country);

            if (pairs.isEmpty() == false)
            {
               // Insert with new user defined code.
               for (Pair<Code, Symbol> pair : pairs)
               {
                  stockDatabase.first.addUserDefinedStockInfo(StockInfo.newInstance(pair.first,
                     pair.second));
               }
            }

            // Prepare proper synchronization for us to change country.
            synchronized (jstock.databaseTaskMonitor)
            {
               if (this.isCancelled())
               {
                  return false;
               }

               jstock.stockInfoDatabase = stockDatabase.first;
               jstock.stockNameDatabase = stockDatabase.second;

               // Register the auto complete JComboBox with latest database.
               jstock.watchListPanel.setStockInfoDatabase(jstock.stockInfoDatabase);

               return true;
            }
         }
      }
      catch (IOException ex)
      {
         log.error(null, ex);
      }
      finally
      {
         if (tempZipDirectory != null)
         {
            Utils.deleteDir(tempZipDirectory, true);
         }
      }
      return false;
   }
   
   private StockInfoDatabase loadStockInfoDatabaseFromCSV(Country country)
   {
      final File stockInfoDatabaseCSVFile = org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFile(country);

      Statements statements = Statements.newInstanceFromCSVFile(stockInfoDatabaseCSVFile);
      
      if (statements.getType() != Statement.Type.StockInfoDatabase)
         return null;
      
      java.util.List<Stock> stocks = new ArrayList<Stock>();
      
      for (int i = 0, ei = statements.size(); i < ei; i++)
      {
         Statement statement = statements.get(i);
         Atom atom0 = statement.getAtom(0);
         Atom atom1 = statement.getAtom(1);
         Atom atom2 = statement.getAtom(2);
         Atom atom3 = statement.getAtom(3);

         Code code = Code.newInstance(atom0.getValue().toString());
         Symbol symbol = Symbol.newInstance(atom1.getValue().toString());
         Industry industry = Industry.Unknown;
         Board board = Board.Unknown;
         
         try
         {
            industry = Industry.valueOf(atom2.getValue().toString());
         }
         catch (Exception exp)
         {
            log.error(null, exp);
         }
         
         try
         {
            board = Board.valueOf(atom3.getValue().toString());
         }
         catch (Exception exp)
         {
            log.error(null, exp);
         }

         // 1.0.9 (Not Visible in 1.0.9.37, replaced with Stock.builder().)
         // Stock stock = new Stock.Builder(code, symbol).board(board).industry(industry).build();
         Stock stock = Stock.builder(code, symbol).board(board).industry(industry).build();
         stocks.add(stock);
      }
      return new StockInfoDatabase(stocks);
   }
   
   private StockNameDatabase loadStockNameDatabaseFromCSV(Country country)
   {
      final File stockNameDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                     + country + File.separator + "database" + File.separator
                                                     + "stock-name-database.csv");

      Statements statements = Statements.newInstanceFromCSVFile(stockNameDatabaseCSVFile);
      
      if (statements.getType() != Statement.Type.StockNameDatabase)
         return null;
      
      java.util.List<Stock> stocks = new ArrayList<Stock>();
      
      for (int i = 0, ei = statements.size(); i < ei; i++)
      {
         Statement statement = statements.get(i);
         Atom atom0 = statement.getAtom(0);
         Atom atom1 = statement.getAtom(1);
         Code code = Code.newInstance(atom0.getValue().toString());
         String name = atom1.getValue().toString();

         // Symbol doesn't matter. Just provide a dummy value for it.
         // 1.0.9 (Not Visible in 1.0.9.37, replaced with Stock.builder().)
         // Stock stock = new Stock.Builder(code, symbol).board(board).industry(industry).build();
         Stock stock = Stock.builder(code, Symbol.newInstance(code.toString())).name(name).build();
         stocks.add(stock);
      }
      return new StockNameDatabase(stocks);
   }
   
   private java.util.List<Pair<Code, Symbol>> loadUserDefinedDatabaseFromCSV(Country country)
   {
      final File userDefinedDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                       + country + File.separator + "database"
                                                       + File.separator + "user-defined-database.csv");

      if (!userDefinedDatabaseCSVFile.exists())
         return new ArrayList<Pair<Code, Symbol>>();

      Statements statements = Statements.newInstanceFromCSVFile(userDefinedDatabaseCSVFile);
      
      if (statements.getType() != Statement.Type.UserDefinedDatabase)
         return new ArrayList<Pair<Code, Symbol>>();
      
      java.util.List<Pair<Code, Symbol>> pairs = new ArrayList<Pair<Code, Symbol>>();
      
      for (int i = 0, ei = statements.size(); i < ei; i++)
      {
         Statement statement = statements.get(i);
         Atom atom0 = statement.getAtom(0);
         Atom atom1 = statement.getAtom(1);
         Code code = Code.newInstance(atom0.getValue().toString());
         Symbol symbol = Symbol.newInstance(atom1.getValue().toString());

         pairs.add(new Pair<Code, Symbol>(code, symbol));
      }
      return pairs;
   }
   
   protected static boolean saveStockNameDatabaseAsCSV(Country country, StockNameDatabase stockNameDatabase)
   {
      final File stockNameDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                     + country + File.separator + "database" + File.separator
                                                     + "stock-name-database.csv");
      final Statements statements = Statements.newInstanceFromStockNameDatabase(stockNameDatabase);
      boolean result = statements.saveAsCSVFile(stockNameDatabaseCSVFile);
      return result;
   }
   
   protected static boolean saveStockInfoDatabaseAsCSV(Country country, StockInfoDatabase stockInfoDatabase)
   {
      org.yccheok.jstock.gui.Utils
            .createCompleteDirectoryHierarchyIfDoesNotExist(org.yccheok.jstock.engine.Utils
                  .getStockInfoDatabaseFileDirectory(country));
      final File stockInfoDatabaseCSVFile = org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFile(country);
      final Statements statements = Statements.newInstanceFromStockInfoDatabase(stockInfoDatabase);
      boolean result = statements.saveAsCSVFile(stockInfoDatabaseCSVFile);
      return result;
   }
   
   protected static boolean saveUserDefinedDatabaseAsCSV(Country country, StockInfoDatabase stockInfoDatabase)
   {
      // Previously, we will store the entire stockcodeandsymboldatabase.xml
      // to cloud server if stockcodeandsymboldatabase.xml is containing
      // user defined code. Due to our server is running out of space, we will
      // only store UserDefined pair. user-defined-database.xml will be only
      // used for cloud storage purpose.
      //final java.util.List<Pair<Code, Symbol>> pairs = getUserDefinedPair(stockInfoDatabase);
      
      final java.util.List<Pair<Code, Symbol>> userStockList;
      userStockList = new ArrayList<Pair<Code, Symbol>>();
      java.util.List<StockInfo> stockInfos = stockInfoDatabase.getUserDefinedStockInfos();
      
      for (StockInfo stockInfo : stockInfos)
         userStockList.add(new Pair<Code, Symbol>(stockInfo.code, stockInfo.symbol));
      
      
      // pairs can be empty. When it is empty, try to delete the file.
      // If deletion fail, we need to overwrite the file to reflect this.
      final File userDefinedDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                       + country + File.separator + "database"
                                                       + File.separator + "user-defined-database.csv");
      
      if (userStockList.isEmpty() && userDefinedDatabaseCSVFile.delete())
         return true;
     
      final Statements statements = Statements.newInstanceFromUserDefinedDatabase(userStockList);
      boolean result = statements.saveAsCSVFile(userDefinedDatabaseCSVFile);
      // This instance never changes always false.
      //JStock.instance().needToSaveUserDefinedDatabase = false;
      return result;
   }
}