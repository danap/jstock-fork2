/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2018 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 04/19/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock File UserDataFile Class.
//         1.0.7.37.01 04/19/2019 Changed Class Instance UIOptionsJson to UIOptionsXml.
//                                Also Changed String to uioptions.xml.
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.file;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 04/19/2019
 */
public enum UserDataFile {
    CashFlowChartJDialogXml("cashflowchartjdialog.xml"),
    ChartJDialogOptionsXml("chartjdialogoptions.xml"),
    IndicatorScannerJPanelXml("indicatorscannerjpanel.xml"),
    MainFrameXml("mainframe.xml"),
    OptionsXml("options.xml"),
    PortfolioManagementJPanelXml("portfoliomanagementjpanel.xml"),
    UIOptionsXml("uioptions.xml");
    
    UserDataFile(String filename) {
        this.filename = filename;
    }

    public String get() {
        return this.filename;
    }

    private final String filename;
}
