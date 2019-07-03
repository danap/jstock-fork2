/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 07/03/2019
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
// Version 1.0.7.37.01 07/03/2018 Original JStock Engine GoogleCodeSQLiteOpenHelper Class.
//                                
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.gui.JStock;
import org.yccheok.jstock.gui.JStockOptions;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 07/03/2019
 */
public enum GoogleCodeSQLiteOpenHelper
{
   INSTANCE;
   
   private Connection conn;
   
   private static final String COLUMN_YAHOO = "yahoo";
   private static final String COLUMN_GOOGLE = "google";
   private static final String UNIQUE_CONSTRAINT = "unique (" + COLUMN_YAHOO + ") on conflict ignore";
   private static final boolean NOTFIND_RESET = false;

   private static final Log log = LogFactory.getLog(GoogleCodeSQLiteOpenHelper.class);
   
   private GoogleCodeSQLiteOpenHelper()
   {
      String url;
      
      conn = null;
      
      url = new StringBuilder("jdbc:sqlite:" + org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                              + "stock_info.db").toString();
      
      // This leaves this connection, attachment to
      // db file open.
      try
      {
         conn = DriverManager.getConnection(url);
      }
      catch (SQLException sqle){}
   }
    
   private String getTableName(Country country)
   {
      return new StringBuilder("google_code_" + country.name().toLowerCase()).toString();
   }
  
   public boolean isTableExists(Country country)
   {
      String sql;
      PreparedStatement pstmt;
      ResultSet rs;
      
      if (conn == null)
        return false;
      
      sql = "select tbl_name from sqlite_master where tbl_name = ? limit 1";
      
      pstmt = null;
      rs = null;
      
      try
      {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, getTableName(country));
         rs = pstmt.executeQuery();
         
         return rs.next();
      }
      catch (SQLException sqle)
      {
         log.error(null, sqle);
         return false;
      }
      finally
      {
         closeResources(pstmt, rs);
      }
   }
  
   public boolean insert(Country country, List<Pair<String, String>> googleCodes)
   {
      int result;
      Statement stmt;
      String tableName;
      PreparedStatement pstmt;
      
      StringBuilder sqlStringBuilder;
      boolean success;
      
      if (conn == null)
         return false;
      
      tableName = getTableName(country);
      
      sqlStringBuilder = new StringBuilder();
      success = true;
      
      pstmt = null;
      
      try
      {
         conn.setAutoCommit(false);
         stmt = conn.createStatement();
         stmt.execute("DROP TABLE IF EXISTS " + tableName);
         
         sqlStringBuilder.append("CREATE TABLE IF NOT EXISTS " + tableName + "("
                                 + COLUMN_YAHOO + " text not null, "
                                 + COLUMN_GOOGLE + " text not null, "
                                 + UNIQUE_CONSTRAINT + ");");
         // System.out.println(sqlStringBuilder);
         stmt.execute(sqlStringBuilder.toString());
         
         sqlStringBuilder.delete(0, sqlStringBuilder.length());
         sqlStringBuilder.append("CREATE INDEX " + tableName + "_yahoo_idx ON "
                                 + tableName + "(" + COLUMN_YAHOO + ");");
         // System.out.println(sqlStringBuilder);
         stmt.execute(sqlStringBuilder.toString());
         
         sqlStringBuilder.delete(0, sqlStringBuilder.length());
         sqlStringBuilder.append("CREATE INDEX " + tableName + "_google_idx ON "
                                 + tableName +  "(" + COLUMN_GOOGLE + ");");
         // System.out.println(sqlStringBuilder);
         stmt.execute(sqlStringBuilder.toString());
         
         sqlStringBuilder.delete(0, sqlStringBuilder.length());
         sqlStringBuilder.append("INSERT INTO " + tableName + "(" + COLUMN_YAHOO + "," + COLUMN_GOOGLE
                                 + ") VALUES(?, ?);");
         // System.out.println(sqlStringBuilder);
         
         pstmt = conn.prepareStatement(sqlStringBuilder.toString());
         
         for (Pair<String, String> googleCode : googleCodes)
         {
            pstmt.setString(1, googleCode.first);
            pstmt.setString(2, googleCode.second);
            result = pstmt.executeUpdate();
            
            if (result != 1)
            {
               success = false;
               break;
            }     
         }
         
         if (success)
            conn.commit();
         else
            conn.rollback();
      }
      catch (SQLException sqle)
      {
         log.error(null, sqle);
         success = false;
      }
      finally
      {
         closeResources(pstmt, null);
         
         try
         {
            conn.setAutoCommit(true);
         }
         catch (SQLException e)
         {
            log.error(null, e);
         }
      } 
      return success;
   }
  
   public String find(Country country, String yahoo)
   {
      JStockOptions jStockOptions;
      Long l;
      
      String sql;
      PreparedStatement pstmt;
      ResultSet rs;
      
      if (conn == null || !isTableExists(country))
         return null;
      
      sql = new StringBuilder("select google from " + getTableName(country)
                              + " where yahoo = ? limit 1").toString();
      pstmt = null;
      rs = null;
      
      try
      {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, yahoo);
         rs = pstmt.executeQuery();
         
         if (rs.next())
            return rs.getString(COLUMN_GOOGLE);
         else
         {
            // Set timestamp for reload of db.
            // Rather not do this automatically if input mistake in find string.
            
            if (NOTFIND_RESET)
            {
               jStockOptions = JStock.instance().getJStockOptions();
               l = jStockOptions.getGoogleCodeDatabaseMetaTimestamp(country);
               
               if (l != null)
               {
                  l = 0L;
                  jStockOptions.setGoogleCodeDatabaseMetaTimestamp(country, l);
               }
            }
            return null;
         }
      }
      catch (SQLException sqle)
      {
         log.error(null, sqle);
         return null;
      }
      finally
      {
         closeResources(pstmt, rs);
      } 
   }
   
   private void closeResources(PreparedStatement pstmt, ResultSet rs)
   {
      try
      {
         if (rs != null)
            rs.close();
      }
      catch (SQLException e)
      {
         log.info(null, e);
      }
      finally
      {
         try
         {
            if (pstmt != null)
               pstmt.close();   
         }
         catch (SQLException e)
         {
            log.info(null, e);
         }
      }
   }
}
