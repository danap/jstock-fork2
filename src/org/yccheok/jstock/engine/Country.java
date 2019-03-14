/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2015 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana M. Proctor <danap@dandymadeproductions.com>
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
// Version 1.0.7.9    07/21/2015 Original Yan Cheng Cheok Country Class
//         1.1.37.1   01/23/2019 Added Country Entries Argentina, Finland, Poland,
//                               Russia, Saudia Arabia, South Africa, Thailand, &
//                               Turkey. DMP.
//
//=================================================================

package org.yccheok.jstock.engine;

import javax.swing.ImageIcon;
import org.yccheok.jstock.engine.currency.Currency;

/**
 *
 * @author yccheok, Dana M. Proctor
 * @Version 1.0.37.1
 */
public enum Country {
	Argentina("/images/16x16/ar.png", "Argentina", Currency.valueOf("ARS"), Currency.valueOf("ARS")),
    Australia("/images/16x16/au.png", "Australia", Currency.valueOf("AUD"), Currency.valueOf("AUD")),
    Austria("/images/16x16/at.png", "Austria", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    Belgium("/images/16x16/be.png", "Belgium", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    Brazil("/images/16x16/br.png", "Brazil", Currency.valueOf("BRL"), Currency.valueOf("BRL")),
    Canada("/images/16x16/ca.png", "Canada", Currency.valueOf("CAD"), Currency.valueOf("CAD")),
    China("/images/16x16/cn.png", "China", Currency.valueOf("CNY"), Currency.valueOf("CNY")),
    Czech("/images/16x16/cz.png", "Czech Republic", Currency.valueOf("CZK"), Currency.valueOf("CZK")),
    Denmark("/images/16x16/dk.png", "Denmark", Currency.valueOf("DKK"), Currency.valueOf("DKK")),
    Finland("/images/16x16/fi.png", "Finland", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    France("/images/16x16/fr.png", "France", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    Germany("/images/16x16/de.png", "Germany", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    HongKong("/images/16x16/hk.png", "Hong Kong", Currency.valueOf("HKD"), Currency.valueOf("HKD")),
    Hungary("/images/16x16/hu.png", "Hungary", Currency.valueOf("HUF"), Currency.valueOf("HUF")),
    India("/images/16x16/in.png", "India", Currency.valueOf("INR"), Currency.valueOf("INR")),
    Indonesia("/images/16x16/id.png", "Indonesia", Currency.valueOf("IDR"), Currency.valueOf("IDR")),
    Israel("/images/16x16/il.png", "Israel", Currency.valueOf("ILS"), Currency.valueOf("ILS")),
    Italy("/images/16x16/it.png", "Italy", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    Japan("/images/16x16/jp.png", "Japan", Currency.valueOf("JPY"), Currency.valueOf("JPY")),
    Korea("/images/16x16/kr.png", "Korea", Currency.valueOf("KPW"), Currency.valueOf("KPW")),
    Malaysia("/images/16x16/my.png", "Malaysia", Currency.valueOf("MYR"), Currency.valueOf("MYR")),
    Netherlands("/images/16x16/nl.png", "Netherlands", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    NewZealand("/images/16x16/nz.png", "New Zealand", Currency.valueOf("NZD"), Currency.valueOf("NZD")),
    Norway("/images/16x16/no.png", "Norway", Currency.valueOf("NOK"), Currency.valueOf("NOK")),
    Poland("/images/16x16/pl.png", "Poland", Currency.valueOf("PLN"), Currency.valueOf("PLN")),
    Portugal("/images/16x16/pt.png", "Portugal", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    Russia("/images/16x16/ru.png", "Russia", Currency.valueOf("RUB"), Currency.valueOf("RUB")),
    SaudiArabia("/images/16x16/sa.png", "SaudiArabia", Currency.valueOf("SAR"), Currency.valueOf("SAR")),
    Singapore("/images/16x16/sg.png", "Singapore", Currency.valueOf("SGD"), Currency.valueOf("SGD")),
    SouthAfrica("/images/16x16/za.png", "SouthAfrica", Currency.valueOf("ZAR"), Currency.valueOf("ZAR")),
    Spain("/images/16x16/es.png", "Spain", Currency.valueOf("EUR"), Currency.valueOf("EUR")),
    Sweden("/images/16x16/se.png", "Sweden", Currency.valueOf("SEK"), Currency.valueOf("SEK")),
    Switzerland("/images/16x16/ch.png", "Switzerland", Currency.valueOf("CHF"), Currency.valueOf("CHF")),
    Taiwan("/images/16x16/tw.png", "Taiwan", Currency.valueOf("TWD"), Currency.valueOf("TWD")),
    Thailand("/images/16x16/th.png", "Thailand", Currency.valueOf("THB"), Currency.valueOf("THB")),
    Turkey("/images/16x16/tr.png", "Turkey", Currency.valueOf("TRY"), Currency.valueOf("TRY")),
    UnitedKingdom("/images/16x16/gb.png", "United Kingdom", Currency.valueOf("GBX"), Currency.valueOf("GBP")),
    UnitedState("/images/16x16/us.png", "United States", Currency.valueOf("USD"), Currency.valueOf("USD"));
            
    Country(String fileName, String humanString, Currency stockCurrency, Currency localCurrency) {
        this.icon = new javax.swing.ImageIcon(this.getClass().getResource(fileName));
        this.humanString = humanString;
        this.stockCurrency = stockCurrency;
        this.localCurrency = localCurrency;
    }
    
    // For legacy reason, when generating path or operation, the following code
    // should be used :
    // String path = country.name() + File.separator
    //
    // However, we make a mistake by using
    // String path = country + File.separator
    //
    // That's why we avoid from override toString. Instead, we provide
    // toHumanReadableString
    //
    //@Override
    //public String toString() {
    //    return string;
    //}
    
    public final ImageIcon icon;
    public final String humanString; 
    public final Currency stockCurrency;
    public final Currency localCurrency;
}
