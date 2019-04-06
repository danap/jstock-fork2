/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2014 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 04/06/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock Engine Index Class.
//         1.0.7.37.01 04/06/2019 Added Entries for Argentina, Brazil, Finland, Poland,
//                                Russia, SaudiaArabia, Thailand, & Turkey. Changed
//                                Denmark Name From KFX to OMXH25. Replaced SMSI Spain
//                                With IBEX35.
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.engine;

import org.yccheok.jstock.engine.Country;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 04/06/2019
 */
public enum Index {
    // By referring to Bloomberg Android app.
    // Added 1.0.7.37?
    NULL(Country.Argentina, "Merval", "Buenos Aires Stock Exchange Merval Index", Code.newInstance("^MERV")),
    ASX(Country.Australia, "ASX 200", "S&P/ASX 200", Code.newInstance("^AXJO")),
    AORD(Country.Australia, "All Ordinaries", "All Ordinaries", Code.newInstance("^AORD")),
    ATX(Country.Austria, "ATX", "Austrian Traded Index", Code.newInstance("^ATX")),
    BFX(Country.Belgium, "BEL-20", "BEL-20", Code.newInstance("^BFX")),
    BVSP(Country.Brazil, "Bovespa", "Ibovespa Brasil Sao Paulo", Code.newInstance("^BVSP")),
    // Added 1.0.7.37 (Check MarketPanel)
    MERV(Country.Brazil, "MERV", "Buenos Aires Stock Exchange", Code.newInstance("^MERV")),
    GSPTSE(Country.Canada, "TSX", "S&P/TSX Composite Index", Code.newInstance("^GSPTSE")),
    CSI300(Country.China, "CSI 300", "Shanghai Shenzhen CSI 300 Index", Code.newInstance("000300.SS")),
    SSEC(Country.China, "China Shanghai Composite", "China Shanghai Composite", Code.newInstance("000001.SS")),
    // Changed 1.0.7.37
    OMXC20CO(Country.Denmark, "OMXC20", "OMX Copenhagen 20 Index", Code.newInstance("^OMXC20")),
    // Added 1.0.7.37
    OMXH25(Country.Finland, "OMXH25", "OMX Helsinki 25 Index", Code.newInstance("^OMXH25")),
    FCHI(Country.France, "CAC 40", "CAC 40 Index", Code.newInstance("^FCHI")),
    DAX(Country.Germany, "DAX", "Deutsche Borse AG German Stock Index", Code.newInstance("^GDAXI")),
    HSI(Country.HongKong, "Hang Seng", "Hong Kong Hang Seng Index", Code.newInstance("^HSI")),
    BSESN(Country.India, "SENSEX", "S&P BSE India Sensex Index", Code.newInstance("^BSESN")),
    NSEI(Country.India, "NIFTY", "National Stock Exchange CNX Nifty Index", Code.newInstance("^NSEI")),
    JKSE(Country.Indonesia, "Jakarta Composite", "Jakarta Composite", Code.newInstance("^JKSE")),
    TA25(Country.Israel, "TA 25", "Tel Aviv 25 Index", Code.newInstance("T25.TA")),
    TA100(Country.Israel, "TA 100", "Tel Aviv 100 Index", Code.newInstance("^TA100")),
    FTSEMIB(Country.Italy, "MIB", "FTSE MIB Index", Code.newInstance("FTSEMIB.MI")),
    NIKKEI(Country.Japan, "Nikkei 225", "Nikkei 225", Code.newInstance("^N225")),
    KS11(Country.Korea, "KOSPI", "Korea Stock Exchange KOSPI Index", Code.newInstance("^KS11")),
    KLSE(Country.Malaysia, "KLCI", "Kuala Lumpur Composite Index", Code.newInstance("^KLSE")),    
    AEX(Country.Netherlands, "AEX", "AEX-Index", Code.newInstance("^AEX")),
    NZSX50(Country.NewZealand, "NZX 50", "New Zealand Exchange 50 Gross Index", Code.newInstance("^NZ50")),
    OSEAX(Country.Norway, "OSEBX", "Oslo Stock Exchange Benchmark Index", Code.newInstance("^OSEAX")),
    // Added 1.0.7.37 (Name:WIG30, Code:WSE:WIG30)
    WIG30(Country.Poland, "WIG30", "Capitalization-Weighted Index", Code.newInstance("WIG30:IND")),
    PSI20(Country.Portugal, "PSI 20", "PSI 20 Index", Code.newInstance("PSI20.LS")),
    // Added 1.0.7.37 (Name:MICEX Index Code:^MICEX)
    MICEX(Country.Russia, "MICEX", "Moscow Interbank Currency Exchange", Code.newInstance("MICEXINDEXCF.ME")),
    // Added 1.0.7.37 (Code:^TASI)
    TASI(Country.SaudiArabia, "TASI", "Tadawul All Shares Index", Code.newInstance("^TASI.SR")),
    STI(Country.Singapore, "STI", "Straits Times Index", Code.newInstance("^STI")),
    // Replaced 1.0.7.37
    // SMSI(Country.Spain, "Madrid General", "Madrid General", Code.newInstance("^SMSI")),
    IBEX35(Country.Spain, "IBEX 35", "Spanish Continuous Exchange Index", Code.newInstance("^IBEX")),
    OMX30(Country.Sweden, "OMX 30", "OMX Stockholm 30 Index", Code.newInstance("^OMX")),
    SSMI(Country.Switzerland, "SMI", "Swiss Market Index", Code.newInstance("^SSMI")),
    TWII(Country.Taiwan, "TSEC", "Taiwan Stock Exchange Weighted Index", Code.newInstance("^TWII")),
    // Added 1.0.7.37 (Long Name:Stock Exchange of Thailand SET Index, Code:^SET (Fails))
    SET(Country.Thailand, "SET", "The Stock Exchange of Thailand Index", Code.newInstance("^SET.BK")),
    // Added 1.0.7.37 (Code:^XU100 (Fails for Google, Yahoo Should Work.)
    BIST100(Country.Turkey, "BIST 100", "Borsa Istanbul 100 Index", Code.newInstance("^XU100")),
    FTSE(Country.UnitedKingdom, "FTSE 100", "FTSE 100 Index", Code.newInstance("^FTSE")),
    DJI(Country.UnitedState, "DOW JONES", "Dow Jones Industrial Average", Code.newInstance("^DJI")),
    IXIC(Country.UnitedState, "NASDAQ", "NASDAQ Composite Index", Code.newInstance("^IXIC")),
    SP500(Country.UnitedState, "S&P500", "Standard and Poors 500 Index", Code.newInstance("^GSPC"));
    
    Index(Country country, String name, String longName, Code code) {
        this.country = country;
        this.name = name;
        this.longName = longName;
        this.code = code;
    }


    @Override
    public String toString() {
        return name;
    }
    
    public final String name;
    public final String longName;
    public final Code code;
    public final Country country;
}