/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.UIManager;

/**
 *
 * @author tiara
 */
public class Receipt extends javax.swing.JFrame {

    /**
     * Creates new form Receipt
     */
    public Receipt() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
        setLocation(x, y);
        currentDate();
    }
    public void currentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.dateNow.setText(dateFormat.format(date));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setText("RECEIPT FOR");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        size.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(size, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 240, 20));

        jLabel5.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel5.setText("Size");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, 20));

        jLabel6.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel6.setText("Shape");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 20));

        jLabel7.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel7.setText("Flavor");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, 20));

        jLabel8.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel8.setText("Layer");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 20));

        name.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jPanel1.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 20));

        jLabel11.setFont(new java.awt.Font("Montserrat SemiBold", 0, 11)); // NOI18N
        jLabel11.setText("Payment Method  :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 20));

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel12.setText("Total");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, 20));

        jLabel13.setFont(new java.awt.Font("Montserrat SemiBold", 0, 11)); // NOI18N
        jLabel13.setText("Date");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 20));

        jLabel14.setFont(new java.awt.Font("Montserrat SemiBold", 0, 11)); // NOI18N
        jLabel14.setText("Date Pickup");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, 20));

        jLabel15.setFont(new java.awt.Font("Montserrat SemiBold", 0, 11)); // NOI18N
        jLabel15.setText("Pickup Method       :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 20));

        shape.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(shape, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 240, 20));

        flavor.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(flavor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 240, 20));

        total.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 210, 20));

        payment.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 100, 20));

        cake.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(cake, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 240, 20));

        dateNow.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(dateNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 110, 20));

        datePickUp.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(datePickUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 80, 20));

        pickup.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(pickup, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 100, 20));

        jLabel23.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel23.setText("Cake");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 20));

        jLabel24.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel24.setText("SubTotal");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, 20));

        jLabel25.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel25.setText("Shipping Cost");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, 20));

        layer.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(layer, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 240, 20));

        tax.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 210, 20));

        subTotal.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(subTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 210, 20));

        jLabel26.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel26.setText("Tax");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 20));

        shipping.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jPanel1.add(shipping, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 170, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/receipt.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, -1));

        jLabel4.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("\"Thank You for Choosing Us\"");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 210, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 360, 520));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/download.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, -1, 70));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");
        
        job.setPrintable(new Printable(){
        public int print(Graphics pg, PageFormat pf, int pageNum){
            pf.setOrientation(PageFormat.PORTRAIT);
            if(pageNum>0){
                return Printable.NO_SUCH_PAGE;
            }
            
            Graphics2D g2 = (Graphics2D)pg;
            g2.translate(pf.getImageableX(), pf.getImageableY());
            g2.scale(0.47, 0.47);
            
            jPanel1.print(g2);
            return Printable.PAGE_EXISTS;
        }
        });
        boolean ok = job.printDialog();
        if(ok){
            try{
                job.print();
            }catch(PrinterException e){
                
            }
        }
        Home1 h = new Home1();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JLabel cake = new javax.swing.JLabel();
    public static final javax.swing.JLabel dateNow = new javax.swing.JLabel();
    public static final javax.swing.JLabel datePickUp = new javax.swing.JLabel();
    public static final javax.swing.JLabel flavor = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static final javax.swing.JLabel layer = new javax.swing.JLabel();
    public static final javax.swing.JLabel name = new javax.swing.JLabel();
    public static final javax.swing.JLabel payment = new javax.swing.JLabel();
    public static final javax.swing.JLabel pickup = new javax.swing.JLabel();
    public static final javax.swing.JLabel shape = new javax.swing.JLabel();
    public static final javax.swing.JLabel shipping = new javax.swing.JLabel();
    public static final javax.swing.JLabel size = new javax.swing.JLabel();
    public static final javax.swing.JLabel subTotal = new javax.swing.JLabel();
    public static final javax.swing.JLabel tax = new javax.swing.JLabel();
    public static final javax.swing.JLabel total = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}