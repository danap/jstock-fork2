/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.20 03/13/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock Gui JStock Class.
//         1.0.7.37.01 02/14/2019 Formatted, Organized Imports. Began Organizing Code
//                                to be Rebuilt. DMP.
//         1.0.7.37.02 02/15/2019 Reverted Most of 1.0.7.37.01 Except Organized Imports.
//                                Commented Setting System Property for Logging. Kept Most
//                                of the Commenting in main() to Not Activate Parts of the
//                                Code Not Desired.
//         1.0.7.37.03 02/20/2019 Method loadUserDefinedDatabaseCSVFile() Added Check File
//                                exists(). Removed the main() Starting a New Thread. Removed
//                                main() GA Tracking. Initial Base JStock Fork Working Project.
//         1.0.7.37.04 02/22/2019 Added SerialVersionUIDs as Needed to Remove Warnings in
//                                IDE. Method save/loadToCloud() Parameterized SwingWorker.
//                                Method getTableModelListener() Commented Unused Instances,
//                                firstRow, lastRow, mColIndex. Method displayStockNews()
//                                Just Instantiated StockNewsJFrame Rather Then Assigning too
//                                Unused Instance stockNewsJFrame. Method getMyJTablePopup()
//                                Commented Unused Instances row, modelIndex, & stock. Method
//                                getUserDefinedPair() Parameterized pairs. Commented Class
//                                Instances smileIcon & smileGrayIcon, Unused. Parameterized
//                                Class Instance jComboBox1.
//         1.0.7.37.05 02/23/2019 Method initComponents() Menu Items File | Open/Save From/To
//                                Cloud, Help | Export, Android/WeNote | Download Removed.
//                                Commented Associated Class Instances & Actions. Commented
//                                Methods saveToCloud() & loadFromCloud().
//         1.0.7.37.06 02/24/2019 Method initComponents() Menu Items Help | Online Help, Keyboard
//                                Shortcuts, Calculator, Donate, & Contribute Removed. Commented
//                                Associated Class Instances & Actions.
//         1.0.7.37.07 02/24/2019 Reorganized Imports. Method init() Commented createSystemTray().
//                                Method initComponents() addWindowListener Commented formWindow
//                                Deiconified() & Modified Some of Main Containers Borders &
//                                Layouts. This is Horrible Coding to Dig Through, Added Some
//                                Mimimal Commenting to Differentiate initComponents(). Method
//                                formWindowClosed() Commented _trayIcon Instantiation. Commented
//                                formWindowDeiconified(), Not Used.  Commented createSystemTray
//                                Icon(). Method update() Commented displayPopupMessage().
//         1.0.7.37.08 02/25/2019 Commented Method reloadAfterDownloadFromCloud(). Added Class
//                                Instance VERSION.
//         1.0.7.37.09 02/25/2019 Commented Method getBestCountryAfterDownloadFromCloud(). Organized
//                                Imports.
//         1.0.7.37.10 02/27/2019 Method init() Commented createIconsAndToolTipTextForJTabbedPane().
//                                Added Class Instances watchListTabIndex, stockIndicatorTabIndex,
//                                indicatorScannerTabIndex, portfolioTabIndex. Method initComponents()
//                                Use of watchListTabIndex for jTabbedPane1, jPanel8, WatchList,
//                                Set Icon & Tooltip. Also Same Method Minor UI Changes. Methods
//                                selectActivePortfolio(), selectActiveWatchList(),  &
//                                watchListNavigation() Use of New Class Instances to Select jTabbedPane1
//                                Tabs. Methods createPortfolioManagementJPanel(), createStockIndicatorEditor(),
//                                createStockIndicatorScannerJPanel() Use of New Class Instances to
//                                Assign jTabbedPane1 Indexes to Panels.
//         1.0.7.37.11 02/28/2019 First Step in Major Cleanup of This Top Level Main Class. After
//                                Several Weeks, Determined, This May be the Only Path to Coerce a
//                                Modification of the Application. Its Horrible Coding, Maybe Purposeful
//                                or Posssibly Part Because of the Use of NetBeans. Thought Perhaps
//                                I Could Keep it Intact. Cleaned Unused Imports. Changed jPanel8 to
//                                New Class WatchListJPanel. Commented Class Instances jScrollPane1,
//                                jTable1, jPanel1, jLabel1, jPanel10, & jPanel3. Method initComponents()
//                                Commented Aspects of Creating Watch List Tab. Referenced All Calls
//                                to Watch List Tab & Its Components to watchListJPanel. Commented
//                                Methods updateDynamicChart(), jTable1keyPressed(), deleteSelectedTableRow(),
//                                getTableModelListener(), getSelectedStock(), getMyJTablePopupMenu(),
//                                Class TableMouseAdapter, Class ColumnHeaderToolTips, getDynamicChart
//                                MouseAdapter, & Class TableKeyEventListener. Made Various Class
//                                Methods & Instances Protected.
//         1.0.7.37.12 03/01/2019 Cleaned Out Commented Code.
//         1.0.7.37.13 03/05/2019 Added Class Instance Main_JMenuBar, menuBar. Method init() Commented
//                                createLookAndFeelMenu(), rebuildCountryMenuItems(), initAlwaysOnTop(),
//                                & initLanguageMenuItemsSelection(). Method initComponents() Font
//                                Change to jTabbedPane1, Commented All Aspects of MenuBar Creation
//                                & Replaced With menuBar. Changed Several Methods From Private to
//                                Protected, To be Reviewed Again. Commented rebuildCountryMenuItems(),
//                                getMyIconImage(), & createLookAndFeelMenu(). Method changeCountry()
//                                Use of menuBar.setSelectedCountryItem(). Changed Several Class
//                                Instances to Protected.
//         1.0.7.37.14 03/06/2019 Cleaned Out Commented Code and All Unused Methods and Instances,
//                                Mainly From 1.0.7.37.13. Changed Class Instance jPanel6 to
//                                mainMarketPanel. Over 5000 Lines of Code to Now Around 3200 &
//                                Still All the Crap Not Cleaned Out. Horribe Design, Coding.
//         1.0.7.37.15 03/07/2019 Removed Remaining Class Instances jMenuItemX, Commented jComboBox1.
//                                Commented Methods requestFocusOnJComboBox(), dettachAllAndStop
//                                AutoCompleteComboBox(), Class ChangeLookAndFeelAction, setLookAnd
//                                Feel(), highLightStock(), getDispObserver(), & getStockInfoObserver().
//                                Most of These Replaced by Calls to watchListPanel, Where AutoComplete
//                                ComboBox Moved to, From initComponents(). Method handleJTabbedPane
//                                StateChanged() Replaced menuItem Actions to Call to menuBar.setEdit
//                                MenuItems(). Removed All the LookAndFeel Code in main(). Method
//                                initAjaxProvider() Used watchListPanel.setGreedyEnabled().
//         1.0.7.37.16 03/08/2019 Cleaned Out Commented Code From 1.0.7.37.15, Unused Imports. Left
//                                Some Aspects, Commented, Dynamic Charts Though Most Moved to
//                                WatchListJPanel.
//         1.0.7.37.17 03/09/2019 Commented Imports of TradingJPanel, TradingView & DriveWealthBundle.
//                                Method main() Minor Change in Original to Call watchListPanel.request
//                                FocusOnJComboBox(). Commented Method getTradingJPanel(). Method
//                                updateStatusBarWithLastUpdateDate() & getBestStatusBarMessage()
//                                Commented Conditional Aspect of tradingJPanel. Organized Class
//                                Instances, Removed jComboBox1, Commented tradingJPanel.
//         1.0.7.37.18 03/11/2019 Commented Import Alert GoogleMail, Associated Processing in update()
//                                jStockOptions.isSendEmail(). Commented Methods open/saveAsExcelFile().
//                                Changed Method setStatusBar() to Protected. Method update(RealTimeStock
//                                Monitor) Alert Conditional jStockOptions.isSendMail().
//         1.0.7.37.19 03/11/2019 Cleaned Out Commented Code, Imports From 1.0.7.37.18. Method update()
//                                Removed jStockOptions.isPopupMessage(), Slated for Removal. Minor
//                                Formatting Changes for Comments in Preparation to Format Code. Removed
//                                Class Instance emailAlertPool.
//         1.0.7.37.20 03/13/2019 Method init() Commented initExtraDatas(), Changed initWatchList() &
//                                initJXLayerOnComboBox() to watchListPanel. Moved watchListNavigation()
//                                & portfolioNavigation() to initKeyBindings() Appropriate actionPerformed()
//                                in Keys. Commented initJXLayerOnComboBox() & initExtraDatas(). Moved
//                                initWatchList() aka initCSVWatchList() to watchListPanel. Made Class
//                                Instance timestamp Protected.
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.analysis.Indicator;
import org.yccheok.jstock.engine.Board;
import org.yccheok.jstock.engine.Code;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.Duration;
import org.yccheok.jstock.engine.Factories;
import org.yccheok.jstock.engine.GoogleCodeDatabaseRunnable;
import org.yccheok.jstock.engine.IEXStockInfoDatabaseRunnable;
import org.yccheok.jstock.engine.Index;
import org.yccheok.jstock.engine.Industry;
import org.yccheok.jstock.engine.Market;
import org.yccheok.jstock.engine.Pair;
import org.yccheok.jstock.engine.PriceSource;
import org.yccheok.jstock.engine.RealTimeIndexMonitor;
import org.yccheok.jstock.engine.RealTimeStockMonitor;
import org.yccheok.jstock.engine.Stock;
import org.yccheok.jstock.engine.StockHistoryMonitor;
import org.yccheok.jstock.engine.StockHistorySerializer;
import org.yccheok.jstock.engine.StockHistoryServer;
import org.yccheok.jstock.engine.StockInfo;
import org.yccheok.jstock.engine.StockInfoDatabase;
import org.yccheok.jstock.engine.StockNameDatabase;
import org.yccheok.jstock.engine.Symbol;
import org.yccheok.jstock.file.Atom;
import org.yccheok.jstock.file.GUIBundleWrapper;
import org.yccheok.jstock.file.Statement;
import org.yccheok.jstock.file.Statements;
import org.yccheok.jstock.file.UserDataDirectory;
import org.yccheok.jstock.file.UserDataFile;
import org.yccheok.jstock.gui.charting.ChartJDialog;
import org.yccheok.jstock.gui.charting.ChartJDialogOptions;
import org.yccheok.jstock.gui.charting.DynamicChart;
import org.yccheok.jstock.gui.news.StockNewsJFrame;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.internationalization.MessagesBundle;
import org.yccheok.jstock.network.ProxyDetector;

import com.google.api.client.auth.oauth2.Credential;

/**
 *
 * @author  doraemon
 * @author Dana M. Proctor
 * @version 1.0.7.37.20 03/13/2019
 */

public class JStock extends javax.swing.JFrame {

   private static final long serialVersionUID = 3554990056522905135L;
   public static final String VERSION = "1.0.7.37.20";
   
   public static final class CSVWatchlist {
        public final TableModel tableModel;
        
        private CSVWatchlist(TableModel tableModel) {
            if (tableModel == null) {
                throw new java.lang.IllegalArgumentException();
            }
            this.tableModel = tableModel;
        }
        
        public static CSVWatchlist newInstance(TableModel tableModel) {
            return new CSVWatchlist(tableModel);
        }
    }
    
    // Comment out, to avoid annoying log messages during debugging.
    //static { System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog"); }
    
    /** Creates new form MainFrame */

    // Private constructor is sufficient to suppress unauthorized calls to the constructor
    private JStock()
    {
    }

