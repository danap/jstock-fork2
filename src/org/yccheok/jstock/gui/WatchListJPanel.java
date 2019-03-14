/*
 * JStock-Fork
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.04 03/13/2019
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
// Version 1.0.7.37.01 02/28/2019 Original WatchListJPanel.
//         1.0.7.37.02 03/08/2019 Added Class Methods getStockInfoObserver(), getDisplayObserver(),
//                                addStockInfoFromAutoCompleteJComboBox(), addStockToTable(),
//                                dettachAllAndSTopAutoCompleteJComboBox(), setSTockInfoDatabase(),
//                                setGreedyEnabled(), & requestFocusOnJComboBox(). Added Class
//                                Instance EMPTY_DYNAMIC_CHART. Formatted. Added High Light Row
//                                to addStockInfoFromAutoCompleteJComboBox() Rather Than a Separate
//                                Method. Organized Code Methods.
//         1.0.7.37.03 03/09/2019 Minor Formatting Changes. Returned Control of setDynamicChartVisible()
//                                to jStockOptions, GUI, Show Intraday Chart.
//         1.0.7.37.04 03/13/2019 Added Method initWatchList().
//
//-----------------------------------------------------------------
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.yccheok.jstock.engine.Code;
import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.DispType;
import org.yccheok.jstock.engine.Stock;
import org.yccheok.jstock.engine.StockInfo;
import org.yccheok.jstock.engine.StockInfoDatabase;
import org.yccheok.jstock.engine.Symbol;
import org.yccheok.jstock.gui.charting.DynamicChart;
import org.yccheok.jstock.gui.table.NonNegativeDoubleEditor;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.watchlist.Utils;

/**
 * @author Dana M. Proctor
 * @version 1.0.7.37.04 03/13/2019
 */

public class WatchListJPanel extends JPanel
{
   // Class Instances
   private static final long serialVersionUID = 55607059084904271L;

   private JPanel stockSelectorPanel;
   private AutoCompleteJComboBox stockSelectorComboBox;
   private JTable watchListTable;
   private JPanel chartPanel;
   private JPanel dynamicChartsPanel;

   private static final DynamicChart EMPTY_DYNAMIC_CHART = new DynamicChart();
   private final MouseAdapter dynamicChartMouseAdapter = createDynamicChartMouseAdapter();

   //==============================================================
   // WatchListJPanel Constructor
   //==============================================================

