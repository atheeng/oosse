
package view;

import com.dao.OrderDao;
import com.dao.daoImpl.OrderDaoImpl;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class OrderViewFrame extends javax.swing.JFrame {
    public OrderViewFrame() {
        initComponents();
    }

   public void loadItemDetails(String orderNo){
    OrderDao orderDao = new OrderDaoImpl();
        List<Map<String, String>> list = orderDao.getOrderItemList(orderNo);
        DefaultTableModel model = new DefaultTableModel();
        String headers[] = {"Order No.", "Product","Qty", "Unit Price", "Description"};
        model.setColumnIdentifiers(headers);
        for (Map<String, String> orderItem : list) {
            model.addRow(
                    new String[]{
                        orderItem.get("orderNo"),
                        orderItem.get("productName"),
                        orderItem.get("qty"),
                        orderItem.get("unitPrice"),
                        orderItem.get("description")
                    });
        }
        tbl_order_items.setModel(model);
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_header = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_order_items = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("order Details");
        setAlwaysOnTop(true);
        setResizable(false);

        txt_header.setEditable(false);
        txt_header.setBackground(new java.awt.Color(153, 255, 255));
        txt_header.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txt_header.setText("                                       ONLINE SHOPPING MGMT SYS.");
        txt_header.setMargin(new java.awt.Insets(2, 30, 2, 30));
        txt_header.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_headerActionPerformed(evt);
            }
        });

        tbl_order_items.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Order No", "Product", "Qty", "Rate", "Description"
            }
        ));
        jScrollPane2.setViewportView(tbl_order_items);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Order Item Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txt_header, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(101, 101, 101))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_headerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_headerActionPerformed
        
    }//GEN-LAST:event_txt_headerActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderViewFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_order_items;
    private javax.swing.JTextField txt_header;
    // End of variables declaration//GEN-END:variables
}