    /**
     * Initialize this MainFrame based on the JStockOptions.
     */
    private void init() {        
        initComponents();

        //createLookAndFeelMenuItems();
        //rebuildCountryMenuItems(true);

        createStockIndicatorEditor();
        createIndicatorScannerJPanel();
        createPortfolioManagementJPanel();

        //createIconsAndToolTipTextForJTabbedPane();

        //this.createSystemTrayIcon();

        this.initPreloadDatabase(false);
        this.initUIOptions();
        //this.initExtraDatas();
        this.initStatusBar();
        this.initMarketJPanel();
        this.initTableHeaderToolTips();
        this.initMyJXStatusBarExchangeRateLabelMouseAdapter();
        this.initMyJXStatusBarCountryLabelMouseAdapter();
        this.initMyJXStatusBarImageLabelMouseAdapter();
        this.initStockInfoDatabaseMeta();
        this.initGoogleCodeDatabaseRunnable();
        this.initIEXStockInfoDatabaseRunnable();
        this.initDatabase(true);
        this.initAjaxProvider();
        this.initRealTimeIndexMonitor();
        this.initLatestNewsTask();
        this.initExchangeRateMonitor();
        this.initRealTimeStockMonitor();
        this.watchListPanel.initWatchlist();
        this.initAlertStateManager();
        this.initDynamicCharts();
        this.initDynamicChartVisibility();
        //this.initAlwaysOnTop();
        this.initStockHistoryMonitor();
        this.initOthersStockHistoryMonitor();
        this.initGUIOptions();
        this.initChartJDialogOptions();
        //this.initLanguageMenuItemsSelection();        
        //this.initJXLayerOnJComboBox();
        this.watchListPanel.addAutoCompleteComboBox();
        this.initKeyBindings();

        // Turn to the last viewed page.
        final int lastSelectedPageIndex = this.getJStockOptions().getLastSelectedPageIndex();
        if (this.jTabbedPane1.getTabCount() > lastSelectedPageIndex) {
            this.jTabbedPane1.setSelectedIndex(lastSelectedPageIndex);
        }

        // setSelectedIndex will not always trigger jTabbedPane1StateChanged,
        // if the selected index is same as current page index. However, we are
        // expecting jTabbedPane1StateChanged to suspend/resume
        // PortfolioManagementJPanel's RealtTimeStockMonitor and MainFrame's
        // CurrencyExchangeMonitor, in order to preserve network resource. Hence,
        // we need to call handleJTabbedPaneStateChanged explicitly.
        handleJTabbedPaneStateChanged(this.jTabbedPane1);

        // Restore previous size and location.
        JStockOptions.BoundsEx boundsEx = jStockOptions.getBoundsEx();
        if (boundsEx == null) {
            // First time. Maximize it.
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
        } else {
            if ((boundsEx.extendedState & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
                this.setExtendedState(Frame.MAXIMIZED_BOTH);
            } else {
                this.setBounds(boundsEx.bounds);
            }
        }
        
        installShutdownHook();
        
        BackwardCompatible.removeGoogleCodeDatabaseIfNecessary();
    }

    private void initKeyBindings() {
        KeyStroke watchlistNavigationKeyStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK);
        KeyStroke portfolioNavigationKeyStroke = KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(watchlistNavigationKeyStroke, "watchlistNavigation");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(portfolioNavigationKeyStroke, "portfolioNavigation");
        getRootPane().getActionMap().put("watchlistNavigation", new AbstractAction() {
            private static final long serialVersionUID = 5097111889683602733L;
            @Override
            public void actionPerformed( ActionEvent e )
            {
               if (JStock.instance().getSelectedComponent() != watchListPanel) {
                  // The page is not active. Make it active.
                  JStock.this.jTabbedPane1.setSelectedIndex(watchListTabIndex);
                  return;
              }
              
              final java.util.List<String> watchlistNames = org.yccheok.jstock.watchlist.Utils.getWatchlistNames();
              final int size = watchlistNames.size();
              if (size <= 1) {
                  // Nothing to navigate.
                  return;
              }
              final String currentWatchlistName = JStock.instance().getJStockOptions().getWatchlistName();
              
              int index = 0;
              
              for (; index < size; index++) {
                  if (watchlistNames.get(index).equals(currentWatchlistName)) {
                      index++;
                      if (index >= size) index = 0;
                      break;
                  }
              }
              JStock.instance().selectActiveWatchlist(watchlistNames.get(index));
            }
        });
        getRootPane().getActionMap().put("portfolioNavigation", new AbstractAction() {
            private static final long serialVersionUID = -4296921725513282997L;
            @Override
            public void actionPerformed( ActionEvent e )
            {
               if (JStock.instance().getSelectedComponent() != JStock.instance().portfolioManagementJPanel) {
                  // The page is not active. Make it active.
                  JStock.this.jTabbedPane1.setSelectedIndex(portfolioTabIndex);
                  return;
              }
              
              final java.util.List<String> portfolioNames = org.yccheok.jstock.portfolio.Utils.getPortfolioNames();
              final int size = portfolioNames.size();
              if (size <= 1) {
                  // Nothing to navigate.
                  return;
              }        
              final String currentPortfolioName = JStock.instance().getJStockOptions().getPortfolioName();
              
              int index = 0;        
              for (; index < size; index++) {
                  if (portfolioNames.get(index).equals(currentPortfolioName)) {
                      index++;
                      if (index >= size) index = 0;
                      break;
                  }
              }
              JStock.instance().selectActivePortfolio(portfolioNames.get(index));
            }
        });        
    }
    
    /*
    private void watchlistNavigation() {
        if (this.getSelectedComponent() != watchListPanel) {
            // The page is not active. Make it active.
            JStock.this.jTabbedPane1.setSelectedIndex(watchListTabIndex);
            return;
        }
        
        final java.util.List<String> watchlistNames = org.yccheok.jstock.watchlist.Utils.getWatchlistNames();
        final int size = watchlistNames.size();
        if (size <= 1) {
            // Nothing to navigate.
            return;
        }
        final String currentWatchlistName = this.getJStockOptions().getWatchlistName();
        
        int index = 0;
        
        for (; index < size; index++) {
            if (watchlistNames.get(index).equals(currentWatchlistName)) {
                index++;
                if (index >= size) index = 0;
                break;
            }
        }
        this.selectActiveWatchlist(watchlistNames.get(index));
    }
    
    private void portfolioNavigation() {
        if (this.getSelectedComponent() != this.portfolioManagementJPanel) {
            // The page is not active. Make it active.
            JStock.this.jTabbedPane1.setSelectedIndex(portfolioTabIndex);
            return;
        }
        
        final java.util.List<String> portfolioNames = org.yccheok.jstock.portfolio.Utils.getPortfolioNames();
        final int size = portfolioNames.size();
        if (size <= 1) {
            // Nothing to navigate.
            return;
        }        
        final String currentPortfolioName = this.getJStockOptions().getPortfolioName();
        
        int index = 0;        
        for (; index < size; index++) {
            if (portfolioNames.get(index).equals(currentPortfolioName)) {
                index++;
                if (index >= size) index = 0;
                break;
            }
        }
        this.selectActivePortfolio(portfolioNames.get(index));
    }
    */
    
    // Register a hook to save app settings when quit via the app menu.
    // This is in Mac OSX only.   
    // http://sourceforge.net/tracker/?func=detail&aid=3490453&group_id=202896&atid=983418
    private void installShutdownHook() {
        if (Utils.isMacOSX()) {
            // Triggered by command + Q
            Runnable runner = new Runnable() {
                @Override
                public void run() {
                    if (isFormWindowClosedCalled) {
                        AppLock.unlock();
                        return;
                    }
                    
                    // 1) Do not call formWindowClosed directly, as accessing UI
                    // will cause "hang".
                    // 2) Calling system.exit will cause "hang" too.
                    JStock.this.save();

                    if (JStock.this.needToSaveUserDefinedDatabase) {
                        // We are having updated user database in memory.
                        // Save it to disk.
                        JStock.this.saveUserDefinedDatabaseAsCSV(jStockOptions.getCountry(), stockInfoDatabase);
                    }
                    
                    AppLock.unlock();
                }
            };
            Runtime.getRuntime().addShutdownHook(new Thread(runner, "Window Prefs Hook"));
        } else {
            Runnable runner = new Runnable() {
                @Override
                public void run() {
                    AppLock.unlock();
                }
            };
            Runtime.getRuntime().addShutdownHook(new Thread(runner, "Window Prefs Hook"));
        }
    }

    /**
     * MainFrameHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to MainFrameHolder.INSTANCE, not before.
     */
    private static class MainFrameHolder {
        private final static JStock INSTANCE = new JStock();
    }

    /**
     * Returns MainFrame as singleton.
     * 
     * @return MainFrame as singleton
     */
    public static JStock instance() {
        return MainFrameHolder.INSTANCE;
    }

    // Install JXLayer around JComboBox.
    // It is used to display busy indicator.
    /*
    private void initJXLayerOnJComboBox() {
        watchListPanel.addAutoCompleteComboBox();
    }
    */

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane1.setFont(jTabbedPane1.getFont().deriveFont(
           jTabbedPane1.getFont().getStyle() | java.awt.Font.BOLD,
           jTabbedPane1.getFont().getSize() + 2));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        setTitle(bundle.getString("MainFrame_Application_Title")); // NOI18N
        setIconImage(new ImageIcon(getClass().getResource("/images/128x128/chart.png")).getImage());
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                //formWindowDeiconified(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });
        
        // Start Adding Main Components
        getContentPane().setLayout(new java.awt.BorderLayout());

