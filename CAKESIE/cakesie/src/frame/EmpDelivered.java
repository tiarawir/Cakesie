/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import code.Database;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiara
 */
public class EmpDelivered extends javax.swing.JFrame {

    /**
     * Creates new form EmpDelivered
     */
    PreparedStatement ps;
    ResultSet rs;
    public EmpDelivered() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
        setLocation(x, y);
        logout.setEnabled(false);
        logout.setVisible(false);
        u.setEnabled(false);
        u.setVisible(false);
    }
    public void delivered(){
        clear2();
        
        
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
    public void clear2(){
        DefaultTableModel table = (DefaultTableModel)jTable2.getModel();
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

        menu = new javax.swing.JLabel();
        custom = new javax.swing.JLabel();
        history = new javax.swing.JLabel();
        node = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        date2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        u = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        u1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setFont(new java.awt.Font("Montserrat Medium", 0, 13)); // NOI18N
        menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu.setText("Pre-Order");
        menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMouseClicked(evt);
            }
        });
        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 70, 30));

        custom.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        custom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        custom.setText("Delivered");
        custom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        custom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customMouseClicked(evt);
            }
        });
        getContentPane().add(custom, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 100, 30));

        history.setFont(new java.awt.Font("Montserrat Medium", 0, 13)); // NOI18N
        history.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        history.setText("Daily Order");
        history.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyMouseClicked(evt);
            }
        });
        getContentPane().add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 80, 30));

        node.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        node.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/node.png"))); // NOI18N
        getContentPane().add(node, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 40, -1));

        jLabel3.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("Date Pickup     :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, 20));
        getContentPane().add(date2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 140, -1));

        jLabel8.setText("search");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, -1, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 750, 210));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo (50 x 50 px).png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cakesie.png"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 80, 30));

        u.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        u.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        u.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uMouseClicked(evt);
            }
        });
        getContentPane().add(u, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 40, 40));

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, -1, -1));

        u1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        u1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        u1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                u1MouseClicked(evt);
            }
        });
        getContentPane().add(u1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 40, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Rectangle 1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customMouseClicked
        
    }//GEN-LAST:event_customMouseClicked

    private void historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyMouseClicked
        EmpDaily i = new EmpDaily();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_historyMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        delivered();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMouseClicked
        EmpPreOrder i = new EmpPreOrder();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_menuMouseClicked

    private void uMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uMouseClicked
        logout.setEnabled(false);
        logout.setVisible(false);
        u.setEnabled(false);
        u.setVisible(false);
        u1.setEnabled(true);
        u1.setVisible(true);
    }//GEN-LAST:event_uMouseClicked

    private void u1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_u1MouseClicked
        logout.setEnabled(true);
        logout.setVisible(true);
        u.setEnabled(true);
        u.setVisible(true);
        u1.setEnabled(false);
        u1.setVisible(false);
    }//GEN-LAST:event_u1MouseClicked

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
            java.util.logging.Logger.getLogger(EmpDelivered.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpDelivered.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpDelivered.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpDelivered.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmpDelivered().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel custom;
    private com.toedter.calendar.JDateChooser date2;
    private javax.swing.JLabel history;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel node;
    private javax.swing.JLabel u;
    private javax.swing.JLabel u1;
    // End of variables declaration//GEN-END:variables
}