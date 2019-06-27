/*
 * JStock-Min - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 06/26/2019
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
// Version 1.0.7.37.01 06/26/2019 Original JStock Engine Result Class.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine.yahoo.chart;

import java.util.List;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 06/26/2019
 */
public class Result
{
   @com.google.gson.annotations.SerializedName(value="meta")
   @com.google.gson.annotations.Expose
   private Meta meta;
  
   @com.google.gson.annotations.SerializedName(value="timestamp")
   @com.google.gson.annotations.Expose
   private List<Long> timestamp;
  
   @com.google.gson.annotations.SerializedName(value="indicators")
   @com.google.gson.annotations.Expose
   private Indicators indicators;
   
   public Result()
   {
      timestamp = null;
   }
   
   public Result(Meta meta, List<Long> timestamp, Indicators indicators)
   {
      this.meta = meta;
      this.timestamp = timestamp;
      this.indicators = indicators;
   }
   
   public Meta getMeta()
   {
      return meta;
   }
   
   public void setMeta(Meta value)
   {
      meta = value;
   }
   
   public List<Long> getTimestamp()
   {
      return timestamp;
   }
   
   public void setTimestamp(List<Long> value)
   {
      timestamp = value;
   }
   
   public Indicators getIndicators()
   {
      return indicators;
   }
   
   public void setIndicators(Indicators value)
   {
      indicators = value;
   }
}
