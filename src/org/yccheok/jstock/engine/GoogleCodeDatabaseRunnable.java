/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 07/02/2019
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
// Version 1.0.7.37.01 07/02/2018 Original JStock Engine GoogleCodeDatabaseRunnable Class.
//                                
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.gui.JStock;
import org.yccheok.jstock.gui.JStockOptions;

import au.com.bytecode.opencsv.CSVReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 07/02/2019
 */
public class GoogleCodeDatabaseRunnable implements Runnable 
{
   private Country country;
   private static java.util.Map<Country, Long> latestGoogleCodeDatabaseMeta;

   private static final Log log = LogFactory.getLog(GoogleCodeDatabaseRunnable.class);

   public GoogleCodeDatabaseRunnable(Country country)
   {
      this.country = country;
   }
   
   public void run()
   {
      boolean status;
      Long latest;
      JStockOptions jStockOptions;
      Long current;
      
      if (latestGoogleCodeDatabaseMeta == null)
         latestGoogleCodeDatabaseMeta = getLatestGoogleCodeDatabaseMeta();
      
      if (latestGoogleCodeDatabaseMeta == null)
      {
         log.info("latestGoogleCodeDatabaseMeta: NULL");
         return;
      }
      
      latest = latestGoogleCodeDatabaseMeta.get(country);
      
      if (latest == null)
         return;
      
      jStockOptions = JStock.instance().getJStockOptions();
      
      current = jStockOptions.getGoogleCodeDatabaseMetaTimestamp(country);
      
      if (latest.longValue() == current.longValue()
          && GoogleCodeSQLiteOpenHelper.INSTANCE.isTableExists((country)))
         return;
      
      status = buildGoogleCodeDatabase();
      
      if (status)
      {
         latest = latestGoogleCodeDatabaseMeta.get(country);
         jStockOptions.setGoogleCodeDatabaseMetaTimestamp(country, latest);
      }
   }
   
   private Map<Country, Long> getLatestGoogleCodeDatabaseMeta()
   {
      String googleCodeDatabaseMetaUrl;
      String googleCodeDatabaseMetaJson;
      com.google.gson.Gson gson;
      
      // "https://raw.githubusercontent.com/yccheok/jstock/master/appengine/jstock-static/war/";
      // "stocks_information/google-code-database-meta.json"
      
      googleCodeDatabaseMetaUrl = org.yccheok.jstock.network.Utils.getURL(
         org.yccheok.jstock.network.Utils.Type.GOOGLE_CODE_DATABASE_META);
      
      googleCodeDatabaseMetaJson = org.yccheok.jstock.gui.Utils.getResponseBodyAsStringBasedOnProxyAuthOption(
         googleCodeDatabaseMetaUrl);
      
      if (googleCodeDatabaseMetaJson == null)
      {
         log.info("latestGoogleCodeDatabaseMeta: NULL");
         return null;
      }
      
      gson = getGsonForDatabaseMeta();
      
      latestGoogleCodeDatabaseMeta = null;
      
      try
      {
         latestGoogleCodeDatabaseMeta = gson.fromJson(googleCodeDatabaseMetaJson,
            new TypeToken<EnumMap<Country, Long>>(){}.getType());
      }
      catch (JsonSyntaxException jse)
      {
         log.error(null, jse);
      }
      return latestGoogleCodeDatabaseMeta; 
   }
   
   private Gson getGsonForDatabaseMeta()
   {
      return new GsonBuilder().registerTypeAdapter(
         new TypeToken<EnumMap<Country, Long>>(){}.getType(),
         new EnumMapInstanceCreator<Country, Long>(Country.class)).create();
   }
   
