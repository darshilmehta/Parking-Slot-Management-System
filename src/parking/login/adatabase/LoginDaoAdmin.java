package parking.login.adatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import parking.login.abean.LoginBeanAdmin;

public class LoginDaoAdmin {
	private String dbUrl = "jdbc:mysql://localhost:3306/demos";
	private String dbUsername = "root";
	private String dbPassword = "asdf1234";
	private String dbDriver = "com.mysql.jdbc.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean validate(LoginBeanAdmin loginBeanadmin)
	{
		boolean status = false;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		
		String sql = "SELECT * FROM admin WHERE username=? and password=?";
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, loginBeanadmin.getAdmin());
		ps.setString(2, loginBeanadmin.getPassword());
		ResultSet rs = ps.executeQuery();
		status = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
