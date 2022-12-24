package code;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class StoreEmployee implements User {

	PreparedStatement ps;
        ResultSet rs;
	private String idnum;
	private String password;

	public StoreEmployee(String idnum, String password) {
		this.idnum = idnum;
                this.password = password;
	}

	public Boolean logIn() {
            String sql = "SELECT * FROM `admin` WHERE `username` = ? AND `admin_password` = ?";
            try{
                ps = Database.getConnection().prepareStatement(sql);
                ps.setString(1, idnum);
                ps.setString(2, password);
                
                rs = ps.executeQuery();
                if(rs.next()){
                    
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Username/Password","Login Error",2);
                }
                return true;
            }catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

}