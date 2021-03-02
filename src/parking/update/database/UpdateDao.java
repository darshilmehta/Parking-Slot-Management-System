package parking.update.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import parking.update.bean.UpdateBean;

public class UpdateDao {
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
	
	public boolean updateClient(UpdateBean updatebean, String username) {
		int result = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		String query = "UPDATE customer SET password=?, vehicle_no=?, vehicle_type=? WHERE username='"+username+"'";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, updatebean.getPassword());
			ps.setString(2, updatebean.getVehicle_number());
			ps.setString(3, updatebean.getVehicle_type());
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
