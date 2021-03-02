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
	<div align="center" class="header">
		<h1 class="header-content">Book a long-term pass</h1>
	</div>
	<div align="center" class="main-content">
		<form action="regular-pass" method="post">
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
					<td><input type="date" name="start-date"></td>
				</tr>
				<tr>
					<td>Duration:</td>
					<td>
						<select name="duration" id="duration">
							<option selected value="-1">Select a time period.</option>
							<option value="day-fifteen">15-days</option>
							<option value="month-one">1-month</option>
							<option value="month-two">2-month</option>
							<option value="month-four">4-month</option>
							<option value="month-six">6-month</option>
							<option value="month-twelve">12-month</option>				
						</select>
					</td>
				</tr> 
			</table>
			<input type="submit" value="Enter" class="btn">
		</form>
	</div>
</body>
</html>