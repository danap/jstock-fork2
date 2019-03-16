/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 03/16/2019
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
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CancellationException;

import javax.swing.SwingWorker;

import org.apache.commons.logging.Log;
import org.yccheok.jstock.engine.Code;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.Pair;
import org.yccheok.jstock.engine.Stock;
import org.yccheok.jstock.engine.StockInfo;
import org.yccheok.jstock.engine.StockInfoDatabase;
import org.yccheok.jstock.engine.StockNameDatabase;
import org.yccheok.jstock.engine.Symbol;
import org.yccheok.jstock.internationalization.GUIBundle;

//Task to initialize both stockInfoDatabase and stockNameDatabase.

/**
 * @author doraemon
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 03/16/2019
 */

class DatabaseTask extends SwingWorker<Boolean, Void>
{
   // Class Instances.
   private JStock jstock;
   private Log log;
   private boolean readFromDisk = true;

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
         StockInfoDatabase tmp_stock_info_database = jstock.loadStockInfoDatabaseFromCSV(country);
         if (tmp_stock_info_database == null)
         {
            // Perhaps we are having a corrupted database. We will
            // restore from database.zip.
            jstock.initPreloadDatabase(true);

            tmp_stock_info_database = jstock.loadStockInfoDatabaseFromCSV(country);
         }

         // StockNameDatabase is an optional item.
         final StockNameDatabase tmp_name_database;
         if (org.yccheok.jstock.engine.Utils.isNameImmutable())
         {
            tmp_name_database = jstock.loadStockNameDatabaseFromCSV(country);
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
            final java.util.List<Pair<Code, Symbol>> pairs = jstock.loadUserDefinedDatabaseFromCSV(country);

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
            JStock.saveStockInfoDatabaseAsCSV(country, stockDatabase.first);
            if (stockDatabase.second != null)
            {
               JStock.saveStockNameDatabaseAsCSV(country, stockDatabase.second);
            }

            // Yes. We need to integrate "user-defined-database.csv" into
            // tmp_stock_info_database
            final java.util.List<Pair<Code, Symbol>> pairs = jstock.loadUserDefinedDatabaseFromCSV(country);

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
}