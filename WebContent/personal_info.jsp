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
		<h1 class="header-content">Personal Information!</h1>
	</div>
	<div class="main-content">
	<%
		HttpSession sess = request.getSession();
		String username = String.valueOf(sess.getAttribute("username"));
		String dbUrl = "jdbc:mysql://localhost:3306/demos";
		String dbUsername = "root";
		String dbPassword = "asdf1234";
		String dbDriver = "com.mysql.jdbc.Driver";
		
		String query = "SELECT * FROM customer WHERE username='"+username+"'";
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			out.println("Customer ID: "+rs.getInt("customer_id"));
			out.println("<br>");
			out.println("First Name : "+rs.getString("firstname"));
			out.println("<br>");
			out.println("Last Name : "+rs.getString("lastname"));
			out.println("<br>");
			out.println("User Name : "+rs.getString("username"));
			out.println("<br>");
			out.println("Password : "+rs.getString("password"));
			out.println("<br>");
			out.println("Vehicle Number : "+rs.getString("vehicle_no"));
			out.println("<br>");
			out.println("Contact Number : "+rs.getString("contact_no"));
			out.println("<br>");
			out.println("Email ID : "+rs.getString("email"));
		}
		else
		{
			out.println("RECORD NOT FOUND");	
		}
	%>
	</div>
	<div class="main-content">
	<form action="clientloginsuccess.jsp" method="post">
		<button type="submit" class="btn">Back</button>
	</form>
	<form action="update_client_info.jsp" method="post">
		<button type="submit" class="btn"><span>Update info</span></button>
	</form>
	</div>
</body>
</html>