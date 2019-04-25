/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2016 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.40 04/25/2019
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
//
//         1.0.7.37.34 04/11/2019 Moved initStockHistoryMonitor() to Order as Dictated by init().
//         1.0.7.37.35 04/11/2019 Commented in init() Call to initExchangeRateMonitor(), Also Method
//                                of Same. Performed in Instantiation of PortfolioManageJPanel. Method
//                                changeCountry() Change Call to initExchangeRateMonitor() to portfolio
//                                ManageJPanel.initExchangeRateMonitor(), Also Order. Same Method Order
//                                of Call to portfolioManagementtJPanel.initExchangedRateMonitor().
//         1.0.7.37.36 04/14/2019 Removed Comments From v1.0.7.37.35. Method init() Chose to Provide
//                                All Sequencing for PortfolioManagementJPanel Calls to initPortfolio(),
//                                Was at End of initRealTimeStockMonitor(), initExchangeRateMonitor(),
//                                & initRealTimeStockMonitor() to Last Stages. Same in Method change
//                                Country(). Moved initRealTimeStockMonitor() Method Code to Order
//                                Sequencing Position.
//         1.0.7.37.37 04/18/2019 Method init() Removed Action Set Last Viewed Tab, Moved to initComponents()
//                                After jTabbedPane1 Setup. Also in init() Removed Call to Method
//                                handleJTabbedPaneStateChanged(). That Method Removed, Moved Into
//                                initComponents() jTabbedPane1.addChangeListener. Changed Order in
//                                initComponents() for Selecting Last Viewed Tab. Changed Class Instance
//                                jTabbedPane1 to mainTabsPane.
//         1.0.7.37.38 04/20/2019 Continued Clean Up, Organizing Imports, Comments, Removing this. Method
//                                init() Removed Call to BackwardCompatibile.removeGoogleCodeDatabase().
//                                Continued Organizing, Sequencing, of Methods, formWindowClosed(),
//                                save(), saveJStockOptions(), saveUIOptions(), & saveGUIOptions().
//                                Method initUIOptions() & saveUIOptions Changed to Use Utils.from/toXML()
//                                Rather Than Using Json, to be Consistent With Other Config Files.
//                                Replaced saveUIOptions() With _saveUIOptions() & Added Argument
//                                boolean to Handle portfolioManagementJPanel Options Saving. Removed
//                                rebuildRealTimeStock/IndexMonitor() Methods. A Whole Method(s) for
//                                One Conditional Test & Called Only by updatePriceSource(), Just
//                                Put Where Belongs in that Method.
//         1.0.7.37.39 04/22/2019 Method init() Removed Call to initGUIOptions(), Removed That Method.
//                                Removed All Calls to getJStockOptions() in this Class, Called Instance
//                                Directly. Moved Code Methods selectActiveWatchlist/Portfolio() to Initial
//                                Call After tabListsNavigation(). Removed Methods removeOldHistoryData(),
//                                openAsCSVFile() & saveGUIOptions(). The Latter Replaced With Direct
//                                Call to Tab Panels Method of Same. Methods getUserDefinedPair() &
//                                update(List<Markets>) Moved Code Into Only Calling Internal Methods
//                                getRealTimeIndexMonitor() & saveUserDefinedDatabaseAsCSV().
//         1.0.7.37.40 04/25/2019 Removed Class Instances dynamicCharts & MAX_DYNAMIC_CHART_SIZE.
//                                Method init() Removed Call to initDynamicCharts() & initDynamicChart
//                                Visibility(), Also Same for changeCountry(). Changed Call to watch
//                                List.initWatchList() to Use New Required boolean Argument. Removed
//                                Methods isStockBeingSelected() & initDynamicCharts(). Method update()
//                                Replaced Dynamic Chart Setup to Call to watchListPanel.addDynamicCharts().
//                                
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import org.yccheok.jstock.gui.news.StockNewsJFrame;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.network.ProxyDetector;
import org.yccheok.jstock.watchlist.CSVWatchList;

import com.google.api.client.auth.oauth2.Credential;

/**
 * @author doraemon
 * @author Dana M. Proctor
 * @version 1.0.7.37.40 04/25/2019
 */

public class JStock extends javax.swing.JFrame
{
   // Class Instances
   private static final long serialVersionUID = 3554990056522905135L;
   
