package parking.regularSlotA.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parking.regularSlotA.bean.RegularSlotAdminBean;

public class RegularSlotAdminDao {
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
	
	public boolean bookSlotAdmin(RegularSlotAdminBean rsab) throws SQLException {
		boolean result_final = false;
		int result = 0;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		String u = rsab.getUsername();
		String getCustomerID = "SELECT customer_id,vehicle_type FROM customer WHERE username='"+u+"'";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(getCustomerID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs.next();
		int customerID = rs.getInt("customer_id");
		int vehicle_type = Integer.parseInt(rs.getString("vehicle_type"));
		int bno = vehicle_type - 1;
		
		String startDate = rsab.getStartDate();
		String endDate = "null";
		String duration = rsab.getDuration();
		int duration_in_mins = 0;
		String location = rsab.getLocation();
		int slot_no = 0; 
		for(int sno=1; sno<=10; sno++) {
			if(location.equals("1")) {
				slot_no = 100 + bno;
				slot_no = slot_no*1000+ sno;
				int slot_availiblity = check_if_available(slot_no);
				if(slot_availiblity == 0) {
					update_slot_booked(slot_no);
					result_final = book_pass_update(customerID, startDate, duration, endDate, duration_in_mins, vehicle_type, slot_no, result);
					break;
				}
			}
			else if(location.equals("2")){
				slot_no = 200 + bno;
				slot_no = slot_no*1000+ sno;
				int slot_availiblity = check_if_available(slot_no);
				if(slot_availiblity == 0) {
					update_slot_booked(slot_no);
					result_final = book_pass_update(customerID, startDate, duration, endDate, duration_in_mins, vehicle_type, slot_no, result);
					break;
				}
			}
			else if(location.equals("3")){
				slot_no = 300 + bno;
				slot_no = slot_no*1000+ sno;
				int slot_availiblity = check_if_available(slot_no);
				if(slot_availiblity == 0) {
					update_slot_booked(slot_no);
					result_final = book_pass_update(customerID, startDate, duration, endDate, duration_in_mins, vehicle_type, slot_no, result);
					break;
				}
			}
			else if(location.equals("4")){
				slot_no = 400 + bno;
				slot_no = slot_no*1000+ sno;
				int slot_availiblity = check_if_available(slot_no);
				if(slot_availiblity == 0) {	
					update_slot_booked(slot_no);
					result_final = book_pass_update(customerID, startDate, duration, endDate, duration_in_mins, vehicle_type, slot_no, result);
					break;
				}
			}
			else {
				System.out.println("Error: error in slot number creation");
			}
		}
		return result_final;
	}
	
	public int check_if_available(int slot_no) throws SQLException {
		String query = "SELECT is_slot_booked FROM parking_slot WHERE parking_slot_id='"+slot_no+"'";
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = ps.executeQuery();
		rs.next();
		int slot_availiblity = rs.getInt("is_slot_booked");
		return slot_availiblity;
	}
	
	public void update_slot_booked(int slot_no) throws SQLException {
		String query = "UPDATE parking_slot SET is_slot_booked=1 WHERE parking_slot_id='"+slot_no+"'";
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps.executeUpdate();
	}
	
	public boolean book_pass_update(int customerID, String startDate, String duration, String endDate, int duration_in_mins, int vehicle_type, int slot_no, int result) throws SQLException {
		String Pattern = "yyyy-MM-dd'T'HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(Pattern);
		
		Calendar c = Calendar.getInstance();		
		Date START = sdf.parse(startDate, new ParsePosition(0));
		c.setTime(START);

		if(duration.equals("30-min")) {
			c.add(Calendar.MINUTE, 30);
			endDate = sdf.format(c.getTime());
			duration_in_mins = 30;
		}
		else if(duration.equals("one-hour")) {
			c.add(Calendar.HOUR, 1);
			endDate = sdf.format(c.getTime());
			duration_in_mins = 60;
		}
		else if(duration.equals("two-hour")) {
			c.add(Calendar.HOUR, 2);
			endDate = sdf.format(c.getTime());
			duration_in_mins = 120;
		}
		else if(duration.equals("four-hour")) {
			c.add(Calendar.HOUR, 4);
			endDate = sdf.format(c.getTime());
			duration_in_mins =240;
		}
		else if(duration.equals("twelve-hour")) {
			c.add(Calendar.HOUR, 12);
			endDate = sdf.format(c.getTime());
			duration_in_mins = 720;
		}
		else if(duration.equals("one-day")) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			endDate = sdf.format(c.getTime());
			duration_in_mins = 1440;
		}
		else{
			System.out.println("Error: error in calculation of end date");
		}
		
		Connection con = getConnection();
		String getPassCost = "SELECT cost FROM regular_reserve_cost WHERE vehicle_type='"+vehicle_type+"' and duration_in_mins='"+duration_in_mins+"'";
		PreparedStatement ps1 = null;
		try {
			ps1 = con.prepareStatement(getPassCost);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			rs1 = ps1.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs1.next();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		double cost = rs1.getDouble("cost");
		
		String query = "INSERT INTO regular_reserve(customer_id, start_date, duration_in_mins, end_date, parking_slot_id, cost, is_booking_over) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps2 = null;
		try {
			ps2 = con.prepareStatement(query);
			ps2.setInt(1, customerID);
			ps2.setString(2, startDate);
			ps2.setInt(3, duration_in_mins);
			ps2.setString(4, endDate);
			ps2.setInt(5, slot_no);
			ps2.setDouble(6, cost);
			ps2.setInt(7, 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result = ps2.executeUpdate();
		if(result == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
