/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2011 Yan Cheng CHEOK <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.01 03/18/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock Gui MyJXStatusBar Class.
//         1.0.7.37.01 03/18/2019 Added Class Methods initStatusBar(), All initMyJXStatusBar
//                     XXXMouseAdapter()s, & getMyJXStatusBarXXXMouseAdapter()s.
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.*;

import org.jdesktop.swingx.*;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.internationalization.MessagesBundle;

/**
 *
 * @author yccheok
 * @author Dana M. Proctor
 * @version 1.0.7.37.01 03/18/2019
 */
public class MyJXStatusBar extends JXStatusBar {
    
    /** Creates a new instance of MyJStatusBar */
    public MyJXStatusBar() {
        super();
        
        mainLabel = new JLabel();
        progressBar = new JProgressBar();
        progressBar.setStringPainted(false);
        exchangeRateLabel = new JLabel();
        countryLabel = new JLabel();
        imageLabel = new JLabel();

        exchangeRateLabel.setHorizontalAlignment(JLabel.CENTER);
        countryLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        JXStatusBar.Constraint c1 = new JXStatusBar.Constraint(JXStatusBar.Constraint.ResizeBehavior.FILL);
        JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(100);
        JXStatusBar.Constraint c3 = new JXStatusBar.Constraint(50);
        JXStatusBar.Constraint c4 = new JXStatusBar.Constraint(50);
        JXStatusBar.Constraint c5 = new JXStatusBar.Constraint(50);
        
        this.add(mainLabel, c1);
        this.add(progressBar, c2);
        this.add(exchangeRateLabel, c3);
        this.add(countryLabel, c4);
        this.add(imageLabel, c5);
    }
    
    protected void initStatusBar()
    {
       final String message = java.util.ResourceBundle.getBundle(
          "org/yccheok/jstock/data/gui").getString(
             "MainFrame_ConnectingToStockServerToRetrieveStockInformation...");
       final ImageIcon icon = JStock.instance().getImageIcon("/images/16x16/network-connecting.png");
       final String iconMessage = java.util.ResourceBundle.getBundle(
          "org/yccheok/jstock/data/gui").getString("MainFrame_Connecting...");

       setMainMessage(message).setImageIcon(icon, iconMessage)
             .setCountryIcon(JStock.instance().getJStockOptions().getCountry().icon,
                             JStock.instance().getJStockOptions().getCountry().humanString);
    }
    
    protected void initMyJXStatusBarExchangeRateLabelMouseAdapter()
    {
       final MouseAdapter mouseAdapter = this.getMyJXStatusBarExchangeRateLabelMouseAdapter();
       addExchangeRateLabelMouseListener(mouseAdapter);
    }
    
    private MouseAdapter getMyJXStatusBarExchangeRateLabelMouseAdapter()
    {
       return new MouseAdapter()
       {
          @Override
          public void mouseClicked(MouseEvent e)
          {
             if (e.getClickCount() == 2)
             {
                // Popup dialog to select currency exchange option.
                OptionsJDialog optionsJDialog = new OptionsJDialog(JStock.instance(), true);
                optionsJDialog.setLocationRelativeTo(JStock.instance());
                optionsJDialog.set(JStock.instance().getJStockOptions());
                optionsJDialog.select(GUIBundle.getString("OptionsJPanel_Wealth"));
                optionsJDialog.setVisible(true);
             }
          }
       };
    }

    protected void initMyJXStatusBarCountryLabelMouseAdapter()
    {
       final MouseAdapter mouseAdapter = this.getMyJXStatusBarCountryLabelMouseAdapter();
       addCountryLabelMouseListener(mouseAdapter);
    }
    
    private MouseAdapter getMyJXStatusBarCountryLabelMouseAdapter()
    {
       return new MouseAdapter()
       {
          @Override
          public void mouseClicked(MouseEvent e)
          {
             if (e.getClickCount() == 2)
             {
                CountryJDialog countryJDialog = new CountryJDialog(JStock.instance(), true);
                countryJDialog.setLocationRelativeTo(JStock.instance());
                countryJDialog.setCountry(JStock.instance().getJStockOptions().getCountry());
                countryJDialog.setVisible(true);

                final Country country = countryJDialog.getCountry();
                JStock.instance().changeCountry(country);
             }
          }
       };
    }

    protected void initMyJXStatusBarImageLabelMouseAdapter()
    {
       final MouseAdapter mouseAdapter = this.getMyJXStatusBarImageLabelMouseAdapter();
       addImageLabelMouseListener(mouseAdapter);
    }
    
