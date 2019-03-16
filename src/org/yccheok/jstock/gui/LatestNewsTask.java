/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 03/16/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock Gui JStock Inner Class
//                                LatestNewsTask.
//         1.0.7.37.01 03/16/2019 Moved Into Separate Class File. Added Arguments JStock,
//                                & Log to Constructor. Referenced Those Instances Throughout
//                                to Replace Local References in Inner Class to JStock.
//                                Formatted.
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingWorker;

import org.apache.commons.logging.Log;

/**
 * @author doraemon
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 03/16/2019
 */

class LatestNewsTask extends SwingWorker<Void, String>
{
   private JStock jstock;
   private Log log;
   
   // Delay first update checking for 20 seconds.
   private static final int SHORT_DELAY = 20 * 1000;
   private volatile CountDownLatch doneSignal;
   
   protected LatestNewsTask(JStock jstock, Log log)
   {
      this.jstock = jstock;
      this.log = log;
   }

   @Override
   protected void done()
   {
   }

   @Override
   protected void process(java.util.List<String> messages)
   {
      boolean show = false;

      for (String message : messages)
      {
         AutoUpdateNewsJDialog dialog = new AutoUpdateNewsJDialog(jstock, true);
         dialog.setNews(message);
         dialog.setVisible(true);
         show = true;
      }

      if (show)
      {
         doneSignal.countDown();
      }
   }

   @Override
   protected Void doInBackground()
   {
      while (!isCancelled())
      {
         try
         {
            Thread.sleep(SHORT_DELAY);
         }
         catch (InterruptedException ex)
         {
            log.info(null, ex);
            break;
         }
         final java.util.Map<String, String> map = Utils.getUUIDValue(
            org.yccheok.jstock.network.Utils.getURL(
               org.yccheok.jstock.network.Utils.Type.NEWS_INFORMATION_TXT));
         final String newsID = jstock.getJStockOptions().getNewsID();
         if (newsID.equals(map.get("news_id")))
         {
            // Seen before. Quit.
            break;
         }
         final String location = map.get("news_url");
         if (location == null)
         {
            break;
         }
         doneSignal = new CountDownLatch(1);
         final String respond = org.yccheok.jstock.gui.Utils
               .getResponseBodyAsStringBasedOnProxyAuthOption(location);
         if (respond == null)
         {
            break;
         }
         if (respond.indexOf(Utils.getJStockUUID()) < 0)
         {
            break;
         }
         publish(respond);
         try
         {
            doneSignal.await();
         }
         catch (InterruptedException ex)
         {
            log.info(null, ex);
            break;
         }
         // Update jStockOptions, will make this while loop break
         // in next iteration.
         jstock.jStockOptions.setNewsID(map.get("news_id"));
      }

      return null;
   }
}