/*
 * JStock-Fork
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 03/29/2019
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
// Version 1.0.7.37    02/29/2019 Original JStock CVSWatchList Inner Static Class.
//         1.0.7.37.01 03/08/2019 Moved From JStock Class Inner Class in org.yccheok.jstock.gui
//                                to Here. Removed static & Removed newInstance() Method.
//                                Changed Name to CSVWatchList.
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.watchlist;

import javax.swing.table.TableModel;

public class CSVWatchList
{
   // Class Instances.
   public TableModel tableModel;

   public CSVWatchList(TableModel tableModel)
   {
      if (tableModel == null)
         throw new java.lang.IllegalArgumentException();
      
      this.tableModel = tableModel;
   }
}