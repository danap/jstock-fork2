/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.34 04/11/2019
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
//         1.0.7.37.21 03/15/2019 Commented Class Instances indicatorPanel, indicatorScannerJPanel, stock
//                                IndicatorIndex, indicatorScannerIndex, alertStateManager, systemTrayAlert
//                                Pool & alertStateManagerObserver. Method init() Commented createStock
//                                IndicatorEditor(), createIndicatorScannerJPanel(), initAlertStateManager(),
//                                & initOthersStockHistoryMonitor(). Method handleJTabbedPaneStateChanged()
//                                Commented indicatorPanel & indicatorScannerJPanel Same in save(), & 
//                                Throughout As This Feature IndicatorEditor & IndicatorScanner Removed.
//                                Same With AlertStateManager.
//         1.0.7.37.22 03/15/2019 Cleaned Out Commented Imports, Instances, Methods Mainly From 1.0.7.37.21.
//                                Over 5000 Lines of Code Now Down to Around 2600. Still Not Done.
//         1.0.7.37.23 03/17/2019 Formatted. Began Organizing Code. Removed Inner Classes DatabaseTake &
//                                LatestNewsTask to Separate Classes to org.yccheok.jstock.gui.
//         1.0.7.37.24 03/18/2019 Commented Unused Imports. Made Class Instance log & DatabaseTask,
//                                Protected. Commented Methods initStatusBar(), & All initMyJXStatusBarXXX
//                                MouseAdapter()s. Called All These Commented Method Directly to statusBar
//                                Class, Where Moved. Changed Method initDatabase() to Protected.
//         1.0.7.37.25 03/18/2019 Cleaned Out Commented Code From 1.0.7.37.24.
//         1.0.7.37.26 03/21/2019 Commented Class Instance latestNewsTask, Along With initLatestNewsTask()
//                                in init(). Method formWindowClosed() Removed Shutting Down latestNewsTask.
//                                Method update() Cleaned Up, Commented, Alert for Popup Message & Sound.
//                                Method main() Apply Font Used jStockOptions.getFontSize().
//         1.0.7.37.27 03/22/2019 Cleaned Out Commented Code From 1.0.7.37.26. Method init() Commented
//                                createPortfolioManagementJPanel() & Moved That Code to initComponents().
//                                Removed saveWatchList() & Referenced to saveCSVWatchList() & Corrected
//                                Spelling. Removed displayHistoryCharts() & displayStockNews() Moved
//                                to WatchListJPanel.
//         1.0.7.37.28 03/25/2019 Commented Unused Imports. Removed Class Instance mainMarketPanel & Did
//                                Not Instantiate statusBar as a final Class Instance. Method init()
//                                Began Organizing/Commented Method Calls, & Placing Them in the Code
//                                in the Order Called. Simplified by Commenting statusBar.initStatusBar(),
//                                initMarketJPanel(), watchListPanel.initTableHeaderToolTips(), and statusBar.
//                                initXXX(). Arranged Order of Main Method Calls in init() to Match
//                                changeCountry(). Method initKeyBindings() Simplified by Adding Helper
//                                Method tabListsNavigation(). Commented Methods formWindowIconified()
//                                & formMouseClicked(), Moved Code to Where Called. Commented Methods
//                                loadUserDefinedDatabaseFromCSV(), loadStockNameDatabaseFromCSV(), & load
//                                StockInfoDatabaseFromCSV() to DatabaseTask Where They Belong. Commented
//                                initMarketJPanel() & initPreloadDatabase(), Just Confusing Trash Coding.
//         1.0.7.37.29 03/26/2019 Cleaned Out Commented Code From 1.0.7.37.28.
//         1.0.7.37.30 03/30/2019 Continued Organizing Ordering Methods. Moved getStockOptionsViaXML(),
//                                initJStockOptions(), & initStockInfoDatabaseMeta(). Modified jStockOptions.
//                                scanningSpeed as Needed in initJStockOptions(). Method initComponents()
//                                Added portfolioManagementJPanel.updateDividerLocation() Directly Rather
//                                Than in main(), Commented That Same Method Here. Method initKeyBindings()
//                                & update() Removed Reference to, THIS, Class as JStock.instance(). All
//                                of a Classes Instances & Methods Do Not Need to Be Referenced by Either
//                                this.xxx or That Horrible Coding Decision. Commented Methods saveCSV
//                                Watchlist() & private saveAsCSVFile(), Referenced to watchListPanel.
//                                Added Method getWatchListPanel() & Commented Inner Class CSVWatchlist.
//         1.0.7.37.31 04/01/2019 Cleaned Out Commented Code From 1.0.7.37.30. Moved initKeyBindings()
//                                Ordering With Setup of Frame. Moved initGoogleCodeDatabaseRunnable()
//                                & initIEXStockInfoDatabaseRunnable() to Method Order Sequencing. Modified
//                                tabListsNaviagtion() to Simplify initKeyBindings().
//         1.0.7.37.32 04/04/2019 Minor Cleanup. Method initChartJDialogOption() Commented Logging Info.
//                                Method initStockInfoDatabaseMeta() Additional Comments & Conditional
//                                Logic Simplification. Commented Methods Instance needToInitDatabase.
//                                Moved initDatabase() to Proper Order Sequencing & Commented its Purpose.
//                                Method changeCountry() Commented Calling Classes Not in 1.0.7.9, New,
//                                Activity That Does Not Seemed to Effect Desired Functionaliy & Has Been
//                                Disabled.
//         1.0.7.37.33 04/09/2019 Additional Comments in init(). Moved Methods initAjaxProvider() &
//                                initRealTimeIndexMonitor() to Proper Sequencing as Dictated by init().
//                                Removed initOthersStockHistoryMonitor() Method That Was Not Cleaned
//                                Out From Version 1.0.7.37.21.

//         1.0.7.37.34 04/11/2019 Moved initStockHistoryMonitor() to Order as Dicated by init().
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yccheok.jstock.engine.Code;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.Duration;
import org.yccheok.jstock.engine.Factories;
import org.yccheok.jstock.engine.Index;
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
import org.yccheok.jstock.network.ProxyDetector;
import org.yccheok.jstock.watchlist.CSVWatchList;

import com.google.api.client.auth.oauth2.Credential;

/**
 * @author doraemon
 * @author Dana M. Proctor
 * @version 1.0.7.37.34 04/11/2019
 */

public class JStock extends javax.swing.JFrame
{
   // Class Instances
   private static final long serialVersionUID = 3554990056522905135L;
   
   public static final String VERSION = "1.0.7.37.34";
   
   private Main_JMenuBar menuBar;
   private JTabbedPane jTabbedPane1;

   private MarketJPanel marketJPanel;
   protected WatchListJPanel watchListPanel;
   protected PortfolioManagementJPanel portfolioManagementJPanel;
   protected MyJXStatusBar statusBar;
   
   private int watchListTabIndex;
   private int portfolioTabIndex;
   private boolean isStatusBarBusy = false;

   protected static final Log log = LogFactory.getLog(JStock.class);
   
   // A set of stock history which we need to display
   // GUI on them, when user request explicitly.
   protected final Set<Code> stockCodeHistoryGUI = new HashSet<>();

   protected volatile StockInfoDatabase stockInfoDatabase = null;
   // StockNameDatabase is an optional item.
   protected volatile StockNameDatabase stockNameDatabase = null;

   private RealTimeIndexMonitor realTimeIndexMonitor = null;
   protected RealTimeStockMonitor realTimeStockMonitor = null;
   protected StockHistoryMonitor stockHistoryMonitor = null;

   protected DatabaseTask databaseTask = null;
   protected final Object databaseTaskMonitor = new Object();
   
   protected JStockOptions jStockOptions;
   private UIOptions uiOptions;
   private ChartJDialogOptions chartJDialogOptions;

   private volatile ExecutorService stockInfoDatabaseMetaPool = Executors.newFixedThreadPool(1);
   private volatile ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(1);

   private final org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result>
                 realTimeStockMonitorObserver = getRealTimeStockMonitorObserver();
   private final org.yccheok.jstock.engine.Observer<RealTimeIndexMonitor, java.util.List<Market>>
                 realTimeIndexMonitorObserver = getRealTimeIndexMonitorObserver();
   private final org.yccheok.jstock.engine.Observer<StockHistoryMonitor,
                                                    StockHistoryMonitor.StockHistoryRunnable>
                 stockHistoryMonitorObserver = getStockHistoryMonitorObserver();

   private final Executor zombiePool = Utils.getZoombiePool();

   // Use ConcurrentHashMap, enable us able to read
   // and write using different threads.
   protected final Map<Code, DynamicChart>
                   dynamicCharts = new ConcurrentHashMap<Code, DynamicChart>();

   // We have 720 (6 * 60 * 2) points per chart, based
   // on 10 seconds per points, with maximum 2 hours.
   // By having maximum 10 charts, we shall not face
   // any memory problem.
   private static final int MAX_DYNAMIC_CHART_SIZE = 10;

