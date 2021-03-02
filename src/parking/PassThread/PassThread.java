package parking.PassThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class PassThread implements Runnable{
	
	String dbUrl = "jdbc:mysql://localhost:3306/demos";
	String dbUsername = "root";
	String dbPassword = "asdf1234";
	String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void run() {
		loadDriver(dbDriver);
		Connection con = getConnection();
		String query = "SELECT end_date,parking_slot_id FROM regular_pass";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int result = 0;
				int final_result = 0;
				String e = rs.getString("end_date");
				int pid = rs.getInt("parking_slot_id");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date end = sdf.parse(e);
				Date current = new Date();
				if(current.after(end)) {
					String update_slot = "UPDATE parking_slot SET is_slot_booked=0 WHERE parking_slot_id='"+pid+"'";
					PreparedStatement ps1 = con.prepareStatement(update_slot);
					result = ps1.executeUpdate();
					if(result == 1) {
						String update_regularPass = "UPDATE regular_pass SET is_booking_over=1 WHERE parking_slot_id='"+pid+"'";
						PreparedStatement ps2 = con.prepareStatement(update_regularPass);
						final_result = ps2.executeUpdate();
						if(final_result == 1) {
							System.out.println("success");
						} else {
							System.out.println("error");
						}
					} else {
						System.out.println("There was some error!");
					}
				}
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}