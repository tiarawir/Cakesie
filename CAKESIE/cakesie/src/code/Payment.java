package code;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Payment {

    CallableStatement cs;
    
    public void makePayment(String method, int amount){
        String sql = "call insertPayment(?,?)";
        try{
            cs = Database.getConnection().prepareCall(sql);
            cs.setString(1, method);
            cs.setInt(2, amount);
            cs.executeQuery();
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public abstract void confirmPayment(String uname, String method, int amount, String add, String date);
}