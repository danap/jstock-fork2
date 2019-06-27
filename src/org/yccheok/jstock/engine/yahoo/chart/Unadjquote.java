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
// Version 1.0.7.37.01 06/26/2019 Original JStock Engine Unadjquote Class.
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
public class Unadjquote
{
   @com.google.gson.annotations.SerializedName(value="unadjclose")
   @com.google.gson.annotations.Expose
   private List<Double> unadjclose;
  
   @com.google.gson.annotations.SerializedName(value="unadjhigh")
   @com.google.gson.annotations.Expose
   private List<Double> unadjhigh;
  
   @com.google.gson.annotations.SerializedName(value="unadjlow")
   @com.google.gson.annotations.Expose
   private List<Double> unadjlow;
  
   @com.google.gson.annotations.SerializedName(value="unadjopen")
   @com.google.gson.annotations.Expose
   private List<Double> unadjopen;
   
   public Unadjquote()
   {
      unadjclose = null;
      unadjhigh = null;
      unadjlow = null;
      unadjopen = null;
   }
   
   public Unadjquote(List<Double> unadjclose, List<Double> unadjhigh, List<Double> unadjlow,
                     List<Double> unadjopen)
   {
      this.unadjclose = unadjclose;
      this.unadjhigh = unadjhigh;
      this.unadjlow = unadjlow;
      this.unadjopen = unadjopen;
   }
   
   public List<Double> getUnadjclose()
   {
      return unadjclose;
   }
   
   public void setUnadjclose(List<Double> value)
   {
      unadjclose = value;
   }
   
   public List<Double> getUnadjhigh()
   {
      return unadjhigh;
   }
   
   public void setUnadjhigh(List<Double> value)
   {
      unadjhigh = value;
   }
   
   public List<Double> getUnadjlow()
   {
      return unadjlow;
   }
   
   public void setUnadjlow(List<Double> value)
   {
      unadjlow = value;
   }
   
   public List<Double> getUnadjopen()
   {
      return unadjopen;
   }
   
   public void setUnadjopen(List<Double> value)
   {
      unadjopen = value;
   }  
}
