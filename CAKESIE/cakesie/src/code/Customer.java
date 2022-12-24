package code;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import frame.*;
import java.sql.ResultSet;

public class Customer implements User {

        PreparedStatement ps;
        ResultSet rs;
	private String uname;
	private String password;
        
        public Customer(String uname, String password){
            this.uname = uname;
            this.password = password;
        }
        
        LogIn login = new LogIn();
        Home1 home = new Home1();
        

	public Boolean logIn() {
            String sql = "SELECT * FROM `customer` WHERE `username` = ? AND `cust_password` = ?";
            try{
                ps = Database.getConnection().prepareStatement(sql);
                ps.setString(1, uname);
                ps.setString(2, password);
                
                rs = ps.executeQuery();
                if(rs.next()){
                    String name = rs.getString("username");
                    home.name1.setText(name);
                    home.setVisible(true);
                    home.pack();
                    home.setLocationRelativeTo(null);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Username/Password","Login Error",2);
                    LogIn l = new LogIn();
                    l.setVisible(false);
                    l.dispose();
                    l.setVisible(true);
                }
                return true;
            }catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        
        public void signUp(String uname, String password, String name, String telephone, String email, String pass2){
            if(verifyFields(uname, password, name, telephone, email, pass2)){
                if(!checkUsername(uname)){
                    String sql = "INSERT INTO `customer`(`username`,`cust_password`,`name`,`telephone`,`email`) VALUES (?,?,?,?,?)";
                    try{
                        ps = Database.getConnection().prepareStatement(sql);
                        ps.setString(1, uname);
                        ps.setString(2, password);
                        ps.setString(3, name);
                        ps.setString(4, telephone);
                        ps.setString(5, email);
                
                        if(ps.executeUpdate() != 0){
                            SignUp signup = new SignUp();
                            JOptionPane.showMessageDialog(signup, "Thank You! Your Registration Successfully","Registration",JOptionPane.PLAIN_MESSAGE);
                            login.setVisible(true);
                            signup.dispose();
                        }else{
                        JOptionPane.showMessageDialog(null, "Error");
                        }
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }  
        }
        
        public boolean verifyFields(String uname, String password, String name, String telephone, String email, String pass2){
            if(uname.trim().equals("")||name.trim().equals("")||telephone.trim().equals("")
                ||email.trim().equals("")||password.trim().equals("")||pass2.trim().equals("")){
                JOptionPane.showMessageDialog(null, "One or More Fields are Empty");
                return false;
            }else if(!password.equals(pass2)){
                JOptionPane.showMessageDialog(null, "Password Doesn't Match","Confirm Password",2);
                return false;
            }else{
                return true;
            }
        }
        
        public boolean checkUsername(String uname){
        boolean username_exist = false;
        
        String query = "SELECT * FROM `customer` WHERE `username` = ?";
        try {
            ps = Database.getConnection().prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();
            if(rs.next()){
                username_exist = true;
                JOptionPane.showMessageDialog(null, "This Username is Already Exist");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return username_exist;
        
    }

}