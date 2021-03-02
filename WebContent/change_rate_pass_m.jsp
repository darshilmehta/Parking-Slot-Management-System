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
		<h1 class="header-content">Change Rate of Short Term Slots</h1>
	</div>
	<div class="main-content">
	<%
		int d = Integer.parseInt(request.getParameter("duration"));
		int v = Integer.parseInt(request.getParameter("vtype"));
		int newCost = Integer.parseInt(request.getParameter("newcost"));
		
		String dbUrl = "jdbc:mysql://localhost:3306/demos";
		String dbUsername = "root";
		String dbPassword = "asdf1234";
		String dbDriver = "com.mysql.jdbc.Driver";
		
		Class.forName(dbDriver);
		String query = "SELECT cost FROM regular_pass_cost WHERE duration_in_days='"+d+"' AND vehicle_type='"+v+"'";
		System.out.println(query);
		Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		rs.next();
		double cost = rs.getDouble("cost");
		out.println("Previous cost of this type of slot is: Rs."+cost);
		out.println("<br>");
		out.println("<br>");
		
		String q = "UPDATE regular_pass_cost SET cost='"+newCost+"' WHERE duration_in_days='"+d+"' AND vehicle_type='"+v+"'";
		PreparedStatement p = con.prepareStatement(q);
		int result = p.executeUpdate();
		if(result==1){
			out.println("Cost of this type of slot has been updated to: Rs."+newCost);
			out.println("<br>");
		} else{
			out.println("Some error has occured! Try again later");
			out.println("<br>");
		}
	%>
	</div>
</body>
</html>