    private MouseAdapter getMyJXStatusBarImageLabelMouseAdapter()
    {
       return new MouseAdapter()
       {
          @Override
          public void mouseClicked(MouseEvent e)
          {
             if (e.getClickCount() == 2)
             {
                // Make sure no other task is running.
                // Use local variable to be thread safe.
                
                final DatabaseTask task = JStock.instance().databaseTask;
                
                if (task != null)
                {
                   if (task.isDone() == true)
                   {
                      // Task is done. But, does it success?
                      boolean success = false;
                      // Some developers suggest that check for isCancelled
                      // before calling get
                      // to avoid CancellationException. Others suggest that just
                      // perform catch
                      // on all Exceptions. I will do it both.
                      if (task.isCancelled() == false)
                      {
                         try
                         {
                            success = task.get();
                         }
                         catch (InterruptedException ex){JStock.log.error(null, ex);}
                         catch (ExecutionException ex){JStock.log.error(null, ex);}
                         catch (CancellationException ex){JStock.log.error(null, ex);}
                      }
                      
                      if (success == false)
                      {
                         // Fail. Automatically reload database for user. Need
                         // not to prompt them message.
                         // As, they do not have any database right now.
                         JStock.instance().initDatabase(true);

                      }
                      else
                      {
                         final int result = JOptionPane.showConfirmDialog(JStock.instance(),
                            MessagesBundle.getString("question_message_perform_server_reconnecting"),
                            MessagesBundle.getString("question_title_perform_server_reconnecting"),
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                         
                         if (result == JOptionPane.YES_OPTION)
                            JStock.instance().initDatabase(false);
                      }
                   }
                   else
                   {
                      // There is task still running. Ask user whether he wants
                      // to stop it.
                      final int result = JOptionPane.showConfirmDialog(JStock.instance(),
                         MessagesBundle.getString("question_message_cancel_server_reconnecting"),
                         MessagesBundle.getString("question_title_cancel_server_reconnecting"),
                         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                      if (result == JOptionPane.YES_OPTION)
                      {
                         synchronized (JStock.instance().databaseTaskMonitor)
                         {
                            JStock.instance().databaseTask.cancel(true);
                            JStock.instance().databaseTask = null;
                         }

                         JStock.instance().setStatusBar(false, GUIBundle.getString("MainFrame_NetworkError"));
                         setImageIcon(JStock.instance().getImageIcon("/images/16x16/network-error.png"),
                            java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
                               "MainFrame_DoubleClickedToTryAgain"));
                      }
                   }
                }
                else
                {
                   // User cancels databaseTask explicitly. (Cancel while
                   // JStock is fetching database from server). Let's read
                   // from disk.
                   JStock.instance().initDatabase(true);
                }
             }
          }
       };
    }

    public MyJXStatusBar setMainMessage(String mainMessage) {
        mainLabel.setText(mainMessage);
        return this;
    }

    public MyJXStatusBar setImageIcon(ImageIcon imageIcon, String imageIconToolTipText) {
        imageLabel.setIcon(imageIcon);        
        imageLabel.setToolTipText(imageIconToolTipText);
        return this;
    }    

    public MyJXStatusBar setCountryIcon(ImageIcon imageIcon, String imageIconToolTipText) {
        countryLabel.setIcon(imageIcon);        
        countryLabel.setToolTipText(imageIconToolTipText);
        return this;
    }
    
    public void addImageLabelMouseListener(MouseListener l) {
        imageLabel.addMouseListener(l);
    }

    public void addCountryLabelMouseListener(MouseListener l) {
        countryLabel.addMouseListener(l);
    }

    /**
     * Add mouse listener to the exchange rate label.
     * 
     * @param l the mouse listener
     */
    public void addExchangeRateLabelMouseListener(MouseListener l) {
        exchangeRateLabel.addMouseListener(l);
    }

    public MyJXStatusBar setProgressBar(boolean newValue) {
        progressBar.setIndeterminate(newValue);
        progressBar.setVisible(newValue);
        return this;
    }

    /**
     * Set the tool tip text on exchange rate label.
     *
     * @param text the tool tip text
     * @return this status bar
     */
    public MyJXStatusBar setExchangeRateToolTipText(String text) {
        exchangeRateLabel.setToolTipText(text);
        return this;
    }

    /**
     * Set the visibility of exchange rate label.
     *
     * @param visible true to make exchange rate label visible. Else false
     * @return this status bar
     */
    public MyJXStatusBar setExchangeRateVisible(final boolean visible) {
        if (visible) {
            // We want to make exchange rate label visible. Only do something
            // if the label is not visible yet.
            if (false == exchangeRateLabel.isVisible()) {
                JXStatusBar.Constraint c = new JXStatusBar.Constraint(50);
                this.add(exchangeRateLabel, c, 2);
                exchangeRateLabel.setVisible(true);
                // Call revalidate followed by repaint to yield a refresh view.
                this.revalidate();
                this.repaint();
            }
        } else {
            // We want to make exchange rate label invisible. Only do something
            // if the label is visible yet.
            if (exchangeRateLabel.isVisible()) {
                this.remove(exchangeRateLabel);
                exchangeRateLabel.setVisible(false);
                // Call revalidate followed by repaint to yield a refresh view.
                this.revalidate();
                this.repaint();
            }
        }
        return this;
    }

    /**
     * Set the exchange rate value.
     *
     * @param exchangeRate the exchange rate value. null to reset
     * @return this status bar
     */
    public MyJXStatusBar setExchangeRate(Double exchangeRate) {
        
        if (exchangeRate == null) {
            exchangeRateLabel.setText("");
            exchangeRateLabel.setIcon(null);            
        } else {
            exchangeRateLabel.setText(org.yccheok.jstock.portfolio.Utils.toExchangeRate(exchangeRate));
            // Determine which icon should be used.
            if (prevExchangeRate != null) {
                final boolean reverse = org.yccheok.jstock.engine.Utils.isFallBelowAndRiseAboveColorReverse();
                if (exchangeRate > prevExchangeRate) {
                    if (reverse) {
                        exchangeRateLabel.setIcon(Icons.RED_UP);
                    } else {
                        exchangeRateLabel.setIcon(Icons.GREEN_UP);
                    }
                } else if (exchangeRate < prevExchangeRate) {
                    if (reverse) {
                        exchangeRateLabel.setIcon(Icons.GREEN_DOWN);
                    } else {
                        exchangeRateLabel.setIcon(Icons.RED_DOWN);
                    }
                } else {
                    // Make no change on current icon display.
                }
            }
        }
        prevExchangeRate = exchangeRate;
        return this;
    }

    // Previous last updated exchange rate. We need this to determine type of
    // icon.
    private Double prevExchangeRate = null;

    private final JLabel mainLabel;
    private final JLabel exchangeRateLabel;
    private final JLabel countryLabel;
    private final JLabel imageLabel;
    private final JProgressBar progressBar;
}
