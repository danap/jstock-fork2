/*
 * JStock-Min - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 06/25/2019
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
// Version 1.0.7.37.01 06/25/2019 Original JStock Engine ChartResponse Class.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine.yahoo.chart;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 06/25/2019
 */
public class ChartResponse
{
   @com.google.gson.annotations.SerializedName(value="chart")
   @com.google.gson.annotations.Expose
   private Chart chart;
   
   public ChartResponse()
   {
      // None.
   }
   
   public ChartResponse(Chart chart)
   {
      this.chart = chart;
   }
   
   public Chart getChart()
   {
      return chart;
   }
   
   public void setChart(Chart chart)
   {
      this.chart = chart;
   }
}
