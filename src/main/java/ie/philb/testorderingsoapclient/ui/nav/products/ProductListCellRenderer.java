/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.testorderingsoapclient.ui.nav.products;

import ie.philb.testorderingsoapclient.ws.Money;
import ie.philb.testorderingsoapclient.ws.Product;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import org.apache.commons.lang3.text.WordUtils;

public class ProductListCellRenderer extends javax.swing.JPanel implements ListCellRenderer<Product> {

    public ProductListCellRenderer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblUnitPrice = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 2, 1, 2));

        lblTitle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitle.setText("The Product Title");

        lblUnitPrice.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblUnitPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUnitPrice.setText("£1.53");

        lblCode.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCode.setText("ABC12345");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCode, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnitPrice)
                    .addComponent(lblCode)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUnitPrice;
    // End of variables declaration//GEN-END:variables
    @Override
    public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            if ((index % 2) == 0) {
                //setBackground(list.getBackground());
                setBackground(new java.awt.Color(255, 255, 255));
                setForeground(list.getForeground());
            } else {
                setBackground(new java.awt.Color(245, 245, 245));
            }
        }

        ListModel lm = list.getModel();

        if (lm instanceof ProductListModel) {
            ProductListModel plm = (ProductListModel) lm;

            if (index < plm.getSize()) {
                Product p = plm.getElementAt(index);
                Money unitPrice = p.getUnitPrice();
                
                lblTitle.setText(WordUtils.capitalize(p.getTitle()));
                lblCode.setText(p.getSkuCode());
                lblUnitPrice.setText("£" + unitPrice.getValue().toPlainString());
            } else {
                lblTitle.setText("");
            }

            return this;
        }

        return (Component) list.getCellRenderer();
    }
}