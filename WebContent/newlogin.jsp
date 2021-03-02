<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookMySlot.com</title>
<link rel="icon" href="https://nameecho.com/images/blog/61.jpg" type="image">
<link rel="stylesheet" href="login.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
</head>
<body>
<div class="header">
	<h1 class="website-name">BookMySlot.com</h1>
	<h5 class="website-tagline">We make your vehicle feel like home...</h5>
</div>
<div class="container">
	<div align="center">
		<h1>Register Here</h1>
		<form action="register" method="post">
			<table>
				<tr><td>First Name:</td><td><input type="text" name="fname"></td></tr>
				<tr><td>Last Name:</td><td><input type="text" name="lname"></td></tr>
				<tr><td>Set UserName:</td><td><input type="text" name="user"></td></tr>
				<tr><td>Set Password:</td><td><input type="password" name="pass"></td></tr>
				<tr><td>Email ID:</td><td><input type="text" name="email"></td></tr>
				<tr><td>Vehicle No:</td><td><input type="text" name="vno"></td></tr>
				<tr>
					<td>Vehicle Type:</td>
					<td>
						<select name="vtype" id="vtype">
							<option selected value="-1">Select vehicle type.</option>
							<option value="2">2-wheeler</option>
							<option value="3">3-wheeler</option>	
							<option value="4">4-wheeler</option>
						</select>
					</td>
				</tr>
				<tr><td>Contact No:</td><td><input type="text" name="cno"></td></tr>
			</table>
			<input type="submit" value="Register!" class="btn">
		</form>
	</div>
	<div align="center">
		<form action="clientlogin.jsp">
			<input type="submit" value="Already a user?" class="btn">
		</form>
	</div>
</div>
</body>
</html>