   private static final int HISTORY_MONITOR_MAX_THREAD = 4;

   // Do we need to save user defined database when
   // we switch country or close this application?
   private volatile boolean needToSaveUserDefinedDatabase = false;

   private volatile boolean isFormWindowClosedCalled = false;

   // The last time when we receive stock price update.
   protected long timestamp = 0;
   protected boolean refreshPriceInProgress = false;
   
   // Comment out, to avoid annoying log messages during debugging.
   
   // static { System.setProperty("org.apache.commons.logging.Log",
   // "org.apache.commons.logging.impl.NoOpLog"); }

   /** Creates new form MainFrame */

   // Private constructor is sufficient to suppress
   // unauthorized calls to the constructor
   private JStock()
   {
   }
   
   /*
   public JStock(JStockOptions jStockOptions, Log log)
   {
      this.jStockOptions = jStockOptions;
      this.log = log;
      
      init();
      setVisible(true);
      updateDividerLocation();
      watchListPanel.requestFocusOnJComboBox();
   }
   */
   
   /**
    * MainFrameHolder is loaded on the first execution of
    * Singleton.getInstance() or the first access to
    * MainFrameHolder.INSTANCE, not before.
    */
   private static class MainFrameHolder
   {
      private final static JStock INSTANCE = new JStock();
   }

   /**
    * Returns MainFrame as singleton.
    * @return MainFrame as singleton
    */
   public static JStock instance()
   {
      return MainFrameHolder.INSTANCE;
   }
   
   //==============================================================
   // Method to load via XML or create as needed JStockOptions.
   //==============================================================
   
   private static JStockOptions getJStockOptionsViaXML()
   {
      // Method Instances.
      File optionsFile;
      JStockOptions jStockOptions;
      
      optionsFile = new File(UserDataDirectory.Config.get() + UserDataFile.OptionsXml.get());
      jStockOptions = Utils.fromXML(JStockOptions.class, optionsFile);
      
      if (jStockOptions == null)
         jStockOptions = new JStockOptions();
      
      return jStockOptions;
   }
   
   //==============================================================
   // Initialize jStockOptions and assigning price source.
   //==============================================================
   
   private void initJStockOptions(JStockOptions jStockOptions)
   {
      this.jStockOptions = jStockOptions;

      /* Hard core fix. */
      if (this.jStockOptions.getScanningSpeed() < JStockOptions.MINIMUM_SCANNING_SPEED)
         this.jStockOptions.setScanningSpeed(JStockOptions.DEFAULT_SCANNING_SPEED);

      // Proxy
      final String proxyHost = this.jStockOptions.getProxyServer();
      final int proxyPort = this.jStockOptions.getProxyPort();

      if ((proxyHost.length() > 0) && (org.yccheok.jstock.engine.Utils.isValidPortNumber(proxyPort)))
      {
         System.getProperties().put("http.proxyHost", proxyHost);
         System.getProperties().put("http.proxyPort", "" + proxyPort);
      }
      else
      {
         System.getProperties().remove("http.proxyHost");
         System.getProperties().remove("http.proxyPort");
      }

      // Assigns price source to each country in engine.Factories,
      // either default or menu selected Country and network option
      // Price Source.
      
      Utils.updateFactoriesPriceSource();
   }
   
   //==============================================================
   // Initialize this MainFrame based on the JStockOptions and
   // other configuration files.
   //==============================================================
   
   private void init()
   {
      // Method Instances.
      ResourceBundle bundle;
      
      // Resource
      bundle = ResourceBundle.getBundle("org/yccheok/jstock/data/gui");
      
      // Setup Frame Aspects
      setTitle(bundle.getString("MainFrame_Application_Title"));
      setIconImage(new ImageIcon(getClass().getResource("/images/128x128/chart.png")).getImage());
      setSize(new java.awt.Dimension(800, 600));
      setLocationRelativeTo(null); // Centers
      
      setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
      
      addWindowListener(new java.awt.event.WindowAdapter()
      {
         public void windowClosed(java.awt.event.WindowEvent evt)
         {
            formWindowClosed(evt);
         }

         public void windowClosing(java.awt.event.WindowEvent evt)
         {
            dispose();
         }

         public void windowDeiconified(java.awt.event.WindowEvent evt)
         {
            // Nothing to Do.
         }

         public void windowIconified(java.awt.event.WindowEvent evt)
         {
            // Calling setVisible(false) will cause modal dialog box to be unblocked
            // for JDialog.setVisible(true). This will happen in Linux system where
            // user are allowed to minimize window even there is a modal JDialog box
            // We have no solution at current moment.
            //

            if (Utils.isWindows())
               setVisible(false);
         }
      });
      
      initKeyBindings();
      
      addMouseListener(new java.awt.event.MouseAdapter()
      {
         public void mouseClicked(java.awt.event.MouseEvent evt)
         {
            watchListPanel.getTable().getSelectionModel().clearSelection();
            portfolioManagementJPanel.clearTableSelection();
            watchListPanel.updateDynamicChart(null);
         }
      });
      
      // Start Initializing components and other
      // requirements.
      
      initComponents(bundle);
      
      // Extract, if does not exists, zipped file of stock listing
      // company names and other attributes by country to user's
      // directory under, .jstock. These are files in csv format
      // and provided in database/database.zip.
      
      Utils.extractZipFile(DatabaseTask.ZIP_DATABASE_FILE_NAME, false);
      
      // Initial various configuration
      // Options for the GUI.
      
      initUIOptions();
      initGUIOptions();
      initChartJDialogOptions();
      
      // WatchListJPanel stock selector search
      // feature via AjaxAutoCompleteJComboBox.
      
      initStockInfoDatabaseMeta();
      initGoogleCodeDatabaseRunnable();
      initIEXStockInfoDatabaseRunnable();
      initDatabase(true);
      initAjaxProvider();
      
      // Comment here.
      
      initRealTimeIndexMonitor();
      initStockHistoryMonitor();
      initExchangeRateMonitor();
      initRealTimeStockMonitor();
      
      // Finalize setup of WatchListJPanel
      // components.
      
      watchListPanel.initWatchlist();
      initDynamicCharts();
      initDynamicChartVisibility();
      watchListPanel.addAutoCompleteComboBox();

      // Turn to the last viewed page.
      final int lastSelectedPageIndex = getJStockOptions().getLastSelectedPageIndex();
      
      if (jTabbedPane1.getTabCount() > lastSelectedPageIndex)
         jTabbedPane1.setSelectedIndex(lastSelectedPageIndex);

      // setSelectedIndex will not always trigger jTabbedPane1StateChanged,
      // if the selected index is same as current page index. However, we are
      // expecting jTabbedPane1StateChanged to suspend/resume
      // PortfolioManagementJPanel's RealtTimeStockMonitor and MainFrame's
      // CurrencyExchangeMonitor, in order to preserve network resource. Hence,
      // we need to call handleJTabbedPaneStateChanged explicitly.
      handleJTabbedPaneStateChanged(jTabbedPane1);

      // Restore previous size and location.
      JStockOptions.BoundsEx boundsEx = jStockOptions.getBoundsEx();
      if (boundsEx == null)
         // First time. Maximize it.
         setExtendedState(Frame.MAXIMIZED_BOTH);
      else
      {
         if ((boundsEx.extendedState & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH)
            setExtendedState(Frame.MAXIMIZED_BOTH);
         else
            setBounds(boundsEx.bounds);
      }

      installShutdownHook();

      BackwardCompatible.removeGoogleCodeDatabaseIfNecessary();
   }
   
   //==============================================================
   // Method to allow selecting WatchList & Portfolio Tabs via the
   // keyboard. Also will toggle between lists for each tab.
   //==============================================================
   
   private void initKeyBindings()
   {
      KeyStroke watchlistNavigationKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_W,
         java.awt.event.InputEvent.CTRL_MASK);
      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(watchlistNavigationKeyStroke,
            "watchlistNavigation");
      
      KeyStroke portfolioNavigationKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P,
         java.awt.event.InputEvent.CTRL_MASK);
      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(portfolioNavigationKeyStroke,
         "portfolioNavigation");
      