   public static final String VERSION = "1.0.7.37.40";
   
   private Main_JMenuBar menuBar;
   private JTabbedPane mainTabsPane;

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
      
      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      
      addWindowListener(new java.awt.event.WindowAdapter()
      {
         // Invoked when a window has been closed as the result
         // of calling dispose on the window.
         public void windowClosed(WindowEvent evt)
         {
            formWindowClosed(evt);
         }

         // Invoked when the user attempts to close the window
         // from the window's system menu.
         public void windowClosing(WindowEvent evt)
         {
            dispose();
         }

         public void windowDeiconified(WindowEvent evt)
         {
            // Nothing to Do.
         }

         public void windowIconified(WindowEvent evt)
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
      
      addMouseListener(new MouseAdapter()
      {
         public void mouseClicked(MouseEvent evt)
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
      // Options for the UI.
      
      initUIOptions();
      initChartJDialogOptions();
      
      // WatchListJPanel stock selector search
      // feature via AjaxAutoCompleteJComboBox.
      
      initStockInfoDatabaseMeta();
      initGoogleCodeDatabaseRunnable();
      initIEXStockInfoDatabaseRunnable();
      initDatabase(true);
      initAjaxProvider();
      
      // These initialize threads to monitor,
      // update, stock exchange, history, and
      // watchlist data.
      
      initRealTimeIndexMonitor();
      initStockHistoryMonitor();
      initRealTimeStockMonitor();
      
      // Finalize setup of WatchListJPanel
      // and PortfolioManagementJPanel
      // components.
      
      watchListPanel.initWatchlist(true);
      watchListPanel.addAutoCompleteComboBox();
      
      portfolioManagementJPanel.initPortfolio();
      portfolioManagementJPanel.initExchangeRateMonitor();
      portfolioManagementJPanel.initRealTimeStockMonitor();

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
   }
   
   //==============================================================
   // Dangerous! We didn't perform proper clean up, because we do
   // not want to give user perspective that our system is slow.
   // But, is it safe to do so?
   //
   // Remember to revise installShutdownHook?
   //==============================================================
   
   private void formWindowClosed(WindowEvent evt)
   {
      isFormWindowClosedCalled = true;

      try
      {
         ExecutorService _stockInfoDatabaseMetaPool = stockInfoDatabaseMetaPool;
         stockInfoDatabaseMetaPool = null;
         
         if (_stockInfoDatabaseMetaPool != null)
            _stockInfoDatabaseMetaPool.shutdownNow();

         ExecutorService _singleThreadExecutor = singleThreadExecutor;
         singleThreadExecutor = null;
         
         if (_singleThreadExecutor != null)
            _singleThreadExecutor.shutdownNow();

         // Always be the first statement. As no matter what happen, we must
         // save all the configuration files.
         save();

         if (needToSaveUserDefinedDatabase)
         {
            // We are having updated user database in memory.
            // Save it to disk.
            saveUserDefinedDatabaseAsCSV(jStockOptions.getCountry(), stockInfoDatabase);
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

      // Yea this is FX thing.
      Platform.exit();

      // All the above operations are done within try block, to ensure
      // System.exit(0) will always be called.
      //
      // Final clean up.
      System.exit(0);
   }
   
   //==============================================================
   // Method to allow selecting WatchList & Portfolio Tabs via the
   // keyboard. Also will toggle between lists for each tab.
   //==============================================================
   
   private void initKeyBindings()
   {
      KeyStroke watchlistNavigationKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK);
      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(watchlistNavigationKeyStroke,
            "watchlistNavigation");
      
      KeyStroke portfolioNavigationKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK);
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
         mainTabsPane.setSelectedIndex(portfolioTabIndex);
         return;
      }
      
      if (sourceKey.equals("watchlist") && getSelectedComponent() != watchListPanel)
      {
         mainTabsPane.setSelectedIndex(watchListTabIndex);
         return;
      }
      
      if (tabListNames.size() <= 1)
         return;
      
      if (getSelectedComponent() == watchListPanel)
         listName = jStockOptions.getWatchlistName();
      else if (getSelectedComponent() == portfolioManagementJPanel)
         listName = jStockOptions.getPortfolioName();
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
   
   /**
    * Activate specified watchlist.
    * @param watchlist
    *           Watchlist name
    */
   
   public void selectActiveWatchlist(String watchlist)
   {
      assert (SwingUtilities.isEventDispatchThread());
      
      watchListPanel.saveCSVWatchlist();
      watchListPanel.saveGUIOptions();
      
      jStockOptions.setWatchlistName(watchlist);
      watchListPanel.initWatchlist(false);
      
      mainTabsPane.setSelectedIndex(watchListTabIndex);

      // No matter how, just stop progress bar, and display best message.
      setStatusBar(false, getBestStatusBarMessage());
   }
   
   /**
    * Activate specified portfolio.
    * @param portfolio
    *           Portfolio name
    */
   
   public void selectActivePortfolio(String portfolio)
   {
      assert (SwingUtilities.isEventDispatchThread());
      
      portfolioManagementJPanel.saveCSVPortfolio();
      portfolioManagementJPanel.saveGUIOptions();
      
      jStockOptions.setPortfolioName(portfolio);
      portfolioManagementJPanel.initPortfolio();
      
      mainTabsPane.setSelectedIndex(portfolioTabIndex);

      portfolioManagementJPanel.updateTitledBorder();

      // No matter how, just stop progress bar, and display best message.
      setStatusBar(false, getBestStatusBarMessage());
   }
   
   //==============================================================
   // Main method to setup the major graphical components of the
   // application, frame.
   //==============================================================
   
   private void initComponents(ResourceBundle bundle)
   {
      // Start Adding Main Components
      getContentPane().setLayout(new BorderLayout());

      // North Market Panel
      marketJPanel = new MarketJPanel(jStockOptions.getCountry());
      getContentPane().add(marketJPanel, BorderLayout.NORTH);
      
      // South Status Bar
      javax.swing.JPanel statusBarPanel = new javax.swing.JPanel();
      
      statusBar = new MyJXStatusBar();
      statusBarPanel.setBorder(BorderFactory.createLoweredBevelBorder());
      statusBarPanel.setLayout(new GridLayout(1, 1));
      statusBarPanel.add(statusBar);
      
      getContentPane().add(statusBarPanel, BorderLayout.SOUTH);

      // Center Tabbed Pane
      mainTabsPane = new JTabbedPane();
      mainTabsPane.setFont(mainTabsPane.getFont().deriveFont(
         mainTabsPane.getFont().getStyle() | Font.BOLD, mainTabsPane.getFont().getSize() + 2));
      mainTabsPane.setBorder(BorderFactory.createEtchedBorder());
      
      watchListPanel = new WatchListJPanel();
      watchListTabIndex = mainTabsPane.getTabCount();
      mainTabsPane.addTab(bundle.getString("MainFrame_Title"), watchListPanel);
      mainTabsPane.setIconAt(watchListTabIndex, getImageIcon("/images/16x16/stock_timezone.png"));
      mainTabsPane.setToolTipTextAt(watchListTabIndex, ResourceBundle.getBundle(
         "org/yccheok/jstock/data/gui").getString("MainFrame_WatchYourFavoriteStockMovement"));
      
      portfolioManagementJPanel = new PortfolioManagementJPanel();
      portfolioTabIndex = mainTabsPane.getTabCount();
      mainTabsPane.addTab(GUIBundle.getString("PortfolioManagementJPanel_Title"), portfolioManagementJPanel);
      mainTabsPane.setIconAt(portfolioTabIndex, getImageIcon("/images/16x16/calc.png"));
      mainTabsPane.setToolTipTextAt(portfolioTabIndex, ResourceBundle.getBundle(
         "org/yccheok/jstock/data/gui").getString(
            "MainFrame_ManageYourRealTimePortfolioWhichEnableYouToTrackBuyAndSellRecords"));
      
      // Set last used tab & add ChangeListener.
      if (mainTabsPane.getTabCount() > jStockOptions.getLastSelectedPageIndex())
         mainTabsPane.setSelectedIndex(jStockOptions.getLastSelectedPageIndex());
      
      mainTabsPane.addChangeListener(new ChangeListener()
      {
         public void stateChanged(ChangeEvent evt)
         {
            JTabbedPane pane = (JTabbedPane) evt.getSource();
            
            // MainFrame
            // Edit Menu (Add Stocks, Clear All Stocks, Refresh Stock Prices)

            if (pane.getSelectedComponent() == watchListPanel)
            {
               menuBar.setEditMenuItems(true, true, true);
               watchListPanel.requestFocusOnJComboBox();
            }
            
            if (pane.getSelectedComponent() == portfolioManagementJPanel)
               menuBar.setEditMenuItems(false, false, true);
            
            if (isStatusBarBusy == false)
               setStatusBar(false, getBestStatusBarMessage());
         }
      });
      
      getContentPane().add(mainTabsPane, BorderLayout.CENTER);
      
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
      final File f = new File(UserDataDirectory.Config.get() + UserDataFile.UIOptionsXml.get());
      uiOptions = Utils.fromXML(UIOptions.class, f);
      
      if (uiOptions == null)
         uiOptions = new UIOptions();
      // else
      //    log.info("iuOptions loaded from " + f.toString() + " successfully.");   
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
   // Stopped following below by commenting needToInitDatabase,
   // see comment below in code.
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
         getImageIcon("/images/16x16/network-connecting.png"), ResourceBundle.getBundle(
            "org/yccheok/jstock/data/gui").getString("MainFrame_Connecting..."));

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
      Country country = jStockOptions.getCountry();
      watchListPanel.setGreedyEnabled(country);
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
      
      stockHistoryMonitor.attach(stockHistoryMonitorObserver);

      Utils.deleteAllOldFiles(new File(Utils.getHistoryDirectory(jStockOptions.getCountry())), 1);

      StockHistorySerializer stockHistorySerializer = new StockHistorySerializer(
         Utils.getHistoryDirectory());
      stockHistoryMonitor.setStockHistorySerializer(stockHistorySerializer);
      stockHistoryMonitor.setDuration(Duration.getTodayDurationByYears(
         jStockOptions.getHistoryDuration()));
   }
   
   //==============================================================
   // Method to create a monitor with observer for the stocks in
   // the WatchListJPanel table.
   //==============================================================
   
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

      realTimeStockMonitor = new RealTimeStockMonitor(Constants.REAL_TIME_STOCK_MONITOR_MAX_THREAD,
         Constants.REAL_TIME_STOCK_MONITOR_MAX_STOCK_SIZE_PER_SCAN, jStockOptions.getScanningSpeed());
      
      realTimeStockMonitor.attach(realTimeStockMonitorObserver);
   }
   
   //==============================================================
   // Register a hook to save app settings when quit via the app
   // menu.
   // This is in Mac OSX only.
   // http://sourceforge.net/tracker/?func=detail&aid=3490453&group_id=202896&atid=983418
   //==============================================================
   
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
   
   //==============================================================
   // Save the entire application settings.
   //==============================================================
   
   public void save()
   {
      // Save the last viewed page.
      jStockOptions.setLastSelectedPageIndex(mainTabsPane.getSelectedIndex());

      // Save current window size and position.
      JStockOptions.BoundsEx boundsEx = new JStockOptions.BoundsEx(getBounds(), getExtendedState());
      jStockOptions.setBoundsEx(boundsEx);

      // Not used.
      // jStockOptions.setApplicationVersionID(Utils.getApplicationVersionID());

      saveJStockOptions();
      saveUIOptions();
      saveChartJDialogOptions();
      
      watchListPanel.saveGUIOptions();
      watchListPanel.saveCSVWatchlist();
      
      portfolioManagementJPanel.saveGUIOptions();
      portfolioManagementJPanel.saveCSVPortfolio();
   }
   
   //==============================================================
   // Save JStock options to disc.
   //==============================================================
   
   private boolean saveJStockOptions()
   {
      if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
         return false;

      File f = new File(UserDataDirectory.Config.get() + UserDataFile.OptionsXml.get());
      return Utils.toXML(jStockOptions, f);
   }
   
   //==============================================================
   // Save various dialogs window dimensions.
   //==============================================================
   
   private boolean saveUIOptions()
   {
      File file = new File(UserDataDirectory.Config.get() + UserDataFile.UIOptionsXml.get());
      return Utils.toXML(uiOptions, file);
   }
   
   //==============================================================
   // Save index, history, JFreeChart dialog options to disc.
   //==============================================================
   
   private boolean saveChartJDialogOptions()
   {
      if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
         return false;

      File f = new File(UserDataDirectory.Config.get() + UserDataFile.ChartJDialogOptionsXml.get());
      return org.yccheok.jstock.gui.Utils.toXML(chartJDialogOptions, f);
   }
   
   
   
   
   
   
   
   
   
   
   
   
   //==============================================================
   // Used in:
   // Main_JMenuBar.fileOpenActionPerformed()
   // PortfolioManagementJPanel.openAsStatements()
   // WatchListJPanel.initWatchList()
   //==============================================================
   
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

      /* Save and set country. */
      
      watchListPanel.saveGUIOptions();
      watchListPanel.saveCSVWatchlist();
      
      portfolioManagementJPanel.saveGUIOptions();
      portfolioManagementJPanel.saveCSVPortfolio();

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
      //this.portfolioManagementJPanel.initPortfolio();

      this.initGoogleCodeDatabaseRunnable();
      this.initDatabase(true);
      this.initAjaxProvider();
      
      this.initRealTimeIndexMonitor();
      this.initStockHistoryMonitor();
      // Initialize real time monitor must come before initialize real time
      // stocks. We need to submit real time stocks to real time stock monitor.
      // Hence, after we load real time stocks from file, real time stock
      // monitor
      // must be ready (initialized).
      this.initRealTimeStockMonitor();
      
      this.watchListPanel.initWatchlist(true);
      
      this.portfolioManagementJPanel.initPortfolio();
      this.portfolioManagementJPanel.initExchangeRateMonitor();
      this.portfolioManagementJPanel.initRealTimeStockMonitor();

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
            if (!markets.isEmpty())
            {
               javax.swing.SwingUtilities.invokeLater(new Runnable()
               {
                  @Override
                  public void run()
                  {
                     marketJPanel.update(markets);
                  }
               });
            }   
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
      //final java.util.List<Pair<Code, Symbol>> pairs = getUserDefinedPair(stockInfoDatabase);
      
      final java.util.List<Pair<Code, Symbol>> userStockList;
      userStockList = new ArrayList<Pair<Code, Symbol>>();
      java.util.List<StockInfo> stockInfos = stockInfoDatabase.getUserDefinedStockInfos();
      
      for (StockInfo stockInfo : stockInfos)
         userStockList.add(new Pair<Code, Symbol>(stockInfo.code, stockInfo.symbol));
      
      
      // pairs can be empty. When it is empty, try to delete the file.
      // If deletion fail, we need to overwrite the file to reflect this.
      final File userDefinedDatabaseCSVFile = new File(org.yccheok.jstock.gui.Utils.getUserDataDirectory()
                                                       + country + File.separator + "database"
                                                       + File.separator + "user-defined-database.csv");
      
      if (userStockList.isEmpty() && userDefinedDatabaseCSVFile.delete())
         return true;
     
      final Statements statements = Statements.newInstanceFromUserDefinedDatabase(userStockList);
      boolean result = statements.saveAsCSVFile(userDefinedDatabaseCSVFile);
      this.needToSaveUserDefinedDatabase = false;
      return result;
   }
   
   public void updatePriceSource(Country country, PriceSource priceSource)
   {
      Factories.INSTANCE.updatePriceSource(country, priceSource);

      if (realTimeStockMonitor != null)
         realTimeStockMonitor.rebuild();
     
      if (realTimeIndexMonitor != null)
         realTimeIndexMonitor.rebuild();

      this.portfolioManagementJPanel.rebuildRealTimeStockMonitor();

      this.refreshAllRealTimeStockMonitors();
      this.refreshRealTimeIndexMonitor();
      this.refreshExchangeRateMonitor();
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
               
               if (watchListPanel.isStockBeingSelected(stock))
                  watchListPanel.updateDynamicChart(stock);
            }
         }
      });

      watchListPanel.addDynamicCharts(stocks);
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
         currentName = jStockOptions.getWatchlistName();
         _timestamp = this.timestamp;
      }
      else if (selected == this.portfolioManagementJPanel)
      {
         currentName = jStockOptions.getPortfolioName();
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
      return this.mainTabsPane.getSelectedComponent();
   }

   public void initDynamicChartVisibility()
   {
      watchListPanel.setDynamicChartVisible();
   }

   /*
   private void initDynamicCharts()
   {
      dynamicCharts.clear();
   }
   */

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