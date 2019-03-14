//=================================================================
//                       Main_JMenuBar
//=================================================================
//
//    This class is used to constructed the menubar for the
// application frame.
//
//               << Main_JMenuBar.java >>
//
//=================================================================
// Copyright (C) 2019 Dana M. Proctor
// Version 1.7 03/10/2019
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version
// 2 of the License, or (at your option) any later version. This
// program is distributed in the hope that it will be useful, 
// but WITHOUT ANY WARRANTY; without even the implied warranty
// of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
// the GNU General Public License for more details. You should
// have received a copy of the GNU General Public License along
// with this program; if not, write to the Free Software Foundation,
// Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// (http://opensource.org)
//
//=================================================================
// Revision History
// Changes to the code should be documented here and reflected
// in the present version number. Author information should
// also be included with the original copyright author.
//=================================================================
// Version 1.0 Original Main_JMenuBar Class.
//         1.1 Added WatchList Menu, createWatchListMenu() & watchListSelected().
//         1.2 Added Class Instance portfolioMenu. Additional Comments. Implemented
//             createPortfolioMenu(), createOptionsMenu(), & helpMenu(). Method
//             languageMenu() Changed Returned Type & Integrated Into Options Menu.
//             Method fileOpenAction() Integrated jstock.openAsExcelFile() Into
//             Clause, Same for jstock.openAsCSVFile(). Cleaned Up Use of this.
//         1.3 Constructor Added rebuildCountryMenuItems(). Replaced Throughout
//             jstock.getImageIcon() With Direct Production of ImageIcon. Changed
//             rebuildCountryMenuItems() to Private.
//         1.4 Promoted editMenu to Class Istances. Added Static Class Instances
//             EDIT_ADD, EDIT_CLEAR, & EDIT_REFRESH. Method createEditMenu()
//             Assigned Action Commands. Added Method setEditMenuItems().
//         1.5 Method databaseActionPerformed() Replaced Call to jstock.jComboBox
//             setStockInfoDatabase() to jstock.watchListPanel.setStockInfoDatabase().
//         1.6 Removed All Aspects, Import, Calls for Design Feature Trading View.
//         1.7 Methods fileOpenActionPerformed() & fileSaveAsActionPerformed() Removed
//             Processing for XLS Files.
//         
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.apache.commons.logging.Log;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.StockInfoDatabase;
import org.yccheok.jstock.engine.StockNameDatabase;
import org.yccheok.jstock.file.Statements;
import org.yccheok.jstock.gui.portfolio.PortfolioJDialog;
import org.yccheok.jstock.gui.watchlist.WatchlistJDialog;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.internationalization.MessagesBundle;

/**
 * The Main_JMenuBar class is used to constructed the menubar
 * for the application frame.
 * 
 * @author Dana M. Proctor
 * @version 1.7 03/10/2019
 */

public class Main_JMenuBar extends JMenuBar
{
   // Instance & Class Fields.
   private static final long serialVersionUID = -4353456362279909393L;

   private JStock jstock;
   private ResourceBundle bundle;
   private Log log;
   
   private JMenu editMenu;

   private JMenu countryMenu;
   private ButtonGroup recentCountriesButtonGroup;
   private ButtonGroup countriesButtonGroup;

   private ButtonGroup languageButtonGroup;
   private JRadioButtonMenuItem currentSelectedLanguageRadioButton;
   private boolean disableLanguageAction;

   private JMenu watchListMenu;
   private JMenu portfolioMenu;
   
   private static String EDIT_ADD = "Edit Add";
   private static String EDIT_CLEAR = "Edit Clear";
   private static String EDIT_REFRESH = "Edit Refresh";

   private static String ENGLISH = Locale.ENGLISH.getDisplayLanguage(Locale.getDefault());
   private static String SIMPLIFIED_CHINESE = Locale.SIMPLIFIED_CHINESE.getDisplayName(Locale.getDefault());
   private static String TRADITIONAL_CHINESE = Locale.TRADITIONAL_CHINESE.getDisplayName(Locale.getDefault());
   private static String FRENCH = Locale.FRENCH.getDisplayLanguage(Locale.getDefault());
   private static String GERMAN = Locale.GERMAN.getDisplayLanguage(Locale.getDefault());
   private static String ITALIAN = Locale.ITALIAN.getDisplayLanguage(Locale.getDefault());
   
   //==============================================================
   // Main_JMenuBar JMenuBar Constructor.
   //==============================================================

