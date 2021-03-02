package parking.register.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import parking.register.bean.RegisterBean;

public class RegisterDao {
	private String dbURL = "jdbc:mysql://localhost:3306/demos";
	private String dbUsername = "root";
	private String dbPassword = "asdf1234";
	private String dbDriver = "com.mysql.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean registerClient(RegisterBean registerbean) {
		int result = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		String query = "INSERT INTO customer(firstname, lastname, username, password, email, contact_no, vehicle_no, vehicle_type, is_regular_customer) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, registerbean.getFirstname());
			ps.setString(2, registerbean.getLastname());
			ps.setString(3, registerbean.getUsername());
			ps.setString(4, registerbean.getPassword());
			ps.setString(5, registerbean.getEmail());
			ps.setString(6, registerbean.getCustomer_number());
			ps.setString(7, registerbean.getVehicle_number());
			ps.setString(8, registerbean.getVehicle_type());
			ps.setString(9, "0");
			result = ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
}
