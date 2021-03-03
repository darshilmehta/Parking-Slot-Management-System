package parking.pdf.database;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import parking.pdf.bean.pdfbean;

public class pdfdao {
	String dbUrl = "jdbc:mysql://localhost:3306/demos";
	String dbUsername = "root";
	String dbPassword = "asdf1234";
	String dbDriver = "com.mysql.jdbc.Driver";
	
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
	
	public boolean MakeSlip(pdfbean db) throws SQLException, ParseException, FileNotFoundException, DocumentException {
		// getting the id that the admin would enter
		int booking_id = db.getBookingID();
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		
		// getting details..
		String checkID = "SELECT * FROM regular_reserve WHERE regular_reserve_id='"+booking_id+"'";
		PreparedStatement ps = con.prepareStatement(checkID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int is_booking_over = rs.getInt("is_booking_over");
		double basic_cost = rs.getDouble("cost");
		String end_date = rs.getString("end_date");
		Date endTime = sdf.parse(end_date);
		int slot_id = rs.getInt("parking_slot_id");
		
		// set variables 
		double penalty = 0.0;
		double total_cost = 0.0;
		
		// set current time 
		Date currentTime = new Date();
		String current = sdf.format(currentTime);
		
		// function to change the final cost incase of delayed exit
		if(is_booking_over == 0) {
			if(currentTime.after(endTime)) {
				penalty = 0.3*basic_cost;
				total_cost = basic_cost + penalty;
			} else {
				penalty = 0.0;
				total_cost = basic_cost + penalty;
			}
			
			// to update that the booking is over
			String updateRRDB = "UPDATE regular_reserve SET is_booking_over=1 WHERE regular_reserve_id='"+booking_id+"'";
			PreparedStatement ps1 = con.prepareStatement(updateRRDB);
			int result1 = ps1.executeUpdate();
			System.out.println("resut1 = "+result1);
			
			// to free up the slot for further bookings on the database
			String updatePSDB = "UPDATE parking_slot SET is_slot_booked=0 WHERE parking_slot_id='"+slot_id+"'";
			PreparedStatement ps2 = con.prepareStatement(updatePSDB);
			int result2 = ps2.executeUpdate();
			System.out.println("resut2 = "+result2);
			
			// to make an entry within the parking_slip database
			String insertPSDB = "INSERT INTO parking_slip(regular_reserve_id,real_exit_time,cost_penalty,total_cost) VALUES(?, ?, ?, ?)";
			PreparedStatement ps3 = con.prepareStatement(insertPSDB);
			ps3.setInt(1, booking_id);
			ps3.setString(2, current);
			ps3.setDouble(3, penalty);
			ps3.setDouble(4, total_cost);
			int result3 = ps3.executeUpdate();
			if(result3 == 1) {
				makePDF(booking_id);
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("payment is already over");
			return false;
		}
	}
	
	public void makePDF(int booking_id) throws SQLException, DocumentException, FileNotFoundException {
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		String pdfDetails = "SELECT * FROM parking_slip ps JOIN regular_reserve rr ON ps.regular_reserve_id = rr.regular_reserve_id WHERE rr.regular_reserve_id='"+booking_id+"'";
		System.out.print(pdfDetails);
		PreparedStatement psf = con.prepareStatement(pdfDetails);
		ResultSet rsf = psf.executeQuery();
		rsf.next();
		
		// Fonts to be used
		Font head = FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD, new CMYKColor(71, 53, 0, 12));
		Font normal = FontFactory.getFont(FontFactory.COURIER, 14, Font.NORMAL, new CMYKColor(0, 0, 0, 100));
		Font endTxt = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD, new CMYKColor(0, 100, 100, 0));
		
		// FilePath
		String filename = "E:\\Eclipse-JavaProgramsStorage\\Parking_Slot_System_Management\\Generated PDFs\\receipt-booking-"+booking_id+".pdf";
		
		// object of the document
		Document doc = new Document();
		PdfWriter pwrite = PdfWriter.getInstance(doc, new FileOutputStream(filename));
		
		// Document writing process starts
		doc.open();
		
		// Used for generating a BarCode
		PdfContentByte pdfcb = pwrite.getDirectContent();
        BarcodeEAN barcodeEAN = new BarcodeEAN();
        barcodeEAN.setCodeType(BarcodeEAN.EAN13);
        barcodeEAN.setCode("1234523453323");
        Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfcb, null, null);
        codeEANImage.scalePercent(100);
        
        // All paragraph strings
        String Billno = "Bill Number - "+rsf.getString("parking_slip_id")+".";
        String Bookingid = "Booking ID - "+rsf.getString("regular_reserve_id")+".";
        String Parkingslotid = "Parking Slot ID - "+rsf.getString("parking_slot_id")+".";
        String Starttime = "Start Time - "+(rsf.getString("start_date")).replace('T', ' ')+".";
        String Endtime = "End Time - "+(rsf.getString("end_date")).replace('T', ' ')+".";
        String Exittime= "Exit Time - "+(rsf.getString("real_exit_time")).replace('T', ' ')+".";
        String Basiccost = "Basic Cost - "+rsf.getString("cost")+".";
        String Penalty = "Extra Charges (incase of delayed exit) - "+rsf.getString("cost_penalty")+".";
        String Newcost = "Total Cost - "+rsf.getString("total_cost")+".";
        
        // All paragraphs
		Paragraph header = new Paragraph("BookMySlot.com",head);
		Paragraph billno = new Paragraph(Billno,normal);
		Paragraph bookingid = new Paragraph(Bookingid,normal);
		Paragraph parkingslotid = new Paragraph(Parkingslotid,normal);
		Paragraph startTime = new Paragraph(Starttime,normal);
		Paragraph endTime = new Paragraph(Endtime,normal);
		Paragraph exitTime = new Paragraph(Exittime,normal);
		Paragraph basiccost = new Paragraph(Basiccost,normal);
		Paragraph penalty = new Paragraph(Penalty,normal);
		Paragraph newcost = new Paragraph(Newcost,normal);
		Paragraph footer = new Paragraph("THANKYOU! PLEASE VISIT AGAIN!",endTxt);
		
		// Alignment of specific items
		header.setAlignment(Element.ALIGN_CENTER);
		footer.setAlignment(Element.ALIGN_CENTER);

		doc.add(header);
		doc.add(billno);
		doc.add(bookingid);
		doc.add(parkingslotid);
		doc.add(startTime);
		doc.add(endTime);
		doc.add(exitTime);
		doc.add(basiccost);
		doc.add(penalty);
		doc.add(newcost);
        doc.add(codeEANImage);
		doc.add(footer);
		
		// Closing of the document
		doc.close();
		pwrite.close();
	}
}