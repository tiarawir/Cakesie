/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import frame.Customize;
import frame.Confirm;
import frame.Receipt;

/**
 *
 * @author tiara
 */
public class Order {
    PreparedStatement ps;
    ResultSet rs;
    
    public void getWidthPrice(String width){
        String sql = "select w_price from width where w_num = ?";
        try{
            ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, width);
            rs = ps.executeQuery();
            while(rs.next()){
                String price1 = rs.getString("w_price");
                System.out.println(price1);
                Customize.w_price.setText(price1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void getCakePrice(String name){
        String sql = "select cake_price from cake where cake_name = ?";
        try{
            ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while(rs.next()){
                String price1 = rs.getString("cake_price");
                System.out.println(price1);
                Customize.c_price.setText(price1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void getLayerPrice(String name){
        String sql = "select l_price from layer where l_name = ?";
        try{
            ps = Database.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while(rs.next()){
                String price1 = rs.getString("l_price");
                System.out.println(price1);
                Customize.l_price.setText(price1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void calSubTotal(){
        int width = Integer.parseInt(Customize.w_price.getText());
        int cake = Integer.parseInt(Customize.c_price.getText());
        int layer = Integer.parseInt(Customize.l_price.getText());
        
        int subtotal = width + cake + layer;
        Customize.subTotal.setText(Integer.toString(subtotal));
        Confirm.subTotal.setText(Integer.toString(subtotal));
        Receipt.subTotal.setText(Integer.toString(subtotal));
    }
    public int calTax(){
        int subTotal = Integer.parseInt(Customize.subTotal.getText());
        int tax = subTotal * 10/100;
        Confirm.tax.setText(Integer.toString(tax));
        Receipt.tax.setText(Integer.toString(tax));
        return tax;
    }
    public int calTotal(){
        int subTotal = Integer.parseInt(Customize.subTotal.getText());
        
        int tax = calTax();
        int total = tax + subTotal;
        
        Confirm.amount.setText(Integer.toString(total));
        return total;
    }
    public void calTotal2(){
        int shipping = Integer.parseInt(Confirm.shipping.getText());
        int total = calTotal();
        int finalTotal = total + shipping;
        
        Confirm.amount.setText(Integer.toString(finalTotal));
    }
}
