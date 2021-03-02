<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
	<form action="change_rate_short_m.jsp" method="post">
		<table>
			<tr>
				<td>Select Duration: </td>
				<td>
					<select name="duration">
						<option selected value="-1">Select Duration</option>
						<option value="30">30 minutes</option>
						<option value="60">1 hour</option>
						<option value="120">2 hours</option>
						<option value="240">4 hours</option>
						<option value="720">12 hours</option>
						<option value="1440">24 hours</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Select Vehicle Type: </td>
				<td>
					<select name="vtype">
						<option selected value="-1">Select type of vehicle</option>
						<option value="2">Two-wheeler</option>
						<option value="3">Three-wheeler</option>
						<option value="4">Four-wheeler</option>
					</select>
				</td>
			</tr>
			<tr>
			<td>Enter New Cost: </td>
			<td><input type="number" name="new_cost"></td>
			</tr>
		</table>
		<button class="btn" type="submit"><span>Click</span></button>
	</form>
	</div>
</body>
</html>