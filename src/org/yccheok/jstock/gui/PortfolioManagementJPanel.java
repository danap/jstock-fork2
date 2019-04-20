/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2015 Yan Cheng Cheok <yccheok@yahoo.com>
 * Copyright (C) 2019 Dana Proctor
 * 
 * Version 1.0.7.37.04 04/20/2019
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
// Version 1.0.7.37    08/26/2018 Original Yan Cheng, JStock Gui PortfolioManagementJPanel Class.
//         1.0.7.37.01 02/14/2019 Commented Methods open/saveAsExcelFile().
//         1.0.7.37.02 04/11/2019 Constructor Integrated Call to initExchangeRateMonitor() as
//                                Instantiation Process.
//         1.0.7.37.03 04/12/2019 Constructor Removed Call to initPortfolio() & initExchangeRate
//                                Monitor() to More Appropriately Track. See JStock v1.0.7.37.36.
//         1.0.7.37.04 04/20/2019 Renamed initCSVPortfolio() to initPortfolio() Which Just Called
//                                the Former. Made Protected.
//
//-----------------------------------------------------------------
//                 yccheok@yahoo.com
//                 danap@dandymadeproductions.com
//=================================================================

package org.yccheok.jstock.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import javax.swing.tree.TreePath;
import org.apache.commons.logging.*;
import org.jdesktop.swingx.JXTableHeader;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.table.TableColumnExt;
import org.jdesktop.swingx.treetable.*;
import org.yccheok.jstock.engine.*;
import org.yccheok.jstock.engine.currency.Currency;
import org.yccheok.jstock.engine.currency.CurrencyPair;
import org.yccheok.jstock.engine.currency.ExchangeRate;
import org.yccheok.jstock.engine.currency.ExchangeRateMonitor;
import org.yccheok.jstock.file.GUIBundleWrapper;
import org.yccheok.jstock.file.Statement;
import org.yccheok.jstock.file.Statements;
import org.yccheok.jstock.file.UserDataDirectory;
import org.yccheok.jstock.file.UserDataFile;
import org.yccheok.jstock.gui.Utils.FileEx;
import org.yccheok.jstock.gui.charting.InvestmentFlowChartJDialog;
import org.yccheok.jstock.gui.portfolio.CommentJDialog;
import org.yccheok.jstock.gui.portfolio.DepositSummaryJDialog;
import org.yccheok.jstock.gui.portfolio.DepositSummaryTableModel;
import org.yccheok.jstock.gui.portfolio.DividendSummaryBarChartJDialog;
import org.yccheok.jstock.gui.portfolio.DividendSummaryJDialog;
import org.yccheok.jstock.gui.portfolio.DividendSummaryTableModel;
import org.yccheok.jstock.gui.portfolio.SplitJDialog;
import org.yccheok.jstock.gui.portfolio.ToolTipHighlighter;
import org.yccheok.jstock.gui.treetable.AbstractPortfolioTreeTableModelEx;
import org.yccheok.jstock.gui.treetable.BuyPortfolioTreeTableModelEx;
import org.yccheok.jstock.gui.treetable.DoubleWithCurrency;
import org.yccheok.jstock.gui.treetable.SellPortfolioTreeTableModelEx;
import org.yccheok.jstock.gui.treetable.SortableTreeTable;
import org.yccheok.jstock.internationalization.GUIBundle;
import org.yccheok.jstock.internationalization.MessagesBundle;
import org.yccheok.jstock.portfolio.Commentable;
import org.yccheok.jstock.portfolio.Contract;
import org.yccheok.jstock.portfolio.DecimalPlace;
import org.yccheok.jstock.portfolio.Deposit;
import org.yccheok.jstock.portfolio.DepositSummary;
import org.yccheok.jstock.portfolio.Dividend;
import org.yccheok.jstock.portfolio.DividendSummary;
import org.yccheok.jstock.portfolio.DoubleWrapper;
import org.yccheok.jstock.portfolio.Portfolio;
import org.yccheok.jstock.portfolio.PortfolioRealTimeInfo;
import org.yccheok.jstock.portfolio.Transaction;
import org.yccheok.jstock.portfolio.TransactionSummary;

/**
 *
 * @author  Owner
 * @author Dana M. Proctor
 * @version 1.0.7.37.04 04/20/2019
 */
public class PortfolioManagementJPanel extends javax.swing.JPanel {
    
