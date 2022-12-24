/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tiara
 */
public class Customization {
    
    CallableStatement cs;
    ResultSet rs;
    private String uname;
    
    public Customization(String uname){
        this.uname = uname;
    }
    
    public Boolean makeOrder() {
        String sql = "call saveOrder1(?)";
        try{
            cs = Database.getConnection().prepareCall(sql);
            cs.setString(1, uname);
            cs.executeQuery();
            return true;    
            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public Boolean customCake(String cake, String width, String shape, String flavor, String layer, String note){
        String sql = "call saveCustom(?, ?, ?, ?, ?, ?, ?)";
        try{
            cs = Database.getConnection().prepareCall(sql);
            cs.setString(1, uname);
            cs.setString(2, cake);
            cs.setString(3, width);
            cs.setString(4, shape);
            cs.setString(5, flavor);
            cs.setString(6, layer);
            cs.setString(7, note);
            
            cs.executeQuery();
            return true;
            
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
