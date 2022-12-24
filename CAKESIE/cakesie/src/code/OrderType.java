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
public class OrderType {
    CallableStatement cs;
    ResultSet rs;
    private String uname;
    private String type;
    
    public OrderType(String uname, String type){
        this.uname = uname;
        this.type = type;
    }
    
    public Boolean selectType(){
        String sql = "call saveType(?,?)";
        try{
            cs = Database.getConnection().prepareCall(sql);
            cs.setString(1, uname);
            cs.setString(2, type);
            cs.executeQuery();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