   public WatchListJPanel()
   {
      // Constructor Instances
      ResourceBundle bundle;
      JScrollPane jScrollPane1;
      JLabel stockLabel;

      // Setup
      bundle = ResourceBundle.getBundle("org/yccheok/jstock/data/gui");
      setLayout(new java.awt.BorderLayout(5, 5));

      // Stock selector panel & components.
      stockSelectorPanel = new JPanel();
      stockSelectorPanel.setBorder(BorderFactory.createRaisedBevelBorder());

      stockLabel = new JLabel();
      stockLabel.setText(bundle.getString("MainFrame_Stock"));
      stockSelectorPanel.add(stockLabel);

      stockSelectorComboBox = new AutoCompleteJComboBox();
      stockSelectorComboBox.setBorder(BorderFactory.createLoweredBevelBorder());
      stockSelectorComboBox.setEditable(true);
      stockSelectorComboBox.setPreferredSize(new java.awt.Dimension(150, 24));
      stockSelectorComboBox.attachStockInfoObserver(getStockInfoObserver());
      stockSelectorComboBox.attachDispObserver(getDispObserver());

      add(stockSelectorPanel, BorderLayout.NORTH);

      // Watch list table.
      watchListTable = new JTable();
      watchListTable.setAutoCreateRowSorter(true);
      watchListTable.setFont(watchListTable.getFont().deriveFont(
         watchListTable.getFont().getStyle() | java.awt.Font.BOLD, watchListTable.getFont().getSize() + 1));
      watchListTable.setModel(new StockTableModel());
      watchListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      watchListTable.setDefaultRenderer(Number.class, new StockTableCellRenderer(SwingConstants.RIGHT));
      watchListTable.setDefaultRenderer(Double.class, new StockTableCellRenderer(SwingConstants.RIGHT));
      watchListTable.setDefaultRenderer(Object.class, new StockTableCellRenderer(SwingConstants.LEFT));

      watchListTable.setDefaultEditor(Double.class, new NonNegativeDoubleEditor());

      watchListTable.getModel().addTableModelListener(createTableModelListener());

      watchListTable.getTableHeader().addMouseListener(new TableColumnSelectionPopupListener(1));
      watchListTable.addMouseListener(new TableMouseAdapter());
      watchListTable.addKeyListener(new TableKeyEventListener());

      if (JStock.instance().getJStockOptions().useLargeFont())
         watchListTable.setRowHeight((int) (watchListTable.getRowHeight() * Constants.FONT_ENLARGE_FACTOR));

      watchListTable.addKeyListener(new KeyAdapter()
      {
         public void keyPressed(KeyEvent evt)
         {
            watchListTableKeyPressed(evt);
         }
      });

      jScrollPane1 = new JScrollPane();
      jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
      jScrollPane1.setViewportView(watchListTable);

      add(jScrollPane1, java.awt.BorderLayout.CENTER);

      // Dynamic chart.
      chartPanel = new JPanel();
      chartPanel.setPreferredSize(new Dimension(328, 170));
      chartPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

      dynamicChartsPanel = new JPanel();
      dynamicChartsPanel.setBackground(new Color(255, 255, 255));
      dynamicChartsPanel.setPreferredSize(new Dimension(170, 160));
      dynamicChartsPanel.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createEtchedBorder(), BorderFactory.createLoweredBevelBorder()));
      dynamicChartsPanel.setLayout(new BorderLayout());

      EMPTY_DYNAMIC_CHART.getChartPanel().addMouseListener(dynamicChartMouseAdapter);
      dynamicChartsPanel.add(EMPTY_DYNAMIC_CHART.getChartPanel(), BorderLayout.CENTER);
      chartPanel.add(dynamicChartsPanel);

      add(chartPanel, BorderLayout.SOUTH);
   }

   //==============================================================
   // Class methods to assign observers to the stockSelectorCombo
   // Box.
   //==============================================================

   private org.yccheok.jstock.engine.Observer<AutoCompleteJComboBox, StockInfo> getStockInfoObserver()
   {
      return new org.yccheok.jstock.engine.Observer<AutoCompleteJComboBox, StockInfo>()
      {
         @Override
         public void update(AutoCompleteJComboBox subject, StockInfo stockInfo)
         {
            assert (stockInfo != null);
            addStockInfoFromAutoCompleteJComboBox(stockInfo);
         }
      };
   }

   private org.yccheok.jstock.engine.Observer<AutoCompleteJComboBox, DispType> getDispObserver()
   {
      return new org.yccheok.jstock.engine.Observer<AutoCompleteJComboBox, DispType>()
      {
         @Override
         public void update(AutoCompleteJComboBox subject, DispType dispType)
         {
            assert (dispType != null);
            Code code = Code.newInstance(dispType.getDispCode());
            final Symbol symbol = Symbol.newInstance(dispType.getDispName());
            final StockInfo stockInfo = StockInfo.newInstance(code, symbol);

            addStockInfoFromAutoCompleteJComboBox(stockInfo);
         }
      };
   }
   
   //==============================================================
   // Method for common code for getStockInfoObserver and
   // getResultObserver.
   //==============================================================
   
   private void addStockInfoFromAutoCompleteJComboBox(StockInfo stockInfo)
   {
      // When user try to enter a stock, and the stock is already in
      // the table, the stock shall be highlighted. Stock will be
      // selected and the table shall be scrolled to be visible.
      final StockTableModel tableModel = (StockTableModel) watchListTable.getModel();

      final Stock emptyStock = org.yccheok.jstock.engine.Utils.getEmptyStock(stockInfo);

      // First add the empty stock, so that the user will not have wrong
      // perspective that
      // our system is slow.
      addStockToTable(emptyStock);
      int row = tableModel.findRow(emptyStock);
      JStock.instance().realTimeStockMonitor.addStockCode(stockInfo.code);
      JStock.instance().realTimeStockMonitor.startNewThreadsIfNecessary();
      JStock.instance().realTimeStockMonitor.refresh();

      // Hightlight Row as Needed.
      if (row != -1)
      {
         int highLightRow = watchListTable.convertRowIndexToView(row);
         // Make it selected.
         watchListTable.getSelectionModel().setSelectionInterval(highLightRow, highLightRow);
         // and visible.
         JTableUtilities.scrollToVisible(watchListTable, highLightRow, 0);
      }
   }
   
   //==============================================================
   // Class method to be called to intialize, set, the watch list
   // table tooltips.
   //==============================================================

   protected void initTableHeaderToolTips()
   {
      JTableHeader header = watchListTable.getTableHeader();

      ColumnHeaderToolTips tips = new ColumnHeaderToolTips();

      tips.setToolTip(
         watchListTable.getColumn(GUIBundle.getString("MainFrame_FallBelow")),
         ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
            "MainFrame_AlertUserWhenLastPriceFallBelowOrEqualToSpecifiedValue"));
      tips.setToolTip(
         watchListTable.getColumn(GUIBundle.getString("MainFrame_RiseAbove")),
         ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
            "MainFrame_AlertUserWhenLastPriceFallAboveOrEqualToSpecifiedValue"));

      header.addMouseMotionListener(tips);
   }
   
   //==============================================================
   // Class method to be called to intialize, to a saved watchlist.
   //==============================================================
   
   protected boolean initWatchlist()
   {  
      JStock.instance().timestamp = 0;
      
      List<String> availableWatchlistNames = Utils.getWatchlistNames();
      
      // Do we have any watchlist for this country?
      if (availableWatchlistNames.size() <= 0)
      {
          // If not, create an empty watchlist.
          Utils.createEmptyWatchlist(Utils.getDefaultWatchlistName());
          availableWatchlistNames = Utils.getWatchlistNames();
      }
      assert(availableWatchlistNames.isEmpty() == false);

      // Is user selected watchlist name within current available watchlist names?
      if (false == availableWatchlistNames.contains(JStock.instance().getJStockOptions().getWatchlistName()))
      {
          // Nope. Reset user selected watchlist name to the first available name.
          JStock.instance().getJStockOptions().setWatchlistName(availableWatchlistNames.get(0));
      }
      
      // openAsCSVFile will "append" stocks. We need to clear previous stocks
      // explicitly.
      
      // Clear the previous data structures.
      JStock.instance().clearAllStocks();
      
      File realTimeStockFile = Utils.getWatchlistFile(Utils.getWatchlistDirectory());
      return JStock.instance().openAsCSVFile(realTimeStockFile);
  }
   
   //==============================================================
   // Class method to create the mouse adapter for the dynamic
   // chart, may not be visible depending on options.
   //==============================================================

   private MouseAdapter createDynamicChartMouseAdapter()
   {
      return new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent e)
         {
            if (e.getClickCount() >= 2)
            {
               final Stock stock = getSelectedStock();
               
               if (stock == null)
                  return;

               final DynamicChart dynamicChart = JStock.instance().dynamicCharts.get(stock.code);
               if (dynamicChart == null)
               {
                  return;
               }
               Symbol symbol = null;
               // Use local variable to ensure thread safety.
               final StockInfoDatabase stock_info_database = JStock.instance().stockInfoDatabase;
               // Is the database ready?
               if (stock_info_database != null)
               {
                  // Possible null if we are trying to get index history.
                  symbol = stock_info_database.codeToSymbol(stock.code);
               }
               final String template = GUIBundle.getString("MainFrame_IntradayMovementTemplate");
               final String message = MessageFormat.format(template, symbol == null ? stock.symbol : symbol);
               dynamicChart.showNewJDialog(JStock.instance(), message);
            }
         }
         // Shall we provide visualize mouse move over effect, so that user
         // knows this is a clickable component?
         /*
          * private final LineBorder lineBorder = new LineBorder(Color.WHITE);
          * private Border oldBorder = null;
          * @Override public void mouseEntered(MouseEvent e) { JPanel jPanel =
          * (JPanel)e.getComponent(); Border old = jPanel.getBorder(); if (old
          * != lineBorder) { oldBorder = old; } jPanel.setBorder(lineBorder); }
          * @Override public void mouseExited(MouseEvent e) { JPanel jPanel =
          * (JPanel)e.getComponent(); jPanel.setBorder(oldBorder); }
          */
      };
   }

   //==============================================================
   // Class Method to add a model listener for the watch list table.
   //==============================================================

   private TableModelListener createTableModelListener()
   {
      return new TableModelListener()
      {
         @Override
         public void tableChanged(TableModelEvent e)
         {
            switch (e.getType())
            {
               case TableModelEvent.INSERT:
                  break;
               case TableModelEvent.UPDATE:
                  break;
               case TableModelEvent.DELETE:
                  break;
            }
         }
      };
   }

   //==============================================================
   // Class method to process the key press events on the stock
   // watch list table.
   //==============================================================

   private void watchListTableKeyPressed(KeyEvent evt)
   {
      if (KeyEvent.VK_DELETE == evt.getKeyCode())
      {
         this.deteleSelectedTableRow();
         return;
      }
      else if (KeyEvent.VK_ENTER == evt.getKeyCode())
      {
         JStock.instance().displayHistoryCharts();
         return;
      }
      else if (evt.isActionKey())
      {
         int[] rows = watchListTable.getSelectedRows();

         if (rows.length == 1)
         {
            int row = rows[0];

            final StockTableModel tableModel = (StockTableModel) watchListTable.getModel();
            final int modelIndex = watchListTable.convertRowIndexToModel(row);
            final Stock stock = tableModel.getStock(modelIndex);
            updateDynamicChart(stock);
         }
         else
            updateDynamicChart(null);
      }
   }

   //==============================================================
   // Class method to process the deletion of stock entries in the
   // stock watch list table.
   //==============================================================

   private void deteleSelectedTableRow()
   {
      assert (EventQueue.isDispatchThread());

      StockTableModel tableModel = (StockTableModel) watchListTable.getModel();
      int rows[] = watchListTable.getSelectedRows();

      Arrays.sort(rows);

      for (int i = rows.length - 1; i >= 0; i--)
      {
         int row = rows[i];

         if (row < 0)
            continue;

         final int modelIndex = watchListTable.getRowSorter().convertRowIndexToModel(row);
         Stock stock = tableModel.getStock(modelIndex);
         JStock.instance().stockCodeHistoryGUI.remove(stock.code);
         JStock.instance().realTimeStockMonitor.removeStockCode(stock.code);
         JStock.instance().stockHistoryMonitor.removeStockCode(stock.code);
         tableModel.removeRow(modelIndex);
         JStock.instance().alertStateManager.clearState(stock);
      }

      updateDynamicChart(null);

      if (JStock.instance().stockCodeHistoryGUI.isEmpty())
      {
         if (JStock.instance().stockInfoDatabase != null)
            JStock.instance().setStatusBar(false, JStock.instance().getBestStatusBarMessage());
      }
   }
   
   //==============================================================
   // Class methods for the auto complete combobox to add it to
   // the panel, dettach, set stock database, request focus, or
   // greedy option.
   //==============================================================

   protected void addAutoCompleteComboBox()
   {
      stockSelectorPanel.add(org.yccheok.jstock.gui.Utils.getBusyJXLayer(stockSelectorComboBox));
   }
   
   /**
    * Dettach all and stop Ajax threading activity in combo box. Once stop, this
    * combo box can no longer be reused.
    */
   
   protected void dettachAllAndStopAutoCompleteJComboBox()
   {
      // We are no longer interest to receive any event from combo box.
      stockSelectorComboBox.dettachAll();
      // Stop all threading activities in AutoCompleteJComboBox.
      stockSelectorComboBox.stop();
   }
   
   protected void setStockInfoDatabase(StockInfoDatabase stockInfoDatabase)
   {
      stockSelectorComboBox.setStockInfoDatabase(stockInfoDatabase);
   }

   protected void setGreedyEnabled(Country country)
   {
      if (country == Country.India)
         stockSelectorComboBox.setGreedyEnabled(true, Arrays.asList("N", "B"));
      else
         stockSelectorComboBox.setGreedyEnabled(false, java.util.Collections.<String> emptyList());
   }

   protected void requestFocusOnJComboBox()
   {
      stockSelectorComboBox.getEditor().getEditorComponent().requestFocus();
   }
   
   //==============================================================
   // Class methods to add, update a stock entry to the stock watch
   // list table, get the current selected stock in the table or
   // the whole table.
   //
   // Should we synchronized the jTable1, or post the job to GUI
   // event dispatch queue?
   //==============================================================
   
   protected void addStockToTable(final Stock stock)
   {
      final JTable _jTable1 = watchListTable;
      if (java.awt.EventQueue.isDispatchThread())
      {
         final StockTableModel tableModel = (StockTableModel) _jTable1.getModel();
         tableModel.addStock(stock);
      }
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               final StockTableModel tableModel = (StockTableModel) _jTable1.getModel();
               tableModel.addStock(stock);
            }
         });
      }
   }
   
   protected void addStockToTable(final Stock stock, final StockAlert alert)
   {
      final JTable _jTable1 = watchListTable;
      if (java.awt.EventQueue.isDispatchThread())
      {
         final StockTableModel tableModel = (StockTableModel) _jTable1.getModel();
         tableModel.addStock(stock, alert);
      }
      else
      {
         SwingUtilities.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               final StockTableModel tableModel = (StockTableModel) _jTable1.getModel();
               tableModel.addStock(stock, alert);
            }
         });
      }
   }
   
   protected void updateStockToTable(final Stock stock)
   {
      StockTableModel tableModel = (StockTableModel) watchListTable.getModel();
      tableModel.updateStock(stock);
   }

   private Stock getSelectedStock()
   {
      int[] rows = watchListTable.getSelectedRows();

      if (rows.length == 1)
      {
         int row = rows[0];

         StockTableModel tableModel = (StockTableModel) watchListTable.getModel();
         int modelIndex = watchListTable.convertRowIndexToModel(row);
         return tableModel.getStock(modelIndex);
      }

      return null;
   }

   protected JTable getTable()
   {
      return watchListTable;
   }
   
   //==============================================================
   // Class methods to update the dynamic chart or make visible.
   //==============================================================

   protected void updateDynamicChart(Stock stock)
   {
      assert (EventQueue.isDispatchThread());

      DynamicChart dynamicChart = stock != null ? JStock.instance().dynamicCharts.get(stock.code)
                                                : EMPTY_DYNAMIC_CHART;
      if (dynamicChart == null)
         dynamicChart = EMPTY_DYNAMIC_CHART;

      if (Arrays.asList(dynamicChartsPanel.getComponents()).contains(dynamicChart.getChartPanel()))
         return;

      this.dynamicChartsPanel.removeAll();
      this.dynamicChartsPanel.add(dynamicChart.getChartPanel(), BorderLayout.CENTER);
      this.dynamicChartsPanel.validate();

      // Not sure why. validate itself is not enough to perform update. We
      // must call repaint as well.
      dynamicChart.getChartPanel().repaint();
      dynamicChart.getChartPanel().removeMouseListener(dynamicChartMouseAdapter);
      dynamicChart.getChartPanel().addMouseListener(dynamicChartMouseAdapter);
   }

   protected void setDynamicChartVisible()
   {
      chartPanel.setVisible(JStock.instance().getJStockOptions().isDynamicChartVisible());
   }
   
   //==============================================================
   // Various inner classes to handle mouse, key, and tool tips
   // for the stock watch list table.
   //==============================================================

   private class TableMouseAdapter extends MouseAdapter
   {
      @Override
      public void mouseClicked(MouseEvent evt)
      {
         int[] rows = watchListTable.getSelectedRows();

         if (rows.length == 1)
         {
            int row = rows[0];

            StockTableModel tableModel = (StockTableModel) watchListTable.getModel();
            int modelIndex = watchListTable.convertRowIndexToModel(row);
            Stock stock = tableModel.getStock(modelIndex);
            updateDynamicChart(stock);
         }
         else
            updateDynamicChart(null);

         if (evt.getClickCount() == 2)
         {
            // by definition of a dbl-click this will always only show one chart
            // because the dbl-click action cannot have multiple items selected
            JStock.instance().displayHistoryCharts();
         }
      }

      @Override
      public void mousePressed(MouseEvent e)
      {
         maybeShowPopup(e);
      }

      @Override
      public void mouseReleased(MouseEvent e)
      {
         maybeShowPopup(e);
      }

      private void maybeShowPopup(MouseEvent e)
      {
         if (e.isPopupTrigger())
         {
            if (watchListTable.getSelectedRowCount() <= 1)
               setFocusToRightClickLocation(e, watchListTable);

            if (watchListTable.getSelectedRowCount() > 0)
               createPopupMenu().show(e.getComponent(), e.getX(), e.getY());
         }
      }

      private JPopupMenu createPopupMenu()
      {
         final JPopupMenu popup = new JPopupMenu();
         final TableModel tableModel = watchListTable.getModel();

         javax.swing.JMenuItem menuItem = new JMenuItem(ResourceBundle.getBundle(
            "org/yccheok/jstock/data/gui").getString("MainFrame_History..."),
            JStock.instance().getImageIcon("/images/16x16/strokedocker.png"));

         menuItem.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
               JStock.instance().displayHistoryCharts();
            }
         });

         popup.add(menuItem);

         menuItem = new JMenuItem(ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
            "MainFrame_News..."), JStock.instance().getImageIcon("/images/16x16/news.png"));
         menuItem.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
               JStock.instance().displayStocksNews();
            }
         });
         popup.add(menuItem);

         popup.addSeparator();

         if (watchListTable.getSelectedRowCount() == 1)
         {
            menuItem = new JMenuItem(ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
               "MainFrame_Buy..."), JStock.instance().getImageIcon("/images/16x16/calc.png"));

            menuItem.addActionListener(new ActionListener()
            {
               @Override
               public void actionPerformed(ActionEvent evt)
               {
                  final int row = watchListTable.getSelectedRow();
                  final int modelIndex = watchListTable.getRowSorter().convertRowIndexToModel(row);
                  final Stock stock = ((StockTableModel) tableModel).getStock(modelIndex);

                  // We have a real nasty bug here. We retrieve stock information
                  // through stock code.
                  // When we receive stock information, we update all its
                  // particular information, including stock
                  // symbol. Here is the catch, the latest updated stock symbol
                  // (stock.getSymbol), may not be the
                  // same as stock symbol found in stock database. If we pass the
                  // stock symbol which is not found
                  // in stock database to portfolio, something can go wrong. This
                  // is because portfolio rely heavily
                  // on symbol <-> code conversion. Hence, instead of using
                  // stock.getSymbol, we prefer to get the
                  // symbol out from stock database. This marks the close of the
                  // following reported bugs :
                  //
                  // [2800598] buyportfolio.xml file not updated with code symbol
                  // [2790218] User unable to add new buy transaction in Spain
                  //
                  // Say no to :
                  // portfolioManagementJPanel.showNewBuyTransactionJDialog(stock.symbol,
                  // stock.getLastPrice(), false);
                  JStock.instance().portfolioManagementJPanel.showNewBuyTransactionJDialog(
                     StockInfo.newInstance(stock), stock.getLastPrice(), false);
               }
            });

            popup.add(menuItem);
            popup.addSeparator();
         }

         menuItem = new JMenuItem(ResourceBundle.getBundle("org/yccheok/jstock/data/gui").getString(
            "MainFrame_Delete"), JStock.instance().getImageIcon("/images/16x16/editdelete.png"));

         menuItem.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
               deteleSelectedTableRow();
            }
         });

         popup.add(menuItem);
         return popup;
      }

      private void setFocusToRightClickLocation(MouseEvent e, JTable table)
      {
         // get the coordinates of the mouse click
         Point p = e.getPoint();

         // get the row index that contains that coordinate
         int rowNumber = table.rowAtPoint(p);

         // either select the row or unselect row (if right-clicked outside rows
         if (rowNumber >= 0 && rowNumber < table.getRowCount())
         {
            // set the selected interval of rows. Using the "rowNumber"
            // variable for the beginning and end selects only that one row.
            table.setRowSelectionInterval(rowNumber, rowNumber);
         }
         else
            table.clearSelection();
      }
   }

   private class TableKeyEventListener extends java.awt.event.KeyAdapter
   {
      @Override
      public void keyTyped(KeyEvent e)
      {
         watchListTable.getSelectionModel().clearSelection();
      }
   }

   private static class ColumnHeaderToolTips extends MouseMotionAdapter
   {
      // Current column whose tooltip is being displayed.
      // This variable is used to minimize the calls to setToolTipText().
      TableColumn curCol;

      // Maps TableColumn objects to tooltips
      java.util.Map<TableColumn, String> tips = new HashMap<TableColumn, String>();

      // If tooltip is null, removes any tooltip text.
      public void setToolTip(TableColumn col, String tooltip)
      {
         if (tooltip == null)
            tips.remove(col);
         else
            tips.put(col, tooltip);
      }

      @Override
      public void mouseMoved(MouseEvent evt)
      {
         TableColumn col = null;
         JTableHeader header = (JTableHeader) evt.getSource();
         JTable table = header.getTable();
         TableColumnModel colModel = table.getColumnModel();
         int vColIndex = colModel.getColumnIndexAtX(evt.getX());

         // Return if not clicked on any column header
         if (vColIndex >= 0)
            col = colModel.getColumn(vColIndex);

         if (col != curCol)
         {
            header.setToolTipText(tips.get(col));
            curCol = col;
         }
      }
   }
}