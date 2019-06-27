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
// Version 1.0.7.37.01 06/26/2019 Original JStock Engine Quote Class.
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
public class Quote
{
   @com.google.gson.annotations.SerializedName(value="high")
   @com.google.gson.annotations.Expose
   private List<Double> high;
  
   @com.google.gson.annotations.SerializedName(value="open")
   @com.google.gson.annotations.Expose
   private List<Double> open;
  
   @com.google.gson.annotations.SerializedName(value="low")
   @com.google.gson.annotations.Expose
   private List<Double> low;
  
   @com.google.gson.annotations.SerializedName(value="volume")
   @com.google.gson.annotations.Expose
   private List<Long> volume;
  
   @com.google.gson.annotations.SerializedName(value="close")
   @com.google.gson.annotations.Expose
   private List<Double> close;
   
   public Quote()
   {
      high = null;
      open = null;
      low = null;
      volume = null;
      close = null;
   }
   
   public Quote(List<Double> high, List<Double> open, List<Double> low, List<Long> volume, List<Double> close)
   {
      this.high = high;
      this.open = open;
      this.low = low;
      this.volume = volume;
      this.close = close;
   }
   
   public List<Double> getHigh()
   {
      return high;
   }
   
   public void setHigh(List<Double> value)
   {
      high = value;
   }
   
   public List<Double> getOpen()
   {
      return open;
   }
   
   public void setOpen(List<Double> value)
   {
      open = value;
   }
   
   public List<Double> getLow()
   {
      return low;
   }
   
   public void setLow(List<Double> value)
   {
      low = value;
   }
   
   public List<Long> getVolume()
   {
      return volume;
   }
   
   public void setVolume(List<Long> value)
   {
      volume = value;
   }
   
   public List<Double> getClose()
   {
      return close;
   }
   
   public void setClose(List<Double> value)
   {
      close = value;
   } 
}
