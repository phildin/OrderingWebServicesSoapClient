/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.testorderingsoapclient.ui.orderform;

import ie.philb.testorderingsoapclient.Application;
import ie.philb.testorderingsoapclient.ApplicationListener;
import ie.philb.testorderingsoapclient.OrderFacade;
import ie.philb.testorderingsoapclient.commands.SaveOrderCommand;
import ie.philb.testorderingsoapclient.ui.nav.orders.OrderCategory;
import ie.philb.testorderingsoapclient.util.TableUtils;
import ie.philb.testorderingsoapclient.ws.Order;
import ie.philb.testorderingsoapclient.ws.Product;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

/**
 *
 * @author philb
 */
public class OrderFormBody extends javax.swing.JPanel implements ApplicationListener {

    public OrderFormBody() {
        initComponents();
        Application.getApplication().addListener(this);

        jTable1.setModel(new OrderTableModel());
        TableUtils.resizeColumns(jTable1, 50, 50, 250);

        setTransferHandler(new TransferHandler() {

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {

                OrderFacade of = Application.getApplication().getOrderFacade();

                if (of.getId() == 0) {
                    return false;
                }

                if (!support.isDrop()) {
                    return false;
                }

                // we only import Strings
                if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
                }

                return true;
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {

                OrderFacade of = Application.getApplication().getOrderFacade();

                if (of.getId() == 0) {
                    return false;
                }

                if (!canImport(support)) {
                    return false;
                }

                try {
                    String skuCode = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);

                    List<Product> products = Application.getApplication().getProducts();
                    Product droppedProduct = null;

                    for (Product product : products) {
                        if (product.getSkuCode().equals(skuCode)) {
                            droppedProduct = product;
                            break;
                        }
                    }

                    if (droppedProduct != null) {
                        of.addProduct(droppedProduct);

                        try {
                            Order saved = new SaveOrderCommand().execute(of.getOrder());
                            Application.getApplication().setOrder(saved);
                        } catch (Exception ex) {

                        }
                    }

                } catch (UnsupportedFlavorException | IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

                }

                return true;
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Line", "Code", "Title", "Unit Price", "Quantity", "Line Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(238, 238, 238));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void orderUpdated(Order oldOrder, Order order) {
        OrderTableModel m = (OrderTableModel) jTable1.getModel();
        m.setOrder(Application.getApplication().getOrderFacade());
    }

    @Override
    public void productsUpdated() {
    }
    
    @Override
    public void categoryUpdated(OrderCategory category) {
        
    }
}