   public Main_JMenuBar(ResourceBundle bundle, Log log)
   {
      this.bundle = bundle;
      this.log = log;

      jstock = JStock.instance();
      disableLanguageAction = false;

      // JMenu Bar for the Frame.
      setBorder(BorderFactory.createEtchedBorder());

      // Creating the File, Edit, Country, Language, Database,
      // WatchList, Portfolio, Options, and Help Menus.
      
      createFileMenu();
      createEditMenu();
      createCountryMenu();
      
      // Integrated Into Options.
      //createLanguageMenu();
      
      createDatabaseMenu();
      createWatchListMenu();
      createPortfolioMenu();
      createOptionsMenu();
      
      // Not Implemented.
      //createLookAndFeelMenu();
      
      createHelpMenu();

      add(Box.createHorizontalGlue());

      // Logo
      ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/16x16/logoIcon.png"));
      JButton logoIconItem = new JButton(logoIcon);
      logoIconItem.setDisabledIcon(logoIcon);
      logoIconItem.setFocusPainted(false);
      logoIconItem.setBorder(BorderFactory.createLoweredBevelBorder());
      add(logoIconItem);
      
      rebuildCountryMenuItems(true);
   }

   //==============================================================
   // Helper Method to create the File Menu.
   //==============================================================

   private void createFileMenu()
   {
      // Method Instances.
      JMenu fileMenu;
      JMenuItem menuItem;

      //===========
      // File Menu

      fileMenu = new JMenu(bundle.getString("MainFrame_File"));
      fileMenu.setFont(fileMenu.getFont().deriveFont(Font.BOLD));

      // Open
      menuItem = new JMenuItem(bundle.getString("MainFrame_Open..."));
      menuItem.setIcon(new ImageIcon(getClass().getResource("/images/16x16/project_open.png")));
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            fileOpenActionPerformed(evt);
         }
      });
      fileMenu.add(menuItem);

      // Save As
      menuItem = new JMenuItem(bundle.getString("MainFrame_SaveAs..."));
      menuItem.setIcon(new ImageIcon(getClass().getResource("/images/16x16/filesave.png")));
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            fileSaveAsActionPerformed(evt);
         }
      });
      fileMenu.add(menuItem);
      fileMenu.add(new JSeparator());

      // Exit
      menuItem = new JMenuItem(bundle.getString("MainFrame_Exit"));
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            fileExitActionPerformed(evt);
         }
      });
      fileMenu.add(menuItem);

      add(fileMenu);
   }

   //==============================================================
   // Helper Method to create the Edit Menu.
   //==============================================================

   private void createEditMenu()
   {
      // Method Instances.
      JMenuItem menuItem;

      //===========
      // Edit Menu

      editMenu = new JMenu(bundle.getString("MainFrame_Edit"));
      editMenu.setFont(editMenu.getFont().deriveFont(Font.BOLD));

      // Add Stocks
      menuItem = new JMenuItem(bundle.getString("MainFrame_AddStocks..."));
      menuItem.setActionCommand(EDIT_ADD);
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            editAddStocksActionPerformed(evt);
         }
      });
      editMenu.add(menuItem);

      // Clear All Stocks
      menuItem = new JMenuItem(bundle.getString("MainFrame_ClearAllStocks"));
      menuItem.setActionCommand(EDIT_CLEAR);
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            jstock.clearAllStocks();
         }
      });
      editMenu.add(menuItem);
      editMenu.add(new JSeparator());

      // Refresh Stock Prices
      menuItem = new JMenuItem(bundle.getString("MainFrame_RefreshStockPrices"));
      menuItem.setActionCommand(EDIT_REFRESH);
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            editRefreshStockPricesActionPerformed(evt);
         }
      });
      editMenu.add(menuItem);

      add(editMenu);
   }

   //==============================================================
   // Helper Method to create the Country Menu.
   //==============================================================

   private void createCountryMenu()
   {
      // Method Instances.

      //===========
      // Country Menu

      recentCountriesButtonGroup = new ButtonGroup();
      countriesButtonGroup = new ButtonGroup();

      countryMenu = new JMenu(bundle.getString("MainFrame_Country"));
      countryMenu.setFont(countryMenu.getFont().deriveFont(Font.BOLD));
      countryMenu.addMenuListener(new MenuListener()
      {
         public void menuCanceled(MenuEvent evt) {}

         public void menuDeselected(MenuEvent evt) {}

         public void menuSelected(MenuEvent evt)
         {
            initRecentCountryMenuItems(countryMenu);
         }
      });
      add(countryMenu);
   }

   //==============================================================
   // Class Method to Initialize the Recent Country Menu.
   //==============================================================

   private void initRecentCountryMenuItems(JMenu countryMenu)
   {
      Enumeration<AbstractButton> e = recentCountriesButtonGroup.getElements();
      boolean hasSeperator = false;

      while (e.hasMoreElements())
      {
         countryMenu.remove(e.nextElement());
         hasSeperator = true;
      }
      // Separator
      if (hasSeperator)
         countryMenu.remove(0);

      recentCountriesButtonGroup = new ButtonGroup();

      int index = 0;

      final Set<Country> countries = new HashSet<>(Utils.getSupportedStockMarketCountries());
      for (final Country country : jstock.getJStockOptions().getRecentCountries())
      {
         if (false == countries.contains(country))
            continue;

         final JMenuItem mi = (JRadioButtonMenuItem) countryMenu.add(
            new JRadioButtonMenuItem(country.humanString, country.icon), index++);

         recentCountriesButtonGroup.add(mi);
         mi.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               jstock.changeCountry(country);
            }
         });

         if (jstock.getJStockOptions().getCountry() == country)
            ((JRadioButtonMenuItem) mi).setSelected(true);
      }

      if (index > 0)
         countryMenu.add(new JSeparator(), index++);
   }

   //==============================================================
   // Helper Method to create the Language Menu.
   //==============================================================

   private JMenu createLanguageMenu()
   {
      // Method Instances.
      JMenu languageMenu;
      JRadioButtonMenuItem radioButtonItem;

      //===========
      // Language Menu

      languageMenu = new JMenu(bundle.getString("MainFrame_Language"));
      languageMenu.setFont(languageMenu.getFont().deriveFont(Font.BOLD));
      languageButtonGroup = new ButtonGroup();

      ActionListener languageActionListener = new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            languageActionPerformed(evt);
         }
      };

      // English
      radioButtonItem = new JRadioButtonMenuItem(ENGLISH);
      radioButtonItem.setActionCommand(ENGLISH);
      radioButtonItem.addActionListener(languageActionListener);
      languageButtonGroup.add(radioButtonItem);
      languageMenu.add(radioButtonItem);

      // Simplified Chinese
      radioButtonItem = new JRadioButtonMenuItem(SIMPLIFIED_CHINESE);
      radioButtonItem.setActionCommand(SIMPLIFIED_CHINESE);
      radioButtonItem.addActionListener(languageActionListener);
      languageButtonGroup.add(radioButtonItem);
      languageMenu.add(radioButtonItem);

      // Traditional Chinese
      radioButtonItem = new JRadioButtonMenuItem(TRADITIONAL_CHINESE);
      radioButtonItem.setActionCommand(TRADITIONAL_CHINESE);
      radioButtonItem.addActionListener(languageActionListener);
      languageButtonGroup.add(radioButtonItem);
      languageMenu.add(radioButtonItem);

      // French
      radioButtonItem = new JRadioButtonMenuItem(FRENCH);
      radioButtonItem.setActionCommand(FRENCH);
      radioButtonItem.addActionListener(languageActionListener);
      languageButtonGroup.add(radioButtonItem);
      languageMenu.add(radioButtonItem);

      // German
      radioButtonItem = new JRadioButtonMenuItem(GERMAN);
      radioButtonItem.setActionCommand(GERMAN);
      radioButtonItem.addActionListener(languageActionListener);
      languageButtonGroup.add(radioButtonItem);
      languageMenu.add(radioButtonItem);

      // Italian
      radioButtonItem = new JRadioButtonMenuItem(ITALIAN);
      radioButtonItem.setActionCommand(ITALIAN);
      radioButtonItem.addActionListener(languageActionListener);
      languageButtonGroup.add(radioButtonItem);
      languageMenu.add(radioButtonItem);

      initLanguageMenuItemsSelection();

      //add(languageMenu);
      
      return languageMenu;
   }

   //==============================================================
   // Class Method to set the initial language selection.
   //==============================================================

   private void initLanguageMenuItemsSelection()
   {
      String selectedLanguage;

      // Please revise Statement's construct code, when adding in new language.
      // So that its language guessing algorithm will work as it is.

      Locale defaultLocale = Locale.getDefault();

      if (Utils.isTraditionalChinese(defaultLocale))
         selectedLanguage = TRADITIONAL_CHINESE;
      else if (Utils.isSimplifiedChinese(defaultLocale))
         selectedLanguage = SIMPLIFIED_CHINESE;
      else if (defaultLocale.getLanguage().equals(Locale.GERMAN.getLanguage()))
         selectedLanguage = GERMAN;
      else if (defaultLocale.getLanguage().equals(Locale.ITALIAN.getLanguage()))
         selectedLanguage = ITALIAN;
      else if (defaultLocale.getLanguage().equals(Locale.FRENCH.getLanguage()))
         selectedLanguage = FRENCH;
      else
         selectedLanguage = ENGLISH;

      Enumeration<AbstractButton> buttonEnumeration = languageButtonGroup.getElements();

      while (buttonEnumeration.hasMoreElements())
      {
         JRadioButtonMenuItem radioButton = (JRadioButtonMenuItem) buttonEnumeration.nextElement();

         if (radioButton.getActionCommand().equals(selectedLanguage))
         {
            radioButton.setSelected(true);
            currentSelectedLanguageRadioButton = radioButton;
            break;
         }
      }
   }

   //==============================================================
   // Helper Method to create the Database Menu.
   //==============================================================

   private void createDatabaseMenu()
   {
      // Method Instances.
      JMenu databaseMenu;
      JMenuItem menuItem;

      //===========
      // Database Menu

      databaseMenu = new JMenu(bundle.getString("MainFrame_Database"));
      databaseMenu.setFont(databaseMenu.getFont().deriveFont(Font.BOLD));

      menuItem = new JMenuItem(bundle.getString("MainFrame_StockDatabase..."));
      menuItem.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            databaseActionPerformed(evt);
         }
      });
      databaseMenu.add(menuItem);

      add(databaseMenu);
   }

   //==============================================================
   // Helper Method to create the WatchList Menu.
   //==============================================================

   private void createWatchListMenu()
   {
      // Method Instances.

      //===========
      // WatchList Menu

      watchListMenu = new JMenu(bundle.getString("MainFrame_Watchlist"));
      watchListMenu.setFont(watchListMenu.getFont().deriveFont(Font.BOLD));

      watchListMenu.addMenuListener(new MenuListener()
      {
         public void menuCanceled(MenuEvent evt) {}

         public void menuDeselected(MenuEvent evt) {}

         public void menuSelected(MenuEvent evt)
         {
            watchListSelected(evt);
         }
      });

      add(watchListMenu);
   }

   //==============================================================
   // Helper Method to create the Portfolio Menu.
   //==============================================================

   private void createPortfolioMenu()
   {
      // Method Instances.

      //===========
      // Portfolio Menu

      portfolioMenu = new JMenu(bundle.getString("MainFrame_Portfolio"));
      portfolioMenu.setFont(portfolioMenu.getFont().deriveFont(Font.BOLD));

      portfolioMenu.addMenuListener(new MenuListener()
      {
         public void menuCanceled(MenuEvent evt) {}

         public void menuDeselected(MenuEvent evt) {}

         public void menuSelected(MenuEvent evt)
         {
            portfolioSelected(evt);
         }
      });

      add(portfolioMenu);
   }

   //==============================================================
   // Helper Method to create the Options Menu.
   //
   // Moved always on top from look and feel here.
   // Moved language selection from separate menu here also.
   //==============================================================

   private void createOptionsMenu()
   {
      // Method Instances.
      JMenu optionsMenu;
      JMenuItem menuItem;
      JCheckBoxMenuItem alwaysOnTopMenuItem;

      //===========
      // Options Menu

      optionsMenu = new JMenu(bundle.getString("MainFrame_Options"));

      menuItem = new JMenuItem(bundle.getString("MainFrame_Options..."));
      menuItem.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent evt)
         {
            OptionsJDialog optionsJDialog = new OptionsJDialog(jstock, true);
            optionsJDialog.setLocationRelativeTo(jstock);
            optionsJDialog.set(jstock.jStockOptions);
            optionsJDialog.setVisible(true);
         }
      });
      optionsMenu.add(menuItem);

      // Always on Top
      optionsMenu.addSeparator();

      alwaysOnTopMenuItem = new JCheckBoxMenuItem(GUIBundle.getString("MainFrame_AlwaysOnTop"));
      alwaysOnTopMenuItem.setSelected(jstock.getJStockOptions().isAlwaysOnTop());
      jstock.setAlwaysOnTop(jstock.getJStockOptions().isAlwaysOnTop());

      alwaysOnTopMenuItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();

            // JStock.this._setAlwaysOnTop(selected);

            // How do you complicate such an easy option
            // into such a mess in the main class. Create
            // your component set it according to your
            // option, act, then add an action to activate,
            // which then acts, saves the option.

            jstock.setAlwaysOnTop(selected);
            jstock.jStockOptions.setAlwaysOnTop(selected);
         }
      });
      optionsMenu.add(alwaysOnTopMenuItem);
      
      // Language
      optionsMenu.addSeparator();
      optionsMenu.add(createLanguageMenu());

      add(optionsMenu);
   }

   //==============================================================
   // Helper Method to create the Look and Feel Menu.
   // I'm not doing this.
   //==============================================================

   /*
   private void createLookAndFeelMenu() {}
   */
   
   //==============================================================
   // Helper Method to create the Help Menu.
   //==============================================================

   private void createHelpMenu()
   {
      // Method Instances.
      JMenu helpMenu;
      JMenuItem menuItem;

      //===========
      // Help Menu
      
      helpMenu = new JMenu(bundle.getString("MainFrame_Help"));

      menuItem = new JMenuItem(bundle.getString("MainFrame_About..."));
      menuItem.addActionListener(new java.awt.event.ActionListener()
      {
          public void actionPerformed(java.awt.event.ActionEvent evt)
          {
             new AboutJDialog(jstock, true).setVisible(true);
          }
      });
      helpMenu.add(menuItem);
      
      add(helpMenu);
   }

   //==============================================================
   // Class method to handle the action events of the menu items.
   //==============================================================

   // File Actions
   private void fileOpenActionPerformed(ActionEvent evt)
   {
      final File file = Utils.promptOpenCSVAndExcelJFileChooser();
      
      if (file == null)
         return;
      
      boolean status = true;
      
      if (Utils.getFileExtension(file).equals("csv"))
      {
         if (jstock.getSelectedComponent() == jstock.watchListPanel)
         {
            // status = jstock.openAsCSVFile(file);
            final Statements statements = Statements.newInstanceFromCSVFile(file);
            status = jstock.openAsStatements(statements, file);
         }
         else if (jstock.getSelectedComponent() == jstock.portfolioManagementJPanel)
            status = jstock.portfolioManagementJPanel.openAsCSVFile(file);
         else
            assert (false);
      }
      else
         assert (false);

      if (false == status)
      {
         final String output = MessageFormat.format(
            MessagesBundle.getString("error_message_bad_file_format_template"), file.getName());
         JOptionPane.showMessageDialog(jstock, output, MessagesBundle.getString("error_title_bad_file_format"),
            JOptionPane.ERROR_MESSAGE);
      }
   }

   private void fileSaveAsActionPerformed(ActionEvent evt)
   {
      String suggestedFileName = "";

      if (jstock.getSelectedComponent() == jstock.watchListPanel)
         suggestedFileName = GUIBundle.getString("MainFrame_Title");
      else if (jstock.getSelectedComponent() == jstock.indicatorScannerJPanel)
         suggestedFileName = GUIBundle.getString("IndicatorScannerJPanel_Title");
      else if (jstock.getSelectedComponent() == jstock.portfolioManagementJPanel)
         suggestedFileName = GUIBundle.getString("PortfolioManagementJPanel_Title");
      else
         assert (false);

      boolean status = true;

      File file = null;
      
      if (jstock.getSelectedComponent() == jstock.watchListPanel
          || jstock.getSelectedComponent() == jstock.indicatorScannerJPanel)
      {
         file = Utils.promptSaveCSVAndExcelJFileChooser(suggestedFileName);

         if (file != null)
         {
            if (Utils.getFileExtension(file).equals("csv"))
            {
               if (jstock.getSelectedComponent() == jstock.watchListPanel)
                  status = jstock.saveAsCSVFile(file, false);
               else if (jstock.getSelectedComponent() == jstock.indicatorScannerJPanel)
                  status = jstock.indicatorScannerJPanel.saveAsCSVFile(file);
               else
                  assert (false);
            }
         }
      }
      else if (jstock.getSelectedComponent() == jstock.portfolioManagementJPanel)
      {
         final Utils.FileEx fileEx = Utils.promptSavePortfolioCSVAndExcelJFileChooser(suggestedFileName);
         if (fileEx != null)
         {
            file = fileEx.file;

            if (Utils.getFileExtension(fileEx.file).equals("csv"))
               status = jstock.portfolioManagementJPanel.saveAsCSVFile(fileEx, false);
         }
      }
      else
         assert (false);

      if (false == status)
      {
         // file will never become null, if status had been changed from true
         // to false.
         assert (file != null);
         final String output = MessageFormat.format(
            MessagesBundle.getString("error_message_nothing_to_be_saved_template"), file.getName());

         JOptionPane.showMessageDialog(jstock, output,
            MessagesBundle.getString("error_title_nothing_to_be_saved"), JOptionPane.ERROR_MESSAGE);
      }
   }

   private void fileExitActionPerformed(ActionEvent evt)
   {
      jstock.setVisible(false);
      jstock.dispose();
   }

   // Edit Actions
   private void editAddStocksActionPerformed(java.awt.event.ActionEvent evt)
   {
      if (jstock.getStockInfoDatabase() == null)
      {
         javax.swing.JOptionPane.showMessageDialog(
            jstock,
            ResourceBundle.getBundle("org/yccheok/jstock/data/messages").getString(
               "info_message_we_havent_connected_to_stock_server"),
            ResourceBundle.getBundle("org/yccheok/jstock/data/messages").getString(
               "info_title_we_havent_connected_to_stock_server"), JOptionPane.INFORMATION_MESSAGE);
         return;
      }

      StockJDialog stockJDialog = new StockJDialog(jstock, true);
      stockJDialog.setLocationRelativeTo(jstock);
      stockJDialog.setVisible(true);
   }

   private void editRefreshStockPricesActionPerformed(java.awt.event.ActionEvent evt)
   {
      jstock.refreshAllRealTimeStockMonitors();
      jstock.refreshRealTimeIndexMonitor();
      jstock.refreshExchangeRateMonitor();

      // Only update UI when there is at least one stock.
      if (jstock.getStocks().isEmpty() == false)
      {
         jstock.setStatusBar(true, GUIBundle.getString("MainFrame_RefreshStockPrices..."));
         jstock.refreshPriceInProgress = true;
      }
   }

   // Language Actions
   //
   // This is a modified, simplified version of the orginal
   // code. This whole mess is not my doing nor would
   // I create a language selector, setting in this manner.
   // It should all be automated, horrible design.

   private void languageActionPerformed(java.awt.event.ActionEvent evt)
   {
      // Method Instances.
      Object evtSource;
      JRadioButtonMenuItem radioButtonItem;
      String actionCommand;

      // Setup.
      evtSource = evt.getSource();

      if (!(evtSource instanceof JRadioButtonMenuItem) || disableLanguageAction)
         return;

      radioButtonItem = (JRadioButtonMenuItem) evtSource;
      actionCommand = radioButtonItem.getActionCommand();

      // Avoid changing language when user selects same language.
      if (radioButtonItem == currentSelectedLanguageRadioButton)
         return;

      if (actionCommand.equals(ENGLISH)
          && false == Utils.hasSpecifiedLanguageFile(jstock.getJStockOptions().getLocale()))
      {
         // User is currently using default langauge. English is our default
         // langauge. Hence, do nothing and return early. This is because we
         // want to avoid from having the following locale.
         //
         // Locale(ENGLISH, FRANCE)
         //
         // This will yield incorrect behavior during currency formatting.
         // We prefer to have
         //
         // Locale(FRANCE, FRANCE)
         //
         // English language will be displayed still, as we do not have
         // FRANCE language file yet.
         //
         return;
      }

      // Do not suprise user with sudden restart. Ask for their permission to
      // do so.
      final int result = JOptionPane.showConfirmDialog(jstock,
         MessagesBundle.getString("question_message_restart_now"),
         MessagesBundle.getString("question_title_restart_now"), JOptionPane.YES_NO_OPTION);

      if (result == JOptionPane.YES_OPTION)
      {
         final Locale locale;

         if (actionCommand.equals(ENGLISH))
            locale = new Locale(Locale.ENGLISH.getLanguage(), Locale.getDefault().getCountry(), Locale
                  .getDefault().getVariant());
         else if (actionCommand.equals(SIMPLIFIED_CHINESE))
         {
            String country = Locale.TRADITIONAL_CHINESE.getCountry().equals(
               Locale.getDefault().getCountry())
               ? Locale.SIMPLIFIED_CHINESE.getCountry()
               : Locale.getDefault().getCountry();
               
            locale = new Locale(Locale.SIMPLIFIED_CHINESE.getLanguage(), country, Locale.getDefault()
                  .getVariant());
         }
         else if (actionCommand.equals(TRADITIONAL_CHINESE))
            locale = new Locale(Locale.TRADITIONAL_CHINESE.getLanguage(),
                                Locale.TRADITIONAL_CHINESE.getCountry(), Locale.getDefault().getVariant());
         else if (actionCommand.equals(FRENCH))
            locale = new Locale(Locale.FRENCH.getLanguage(), Locale.getDefault().getCountry(), Locale
                  .getDefault().getVariant());
         else if (actionCommand.equals(GERMAN))
            locale = new Locale(Locale.GERMAN.getLanguage(), Locale.getDefault().getCountry(), Locale
                  .getDefault().getVariant());
         // Must Italian
         else
            locale = new Locale(Locale.ITALIAN.getLanguage(), Locale.getDefault().getCountry(), Locale
                  .getDefault().getVariant());

         jstock.getJStockOptions().setLocale(locale);
         currentSelectedLanguageRadioButton = radioButtonItem;
         Utils.restartApplication(jstock);
      }
      // return to the previous selection if the user press "no" in the
      // dialog
      else
      {
         disableLanguageAction = true;
         currentSelectedLanguageRadioButton.setSelected(true);
         disableLanguageAction = false;
      }

      log.info("Main_JMenuBar languageActionPerformed() Language:" + actionCommand);
   }

   // Database Actions
   private void databaseActionPerformed(ActionEvent evt)
   {
      // Use local variable to ensure thread safety.
      final StockInfoDatabase stock_info_database = jstock.getStockInfoDatabase();

      if (stock_info_database == null)
      {
         JOptionPane.showMessageDialog(
            jstock,
            ResourceBundle.getBundle("org/yccheok/jstock/data/messages").getString(
               "info_message_there_are_no_database_ready_yet"),
            ResourceBundle.getBundle("org/yccheok/jstock/data/messages").getString(
               "info_title_there_are_no_database_ready_yet"), JOptionPane.INFORMATION_MESSAGE);
         return;
      }

      // stockDatabaseJDialog will be calling mutable methods of
      // stock_info_database.
      StockDatabaseJDialog stockDatabaseJDialog = new StockDatabaseJDialog(jstock, stock_info_database, true);
      stockDatabaseJDialog.setSize(540, 540);
      stockDatabaseJDialog.setLocationRelativeTo(jstock);
      stockDatabaseJDialog.setVisible(true);

      if (stockDatabaseJDialog.getResult() != null)
      {
         assert (stockDatabaseJDialog.getResult() == jstock.getStockInfoDatabase());
         jstock.stockInfoDatabase = stockDatabaseJDialog.getResult();
         jstock.watchListPanel.setStockInfoDatabase(jstock.stockInfoDatabase);
         jstock.indicatorPanel.setStockInfoDatabase(jstock.stockInfoDatabase);
         log.info("saveStockCodeAndSymbolDatabase...");
         saveDatabase();
      }
   }

   private boolean saveDatabase()
   {
      final Country country = jstock.getJStockOptions().getCountry();

      if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(Utils.getUserDataDirectory() + country
                                                               + File.separator + "database") == false)
         return false;

      // Use local variable to ensure thread safety.
      final StockInfoDatabase stock_info_database = jstock.stockInfoDatabase;
      final StockNameDatabase name_database = jstock.stockNameDatabase;

      boolean b0 = true;

      if (name_database != null)
      {
         final Statements statements = Statements.newInstanceFromStockNameDatabase(name_database);
         final File stockNameDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                        + country + File.separator + "database"
                                                        + File.separator + "stock-name-database.csv");
         b0 = statements.saveAsCSVFile(stockNameDatabaseCSVFile);
      }

      if (stock_info_database == null)
         return false;

      // This could happen when OutOfMemoryException happen while fetching stock
      // database information
      // from the server.
      if (stock_info_database.isEmpty())
      {
         log.info("Main_JMenuBar saveDatabase() Database was corrupted.");
         return false;
      }

      final boolean b1 = jstock.saveUserDefinedDatabaseAsCSV(country, stock_info_database);

      // For optimization purpose.
      // symbol_database will always contain UserDefined code and
      // non-UserDefined
      // code. As we may always recover UserDefined code from
      // user-defined-database.xml, we will not save the duplicated information.
      //
      // We will only do it, if stock-info-database.xml is not available,
      // which is very unlikely. Because during application startup, we will
      // always check the existance of stock-info-database.xml.
      boolean b2 = true;
      final File f = org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFile(country);
      if (f.exists() == false)
      {
         b2 = JStock.saveStockInfoDatabaseAsCSV(country, stock_info_database);
      }

      return b0 && b1 && b2;
   }

   // WatchList Actions
   private void watchListSelected(MenuEvent evt)
   {
      watchListMenu.removeAll();
      final List<String> watchlistNames = org.yccheok.jstock.watchlist.Utils.getWatchlistNames();

      final String currentWatchlistName = jstock.getJStockOptions().getWatchlistName();
      final ButtonGroup buttonGroup = new ButtonGroup();

      for (String watchlistName : watchlistNames)
      {
         final JMenuItem mi = (JRadioButtonMenuItem) watchListMenu
               .add(new JRadioButtonMenuItem(watchlistName));
         buttonGroup.add(mi);

         mi.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               final String s = ((JRadioButtonMenuItem) e.getSource()).getText();

               if (false == s.equals(currentWatchlistName))
                  jstock.selectActiveWatchlist(s);
            }

         });
         mi.setSelected(watchlistName.equals(currentWatchlistName));
      }

      watchListMenu.addSeparator();

      final JMenuItem mi = new JMenuItem(GUIBundle.getString("MainFrame_MultipleWatchlist..."),
                                         new ImageIcon(getClass().getResource(
                                            "/images/16x16/stock_timezone.png")));

      mi.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // multipleWatchlists();
            WatchlistJDialog watchlistJDialog = new WatchlistJDialog(jstock, true);
            watchlistJDialog.setLocationRelativeTo(jstock);
            watchlistJDialog.setVisible(true);
         }

      });

      watchListMenu.add(mi);
   }

   // Portfolio Actions
   private void portfolioSelected(javax.swing.event.MenuEvent evt)
   {
      portfolioMenu.removeAll();
      final List<String> portfolioNames = org.yccheok.jstock.portfolio.Utils.getPortfolioNames();

      final String currentPortfolioName = jstock.getJStockOptions().getPortfolioName();
      final ButtonGroup buttonGroup = new ButtonGroup();

      for (String portfolioName : portfolioNames)
      {
         final JMenuItem mi = (JRadioButtonMenuItem) portfolioMenu
               .add(new JRadioButtonMenuItem(portfolioName));
         buttonGroup.add(mi);
         mi.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               final String s = ((JRadioButtonMenuItem) e.getSource()).getText();

               if (false == s.equals(currentPortfolioName))
               {
                  jstock.selectActivePortfolio(s);
               }
            }

         });
         mi.setSelected(portfolioName.equals(currentPortfolioName));
      }

      portfolioMenu.addSeparator();

      final JMenuItem mi = new JMenuItem(GUIBundle.getString("MainFrame_MultiplePortolio..."),
                                         new ImageIcon(getClass().getResource("/images/16x16/calc.png")));
      mi.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // multiplePortfolios();
            PortfolioJDialog portfolioJDialog = new PortfolioJDialog(jstock, true);
            portfolioJDialog.setLocationRelativeTo(jstock);
            portfolioJDialog.setVisible(true);
         }
      });

      portfolioMenu.add(mi);
   }

   //==============================================================
   // Class Method to Rebuild the Countries in the Country menu.
   //==============================================================

   private void rebuildCountryMenuItems(boolean useCache)
   {
      final List<Country> countries = Utils.getSupportedStockMarketCountries(useCache);

      countryMenu.removeAll();

      for (Enumeration<AbstractButton> e = countriesButtonGroup.getElements(); e.hasMoreElements();)
         countriesButtonGroup.remove(e.nextElement());

      Map<Continent, JMenu> menus = new EnumMap<>(Continent.class);
      for (final Continent continent : Continent.values())
      {
         JMenu jMenu = new JMenu(continent.name());
         countryMenu.add(jMenu);
         menus.put(continent, jMenu);
      }

      for (final Country country : countries)
      {
         JMenu jMenu = menus.get(Continent.toContinent(country));

         // Ugly fix on spelling mistake.
         final JMenuItem mi = (JRadioButtonMenuItem) jMenu.add(new JRadioButtonMenuItem(country.humanString,
                                                                                        country.icon));

         countriesButtonGroup.add(mi);
         mi.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               jstock.changeCountry(country);
            }
         });

         if (jstock.getJStockOptions().getCountry() == country)
            ((JRadioButtonMenuItem) mi).setSelected(true);
      }
   }
   
   //==============================================================
   // Class Method to set the edit menu items enable/disable.
   //==============================================================

   protected void setEditMenuItems(boolean addEnabled, boolean clearEnabled, boolean refreshEnabled)
   {
      for (int i = 0; i < editMenu.getItemCount(); i++)
         if (editMenu.getItem(i) instanceof JMenuItem)
         {
            JMenuItem menuItem = (JMenuItem) editMenu.getItem(i);
            
            if (menuItem.getActionCommand().equals(EDIT_ADD))
               menuItem.setEnabled(addEnabled);
            else if (menuItem.getActionCommand().equals(EDIT_CLEAR))
               menuItem.setEnabled(clearEnabled);
            if (menuItem.getActionCommand().equals(EDIT_REFRESH))
               menuItem.setEnabled(refreshEnabled);
            else
            {
               // Do Nothing.
            } 
         }     
   }

   //==============================================================
   // Class Method to set the country in the country menu.
   //==============================================================

   protected void setSelectedCountryItem(Country country)
   {
      for (Enumeration<AbstractButton> e = countriesButtonGroup.getElements(); e.hasMoreElements();)
      {
         AbstractButton button = e.nextElement();
         JRadioButtonMenuItem buttonItem = (JRadioButtonMenuItem) button;

         // Ugly fix on spelling mistake.
         if (country == Country.UnitedState && buttonItem.getText().equals(country.toString() + "s"))
         {
            buttonItem.setSelected(true);
            break;
         }

         if (buttonItem.getText().equals(country.toString()))
         {
            buttonItem.setSelected(true);
            break;
         }
      }
   }
}