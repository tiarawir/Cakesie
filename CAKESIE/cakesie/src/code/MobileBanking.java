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
public class MobileBanking extends Payment{

    CallableStatement cs;
    ResultSet rs;


    @Override
    public void confirmPayment(String uname, String method, int amount, String add, String date) {
        String sql = "call savePayment(?,?,?,?,?)";
        try{
            cs = Database.getConnection().prepareCall(sql);
            cs.setString(1, uname);
            cs.setString(2, method);
            cs.setInt(3, amount);
            cs.setString(4, add);
            cs.setString(5, date);
           
            cs.executeQuery();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
