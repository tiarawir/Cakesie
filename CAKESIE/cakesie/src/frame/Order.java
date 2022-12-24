/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import code.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiara
 */
public class Order extends javax.swing.JFrame {

    /**
     * Creates new form AdminInventory
     */
    PreparedStatement ps;
    ResultSet rs;
    public Order() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
        setLocation(x, y);
        logout.setEnabled(false);
        logout.setVisible(false);
        u.setEnabled(false);
        u.setVisible(false);
        
//        jTable1.getTableHeader().setFont(new Font("Montserrat Semibold", Font.PLAIN, 12));
//        jTable1.getTableHeader().setBackground(new Color(255, 140, 142));
//        jTable1.getTableHeader().setOpaque(false);
//        jTable1.getTableHeader().setForeground(Color.BLACK);
    }
    public void preOrder(){
        clear1();
        PreparedStatement ps;
        ResultSet rs;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(date1.getDate());
        
        String sql = "select co.order_id, co.cust_id, ca.cake_name, f.flavor_name, l.l_name, s.shape, w.w_num,  t.type_name from cake_order co\n" +
"            inner join custom_cake cc on cc.CUSTOM_ID = co.CUSTOM_ID\n" +
"            inner join cake ca on ca.CAKE_ID = cc.CAKE_ID\n" +
"            inner join flavor f on f.FLAVOR_ID = cc.FLAVOR_ID\n" +
"            inner join layer l on l.LAYER_ID = cc.LAYER_ID\n" +
"            inner join shape s on s.S_ID = cc.S_ID\n" +
"            inner join width w on w.W_ID = cc.W_ID\n" +
"            inner join order_type t on t.TYPE_ID = co.TYPE_ID\n" +
"            where co.date_pickup LIKE '%"+fromDate+"%' and co.status = ''\n" +
"            order by order_id";
        
        try{
            ps = Database.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            String[] cols = new String[columnCount];
            int i;
            for(i = 1; i <= columnCount; i++){
            cols[i-1] = md.getColumnName(i);
        }
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            while(rs.next()){
                Object[] row = new Object[columnCount];
                for(i=1;i<=columnCount;i++){
                    row[i-1] = rs.getObject(i);
                }
                model.addRow(row);
            }
            jTable1.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }

    public void delivered(){
        clear2();
        PreparedStatement ps;
        ResultSet rs;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(date2.getDate());
        
        String sql = "select co.order_id, co.cust_id, ca.cake_name, f.flavor_name, l.l_name, s.shape, w.w_num, t.type_name, co.status from cake_order co\n" +
"            inner join custom_cake cc on cc.CUSTOM_ID = co.CUSTOM_ID\n" +
"            inner join cake ca on ca.CAKE_ID = cc.CAKE_ID\n" +
"            inner join flavor f on f.FLAVOR_ID = cc.FLAVOR_ID\n" +
"            inner join layer l on l.LAYER_ID = cc.LAYER_ID\n" +
"            inner join shape s on s.S_ID = cc.S_ID\n" +
"            inner join width w on w.W_ID = cc.W_ID\n" +
"            inner join order_type t on t.TYPE_ID = co.TYPE_ID\n" +
"            where co.date_pickup LIKE '%"+fromDate+"%'\n" +
"            order by order_id";
        
        try{
            ps = Database.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            String[] cols = new String[columnCount];
            int i;
            for(i = 1; i <= columnCount; i++){
            cols[i-1] = md.getColumnName(i);
        }
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            while(rs.next()){
                Object[] row = new Object[columnCount];
                for(i=1;i<=columnCount;i++){
                    row[i-1] = rs.getObject(i);
                }
                model.addRow(row);
            }
            jTable2.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public void daily(){
        clear3();
        PreparedStatement ps;
        ResultSet rs;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(from.getDate());
        String toDate = dateFormat.format(to.getDate());
        
        String sql = "select co.order_id, co.cust_id, cc.cake_id, cc.flavor_id, cc.layer_id, cc.s_id, cc.w_id, t.type_name, co.date_pickup, co.status from cake_order co\n" +
"           inner join custom_cake cc on cc.CUSTOM_ID = co.CUSTOM_ID\n" +
"          inner join order_type t on t.TYPE_ID = co.TYPE_ID\n" +
"          where co.order_date between '"+fromDate+"' and '"+toDate+"' \n" +
"           order by order_id";
        
        try{
            ps = Database.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            String[] cols = new String[columnCount];
            int i;
            for(i = 1; i <= columnCount; i++){
            cols[i-1] = md.getColumnName(i);
        }
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            while(rs.next()){
                Object[] row = new Object[columnCount];
                for(i=1;i<=columnCount;i++){
                    row[i-1] = rs.getObject(i);
                }
                model.addRow(row);
            }
            jTable3.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public void clear1(){
        DefaultTableModel table = (DefaultTableModel)jTable1.getModel();
        table.setRowCount(0);
    }
    public void clear2(){
        DefaultTableModel table = (DefaultTableModel)jTable2.getModel();
        table.setRowCount(0);
    }
    public void clear3(){
        DefaultTableModel table = (DefaultTableModel)jTable3.getModel();
        table.setRowCount(0);
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        u1 = new javax.swing.JLabel();
        u = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JLabel();
        custom = new javax.swing.JLabel();
        history = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        node = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        date1 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        from = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        base = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        u1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        u1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        u1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                u1MouseClicked(evt);
            }
        });
        getContentPane().add(u1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 40, 40));

        u.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        u.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        u.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uMouseClicked(evt);
            }
        });
        getContentPane().add(u, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 40, 40));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu.setText("Order");
        menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 70, 30));

        custom.setFont(new java.awt.Font("Montserrat Medium", 0, 13)); // NOI18N
        custom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        custom.setText("Item");
        custom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        custom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customMouseClicked(evt);
            }
        });
        jPanel1.add(custom, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 100, 30));

        history.setFont(new java.awt.Font("Montserrat Medium", 0, 13)); // NOI18N
        history.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        history.setText("Report");
        history.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyMouseClicked(evt);
            }
        });
        jPanel1.add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 70, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo (50 x 50 px).png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cakesie.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 80, 30));

        node.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        node.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/node.png"))); // NOI18N
        jPanel1.add(node, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 30, -1));

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, -1, -1));

        jTabbedPane1.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel2.setText("Date Pickup   :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        jLabel7.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel7.setText("Order ID           :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, 30));

        id.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jPanel2.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 130, 30));

        jLabel4.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel4.setText("Status               :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, 30));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jCheckBox1.setText("Delivered");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, 30));
        jPanel2.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 180, -1));

        jLabel6.setText("search");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 750, 210));

        jTabbedPane1.addTab("Pre-Order", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("Date Pickup     :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));
        jPanel3.add(date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 140, -1));

        jLabel8.setText("search");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 750, 220));

        jTabbedPane1.addTab("Delivered", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jLabel9.setText("To");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jLabel10.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jLabel10.setText("From");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));
        jPanel4.add(from, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 140, -1));
        jPanel4.add(to, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 140, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 750, 200));

        jLabel11.setText("search");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        jTabbedPane1.addTab("Daily Order", jPanel4);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 780, 330));

        base.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Rectangle 1.png"))); // NOI18N
        jPanel1.add(base, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customMouseClicked
        Inventory i = new Inventory();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_customMouseClicked

    private void historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyMouseClicked
        Report r = new Report();
        r.setVisible(true);
        dispose();
    }//GEN-LAST:event_historyMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(date1.getDate());
        System.out.println(fromDate);
        preOrder();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        String id = jTable1.getModel().getValueAt(row, 0).toString();
        
        this.id.setText(id);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        String status = "delivered";
        String id = this.id.getText();
        if(jCheckBox1.isSelected()){
            String sql = "update cake_order set status = ? where order_id = ?";
        try{
            ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);

            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Success Updating");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        }
        preOrder();
        jCheckBox1.setSelected(false);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        delivered();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = dateFormat.format(from.getDate());
        String toDate = dateFormat.format(to.getDate());
        System.out.println(fromDate);
        System.out.println(toDate);
        daily();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void u1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_u1MouseClicked
        logout.setEnabled(true);
        logout.setVisible(true);
        u.setEnabled(true);
        u.setVisible(true);
        u1.setEnabled(false);
        u1.setVisible(false);
    }//GEN-LAST:event_u1MouseClicked

    private void uMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uMouseClicked
        logout.setEnabled(false);
        logout.setVisible(false);
        u.setEnabled(false);
        u.setVisible(false);
        u1.setEnabled(true);
        u1.setVisible(true);
    }//GEN-LAST:event_uMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        Dashboard d = new Dashboard();
        d.setVisible(true);
        dispose();
    }//GEN-LAST:event_logoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel base;
    private javax.swing.JLabel custom;
    private com.toedter.calendar.JDateChooser date1;
    private com.toedter.calendar.JDateChooser date2;
    private com.toedter.calendar.JDateChooser from;
    private javax.swing.JLabel history;
    private javax.swing.JLabel id;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel node;
    private com.toedter.calendar.JDateChooser to;
    private javax.swing.JLabel u;
    private javax.swing.JLabel u1;
    // End of variables declaration//GEN-END:variables
}
