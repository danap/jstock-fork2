/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 04/25/2019
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
// Version 1.0.7.37.01 04/06/2019 Original JStock Engine Intraday Class.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 04/25/2019
 */
public class Intraday
{
   // Class Instances
   public long open;
   public long close;
   
   //==============================================================
   // Constructor.
   // Open & Close represents time in epoch ms.
   //==============================================================
   
   public Intraday(long open, long close)
   {
      this.open = open;
      this.close = close;
   }
   
   @Override
   public String toString()
   {
      StringBuffer parameters = new StringBuffer("[Intraday: ");
      
      parameters.append("[open = " + open + "]");
      parameters.append("[close = " + close + "]");
      
      return parameters.toString();
   } 
}