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
		<h1 class="header-content">View Client Information</h1>
	</div>
	<div class="main-content">
	<%
		String cu = request.getParameter("client_username");
		HttpSession hs = request.getSession();
		hs.setAttribute("client_username", cu);
		
		String dbUrl = "jdbc:mysql://localhost:3306/demos";
		String dbUsername = "root";
		String dbPassword = "asdf1234";
		String dbDriver = "com.mysql.jdbc.Driver";
		
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		String getCustomerID = "SELECT customer_id FROM customer WHERE username='"+cu+"'";
		PreparedStatement ps = con.prepareStatement(getCustomerID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		try{
			int cid = rs.getInt("customer_id");
			String getClientDetails = "SELECT * FROM customer WHERE customer_id='"+cid+"'";
			PreparedStatement ps1 = con.prepareStatement(getClientDetails);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			out.println("<b>Customer ID:  "+cid);
			out.println("<br>");
			out.println("First Name:  "+rs1.getString("firstname"));
			out.println("<br>");
			out.println("Last Name:  "+rs1.getString("lastname"));
			out.println("<br>");
			out.println("Username:  "+rs1.getString("username"));
			out.println("<br>");
			out.println("Contact Number:  "+rs1.getString("contact_no"));
			out.println("<br>");
			out.println("Email ID:  "+rs1.getString("email"));
			out.println("<br>");
			out.println("Vehicle Number:  "+rs1.getString("Vehicle_no"));
			out.println("<br>");
			out.println("Vehicle Type:  "+rs1.getString("vehicle_type")+"-wheeler");
			out.println("<br>");
			out.println("<br>");
		} catch(SQLException e){
			out.println("No customer ID present with username : "+cu);
		}
	%>
	</div>
	<div class="main-content">
		<form action="admin_client_pass.jsp" method="post">
			<button type="submit" class="btn">Long Term Bookings!</button>
		</form>
		<form action="admin_client_reserve.jsp" method="post">
			<button type="submit" class="btn">Short Term Bookings!</button>
		</form>
	</div>
</body>
</html>