        // North Market Panel
        mainMarketPanel = new javax.swing.JPanel();
        mainMarketPanel.setLayout(new java.awt.GridLayout(1, 1));
        mainMarketPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
           javax.swing.BorderFactory.createRaisedSoftBevelBorder(),
           javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0)));
        getContentPane().add(mainMarketPanel, java.awt.BorderLayout.NORTH);

        // Center Tabbed Pane
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        watchListPanel = new WatchListJPanel();
        watchListTabIndex = jTabbedPane1.getTabCount();
        jTabbedPane1.addTab(bundle.getString("MainFrame_Title"), watchListPanel); // NOI18N
        jTabbedPane1.setIconAt(watchListTabIndex, this.getImageIcon("/images/16x16/stock_timezone.png"));
        jTabbedPane1.setToolTipTextAt(watchListTabIndex, java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_WatchYourFavoriteStockMovement"));
        
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        
        // South Status Bar
        javax.swing.JPanel statusBarPanel = new javax.swing.JPanel();
        statusBarPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
        statusBarPanel.setLayout(new java.awt.GridLayout(1, 1));
        statusBarPanel.add(statusBar);
        getContentPane().add(statusBarPanel, java.awt.BorderLayout.SOUTH);

        // MenuBar
        menuBar = new Main_JMenuBar(bundle, log);
        setJMenuBar(menuBar);
        
        setSize(new java.awt.Dimension(952, 478));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    protected boolean openAsCSVFile(File file) {
        final Statements statements = Statements.newInstanceFromCSVFile(file);
        return this.openAsStatements(statements, file);
    }

    public boolean openAsStatements(Statements statements, File file) {
        assert(statements != null);
        
        final GUIBundleWrapper guiBundleWrapper = statements.getGUIBundleWrapper();
        
        if (statements.getType() == Statement.Type.RealtimeInfo) {
            final int size = statements.size();
            for (int i = 0; i < size; i++) {
                final org.yccheok.jstock.file.Statement statement = statements.get(i);
                final String codeStr = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Code"));
                final String symbolStr = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Symbol"));
                final Double fallBelowDouble = statement.getValueAsDouble(guiBundleWrapper.getString("MainFrame_FallBelow"));
                final Double riseAboveDouble = statement.getValueAsDouble(guiBundleWrapper.getString("MainFrame_RiseAbove"));
                if (codeStr.length() > 0 && symbolStr.length() > 0) {
                    final Stock stock = org.yccheok.jstock.engine.Utils.getEmptyStock(Code.newInstance(codeStr), Symbol.newInstance(symbolStr));
                    final StockAlert stockAlert = new StockAlert().setFallBelow(fallBelowDouble).setRiseAbove(riseAboveDouble);
                    watchListPanel.addStockToTable(stock, stockAlert);
                    realTimeStockMonitor.addStockCode(Code.newInstance(codeStr));
                }
            }
            realTimeStockMonitor.startNewThreadsIfNecessary();
            realTimeStockMonitor.refresh();
        } else if (statements.getType() == Statement.Type.StockIndicatorScanner) {
            // Some users request of having Stock Watchlist able to load stocks
            // saved from Stock Indicators Scanner.
            final int size = statements.size();
            for (int i = 0; i < size; i++) {
                final org.yccheok.jstock.file.Statement statement = statements.get(i);
                final String codeStr = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Code"));
                final String symbolStr = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Symbol"));
                if (codeStr.length() > 0 && symbolStr.length() > 0) {
                    final Stock stock = org.yccheok.jstock.engine.Utils.getEmptyStock(Code.newInstance(codeStr), Symbol.newInstance(symbolStr));
                    watchListPanel.addStockToTable(stock);
                    realTimeStockMonitor.addStockCode(Code.newInstance(codeStr));
                }
            }
            realTimeStockMonitor.startNewThreadsIfNecessary();
            realTimeStockMonitor.refresh();
        } else if (statements.getType() == Statement.Type.PortfolioManagementBuy || statements.getType() == Statement.Type.PortfolioManagementSell || statements.getType() == Statement.Type.PortfolioManagementDeposit || statements.getType() == Statement.Type.PortfolioManagementDividend) {
            // Open using other tabs. 
            return this.portfolioManagementJPanel.openAsStatements(statements, file);
        } else {
            return false;
        }
        return true;
    }

    public RealTimeStockMonitor getRealTimeStockMonitor() {
        return realTimeStockMonitor;
    }

    private void handleJTabbedPaneStateChanged(JTabbedPane pane) {
        
        if (menuBar == null)
           return;
       
        // MainFrame
        // Edit Menu (Add Stocks, Clear All Stocks, Refresh Stock Prices)
        
        if (pane.getSelectedComponent() == watchListPanel) {
            menuBar.setEditMenuItems(true, true, true);
            watchListPanel.requestFocusOnJComboBox();
        }
        else if (pane.getSelectedComponent() == this.indicatorPanel) {
            menuBar.setEditMenuItems(false, false, false);
        }
        else if(pane.getSelectedComponent() == this.indicatorScannerJPanel
                || pane.getSelectedComponent() == this.portfolioManagementJPanel) {
            menuBar.setEditMenuItems(false,  false, true);
        }
        
        if (this.isStatusBarBusy == false) {
            this.setStatusBar(false, this.getBestStatusBarMessage());
        }
    }

    // Policy : Each pane should have their own real time stock monitoring.
    //
    //          Each pane should share history monitoring with main frame, 
    //          for optimized history retrieving purpose.
    //
    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        JTabbedPane pane = (JTabbedPane)evt.getSource();
        handleJTabbedPaneStateChanged(pane);
    }//GEN-LAST:event_jTabbedPane1StateChanged

    /**
     * Returns JStock options of this main frame.
     * @return JStock options of this main frame
     */
    public JStockOptions getJStockOptions() {
        return this.jStockOptions;
    }
    
    public UIOptions getUIOptions() {
        return this.uiOptions;
    }
    
    /**
     * Returns the chart dialog options of this main frame.
     * @return the chart dialog options of this main frame
     */
    public ChartJDialogOptions getChartJDialogOptions() {
        return this.chartJDialogOptions;
    }

    /**
     * Save the entire application settings.
     */
    public void save() {
        // Save the last viewed page.
        this.getJStockOptions().setLastSelectedPageIndex(this.jTabbedPane1.getSelectedIndex());

        // Save current window size and position.
        JStockOptions.BoundsEx boundsEx = new JStockOptions.BoundsEx(this.getBounds(), this.getExtendedState());
        this.getJStockOptions().setBoundsEx(boundsEx);
        
        jStockOptions.setApplicationVersionID(Utils.getApplicationVersionID());

        this.saveJStockOptions();
        this.saveUIOptions();
        this.saveGUIOptions();
        this.saveChartJDialogOptions();
        this.saveWatchlist();
        this.indicatorPanel.saveAlertIndicatorProjectManager();
        this.indicatorPanel.saveModuleIndicatorProjectManager();
        this.portfolioManagementJPanel.savePortfolio();
    }

    // windowClosing
    // Invoked when the user attempts to close the window from the window's system menu.
    //
    // windowClosed
    // Invoked when a window has been closed as the result of calling dispose on the window.
    //
    /* Dangerous! We didn't perform proper clean up, because we do not want
     * to give user perspective that our system is slow. But, is it safe
     * to do so?
     */
    
    // Remember to revise installShutdownHook
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        isFormWindowClosedCalled = true;
        
        try {
            ExecutorService _stockInfoDatabaseMetaPool = this.stockInfoDatabaseMetaPool;
            this.stockInfoDatabaseMetaPool = null;
            if (_stockInfoDatabaseMetaPool != null) {
                _stockInfoDatabaseMetaPool.shutdownNow();
            }

            ExecutorService _singleThreadExecutor = this.singleThreadExecutor;
            this.singleThreadExecutor = null;
            if (_singleThreadExecutor != null) {
                _singleThreadExecutor.shutdownNow();
            }

            // Always be the first statement. As no matter what happen, we must
            // save all the configuration files.
            this.save();

            if (this.needToSaveUserDefinedDatabase) {
                // We are having updated user database in memory.
                // Save it to disk.
                this.saveUserDefinedDatabaseAsCSV(jStockOptions.getCountry(), stockInfoDatabase);
            }
            
            watchListPanel.dettachAllAndStopAutoCompleteJComboBox();
            this.indicatorPanel.dettachAllAndStopAutoCompleteJComboBox();
            
            log.info("latestNewsTask stop...");

            if (this.latestNewsTask != null)
            {
                this.latestNewsTask.cancel(true);
            }

            _stockInfoDatabaseMetaPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            
            // We suppose to call shutdownAll to clean up all network resources.
            // However, that will cause Exception in other threads if they are still using httpclient.
            // Exception in thread "Thread-4" java.lang.IllegalStateException: Connection factory has been shutdown.
            //
            // MultiThreadedHttpConnectionManager.shutdownAll();

            log.info("Widnow is closed.");
        }
        catch (Exception exp) {
            log.error("Unexpected error while trying to quit application", exp);
        }

        Platform.exit();
        
        // All the above operations are done within try block, to ensure
        // System.exit(0) will always be called.
        //
        // Final clean up.
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        // Calling setVisible(false) will cause modal dialog box to be unblocked
        // for JDialog.setVisible(true). This will happen in Linux system where
        // user are allowed to minimize window even there is a modal JDialog box
        // We have no solution at current moment.
        //

        if (Utils.isWindows())
        {
            this.setVisible(false);
        }
    }//GEN-LAST:event_formWindowIconified

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        this.watchListPanel.getTable().getSelectionModel().clearSelection();
        this.indicatorScannerJPanel.clearTableSelection();
        this.portfolioManagementJPanel.clearTableSelection();
        this.watchListPanel.updateDynamicChart(null);
    }//GEN-LAST:event_formMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (this.indicatorPanel.promptToSaveSignificantEdits()) {
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * Activate specified watchlist.
     *
     * @param watchlist Watchlist name
     */
    public void selectActiveWatchlist(String watchlist) {
        assert(SwingUtilities.isEventDispatchThread());
        // Save current watchlist.
        JStock.this.saveWatchlist();
        // Save current GUI options.
        // Do not call MainFrame.this.saveGUIOptions() (Pay note on the underscore)
        // , as that will save portfolio's and indicator scanner's as well.
        JStock.this._saveGUIOptions();
        // And switch to new portfolio.
        JStock.this.getJStockOptions().setWatchlistName(watchlist);
        JStock.this.watchListPanel.initWatchlist();
        // I guess user wants to watch the current active watchlist right now.
        // We will help him to turn to the stock watchlist page.
        JStock.this.jTabbedPane1.setSelectedIndex(watchListTabIndex);

        // No matter how, just stop progress bar, and display best message.
        this.setStatusBar(false, this.getBestStatusBarMessage());
    }

    /**
     * Activate specified portfolio.
     *
     * @param portfolio Portfolio name
     */
    public void selectActivePortfolio(String portfolio) {
        assert(SwingUtilities.isEventDispatchThread());
        // Save current portfolio.
        JStock.this.portfolioManagementJPanel.savePortfolio();
        // Save current GUI options.
        JStock.this.portfolioManagementJPanel.saveGUIOptions();
        // And switch to new portfolio.
        JStock.this.getJStockOptions().setPortfolioName(portfolio);
        JStock.this.portfolioManagementJPanel.initPortfolio();
        // I guess user wants to watch the current active portfolio right now.
        // We will help him to turn to the portfolio page.
        //JStock.this.jTabbedPane1.setSelectedIndex(3);
        JStock.this.jTabbedPane1.setSelectedIndex(portfolioTabIndex);
        
        JStock.this.portfolioManagementJPanel.updateTitledBorder();

        // No matter how, just stop progress bar, and display best message.
        this.setStatusBar(false, this.getBestStatusBarMessage());
    }
    
    private static boolean saveAsCSVFile(CSVWatchlist csvWatchlist, File file, boolean languageIndependent) {
        final org.yccheok.jstock.file.Statements statements = org.yccheok.jstock.file.Statements.newInstanceFromTableModel(csvWatchlist.tableModel, languageIndependent);
        assert(statements != null);
        return statements.saveAsCSVFile(file);
    }    
    
    protected boolean saveAsCSVFile(File file, boolean languageIndependent) {
        final TableModel tableModel = watchListPanel.getTable().getModel();
        CSVWatchlist csvWatchlist = CSVWatchlist.newInstance(tableModel);
        return saveAsCSVFile(csvWatchlist, file, languageIndependent);
    }

    private static JStockOptions getJStockOptionsViaXML() {
        final File f = new File(UserDataDirectory.Config.get() + UserDataFile.OptionsXml.get());
        JStockOptions jStockOptions = Utils.fromXML(JStockOptions.class, f);
        if (jStockOptions == null) {
            // JStockOptions's file not found. Perhaps this is the first time we
            // run JStock.
            jStockOptions = new JStockOptions();
        }
        return jStockOptions;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /***********************************************************************
         * UI Manager initialization via JStockOptions.
         **********************************************************************/
        final JStockOptions jStockOptions = getJStockOptionsViaXML();

        // allow CORS in JavaFX WebView
        //System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        // OSX menu bar at top.
        /*
        if (Utils.isMacOSX()) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.brushMetalLook", "true");
        }
        */  
        
        /***********************************************************************
         * Ensure correct localization.
         **********************************************************************/
        // This global effect, should just come before anything else, 
        // after we get an instance of JStockOptions.
        Locale.setDefault(jStockOptions.getLocale());
        
        /***********************************************************************
         * Single application instance enforcement.
         **********************************************************************/
        /*
        if (false == AppLock.lock()) {
            final int choice = JOptionPane.showOptionDialog(null, 
                    MessagesBundle.getString("warning_message_running_2_jstock"),
                    MessagesBundle.getString("warning_title_running_2_jstock"),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    new String[] {MessagesBundle.getString("yes_button_running_2_jstock"), MessagesBundle.getString("no_button_running_2_jstock")}, 
                    MessagesBundle.getString("no_button_running_2_jstock"));
            if (choice != JOptionPane.YES_OPTION) {
                System.exit(0);
                return;
            }
        } 
        */       
        
        // Avoid "JavaFX IllegalStateException when disposing JFXPanel in Swing"
        // http://stackoverflow.com/questions/16867120/javafx-illegalstateexception-when-disposing-jfxpanel-in-swing
        //Platform.setImplicitExit(false);
        
        // As ProxyDetector is affected by system properties
        // http.proxyHost, we are forced to initialized ProxyDetector right here,
        // before we manually change the system properties according to
        // JStockOptions.
        ProxyDetector.getInstance();      
              
        /***********************************************************************
         * Apply large font if possible.
         **********************************************************************/
        /*
        if (jStockOptions.useLargeFont()) {
            java.util.Enumeration keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get (key);
                if (value != null && value instanceof javax.swing.plaf.FontUIResource) {
                    javax.swing.plaf.FontUIResource fr = (javax.swing.plaf.FontUIResource)value;
                    UIManager.put(key, new javax.swing.plaf.FontUIResource(fr.deriveFont((float)fr.getSize2D() * (float)Constants.FONT_ENLARGE_FACTOR)));
                }
            } 
        }
        */
        
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JStock mainFrame = JStock.instance();
                
                // We need to first assign jStockOptions to mainFrame, as during
                // Utils.migrateXMLToCSVPortfolios, we will be accessing mainFrame's
                // jStockOptions.
                mainFrame.initJStockOptions(jStockOptions);
                
                mainFrame.init();
                mainFrame.setVisible(true);
                mainFrame.updateDividerLocation();
                mainFrame.watchListPanel.requestFocusOnJComboBox();
            }
        });
        */
        
        final JStock mainFrame = JStock.instance();
        
        // We need to first assign jStockOptions to mainFrame, as during
        // Utils.migrateXMLToCSVPortfolios, we will be accessing mainFrame's
        // jStockOptions.
        mainFrame.initJStockOptions(jStockOptions);
        
        mainFrame.init();
        mainFrame.setVisible(true);
        mainFrame.updateDividerLocation();
        mainFrame.watchListPanel.requestFocusOnJComboBox();
    }

    // Restore the last saved divider location for portfolio management panel.
    private void updateDividerLocation() {
        this.portfolioManagementJPanel.updateDividerLocation();
    }
    
    protected void clearAllStocks() {
        if (stockCodeHistoryGUI != null) {
            stockCodeHistoryGUI.clear();
        }
        if (realTimeStockMonitor != null) {
            realTimeStockMonitor.clearStockCodes();
        }
        if (stockHistoryMonitor != null) {
            stockHistoryMonitor.clearStockCodes();
        }
        final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();                                 
        this.initAlertStateManager();

        if (java.awt.EventQueue.isDispatchThread()) {
            tableModel.clearAllStocks();
            watchListPanel.updateDynamicChart(null);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    tableModel.clearAllStocks();
                    watchListPanel.updateDynamicChart(null);
                }                
            });
        }

        if (stockCodeHistoryGUI != null) {
            if (stockCodeHistoryGUI.isEmpty()) {
                if (this.stockInfoDatabase != null) {
                    this.setStatusBar(false, this.getBestStatusBarMessage());
                }
            }        
        }        
    }

    /**
     * Set the exchange rate value on status bar.
     *
     * @param exchangeRate the exchange rate value. null to reset
     */
    public void setStatusBarExchangeRate(final Double exchangeRate) {
        if (SwingUtilities.isEventDispatchThread()) {
            statusBar.setExchangeRate(exchangeRate);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    statusBar.setExchangeRate(exchangeRate);
                }
            });
        }
    }

    /**
     * Set the visibility of exchange rate label on status bar.
     *
     * @param visible true to make the exchange rate label visible. Else false
     */
    public void setStatusBarExchangeRateVisible(final boolean visible) {
        if (SwingUtilities.isEventDispatchThread()) {
            statusBar.setExchangeRateVisible(visible);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    statusBar.setExchangeRateVisible(visible);
                }
            });
        }
    }

    /**
     * Set the tool tip text of exchange rate label on status bar.
     *
     * @param text the tool tip text
     */
    public void setStatusBarExchangeRateToolTipText(final String text) {
        if (SwingUtilities.isEventDispatchThread()) {
            statusBar.setExchangeRateToolTipText(text);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    statusBar.setExchangeRateToolTipText(text);
                }
            });
        }
    }

    /**
     * Update the status bar.
     *
     * @param progressBar true to make progress bar busy. Else false
     * @param mainMessage message on the left
     */
    protected void setStatusBar(final boolean progressBar, final String mainMessage) {
        if (SwingUtilities.isEventDispatchThread())
        {
            isStatusBarBusy = progressBar;
            statusBar.setProgressBar(progressBar);
            statusBar.setMainMessage(mainMessage);
        }
        else
        {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    isStatusBarBusy = progressBar;
                    statusBar.setProgressBar(progressBar);
                    statusBar.setMainMessage(mainMessage);
                }
            });
        }
    }

    public PortfolioManagementJPanel getPortfolioManagementJPanel() {
        return this.portfolioManagementJPanel;
    }

    private void createPortfolioManagementJPanel() {
        portfolioManagementJPanel = new PortfolioManagementJPanel();
        portfolioTabIndex = jTabbedPane1.getTabCount();
        jTabbedPane1.addTab(GUIBundle.getString("PortfolioManagementJPanel_Title"), portfolioManagementJPanel);
        jTabbedPane1.setIconAt(portfolioTabIndex, this.getImageIcon("/images/16x16/calc.png"));
        jTabbedPane1.setToolTipTextAt(portfolioTabIndex, java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_ManageYourRealTimePortfolioWhichEnableYouToTrackBuyAndSellRecords"));
    }

    private void createStockIndicatorEditor() {
        indicatorPanel = new IndicatorPanel();
        stockIndicatorTabIndex = jTabbedPane1.getTabCount();
        jTabbedPane1.addTab(GUIBundle.getString("IndicatorPanel_Title"), indicatorPanel);
        jTabbedPane1.setIconAt(stockIndicatorTabIndex, this.getImageIcon("/images/16x16/color_line.png"));
        jTabbedPane1.setToolTipTextAt(stockIndicatorTabIndex, java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_CustomizeYourOwnStockIndicatorForAlertPurpose"));
    }

    private void createIndicatorScannerJPanel() {
        this.indicatorScannerJPanel = new IndicatorScannerJPanel();
        indicatorScannerTabIndex = jTabbedPane1.getTabCount();
        jTabbedPane1.addTab(GUIBundle.getString("IndicatorScannerJPanel_Title"), indicatorScannerJPanel);
        jTabbedPane1.setIconAt(indicatorScannerTabIndex, this.getImageIcon("/images/16x16/find.png"));
        jTabbedPane1.setToolTipTextAt(indicatorScannerTabIndex, java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_ScanThroughTheEntireStockMarketSoThatYouWillBeInformedWhatToSellOrBuy"));
        jTabbedPane1.addChangeListener(indicatorScannerJPanel);
    }
    
    private void initTableHeaderToolTips() {
        watchListPanel.initTableHeaderToolTips();
    }

    protected void changeCountry(Country country) {
        if (country == null) {
            return;
        }

        if (jStockOptions.getCountry() == country) {
            return;
        }

        org.yccheok.jstock.engine.Utils.clearGoogleCodeDatabaseCache();
        org.yccheok.jstock.engine.Utils.clearAllIEXStockInfoDatabaseCaches();
        
        final Country oldCountry = jStockOptions.getCountry();
        
        if (needToSaveUserDefinedDatabase) {
            // We are having updated user database in memory.
            // Save it to disk.
            this.saveUserDefinedDatabaseAsCSV(oldCountry, stockInfoDatabase);
        }

        /* Save the GUI look. */
        saveGUIOptions();

        /* Need to save chart dialog options? */
        
        saveWatchlist();
        this.portfolioManagementJPanel.savePortfolio();

        jStockOptions.setCountry(country);
        jStockOptions.addRecentCountry(country);
        JStock.this.statusBar.setCountryIcon(country.icon, country.humanString);

        // Here is the dirty trick here. We let our the 'child' panels perform
        // cleanup/ initialization first before initStockCodeAndSymbolDatabase.
        // This is because all child panels and stock symbol database task do
        // interact with status bar. However, We are only most interest in stock symbol
        // database, as it will be the most busy. Hence, we let the stock symbol
        // database to be the last, so that its interaction will overwrite the others.
        this.portfolioManagementJPanel.initPortfolio();
        this.indicatorScannerJPanel.stop();
        this.indicatorScannerJPanel.clear();

        this.initGoogleCodeDatabaseRunnable();
        this.initDatabase(true);
        this.initAjaxProvider();
        this.initRealTimeIndexMonitor();
        this.initMarketJPanel();
        this.initStockHistoryMonitor();
        this.initOthersStockHistoryMonitor();
        this.initExchangeRateMonitor();
        // Initialize real time monitor must come before initialize real time
        // stocks. We need to submit real time stocks to real time stock monitor.
        // Hence, after we load real time stocks from file, real time stock monitor
        // must be ready (initialized).
        this.initRealTimeStockMonitor();
        this.watchListPanel.initWatchlist();
        this.initAlertStateManager();
        this.initDynamicCharts();
        // this.initDynamicChartVisibility();

        menuBar.setSelectedCountryItem(country);
    }

    private MouseAdapter getMyJXStatusBarExchangeRateLabelMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Popup dialog to select currency exchange option.
                    OptionsJDialog optionsJDialog = new OptionsJDialog(JStock.this, true);
                    optionsJDialog.setLocationRelativeTo(JStock.this);
                    optionsJDialog.set(jStockOptions);
                    optionsJDialog.select(GUIBundle.getString("OptionsJPanel_Wealth"));
                    optionsJDialog.setVisible(true);
                }
            }
        };
    }

    private MouseAdapter getMyJXStatusBarCountryLabelMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    CountryJDialog countryJDialog = new CountryJDialog(JStock.this, true);
                    countryJDialog.setLocationRelativeTo(JStock.this);
                    countryJDialog.setCountry(jStockOptions.getCountry());
                    countryJDialog.setVisible(true);
                    
                    final Country country = countryJDialog.getCountry();
                    changeCountry(country);
                }
            }
        };
    }
    
    private MouseAdapter getMyJXStatusBarImageLabelMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    
                    // Make sure no other task is running.
                    // Use local variable to be thread safe.
                    final DatabaseTask task = JStock.this.databaseTask;
                    if (task != null) {
                        if (task.isDone() == true) {
                            // Task is done. But, does it success?
                            boolean success = false;
                            // Some developers suggest that check for isCancelled before calling get
                            // to avoid CancellationException. Others suggest that just perform catch
                            // on all Exceptions. I will do it both.
                            if (task.isCancelled() == false) {
                                try {
                                    success = task.get();
                                } catch (InterruptedException ex) {
                                    log.error(null, ex);
                                } catch (ExecutionException ex) {
                                    log.error(null, ex);
                                } catch (CancellationException ex) {
                                    log.error(null, ex);
                                }
                            }
                            if (success == false) {
                                // Fail. Automatically reload database for user. Need not to prompt them message.
                                // As, they do not have any database right now.
                                JStock.this.initDatabase(true);
                                
                            } else {
                                final int result = JOptionPane.showConfirmDialog(JStock.this, MessagesBundle.getString("question_message_perform_server_reconnecting"), MessagesBundle.getString("question_title_perform_server_reconnecting"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (result == JOptionPane.YES_OPTION) {
                                    JStock.this.initDatabase(false);
                                }
                            }
                        }
                        else {
                            // There is task still running. Ask user whether he wants
                            // to stop it.
                            final int result = JOptionPane.showConfirmDialog(JStock.this, MessagesBundle.getString("question_message_cancel_server_reconnecting"), MessagesBundle.getString("question_title_cancel_server_reconnecting"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            
                            if (result == JOptionPane.YES_OPTION)
                            {                            
                                synchronized (JStock.this.databaseTaskMonitor)
                                {
                                    JStock.this.databaseTask.cancel(true);
                                    JStock.this.databaseTask = null;
                                }
                                
                                setStatusBar(false, GUIBundle.getString("MainFrame_NetworkError"));
                                statusBar.setImageIcon(getImageIcon("/images/16x16/network-error.png"), java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_DoubleClickedToTryAgain"));
                            }
                        }
                    }
                    else {
                        // User cancels databaseTask explicitly. (Cancel while
                        // JStock is fetching database from server). Let's read
                        // from disk.
                        initDatabase(true);
                    }
                            
                }
            }
        };
    }

    public StockInfoDatabase getStockInfoDatabase() {
        return this.stockInfoDatabase;
    }

    public StockNameDatabase getStockNameDatabase() {
        return stockNameDatabase;
    }
    
    public java.util.List<Stock> getStocks() {
        final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();
        return tableModel.getStocks();
    }

    // Only will return true if the selected stock is the one and only one.
    private boolean isStockBeingSelected(final Stock stock) {
        //int[] rows = JStock.this.jTable1.getSelectedRows();
        int[] rows = watchListPanel.getTable().getSelectedRows();
            
        if (rows.length == 1) {
            final int row = rows[0];
            final StockTableModel tableModel = (StockTableModel)watchListPanel.getTable().getModel();
            final int modelIndex = watchListPanel.getTable().convertRowIndexToModel(row);
            if (stock.code.equals(tableModel.getStock(modelIndex).code)) {
                return true;
            }
        }
        
        return false;
    }
    
    private void update(final Indicator indicator, Boolean result)
    {
        final boolean flag = result;

        if (flag == false) {
            return;
        }

        final Stock stock = indicator.getStock();        
        final double lastPrice = stock.getLastPrice();

        // Using lastPrice = 0 to compare against fall below and rise above
        // target price is meaningless. In normal condition, no stock price
        // shall fall until 0. When we get last price is 0, most probably
        // market is not opened yet.
        if (lastPrice <= 0.0) {
            return;
        }

        // Sound alert hasn't been submitted to pop up message pool.
        if (jStockOptions.isPopupMessage() == false && jStockOptions.isSoundEnabled()) {
            final Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (jStockOptions.isSoundEnabled()) {
                        /* Non-blocking. */
                        Utils.playAlertSound();

                        try {
                            Thread.sleep(jStockOptions.getAlertSpeed() * 1000);
                        }
                        catch (InterruptedException exp) {
                            log.error(null, exp);
                        }
                    }
                }
            };

            try {
                systemTrayAlertPool.submit(r);
            }
            catch (java.util.concurrent.RejectedExecutionException exp) {
                log.error(null, exp);
            }
        }   /* if(this.jStockOptions.isSoundEnabled()) */
    }

    private org.yccheok.jstock.engine.Observer<Indicator, Boolean> getAlertStateManagerObserver() {
        return new org.yccheok.jstock.engine.Observer<Indicator, Boolean>() {
            @Override
            public void update(Indicator subject, Boolean arg) {
                JStock.this.update(subject, arg);
            }
        };
    }

    // This is the workaround to overcome Erasure by generics. We are unable to make MainFrame to
    // two observers at the same time.
    private org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result> getRealTimeStockMonitorObserver() {
        return new org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result>() {
            @Override
            public void update(RealTimeStockMonitor monitor, RealTimeStockMonitor.Result result)
            {
                JStock.this.update(monitor, result);
            }
        };
    }

    private org.yccheok.jstock.engine.Observer<RealTimeIndexMonitor, java.util.List<Market>> getRealTimeIndexMonitorObserver() {
        return new org.yccheok.jstock.engine.Observer<RealTimeIndexMonitor, java.util.List<Market>>() {
            @Override
            public void update(RealTimeIndexMonitor monitor, java.util.List<Market> markets)
            {
                JStock.this.update(markets);
            }
        };        
    }
    
    private org.yccheok.jstock.engine.Observer<StockHistoryMonitor, StockHistoryMonitor.StockHistoryRunnable> getStockHistoryMonitorObserver() {
        return new org.yccheok.jstock.engine.Observer<StockHistoryMonitor, StockHistoryMonitor.StockHistoryRunnable>() {
            @Override
            public void update(StockHistoryMonitor monitor, StockHistoryMonitor.StockHistoryRunnable runnable)
            {
                JStock.this.update(monitor, runnable);
            }
        };
    }
    
    // Asynchronous call. Must be called by event dispatch thread.
    public void displayHistoryChart(StockInfo stockInfo) {
        final StockHistoryServer stockHistoryServer = stockHistoryMonitor.getStockHistoryServer(stockInfo.code);
        if (stockHistoryServer == null) {
            if (stockCodeHistoryGUI.add(stockInfo.code) && stockHistoryMonitor.addStockCode(stockInfo.code)) {                                
                final String template = GUIBundle.getString("MainFrame_LookingForHistory_template");
                final String message = MessageFormat.format(template, stockInfo.symbol, stockCodeHistoryGUI.size());
                setStatusBar(true, message);
            }
        } else {
            ChartJDialog chartJDialog = new ChartJDialog(JStock.this, stockInfo.symbol + " (" + stockInfo.code + ")", false, stockHistoryServer);
            chartJDialog.setVisible(true);                            
        }        
    }
    
    public void displayHistoryCharts() {
        int rows[] = watchListPanel.getTable().getSelectedRows();
        final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();

        for (int row : rows) {
            final int modelIndex = watchListPanel.getTable().getRowSorter().convertRowIndexToModel(row);
            Stock stock = tableModel.getStock(modelIndex);
            displayHistoryChart(StockInfo.newInstance(stock));
        }
    }

    public void displayStockNews(StockInfo stockInfo) {
        assert(SwingUtilities.isEventDispatchThread());

        final String title = stockInfo.symbol + " (" + stockInfo.code + ")";
        //final StockNewsJFrame stockNewsJFrame = new StockNewsJFrame(this, stockInfo, title);
        new StockNewsJFrame(this, stockInfo, title);
    }

    protected void displayStocksNews() {
        int rows[] = watchListPanel.getTable().getSelectedRows();
        final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();

        for (int row : rows) {
            final int modelIndex = watchListPanel.getTable().getRowSorter().convertRowIndexToModel(row);
            final Stock stock = tableModel.getStock(modelIndex);
            displayStockNews(StockInfo.newInstance(stock));
        }
    }

    private static boolean saveStockNameDatabaseAsCSV(Country country, StockNameDatabase stockNameDatabase) {
        final File stockNameDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database" + File.separator + "stock-name-database.csv");
        final Statements statements = Statements.newInstanceFromStockNameDatabase(stockNameDatabase);
        boolean result = statements.saveAsCSVFile(stockNameDatabaseCSVFile);
        return result;
    }
    
    protected static boolean saveStockInfoDatabaseAsCSV(Country country, StockInfoDatabase stockInfoDatabase) {
        org.yccheok.jstock.gui.Utils.createCompleteDirectoryHierarchyIfDoesNotExist(org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFileDirectory(country));
        final File stockInfoDatabaseCSVFile = org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFile(country);
        final Statements statements = Statements.newInstanceFromStockInfoDatabase(stockInfoDatabase);
        boolean result = statements.saveAsCSVFile(stockInfoDatabaseCSVFile);
        return result;
    }
    
    protected boolean saveUserDefinedDatabaseAsCSV(Country country, StockInfoDatabase stockInfoDatabase) {
        // Previously, we will store the entire stockcodeandsymboldatabase.xml
        // to cloud server if stockcodeandsymboldatabase.xml is containing
        // user defined code. Due to our server is running out of space, we will
        // only store UserDefined pair. user-defined-database.xml will be only
        // used for cloud storage purpose.
        final java.util.List<Pair<Code, Symbol>> pairs = getUserDefinedPair(stockInfoDatabase);
        // pairs can be empty. When it is empty, try to delete the file.
        // If deletion fail, we need to overwrite the file to reflect this.
        final File userDefinedDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database" + File.separator + "user-defined-database.csv");
        if (pairs.isEmpty()) {            
            if (userDefinedDatabaseCSVFile.delete() == true) {
                return true;
            }
        }
        final Statements statements = Statements.newInstanceFromUserDefinedDatabase(pairs);
        boolean result = statements.saveAsCSVFile(userDefinedDatabaseCSVFile);
        this.needToSaveUserDefinedDatabase = false;
        return result;
    }
    
    private java.util.List<Pair<Code, Symbol>> loadUserDefinedDatabaseFromCSV(Country country) {
        final File userDefinedDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database" + File.separator + "user-defined-database.csv");
        
        if (!userDefinedDatabaseCSVFile.exists())
           return new ArrayList<Pair<Code, Symbol>>();
        
        Statements statements = Statements.newInstanceFromCSVFile(userDefinedDatabaseCSVFile);
        if (statements.getType() != Statement.Type.UserDefinedDatabase) {
            return new ArrayList<Pair<Code, Symbol>>();
        }        
        java.util.List<Pair<Code, Symbol>> pairs = new ArrayList<Pair<Code, Symbol>>();
        for (int i = 0, ei = statements.size(); i < ei; i++) {
            Statement statement = statements.get(i);
            Atom atom0 = statement.getAtom(0);
            Atom atom1 = statement.getAtom(1);
            Code code = Code.newInstance(atom0.getValue().toString());
            Symbol symbol = Symbol.newInstance(atom1.getValue().toString());
            
            pairs.add(new Pair<Code, Symbol>(code, symbol));
        }
        return pairs;
    }
    
    private StockNameDatabase loadStockNameDatabaseFromCSV(Country country) {
        final File stockNameDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database" + File.separator + "stock-name-database.csv");
        
        Statements statements = Statements.newInstanceFromCSVFile(stockNameDatabaseCSVFile);
        if (statements.getType() != Statement.Type.StockNameDatabase) {
            return null;
        }        
        java.util.List<Stock> stocks = new ArrayList<Stock>();
        for (int i = 0, ei = statements.size(); i < ei; i++) {
            Statement statement = statements.get(i);
            Atom atom0 = statement.getAtom(0);
            Atom atom1 = statement.getAtom(1);
            Code code = Code.newInstance(atom0.getValue().toString());
            String name = atom1.getValue().toString();
            
            // Symbol doesn't matter. Just provide a dummy value for it.
            Stock stock = Stock.builder(code, Symbol.newInstance(code.toString())).name(name).build();
            stocks.add(stock);
        }
        return new StockNameDatabase(stocks);
    }
    
    private StockInfoDatabase loadStockInfoDatabaseFromCSV(Country country) {
        final File stockInfoDatabaseCSVFile = org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFile(country);
        
        Statements statements = Statements.newInstanceFromCSVFile(stockInfoDatabaseCSVFile);
        if (statements.getType() != Statement.Type.StockInfoDatabase) {
            return null;
        }
        java.util.List<Stock> stocks = new ArrayList<Stock>();
        for (int i = 0, ei = statements.size(); i < ei; i++) {
            Statement statement = statements.get(i);
            Atom atom0 = statement.getAtom(0);
            Atom atom1 = statement.getAtom(1);
            Atom atom2 = statement.getAtom(2);
            Atom atom3 = statement.getAtom(3);
            
            Code code = Code.newInstance(atom0.getValue().toString());
            Symbol symbol = Symbol.newInstance(atom1.getValue().toString());
            Industry industry = Industry.Unknown;
            Board board = Board.Unknown;
            try {
                industry = Industry.valueOf(atom2.getValue().toString());
            } catch (Exception exp) {
                log.error(null, exp);
            }
            try {
                board = Board.valueOf(atom3.getValue().toString());
            } catch (Exception exp) {
                log.error(null, exp);
            }
            
            Stock stock = Stock.builder(code, symbol).board(board).industry(industry).build();
            stocks.add(stock);
        }
        return new StockInfoDatabase(stocks);
    }
    
    // Task to initialize both stockInfoDatabase and stockNameDatabase.
    private class DatabaseTask extends SwingWorker<Boolean, Void> {
        private boolean readFromDisk = true;

        public DatabaseTask(boolean readFromDisk)
        {
            this.readFromDisk = readFromDisk;
        }
        
        @Override
        protected void done() {
            // The done Method: When you are informed that the SwingWorker
            // is done via a property change or via the SwingWorker object's
            // done method, you need to be aware that the get methods can
            // throw a CancellationException. A CancellationException is a
            // RuntimeException, which means you do not need to declare it
            // thrown and you do not need to catch it. Instead, you should
            // test the SwingWorker using the isCancelled method before you
            // use the get method.
            if (this.isCancelled()) {
                // Cancelled by user explicitly. Do not perform any GUI update.
                // No pop-up message.
                return;
            }

            boolean success = false;
            
            try {
                success = get();
            } catch (InterruptedException exp) {
                log.error(null, exp);
            } catch (java.util.concurrent.ExecutionException exp) {
                log.error(null, exp);
            } catch (CancellationException ex) {
                // Not sure. Some developers suggest to catch this exception as
                // well instead of checking on isCancelled. I will do it both.
                log.error(null, ex);
            }
           
            if (success) {
                setStatusBar(false, getBestStatusBarMessage());
                statusBar.setImageIcon(getImageIcon("/images/16x16/network-transmit-receive.png"), java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_Connected"));
            }
            else {
                setStatusBar(false, GUIBundle.getString("MainFrame_NetworkError"));
                statusBar.setImageIcon(getImageIcon("/images/16x16/network-error.png"), java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_DoubleClickedToTryAgain"));
            }
       }
       
        @Override
        public Boolean doInBackground() {
            final Country country = jStockOptions.getCountry();
            
            Utils.createCompleteDirectoryHierarchyIfDoesNotExist(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database");

            if (this.readFromDisk)
            {
                StockInfoDatabase tmp_stock_info_database = loadStockInfoDatabaseFromCSV(country);
                if (tmp_stock_info_database == null) {
                    // Perhaps we are having a corrupted database. We will 
                    // restore from database.zip.
                    initPreloadDatabase(true);        
                    
                    tmp_stock_info_database = loadStockInfoDatabaseFromCSV(country);
                }

                // StockNameDatabase is an optional item.
                final StockNameDatabase tmp_name_database;
                if (org.yccheok.jstock.engine.Utils.isNameImmutable()) {
                    tmp_name_database = JStock.this.loadStockNameDatabaseFromCSV(country);
                } else {
                    tmp_name_database = null;
                }

                // After time consuming operation, check whether we should 
                // cancel.
                if (this.isCancelled()) {
                    return false;
                }
                
                if (tmp_stock_info_database != null && false == tmp_stock_info_database.isEmpty()) {
                    // Yes. We need to integrate "user-defined-database.csv" into tmp_stock_info_database
                    final java.util.List<Pair<Code, Symbol>> pairs = loadUserDefinedDatabaseFromCSV(country);
                    
                    boolean addUserDefinedStockInfoSuccessAtLeastOnce = false;
                    
                    if (pairs.isEmpty() == false) {
                        // Remove the old user defined database. Legacy stockcodeandsymboldatabase.xml
                        // may contain user defined codes.
                        tmp_stock_info_database.removeAllUserDefinedStockInfos();
                        
                        // Insert with new user defined code.
                        for (Pair<Code, Symbol> pair : pairs) {
                            if (tmp_stock_info_database.addUserDefinedStockInfo(StockInfo.newInstance(pair.first, pair.second))) {
                                addUserDefinedStockInfoSuccessAtLeastOnce = true;
                            }
                        }
                    }

                    if (false == addUserDefinedStockInfoSuccessAtLeastOnce) {
                        // user-defined-database.csv is no longer needed.
                        new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory() + country + File.separator + "database" + File.separator + "user-defined-database.csv").delete();
                    }

                    // Prepare proper synchronization for us to change country.
                    synchronized (JStock.this.databaseTaskMonitor)
                    {
                        if (this.isCancelled()) {
                            return false;
                        }
                        
                        JStock.this.stockInfoDatabase = tmp_stock_info_database;
                        JStock.this.stockNameDatabase = tmp_name_database;
                        JStock.this.watchListPanel.setStockInfoDatabase(JStock.this.stockInfoDatabase);
                        JStock.this.indicatorPanel.setStockInfoDatabase(JStock.this.stockInfoDatabase);

                        return true;
                    }                
                }   // if (tmp_stock_info_database != null && false == tmp_stock_info_database.isEmpty())
            }   // if(this.readFromDisk)
                    
            // When we fall here, we either fail to read from disk or user
            // explicitly doesn't allow us to read from disk. Let's perform
            // networking stuff.
            //
            // For networking stuff, we will try on JStock static server.

            final String location = org.yccheok.jstock.engine.Utils.getStocksCSVZipFileLocation(country);
            // Try to download the CSV file.
            final File zipFile = Utils.downloadAsTempFile(location);
            // Is download success?
            if (zipFile == null) {
                return false;
            }
            
            File tempZipDirectory = null;
                    
            try {
                tempZipDirectory = java.nio.file.Files.createTempDirectory(null).toFile();

                if (false == Utils.extractZipFile(zipFile, tempZipDirectory.getAbsolutePath(), true)) {
                    return false;
                }

                File file = new File(tempZipDirectory, "stocks.csv");

                // Try to parse the CSV file.
                final java.util.List<Stock> stocks = org.yccheok.jstock.engine.Utils.getStocksFromCSVFile(file);
                // Is the stocks good enough?
                if (false == stocks.isEmpty()) {
                    final Pair<StockInfoDatabase, StockNameDatabase> stockDatabase = org.yccheok.jstock.engine.Utils.toStockDatabase(stocks, country);

                    // After time consuming operation, check whether we should
                    // cancel.
                    if (this.isCancelled()) {
                        return false;
                    }

                    // Save to disk.
                    JStock.saveStockInfoDatabaseAsCSV(country, stockDatabase.first);
                    if (stockDatabase.second != null) {
                        JStock.saveStockNameDatabaseAsCSV(country, stockDatabase.second);
                    }

                    // Yes. We need to integrate "user-defined-database.csv" into tmp_stock_info_database
                    final java.util.List<Pair<Code, Symbol>> pairs = loadUserDefinedDatabaseFromCSV(country);

                    if (pairs.isEmpty() == false) {
                        // Insert with new user defined code.
                        for (Pair<Code, Symbol> pair : pairs) {
                            stockDatabase.first.addUserDefinedStockInfo(StockInfo.newInstance(pair.first, pair.second));
                        }
                    }

                    // Prepare proper synchronization for us to change country.
                    synchronized (JStock.this.databaseTaskMonitor)
                    {
                        if (this.isCancelled()) {
                            return false;
                        }

                        JStock.this.stockInfoDatabase = stockDatabase.first;
                        JStock.this.stockNameDatabase = stockDatabase.second;

                        // Register the auto complete JComboBox with latest database.
                        //((AutoCompleteJComboBox)JStock.this.jComboBox1).setStockInfoDatabase(JStock.this.stockInfoDatabase);
                        watchListPanel.setStockInfoDatabase(JStock.this.stockInfoDatabase);
                        JStock.this.indicatorPanel.setStockInfoDatabase(JStock.this.stockInfoDatabase);

                        return true;
                    }
                }
            } catch (IOException ex) {
                log.error(null, ex);
            } finally {
                if (tempZipDirectory != null) {
                    Utils.deleteDir(tempZipDirectory, true);
                }
            }
            return false;
        }
    }

    private void initMyJXStatusBarExchangeRateLabelMouseAdapter() {
        final MouseAdapter mouseAdapter = this.getMyJXStatusBarExchangeRateLabelMouseAdapter();
        this.statusBar.addExchangeRateLabelMouseListener(mouseAdapter);
    }

    private void initMyJXStatusBarCountryLabelMouseAdapter() {
        final MouseAdapter mouseAdapter = this.getMyJXStatusBarCountryLabelMouseAdapter();
        this.statusBar.addCountryLabelMouseListener(mouseAdapter);
    }
    
    private void initMyJXStatusBarImageLabelMouseAdapter() {
        final MouseAdapter mouseAdapter = this.getMyJXStatusBarImageLabelMouseAdapter();
        this.statusBar.addImageLabelMouseListener(mouseAdapter);
    }

    /**
     * Initializes currency exchange monitor.
     */
    public void initExchangeRateMonitor() {
        this.portfolioManagementJPanel.initExchangeRateMonitor();
    }

    private void initRealTimeIndexMonitor() {
        final RealTimeIndexMonitor oldRealTimeIndexMonitor = realTimeIndexMonitor;
        if (oldRealTimeIndexMonitor != null) {            
            zombiePool.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("Prepare to shut down " + oldRealTimeIndexMonitor + "...");
                    oldRealTimeIndexMonitor.clearIndices();
                    oldRealTimeIndexMonitor.dettachAll();
                    oldRealTimeIndexMonitor.stop();
                    log.info("Shut down " + oldRealTimeIndexMonitor + " peacefully.");
                }
            });
        }

        realTimeIndexMonitor = new RealTimeIndexMonitor(
                Constants.REAL_TIME_INDEX_MONITOR_MAX_THREAD, 
                Constants.REAL_TIME_INDEX_MONITOR_MAX_STOCK_SIZE_PER_SCAN,
                jStockOptions.getScanningSpeed());
        
        realTimeIndexMonitor.attach(this.realTimeIndexMonitorObserver);
        
        for (Index index : org.yccheok.jstock.engine.Utils.getStockIndices(jStockOptions.getCountry())) {
            realTimeIndexMonitor.addIndex(index);
        }
        
        realTimeIndexMonitor.startNewThreadsIfNecessary();
    }
    
    private void initRealTimeStockMonitor() {
        final RealTimeStockMonitor oldRealTimeStockMonitor = realTimeStockMonitor;
        if (oldRealTimeStockMonitor != null) {            
            zombiePool.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("Prepare to shut down " + oldRealTimeStockMonitor + "...");
                    oldRealTimeStockMonitor.clearStockCodes();
                    oldRealTimeStockMonitor.dettachAll();
                    oldRealTimeStockMonitor.stop();
                    log.info("Shut down " + oldRealTimeStockMonitor + " peacefully.");
                }
            });
        }

        realTimeStockMonitor = new RealTimeStockMonitor(
                Constants.REAL_TIME_STOCK_MONITOR_MAX_THREAD, 
                Constants.REAL_TIME_STOCK_MONITOR_MAX_STOCK_SIZE_PER_SCAN,
                jStockOptions.getScanningSpeed());

        realTimeStockMonitor.attach(this.realTimeStockMonitorObserver);

        this.indicatorScannerJPanel.initRealTimeStockMonitor();
        this.portfolioManagementJPanel.initRealTimeStockMonitor();
    }

    private void initUIOptions() {
        File file = new File(UserDataDirectory.Config.get() + UserDataFile.UIOptionsJson.get());
        this.uiOptions = Utils.fromJson(file, UIOptions.class);
        if (this.uiOptions == null) {
            this.uiOptions = new UIOptions();
        }
    }
    
    private void initGUIOptions() {
        final File f = new File(UserDataDirectory.Config.get() + UserDataFile.MainFrameXml.get());
        GUIOptions guiOptions = Utils.fromXML(GUIOptions.class, f);

        if (guiOptions == null)
        {
            // When user launches JStock for first time, we will help him to
            // turn off the following column(s), as we feel those information
            // is redundant. If they wish to view those information, they have
            // to turn it on explicitly.
            JTableUtilities.removeTableColumn(watchListPanel.getTable(), GUIBundle.getString("MainFrame_Open"));
            return;
        }

        if (guiOptions.getJTableOptionsSize() <= 0)
        {
            // When user launches JStock for first time, we will help him to
            // turn off the following column(s), as we feel those information
            // is redundant. If they wish to view those information, they have
            // to turn it on explicitly.
            JTableUtilities.removeTableColumn(watchListPanel.getTable(), GUIBundle.getString("MainFrame_Open"));
            return;
        }

        /* Set Table Settings */
        JTableUtilities.setJTableOptions(watchListPanel.getTable(), guiOptions.getJTableOptions(0));
    }

    private void saveUIOptions() {
        File file = new File(UserDataDirectory.Config.get() + UserDataFile.UIOptionsJson.get());
        Utils.saveJson(file, this.uiOptions);
    }
    
    private void saveGUIOptions() {
        _saveGUIOptions();
        this.indicatorScannerJPanel.saveGUIOptions();
        this.portfolioManagementJPanel.saveGUIOptions();
    }
    
    private boolean _saveGUIOptions() {
        if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
        {
            return false;
        }
        
        final GUIOptions.JTableOptions jTableOptions = new GUIOptions.JTableOptions();
        
        final int count = watchListPanel.getTable().getColumnCount();
        for (int i = 0; i < count; i++) {
            final String name = watchListPanel.getTable().getColumnName(i);
            final TableColumn column = watchListPanel.getTable().getColumnModel().getColumn(i);
            jTableOptions.addColumnOption(GUIOptions.JTableOptions.ColumnOption.newInstance(name, column.getWidth()));
        }
        
        final GUIOptions guiOptions = new GUIOptions();
        guiOptions.addJTableOptions(jTableOptions);
        
        File f = new File(UserDataDirectory.Config.get() + UserDataFile.MainFrameXml.get());
        return Utils.toXML(guiOptions, f);
    }

    /**
     * Initialize chart dialog options.
     */
    private void initChartJDialogOptions() {
        final File f = new File(UserDataDirectory.Config.get() + UserDataFile.ChartJDialogOptionsXml.get());
        final ChartJDialogOptions tmp = Utils.fromXML(ChartJDialogOptions.class, f);
        if (tmp == null) {
            this.chartJDialogOptions = new ChartJDialogOptions();
        }
        else {
            this.chartJDialogOptions = tmp;
            log.info("chartJDialogOptions loaded from " + f.toString() + " successfully.");
        }
    }

    /**
     * Initialize JStock options.
     */
    public void initJStockOptions(JStockOptions jStockOptions) {
        this.jStockOptions = jStockOptions;

        /* Hard core fix. */
        if (this.jStockOptions.getScanningSpeed() == 0) {
            this.jStockOptions.setScanningSpeed(1*60*1000);
        }
                
        final String proxyHost = this.jStockOptions.getProxyServer();
        final int proxyPort = this.jStockOptions.getProxyPort();
        
        if ((proxyHost.length() > 0) && (org.yccheok.jstock.engine.Utils.isValidPortNumber(proxyPort))) {
            System.getProperties().put("http.proxyHost", proxyHost);
            System.getProperties().put("http.proxyPort", "" + proxyPort);
        } else {
            System.getProperties().remove("http.proxyHost");
            System.getProperties().remove("http.proxyPort");
        }
        
        Utils.updateFactoriesPriceSource();        
    }   

    public void updatePriceSource(Country country, PriceSource priceSource) {
        Factories.INSTANCE.updatePriceSource(country, priceSource);
        
        rebuildRealTimeStockMonitor();
        rebuildRealTimeIndexMonitor();

        this.indicatorScannerJPanel.rebuildRealTimeStockMonitor();
        this.portfolioManagementJPanel.rebuildRealTimeStockMonitor();
        
        this.refreshAllRealTimeStockMonitors();
        this.refreshRealTimeIndexMonitor();
        this.refreshExchangeRateMonitor();
    }

    private void rebuildRealTimeStockMonitor() {
        RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
        if (_realTimeStockMonitor != null) {
            _realTimeStockMonitor.rebuild();
        }
    }
    
    private void rebuildRealTimeIndexMonitor() {
        RealTimeIndexMonitor _realTimeIndexMonitor = this.realTimeIndexMonitor;
        if (_realTimeIndexMonitor != null) {
            _realTimeIndexMonitor.rebuild();
        }
    }
    
    /*
    private void initWatchlist() {
        // This is new watchlist. Reset last update date.
        this.timestamp = 0;
        initCSVWatchlist();
    }
    
    private boolean initCSVWatchlist() {  
        java.util.List<String> availableWatchlistNames = org.yccheok.jstock.watchlist.Utils.getWatchlistNames();
        // Do we have any watchlist for this country?
        if (availableWatchlistNames.size() <= 0) {
            // If not, create an empty watchlist.
            org.yccheok.jstock.watchlist.Utils.createEmptyWatchlist(org.yccheok.jstock.watchlist.Utils.getDefaultWatchlistName());
            availableWatchlistNames = org.yccheok.jstock.watchlist.Utils.getWatchlistNames();
        }
        assert(availableWatchlistNames.isEmpty() == false);

        // Is user selected watchlist name within current available watchlist names?
        if (false == availableWatchlistNames.contains(jStockOptions.getWatchlistName())) {
            // Nope. Reset user selected watchlist name to the first available name.
            jStockOptions.setWatchlistName(availableWatchlistNames.get(0));
        }
        
        // openAsCSVFile will "append" stocks. We need to clear previous stocks
        // explicitly.
        
        // Clear the previous data structures.
        clearAllStocks();
        
        File realTimeStockFile = org.yccheok.jstock.watchlist.Utils.getWatchlistFile(org.yccheok.jstock.watchlist.Utils.getWatchlistDirectory());
        return this.openAsCSVFile(realTimeStockFile);
    }
    */
    
    public static boolean saveCSVWatchlist(String directory, CSVWatchlist csvWatchlist) {
        assert(directory.endsWith(File.separator));
        if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(directory) == false)
        {
            return false;
        } 
        return JStock.saveAsCSVFile(csvWatchlist, org.yccheok.jstock.watchlist.Utils.getWatchlistFile(directory), true);
    }
    
    private boolean saveCSVWathclist() {
        final String directory = org.yccheok.jstock.watchlist.Utils.getWatchlistDirectory();
        final TableModel tableModel = watchListPanel.getTable().getModel();
        CSVWatchlist csvWatchlist = CSVWatchlist.newInstance(tableModel);
        return JStock.saveCSVWatchlist(directory, csvWatchlist);
    }

    private boolean saveWatchlist() {
        return this.saveCSVWathclist();
    }

    private static java.util.List<Pair<Code, Symbol>> getUserDefinedPair(StockInfoDatabase stockInfoDatabase) {
        java.util.List<Pair<Code, Symbol>> pairs = new ArrayList<Pair<Code, Symbol>>();
        java.util.List<StockInfo> stockInfos = stockInfoDatabase.getUserDefinedStockInfos();
        for (StockInfo stockInfo : stockInfos) {
            pairs.add(new Pair<Code, Symbol>(stockInfo.code, stockInfo.symbol));
        }
        return pairs;
    }

    /**
     * Save chart dialog options to disc.
     * @return <tt>true</tt> if saving operation is success
     */
    private boolean saveChartJDialogOptions() {
        if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
        {
            return false;
        }

        File f = new File(UserDataDirectory.Config.get() + UserDataFile.ChartJDialogOptionsXml.get());
        return org.yccheok.jstock.gui.Utils.toXML(this.chartJDialogOptions, f);
    }

    /**
     * Save JStock options to disc.
     * @return <tt>true</tt> if saving operation is success
     */
    private boolean saveJStockOptions() {
        if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
        {
            return false;
        }
        
        File f = new File(UserDataDirectory.Config.get() + UserDataFile.OptionsXml.get());
        return org.yccheok.jstock.gui.Utils.toXML(this.jStockOptions, f);
    }

    private void removeOldHistoryData(Country country) {
        // We do not want "yesterday" history record. We will remove 1 day old files.
        org.yccheok.jstock.gui.Utils.deleteAllOldFiles(new File(Utils.getHistoryDirectory(country)), 1);
    }

    private void initAlertStateManager() {
        alertStateManager.clearState();
        alertStateManager.attach(alertStateManagerObserver);
    }

    private void initOthersStockHistoryMonitor()
    {
        this.indicatorPanel.initStockHistoryMonitor();
        this.indicatorScannerJPanel.initStockHistoryMonitor();
    }

    // Do not combine initOthersStockHistoryMonitor with initStockHistoryMonitor. We need to be able to update
    // only MainFrame's history monitor, when we change the history duration option. Other's history monitors
    // are not affected.
    private void initStockHistoryMonitor() {
        final StockHistoryMonitor oldStockHistoryMonitor = stockHistoryMonitor;
        if (oldStockHistoryMonitor != null) {            
            zombiePool.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("Prepare to shut down " + oldStockHistoryMonitor + "...");
                    oldStockHistoryMonitor.clearStockCodes();
                    oldStockHistoryMonitor.dettachAll();
                    oldStockHistoryMonitor.stop();
                    log.info("Shut down " + oldStockHistoryMonitor + " peacefully.");
                }
            });
        }
        
        this.stockHistoryMonitor = new StockHistoryMonitor(HISTORY_MONITOR_MAX_THREAD);
        
        stockHistoryMonitor.attach(this.stockHistoryMonitorObserver);

        final Country country = jStockOptions.getCountry();

        removeOldHistoryData(country);

        StockHistorySerializer stockHistorySerializer = new StockHistorySerializer(Utils.getHistoryDirectory());

        stockHistoryMonitor.setStockHistorySerializer(stockHistorySerializer);

        stockHistoryMonitor.setDuration(Duration.getTodayDurationByYears(jStockOptions.getHistoryDuration()));
    }

    public void initLatestNewsTask()
    {
        if (jStockOptions.isAutoUpdateNewsEnabled() == true)
        {
            if (latestNewsTask == null)
            {
                latestNewsTask = new LatestNewsTask();
                latestNewsTask.execute();
            }
        }
        else
        {
            final LatestNewsTask oldLatestNewsTask = latestNewsTask;
            if (oldLatestNewsTask != null) {                
                zombiePool.execute(new Runnable() {
                    @Override
                    public void run() {
                        log.info("Prepare to shut down " + oldLatestNewsTask + "...");
                        oldLatestNewsTask.cancel(true);
                        log.info("Shut down " + oldLatestNewsTask + " peacefully.");
                    }
                });

                latestNewsTask = null;
            }
        }
    }
    
    private void initAjaxProvider() {
        Country country = this.jStockOptions.getCountry();
        this.watchListPanel.setGreedyEnabled(country);
        
        this.indicatorPanel.initAjaxProvider();
    }

    private void initGoogleCodeDatabaseRunnable() {
        final Country country = jStockOptions.getCountry();

        if (org.yccheok.jstock.engine.Utils.isGoogleCodeDatabaseRequired(country)) {
            this.singleThreadExecutor.submit(new GoogleCodeDatabaseRunnable(country));
        }    
    }
    
    private void initIEXStockInfoDatabaseRunnable() {
        if (IEXStockInfoDatabaseRunnable.needToBuild()) {
            singleThreadExecutor.submit(new IEXStockInfoDatabaseRunnable());
        }
    }
    
    private void initStockInfoDatabaseMeta() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Read existing stock-info-database-meta.json
                final Map<Country, Long> localStockInfoDatabaseMeta = Utils.loadStockInfoDatabaseMeta(Utils.getStockInfoDatabaseMetaFile());
                
                final String location = org.yccheok.jstock.network.Utils.getURL(org.yccheok.jstock.network.Utils.Type.STOCK_INFO_DATABASE_META);
                
                final String json = Utils.downloadAsString(location);
                
                final Map<Country, Long> latestStockInfoDatabaseMeta = Utils.loadStockInfoDatabaseMeta(json);

                final Map<Country, Long> successStockInfoDatabaseMeta = new EnumMap<Country, Long>(Country.class);
                
                boolean needToInitDatabase = false;
                
                // Build up list of stock-info-database.csv that needed to be
                // updated.
                for (Map.Entry<Country, Long> entry : latestStockInfoDatabaseMeta.entrySet()) {
                    if (Thread.currentThread().isInterrupted() || stockInfoDatabaseMetaPool == null) {
                        break;
                    }
                    
                    Country country = entry.getKey();
                    Long latest = entry.getValue();
                    Long local = localStockInfoDatabaseMeta.get(country);
                                        
                    if (false == latest.equals(local)) {
                        final String stocksCSVZipFileLocation = org.yccheok.jstock.engine.Utils.getStocksCSVZipFileLocation(country);

                        final File zipFile = Utils.downloadAsTempFile(stocksCSVZipFileLocation);
                        
                        if (zipFile == null) {
                            continue;
                        }
                        
                        File tempZipDirectory = null;
                        
                        try {
                            tempZipDirectory = java.nio.file.Files.createTempDirectory(null).toFile();

                            if (false == Utils.extractZipFile(zipFile, tempZipDirectory.getAbsolutePath(), true)) {
                                continue;
                            }

                            File file = new File(tempZipDirectory, "stocks.csv");

                            final java.util.List<Stock> stocks = org.yccheok.jstock.engine.Utils.getStocksFromCSVFile(file);

                            if (false == stocks.isEmpty()) {
                                final Pair<StockInfoDatabase, StockNameDatabase> stockDatabase = org.yccheok.jstock.engine.Utils.toStockDatabase(stocks, country);

                                final boolean success = JStock.saveStockInfoDatabaseAsCSV(country, stockDatabase.first);
                                
                                if (stockDatabase.second != null) {
                                    JStock.saveStockNameDatabaseAsCSV(country, stockDatabase.second);
                                }
                                
                                if (success) {
                                    successStockInfoDatabaseMeta.put(country, latest);
                                    if (country == jStockOptions.getCountry()) {
                                        needToInitDatabase = true;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            log.error(null, ex);
                        } finally {
                            if (tempZipDirectory != null) {
                                Utils.deleteDir(tempZipDirectory, true);
                            }
                        }
                    }
                }
                
                if (successStockInfoDatabaseMeta.isEmpty()) {
                    return;
                }
                
                // Retain old meta value.
                for (Map.Entry<Country, Long> entry : localStockInfoDatabaseMeta.entrySet()) {
                    Country country = entry.getKey();
                    Long old = entry.getValue();
                    
                    if (false == successStockInfoDatabaseMeta.containsKey(country)) {
                        successStockInfoDatabaseMeta.put(country, old);
                    }
                }
                
                Utils.saveStockInfoDatabaseMeta(Utils.getStockInfoDatabaseMetaFile(), successStockInfoDatabaseMeta);
                
                if (needToInitDatabase) {
                    initDatabase(true);
                }
            }           
        };
        
        stockInfoDatabaseMetaPool.execute(runnable);
    }
    
    private void initDatabase(boolean readFromDisk) {
        // Update GUI state.
        this.setStatusBar(true, GUIBundle.getString("MainFrame_ConnectingToStockServerToRetrieveStockInformation..."));
        this.statusBar.setImageIcon(getImageIcon("/images/16x16/network-connecting.png"), java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_Connecting..."));

        // Stop any on-going activities.
        // Entire block will be synchronized, as we do not want to hit by more
        // than 1 databaseTask running.
        synchronized (this.databaseTaskMonitor)
        {
            if (this.databaseTask != null)
            {
                this.databaseTask.cancel(true);
                this.stockInfoDatabase = null;
                this.stockNameDatabase = null;
                this.watchListPanel.setStockInfoDatabase(null);
                this.indicatorPanel.setStockInfoDatabase(null);
            }
            
            this.databaseTask = new DatabaseTask(readFromDisk);
            this.databaseTask.execute();
        }

        // We may hold a large database previously. Invoke garbage collector to perform cleanup.
        System.gc();
    }
        
    private void update(RealTimeStockMonitor monitor, final RealTimeStockMonitor.Result result) { 
        final java.util.List<Stock> stocks = result.stocks;
        
        // We need to ignore symbol names given by stock server. Replace them
        // with database's.
        final boolean isSymbolImmutable = org.yccheok.jstock.engine.Utils.isSymbolImmutable();                
        for (int i = 0, size = stocks.size(); i < size; i++) {
            final Stock stock = stocks.get(i);
            Stock new_stock = stock;
            // Sometimes server goes crazy by returning empty symbol.
            if (isSymbolImmutable || new_stock.symbol.toString().isEmpty()) {                
                // Use local variable to ensure thread safety.
                final StockInfoDatabase stock_info_database = this.stockInfoDatabase;
                //final StockNameDatabase name_database = this.stockNameDatabase;
                
                if (stock_info_database != null) {
                    final Symbol symbol = stock_info_database.codeToSymbol(stock.code);
                    if (symbol != null) {
                        new_stock = new_stock.deriveStock(symbol);
                    } else {
                        // Shouldn't be null. Let's give some warning on this.
                        log.error("Wrong stock code " + stock.code + " given by stock server.");
                    }
                } else {
                    // stockCodeAndSymbolDatabase is not ready yet. Use the information
                    // from stock table.
                    final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();
                    final int row = tableModel.findRow(stock);
                    if (row >= 0) {
                        final Symbol symbol = tableModel.getStock(row).symbol;
                        new_stock = new_stock.deriveStock(symbol);
                    }
                }   // if (symbol_database != null)

                // Doesn't matter, as we do not need to show "name" in table.
                // Need not to perform derive for speed optimization.
                //if (org.yccheok.jstock.engine.Utils.isNameImmutable()) {
                //    if (name_database != null) {
                //        final String name = name_database.codeToName(stock.code);
                //        if (name != null) {
                //            new_stock = new_stock.deriveStock(name);
                //        } else {
                //            // Shouldn't be null. Let's give some warning on this.
                //            log.error("Wrong stock code " + stock.code + " given by stock server.");
                //        }
                //    } else {
                //        // stockNameDatabase is not ready yet. Use the information
                //        // from stock table.
                //        final StockTableModel tableModel = (StockTableModel)jTable1.getModel();
                //        final int row = tableModel.findRow(stock);
                //        if (row >= 0) {
                //            final String name = tableModel.getStock(row).getName();
                //            new_stock = new_stock.deriveStock(name);
                //        }
                //    }
                //}

                if (stock != new_stock) {
                    stocks.set(i, new_stock);
                }
            }   // if (isSymbolImmutable || new_stock.symbol.toString().isEmpty())
        }   // for (int i = 0, size = stocks.size(); i < size; i++)
        
        if (false == stocks.isEmpty()) {
            // Update status bar with current time string.
            this.timestamp = System.currentTimeMillis();
            ((StockTableModel)watchListPanel.getTable().getModel()).setTimestamp(this.timestamp);
        }
        
        JStock.instance().updateStatusBarWithLastUpdateDateMessageIfPossible();

        // Do it in GUI event dispatch thread. Otherwise, we may face deadlock.
        // For example, we lock the jTable, and try to remove the stock from the
        // real time monitor. While we wait for the real time monitor to complete,
        // real time monitor will call this function and, be locked at function
        // updateStockToTable.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (Stock stock : stocks) {                    
                    watchListPanel.updateStockToTable(stock);
                    if (isStockBeingSelected(stock)) {
                        watchListPanel.updateDynamicChart(stock);
                    }
                }               
            }
        });

        // Dynamic charting. Intraday trader might love this.
        for (Stock stock : stocks) {
            final Code code = stock.code;
            DynamicChart dynamicChart = this.dynamicCharts.get(code);
            if (dynamicChart == null) {
                // Not found. Try to create a new dynamic chart.
                if (this.dynamicCharts.size() <= JStock.MAX_DYNAMIC_CHART_SIZE) {
                    dynamicChart = new DynamicChart();
                    this.dynamicCharts.put(code, dynamicChart);
                }
                else {
                    // Full already. Shall we remove?
                    if (this.isStockBeingSelected(stock)) {
                        Set<Code> codes = this.dynamicCharts.keySet();
                        for (Code c : codes) {
                            // Random remove. We do not care who is being removed.
                            this.dynamicCharts.remove(c);
                            if (this.dynamicCharts.size() <= JStock.MAX_DYNAMIC_CHART_SIZE) {
                                // Remove success.
                                break;
                            }
                        }
                        dynamicChart = new DynamicChart();
                        this.dynamicCharts.put(code, dynamicChart);
                    }
                }
            }   /* if (dynamicChart == null) */

            // Still null?
            if (dynamicChart == null) {
                // This usually indicate that dynamic chart list is full, and
                // no one is selecting this particular stock.
                continue;
            }

            if (this.isStockBeingSelected(stock)) {
                dynamicChart.addPriceObservation(stock.getTimestamp(), stock.getLastPrice());
                final Stock s = stock;
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        watchListPanel.updateDynamicChart(s);
                    }
                });
            }
            else {
                // Although no one is watching at us, we still need to perform notification.
                // Weird?
                dynamicChart.addPriceObservation(stock.getTimestamp(), stock.getLastPrice());
            }
        }   /* for (Stock stock : stocks) */

        // No alert is needed. Early return.
        if ((jStockOptions.isPopupMessage() == false) && (jStockOptions.isSoundEnabled() == false)) {
            return;
        }

        final StockTableModel stockTableModel = (StockTableModel) watchListPanel.getTable().getModel();

        for (Stock stock : stocks) {
            final Double fallBelow = stockTableModel.getFallBelow(stock);            
            if (fallBelow != null) {
                final Indicator indicator = Utils.getLastPriceFallBelowIndicator(fallBelow);
                indicator.setStock(stock);
                alertStateManager.alert(indicator);
            }
            else {
                 // Having FALL_BELOW_INDICATOR and RISE_ABOVE_INDICATOR, is to enable us
                 // to remove a particular indicator from AlertStateManager, without the need
                 // to call time-consuming getLastPriceFallBelowIndicator and
                 // getLastPriceRiseAboveIndicator. In order for indicator to perform
                 // correctly, we need to call indicator's mutable method (setStock).
                 // However, since FALL_BELOW_INDICATOR and RISE_ABOVE_INDICATOR are
                 // sharable variables, we are not allowed to call setStock outside
                 // synchronized block. We need to perfrom a hacking liked workaround
                 // Within syncrhonized block, call getStock (To get old stock), setStock and
                 // restore back old stock.
               
                alertStateManager.clearState(FALL_BELOW_INDICATOR, stock);
            }

            final Double riseAbove = stockTableModel.getRiseAbove(stock);
            if (riseAbove != null) {
                final Indicator indicator = Utils.getLastPriceRiseAboveIndicator(riseAbove);
                indicator.setStock(stock);
                alertStateManager.alert(indicator);
            }
            else {
                 // Having FALL_BELOW_INDICATOR and RISE_ABOVE_INDICATOR, is to enable us
                 // to remove a particular indicator from AlertStateManager, without the need
                 // to call time-consuming getLastPriceFallBelowIndicator and
                 // getLastPriceRiseAboveIndicator. In order for indicator to perform
                 // correctly, we need to call indicator's mutable method (setStock).
                 // However, since FALL_BELOW_INDICATOR and RISE_ABOVE_INDICATOR are
                 // sharable variables, we are not allowed to call setStock outside
                 // synchronized block. We need to perfrom a hacking liked workaround
                 // Within syncrhonized block, call getStock (To get old stock), setStock and
                 // restore back old stock.
               
                alertStateManager.clearState(RISE_ABOVE_INDICATOR, stock);
            }
        }
    }

    public void updateStatusBarWithLastUpdateDateMessageIfPossible() {
        if (this.refreshPriceInProgress) {
            // Stop refresh price in progress message.
            this.setStatusBar(false, getBestStatusBarMessage());
            this.refreshPriceInProgress = false;
            return;
        }
        
        if (this.isStatusBarBusy) {
            return;
        }
        
        Component selected = this.getSelectedComponent();
        if (selected != watchListPanel && selected != this.portfolioManagementJPanel
            /*&& selected != this.tradingJPanel*/) {
            return;
        }
        
        this.setStatusBar(false, getBestStatusBarMessage());
    }
    
    // Connected
    // [My Watchlist] Last update: Connected
    // [My Watchlist] Last update: 10:40AM
    public String getBestStatusBarMessage() {        
        final String currentName;
        final long _timestamp;
        
        Component selected = this.getSelectedComponent();
        
        // MainFrame
        if (selected == watchListPanel) {
            currentName = this.getJStockOptions().getWatchlistName();
            _timestamp = this.timestamp;
        } else if (selected == this.portfolioManagementJPanel) {
            currentName = this.getJStockOptions().getPortfolioName();
            _timestamp = this.portfolioManagementJPanel.getTimestamp();
        }/* else if (selected == this.tradingJPanel) {
            currentName = DriveWealthBundle.getString("Drivewealth_data_by_BATS");
            _timestamp = TradingView.getInstance().getTimestamp();
        }*/ else {
            return GUIBundle.getString("MainFrame_Connected");
        }
        
        if (_timestamp == 0) {
            return MessageFormat.format(GUIBundle.getString("MainFrame_Connected_template"), currentName);            
        }

        Date date = new Date(_timestamp);
        String time;
        if (Utils.isToday(_timestamp)) {
            time = Utils.getTodayLastUpdateTimeFormat().format(date);
        } else {
            time = Utils.getOtherDayLastUpdateTimeFormat().format(date);
        }

        return MessageFormat.format(GUIBundle.getString("MainFrame_LastUpdate_template"), currentName, time);
    }
    
    private void update(final java.util.List<Market> markets) {
        assert(markets.isEmpty() == false);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               marketJPanel.update(markets);
            }
        });
    }
    
    public void update(StockHistoryMonitor monitor, final StockHistoryMonitor.StockHistoryRunnable runnable) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Code code = runnable.getCode();
                Symbol symbol = null;

                // Use local variable to ensure thread safety.
                final StockInfoDatabase stock_info_database = JStock.this.stockInfoDatabase;
                // Is the database ready?
                if (stock_info_database != null) {
                    // Possible null if we are trying to get index history.
                    symbol = stock_info_database.codeToSymbol(code);
                }
                final boolean shouldShowGUI = JStock.this.stockCodeHistoryGUI.remove(code);
               
                if (stockCodeHistoryGUI.isEmpty()) {
                    if (runnable.getStockHistoryServer() != null) {
                        final String template = GUIBundle.getString("MainFrame_HistorySuccess_template");
                        final String message = MessageFormat.format(template, (symbol != null ? symbol : code));
                        setStatusBar(false, message);
                    }
                    else {
                        final String template = GUIBundle.getString("MainFrame_HistoryFailed_template");
                        final String message = MessageFormat.format(template, (symbol != null ? symbol : code));
                        setStatusBar(false, message);
                    }
                }
                else {
                    if (runnable.getStockHistoryServer() != null) {
                        final String template = GUIBundle.getString("MainFrame_HistorySuccessStillWaitingForHistoryTotal_template");
                        final String message = MessageFormat.format(template, (symbol != null ? symbol : code), stockCodeHistoryGUI.size());
                        setStatusBar(true, message);
                    }
                    else {
                        final String template = GUIBundle.getString("MainFrame_HistoryFailedStillWaitingForHistoryTotal_template");
                        final String message = MessageFormat.format(template, (symbol != null ? symbol : code), stockCodeHistoryGUI.size());
                        setStatusBar(true, message);
                    }
                }
               
                if ((runnable.getStockHistoryServer() != null) && shouldShowGUI) {
                    ChartJDialog chartJDialog = new ChartJDialog(JStock.this, (symbol != null ? symbol : code) + " (" + code + ")", false, runnable.getStockHistoryServer());
                    chartJDialog.setVisible(true);
                }
           } 
        });
    }

    protected ImageIcon getImageIcon(String imageIcon) {
        return new javax.swing.ImageIcon(getClass().getResource(imageIcon));
    }
    
    public IndicatorProjectManager getAlertIndicatorProjectManager()
    {
        return this.indicatorPanel.getAlertIndicatorProjectManager();
    }
    
    public void updateScanningSpeed(int speed) {
        this.realTimeStockMonitor.setDelay(speed);
        this.realTimeIndexMonitor.setDelay(speed);
        this.indicatorScannerJPanel.updateScanningSpeed(speed);
    }

    public void updateHistoryDuration(Duration historyDuration) {
        final Duration oldDuration = stockHistoryMonitor.getDuration();

        if (oldDuration.isContains(historyDuration))
        {
            this.stockHistoryMonitor.setDuration(historyDuration);
            return;
        }

        // The history files that we are going to read, their duration are
        // too short compared to historyDuration. The easiest way to overcome
        // this problem is to remove them all.
        log.info("We are going to remove all history files, due to new duration " + historyDuration + " is not within old duration " + oldDuration);

        Country[] countries = Country.values();
        for (Country country : countries)
        {
            Utils.deleteDir(Utils.getHistoryDirectory(country), false);
        }

        // Avoid from using old history monitor. History monitor contains their own memory data.
        // Since their duration are no longer valid, the memory data are no longer valid too.
        //
        this.initStockHistoryMonitor();

    }

    public void repaintTable() {
        Component c = getSelectedComponent();
        
        if(c instanceof IndicatorScannerJPanel) {
            indicatorScannerJPanel.repaintTable();
        }
        else if(c instanceof IndicatorPanel) {
            
        }
        else {
            watchListPanel.getTable().repaint();
        }
    }
    
    private void initMarketJPanel() {
        if (this.marketJPanel != null) {
            mainMarketPanel.remove(marketJPanel);            
        }
        
        this.marketJPanel = new MarketJPanel(jStockOptions.getCountry());
        mainMarketPanel.add(marketJPanel);
        mainMarketPanel.revalidate();
    }

    private void initPreloadDatabase(boolean overWrite) {
        /* No overwrite. */
        Utils.extractZipFile("database" + File.separator + "database.zip", overWrite);
    }

    private class LatestNewsTask extends SwingWorker<Void, String> {
        // Delay first update checking for 20 seconds.
        private static final int SHORT_DELAY = 20 * 1000;

        private volatile CountDownLatch doneSignal;       

        @Override
        protected void done() {
        }

        @Override
        protected void process(java.util.List<String> messages) {
            boolean show = false;

            for (String message : messages)
            {
                AutoUpdateNewsJDialog dialog = new AutoUpdateNewsJDialog(JStock.this, true);
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
        protected Void doInBackground() {
            while (!isCancelled())
            {
                try {
                    Thread.sleep(SHORT_DELAY);
                } catch (InterruptedException ex) {
                    log.info(null, ex);
                    break;
                }
                final java.util.Map<String, String> map = Utils.getUUIDValue(org.yccheok.jstock.network.Utils.getURL(org.yccheok.jstock.network.Utils.Type.NEWS_INFORMATION_TXT));
                final String newsID = JStock.this.getJStockOptions().getNewsID();
                if (newsID.equals(map.get("news_id"))) {
                    // Seen before. Quit.
                    break;
                }
                final String location = map.get("news_url");
                if (location == null) {
                    break;
                }
                doneSignal = new CountDownLatch(1);
                final String respond = org.yccheok.jstock.gui.Utils.getResponseBodyAsStringBasedOnProxyAuthOption(location);
                if (respond == null)
                {                    
                    break;
                }
                if (respond.indexOf(Utils.getJStockUUID()) < 0)
                {
                    break;
                }
                publish(respond);
                try {
                    doneSignal.await();
                } catch (InterruptedException ex) {
                    log.info(null, ex);
                    break;
                }
                // Update jStockOptions, will make this while loop break
                // in next iteration.
                jStockOptions.setNewsID(map.get("news_id"));
            }

            return null;
        }
    }

    public Component getSelectedComponent() {
        return this.jTabbedPane1.getSelectedComponent();
    }
    
    // Unlike a JButton, setIcon() does not add an icon to the text label. 
    // Rather, in a radio button, the method is used to customize the icons used
    // to depict its state. However, by using the HTML capabilities in a label, 
    // it is possible to add an icon to the label without affecting the 
    // state-depicting icons.
    // We need to have image files being extracted outside executable jar file.
    /*
    private void initExtraDatas() {
        // No overwrite.
        Utils.extractZipFile("extra" + File.separator + "extra.zip", false);
    }
    */
    
    public void initDynamicChartVisibility() {
        watchListPanel.setDynamicChartVisible();
    }

    private void initDynamicCharts()
    {
        dynamicCharts.clear();
    }

    private void initStatusBar()
    {
        final String message = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_ConnectingToStockServerToRetrieveStockInformation...");
        final ImageIcon icon = getImageIcon("/images/16x16/network-connecting.png");
        final String iconMessage = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString("MainFrame_Connecting...");
        
        statusBar.setMainMessage(message)
                .setImageIcon(icon, iconMessage)
                .setCountryIcon(jStockOptions.getCountry().icon, jStockOptions.getCountry().humanString);
    }
    
    protected void refreshExchangeRateMonitor() {
        this.portfolioManagementJPanel.refreshExchangeRateMonitor();
    }
    
    public void refreshAllRealTimeStockMonitors() {
        RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
        if (_realTimeStockMonitor != null) {
            _realTimeStockMonitor.refresh();
        }
        this.indicatorScannerJPanel.refreshRealTimeStockMonitor();
        this.portfolioManagementJPanel.refreshRealTimeStockMonitor();
    }

    public void refreshRealTimeIndexMonitor() {        
        RealTimeIndexMonitor _realTimeIndexMonitor = this.realTimeIndexMonitor;
        if (_realTimeIndexMonitor != null) {
            _realTimeIndexMonitor.refresh();
        }
    }
    
    // Class Instances
    private Main_JMenuBar menuBar;
    private JTabbedPane jTabbedPane1;
    
    private JPanel mainMarketPanel;
    private MarketJPanel marketJPanel;
    protected WatchListJPanel watchListPanel;
    protected IndicatorPanel indicatorPanel;
    protected IndicatorScannerJPanel indicatorScannerJPanel;
    protected PortfolioManagementJPanel portfolioManagementJPanel;
    
    private int watchListTabIndex;
    private int stockIndicatorTabIndex;
    private int indicatorScannerTabIndex;
    private int portfolioTabIndex;
    
    private static final Log log = LogFactory.getLog(JStock.class);
        
    private final MyJXStatusBar statusBar = new MyJXStatusBar();
    private boolean isStatusBarBusy = false;
    
    // A set of stock history which we need to display GUI on them, when user request explicitly.
    protected final java.util.Set<Code> stockCodeHistoryGUI = new java.util.HashSet<>();
    
    protected volatile StockInfoDatabase stockInfoDatabase = null;
    // StockNameDatabase is an optional item.
    protected volatile StockNameDatabase stockNameDatabase = null;
    
    protected RealTimeStockMonitor realTimeStockMonitor = null;
    private RealTimeIndexMonitor realTimeIndexMonitor = null;
    protected StockHistoryMonitor stockHistoryMonitor = null;

    private DatabaseTask databaseTask = null;
    private final Object databaseTaskMonitor = new Object();

    private LatestNewsTask latestNewsTask = null;
    protected JStockOptions jStockOptions;
    private UIOptions uiOptions;
    private ChartJDialogOptions chartJDialogOptions;
    
    protected final AlertStateManager alertStateManager = new AlertStateManager();
    private final ExecutorService systemTrayAlertPool = Executors.newFixedThreadPool(1);
    private volatile ExecutorService stockInfoDatabaseMetaPool = Executors.newFixedThreadPool(1);
    private volatile ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(1);
    
    private final org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result> realTimeStockMonitorObserver = this.getRealTimeStockMonitorObserver();
    private final org.yccheok.jstock.engine.Observer<RealTimeIndexMonitor, java.util.List<Market>> realTimeIndexMonitorObserver = this.getRealTimeIndexMonitorObserver();
    private final org.yccheok.jstock.engine.Observer<StockHistoryMonitor, StockHistoryMonitor.StockHistoryRunnable> stockHistoryMonitorObserver = this.getStockHistoryMonitorObserver();
    private final org.yccheok.jstock.engine.Observer<Indicator, Boolean> alertStateManagerObserver = this.getAlertStateManagerObserver();

    private final Executor zombiePool = Utils.getZoombiePool();

    // Use ConcurrentHashMap, enable us able to read and write using different
    // threads.
    protected final java.util.Map<Code, DynamicChart> dynamicCharts = new java.util.concurrent.ConcurrentHashMap<Code, DynamicChart>();
    
    // We have 720 (6 * 60 * 2) points per chart, based on 10 seconds per points, with maximum 2 hours.
    // By having maximum 10 charts, we shall not face any memory problem.
    private static final int MAX_DYNAMIC_CHART_SIZE = 10;
    
    private static final int HISTORY_MONITOR_MAX_THREAD = 4;
    
    /*
     * Having FALL_BELOW_INDICATOR and RISE_ABOVE_INDICATOR, is to enable us
     * to remove a particular indicator from AlertStateManager, without the need
     * to call time-consuming getLastPriceFallBelowIndicator and
     * getLastPriceRiseAboveIndicator. In order for indicator to perform
     * correctly, we need to call indicator's mutable method (setStock).
     * However, since FALL_BELOW_INDICATOR and RISE_ABOVE_INDICATOR are
     * sharable variables, we are not allowed to call setStock outside
     * synchronized block. We need to perfrom a hacking liked workaround
     * Within syncrhonized block, call getStock (To get old stock), setStock and 
     * restore back old stock.
     */
    private static final Indicator FALL_BELOW_INDICATOR = Utils.getLastPriceFallBelowIndicator(0.0);
    private static final Indicator RISE_ABOVE_INDICATOR = Utils.getLastPriceRiseAboveIndicator(0.0);

    // Do we need to save user defined database when we switch country or close
    // this application?
    private volatile boolean needToSaveUserDefinedDatabase = false;

    private volatile boolean isFormWindowClosedCalled = false;
    
    // The last time when we receive stock price update.
    protected long timestamp = 0;
    protected boolean refreshPriceInProgress = false;  
}