   private boolean buildGoogleCodeDatabase()
   {
      File googleCodeDatabaseFile;
      List<Pair<String, String>> googleCodes;
      boolean result;
      String googleCodeDatabaseUrl;
      File googleCodeDatabaseZipFile;
      File tempZipDirectory;
      
      // "https://raw.githubusercontent.com/yccheok/jstock/master/appengine/jstock-static/war/";
      // "stocks_information/" + COUNTRY_TEMPLATE + "/google-code-database.zip"
      
      googleCodeDatabaseUrl = org.yccheok.jstock.network.Utils.getURL(
         org.yccheok.jstock.network.Utils.Type.GOOGLE_CODE_DATABASE, country);
      
      googleCodeDatabaseZipFile = org.yccheok.jstock.gui.Utils.downloadAsTempFile(googleCodeDatabaseUrl);
      
      if (googleCodeDatabaseZipFile == null)
         return false;
      
      tempZipDirectory = null;
      
      try
      {
         tempZipDirectory = Files.createTempDirectory(null, new FileAttribute[0]).toFile();
         
         if (tempZipDirectory != null)
         {
            if (org.yccheok.jstock.gui.Utils.extractZipFile(googleCodeDatabaseZipFile,
               tempZipDirectory.getAbsolutePath(), true))
            {
               googleCodeDatabaseFile = new File(tempZipDirectory, "google-code-database.csv");
               googleCodes = getGoogleCodesFromCSVFile(googleCodeDatabaseFile);
               
               if (!googleCodes.isEmpty())
               {
                  result = GoogleCodeSQLiteOpenHelper.INSTANCE.insert(country, googleCodes);
                  
                  if (result && GoogleCodeSQLiteOpenHelper.INSTANCE.isTableExists(country))
                       return true;
               }
            }
         }
         return false;
      }
      catch (IOException ioe)
      {
         log.error(null, ioe);
         return false;
      }
      finally
      {
         if (googleCodeDatabaseZipFile != null)
            googleCodeDatabaseZipFile.delete();
         
         if (tempZipDirectory != null)
            org.yccheok.jstock.gui.Utils.deleteDir(tempZipDirectory);
      }  
   }
   
   private List<Pair<String, String>> getGoogleCodesFromCSVFile(File googleCodeDatabaseFile)
   {
      String[] types;
      int yahoo_index;
      int google_index;
      boolean success_index;
      String[] nextLine;
      List<Pair<String, String>> googleCodes;
      
      FileInputStream fileInputStream;
      InputStreamReader inputStreamReader;
      CSVReader csvreader;
      
      googleCodes = new ArrayList<Pair<String, String>>();
      
      fileInputStream = null;
      inputStreamReader = null;
      csvreader = null;
      
      try
      {
         fileInputStream = new FileInputStream(googleCodeDatabaseFile);
         inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
         csvreader = new CSVReader(inputStreamReader);
         
         types = csvreader.readNext();
         
         if (types == null)
            return Collections.emptyList();
         
         yahoo_index = -1;
         google_index = -1;
         success_index = false;
         
         for (int index = 0; index < types.length; index++)
         {  
            if (types[index].compareToIgnoreCase("yahoo") == 0)
               yahoo_index = index;
            
            if (types[index].compareToIgnoreCase("google") == 0)
               google_index = index;
            
            if (yahoo_index != -1 && google_index != -1)
               break;
         }
         
         if (yahoo_index != -1 && google_index != -1)
            success_index = true;
         else
            return Collections.emptyList();
         
         while (success_index)
         {
            nextLine = csvreader.readNext();
            
            if (nextLine == null)
            {
               success_index = false;
               break;
            }
            
            if (nextLine.length != types.length)
               log.error("Incorrect CSV format. There should be exactly " + types.length + " item(s)");
            else
               googleCodes.add(Pair.create(nextLine[yahoo_index], nextLine[google_index]));
         }
      }
      catch (IOException ioe)
      {
         log.error(null, ioe);
         return Collections.emptyList();
      }
      finally
      {
         try
         {
            if (csvreader != null)
               csvreader.close();
         }
         catch (IOException e)
         {
            log.error(null, e);
         }
         finally
         {
            org.yccheok.jstock.file.Utils.close(inputStreamReader);
            org.yccheok.jstock.file.Utils.close(fileInputStream);
         }
      }
      
      if (!googleCodes.isEmpty())
         return googleCodes;
      else
         return Collections.emptyList();
   }
   
   public void clear()
   {
      latestGoogleCodeDatabaseMeta = null;
   } 
}