      getRootPane().getActionMap().put("watchlistNavigation", new AbstractAction()
      {
         private static final long serialVersionUID = 5097111889683602733L;

         @Override
         public void actionPerformed(ActionEvent e)
         {
            tabListsNavigation("watchlist", org.yccheok.jstock.watchlist.Utils.getWatchlistNames());
         }
      });
      getRootPane().getActionMap().put("portfolioNavigation", new AbstractAction()
      {
         private static final long serialVersionUID = -4296921725513282997L;

         @Override
         public void actionPerformed(ActionEvent e)
         {
            tabListsNavigation("portfolio", org.yccheok.jstock.portfolio.Utils.getPortfolioNames());
         }
      });
   }
   
   private void tabListsNavigation(String sourceKey, java.util.List<String> tabListNames)
   {  
      // Method Instances.
      String listName;
      int index;
      
      if (sourceKey.equals("portfolio") && getSelectedComponent() != portfolioManagementJPanel)
      {
         jTabbedPane1.setSelectedIndex(portfolioTabIndex);
         return;
      }
      
      if (sourceKey.equals("watchlist") && getSelectedComponent() != watchListPanel)
      {
         jTabbedPane1.setSelectedIndex(watchListTabIndex);
         return;
      }
      
      if (tabListNames.size() <= 1)
         return;
      
      if (getSelectedComponent() == watchListPanel)
         listName = getJStockOptions().getWatchlistName();
      else if (getSelectedComponent() == portfolioManagementJPanel)
         listName = getJStockOptions().getPortfolioName();
      else
         return;
      
      index = 0;

      for (; index < tabListNames.size(); index++)
         if (tabListNames.get(index).equals(listName))
         {
            index++;
            if (index >= tabListNames.size())
               index = 0;
            break;
         }
      
      if (getSelectedComponent() == watchListPanel)
         selectActiveWatchlist(tabListNames.get(index));
      else
         selectActivePortfolio(tabListNames.get(index));
   }
   
   //==============================================================
   // Main method to setup the major graphical components of the
   // application, frame.
   //==============================================================
   
   private void initComponents(ResourceBundle bundle)
   {
      // Start Adding Main Components
      getContentPane().setLayout(new java.awt.BorderLayout());

      // North Market Panel
      marketJPanel = new MarketJPanel(jStockOptions.getCountry());
      getContentPane().add(marketJPanel, java.awt.BorderLayout.NORTH);
      
      // South Status Bar
      javax.swing.JPanel statusBarPanel = new javax.swing.JPanel();
      
      statusBar = new MyJXStatusBar();
      statusBarPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
      statusBarPanel.setLayout(new java.awt.GridLayout(1, 1));
      statusBarPanel.add(statusBar);
      
      getContentPane().add(statusBarPanel, java.awt.BorderLayout.SOUTH);

      // Center Tabbed Pane
      jTabbedPane1 = new javax.swing.JTabbedPane();
      jTabbedPane1.setFont(jTabbedPane1.getFont().deriveFont(
         jTabbedPane1.getFont().getStyle() | java.awt.Font.BOLD, jTabbedPane1.getFont().getSize() + 2));
      jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      
      jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener()
      {
         public void stateChanged(javax.swing.event.ChangeEvent evt)
         {
            JTabbedPane pane = (JTabbedPane) evt.getSource();
            handleJTabbedPaneStateChanged(pane);
         }
      });

      watchListPanel = new WatchListJPanel();
      watchListTabIndex = jTabbedPane1.getTabCount();
      jTabbedPane1.addTab(bundle.getString("MainFrame_Title"), watchListPanel);
      jTabbedPane1.setIconAt(watchListTabIndex, this.getImageIcon("/images/16x16/stock_timezone.png"));
      jTabbedPane1.setToolTipTextAt(watchListTabIndex, ResourceBundle.getBundle(
         "org/yccheok/jstock/data/gui").getString("MainFrame_WatchYourFavoriteStockMovement"));
      
      portfolioManagementJPanel = new PortfolioManagementJPanel();
      portfolioTabIndex = jTabbedPane1.getTabCount();
      jTabbedPane1.addTab(GUIBundle.getString("PortfolioManagementJPanel_Title"), portfolioManagementJPanel);
      jTabbedPane1.setIconAt(portfolioTabIndex, this.getImageIcon("/images/16x16/calc.png"));
      jTabbedPane1.setToolTipTextAt(portfolioTabIndex, ResourceBundle.getBundle(
         "org/yccheok/jstock/data/gui").getString(
            "MainFrame_ManageYourRealTimePortfolioWhichEnableYouToTrackBuyAndSellRecords"));

      getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
      
      portfolioManagementJPanel.updateDividerLocation();
      
      // MenuBar
      menuBar = new Main_JMenuBar(bundle, log);
      setJMenuBar(menuBar);
   }
   
   //==============================================================
   // Method to setup the UI options associated with various dialog
   // frames.
   //
   // NewBuy/SellTransactionJDialog, DepositSummaryJDialog, 
   // DividendSummaryJDialog, & AutoDividendJDialog.
   // 
   // Not Used:
   // FairUsagePolicyJDialog. 
   //==============================================================
   
   private void initUIOptions()
   {
      File file = new File(UserDataDirectory.Config.get() + UserDataFile.UIOptionsJson.get());
      uiOptions = Utils.fromJson(file, UIOptions.class);
      
      if (uiOptions == null)
         uiOptions = new UIOptions();
   }
   
   //==============================================================
   // Method to setup the GU options associated with Locale and
   // JTableOptions having do with names, column view/sizes for the
   // Watch List. 
   //==============================================================
   
   private void initGUIOptions()
   {
      final File f = new File(UserDataDirectory.Config.get() + UserDataFile.MainFrameXml.get());
      GUIOptions guiOptions = Utils.fromXML(GUIOptions.class, f);

      if (guiOptions == null || guiOptions.getJTableOptionsSize() <= 0)
      {
         // When user launches JStock for first time, we will help him to
         // turn off the following column(s), as we feel those information
         // is redundant. If they wish to view those information, they have
         // to turn it on explicitly.
         JTableUtilities.removeTableColumn(watchListPanel.getTable(), GUIBundle.getString("MainFrame_Open"));
      }
      /* Set Table Settings */
      else
         JTableUtilities.setJTableOptions(watchListPanel.getTable(), guiOptions.getJTableOptions(0));
   }
   
   //==============================================================
   // Method to setup the ChartJDialogOptions configuration that
   // has to do with the stock and market history charts. These
   // should have separate property configurations.
   //==============================================================
   
   private void initChartJDialogOptions()
   {
      final File f = new File(UserDataDirectory.Config.get() + UserDataFile.ChartJDialogOptionsXml.get());
      
      chartJDialogOptions = Utils.fromXML(ChartJDialogOptions.class, f);
      
      if (chartJDialogOptions == null)
         chartJDialogOptions = new ChartJDialogOptions();
      // else
      //    log.info("chartJDialogOptions loaded from " + f.toString() + " successfully.");   
   }
   
   //==============================================================
   // Method reads local configuration file stock-info-database-meta.json
   // with com.google.gson.GsonBuilder, package gson-2.6.2.jar,
   // containing Country with a ID, then compares to appengine
   // Internet same file. If there is a difference then updates
   // via download to a temporary csv file, that is used to update
   // country configuaration folder database/stock-info-database.csv.
   // 
   // Stopped following by commenting needToInitDatabase, see
   // comment below in code.
   //
   // Selected country has database reinitialized, as needed,
   // with call to initDatabase(true).
   //==============================================================
   
   private void initStockInfoDatabaseMeta()
   {
      Runnable runnable = new Runnable()
      {
         @Override
         public void run()
         {
            // Read existing stock-info-database-meta.json
            final Map<Country, Long> localStockInfoDatabaseMeta = Utils.loadStockInfoDatabaseMeta(Utils
                  .getStockInfoDatabaseMetaFile());

            // Uses Github to serve as source of files.
            // https://raw.githubusercontent.com/yccheok/jstock/master/appengine/jstock-static/war/
            // stocks_information/stock-info-database-meta.json
            
            final String location = org.yccheok.jstock.network.Utils
                  .getURL(org.yccheok.jstock.network.Utils.Type.STOCK_INFO_DATABASE_META);

            final String json = Utils.downloadAsString(location);
            
            if (json.isEmpty())
               log.info("JStock initStockInfoDatabase() Download:stock-info-database-meta.json EMPTY");
            else
               log.info("JStock initStockInfoDatabase() Download:stock-info-database-meta.json SUCCESSFUL");
               
            
            final Map<Country, Long> latestStockInfoDatabaseMeta = Utils.loadStockInfoDatabaseMeta(json);
            final Map<Country, Long> successStockInfoDatabaseMeta = new EnumMap<Country, Long>(Country.class);

            //boolean needToInitDatabase = false;

            // Build up list of stock-info-database.csv that needed to be
            // updated.
            for (Map.Entry<Country, Long> entry : latestStockInfoDatabaseMeta.entrySet())
            {
               if (Thread.currentThread().isInterrupted() || stockInfoDatabaseMetaPool == null)
                  break;

               Country country = entry.getKey();
               Long latest = entry.getValue();
               Long local = localStockInfoDatabaseMeta.get(country);
               
               // log.info("latest:local " + country + ":" + latest + ":" + local);

               if (!latest.equals(local))
               {
                  final String stocksCSVZipFileLocation = org.yccheok.jstock.engine.Utils
                        .getStocksCSVZipFileLocation(country);
                  
                  // https://raw.githubusercontent.com/yccheok/jstock/master/appengine/jstock-static/war/
                  // stocks_information/unitedstate/stocks.zip
                  log.info("JStock initStockInfoDatabase() latest!=locale stocksCSVZipFileLocation: "
                           + stocksCSVZipFileLocation);

                  final File zipFile = Utils.downloadAsTempFile(stocksCSVZipFileLocation);

                  if (zipFile == null)
                     continue;

                  File tempZipDirectory = null;

                  try
                  {
                     tempZipDirectory = java.nio.file.Files.createTempDirectory(null).toFile();

                     if (false == Utils.extractZipFile(zipFile, tempZipDirectory.getAbsolutePath(), true))
                        continue;

                     // Normal default os tmp directory.
                     File file = new File(tempZipDirectory, "stocks.csv");

                     final java.util.List<Stock> stocks = org.yccheok.jstock.engine.Utils
                           .getStocksFromCSVFile(file);

                     if (!stocks.isEmpty())
                     {
                        final Pair<StockInfoDatabase, StockNameDatabase> stockDatabase = 
                              org.yccheok.jstock.engine.Utils.toStockDatabase(stocks, country);

                        final boolean success = JStock.saveStockInfoDatabaseAsCSV(country,
                           stockDatabase.first);

                        if (stockDatabase.second != null)
                           JStock.saveStockNameDatabaseAsCSV(country, stockDatabase.second);

                        if (success)
                        {
                           successStockInfoDatabaseMeta.put(country, latest);
                           
                           //if (country == jStockOptions.getCountry())
                           //   needToInitDatabase = true;
                        }
                     }
                  }
                  catch (IOException ex)
                  {
                     log.error("JStock.initStockInfoDatabaseMeta()", ex);
                  }
                  finally
                  {
                     if (tempZipDirectory != null)
                        Utils.deleteDir(tempZipDirectory, true);
                  }
               }
            }

            if (successStockInfoDatabaseMeta.isEmpty())
               return;

            // Retain old meta value.
            for (Map.Entry<Country, Long> entry : localStockInfoDatabaseMeta.entrySet())
            {
               Country country = entry.getKey();
               Long old = entry.getValue();

               if (!successStockInfoDatabaseMeta.containsKey(country))
                  successStockInfoDatabaseMeta.put(country, old);
            }

            Utils.saveStockInfoDatabaseMeta(Utils.getStockInfoDatabaseMetaFile(),
                                            successStockInfoDatabaseMeta);

            // This is not required because this method is only called
            // from init() & initDatabase(true) will be called shortly
            // from there. Useless wasteful performance degradation.
            
            //if (needToInitDatabase)
            //   initDatabase(true);
         }
      };
      stockInfoDatabaseMetaPool.execute(runnable);
   }
   
   //==============================================================
   // See docs notes engine fabrication.
   //==============================================================
   
   private void initGoogleCodeDatabaseRunnable()
   { 
      /*
      final Country country = jStockOptions.getCountry();

      if (org.yccheok.jstock.engine.Utils.isGoogleCodeDatabaseRequired(country))
      {
          this.singleThreadExecutor.submit(new GoogleCodeDatabaseRunnable(country));
      }
      */ 
   }
   
   //==============================================================
   // See docs notes engine fabrication.
   //==============================================================
   
   private void initIEXStockInfoDatabaseRunnable()
   {
      /*
      if (IEXStockInfoDatabaseRunnable.needToBuild())
         singleThreadExecutor.submit(new IEXStockInfoDatabaseRunnable());
      */
   }
   
   //==============================================================
   // Method to be used to load the current selected countries'
   // database/stock-info-database.csv contents of stock listings
   // into data structures for use. The core object is Stock, with
   // an additional class StockInfo, that can be used for just Code
   // and Symbol.
   //
   // Data Structures: StockInfoDatabase & StockNameDatabase.
   // Instances: stockInfoDatabase, stockNameDatabase.
   // 
   // Also called from MyJXStatusBar, can select country by double
   // clicking flag.
   //==============================================================
   
   protected void initDatabase(boolean readFromDisk)
   {
      // Update GUI state.
      setStatusBar(true,
                   GUIBundle.getString("MainFrame_ConnectingToStockServerToRetrieveStockInformation..."));
      statusBar.setImageIcon(
         getImageIcon("/images/16x16/network-connecting.png"),
         java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
            "MainFrame_Connecting..."));

      // Stop any on-going activities.
      // Entire block will be synchronized, as we do not want to hit by more
      // than 1 databaseTask running.
      synchronized (databaseTaskMonitor)
      {
         if (databaseTask != null)
         {
            databaseTask.cancel(true);
            stockInfoDatabase = null;
            stockNameDatabase = null;
            watchListPanel.setStockInfoDatabase(null);
         }

         databaseTask = new DatabaseTask(this, log, readFromDisk);
         databaseTask.execute();
      }

      // We may hold a large database previously. Invoke garbage collector to
      // perform cleanup.
      System.gc();
   }
   
   //==============================================================
   // Aspect of search feature on AjaxAutoCompleteJComboBox.
   //==============================================================
   
   private void initAjaxProvider()
   {
      Country country = this.jStockOptions.getCountry();
      this.watchListPanel.setGreedyEnabled(country);
   }
   
   //==============================================================
   // Method to create a monitor with observer for the market
   // indexes with a scanning speed for updates. Re-aquiring data
   // for MarketJPanel.
   //==============================================================
   
   private void initRealTimeIndexMonitor()
   {
      final RealTimeIndexMonitor oldRealTimeIndexMonitor = realTimeIndexMonitor;
      
      if (oldRealTimeIndexMonitor != null)
      {
         zombiePool.execute(new Runnable()
         {
            @Override
            public void run()
            {
               log.info("initRealTimeIndexMonitor() Prepare to shut down: "
                        + oldRealTimeIndexMonitor + "...");
               oldRealTimeIndexMonitor.clearIndices();
               oldRealTimeIndexMonitor.dettachAll();
               oldRealTimeIndexMonitor.stop();
               log.info("initRealTimeIndexMonitor() Shut down: "
                        + oldRealTimeIndexMonitor + " peacefully.");
            }
         });
      }

      realTimeIndexMonitor = new RealTimeIndexMonitor(
         Constants.REAL_TIME_INDEX_MONITOR_MAX_THREAD,
         Constants.REAL_TIME_INDEX_MONITOR_MAX_STOCK_SIZE_PER_SCAN, jStockOptions.getScanningSpeed());
      
      realTimeIndexMonitor.attach(realTimeIndexMonitorObserver);
      
      for (Index index : org.yccheok.jstock.engine.Utils.getStockIndices(jStockOptions.getCountry()))
         realTimeIndexMonitor.addIndex(index);

      realTimeIndexMonitor.startNewThreadsIfNecessary();
   }
   
   //==============================================================
   // Method to create a monitor with observer for the stock
   // history charting. These history can be called for indexes
   // in the MarketJPanel, and individual stocks in the WatchList
   // and Portfolio Tabs.
   //==============================================================
   
   private void initStockHistoryMonitor()
   {
      final StockHistoryMonitor oldStockHistoryMonitor = stockHistoryMonitor;
      
      if (oldStockHistoryMonitor != null)
      {
         zombiePool.execute(new Runnable()
         {
            @Override
            public void run()
            {
               log.info("Prepare to shut down " + oldStockHistoryMonitor + "...");
               oldStockHistoryMonitor.clearStockCodes();
               oldStockHistoryMonitor.dettachAll();
               oldStockHistoryMonitor.stop();
               log.info("Shut down " + oldStockHistoryMonitor + " peacefully.");
            }
         });
      }

      stockHistoryMonitor = new StockHistoryMonitor(HISTORY_MONITOR_MAX_THREAD);
      
      
      stockHistoryMonitor.attach(this.stockHistoryMonitorObserver);

      final Country country = jStockOptions.getCountry();

      removeOldHistoryData(country);

      StockHistorySerializer stockHistorySerializer = new StockHistorySerializer(
         Utils.getHistoryDirectory());
      stockHistoryMonitor.setStockHistorySerializer(stockHistorySerializer);
      stockHistoryMonitor.setDuration(Duration.getTodayDurationByYears(
         jStockOptions.getHistoryDuration()));
   }
   
   
   

   
   
   
   
   
   

   // Register a hook to save app settings when quit via the app menu.
   // This is in Mac OSX only.
   // http://sourceforge.net/tracker/?func=detail&aid=3490453&group_id=202896&atid=983418
   private void installShutdownHook()
   {
      if (Utils.isMacOSX())
      {
         // Triggered by command + Q
         Runnable runner = new Runnable()
         {
            @Override
            public void run()
            {
               if (isFormWindowClosedCalled)
               {
                  AppLock.unlock();
                  return;
               }

               // 1) Do not call formWindowClosed directly, as accessing UI
               // will cause "hang".
               // 2) Calling system.exit will cause "hang" too.
               JStock.this.save();

               if (JStock.this.needToSaveUserDefinedDatabase)
               {
                  // We are having updated user database in memory.
                  // Save it to disk.
                  JStock.this.saveUserDefinedDatabaseAsCSV(jStockOptions.getCountry(), stockInfoDatabase);
               }

               AppLock.unlock();
            }
         };
         Runtime.getRuntime().addShutdownHook(new Thread(runner, "Window Prefs Hook"));
      }
      else
      {
         Runnable runner = new Runnable()
         {
            @Override
            public void run()
            {
               AppLock.unlock();
            }
         };
         Runtime.getRuntime().addShutdownHook(new Thread(runner, "Window Prefs Hook"));
      }
   }
   
   protected boolean openAsCSVFile(File file)
   {
      final Statements statements = Statements.newInstanceFromCSVFile(file);
      return this.openAsStatements(statements, file);
   }

   public boolean openAsStatements(Statements statements, File file)
   {
      assert (statements != null);

      final GUIBundleWrapper guiBundleWrapper = statements.getGUIBundleWrapper();

      if (statements.getType() == Statement.Type.RealtimeInfo)
      {
         final int size = statements.size();
         for (int i = 0; i < size; i++)
         {
            final org.yccheok.jstock.file.Statement statement = statements.get(i);
            final String codeStr = statement.getValueAsString(
               guiBundleWrapper.getString("MainFrame_Code"));
            final String symbolStr = statement.getValueAsString(
               guiBundleWrapper.getString("MainFrame_Symbol"));
            final Double fallBelowDouble = statement.getValueAsDouble(
               guiBundleWrapper.getString("MainFrame_FallBelow"));
            final Double riseAboveDouble = statement.getValueAsDouble(
               guiBundleWrapper.getString("MainFrame_RiseAbove"));
            if (codeStr.length() > 0 && symbolStr.length() > 0)
            {
               final Stock stock = org.yccheok.jstock.engine.Utils.getEmptyStock(Code.newInstance(codeStr),
                  Symbol.newInstance(symbolStr));
               final StockAlert stockAlert = new StockAlert().setFallBelow(fallBelowDouble).setRiseAbove(
                  riseAboveDouble);
               watchListPanel.addStockToTable(stock, stockAlert);
               realTimeStockMonitor.addStockCode(Code.newInstance(codeStr));
            }
         }
         realTimeStockMonitor.startNewThreadsIfNecessary();
         realTimeStockMonitor.refresh();
      }
      else if (statements.getType() == Statement.Type.PortfolioManagementBuy
               || statements.getType() == Statement.Type.PortfolioManagementSell
               || statements.getType() == Statement.Type.PortfolioManagementDeposit
               || statements.getType() == Statement.Type.PortfolioManagementDividend)
      {
         // Open using other tabs.
         return this.portfolioManagementJPanel.openAsStatements(statements, file);
      }
      else
      {
         return false;
      }
      return true;
   }

   public RealTimeStockMonitor getRealTimeStockMonitor()
   {
      return realTimeStockMonitor;
   }

   private void handleJTabbedPaneStateChanged(JTabbedPane pane)
   {
      if (menuBar == null)
         return;

      // MainFrame
      // Edit Menu (Add Stocks, Clear All Stocks, Refresh Stock Prices)

      if (pane.getSelectedComponent() == watchListPanel)
      {
         menuBar.setEditMenuItems(true, true, true);
         watchListPanel.requestFocusOnJComboBox();
      }

      if (this.isStatusBarBusy == false)
         this.setStatusBar(false, this.getBestStatusBarMessage());
   }

   /**
    * Returns JStock options of this main frame.
    * @return JStock options of this main frame
    */
   public JStockOptions getJStockOptions()
   {
      return this.jStockOptions;
   }

   public UIOptions getUIOptions()
   {
      return this.uiOptions;
   }

   /**
    * Returns the chart dialog options of this main frame.
    * @return the chart dialog options of this main frame
    */
   public ChartJDialogOptions getChartJDialogOptions()
   {
      return this.chartJDialogOptions;
   }

   /**
    * Save the entire application settings.
    */
   public void save()
   {
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
      //this.saveCSVWatchlist();
      this.watchListPanel.saveCSVWatchlist();
      this.portfolioManagementJPanel.savePortfolio();
   }

   // windowClosing
   // Invoked when the user attempts to close the window from the window's
   // system menu.
   //
   // windowClosed
   // Invoked when a window has been closed as the result of calling dispose on
   // the window.
   //
   /*
    * Dangerous! We didn't perform proper clean up, because we do not want to
    * give user perspective that our system is slow. But, is it safe to do so?
    */

   // Remember to revise installShutdownHook
   private void formWindowClosed(java.awt.event.WindowEvent evt)
   {
      isFormWindowClosedCalled = true;

      try
      {
         ExecutorService _stockInfoDatabaseMetaPool = this.stockInfoDatabaseMetaPool;
         this.stockInfoDatabaseMetaPool = null;
         
         if (_stockInfoDatabaseMetaPool != null)
            _stockInfoDatabaseMetaPool.shutdownNow();

         ExecutorService _singleThreadExecutor = this.singleThreadExecutor;
         this.singleThreadExecutor = null;
         
         if (_singleThreadExecutor != null)
            _singleThreadExecutor.shutdownNow();

         // Always be the first statement. As no matter what happen, we must
         // save all the configuration files.
         this.save();

         if (this.needToSaveUserDefinedDatabase)
         {
            // We are having updated user database in memory.
            // Save it to disk.
            this.saveUserDefinedDatabaseAsCSV(jStockOptions.getCountry(), stockInfoDatabase);
         }

         watchListPanel.dettachAllAndStopAutoCompleteJComboBox();

         _stockInfoDatabaseMetaPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

         // We suppose to call shutdownAll to clean up all network resources.
         // However, that will cause Exception in other threads if they are
         // still using httpclient.
         // Exception in thread "Thread-4" java.lang.IllegalStateException:
         // Connection factory has been shutdown.
         //
         // MultiThreadedHttpConnectionManager.shutdownAll();

         log.info("Widnow is closed.");
      }
      catch (Exception exp)
      {
         log.error("Unexpected error while trying to quit application", exp);
      }

      Platform.exit();

      // All the above operations are done within try block, to ensure
      // System.exit(0) will always be called.
      //
      // Final clean up.
      System.exit(0);
   }

   /**
    * Activate specified watchlist.
    * @param watchlist
    *           Watchlist name
    */
   public void selectActiveWatchlist(String watchlist)
   {
      assert (SwingUtilities.isEventDispatchThread());
      // Save current watchlist.
      //JStock.this.saveCSVWatchlist();
      watchListPanel.saveCSVWatchlist();
      // Save current GUI options.
      // Do not call MainFrame.this.saveGUIOptions() (Pay note on the
      // underscore)
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
    * @param portfolio
    *           Portfolio name
    */
   public void selectActivePortfolio(String portfolio)
   {
      assert (SwingUtilities.isEventDispatchThread());
      // Save current portfolio.
      JStock.this.portfolioManagementJPanel.savePortfolio();
      // Save current GUI options.
      JStock.this.portfolioManagementJPanel.saveGUIOptions();
      // And switch to new portfolio.
      JStock.this.getJStockOptions().setPortfolioName(portfolio);
      JStock.this.portfolioManagementJPanel.initPortfolio();
      // I guess user wants to watch the current active portfolio right now.
      // We will help him to turn to the portfolio page.
      JStock.this.jTabbedPane1.setSelectedIndex(portfolioTabIndex);

      JStock.this.portfolioManagementJPanel.updateTitledBorder();

      // No matter how, just stop progress bar, and display best message.
      this.setStatusBar(false, this.getBestStatusBarMessage());
   }

   protected boolean saveAsCSVFile(File file, boolean languageIndependent)
   {
      final TableModel tableModel = watchListPanel.getTable().getModel();
      //CSVWatchlist csvWatchlist = CSVWatchlist.newInstance(tableModel);
      CSVWatchList csvWatchlist = new CSVWatchList(tableModel);
      return WatchListJPanel.saveAsCSVFile(csvWatchlist, file, languageIndependent);
   }

   protected void clearAllStocks()
   {
      if (stockCodeHistoryGUI != null)
         stockCodeHistoryGUI.clear();
      
      if (realTimeStockMonitor != null)
         realTimeStockMonitor.clearStockCodes();
      
      if (stockHistoryMonitor != null)
         stockHistoryMonitor.clearStockCodes();
      
      final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();

      if (java.awt.EventQueue.isDispatchThread())
      {
         tableModel.clearAllStocks();
         watchListPanel.updateDynamicChart(null);
      }
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               tableModel.clearAllStocks();
               watchListPanel.updateDynamicChart(null);
            }
         });
      }

      if (stockCodeHistoryGUI != null && stockCodeHistoryGUI.isEmpty())
      {
         if (this.stockInfoDatabase != null)
               this.setStatusBar(false, this.getBestStatusBarMessage());
      }
   }

   /**
    * Set the exchange rate value on status bar.
    * @param exchangeRate
    *           the exchange rate value. null to reset
    */
   public void setStatusBarExchangeRate(final Double exchangeRate)
   {
      if (SwingUtilities.isEventDispatchThread())
         statusBar.setExchangeRate(exchangeRate);
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               statusBar.setExchangeRate(exchangeRate);
            }
         });
      }
   }

   /**
    * Set the visibility of exchange rate label on status bar.
    * @param visible
    *           true to make the exchange rate label visible. Else false
    */
   public void setStatusBarExchangeRateVisible(final boolean visible)
   {
      if (SwingUtilities.isEventDispatchThread())
         statusBar.setExchangeRateVisible(visible);
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               statusBar.setExchangeRateVisible(visible);
            }
         });
      }
   }

   /**
    * Set the tool tip text of exchange rate label on status bar.
    * @param text
    *           the tool tip text
    */
   public void setStatusBarExchangeRateToolTipText(final String text)
   {
      if (SwingUtilities.isEventDispatchThread())
         statusBar.setExchangeRateToolTipText(text);
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               statusBar.setExchangeRateToolTipText(text);
            }
         });
      }
   }

   /**
    * Update the status bar.
    * @param progressBar
    *           true to make progress bar busy. Else false
    * @param mainMessage
    *           message on the left
    */
   protected void setStatusBar(final boolean progressBar, final String mainMessage)
   {
      if (SwingUtilities.isEventDispatchThread())
      {
         isStatusBarBusy = progressBar;
         statusBar.setProgressBar(progressBar);
         statusBar.setMainMessage(mainMessage);
      }
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               isStatusBarBusy = progressBar;
               statusBar.setProgressBar(progressBar);
               statusBar.setMainMessage(mainMessage);
            }
         });
      }
   }

   public WatchListJPanel getWatchListPanel()
   {
      return this.watchListPanel;
   }
   
   public PortfolioManagementJPanel getPortfolioManagementJPanel()
   {
      return this.portfolioManagementJPanel;
   }

   protected void changeCountry(Country country)
   {
      if (country == null)
         return;

      if (jStockOptions.getCountry() == country)
         return;

      // Not in 1.0.7.9
      //org.yccheok.jstock.engine.Utils.clearGoogleCodeDatabaseCache();
      //org.yccheok.jstock.engine.Utils.clearAllIEXStockInfoDatabaseCaches();

      final Country oldCountry = jStockOptions.getCountry();

      if (needToSaveUserDefinedDatabase)
      {
         // We are having updated user database in memory.
         // Save it to disk.
         this.saveUserDefinedDatabaseAsCSV(oldCountry, stockInfoDatabase);
      }

      /* Save the GUI look. */
      saveGUIOptions();

      /* Need to save chart dialog options? */

      //saveCSVWatchlist();
      watchListPanel.saveCSVWatchlist();
      portfolioManagementJPanel.savePortfolio();

      jStockOptions.setCountry(country);
      jStockOptions.addRecentCountry(country);
      
      marketJPanel.setCountry(jStockOptions.getCountry());
      statusBar.setCountryIcon(country.icon, country.humanString);

      // Here is the dirty trick here. We let our the 'child' panels perform
      // cleanup/ initialization first before initStockCodeAndSymbolDatabase.
      // This is because all child panels and stock symbol database task do
      // interact with status bar. However, We are only most interest in stock
      // symbol
      // database, as it will be the most busy. Hence, we let the stock symbol
      // database to be the last, so that its interaction will overwrite the
      // others.
      this.portfolioManagementJPanel.initPortfolio();

      this.initGoogleCodeDatabaseRunnable();
      this.initDatabase(true);
      this.initAjaxProvider();
      this.initRealTimeIndexMonitor();
      this.initStockHistoryMonitor();
      this.initExchangeRateMonitor();
      // Initialize real time monitor must come before initialize real time
      // stocks. We need to submit real time stocks to real time stock monitor.
      // Hence, after we load real time stocks from file, real time stock
      // monitor
      // must be ready (initialized).
      this.initRealTimeStockMonitor();
      this.watchListPanel.initWatchlist();
      this.initDynamicCharts();
      this.initDynamicChartVisibility();

      menuBar.setSelectedCountryItem(country);
   }

   public StockInfoDatabase getStockInfoDatabase()
   {
      return this.stockInfoDatabase;
   }

   public StockNameDatabase getStockNameDatabase()
   {
      return stockNameDatabase;
   }

   public java.util.List<Stock> getStocks()
   {
      final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();
      return tableModel.getStocks();
   }

   // Only will return true if the selected stock is the one and only one.
   private boolean isStockBeingSelected(final Stock stock)
   {
      int[] rows = watchListPanel.getTable().getSelectedRows();

      if (rows.length == 1)
      {
         final int row = rows[0];
         final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();
         final int modelIndex = watchListPanel.getTable().convertRowIndexToModel(row);
         
         if (stock.code.equals(tableModel.getStock(modelIndex).code))
            return true;
      }

      return false;
   }

   // This is the workaround to overcome Erasure by generics.
   // We are unable to make MainFrame to two observers at the
   // same time.
   private org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result> getRealTimeStockMonitorObserver()
   {
      return new org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result>()
      {
         @Override
         public void update(RealTimeStockMonitor monitor, RealTimeStockMonitor.Result result)
         {
            JStock.this.update(monitor, result);
         }
      };
   }

   private org.yccheok.jstock.engine.Observer<RealTimeIndexMonitor, java.util.List<Market>> getRealTimeIndexMonitorObserver()
   {
      return new org.yccheok.jstock.engine.Observer<RealTimeIndexMonitor, java.util.List<Market>>()
      {
         @Override
         public void update(RealTimeIndexMonitor monitor, java.util.List<Market> markets)
         {
            JStock.this.update(markets);
         }
      };
   }

   private org.yccheok.jstock.engine.Observer<StockHistoryMonitor, StockHistoryMonitor.StockHistoryRunnable> getStockHistoryMonitorObserver()
   {
      return new org.yccheok.jstock.engine.Observer<StockHistoryMonitor, StockHistoryMonitor.StockHistoryRunnable>()
      {
         @Override
         public void update(StockHistoryMonitor monitor, StockHistoryMonitor.StockHistoryRunnable runnable)
         {
            JStock.this.update(monitor, runnable);
         }
      };
   }

   // Asynchronous call. Must be called by event dispatch thread.
   public void displayHistoryChart(StockInfo stockInfo)
   {
      final StockHistoryServer stockHistoryServer = stockHistoryMonitor.getStockHistoryServer(stockInfo.code);
      if (stockHistoryServer == null)
      {
         if (stockCodeHistoryGUI.add(stockInfo.code) && stockHistoryMonitor.addStockCode(stockInfo.code))
         {
            final String template = GUIBundle.getString("MainFrame_LookingForHistory_template");
            final String message = MessageFormat.format(template, stockInfo.symbol,
               stockCodeHistoryGUI.size());
            setStatusBar(true, message);
         }
      }
      else
      {
         ChartJDialog chartJDialog = new ChartJDialog(JStock.this, stockInfo.symbol + " (" + stockInfo.code
                                                                   + ")", false, stockHistoryServer);
         chartJDialog.setVisible(true);
      }
   }

   public void displayStockNews(StockInfo stockInfo)
   {
      assert (SwingUtilities.isEventDispatchThread());
      new StockNewsJFrame(this, stockInfo, stockInfo.symbol + " (" + stockInfo.code + ")");
   }

   protected static boolean saveStockNameDatabaseAsCSV(Country country, StockNameDatabase stockNameDatabase)
   {
      final File stockNameDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                     + country + File.separator + "database" + File.separator
                                                     + "stock-name-database.csv");
      final Statements statements = Statements.newInstanceFromStockNameDatabase(stockNameDatabase);
      boolean result = statements.saveAsCSVFile(stockNameDatabaseCSVFile);
      return result;
   }

   protected static boolean saveStockInfoDatabaseAsCSV(Country country, StockInfoDatabase stockInfoDatabase)
   {
      org.yccheok.jstock.gui.Utils
            .createCompleteDirectoryHierarchyIfDoesNotExist(org.yccheok.jstock.engine.Utils
                  .getStockInfoDatabaseFileDirectory(country));
      final File stockInfoDatabaseCSVFile = org.yccheok.jstock.engine.Utils.getStockInfoDatabaseFile(country);
      final Statements statements = Statements.newInstanceFromStockInfoDatabase(stockInfoDatabase);
      boolean result = statements.saveAsCSVFile(stockInfoDatabaseCSVFile);
      return result;
   }

   protected boolean saveUserDefinedDatabaseAsCSV(Country country, StockInfoDatabase stockInfoDatabase)
   {
      // Previously, we will store the entire stockcodeandsymboldatabase.xml
      // to cloud server if stockcodeandsymboldatabase.xml is containing
      // user defined code. Due to our server is running out of space, we will
      // only store UserDefined pair. user-defined-database.xml will be only
      // used for cloud storage purpose.
      final java.util.List<Pair<Code, Symbol>> pairs = getUserDefinedPair(stockInfoDatabase);
      // pairs can be empty. When it is empty, try to delete the file.
      // If deletion fail, we need to overwrite the file to reflect this.
      final File userDefinedDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                       + country + File.separator + "database"
                                                       + File.separator + "user-defined-database.csv");
      
      if (pairs.isEmpty() && userDefinedDatabaseCSVFile.delete())
         return true;
     
      final Statements statements = Statements.newInstanceFromUserDefinedDatabase(pairs);
      boolean result = statements.saveAsCSVFile(userDefinedDatabaseCSVFile);
      this.needToSaveUserDefinedDatabase = false;
      return result;
   }

   /**
    * Initializes currency exchange monitor.
    */
   public void initExchangeRateMonitor()
   {
      this.portfolioManagementJPanel.initExchangeRateMonitor();
   }
   
   private void initRealTimeStockMonitor()
   {
      final RealTimeStockMonitor oldRealTimeStockMonitor = realTimeStockMonitor;
      
      if (oldRealTimeStockMonitor != null)
      {
         zombiePool.execute(new Runnable()
         {
            @Override
            public void run()
            {
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

      this.portfolioManagementJPanel.initRealTimeStockMonitor();
   }
   
   private void saveUIOptions()
   {
      File file = new File(UserDataDirectory.Config.get() + UserDataFile.UIOptionsJson.get());
      Utils.saveJson(file, this.uiOptions);
   }

   private void saveGUIOptions()
   {
      _saveGUIOptions();
      this.portfolioManagementJPanel.saveGUIOptions();
   }

   private boolean _saveGUIOptions()
   {
      if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
         return false;

      final GUIOptions.JTableOptions jTableOptions = new GUIOptions.JTableOptions();

      final int count = watchListPanel.getTable().getColumnCount();
      
      for (int i = 0; i < count; i++)
      {
         final String name = watchListPanel.getTable().getColumnName(i);
         final TableColumn column = watchListPanel.getTable().getColumnModel().getColumn(i);
         jTableOptions.addColumnOption(GUIOptions.JTableOptions.ColumnOption.newInstance(name,
            column.getWidth()));
      }

      final GUIOptions guiOptions = new GUIOptions();
      guiOptions.addJTableOptions(jTableOptions);

      File f = new File(UserDataDirectory.Config.get() + UserDataFile.MainFrameXml.get());
      return Utils.toXML(guiOptions, f);
   }
   
   public void updatePriceSource(Country country, PriceSource priceSource)
   {
      Factories.INSTANCE.updatePriceSource(country, priceSource);

      rebuildRealTimeStockMonitor();
      rebuildRealTimeIndexMonitor();

      this.portfolioManagementJPanel.rebuildRealTimeStockMonitor();

      this.refreshAllRealTimeStockMonitors();
      this.refreshRealTimeIndexMonitor();
      this.refreshExchangeRateMonitor();
   }

   private void rebuildRealTimeStockMonitor()
   {
      RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
      
      if (_realTimeStockMonitor != null)
         _realTimeStockMonitor.rebuild();
   }

   private void rebuildRealTimeIndexMonitor()
   {
      RealTimeIndexMonitor _realTimeIndexMonitor = this.realTimeIndexMonitor;
      
      if (_realTimeIndexMonitor != null)
         _realTimeIndexMonitor.rebuild();
   }

   private static java.util.List<Pair<Code, Symbol>> getUserDefinedPair(StockInfoDatabase stockInfoDatabase)
   {
      java.util.List<Pair<Code, Symbol>> pairs = new ArrayList<Pair<Code, Symbol>>();
      java.util.List<StockInfo> stockInfos = stockInfoDatabase.getUserDefinedStockInfos();
      
      for (StockInfo stockInfo : stockInfos)
         pairs.add(new Pair<Code, Symbol>(stockInfo.code, stockInfo.symbol));
      
      return pairs;
   }

   /**
    * Save chart dialog options to disc.
    * @return <tt>true</tt> if saving operation is success
    */
   private boolean saveChartJDialogOptions()
   {
      if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
         return false;

      File f = new File(UserDataDirectory.Config.get() + UserDataFile.ChartJDialogOptionsXml.get());
      return org.yccheok.jstock.gui.Utils.toXML(this.chartJDialogOptions, f);
   }

   /**
    * Save JStock options to disc.
    * @return <tt>true</tt> if saving operation is success
    */
   private boolean saveJStockOptions()
   {
      if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
         return false;

      File f = new File(UserDataDirectory.Config.get() + UserDataFile.OptionsXml.get());
      return org.yccheok.jstock.gui.Utils.toXML(this.jStockOptions, f);
   }

   private void removeOldHistoryData(Country country)
   {
      // We do not want "yesterday" history record. We will remove 1 day old
      // files.
      org.yccheok.jstock.gui.Utils.deleteAllOldFiles(new File(Utils.getHistoryDirectory(country)), 1);
   }

   
   
   private void update(RealTimeStockMonitor monitor, final RealTimeStockMonitor.Result result)
   {
      final java.util.List<Stock> stocks = result.stocks;

      // We need to ignore symbol names given by stock server. Replace them
      // with database's.
      final boolean isSymbolImmutable = org.yccheok.jstock.engine.Utils.isSymbolImmutable();
      
      for (int i = 0, size = stocks.size(); i < size; i++)
      {
         final Stock stock = stocks.get(i);
         Stock new_stock = stock;
         // Sometimes server goes crazy by returning empty symbol.
         if (isSymbolImmutable || new_stock.symbol.toString().isEmpty())
         {
            // Use local variable to ensure thread safety.
            final StockInfoDatabase stock_info_database = this.stockInfoDatabase;

            if (stock_info_database != null)
            {
               final Symbol symbol = stock_info_database.codeToSymbol(stock.code);
               
               if (symbol != null)
                  new_stock = new_stock.deriveStock(symbol);
               else
               {
                  // Shouldn't be null. Let's give some warning on this.
                  log.error("Wrong stock code " + stock.code + " given by stock server.");
               }
            }
            else
            {
               // stockCodeAndSymbolDatabase is not ready yet. Use the
               // information
               // from stock table.
               final StockTableModel tableModel = (StockTableModel) watchListPanel.getTable().getModel();
               final int row = tableModel.findRow(stock);
               
               if (row >= 0)
               {
                  final Symbol symbol = tableModel.getStock(row).symbol;
                  new_stock = new_stock.deriveStock(symbol);
               }
            } // if (symbol_database != null)

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

            if (stock != new_stock)
               stocks.set(i, new_stock);
            
         } // if (isSymbolImmutable || new_stock.symbol.toString().isEmpty())
      } // for (int i = 0, size = stocks.size(); i < size; i++)

      if (false == stocks.isEmpty())
      {
         // Update status bar with current time string.
         this.timestamp = System.currentTimeMillis();
         ((StockTableModel) watchListPanel.getTable().getModel()).setTimestamp(this.timestamp);
      }

      updateStatusBarWithLastUpdateDateMessageIfPossible();

      // Do it in GUI event dispatch thread. Otherwise, we may face deadlock.
      // For example, we lock the jTable, and try to remove the stock from the
      // real time monitor. While we wait for the real time monitor to complete,
      // real time monitor will call this function and, be locked at function
      // updateStockToTable.
      javax.swing.SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            for (Stock stock : stocks)
            {
               watchListPanel.updateStockToTable(stock);
               if (isStockBeingSelected(stock))
               {
                  watchListPanel.updateDynamicChart(stock);
               }
            }
         }
      });

      // Dynamic charting. Intraday trader might love this.
      for (Stock stock : stocks)
      {
         final Code code = stock.code;
         DynamicChart dynamicChart = this.dynamicCharts.get(code);
         if (dynamicChart == null)
         {
            // Not found. Try to create a new dynamic chart.
            if (this.dynamicCharts.size() <= JStock.MAX_DYNAMIC_CHART_SIZE)
            {
               dynamicChart = new DynamicChart();
               this.dynamicCharts.put(code, dynamicChart);
            }
            else
            {
               // Full already. Shall we remove?
               if (this.isStockBeingSelected(stock))
               {
                  Set<Code> codes = this.dynamicCharts.keySet();
                  for (Code c : codes)
                  {
                     // Random remove. We do not care who is being removed.
                     this.dynamicCharts.remove(c);
                     if (this.dynamicCharts.size() <= JStock.MAX_DYNAMIC_CHART_SIZE)
                     {
                        // Remove success.
                        break;
                     }
                  }
                  dynamicChart = new DynamicChart();
                  this.dynamicCharts.put(code, dynamicChart);
               }
            }
         } /* if (dynamicChart == null) */

         // Still null?
         if (dynamicChart == null)
         {
            // This usually indicate that dynamic chart list is full, and
            // no one is selecting this particular stock.
            continue;
         }

         if (this.isStockBeingSelected(stock))
         {
            dynamicChart.addPriceObservation(stock.getTimestamp(), stock.getLastPrice());
            final Stock s = stock;
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
               @Override
               public void run()
               {
                  watchListPanel.updateDynamicChart(s);
               }
            });
         }
         else
         {
            // Although no one is watching at us, we still need to perform
            // notification.
            // Weird?
            dynamicChart.addPriceObservation(stock.getTimestamp(), stock.getLastPrice());
         }
      } /* for (Stock stock : stocks) */
   }

   public void updateStatusBarWithLastUpdateDateMessageIfPossible()
   {
      if (this.refreshPriceInProgress)
      {
         // Stop refresh price in progress message.
         this.setStatusBar(false, getBestStatusBarMessage());
         this.refreshPriceInProgress = false;
         return;
      }

      if (this.isStatusBarBusy)
         return;

      Component selected = this.getSelectedComponent();
      
      if (selected != watchListPanel && selected != this.portfolioManagementJPanel)
         return;

      this.setStatusBar(false, getBestStatusBarMessage());
   }

   // Connected
   // [My Watchlist] Last update: Connected
   // [My Watchlist] Last update: 10:40AM
   public String getBestStatusBarMessage()
   {
      final String currentName;
      final long _timestamp;

      Component selected = this.getSelectedComponent();

      // MainFrame
      if (selected == watchListPanel)
      {
         currentName = this.getJStockOptions().getWatchlistName();
         _timestamp = this.timestamp;
      }
      else if (selected == this.portfolioManagementJPanel)
      {
         currentName = this.getJStockOptions().getPortfolioName();
         _timestamp = this.portfolioManagementJPanel.getTimestamp();
      }
      else
         return GUIBundle.getString("MainFrame_Connected");

      if (_timestamp == 0)
         return MessageFormat.format(GUIBundle.getString("MainFrame_Connected_template"), currentName);

      Date date = new Date(_timestamp);
      String time;
      
      if (Utils.isToday(_timestamp))
         time = Utils.getTodayLastUpdateTimeFormat().format(date);
      else
         time = Utils.getOtherDayLastUpdateTimeFormat().format(date);

      return MessageFormat.format(GUIBundle.getString("MainFrame_LastUpdate_template"), currentName, time);
   }

   private void update(final java.util.List<Market> markets)
   {
      assert (markets.isEmpty() == false);

      javax.swing.SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            marketJPanel.update(markets);
         }
      });
   }

   public void update(StockHistoryMonitor monitor, final StockHistoryMonitor.StockHistoryRunnable runnable)
   {
      javax.swing.SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            Code code = runnable.getCode();
            Symbol symbol = null;

            // Use local variable to ensure thread safety.
            final StockInfoDatabase stock_info_database = JStock.this.stockInfoDatabase;
            // Is the database ready?
            if (stock_info_database != null)
            {
               // Possible null if we are trying to get index history.
               symbol = stock_info_database.codeToSymbol(code);
            }
            final boolean shouldShowGUI = JStock.this.stockCodeHistoryGUI.remove(code);

            if (stockCodeHistoryGUI.isEmpty())
            {
               if (runnable.getStockHistoryServer() != null)
               {
                  final String template = GUIBundle.getString("MainFrame_HistorySuccess_template");
                  final String message = MessageFormat.format(template, (symbol != null ? symbol : code));
                  setStatusBar(false, message);
               }
               else
               {
                  final String template = GUIBundle.getString("MainFrame_HistoryFailed_template");
                  final String message = MessageFormat.format(template, (symbol != null ? symbol : code));
                  setStatusBar(false, message);
               }
            }
            else
            {
               if (runnable.getStockHistoryServer() != null)
               {
                  final String template = GUIBundle
                        .getString("MainFrame_HistorySuccessStillWaitingForHistoryTotal_template");
                  final String message = MessageFormat.format(template, (symbol != null ? symbol : code),
                     stockCodeHistoryGUI.size());
                  setStatusBar(true, message);
               }
               else
               {
                  final String template = GUIBundle
                        .getString("MainFrame_HistoryFailedStillWaitingForHistoryTotal_template");
                  final String message = MessageFormat.format(template, (symbol != null ? symbol : code),
                     stockCodeHistoryGUI.size());
                  setStatusBar(true, message);
               }
            }

            if ((runnable.getStockHistoryServer() != null) && shouldShowGUI)
            {
               ChartJDialog chartJDialog = new ChartJDialog(JStock.this, (symbol != null ? symbol : code)
                                                                         + " (" + code + ")", false, runnable
                     .getStockHistoryServer());
               chartJDialog.setVisible(true);
            }
         }
      });
   }

   protected ImageIcon getImageIcon(String imageIcon)
   {
      return new javax.swing.ImageIcon(getClass().getResource(imageIcon));
   }

   public void updateScanningSpeed(int speed)
   {
      this.realTimeStockMonitor.setDelay(speed);
      this.realTimeIndexMonitor.setDelay(speed);
   }

   public void updateHistoryDuration(Duration historyDuration)
   {
      final Duration oldDuration = stockHistoryMonitor.getDuration();

      if (oldDuration.isContains(historyDuration))
      {
         this.stockHistoryMonitor.setDuration(historyDuration);
         return;
      }

      // The history files that we are going to read, their duration are
      // too short compared to historyDuration. The easiest way to overcome
      // this problem is to remove them all.
      log.info("We are going to remove all history files, due to new duration " + historyDuration
               + " is not within old duration " + oldDuration);

      Country[] countries = Country.values();
      
      for (Country country : countries)
         Utils.deleteDir(Utils.getHistoryDirectory(country), false);

      // Avoid from using old history monitor. History monitor contains their
      // own memory data.
      // Since their duration are no longer valid, the memory data are no longer
      // valid too.
      //
      this.initStockHistoryMonitor();

   }

   public void repaintTable()
   {
      Component c = getSelectedComponent();

      if (c instanceof WatchListJPanel)
         watchListPanel.getTable().repaint();
   }

   public Component getSelectedComponent()
   {
      return this.jTabbedPane1.getSelectedComponent();
   }

   public void initDynamicChartVisibility()
   {
      watchListPanel.setDynamicChartVisible();
   }

   private void initDynamicCharts()
   {
      dynamicCharts.clear();
   }

   protected void refreshExchangeRateMonitor()
   {
      this.portfolioManagementJPanel.refreshExchangeRateMonitor();
   }

   public void refreshAllRealTimeStockMonitors()
   {
      RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
      
      if (_realTimeStockMonitor != null)
         _realTimeStockMonitor.refresh();
      
      this.portfolioManagementJPanel.refreshRealTimeStockMonitor();
   }

   public void refreshRealTimeIndexMonitor()
   {
      RealTimeIndexMonitor _realTimeIndexMonitor = this.realTimeIndexMonitor;
      
      if (_realTimeIndexMonitor != null)
         _realTimeIndexMonitor.refresh();
   }
   
   /**
    * @param args the command line arguments
    */
   public static void main(String args[])
   {
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
        * Apply font.
        **********************************************************************/
       
       StringBuilder uiManagerError = new StringBuilder();
       
       try
       {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          Utils.setUIManagerFont(jStockOptions.getFontSize());
       }
       catch (ClassNotFoundException e){uiManagerError.append(e.toString());}
       catch (InstantiationException e){uiManagerError.append(e.toString());}
       catch (IllegalAccessException e){uiManagerError.append("Can't set look and feel.\n" + e.toString());}
       catch (UnsupportedLookAndFeelException e){uiManagerError.append("Can't set look and feel.\n"
                                                                       + e.toString());}
       
       if (!uiManagerError.toString().isEmpty())
          log.error("JStock main(): " + uiManagerError);
       
       /***********************************************************************
        * Start Application.
        **********************************************************************/
       
       java.awt.EventQueue.invokeLater(new Runnable()
       {
           @Override
           public void run()
           {
              // This is just crap.
              final JStock mainFrame = JStock.instance();
               
              // We need to first assign jStockOptions to mainFrame, as during
              // Utils.migrateXMLToCSVPortfolios, we will be accessing mainFrame's
              // jStockOptions. Not quite, if better design would have been
              // implemented.
               
              mainFrame.initJStockOptions(jStockOptions);
               
              mainFrame.init();
              mainFrame.setVisible(true);
              mainFrame.watchListPanel.requestFocusOnJComboBox();
           }
       }); 
   }
}