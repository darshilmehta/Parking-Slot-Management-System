<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookMySlot.com</title>
<link rel="icon" href="https://nameecho.com/images/blog/61.jpg" type="image">
<link rel="stylesheet" href="content.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
</head>
<body>
	<div align="center" class="header">
		<h1 class="header-content">Client Long term Pass</h1>
	</div>
	<div class="main-content">
	<table class="table-data">
	<tr>
		<td class="a">Booking ID</td>
		<td class="a">Start Date</td>
		<td class="a">End Date</td>
		<td class="a">Slot Number</td>
		<td class="a">Cost</td> 
		<td class="a">Booking Details</td>
	</tr>
	<%
		HttpSession sess = request.getSession();
		String username = String.valueOf(sess.getAttribute("client_username"));
		String dbUrl = "jdbc:mysql://localhost:3306/demos";
		String dbUsername = "root";
		String dbPassword = "asdf1234";
		String dbDriver = "com.mysql.jdbc.Driver";
		
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
		String getCustomerID = "SELECT customer_id FROM customer WHERE username='"+username+"'";
		PreparedStatement ps = con.prepareStatement(getCustomerID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int customerID = rs.getInt("customer_id");
		System.out.println(customerID);
		
		String Lquery = "SELECT * FROM regular_pass WHERE customer_id='"+customerID+"'";
		PreparedStatement psl = con.prepareStatement(Lquery);
		ResultSet rsl = psl.executeQuery();
		while(rsl.next()){
			String booking_status = rsl.getString("is_booking_over");
			System.out.println(booking_status);
			if(booking_status.equals("0")){
				booking_status = "Ongoing";
			} else{
				booking_status = "Over";
			}
	%>
	<tr>
		<td class="a"><%=rsl.getString("regular_pass_id") %></td>
		<td class="a"><%=rsl.getString("start_date") %></td>
		<td class="a"><%=rsl.getString("end_date") %></td>
		<td class="a"><%=rsl.getString("parking_slot_id") %></td>
		<td class="a"><%=rsl.getString("cost") %></td>
		<td class="a"><%=booking_status %></td>
	</tr>
	<%
		}
	%>
	</table>
	</div>
	
	<div class="main-content">
	<form action="aloginsuccess.jsp" method="post">
		<button type="submit" class="click-btn">Go Home</button>
	</form>
	</div>
</body>
</html>