    /** Creates new form PortfoliioJPanel */
    public PortfolioManagementJPanel() {
        initComponents();        
        
        //this.initPortfolio();
        //this.initExchangeRateMonitor();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        buyTreeTable = new SortableTreeTable(new BuyPortfolioTreeTableModelEx());
        // We need to have a hack way, to have "Comment" in the model, but not visible to user.
        // So that our ToolTipHighlighter can work correctly.
        // setVisible should be called after JXTreeTable has been constructed. This is to avoid
        // initGUIOptions from calling JTable.removeColumn
        // ToolTipHighlighter will not work correctly if we tend to hide column view by removeColumn.
        // We need to hide the view by using TableColumnExt.setVisible.
        // Why? Don't ask me. Ask SwingX team.
        ((TableColumnExt)buyTreeTable.getColumn(GUIBundle.getString("PortfolioManagementJPanel_Comment"))).setVisible(false);
        jScrollPane2 = new javax.swing.JScrollPane();
        sellTreeTable = new SortableTreeTable(new SellPortfolioTreeTableModelEx());

        // We need to have a hack way, to have "Comment" in the model, but not visible to user.
        // So that our ToolTipHighlighter can work correctly.
        // setVisible should be called after JXTreeTable has been constructed. This is to avoid
        // initGUIOptions from calling JTable.removeColumn
        // ToolTipHighlighter will not work correctly if we tend to hide column view by removeColumn.
        // We need to hide the view by using TableColumnExt.setVisible.
        // Why? Don't ask me. Ask SwingX team.
        ((TableColumnExt)sellTreeTable.getColumn(GUIBundle.getString("PortfolioManagementJPanel_Comment"))).setVisible(false);
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Portfolio Management"));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText(getShareLabel());
        jPanel3.add(jLabel1);

        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD));
        jPanel3.add(jLabel2);

        jLabel3.setText(getCashLabel());
        jPanel3.add(jLabel3);

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD));
        jPanel3.add(jLabel4);

        jPanel4.add(jPanel3, java.awt.BorderLayout.WEST);

        jLabel5.setText(getPaperProfitLabel());
        jPanel5.add(jLabel5);

        jLabel6.setFont(jLabel6.getFont().deriveFont(jLabel6.getFont().getStyle() | java.awt.Font.BOLD));
        jPanel5.add(jLabel6);

        jLabel7.setText(getRealizedProfitLabel());
        jPanel5.add(jLabel7);

        jLabel8.setFont(jLabel8.getFont().deriveFont(jLabel8.getFont().getStyle() | java.awt.Font.BOLD));
        jPanel5.add(jLabel8);

        jPanel4.add(jPanel5, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel4, java.awt.BorderLayout.NORTH);

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PortfolioManagementJPanel_Buy"))); // NOI18N

        buyTreeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        buyTreeTable.setRootVisible(true);
        // this must be before any sort instructions or get funny results
        buyTreeTable.setAutoCreateColumnsFromModel(false);

        buyTreeTable.addMouseListener(new BuyTableRowPopupListener());
        buyTreeTable.addKeyListener(new TableKeyEventListener());

        org.jdesktop.swingx.decorator.Highlighter highlighter0 = org.jdesktop.swingx.decorator.HighlighterFactory.createSimpleStriping(new Color(245, 245, 220));
        buyTreeTable.addHighlighter(highlighter0);
        buyTreeTable.addHighlighter(new ToolTipHighlighter());

        initTreeTableDefaultRenderer(buyTreeTable);

        // Not sure why. Without this code, sorting won't work just after you resize 
        // table header.
        JTableHeader oldBuyTableHeader = buyTreeTable.getTableHeader();
        JXTableHeader newBuyTableHeader = new JXTableHeader(oldBuyTableHeader.getColumnModel());
        buyTreeTable.setTableHeader(newBuyTableHeader);

        // We need to have a hack way, to have "Comment" in the model, but not visible to user.
        // So that our ToolTipHighlighter can work correctly.
        buyTreeTable.getTableHeader().addMouseListener(new TableColumnSelectionPopupListener(1, new String[]{GUIBundle.getString("PortfolioManagementJPanel_Comment")}));
        buyTreeTable.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                buyTreeTableValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(buyTreeTable);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PortfolioManagementJPanel_Sell"))); // NOI18N

        sellTreeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        sellTreeTable.setRootVisible(true);
        // this must be before any sort instructions or get funny results
        sellTreeTable.setAutoCreateColumnsFromModel(false);

        sellTreeTable.addMouseListener(new SellTableRowPopupListener());
        sellTreeTable.addKeyListener(new TableKeyEventListener());

        org.jdesktop.swingx.decorator.Highlighter highlighter1 = org.jdesktop.swingx.decorator.HighlighterFactory.createSimpleStriping(new Color(245, 245, 220));
        sellTreeTable.addHighlighter(highlighter1);
        sellTreeTable.addHighlighter(new ToolTipHighlighter());

        initTreeTableDefaultRenderer(sellTreeTable);

        // Not sure why. Without this code, sorting won't work just after you resize 
        // table header.
        JTableHeader oldSellTableHeader = sellTreeTable.getTableHeader();
        JXTableHeader newSellTableHeader = new JXTableHeader(oldSellTableHeader.getColumnModel());
        sellTreeTable.setTableHeader(newSellTableHeader);

        // We need to have a hack way, to have "Comment" in the model, but not visible to user.
        // So that our ToolTipHighlighter can work correctly.
        sellTreeTable.getTableHeader().addMouseListener(new TableColumnSelectionPopupListener(1, new String[]{GUIBundle.getString("PortfolioManagementJPanel_Comment")}));
        sellTreeTable.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                sellTreeTableValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(sellTreeTable);

        jSplitPane1.setRightComponent(jScrollPane2);

        jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/inbox.png"))); // NOI18N
        jButton1.setText(bundle.getString("PortfolioManagementJPanel_Buy...")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/outbox.png"))); // NOI18N
        jButton3.setText(bundle.getString("PortfolioManagementJPanel_Sell...")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/money.png"))); // NOI18N
        jButton4.setText(bundle.getString("PortfolioManagementJPanel_Cash...")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/money2.png"))); // NOI18N
        jButton5.setText(bundle.getString("PortfolioManagementJPanel_Dividen...")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<StockInfo> stockInfos = getSelectedStockInfos();
        if (stockInfos.size() == 1) {
            this.showNewBuyTransactionJDialog(stockInfos.get(0), this.getStockPrice(stockInfos.get(0).code), true);
        } else {
            this.showNewBuyTransactionJDialog(null, 0.0, true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Define our own renderers, so that we may have a consistent decimal places.
    private void initTreeTableDefaultRenderer(JXTreeTable treeTable) {
        final TableCellRenderer doubleOldTableCellRenderer = treeTable.getDefaultRenderer(Double.class);

        treeTable.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = doubleOldTableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                final String UNITS = GUIBundle.getString("PortfolioManagementJPanel_Units");
                // Do not manipulate display for "Units". We do not want 100
                // "units" displayed as 100.00 "units".
                if (false == UNITS.equals(table.getColumnName(column))) {
                    if (value != null) {
                        if (c instanceof JLabel) {
                            // Re-define the displayed value.
                            
                            // Ugly hacking.
                            if (value instanceof DoubleWrapper) {
                                DoubleWrapper v = (DoubleWrapper)value;
                                ((JLabel)c).setText(org.yccheok.jstock.portfolio.Utils.toCurrency(v.decimalPlace, v.value));
                            } else if (value instanceof DoubleWithCurrency) {
                                DoubleWithCurrency v = (DoubleWithCurrency)value;
                                Currency currency = v.currency();
                                String content = org.yccheok.jstock.portfolio.Utils.toCurrency(v.decimalPlace(), v.Double());
                                if (currency == null) {
                                    ((JLabel)c).setText(content);
                                } else {
                                    String prefix = currency.toString();
                                    if (Currency.GBX.equals(prefix)) {
                                        // Special handling.
                                        prefix = Currency.GBP;
                                    } else if (Currency.ZAC.equals(prefix)) {
                                        // Special handling.
                                        prefix = Currency.ZAR;
                                    }
                                    
                                    ((JLabel)c).setText(prefix + " " + content);
                                }  
                            }
                            else {
                                ((JLabel)c).setText(org.yccheok.jstock.portfolio.Utils.toCurrency(DecimalPlace.Four, value));
                            }
                        }
                    }
                } else {
                    if (value != null) {
                        if (c instanceof JLabel) {
                            ((JLabel)c).setText(org.yccheok.jstock.portfolio.Utils.toUnits(value));
                        }
                    }                    
                }
                return c;
            }
        });

        treeTable.setDefaultRenderer(SimpleDate.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);                
                if (value != null) {
                    if (c instanceof JLabel) {
                        DateFormat dateFormat = DateFormat.getDateInstance();
                        SimpleDate simpleDate = (SimpleDate)value;
                        ((JLabel)c).setText(dateFormat.format(simpleDate.getTime()));
                    }
                }
                return c;
            }
        });
    }

    private boolean isValidTreeTableNode(TreeTableModel treeTableModel, Object node) {
        boolean result = false;
        
        final Object root = treeTableModel.getRoot();
        
        if (node instanceof TreeTableNode) {
            TreeTableNode ttn = (TreeTableNode) node;

            while (!result && ttn != null) {
                result = ttn == root;

                ttn = ttn.getParent();
            }
        }

        return result;
    }

    private String getSelectedFirstColumnString(JXTreeTable treeTable) {
        final TreePath[] treePaths = treeTable.getTreeSelectionModel().getSelectionPaths();

        if (treePaths == null) {
            return null;
        }

        if (treePaths.length == 1) {
            return treePaths[0].getLastPathComponent().toString();
        }

        return null;
    }

    private Commentable getSelectedCommentable(JXTreeTable treeTable) {
        final TreePath[] treePaths = treeTable.getTreeSelectionModel().getSelectionPaths();

        if(treePaths == null) {
            return null;
        }

        if(treePaths.length == 1) {
            if(treePaths[0].getLastPathComponent() instanceof Commentable) {
                return (Commentable)treePaths[0].getLastPathComponent();
            }
        }

        return null;
    }

    /*
    public boolean openAsExcelFile(File file) {
        final java.util.List<Statements> statementsList = Statements.newInstanceFromExcelFile(file);
        boolean status = true;
        for (Statements statements : statementsList) {
            status = status & this.openAsStatements(statements, file);
        }
        return status;
    }
    */

    public boolean openAsCSVFile(File file) {
        final Statements statements = Statements.newInstanceFromCSVFile(file);
        return this.openAsStatements(statements, file);
    }

    public boolean openAsStatements(Statements statements, File file) {
        assert(statements != null);
        
        if (statements.getType() == Statement.Type.PortfolioManagementBuy || statements.getType() == Statement.Type.PortfolioManagementSell || statements.getType() == Statement.Type.PortfolioManagementDeposit || statements.getType() == Statement.Type.PortfolioManagementDividend) {
            final GUIBundleWrapper guiBundleWrapper = statements.getGUIBundleWrapper();
            // We will use a fixed date format (Locale.English), so that it will be
            // easier for Android to process.
            //
            // "Sep 5, 2011"    -   Locale.ENGLISH
            // "2011-9-5"       -   Locale.SIMPLIFIED_CHINESE
            // "2011/9/5"       -   Locale.TRADITIONAL_CHINESE
            // 05.09.2011       -   Locale.GERMAN
            //
            // However, for backward compatible purpose (Able to read old CSV),
            // we perform a for loop to determine the best date format.
            DateFormat dateFormat = null;
            final int size = statements.size();
            switch(statements.getType()) {
                case PortfolioManagementBuy:
                {
                    final List<Transaction> transactions = new ArrayList<Transaction>();

                    for (int i = 0; i < size; i++) {
                        final Statement statement = statements.get(i);
                        final String _code = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Code"));
                        final String _symbol = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Symbol"));
                        final String _date = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Date"));
                        final Double units = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_Units"));
                        final Double purchasePrice = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_PurchasePrice"));
                        final Double broker = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_Broker"));
                        final Double clearingFee = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_ClearingFee"));
                        final Double stampDuty = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_StampDuty"));
                        final String _comment = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Comment"));

                        StockInfo stockInfo = null;
                        if (_code.length() > 0 && _symbol.length() > 0) {
                            stockInfo = StockInfo.newInstance(Code.newInstance(_code), Symbol.newInstance(_symbol));
                        }
                        else {
                            log.error("Unexpected empty stock. Ignore");
                            // stock is null.
                            continue;
                        }
                        Date date = null;

                        if (dateFormat == null) {
                            // However, for backward compatible purpose (Able to read old CSV),
                            // we perform a for loop to determine the best date format.
                            // For the latest CSV, it should be Locale.ENGLISH.
                            Locale[] locales = {Locale.ENGLISH, Locale.SIMPLIFIED_CHINESE, Locale.GERMAN, Locale.TRADITIONAL_CHINESE, Locale.ITALIAN};
                            for (Locale locale : locales) {
                                dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                                try {
                                    date = dateFormat.parse((String)_date);
                                } catch (ParseException exp) {
                                    log.error(null, exp);
                                    date = null;
                                    dateFormat = null;
                                    continue;
                                }
                                // We had found our best dateFormat. Early break.
                                break;
                            }
                        } else {
                            // We already determine our best dateFormat.
                            try {
                                date = dateFormat.parse((String)_date);
                            } catch (ParseException exp) {
                                log.error(null, exp);
                            }
                        }

                        if (date == null) {
                            log.error("Unexpected wrong date. Ignore");
                            continue;
                        }

                        // Shall we continue to ignore, or shall we just return false to
                        // flag an error?
                        if (units == null) {
                            log.error("Unexpected wrong units. Ignore");
                            continue;
                        }
                        if (purchasePrice == null || broker == null || clearingFee == null || stampDuty == null) {
                            log.error("Unexpected wrong purchasePrice/broker/clearingFee/stampDuty. Ignore");
                            continue;
                        }

                        final SimpleDate simpleDate = new SimpleDate(date);
                        final Contract.Type type = Contract.Type.Buy;
                        final Contract.ContractBuilder builder = new Contract.ContractBuilder(stockInfo, simpleDate);
                        final Contract contract = builder.type(type).quantity(units).price(purchasePrice).build();
                        final Transaction t = new Transaction(contract, broker, stampDuty, clearingFee);
                        t.setComment(org.yccheok.jstock.portfolio.Utils.replaceCSVLineFeedToSystemLineFeed(_comment));
                        transactions.add(t);
                    }

                    // We allow empty portfolio.
                    //if (transactions.size() <= 0) {
                    //    return false;
                    //}

                    // Is there any exsiting displayed data?
                    if (this.getBuyTransactionSize() > 0) {
                        final String output = MessageFormat.format(MessagesBundle.getString("question_message_load_file_for_buy_portfolio_template"), file.getName());
                        final int result = javax.swing.JOptionPane.showConfirmDialog(JStock.instance(), output, MessagesBundle.getString("question_title_load_file_for_buy_portfolio"), javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
                        if (result != javax.swing.JOptionPane.YES_OPTION) {
                            // Assume success.
                            return true;
                        }
                    }
                    
                    this.buyTreeTable.setTreeTableModel(new BuyPortfolioTreeTableModelEx());
                    final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
                    buyPortfolioTreeTableModel.bind(this.portfolioRealTimeInfo);
                    buyPortfolioTreeTableModel.bind(this);

                    Map<String, String> metadatas = statements.getMetadatas();
                    for (Transaction transaction : transactions) {
                        final Code code = transaction.getStockInfo().code;
                        TransactionSummary transactionSummary = this.addBuyTransaction(transaction);
                        if (transactionSummary != null) {
                            String comment = metadatas.get(code.toString());
                            if (comment != null) {
                                transactionSummary.setComment(org.yccheok.jstock.portfolio.Utils.replaceCSVLineFeedToSystemLineFeed(comment));
                            }
                        }
                    }

                    // Only shows necessary columns.
                    initGUIOptions();

                    expandTreeTable(buyTreeTable);
                    
                    updateRealTimeStockMonitorAccordingToPortfolioTreeTableModels();  
                    updateExchangeRateMonitorAccordingToPortfolioTreeTableModels();
                    
                    // updateWealthHeader will be called at end of switch.
                    
                    refreshStatusBarExchangeRateVisibility();
                }
                break;

                case PortfolioManagementSell:
                {
                    final List<Transaction> transactions = new ArrayList<Transaction>();

                    for (int i = 0; i < size; i++) {
                        final Statement statement = statements.get(i);
                        final String _code = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Code"));
                        final String _symbol = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Symbol"));
                        final String _referenceDate = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_ReferenceDate"));
                        
                        // Legacy file handling. PortfolioManagementJPanel_PurchaseBroker, PortfolioManagementJPanel_PurchaseClearingFee,
                        // and PortfolioManagementJPanel_PurchaseStampDuty are introduced starting from 1.0.6x
                        Double purchaseBroker = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_PurchaseBroker"));
                        if (purchaseBroker == null) {
                            // Legacy file handling. PortfolioManagementJPanel_PurchaseFee is introduced starting from 1.0.6s
                            purchaseBroker = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_PurchaseFee"));
                            if (purchaseBroker == null) {
                                purchaseBroker = new Double(0.0);
                            }
                        }
                        Double purchaseClearingFee = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_PurchaseClearingFee"));
                        if (purchaseClearingFee == null) {
                            purchaseClearingFee = new Double(0.0);                            
                        }
                        Double purchaseStampDuty = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_PurchaseStampDuty"));
                        if (purchaseStampDuty == null) {
                            purchaseStampDuty = new Double(0.0);                            

                        }                        
                        final String _date = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Date"));
                        final Double units = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_Units"));
                        final Double sellingPrice =  statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_SellingPrice"));
                        final Double purchasePrice = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_PurchasePrice"));
                        final Double broker = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_Broker"));
                        final Double clearingFee = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_ClearingFee"));
                        final Double stampDuty = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_StampDuty"));
                        final String _comment = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Comment"));

                        StockInfo stockInfo = null;
                        if (_code.length() > 0 && _symbol.length() > 0) {
                            stockInfo = StockInfo.newInstance(Code.newInstance(_code), Symbol.newInstance(_symbol));
                        }
                        else {
                            log.error("Unexpected empty stock. Ignore");
                            // stock is null.
                            continue;
                        }

                        Date date = null;
                        Date referenceDate = null;

                        if (dateFormat == null) {
                            // However, for backward compatible purpose (Able to read old CSV),
                            // we perform a for loop to determine the best date format.
                            // For the latest CSV, it should be Locale.ENGLISH.
                            Locale[] locales = {Locale.ENGLISH, Locale.SIMPLIFIED_CHINESE, Locale.GERMAN, Locale.TRADITIONAL_CHINESE, Locale.ITALIAN};
                            for (Locale locale : locales) {
                                dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                                try {
                                    date = dateFormat.parse((String)_date);
                                    referenceDate = dateFormat.parse((String)_referenceDate);
                                } catch (ParseException exp) {
                                    log.error(null, exp);
                                    date = null;
                                    referenceDate = null;
                                    dateFormat = null;
                                    continue;
                                }
                                // We had found our best dateFormat. Early break.
                                break;
                            }
                        } else {
                            // We already determine our best dateFormat.
                            try {
                                date = dateFormat.parse((String)_date);
                                referenceDate = dateFormat.parse((String)_referenceDate);
                            } catch (ParseException exp) {
                                log.error(null, exp);
                            }
                        }

                        if (date == null || referenceDate == null) {
                            log.error("Unexpected wrong date/referenceDate. Ignore");
                            continue;
                        }
                        // Shall we continue to ignore, or shall we just return false to
                        // flag an error?
                        if (units == null) {
                            log.error("Unexpected wrong units. Ignore");
                            continue;
                        }
                        if (purchasePrice == null || broker == null || clearingFee == null || stampDuty == null || sellingPrice == null) {
                            log.error("Unexpected wrong purchasePrice/broker/clearingFee/stampDuty/sellingPrice. Ignore");
                            continue;
                        }
                        
                        final SimpleDate simpleDate = new SimpleDate(date);
                        final SimpleDate simpleReferenceDate = new SimpleDate(referenceDate);
                        final Contract.Type type = Contract.Type.Sell;
                        final Contract.ContractBuilder builder = new Contract.ContractBuilder(stockInfo, simpleDate);
                        final Contract contract = builder.type(type).quantity(units).price(sellingPrice).referencePrice(purchasePrice).referenceDate(simpleReferenceDate)
                                .referenceBroker(purchaseBroker)
                                .referenceClearingFee(purchaseClearingFee)
                                .referenceStampDuty(purchaseStampDuty)
                                .build();
                        final Transaction t = new Transaction(contract, broker, stampDuty, clearingFee);
                        t.setComment(org.yccheok.jstock.portfolio.Utils.replaceCSVLineFeedToSystemLineFeed(_comment));
                        transactions.add(t);
                    }   // for
                    
                    // We allow empty portfolio.
                    //if (transactions.size() <= 0) {
                    //    return false;
                    //}

                    // Is there any exsiting displayed data?
                    if (this.getSellTransactionSize() > 0) {
                        final String output = MessageFormat.format(MessagesBundle.getString("question_message_load_file_for_sell_portfolio_template"), file.getName());
                        final int result = javax.swing.JOptionPane.showConfirmDialog(JStock.instance(), output, MessagesBundle.getString("question_title_load_file_for_sell_portfolio"), javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
                        if (result != javax.swing.JOptionPane.YES_OPTION) {
                            // Assume success.
                            return true;
                        }
                    }

                    this.sellTreeTable.setTreeTableModel(new SellPortfolioTreeTableModelEx());                    
                    final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
                    sellPortfolioTreeTableModel.bind(this.portfolioRealTimeInfo);
                    sellPortfolioTreeTableModel.bind(this);

                    Map<String, String> metadatas = statements.getMetadatas();

                    for (Transaction transaction : transactions) {
                        final Code code = transaction.getStockInfo().code;
                        TransactionSummary transactionSummary = this.addSellTransaction(transaction);
                        if (transactionSummary != null) {
                            String comment = metadatas.get(code.toString());
                            if (comment != null) {
                                transactionSummary.setComment(org.yccheok.jstock.portfolio.Utils.replaceCSVLineFeedToSystemLineFeed(comment));
                            }
                        }
                    }

                    // Only shows necessary columns.
                    initGUIOptions();
                    
                    expandTreeTable(this.sellTreeTable);

                    updateExchangeRateMonitorAccordingToPortfolioTreeTableModels();
                    
                    // updateWealthHeader will be called at end of switch.
                    
                    refreshStatusBarExchangeRateVisibility();
                }
                break;

                case PortfolioManagementDeposit:
                {
                    final List<Deposit> deposits = new ArrayList<Deposit>();

                    for (int i = 0; i < size; i++) {
                        Date date = null;
                        final Statement statement = statements.get(i);
                        final String object0 = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Date"));
                        assert(object0 != null);

                        if (dateFormat == null) {
                            // However, for backward compatible purpose (Able to read old CSV),
                            // we will perform a for loop to determine the best date format.
                            // For the latest CSV, it should be Locale.ENGLISH.
                            Locale[] locales = {Locale.ENGLISH, Locale.SIMPLIFIED_CHINESE, Locale.GERMAN, Locale.TRADITIONAL_CHINESE, Locale.ITALIAN};
                            for (Locale locale : locales) {
                                dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                                try {
                                    date = dateFormat.parse(object0);
                                } catch (ParseException exp) {
                                    log.error(null, exp);
                                    date = null;
                                    dateFormat = null;
                                    continue;
                                }
                                // We had found our best dateFormat. Early break.
                                break;
                            }
                        } else {
                            // We already determine our best dateFormat.
                            try {
                                date = dateFormat.parse(object0);
                            } catch (ParseException exp) {
                                log.error(null, exp);
                            }
                        }

                        // Shall we continue to ignore, or shall we just return false to
                        // flag an error?
                        if (date == null) {
                            log.error("Unexpected wrong date. Ignore");
                            continue;
                        }
                        final Double cash = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_Cash"));
                        // Shall we continue to ignore, or shall we just return false to
                        // flag an error?
                        if (cash == null) {
                            log.error("Unexpected wrong cash. Ignore");
                            continue;
                        }
                        final Deposit deposit = new Deposit(cash, new SimpleDate(date));
                        
                        final String comment = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Comment"));
                        if (comment != null) {
                            // Possible to be null. As in version <=1.0.6p, comment
                            // is not being saved to CSV.
                            deposit.setComment(org.yccheok.jstock.portfolio.Utils.replaceCSVLineFeedToSystemLineFeed(comment));
                        }

                        deposits.add(deposit);
                    }

                    // We allow empty portfolio.
                    //if (deposits.size() <= 0) {
                    //    return false;
                    //}

                    // Is there any exsiting displayed data?
                    if (this.depositSummary.size() > 0) {
                        final String output = MessageFormat.format(MessagesBundle.getString("question_message_load_file_for_cash_deposit_template"), file.getName());
                        final int result = javax.swing.JOptionPane.showConfirmDialog(JStock.instance(), output, MessagesBundle.getString("question_title_load_file_for_cash_deposit"), javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
                        if (result != javax.swing.JOptionPane.YES_OPTION) {
                            // Assume success.
                            return true;
                        }                        
                    }

                    this.depositSummary = new DepositSummary();
                    
                    for (Deposit deposit : deposits) {
                        depositSummary.add(deposit);
                    }
                }
                break;

                case PortfolioManagementDividend:
                {
                    final List<Dividend> dividends = new ArrayList<Dividend>();

                    for (int i = 0; i < size; i++) {
                        Date date = null;
                        StockInfo stockInfo = null;
                        final Statement statement = statements.get(i);
                        final String object0 = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Date"));
                        assert(object0 != null);
                        
                        if (dateFormat == null) {
                            // However, for backward compatible purpose (Able to read old CSV),
                            // we will perform a for loop to determine the best date format.
                            // For the latest CSV, it should be Locale.ENGLISH.
                            Locale[] locales = {Locale.ENGLISH, Locale.SIMPLIFIED_CHINESE, Locale.GERMAN, Locale.TRADITIONAL_CHINESE, Locale.ITALIAN};
                            for (Locale locale : locales) {
                                dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
                                try {
                                    date = dateFormat.parse(object0);
                                } catch (ParseException exp) {
                                    log.error(null, exp);
                                    date = null;
                                    dateFormat = null;
                                    continue;
                                }
                                // We had found our best dateFormat. Early break.
                                break;
                            }
                        } else {
                            // We already determine our best dateFormat.
                            try {
                                date = dateFormat.parse(object0);
                            } catch (ParseException exp) {
                                log.error(null, exp);
                            }
                        }

                        // Shall we continue to ignore, or shall we just return false to
                        // flag an error?
                        if (date == null) {
                            log.error("Unexpected wrong date. Ignore");
                            continue;
                        }
                        final Double dividend = statement.getValueAsDouble(guiBundleWrapper.getString("PortfolioManagementJPanel_Dividend"));
                        // Shall we continue to ignore, or shall we just return false to
                        // flag an error?
                        if (dividend == null) {
                            log.error("Unexpected wrong dividend. Ignore");
                            continue;
                        }
                        final String codeStr = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Code"));
                        final String symbolStr = statement.getValueAsString(guiBundleWrapper.getString("MainFrame_Symbol"));
                        if (codeStr.isEmpty() == false && symbolStr.isEmpty() == false) {
                            stockInfo = StockInfo.newInstance(Code.newInstance(codeStr), Symbol.newInstance(symbolStr));
                        } else {
                            log.error("Unexpected wrong stock. Ignore");
                            // stock is null.
                            continue;
                        }
                        
                        assert(stockInfo != null);
                        assert(dividend != null);
                        assert(date != null);
                        
                        final Dividend d = new Dividend(stockInfo, dividend, new SimpleDate(date));
                        
                        final String comment = statement.getValueAsString(guiBundleWrapper.getString("PortfolioManagementJPanel_Comment"));
                        if (comment != null) {
                            // Possible to be null. As in version <=1.0.6p, comment
                            // is not being saved to CSV.
                            d.setComment(org.yccheok.jstock.portfolio.Utils.replaceCSVLineFeedToSystemLineFeed(comment));
                        }
                        
                        dividends.add(d);
                    }

                    // We allow empty portfolio.
                    //if (dividends.size() <= 0) {
                    //    return false;
                    //}                    

                    if (this.dividendSummary.size() > 0) {
                        final String output = MessageFormat.format(MessagesBundle.getString("question_message_load_file_for_dividend_template"), file.getName());
                        final int result = javax.swing.JOptionPane.showConfirmDialog(JStock.instance(), output, MessagesBundle.getString("question_title_load_file_for_dividend"), javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
                        if (result != javax.swing.JOptionPane.YES_OPTION) {
                            // Assume success.
                            return true;
                        }                        
                    }

                    this.dividendSummary = new DividendSummary();
                    
                    for (Dividend dividend : dividends) {
                        dividendSummary.add(dividend);
                    }
                }
                break;

                default:
                    assert(false);
            }   // End of switch
            
            this.updateWealthHeader();
        }
        else if (statements.getType() == Statement.Type.RealtimeInfo) {
            /* Open using other tabs. */
            return JStock.instance().openAsStatements(statements, file);
        }
        else {
            return false;
        }
        return true;
    }

    private List<StockInfo> getSelectedStockInfos() {
        List<StockInfo> stockInfos0 = this.getSelectedStockInfos(buyTreeTable);
        List<StockInfo> stockInfos1 = this.getSelectedStockInfos(sellTreeTable);
        Set<Code> c = new HashSet<>();
        List<StockInfo> stockInfos = new ArrayList<>();

        for (StockInfo stockInfo : stockInfos0) {
            if (c.contains(stockInfo.code) == false) {
                c.add(stockInfo.code);
                stockInfos.add(stockInfo);
            }
        }

        for (StockInfo stockInfo : stockInfos1) {
            if (c.contains(stockInfo.code) == false) {
                c.add(stockInfo.code);
                stockInfos.add(stockInfo);
            }
        }

        return Collections.unmodifiableList(stockInfos);
    }
    
    public double getStockPrice(Code code) {
        Double price = portfolioRealTimeInfo.stockPrices.get(code);
        if (price == null) {
            return 0.0;
        }
        return price;
    }

    private void showNewSellTransactionJDialog(List<Transaction> buyTransactions) {
        final JStock mainFrame = JStock.instance();        
        NewSellTransactionJDialog newSellTransactionJDialog = new NewSellTransactionJDialog(mainFrame, true);
        if (buyTransactions.size() > 1) {
            final String template = GUIBundle.getString("PortfolioManagementJPanel_BatchSell_template");
            newSellTransactionJDialog.setTitle(MessageFormat.format(template, newSellTransactionJDialog.getTitle(), buyTransactions.size()));
        }
        newSellTransactionJDialog.setLocationRelativeTo(this);
        newSellTransactionJDialog.setBuyTransactions(buyTransactions);       
        newSellTransactionJDialog.setVisible(true);
        
        final List<Transaction> newSellTransactions = newSellTransactionJDialog.getTransactions();
        
        for (int i = 0; i < newSellTransactions.size(); i++) {
            Transaction newSellTransaction = newSellTransactions.get(i);
            Transaction buyTransaction = buyTransactions.get(i);

            final double remain = buyTransaction.getQuantity() - newSellTransaction.getQuantity();
            
            assert(remain >= 0);
            
            addSellTransaction(newSellTransaction);
            
            final BuyPortfolioTreeTableModelEx portfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
            
            if (remain <= 0) {
                portfolioTreeTableModel.removeTransaction(buyTransaction);
            } else {
                final double newBroker = buyTransaction.getBroker() - newSellTransaction.getReferenceBroker();
                final double newStampDuty = buyTransaction.getStampDuty() - newSellTransaction.getReferenceStampDuty();
                final double newClearingFee = buyTransaction.getClearingFee() - newSellTransaction.getReferenceClearingFee();
                
                this.editBuyTransaction(
                        buyTransaction.deriveWithQuantity(remain).deriveWithBroker(newBroker).deriveWithStampDuty(newStampDuty).deriveWithClearingFee(newClearingFee), 
                        buyTransaction);
            }                        
        }

        updateWealthHeader();
    }
    
    private void showEditTransactionJDialog(Transaction transaction) {
        final JStock mainFrame = JStock.instance();

        if (transaction.getType() == Contract.Type.Buy) {
            NewBuyTransactionJDialog newTransactionJDialog = new NewBuyTransactionJDialog(mainFrame, true);
            newTransactionJDialog.setStockSelectionEnabled(false);
            newTransactionJDialog.setTransaction(transaction);
            final String template = GUIBundle.getString("PortfolioManagementJPanel_EditBuy_template");
            newTransactionJDialog.setTitle(MessageFormat.format(template, transaction.getStockInfo().symbol));
            newTransactionJDialog.setLocationRelativeTo(this);
            newTransactionJDialog.setVisible(true);

            final Transaction newTransaction = newTransactionJDialog.getTransaction();
            if (newTransaction != null) {
                this.editBuyTransaction(newTransaction, transaction);
                updateWealthHeader();
            }        
        }
        else {
            assert(transaction.getType() == Contract.Type.Sell);
            
            NewSellTransactionJDialog newTransactionJDialog = new NewSellTransactionJDialog(mainFrame, true);
            newTransactionJDialog.setSellTransaction(transaction);
            final String template = GUIBundle.getString("PortfolioManagementJPanel_EditSell_template");
            newTransactionJDialog.setTitle(MessageFormat.format(template, transaction.getStockInfo().symbol));
            newTransactionJDialog.setLocationRelativeTo(this);
            newTransactionJDialog.setVisible(true);

            List<Transaction> transactions = newTransactionJDialog.getTransactions();
            for (Transaction newTransaction : transactions) {
                this.editSellTransaction(newTransaction, transaction);
                updateWealthHeader();
            }                    
        }
    }
    
    public void showNewBuyTransactionJDialog(StockInfo stockInfo, double lastPrice, boolean JComboBoxEnabled) {

        final JStock mainFrame = JStock.instance();

        final StockInfoDatabase stockInfoDatabase = mainFrame.getStockInfoDatabase();
        
        if (stockInfoDatabase == null) {
            javax.swing.JOptionPane.showMessageDialog(this, MessagesBundle.getString("info_message_we_havent_connected_to_stock_server"), MessagesBundle.getString("info_title_we_havent_connected_to_stock_server"), javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        NewBuyTransactionJDialog newTransactionJDialog = new NewBuyTransactionJDialog(mainFrame, true);
        newTransactionJDialog.setLocationRelativeTo(this);
        newTransactionJDialog.setStockInfo(stockInfo);
        newTransactionJDialog.setPrice(lastPrice);
        newTransactionJDialog.setJComboBoxEnabled(JComboBoxEnabled);
        newTransactionJDialog.setStockInfoDatabase(stockInfoDatabase);

        // If we are not in portfolio page, we shall provide user a hint, so that
        // user will know this transaction will go into which portfolio, without
        // having to click on the Portfolio drop-down menu.
        if (mainFrame.getSelectedComponent() != this) {
            final JStockOptions jStockOptions = mainFrame.getJStockOptions();
            final String title = newTransactionJDialog.getTitle() + " (" + jStockOptions.getPortfolioName() + ")";
            newTransactionJDialog.setTitle(title);
        }

        newTransactionJDialog.setVisible(true);
        
        final Transaction transaction = newTransactionJDialog.getTransaction();
        if (transaction != null) {
            this.addBuyTransaction(transaction);
            this.refreshStatusBarExchangeRateVisibility();
            this.updateWealthHeader();
        }
    }
    
    public void clearTableSelection() {
        buyTreeTable.getSelectionModel().clearSelection();
        sellTreeTable.getSelectionModel().clearSelection();
    }
    
    private boolean deleteSelectedTreeTableRow(org.jdesktop.swingx.JXTreeTable treeTable) {
        final AbstractPortfolioTreeTableModelEx portfolioTreeTableModel = (AbstractPortfolioTreeTableModelEx)treeTable.getTreeTableModel();
        final TreePath[] treePaths = treeTable.getTreeSelectionModel().getSelectionPaths();
        
        if (treePaths == null) {
            return false;
        }
        
        boolean atLeastOnce = false;
        
        for (TreePath treePath : treePaths) {
            final Object o = treePath.getLastPathComponent();
            if (portfolioTreeTableModel.getRoot() == o) {
                continue;
            }
            final MutableTreeTableNode mutableTreeTableNode = (MutableTreeTableNode)o;
            if (isValidTreeTableNode(portfolioTreeTableModel, mutableTreeTableNode) == false) {
                //???
                portfolioTreeTableModel.fireTreeTableNodeChanged(mutableTreeTableNode);
                continue;
            }
                        
            if (o instanceof Transaction) {
                portfolioTreeTableModel.removeTransaction((Transaction)o);     
                atLeastOnce = true;
            }
            else if (o instanceof TransactionSummary) {
                portfolioTreeTableModel.removeTransactionSummary((TransactionSummary)o);
                atLeastOnce = true;
            }
        } 
        
        return atLeastOnce;
    }
    
    private void deteleSelectedTreeTableRow() {
        boolean refreshStatusBarExchangeRateVisibility = false;
        
        if (deleteSelectedTreeTableRow(this.buyTreeTable)) {
            refreshStatusBarExchangeRateVisibility = true;
        }
        
        if (deleteSelectedTreeTableRow(this.sellTreeTable)) {
            refreshStatusBarExchangeRateVisibility = true;
        };
        
        if (refreshStatusBarExchangeRateVisibility) {
            this.refreshStatusBarExchangeRateVisibility();
        }
        
        updateWealthHeader();
    }
    
    private void buyTreeTableValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_buyTreeTableValueChanged
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (buyTreeTable.getSelectedRowCount() > 0) {
                    sellTreeTable.clearSelection();
                }
            }
        });
    }//GEN-LAST:event_buyTreeTableValueChanged

    private void sellTreeTableValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_sellTreeTableValueChanged
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (sellTreeTable.getSelectedRowCount() > 0) {
                    buyTreeTable.clearSelection();
                }
            }
        });
    }//GEN-LAST:event_sellTreeTableValueChanged

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        this.clearTableSelection();
    }//GEN-LAST:event_formMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        final List<StockInfo> stockInfos = this.getSelectedStockInfos(buyTreeTable);
        if (stockInfos.size() != 1) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/messages").getString("info_message_you_need_to_select_only_single_stock_from_buy_portfolio_to_perform_sell_transaction"), java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/messages").getString("info_title_you_need_to_select_only_single_stock_from_buy_portfolio_to_perform_sell_transaction"), JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<Transaction> transactions = this.getSelectedTransactions(buyTreeTable);
        this.showNewSellTransactionJDialog(transactions);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        showDepositSummaryJDialog();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        showDividendSummaryJDialog();
    }//GEN-LAST:event_jButton5ActionPerformed

    // When transaction summary being selected, we assume all its transactions are being selected.
    // This is most of the users intention too, I guess.
    private List<Transaction> getSelectedTransactions(JXTreeTable treeTable) {
        final TreePath[] treePaths = treeTable.getTreeSelectionModel().getSelectionPaths();
        List<Transaction> transactions = new ArrayList<Transaction>();

        if(treePaths == null) {
            return Collections.unmodifiableList(transactions);
        }

        for (TreePath treePath : treePaths) {
            final Object o = treePath.getLastPathComponent();
            if (o instanceof Transaction) {
                final Transaction transaction = (Transaction)o;

                if (transactions.contains(transaction) == false) {
                    transactions.add(transaction);
                }
            }
            else if (o instanceof TransactionSummary) {
                final TransactionSummary transactionSummary = (TransactionSummary)o;
                final int count = transactionSummary.getChildCount();
                for (int i = 0; i < count; i++) {
                    final Transaction transaction = (Transaction)transactionSummary.getChildAt(i);

                    if (transactions.contains(transaction) == false) {
                        transactions.add(transaction);
                    }
                }
            }
        }
        
        return Collections.unmodifiableList(transactions);
    }
    
    private boolean isOnlyTreeTableRootBeingSelected(JXTreeTable treeTable) {
        if(treeTable.getSelectedRowCount() != 1) return false;
        
        final TreePath[] treePaths = treeTable.getTreeSelectionModel().getSelectionPaths();
        
        final Object o = treePaths[0].getLastPathComponent();

        final AbstractPortfolioTreeTableModelEx portfolioTreeTableModel = (AbstractPortfolioTreeTableModelEx)treeTable.getTreeTableModel();
        
        return (portfolioTreeTableModel.getRoot() == o);
    }

    private void showDividendSummaryJDialog() {
        final JStock mainFrame = JStock.instance();
        DividendSummaryJDialog dividendSummaryJDialog = new DividendSummaryJDialog(mainFrame, true, this.getDividendSummary(), this);
        dividendSummaryJDialog.setLocationRelativeTo(this);

        List<StockInfo> stockInfos = this.getSelectedStockInfos();
        if (stockInfos.size() == 1) {
            dividendSummaryJDialog.addNewDividend(stockInfos.get(0));
        }
        dividendSummaryJDialog.setVisible(true);

        final DividendSummary _dividendSummary = dividendSummaryJDialog.getDividendSummaryAfterPressingOK();
        if (_dividendSummary != null) {
            this.dividendSummary = _dividendSummary;
            updateWealthHeader();
        }
    }

    private void showSplitOrMergeJDialog(StockInfo stockInfo) {
        final JStock mainFrame = JStock.instance();
        SplitJDialog splitOrMergeJDialog = new SplitJDialog(mainFrame, true, stockInfo);
        splitOrMergeJDialog.pack();
        splitOrMergeJDialog.setLocationRelativeTo(this);
        splitOrMergeJDialog.setVisible(true);

        if (splitOrMergeJDialog.getRatio() == null) {
            return;
        }

        double ratio = splitOrMergeJDialog.getRatio();
        final BuyPortfolioTreeTableModelEx portfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        // Perform splitting. (Or merging)
        portfolioTreeTableModel.split(stockInfo, ratio);
        // Update the wealth. The value of wealth shall remain unchanged.
        this.updateWealthHeader();
    }

    private void showDepositSummaryJDialog() {
        final JStock mainFrame = JStock.instance();
        DepositSummaryJDialog depositSummaryJDialog = new DepositSummaryJDialog(mainFrame, true, this.getDepositSummary());
        depositSummaryJDialog.setLocationRelativeTo(this);
        depositSummaryJDialog.setVisible(true);

        final DepositSummary _depositSummary = depositSummaryJDialog.getDepositSummaryAfterPressingOK();
        if (_depositSummary != null) {
            this.depositSummary = _depositSummary;
            updateWealthHeader();
        }
    }
    
    private void showCommentJDialog(Commentable commentable, String title) {
        if (commentable == null) {
            // Nothing to be shown.
            return;
        }

        final JStock mainFrame = JStock.instance();
        CommentJDialog commentJDialog = new CommentJDialog(mainFrame, true, commentable);
        commentJDialog.setTitle(title);
        commentJDialog.setLocationRelativeTo(this);
        commentJDialog.setVisible(true);
    }

    /**
     * @return the depositSummary
     */
    public DepositSummary getDepositSummary() {
        return depositSummary;
    }

    /**
     * @return the dividendSummary
     */
    public DividendSummary getDividendSummary() {
        return dividendSummary;
    }

    private class TableKeyEventListener extends java.awt.event.KeyAdapter {
        @Override
        public void keyTyped(java.awt.event.KeyEvent e) {
            PortfolioManagementJPanel.this.clearTableSelection();
        }
    }

    private class BuyTableRowPopupListener extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent evt) {
            if(evt.getClickCount() == 2) {
                final List<Transaction> transactions = getSelectedTransactions(buyTreeTable);
                if (transactions.size() == 1) {
                    PortfolioManagementJPanel.this.showEditTransactionJDialog(transactions.get(0));
                }
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }
        
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                getBuyTreeTablePopupMenu().show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    private class SellTableRowPopupListener extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent evt) {
            if(evt.getClickCount() == 2) {
                final List<Transaction> transactions = getSelectedTransactions(sellTreeTable);
                if (transactions.size() == 1) {
                    PortfolioManagementJPanel.this.showEditTransactionJDialog(transactions.get(0));
                }
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }
        
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                final JPopupMenu popupMenu = getSellTreeTablePopupMenu();
                if(popupMenu != null)
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
    
    private ImageIcon getImageIcon(String imageIcon) {
        return new javax.swing.ImageIcon(getClass().getResource(imageIcon));
    }
    
    private void showBuyPortfolioChartJDialog() {
        final JStock m = JStock.instance();
        final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        BuyPortfolioChartJDialog buyPortfolioChartJDialog = new BuyPortfolioChartJDialog(m, false, buyPortfolioTreeTableModel, this.portfolioRealTimeInfo, this.getDividendSummary());
        buyPortfolioChartJDialog.setVisible(true);                                    
    }

    private void showChashFlowChartJDialog() {
        final JStock m = JStock.instance();
        InvestmentFlowChartJDialog cashFlowChartJDialog = new InvestmentFlowChartJDialog(m, false, this);
        cashFlowChartJDialog.setVisible(true);
    }

    private void showSellPortfolioChartJDialog() {
        final JStock m = JStock.instance();
        final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        SellPortfolioChartJDialog sellPortfolioChartJDialog = new SellPortfolioChartJDialog(m, false, sellPortfolioTreeTableModel, this.portfolioRealTimeInfo, this.getDividendSummary());
        sellPortfolioChartJDialog.setVisible(true);                                    
    }
    
    private JPopupMenu getSellTreeTablePopupMenu() {                
        final List<Transaction> transactions = getSelectedTransactions(this.sellTreeTable);

        JPopupMenu popup = new JPopupMenu();

        JMenuItem menuItem = null;

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagementJPanel_Cash..."), this.getImageIcon("/images/16x16/money.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showDepositSummaryJDialog();
            }
        });

        popup.add(menuItem);

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Dividend..."), this.getImageIcon("/images/16x16/money2.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showDividendSummaryJDialog();
            }
        });

        popup.add(menuItem);

        popup.addSeparator();
        
        if (transactions.size() == 1) {
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Edit..."), this.getImageIcon("/images/16x16/edit.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PortfolioManagementJPanel.this.showEditTransactionJDialog(transactions.get(0));
                }
            });            

            popup.add(menuItem);
        }

        final Commentable commentable = getSelectedCommentable(this.sellTreeTable);
        final String tmp = getSelectedFirstColumnString(this.sellTreeTable);
        if (commentable != null && tmp != null) {
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Note..."), this.getImageIcon("/images/16x16/sticky.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    final String template = GUIBundle.getString("PortfolioManagementJPanel_NoteFor_template");
                    final String title = MessageFormat.format(template, tmp);
                    PortfolioManagementJPanel.this.showCommentJDialog(commentable, title);
                }
            });

            popup.add(menuItem);

            popup.addSeparator();
        }
        else if (transactions.size() == 1) {
            popup.addSeparator();
        }

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_InvestmentChart..."), this.getImageIcon("/images/16x16/graph.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showChashFlowChartJDialog();
            }
        });

        popup.add(menuItem);

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagementJPanel_DividendChart"), this.getImageIcon("/images/16x16/chart.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PortfolioManagementJPanel.this.showDividendSummaryBarChartJDialog();
            }
        });

        popup.add(menuItem);

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Summary..."), this.getImageIcon("/images/16x16/pie_chart.png"));
        
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PortfolioManagementJPanel.this.showSellPortfolioChartJDialog();
            }
        });

        popup.add(menuItem);        
        
        if(isOnlyTreeTableRootBeingSelected(sellTreeTable) == false && (sellTreeTable.getSelectedRow() > 0)) {
            final JStock m = JStock.instance();
                                
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_History..."), this.getImageIcon("/images/16x16/strokedocker.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    List<StockInfo> stockInfos = getSelectedStockInfos(sellTreeTable);

                    for (StockInfo stockInfo : stockInfos) {
                        m.displayHistoryChart(stockInfo);
                    }
                }
            });

            popup.addSeparator();
            
            popup.add(menuItem);
            
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_News..."), this.getImageIcon("/images/16x16/news.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    List<StockInfo> stockInfos = getSelectedStockInfos(sellTreeTable);

                    for(StockInfo stockInfo : stockInfos) {
                        m.displayStockNews(stockInfo);
                    }
                }
            });
            
            popup.add(menuItem);

            popup.addSeparator();
            
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Delete"), this.getImageIcon("/images/16x16/editdelete.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PortfolioManagementJPanel.this.deteleSelectedTreeTableRow();
                }
            });

            popup.add(menuItem);
        }
        
        return popup;
    }

    private void showDividendSummaryBarChartJDialog() {
        final JStock m = JStock.instance();
        final DividendSummaryBarChartJDialog dividendSummaryBarChartJDialog = new DividendSummaryBarChartJDialog(m, false, this.getDividendSummary());
        dividendSummaryBarChartJDialog.setVisible(true);
    }

    private JPopupMenu getBuyTreeTablePopupMenu() {                
        JPopupMenu popup = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Buy..."), this.getImageIcon("/images/16x16/inbox.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                List<StockInfo> stockInfos = getSelectedStockInfos();
                if (stockInfos.size() == 1) {
                    PortfolioManagementJPanel.this.showNewBuyTransactionJDialog(stockInfos.get(0), PortfolioManagementJPanel.this.getStockPrice(stockInfos.get(0).code), true);
                }
                else {
                    PortfolioManagementJPanel.this.showNewBuyTransactionJDialog(StockInfo.newInstance(Code.newInstance(""), Symbol.newInstance("")), 0.0, true);
                }
            }
        });

        popup.add(menuItem);

        final List<Transaction> transactions = getSelectedTransactions(this.buyTreeTable);
        final List<StockInfo> stockInfos = this.getSelectedStockInfos(this.buyTreeTable);

        if (transactions.size() > 0 && stockInfos.size() == 1) {
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Sell..."), this.getImageIcon("/images/16x16/outbox.png"));
            
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PortfolioManagementJPanel.this.showNewSellTransactionJDialog(transactions);
                }
            });            
            
            popup.add(menuItem);  
        }       

        popup.addSeparator();

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagementJPanel_Cash..."), this.getImageIcon("/images/16x16/money.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showDepositSummaryJDialog();
            }
        });

        popup.add(menuItem);

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Dividend..."), this.getImageIcon("/images/16x16/money2.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showDividendSummaryJDialog();
            }
        });

        popup.add(menuItem);

        popup.addSeparator();

        boolean needToAddSeperator = false;

        if (transactions.size() == 1) {
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Edit..."), this.getImageIcon("/images/16x16/edit.png"));
            
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PortfolioManagementJPanel.this.showEditTransactionJDialog(transactions.get(0));
                }
            });            
            
            popup.add(menuItem);
            needToAddSeperator = true;
        }       

        final Commentable commentable = getSelectedCommentable(this.buyTreeTable);
        final String tmp = getSelectedFirstColumnString(this.buyTreeTable);
        if (commentable != null && tmp != null) {
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Note..."), this.getImageIcon("/images/16x16/sticky.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    final String template = GUIBundle.getString("PortfolioManagementJPanel_NoteFor_template");
                    final String title = MessageFormat.format(template, tmp);
                    PortfolioManagementJPanel.this.showCommentJDialog(commentable, title);
                }
            });

            popup.add(menuItem);
            needToAddSeperator = true;
        }

        // Split or merge only allowed, if there is one and only one stock
        // being selected.
        final List<StockInfo> selectedStockInfos = this.getSelectedStockInfos();
        if (selectedStockInfos.size() == 1) {
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagementJPanel_SplitOrMerge"), this.getImageIcon("/images/16x16/merge.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PortfolioManagementJPanel.this.showSplitOrMergeJDialog(selectedStockInfos.get(0));
                }
            });

            popup.add(menuItem);
            needToAddSeperator = true;
        }

        if (needToAddSeperator) {
            popup.addSeparator();
        }

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_InvestmentChart..."), this.getImageIcon("/images/16x16/graph.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                showChashFlowChartJDialog();
            }
        });

        popup.add(menuItem);

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagementJPanel_DividendChart"), this.getImageIcon("/images/16x16/chart.png"));

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PortfolioManagementJPanel.this.showDividendSummaryBarChartJDialog();
            }
        });

        popup.add(menuItem);

        menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Summary..."), this.getImageIcon("/images/16x16/pie_chart.png"));
        
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PortfolioManagementJPanel.this.showBuyPortfolioChartJDialog();
            }
        });

        popup.add(menuItem);                
        
        if (isOnlyTreeTableRootBeingSelected(buyTreeTable) == false && (buyTreeTable.getSelectedRow() > 0)) {
            final JStock m = JStock.instance();
                                
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_History..."), this.getImageIcon("/images/16x16/strokedocker.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    List<StockInfo> stockInfos = getSelectedStockInfos(buyTreeTable);

                    for (StockInfo stockInfo : stockInfos) {
                        m.displayHistoryChart(stockInfo);
                    }
                }
            });

            popup.addSeparator();
            
            popup.add(menuItem);

            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_News..."), this.getImageIcon("/images/16x16/news.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    List<StockInfo> stockInfos = getSelectedStockInfos(buyTreeTable);

                    for (StockInfo stockInfo : stockInfos) {
                        m.displayStockNews(stockInfo);
                    }
                }
            });
                        
            popup.add(menuItem);

            popup.addSeparator();
            
            menuItem = new JMenuItem(GUIBundle.getString("PortfolioManagement_Delete"), this.getImageIcon("/images/16x16/editdelete.png"));

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    PortfolioManagementJPanel.this.deteleSelectedTreeTableRow();
                }
            });

            popup.add(menuItem);
        }
        
        return popup;
    }

    private void editSellTransaction(Transaction newTransaction, Transaction oldTransaction) {
        assert(newTransaction.getType() == Contract.Type.Sell);
        assert(oldTransaction.getType() == Contract.Type.Sell);
        
        final SellPortfolioTreeTableModelEx portfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        portfolioTreeTableModel.editTransaction(newTransaction, oldTransaction);        
    }
    
    private void editBuyTransaction(Transaction newTransaction, Transaction oldTransaction) {
        assert(newTransaction.getType() == Contract.Type.Buy);
        assert(oldTransaction.getType() == Contract.Type.Buy);
        
        final BuyPortfolioTreeTableModelEx portfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        portfolioTreeTableModel.editTransaction(newTransaction, oldTransaction);        
    }

    private Set<Code> getCodes(org.yccheok.jstock.gui.treetable.SortableTreeTable treeTable) {
        Set<Code> codes = new HashSet<>();
        
        final AbstractPortfolioTreeTableModelEx portfolioTreeTableModelEx = (AbstractPortfolioTreeTableModelEx)treeTable.getTreeTableModel();
        Portfolio portfolio = (Portfolio) portfolioTreeTableModelEx.getRoot();
        
        Enumeration<? extends MutableTreeTableNode> transactionSummaries = portfolio.children();
        
        while (transactionSummaries.hasMoreElements()) {
            TransactionSummary transactionSummary = (TransactionSummary)transactionSummaries.nextElement();
            
            Enumeration<? extends MutableTreeTableNode> transactions = transactionSummary.children();
            if (transactions.hasMoreElements()) {
                Transaction transaction = (Transaction)transactionSummary.children().nextElement();
                StockInfo stockInfo = transaction.getStockInfo();
                codes.add(stockInfo.code);
            }
        }
        
        return codes;
    }
    
    private Set<Code> getBuyCodes() {
        return getCodes(buyTreeTable);
    }
    
    private Set<Code> getSellCodes() {
        return getCodes(sellTreeTable);
    }
    
    private Set<Code> getCodes() {
        Set<Code> codes = getBuyCodes();
        codes.addAll(getSellCodes());
        return codes;
    }
    
    private Set<CurrencyPair> getCurrencyPairs() {
        final Currency localCurrency = org.yccheok.jstock.portfolio.Utils.getLocalCurrency();
        if (localCurrency == null) {
            return Collections.<CurrencyPair>emptySet();
        }
        
        Set<CurrencyPair> currencyPairs = new HashSet<>();
        
        Set<Code> codes = getCodes();
        
        for (Code code : codes) {
            final Currency stockCurrency = org.yccheok.jstock.portfolio.Utils.getStockCurrency(portfolioRealTimeInfo, code);
                
            if (stockCurrency.equals(localCurrency)) {
                continue;
            }

            CurrencyPair currencyPair = CurrencyPair.create(stockCurrency, localCurrency);
            currencyPairs.add(currencyPair);
        }
        
        return currencyPairs;
    }
    
    private int getBuyTransactionSize() {
        final BuyPortfolioTreeTableModelEx portfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        return portfolioTreeTableModel.getTransactionSize();
    }

    private int getSellTransactionSize() {
        final SellPortfolioTreeTableModelEx portfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        return portfolioTreeTableModel.getTransactionSize();
    }

    private TransactionSummary addBuyTransaction(Transaction transaction) {
        assert(transaction.getType() == Contract.Type.Buy);
        
        final BuyPortfolioTreeTableModelEx portfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        TransactionSummary transactionSummary = portfolioTreeTableModel.addTransaction(transaction);

        // This is to prevent NPE, during initPortfolio through constructor.
        // Information will be pumped in later to realTimeStockMonitor, through 
        // initRealTimeStockMonitor.
        final RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
        if (_realTimeStockMonitor != null) {
            _realTimeStockMonitor.addStockCode(transaction.getStockInfo().code);
            _realTimeStockMonitor.startNewThreadsIfNecessary();
            _realTimeStockMonitor.refresh();
        }
        
        final ExchangeRateMonitor _exchangeRateMonitor = this.exchangeRateMonitor;
        if (_exchangeRateMonitor != null) {
            final Currency localCurrency = org.yccheok.jstock.portfolio.Utils.getLocalCurrency();
            if (localCurrency != null) {
                final Currency stockCurrency = org.yccheok.jstock.portfolio.Utils.getStockCurrency(portfolioRealTimeInfo, transaction.getStockInfo().code);
                if (false == stockCurrency.equals(localCurrency)) {
                    _exchangeRateMonitor.addCurrencyPair(CurrencyPair.create(stockCurrency, localCurrency));
                    _exchangeRateMonitor.startNewThreadsIfNecessary();
                    _exchangeRateMonitor.refresh();
                }
            }
        }
        
        return transactionSummary;
    }

    public List<TransactionSummary> getTransactionSummariesFromPortfolios() {
        final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        final Portfolio buyPortfolio = (Portfolio) buyPortfolioTreeTableModel.getRoot();
        final Portfolio sellPortfolio = (Portfolio) sellPortfolioTreeTableModel.getRoot();
        List<TransactionSummary> summaries = new ArrayList<>();

        for (int i = 0, count = buyPortfolio.getChildCount(); i < count; i++) {
            summaries.add((TransactionSummary)buyPortfolio.getChildAt(i));
        }
        
        for (int i = 0, count = sellPortfolio.getChildCount(); i < count; i++) {
            summaries.add((TransactionSummary)sellPortfolio.getChildAt(i));
        }

        return summaries;
    }

    public List<StockInfo> getStockInfosFromPortfolios() {
        final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        final Portfolio buyPortfolio = (Portfolio) buyPortfolioTreeTableModel.getRoot();
        final Portfolio sellPortfolio = (Portfolio) sellPortfolioTreeTableModel.getRoot();

        Set<Code> codes = new HashSet<>();
        List<StockInfo> stockInfos = new ArrayList<>();

        final int count = buyPortfolio.getChildCount();
        TransactionSummary transactionSummary = null;
        for (int i = 0; i < count; i++) {
            transactionSummary = (TransactionSummary)buyPortfolio.getChildAt(i);

            assert(transactionSummary.getChildCount() > 0);

            Transaction transaction = (Transaction)transactionSummary.getChildAt(0);

            StockInfo stockInfo = transaction.getStockInfo();

            if (codes.contains(stockInfo.code) == false) {
                codes.add(stockInfo.code);
                stockInfos.add(stockInfo);
            }
        }

        final int count2 = sellPortfolio.getChildCount();
        transactionSummary = null;
        for (int i = 0; i < count2; i++) {
            transactionSummary = (TransactionSummary)sellPortfolio.getChildAt(i);

            assert(transactionSummary.getChildCount() > 0);

            Transaction transaction = (Transaction)transactionSummary.getChildAt(0);

            StockInfo stockInfo = transaction.getStockInfo();

            if (codes.contains(stockInfo.code) == false) {
                codes.add(stockInfo.code);
                stockInfos.add(stockInfo);
            }
        }

        return stockInfos;
    }

    private TransactionSummary addSellTransaction(Transaction transaction) {
        assert(transaction.getType() == Contract.Type.Sell);
        
        final SellPortfolioTreeTableModelEx portfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        final TransactionSummary transactionSummary = portfolioTreeTableModel.addTransaction(transaction);
        
        final ExchangeRateMonitor _exchangeRateMonitor = this.exchangeRateMonitor;
        if (_exchangeRateMonitor != null) {
            final Currency localCurrency = org.yccheok.jstock.portfolio.Utils.getLocalCurrency();
            if (localCurrency != null) {
                final Currency stockCurrency = org.yccheok.jstock.portfolio.Utils.getStockCurrency(portfolioRealTimeInfo, transaction.getStockInfo().code);
                
                if (false == stockCurrency.equals(localCurrency)) {
                    _exchangeRateMonitor.addCurrencyPair(CurrencyPair.create(stockCurrency, localCurrency));
                    _exchangeRateMonitor.startNewThreadsIfNecessary();
                    _exchangeRateMonitor.refresh();
                }
            }
        }
        
        return transactionSummary;
    }
    
    private void updateExchangeRateMonitorAccordingToPortfolioTreeTableModels() {
        ExchangeRateMonitor _exchangeRateMonitor = this.exchangeRateMonitor;
        
        if (_exchangeRateMonitor == null) {
            return;
        }
                
        _exchangeRateMonitor.clearCurrencyPairs();
        
        Set<CurrencyPair> currencyPairs = this.getCurrencyPairs();

        for (CurrencyPair currencyPair : currencyPairs) {
            _exchangeRateMonitor.addCurrencyPair(currencyPair);
        }

        _exchangeRateMonitor.startNewThreadsIfNecessary();
        _exchangeRateMonitor.refresh();
    }
    
    private void updateRealTimeStockMonitorAccordingToPortfolioTreeTableModels() {
        RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
        
        if (_realTimeStockMonitor == null) {
            return;
        }
         
        _realTimeStockMonitor.clearStockCodes();

        for (Code code : getBuyCodes()) {
            _realTimeStockMonitor.addStockCode(code);
        }
        
        for (Code code : getSellCodes()) {
            // This is for optimization purpose. If we already have currency information for a stock
            // code, we will not further query for it. However, this does have its own shortcoming.
            // If the stock currency has been changed, we will not have chance to make any amendment.
            // So far, we treat this as an extremely rare case, and should not happen in real life.
            // However, if it does so, please remove the following optimization - contains(code)
            // check.
            if (false == this.portfolioRealTimeInfo.currencies.containsKey(code)) {
                _realTimeStockMonitor.addStockCode(code);
            }
        }
        
        _realTimeStockMonitor.startNewThreadsIfNecessary();
        _realTimeStockMonitor.refresh();
    }
    
    private List<StockInfo> getSelectedStockInfos(JXTreeTable treeTable) {
        final TreePath[] treePaths = treeTable.getTreeSelectionModel().getSelectionPaths();
        List<StockInfo> stockInfos = new ArrayList<>();
        Set<Code> c = new HashSet<>();
        
        if (treePaths == null) {
            return Collections.unmodifiableList(stockInfos);
        }
        
        for (TreePath treePath : treePaths) {
            final Object lastPathComponent = treePath.getLastPathComponent();
            
            if (lastPathComponent instanceof TransactionSummary) {
                final TransactionSummary transactionSummary = (TransactionSummary)lastPathComponent;
                assert(transactionSummary.getChildCount() > 0);
                final Transaction transaction = (Transaction)transactionSummary.getChildAt(0);
                final StockInfo stockInfo = transaction.getStockInfo();
                final Code code = stockInfo.code;
                
                if(c.contains(code)) continue;
                
                stockInfos.add(stockInfo);
                c.add(code);
            }
            else if (lastPathComponent instanceof Transaction) {
                final Transaction transaction = (Transaction)lastPathComponent;
                final StockInfo stockInfo = transaction.getStockInfo();
                final Code code = stockInfo.code;
                
                if(c.contains(code)) continue;
                
                stockInfos.add(stockInfo);
                c.add(code);
            }                        
        }
        
        return Collections.unmodifiableList(stockInfos);
    }

    // Initialize portfolios through CSV files. This is the preferable method,
    // as it works well under Desktop platform and Android platform.
    protected boolean initPortfolio() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        
        List<String> availablePortfolioNames = org.yccheok.jstock.portfolio.Utils.getPortfolioNames();
        // Do we have any portfolio for this country?
        if (availablePortfolioNames.size() <= 0) {
            // If not, create an empty portfolio.
            org.yccheok.jstock.portfolio.Utils.createEmptyPortfolio(org.yccheok.jstock.portfolio.Utils.getDefaultPortfolioName());
            availablePortfolioNames = org.yccheok.jstock.portfolio.Utils.getPortfolioNames();
        }
        assert(availablePortfolioNames.isEmpty() == false);

        // Is user selected portfolio name within current available portfolio names?
        if (false == availablePortfolioNames.contains(jStockOptions.getPortfolioName())) {
            // Nope. Reset user selected portfolio name to the first available name.
            jStockOptions.setPortfolioName(availablePortfolioNames.get(0));
        }
        
        // Clear the previous data structures.
        PortfolioManagementJPanel.this.buyTreeTable.setTreeTableModel(new BuyPortfolioTreeTableModelEx());
        PortfolioManagementJPanel.this.sellTreeTable.setTreeTableModel(new SellPortfolioTreeTableModelEx());
        PortfolioManagementJPanel.this.depositSummary = new DepositSummary();
        PortfolioManagementJPanel.this.dividendSummary = new DividendSummary();
            
        final File buyPortfolioFile = new File(org.yccheok.jstock.portfolio.Utils.getPortfolioDirectory() + "buyportfolio.csv");
        final File sellPortfolioFile = new File(org.yccheok.jstock.portfolio.Utils.getPortfolioDirectory() + "sellportfolio.csv");
        final File depositSummaryFile = new File(org.yccheok.jstock.portfolio.Utils.getPortfolioDirectory() + "depositsummary.csv");
        final File dividendSummaryFile = new File(org.yccheok.jstock.portfolio.Utils.getPortfolioDirectory() + "dividendsummary.csv");

        // PortfolioRealTimeInfo should be the very first thing to be loaded,
        // as updateRealTimeStockMonitorAccordingToPortfolioTreeTableModels depends on this.portfolioRealTimeInfo.
        File portfolioRealTimeInfoFile = new File(org.yccheok.jstock.portfolio.Utils.getPortfolioRealTimeInfoFilepath());
        PortfolioRealTimeInfo _portfolioRealTimeInfo = new PortfolioRealTimeInfo();
        boolean status = _portfolioRealTimeInfo.load(portfolioRealTimeInfoFile);
        if (false == status) {
            Pair<HashMap<Code, Double>, Long> csvStockPrices = org.yccheok.jstock.portfolio.Utils.getCSVStockPrices();
            _portfolioRealTimeInfo.stockPrices.putAll(csvStockPrices.first);
            _portfolioRealTimeInfo.stockPricesTimestamp = csvStockPrices.second;
            _portfolioRealTimeInfo.stockPricesDirty = !_portfolioRealTimeInfo.stockPrices.isEmpty();
        }
        this.portfolioRealTimeInfo = _portfolioRealTimeInfo;
        
        if (openAsCSVFile(buyPortfolioFile) == false) {
            // If CSV file is not there, consider this as empty record. This is
            // because in createEmptyPortfolio, we only create portfolio-real-time-info.json,
            // for space and speed optimization purpose.            
            if (buyPortfolioFile.exists()) {
                // Either CSV format is corrupted.
                return false;
            }
        }
        
        if (openAsCSVFile(sellPortfolioFile) == false) {
            // If CSV file is not there, consider this as empty record. This is
            // because in createEmptyPortfolio, we only create portfolio-real-time-info.json,
            // for space and speed optimization purpose.            
            if (sellPortfolioFile.exists()) {
                // Either CSV format is corrupted.
                return false;
            }
        }
                
        if (openAsCSVFile(dividendSummaryFile) == false) {
            // If CSV file is not there, consider this as empty record. This is
            // because in createEmptyPortfolio, we only create portfolio-real-time-info.json,
            // for space and speed optimization purpose.            
            if (dividendSummaryFile.exists()) {
                // Either CSV format is corrupted.
                return false;
            }
        }        
        if (openAsCSVFile(depositSummaryFile) == false) {
            // If CSV file is not there, consider this as empty record. This is
            // because in createEmptyPortfolio, we only create portfolio-real-time-info.json,
            // for space and speed optimization purpose.            
            if (depositSummaryFile.exists()) {
                // Either CSV format is corrupted.
                return false;
            }
        }
        
        refershGUIAfterInitPortfolio(
            (BuyPortfolioTreeTableModelEx)PortfolioManagementJPanel.this.buyTreeTable.getTreeTableModel(), 
            (SellPortfolioTreeTableModelEx)PortfolioManagementJPanel.this.sellTreeTable.getTreeTableModel(), 
            this.dividendSummary,
            this.depositSummary);

        return true;
    }

    // Different among refreshGUIAfterOptionsJDialog and refreshGUIAfterFeeCalculationEnabledOptionsChanged
    // is that, refreshGUIAfterOptionsJDialog doesn't touch on column visibility.
    public void refreshGUIAfterOptionsJDialog() {
        if (SwingUtilities.isEventDispatchThread()) {
            _refreshGUIAfterOptionsJDialog();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    _refreshGUIAfterOptionsJDialog();
                }
            });            
        }        
    }
    
    private void _refreshGUIAfterOptionsJDialog() {
        this.buyTreeTable.repaint();
        this.sellTreeTable.repaint();
        this.updateWealthHeader();        
    }
    
    public void refreshGUIAfterFeeCalculationEnabledOptionsChanged() {
        if (SwingUtilities.isEventDispatchThread()) {
            _refreshGUIAfterFeeCalculationEnabledOptionsChanged();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    _refreshGUIAfterFeeCalculationEnabledOptionsChanged();
                }
            });            
        }        
    }
    
    public void _refreshGUIAfterFeeCalculationEnabledOptionsChanged() {
        this.buyTreeTable.repaint();
        this.sellTreeTable.repaint();
        this.updateWealthHeader();
        
        // Add/ remove columns based on user option.
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        JTable[] tables = {this.sellTreeTable, this.buyTreeTable};
        String[] names = { 
            GUIBundle.getString("PortfolioManagementJPanel_Broker"),
            GUIBundle.getString("PortfolioManagementJPanel_ClearingFee"),            
            GUIBundle.getString("PortfolioManagementJPanel_StampDuty")
        };
        
        for (JTable table : tables) {
            for (String name : names) {
                if (jStockOptions.isFeeCalculationEnabled()) {
                    final int columnCount = table.getColumnCount();
                    JTableUtilities.insertTableColumnFromModel(table, name, columnCount);                      
                } else {
                  JTableUtilities.removeTableColumn(table, name);
                }
            }
        }        
    }
    
    private void _refershGUIAfterInitPortfolio(
            final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel,
            final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel,
            final DividendSummary _dividendSummary,
            final DepositSummary _depositSummary          
            ) {
        // Without "if" checking, tree expand won't work. Weird!
        if (PortfolioManagementJPanel.this.buyTreeTable.getTreeTableModel() != buyPortfolioTreeTableModel) {
            PortfolioManagementJPanel.this.buyTreeTable.setTreeTableModel(buyPortfolioTreeTableModel);
        }
        
        // Without "if" checking, tree expand won't work. Weird!
        if (PortfolioManagementJPanel.this.sellTreeTable.getTreeTableModel() != sellPortfolioTreeTableModel) {
            PortfolioManagementJPanel.this.sellTreeTable.setTreeTableModel(sellPortfolioTreeTableModel);
        }
        PortfolioManagementJPanel.this.depositSummary = _depositSummary;
        PortfolioManagementJPanel.this.dividendSummary = _dividendSummary;

        PortfolioManagementJPanel.this.updateRealTimeStockMonitorAccordingToPortfolioTreeTableModels();
        PortfolioManagementJPanel.this.updateExchangeRateMonitorAccordingToPortfolioTreeTableModels();
        
        PortfolioManagementJPanel.this.updateWealthHeader();

        // Give user preferred GUI look. We do it here, because the entire table model is being changed.
        PortfolioManagementJPanel.this.initGUIOptions();

        PortfolioManagementJPanel.this.updateTitledBorder();

        // Every country is having different currency symbol. Remember to
        // refresh the currency symbol after we change the country.
        PortfolioManagementJPanel.this.refreshCurrencySymbol();
        
        expandTreeTable(this.buyTreeTable);
        expandTreeTable(this.sellTreeTable);
    }
    
    private void expandTreeTable(JXTreeTable treeTable) {
        // Due to bug in JXTreeTable, expandRow sometimes just won't work. Here
        // is the hacking which makes it works.
        if (treeTable.isExpanded(0)) {
            treeTable.collapseRow(0);
        }
                
        // Expand the trees.
        treeTable.expandRow(0);      
    }
    
    private void refershGUIAfterInitPortfolio(
            final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel,
            final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel,
            final DividendSummary _dividendSummary,
            final DepositSummary _depositSummary) {
        if (SwingUtilities.isEventDispatchThread()) {
            _refershGUIAfterInitPortfolio(buyPortfolioTreeTableModel, sellPortfolioTreeTableModel, _dividendSummary, _depositSummary);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    _refershGUIAfterInitPortfolio(buyPortfolioTreeTableModel, sellPortfolioTreeTableModel, _dividendSummary, _depositSummary);
                }
            });            
        }
    }
    
    /*
    public final void initPortfolio() {
        this.initCSVPortfolio();
    }
    */

    public void updateTitledBorder() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        if (SwingUtilities.isEventDispatchThread()) {
            final TitledBorder titledBorder = (TitledBorder)PortfolioManagementJPanel.this.jPanel1.getBorder();
            titledBorder.setTitle(jStockOptions.getPortfolioName());
            // So that title will refresh immediately.
            PortfolioManagementJPanel.this.jPanel1.repaint();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    final TitledBorder titledBorder = (TitledBorder)PortfolioManagementJPanel.this.jPanel1.getBorder();
                    titledBorder.setTitle(jStockOptions.getPortfolioName());
                    // So that title will refresh immediately.
                    PortfolioManagementJPanel.this.jPanel1.repaint();
                }
            });
        }
    }

    private boolean saveCSVPortfolio(String directory, PortfolioRealTimeInfo portfolioRealTimeInfo) {
        if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(directory) == false)
        {
            return false;
        }
        
        assert(directory.endsWith(File.separator));
        
        final File buyPortfolioFile = new File(directory + "buyportfolio.csv");
        final File sellPortfolioFile = new File(directory + "sellportfolio.csv");
        final File dividendSummaryFile = new File(directory + "dividendsummary.csv");
        final File depositSummaryFile = new File(directory + "depositsummary.csv");

        final FileEx buyPortfolioFileEx = new FileEx(buyPortfolioFile, org.yccheok.jstock.file.Statement.Type.PortfolioManagementBuy);
        final FileEx sellPortfolioFileEx = new FileEx(sellPortfolioFile, org.yccheok.jstock.file.Statement.Type.PortfolioManagementSell);
        final FileEx dividendSummaryFileEx = new FileEx(dividendSummaryFile, org.yccheok.jstock.file.Statement.Type.PortfolioManagementDividend);
        final FileEx depositSummaryFileEx = new FileEx(depositSummaryFile, org.yccheok.jstock.file.Statement.Type.PortfolioManagementDeposit);

        if (false == saveAsCSVFile(buyPortfolioFileEx, true)) {
            final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)this.buyTreeTable.getTreeTableModel();
            // org.yccheok.jstock.file.Statements is not good in handling empty 
            // case. Let us handle it seperately.
            int count = buyPortfolioTreeTableModel.getRoot().getChildCount();
            if (count > 0) {
                buyPortfolioFileEx.file.delete();
                // Is not empty, but we fail to save it for unknown reason.
                return false;
            }
        }
        
        if (false == saveAsCSVFile(sellPortfolioFileEx, true)) {            
            final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)this.sellTreeTable.getTreeTableModel();
            
            // org.yccheok.jstock.file.Statements is not good in handling empty 
            // case. Let us handle it seperately.
            int count = sellPortfolioTreeTableModel.getRoot().getChildCount();
            if (count > 0) {
                sellPortfolioFileEx.file.delete();
                buyPortfolioFileEx.file.delete();
                // Is not empty, but we fail to save it for unknown reason.
                return false;
            }
        }

        if (false == saveAsCSVFile(dividendSummaryFileEx, true)) {            
            // org.yccheok.jstock.file.Statements is not good in handling empty 
            // case. Let us handle it seperately.
            int count = this.dividendSummary.size();
            if (count > 0) {
                dividendSummaryFileEx.file.delete();
                depositSummaryFileEx.file.delete();
                sellPortfolioFileEx.file.delete();
                buyPortfolioFileEx.file.delete();
                
                // Is not empty, but we fail to save it for unknown reason.
                return false;
            }
        }

        if (false == saveAsCSVFile(depositSummaryFileEx, true)) {
            // org.yccheok.jstock.file.Statements is not good in handling empty 
            // case. Let us handle it seperately.
            int count = this.depositSummary.size();
            if (count > 0) {
                depositSummaryFileEx.file.delete();
                sellPortfolioFileEx.file.delete();
                buyPortfolioFileEx.file.delete();                
                // Is not empty, but we fail to save it for unknown reason.
                return false;
            }
        }
        
        boolean status = savePortfolioRealTimeInfo(directory, portfolioRealTimeInfo);
        
        if (status) {
            // Legacy file.
            final File stockPricesFile = new File(org.yccheok.jstock.portfolio.Utils.getStockPricesFilepathViaDirectory(directory));
            stockPricesFile.delete();
        }
        
        return status;
    }
    
    private boolean saveCSVPortfolio() {
        return saveCSVPortfolio(org.yccheok.jstock.portfolio.Utils.getPortfolioDirectory(),
            this.portfolioRealTimeInfo
        );
    }
        
    private boolean savePortfolioRealTimeInfo(String directory, PortfolioRealTimeInfo portfolioRealTimeInfo) {
        PortfolioRealTimeInfo _portfolioRealTimeInfo = new PortfolioRealTimeInfo(portfolioRealTimeInfo);

        Map<Code, Double> goodStockPrices = new HashMap<>();
        Map<Code, Double> goodChangePrices = new HashMap<>();
        Map<Code, Double> goodChangePricePercentages = new HashMap<>();
        Map<Code, Currency> goodCurrencies = new HashMap<>();
        Map<CurrencyPair, Double> goodExchangeRates = new HashMap<>();

        ////////////////////////////////////////////////////////////////////////
        // goodStockPrices
        ////////////////////////////////////////////////////////////////////////
        for (Code code : getBuyCodes()) {
            final Double price = _portfolioRealTimeInfo.stockPrices.get(code);
            final Double changePrice = _portfolioRealTimeInfo.changePrices.get(code);
            final Double changePricePercentage = _portfolioRealTimeInfo.changePricePercentages.get(code);

            if (price == null) {
                // Having 0 is important too, so that we can generate correct
                // portfolioinfos.csv
                goodStockPrices.put(code, 0.0);
            } else {
                goodStockPrices.put(code, price);
            }

            if (changePrice == null) {
                // Having 0 is not really that important. We just mimic what is being done on goodStockPrices.
                goodChangePrices.put(code, 0.0);
            } else {
                goodChangePrices.put(code, changePrice);
            }

            if (changePricePercentage == null) {
                // Having 0 is not really that important. We just mimic what is being done on goodStockPrices.
                goodChangePricePercentages.put(code, 0.0);
            } else {
                goodChangePricePercentages.put(code, changePricePercentage);
            }
        }

        ////////////////////////////////////////////////////////////////////////
        // goodCurrencies. Don't use getBuyCodes, as we need to perform currency
        // conversion on sell stocks too.
        ////////////////////////////////////////////////////////////////////////
        for (Code code : getCodes()) {
            final Currency currency = _portfolioRealTimeInfo.currencies.get(code);
            if (currency != null) {
                goodCurrencies.put(code, currency);
            }
        }

        ////////////////////////////////////////////////////////////////////////
        // goodExchangeRates
        ////////////////////////////////////////////////////////////////////////
        for (CurrencyPair currencyPair : getCurrencyPairs()) {
            final Double rate = _portfolioRealTimeInfo.exchangeRates.get(currencyPair);
            if (rate != null) {
                goodExchangeRates.put(currencyPair, rate);
            }
        }

        _portfolioRealTimeInfo.stockPrices.clear();
        _portfolioRealTimeInfo.currencies.clear();
        _portfolioRealTimeInfo.exchangeRates.clear();
        _portfolioRealTimeInfo.changePrices.clear();
        _portfolioRealTimeInfo.changePricePercentages.clear();

        _portfolioRealTimeInfo.stockPrices.putAll(goodStockPrices);
        _portfolioRealTimeInfo.currencies.putAll(goodCurrencies);
        _portfolioRealTimeInfo.exchangeRates.putAll(goodExchangeRates);
        _portfolioRealTimeInfo.changePrices.putAll(goodChangePrices);
        _portfolioRealTimeInfo.changePricePercentages.putAll(goodChangePricePercentages);

        final File stockPricesFile = new File(directory + "portfolio-real-time-info.json");
        return _portfolioRealTimeInfo.save(stockPricesFile);
    }

    public boolean savePortfolio() {
        return saveCSVPortfolio();
    }

    private void refreshStatusBarExchangeRateVisibility() {
        final JStock mainFrame = JStock.instance();
        final JStockOptions jStockOptions = mainFrame.getJStockOptions();
        final Country country = jStockOptions.getCountry();
        
        final boolean currencyExchangeEnable = jStockOptions.isCurrencyExchangeEnable(country);
        
        if (!currencyExchangeEnable) {
            mainFrame.setStatusBarExchangeRateVisible(false);
            mainFrame.setStatusBarExchangeRateToolTipText(null);
            return;
        }

        Set<CurrencyPair> currencyPairs = this.getCurrencyPairs();
        // Special handling for GBX.
        currencyPairs.remove(CurrencyPair.create(Currency.GBX, Currency.GBP));
        // Special handling for ZAC.
        currencyPairs.remove(CurrencyPair.create(Currency.ZAC, Currency.ZAR));
        
        if (currencyPairs.isEmpty()) {
            mainFrame.setStatusBarExchangeRateVisible(false);
            mainFrame.setStatusBarExchangeRateToolTipText(null);
            return;
        }
        
        final int size = currencyPairs.size();
        
        if (size > 1) {        
            mainFrame.setStatusBarExchangeRateVisible(false);
            mainFrame.setStatusBarExchangeRateToolTipText(null);
            return;
        }
        
        // We will display the currency exchange rate, only if there is 1 
        // currency pair.
        if (size == 1) {
            // Update the tool tip text.
            CurrencyPair currencyPair = currencyPairs.iterator().next();
            Currency fromCurrency = currencyPair.from();
            Currency toCurrency = currencyPair.to();
            final String text = MessageFormat.format(GUIBundle.getString("MyJXStatusBar_CurrencyExchangeRateFor"), fromCurrency.toString(), toCurrency.toString());
            mainFrame.setStatusBarExchangeRateVisible(true);
            mainFrame.setStatusBarExchangeRateToolTipText(text);
            Double rate = this.portfolioRealTimeInfo.exchangeRates.get(currencyPair);
            
            if (rate != null) {
                // Special handling for GBX/ZAC. User would prefer to see the 
                // currency exchange in GBP/ZAR.
                if (currencyPair.from().isGBX() || currencyPair.from().isZAC()) {
                    rate = rate * 100.0;
                }
            
                if (rate == 1.0) {
                    // User are not interested in such exchange rate.
                    mainFrame.setStatusBarExchangeRate(null);    
                } else {
                    mainFrame.setStatusBarExchangeRate(rate);
                }
            } else {
                mainFrame.setStatusBarExchangeRate(null);
            }
            return;
        }
    }
    
    /**
     * Initializes exchange rate monitor.
     */
    public void initExchangeRateMonitor() {
        final JStock jstock = JStock.instance();
        final JStockOptions jStockOptions = jstock.getJStockOptions();

        final Country country = jStockOptions.getCountry();

        final boolean currencyExchangeEnable = jStockOptions.isCurrencyExchangeEnable(country);

        refreshStatusBarExchangeRateVisibility();
        
        final ExchangeRateMonitor oldExchangeRateMonitor = this.exchangeRateMonitor;
        if (oldExchangeRateMonitor != null) {            
            Utils.getZoombiePool().execute(new Runnable() {
                @Override
                public void run() {
                    log.info("Prepare to shut down " + oldExchangeRateMonitor + "...");
                    oldExchangeRateMonitor.dettachAll();
                    oldExchangeRateMonitor.stop();
                    log.info("Shut down " + oldExchangeRateMonitor + " peacefully.");
                }
            });
        }
        

        if (!currencyExchangeEnable) {
            this.exchangeRateMonitor = null;
            if (oldExchangeRateMonitor != null) { 
                this.updateWealthHeader();
            }
            return;
        }
        
        assert(currencyExchangeEnable);
        
        this.exchangeRateMonitor = new ExchangeRateMonitor(
            Constants.EXCHANGE_RATE_MONITOR_MAX_THREAD, 
            Constants.EXCHANGE_RATE_MONITOR_MAX_STOCK_SIZE_PER_SCAN,
            jStockOptions.getScanningSpeed());

        this.exchangeRateMonitor.attach(exchangeRateMonitorObserver);
        
        updateExchangeRateMonitorAccordingToPortfolioTreeTableModels();
    }

    public void initRealTimeStockMonitor() {
        final RealTimeStockMonitor oldRealTimeStockMonitor = realTimeStockMonitor;
        if (oldRealTimeStockMonitor != null) {            
            Utils.getZoombiePool().execute(new Runnable() {
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
                JStock.instance().getJStockOptions().getScanningSpeed());
        
        realTimeStockMonitor.attach(this.realTimeStockMonitorObserver);
        
        updateRealTimeStockMonitorAccordingToPortfolioTreeTableModels();
    }

    private org.yccheok.jstock.engine.Observer<ExchangeRateMonitor, List<ExchangeRate>> getExchangeRateMonitorObserver() {
        return new org.yccheok.jstock.engine.Observer<ExchangeRateMonitor, List<ExchangeRate>>() {
            @Override
            public void update(ExchangeRateMonitor subject, java.util.List<ExchangeRate> exchangeRates) {
                final ExchangeRateMonitor _exchangeRateMonitor = exchangeRateMonitor;
                
                if (_exchangeRateMonitor != null) {
                    final Map<CurrencyPair, Double> exchangeRatesMap = portfolioRealTimeInfo.exchangeRates;
                    
                    Set<CurrencyPair> currencyPairs = getCurrencyPairs();
                    for (ExchangeRate exchangeRate : exchangeRates) {
                        final CurrencyPair currencyPair = exchangeRate.currencyPair();
                        if (false == currencyPairs.contains(currencyPair)) {
                            // Thread safety purpose.
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (false == getCurrencyPairs().contains(currencyPair)) {
                                        _exchangeRateMonitor.removeCurrencyPair(currencyPair);
                                        exchangeRatesMap.remove(currencyPair);                                        
                                    }
                                }
                            });
                        } else {
                            double rate = exchangeRate.rate();
                            exchangeRatesMap.put(currencyPair, rate);
                        }
                        portfolioRealTimeInfo.exchangeRatesDirty = true;
                    }
                    
                    portfolioRealTimeInfo.exchangeRatesTimestamp = System.currentTimeMillis();
                }
                
                refreshStatusBarExchangeRateVisibility();
                
                updateWealthHeader();
                
                final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
                final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
                buyPortfolioTreeTableModel.refreshRoot();
                sellPortfolioTreeTableModel.refreshRoot();
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
                PortfolioManagementJPanel.this.update(monitor, result);
            }
        };
    }
    
    private double getNonZeroPriceIfPossible(Stock stock) {
        // First.
        final double lastPrice = stock.getLastPrice();
        if (lastPrice > 0.0) {
            return lastPrice;
        }
        
        // Second.
        final double openPrice = stock.getOpenPrice();
        if (openPrice > 0.0) {
            return openPrice;
        }
        
        // Third.
        final double prevPrice = stock.getPrevPrice();
        return Math.max(0.0, prevPrice);
    }
    
    private void update(RealTimeStockMonitor monitor, final RealTimeStockMonitor.Result result) {
        final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)buyTreeTable.getTreeTableModel();
        final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)sellTreeTable.getTreeTableModel();
        
        final Map<Code, Double> stockPrices = this.portfolioRealTimeInfo.stockPrices;
        final Map<Code, Currency> currencies = this.portfolioRealTimeInfo.currencies;
        final Map<Code, Double> changePrices = portfolioRealTimeInfo.changePrices;
        final Map<Code, Double> changePricePercentages = portfolioRealTimeInfo.changePricePercentages;

        final Set<Code> buyCodes = this.getBuyCodes();
        final Set<Code> sellCodes = this.getSellCodes();
        
        for (Stock stock : result.stocks) {            
            final Code code = stock.code;
            final Currency currency = stock.getCurrency();
            
            boolean needBuyRefresh = false;
            boolean needSellRefresh = false;
            
            if (buyCodes.contains(code)) {
                final Double price = getNonZeroPriceIfPossible(stock);
                final Double oldPrice = stockPrices.put(code, price);
                if (false == price.equals(oldPrice)) {
                    this.portfolioRealTimeInfo.stockPricesDirty = true;
                    needBuyRefresh = true;
                }
                
                // ConcurrentHashMap doesn't support null value.
                // http://stackoverflow.com/questions/698638/why-does-concurrenthashmap-prevent-null-keys-and-values
                if (currency != null) {
                    final Currency oldCurrency = currencies.put(code, currency);
                    if (false == currency.equals(oldCurrency)) {
                        this.portfolioRealTimeInfo.currenciesDirty = true;
                        needBuyRefresh = true;
                        // Should sell portfolio refresh too, since we have new currency info?
                        if (sellCodes.contains(code)) {
                            needSellRefresh = true;    
                        }
                    }
                }
                
                final double changePrice = stock.getChangePrice();
                final double changePricePercentage = stock.getChangePricePercentage();

                Double oldChangePrice = changePrices.get(code);
                changePrices.put(code, changePrice);
                if (oldChangePrice == null || oldChangePrice != changePrice) {
                    portfolioRealTimeInfo.changePricesDirty = true;
                }

                Double oldChangePricePercentage = changePricePercentages.get(code);
                changePricePercentages.put(code, changePricePercentage);
                if (oldChangePricePercentage == null || oldChangePricePercentage != changePricePercentage) {
                    portfolioRealTimeInfo.changePricePercentagesDirty = true;
                }
            } else if (sellCodes.contains(code)) {
                if (currency != null) {
                    final Currency oldCurrency = currencies.put(code, currency);
                    if (false == currency.equals(oldCurrency)) {
                        this.portfolioRealTimeInfo.currenciesDirty = true;
                        needSellRefresh = true;
                    }

                    // Thread safety purpose.
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Set<Code> buyCodes = PortfolioManagementJPanel.this.getBuyCodes();
                            if (false == buyCodes.contains(code)) {
                                // We can remove this code safely.
                                PortfolioManagementJPanel.this.realTimeStockMonitor.removeStockCode(code);
                            }
                        }
                    });
                } else {
                    // Should I do something here? Or, should I keep retrying
                    // without removing code from 
                }

            } else {
                // Thread safety purpose.
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Set<Code> codes = PortfolioManagementJPanel.this.getCodes();
                        if (false == codes.contains(code)) {
                            // We can remove this code safely.
                            PortfolioManagementJPanel.this.realTimeStockMonitor.removeStockCode(code);
                        }
                    }
                });

                continue;
            }
            
            if (needBuyRefresh) {
                // Because we have new price & new currency.
                buyPortfolioTreeTableModel.refresh(code);
            }
            
            if (needSellRefresh) {
                // Because we have new currency.
                sellPortfolioTreeTableModel.refresh(code);
            }
            
            // Update currency monitor as well.
            ExchangeRateMonitor _exchangeRateMonitor = this.exchangeRateMonitor;
            if (currency != null && _exchangeRateMonitor != null) {
                final Currency localCurrency = org.yccheok.jstock.portfolio.Utils.getLocalCurrency();
                if (localCurrency != null) {
                    if (false == currency.equals(localCurrency)) {
                        CurrencyPair currencyPair = CurrencyPair.create(currency, localCurrency);
                        if (_exchangeRateMonitor.addCurrencyPair(currencyPair)) {
                            _exchangeRateMonitor.startNewThreadsIfNecessary();
                            _exchangeRateMonitor.refresh();
                        }
                    }                    
                }
            }
        }   // for
        
        updateWealthHeader();
        
        // Update status bar with current time string.
        this.portfolioRealTimeInfo.stockPricesTimestamp = System.currentTimeMillis();
        
        JStock.instance().updateStatusBarWithLastUpdateDateMessageIfPossible();     
    }  

    public long getTimestamp() {
        return this.portfolioRealTimeInfo.stockPricesTimestamp;
    }
    
    private void initGUIOptions() {
        File f = new File(UserDataDirectory.Config.get() + UserDataFile.PortfolioManagementJPanelXml.get());
        final GUIOptions guiOptions = Utils.fromXML(GUIOptions.class, f);

        if (guiOptions == null)
        {
            return;
        }

        if (guiOptions.getJTableOptionsSize() <= 1)
        {
            return;
        }

        final org.jdesktop.swingx.JXTreeTable[] treeTables = {buyTreeTable, sellTreeTable};

        /* Set Table Settings */
        for (int tableIndex = 0; tableIndex < treeTables.length; tableIndex++) {
            final JXTreeTable treeTable = treeTables[tableIndex];
            final javax.swing.table.JTableHeader jTableHeader = treeTable.getTableHeader();
            final JTable jTable = jTableHeader.getTable();
            JTableUtilities.setJTableOptions(jTable, guiOptions.getJTableOptions(tableIndex));
        }

        // Do we have the divider location option?
        if (guiOptions.getDividerLocationSize() > 0) {
            // Yes. Remember the divider location.
            // It will be used in updateDividerLocation later.
            this.dividerLocation = guiOptions.getDividerLocation(0);
        }
    }

    // Also, be aware: Calling setDividerLocation(double) before the JSplitPane
    // is visible will not work correctly.
    // Workaround for bug : http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6528446
    public void updateDividerLocation() {
        if (this.dividerLocation > 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Have the code in AWT event dispatching thread, in order
                    // to ensure MainFrame is already shown on the screen.
                    jSplitPane1.setDividerLocation(dividerLocation);
                }
            });
        }
    }

    public boolean saveGUIOptions() {
        if (Utils.createCompleteDirectoryHierarchyIfDoesNotExist(UserDataDirectory.Config.get()) == false)
        {
            return false;
        }

        final GUIOptions guiOptions = new GUIOptions();

        final org.jdesktop.swingx.JXTreeTable[] treeTables = {buyTreeTable, sellTreeTable};

        for (org.jdesktop.swingx.JXTreeTable treeTable : treeTables)
        {
            final javax.swing.table.JTableHeader jTableHeader = treeTable.getTableHeader();
            final JTable jTable = jTableHeader.getTable();
            final GUIOptions.JTableOptions jTableOptions = new GUIOptions.JTableOptions();
            
            final int count = jTable.getColumnCount();
            for (int i = 0; i < count; i++) {
                final String name = jTable.getColumnName(i);
                final TableColumn column = jTable.getColumnModel().getColumn(i);
                jTableOptions.addColumnOption(GUIOptions.JTableOptions.ColumnOption.newInstance(name, column.getWidth()));
            }

            guiOptions.addJTableOptions(jTableOptions);
        }

        guiOptions.addDividerLocation(jSplitPane1.getDividerLocation());
        
        File f = new File(UserDataDirectory.Config.get() + UserDataFile.PortfolioManagementJPanelXml.get());
        return org.yccheok.jstock.gui.Utils.toXML(guiOptions, f);
    }

    /*
    public boolean saveAsExcelFile(File file, boolean languageIndependent) {
        org.yccheok.jstock.file.Statements.StatementsEx statementsEx0, statementsEx1, statementsEx2, statementsEx3;
        Statements statements0 = org.yccheok.jstock.file.Statements.newInstanceFromBuyPortfolioTreeTableModel((BuyPortfolioTreeTableModelEx)this.buyTreeTable.getTreeTableModel(), this.portfolioRealTimeInfo, languageIndependent);
        Statements statements1 = org.yccheok.jstock.file.Statements.newInstanceFromSellPortfolioTreeTableModel((SellPortfolioTreeTableModelEx)this.sellTreeTable.getTreeTableModel(), this.portfolioRealTimeInfo, languageIndependent);
        Statements statements2 = org.yccheok.jstock.file.Statements.newInstanceFromTableModel(new DividendSummaryTableModel(this.dividendSummary), languageIndependent);
        Statements statements3 = org.yccheok.jstock.file.Statements.newInstanceFromTableModel(new DepositSummaryTableModel(this.depositSummary), languageIndependent);
        
        statementsEx0 = new org.yccheok.jstock.file.Statements.StatementsEx(statements0, GUIBundle.getString("PortfolioManagementJPanel_BuyPortfolio"));
        statementsEx1 = new org.yccheok.jstock.file.Statements.StatementsEx(statements1, GUIBundle.getString("PortfolioManagementJPanel_SellPortfolio"));
        statementsEx2 = new org.yccheok.jstock.file.Statements.StatementsEx(statements2, GUIBundle.getString("PortfolioManagementJPanel_DividendPortfolio"));
        statementsEx3 = new org.yccheok.jstock.file.Statements.StatementsEx(statements3, GUIBundle.getString("PortfolioManagementJPanel_CashDepositPortfolio"));
        List<org.yccheok.jstock.file.Statements.StatementsEx> statementsExs = Arrays.asList(statementsEx0, statementsEx1, statementsEx2, statementsEx3);
        return Statements.saveAsExcelFile(file, statementsExs);
    }
    */

    public boolean saveAsCSVFile(Utils.FileEx fileEx, boolean languageIndependent) {
        org.yccheok.jstock.file.Statements statements = null;
        if (fileEx.type == org.yccheok.jstock.file.Statement.Type.PortfolioManagementBuy) {
            // For buy portfolio, need not save metadata information, as we have
            // seperate "stockprices.csv" to handle it. However, I am not really
            // sure that whether seperating them is a good idea.
            statements = org.yccheok.jstock.file.Statements.newInstanceFromBuyPortfolioTreeTableModel((BuyPortfolioTreeTableModelEx)this.buyTreeTable.getTreeTableModel(), this.portfolioRealTimeInfo, languageIndependent);
        }
        else if (fileEx.type == org.yccheok.jstock.file.Statement.Type.PortfolioManagementSell) {
            statements = org.yccheok.jstock.file.Statements.newInstanceFromSellPortfolioTreeTableModel((SellPortfolioTreeTableModelEx)this.sellTreeTable.getTreeTableModel(), this.portfolioRealTimeInfo, languageIndependent);
        }
        else if (fileEx.type == org.yccheok.jstock.file.Statement.Type.PortfolioManagementDividend) {
            statements = org.yccheok.jstock.file.Statements.newInstanceFromTableModel(new DividendSummaryTableModel(this.dividendSummary), languageIndependent);
        }
        else if (fileEx.type == org.yccheok.jstock.file.Statement.Type.PortfolioManagementDeposit) {
            statements = org.yccheok.jstock.file.Statements.newInstanceFromTableModel(new DepositSummaryTableModel(this.depositSummary), languageIndependent);
        }
        // Use metadata to store TransactionSummary's comment.
        return statements.saveAsCSVFile(fileEx.file);
    }
    
    private void updateWealthHeader() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        final boolean isFeeCalculationEnabled = jStockOptions.isFeeCalculationEnabled();
        
        final BuyPortfolioTreeTableModelEx buyPortfolioTreeTableModel = (BuyPortfolioTreeTableModelEx)this.buyTreeTable.getTreeTableModel();
        final SellPortfolioTreeTableModelEx sellPortfolioTreeTableModel = (SellPortfolioTreeTableModelEx)this.sellTreeTable.getTreeTableModel();
      
        final Currency localCurrency = org.yccheok.jstock.portfolio.Utils.getLocalCurrency();
        
        final double share;
        final double cash;
        final double paperProfit;
        final double realizedProfit;

        share = buyPortfolioTreeTableModel.getCurrentValue(localCurrency);

        final Country country = jStockOptions.getCountry();

        if (isFeeCalculationEnabled) {
            final double exchangeRate = org.yccheok.jstock.portfolio.Utils.getExchangeRate(portfolioRealTimeInfo, localCurrency, country.stockCurrency);
            
            double _cash =  
                sellPortfolioTreeTableModel.getNetSellingValue(localCurrency) - 
                ((Portfolio)sellPortfolioTreeTableModel.getRoot()).getNetReferenceTotal(localCurrency) - 
                buyPortfolioTreeTableModel.getNetPurchaseValue(localCurrency);
            
            double deposit = this.getDepositSummary().getTotal() * exchangeRate;
            if (country.stockCurrency.isGBX() || country.stockCurrency.isZAC()) {
                // Use will input cash in GBP/ZAR instead of GBX/ZAC.
                deposit = deposit * 100.0;
            }
            
            double dividend = this.getDividendSummary().getTotal(this.portfolioRealTimeInfo, localCurrency);
            
            _cash += deposit;
            _cash += dividend;
            cash = _cash;
            
            paperProfit = buyPortfolioTreeTableModel.getNetGainLossValue(localCurrency);
            realizedProfit = sellPortfolioTreeTableModel.getNetGainLossValue(localCurrency);
        } else {
            final double exchangeRate = org.yccheok.jstock.portfolio.Utils.getExchangeRate(portfolioRealTimeInfo, localCurrency, country.stockCurrency);
            
            double _cash = 
                sellPortfolioTreeTableModel.getSellingValue(localCurrency) - 
                ((Portfolio)sellPortfolioTreeTableModel.getRoot()).getReferenceTotal(localCurrency) - 
                buyPortfolioTreeTableModel.getPurchaseValue(localCurrency);
            
            double deposit = this.getDepositSummary().getTotal() * exchangeRate;
            if (country.stockCurrency.isGBX() || country.stockCurrency.isZAC()) {
                // Use will input cash in GBP/ZAR instead of GBX/ZAC.
                deposit = deposit * 100.0;
            }
            
            double dividend = this.getDividendSummary().getTotal(this.portfolioRealTimeInfo, localCurrency);
            
            _cash += deposit;
            _cash += dividend;
            cash = _cash;
            
            paperProfit = buyPortfolioTreeTableModel.getGainLossValue(localCurrency);
            realizedProfit = sellPortfolioTreeTableModel.getGainLossValue(localCurrency);
        }

        final double paperProfitPercentage;
        final double realizedProfitPercentage;

        if (isFeeCalculationEnabled) {
            paperProfitPercentage = buyPortfolioTreeTableModel.getNetGainLossPercentage(localCurrency);
            realizedProfitPercentage = sellPortfolioTreeTableModel.getNetGainLossPercentage(localCurrency);
        } else {
            paperProfitPercentage = buyPortfolioTreeTableModel.getGainLossPercentage(localCurrency);
            realizedProfitPercentage = sellPortfolioTreeTableModel.getGainLossPercentage(localCurrency);
        }
        
        final DecimalPlace decimalPlace = jStockOptions.getDecimalPlace();
        
        final String _share = org.yccheok.jstock.portfolio.Utils.toCurrency(decimalPlace, share);
        final String _cash = org.yccheok.jstock.portfolio.Utils.toCurrency(decimalPlace, cash);
        final String _paperProfit = org.yccheok.jstock.portfolio.Utils.toCurrency(decimalPlace, paperProfit);
        final String _paperProfitPercentage = org.yccheok.jstock.portfolio.Utils.toCurrency(DecimalPlace.Two, paperProfitPercentage);
        final String _realizedProfit = org.yccheok.jstock.portfolio.Utils.toCurrency(decimalPlace, realizedProfit);
        final String _realizedProfitPercentage = org.yccheok.jstock.portfolio.Utils.toCurrency(DecimalPlace.Two, realizedProfitPercentage);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jLabel2.setText(_share);
                jLabel4.setText(_cash);
                jLabel6.setText(_paperProfit + " (" + _paperProfitPercentage + "%)");
                jLabel8.setText(_realizedProfit + " (" + _realizedProfitPercentage + "%)");
                jLabel2.setForeground(Utils.getColor(share, 0.0));
                jLabel4.setForeground(Utils.getColor(cash, 0.0));
                jLabel6.setForeground(Utils.getColor(paperProfit, 0.0));
                jLabel8.setForeground(Utils.getColor(realizedProfit, 0.0));
           }
        });
    }
    
    public void resumeRealTimeStockMonitor() {
        if (realTimeStockMonitor == null) {
            return;
        }    
        realTimeStockMonitor.resume();
    }
    
    public void suspendRealTimeStockMonitor() {
        if (realTimeStockMonitor == null) {
            return;
        }        
        realTimeStockMonitor.suspend();
    }

    /**
     * Refresh all the labels with latest currency symbol.
     */
    public void refreshCurrencySymbol() {
        jLabel1.setText(getShareLabel());
        jLabel3.setText(getCashLabel());
        jLabel5.setText(getPaperProfitLabel());
        jLabel7.setText(getRealizedProfitLabel());
    }

    private String getShareLabel() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        return MessageFormat.format(
            GUIBundle.getString("PortfolioManagementJPanel_ShareLabel"),
            jStockOptions.getCurrencySymbol(jStockOptions.getCountry())
        );
    }
    
    private String getCashLabel() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        return MessageFormat.format(
            GUIBundle.getString("PortfolioManagementJPanel_CashLabel_template"),
            jStockOptions.getCurrencySymbol(jStockOptions.getCountry())
        );
    }
    
    private String getPaperProfitLabel() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        return MessageFormat.format(
            GUIBundle.getString("PortfolioManagementJPanel_PaperProfitLabel_template"),
            jStockOptions.getCurrencySymbol(jStockOptions.getCountry())
        );
    }
    
    private String getRealizedProfitLabel() {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        return MessageFormat.format(
            GUIBundle.getString("PortfolioManagementJPanel_RealizedProfitLabel_template"),
            jStockOptions.getCurrencySymbol(jStockOptions.getCountry())
        );
    }

    public void refreshExchangeRateMonitor() {
        ExchangeRateMonitor _exchangeRateMonitor = this.exchangeRateMonitor;
        if (_exchangeRateMonitor != null) {
            _exchangeRateMonitor.refresh();
        }
    }
    
    public void refreshRealTimeStockMonitor() {
        RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
        if (_realTimeStockMonitor != null) {
            _realTimeStockMonitor.refresh();
        }
    }
    
    public void rebuildRealTimeStockMonitor() {
        RealTimeStockMonitor _realTimeStockMonitor = this.realTimeStockMonitor;
        if (_realTimeStockMonitor != null) {
            _realTimeStockMonitor.rebuild();
        }
    }
    
    public PortfolioRealTimeInfo getPortfolioRealTimeInfo() {
        return this.portfolioRealTimeInfo;
    }
    
    // We will display currency info if currency exchange feature is enabled,
    // and the stock currency is different from country stock currency.
    public boolean shouldDisplayCurrencyForValue(Code code) {
        final JStockOptions jStockOptions = JStock.instance().getJStockOptions();
        final Country country = jStockOptions.getCountry();
        final boolean isCurrencyExchangeEnable = jStockOptions.isCurrencyExchangeEnable(country);
        if (false == isCurrencyExchangeEnable) {
            return false;
        }
        
        final Currency stockCurrency = org.yccheok.jstock.portfolio.Utils.getStockCurrency(portfolioRealTimeInfo, code);
        final Currency countryStockCurrency = country.stockCurrency;
        
        return (false == stockCurrency.equals(countryStockCurrency));
    }
    
    private static final Log log = LogFactory.getLog(PortfolioManagementJPanel.class);

    private int dividerLocation = -1;

    // Data structure.
    private DepositSummary depositSummary = new DepositSummary();
    private DividendSummary dividendSummary = new DividendSummary();

    private RealTimeStockMonitor realTimeStockMonitor = null;
    
    private ExchangeRateMonitor exchangeRateMonitor = null;
    
    private final org.yccheok.jstock.engine.Observer<RealTimeStockMonitor, RealTimeStockMonitor.Result> realTimeStockMonitorObserver = this.getRealTimeStockMonitorObserver();
    private final org.yccheok.jstock.engine.Observer<ExchangeRateMonitor, List<ExchangeRate>> exchangeRateMonitorObserver = this.getExchangeRateMonitorObserver();

    private PortfolioRealTimeInfo portfolioRealTimeInfo = new PortfolioRealTimeInfo();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.yccheok.jstock.gui.treetable.SortableTreeTable buyTreeTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private org.yccheok.jstock.gui.treetable.SortableTreeTable sellTreeTable;
    // End of variables declaration//GEN-END:variables
}
