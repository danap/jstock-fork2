/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2015 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 04/10/2019
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
// Version 1.0.7.9     08/26/2018 Original Yan Cheng, JStock Engine Period Class.
//         1.0.7.37.01 04/10/2019 Added Element Day1. Changed Class Instance field
//                                to calendar. Made Both Instances Public.
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import java.util.Calendar;

/**
 * @author yccheok 
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 04/10/2019
 */

public enum Period {
    // Order is important. Less days come first.
    Day1(Calendar.DATE, 1),
    Days7(Calendar.DATE, 7),
    Month1(Calendar.MONTH, 1),
    Months3(Calendar.MONTH, 3),
    Months6(Calendar.MONTH, 6),
    Year1(Calendar.YEAR, 1),
    Years5(Calendar.YEAR, 5),
    Years10(Calendar.YEAR, 10);

    Period(int calendar, int count) {
        this.calendar = calendar;
        this.count = count;
    }
    
    public long getStartTimestamp(long endTimestamp) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endTimestamp);
        calendar.add(this.calendar, -count);
        
        return calendar.getTimeInMillis();
    }

    public final int calendar;
    public final int count;
}
