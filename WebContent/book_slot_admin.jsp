<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="parking.PassThread.*" %>
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
	<%
		PassThread t = new PassThread();
		Thread obj = new Thread(t);
		obj.start();
	%>
	<div class="header" align="center">
		<h1 class="header-content">Book Slot For Client</h1>
	</div>
	<%
		String username = request.getParameter("client_username");
		HttpSession sess = request.getSession();
		sess.setAttribute("c_username",username);
	%>
		<div align="center" class="main-content">
		<form action="regular-slot-admin" method="post">
			<table>
				<tr>
					<td>Location: </td>
					<td>
						<select name="location" id="location">
							<option selected value="-1">Select your current Location.</option>
							<option value="1">Ghatkopar</option>
							<option value="2">Mulund</option>
							<option value="3">Thane</option>
							<option value="4">Goregaon</option>			
						</select>
					</td>
				</tr>
				<tr>
					<td>Start Time: </td>
					<td><input type="datetime-local" name="start-date"></td>
				</tr>
				<tr>
					<td>Duration:</td>
					<td>
						<select name="duration" id="duration">
							<option selected value="-1">Select a time period.</option>
							<option value="30-min">30-minutes</option>
							<option value="one-hour">1-hour</option>
							<option value="two-hour">2-hours</option>
							<option value="four-hour">4-hours</option>
							<option value="twelve-hour">6-hours</option>
							<option value="one-day">1-day</option>				
						</select>
					</td>
				</tr> 
			</table>
			<input type="submit" value="Enter" class="btn">
		</form>
	</div>
</body>
</html>