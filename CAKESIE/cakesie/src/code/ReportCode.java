/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import frame.Report;

/**
 *
 * @author tiara
 */
public class ReportCode {
    PreparedStatement ps;
    ResultSet rs;
    private String month, year;
    
    public ReportCode(String month, String year){
        this.month = month;
        this.year = year;
    }
    public void totalPenjualan(){
        String sql = "select sum(total_amount) as TOTAL_PENJUALAN from payment p\n" +
                        "inner join cake_order r on r.ID_payment = p.ID_PAYMENT\n" +
                        "where r.order_date LIKE '%"+year+"-"+month+"%'";
        
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
            Report.jTotal1.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public void totalPemesanan(){
        String sql = "select count(order_id) as JUMLAH_PEMESANAN from cake_order\n" +
                        "where order_date LIKE '%"+year+"-"+month+"%'";
        
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
            Report.jTotal2.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public void dataItem(){
        String sql = "select c.cake_name as ITEM, count(cake_name) as JUMLAH from cake c\n" +
"                        inner join custom_cake cc on cc.CAKE_ID = c.CAKE_ID\n" +
"                        inner join cake_order co on co.CUSTOM_ID = cc.CUSTOM_ID\n" +
"                        where co.ORDER_DATE like '%"+year+"-"+month+"%'\n" +
"                        group by cake_name";
        
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
            Report.jTableItem.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public void dataPenjualan(){
        String sql = "select co.order_id, co.cust_id, ca.cake_name, f.flavor_name, l.l_name, s.shape, w.w_num,  t.type_name, co.order_date from cake_order co\n" +
            "inner join custom_cake cc on cc.CUSTOM_ID = co.CUSTOM_ID\n" +
            "inner join cake ca on ca.CAKE_ID = cc.CAKE_ID\n" +
            "inner join flavor f on f.FLAVOR_ID = cc.FLAVOR_ID\n" +
            "inner join layer l on l.LAYER_ID = cc.LAYER_ID\n" +
            "inner join shape s on s.S_ID = cc.S_ID\n" +
            "inner join width w on w.W_ID = cc.W_ID\n" +
            "inner join order_type t on t.TYPE_ID = co.TYPE_ID\n" +
            "where co.order_date LIKE '%"+year+"-"+month+"%'\n" +
            "order by order_id";
        
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
            Report.jTablePenjualan.setModel(model);
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
}
