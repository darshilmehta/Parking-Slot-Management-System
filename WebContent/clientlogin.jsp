<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<!-- Normal client login -->
	<div align="center">
		<h1>Login Page</h1>
		<form action="login" method="post">
			<table>
				<tr><td>User Name: </td><td><input type="text" name="username"></td></tr>
				<tr><td>Password: </td><td><input type="password" name="password"></td></tr>
			</table>
			<input type="submit" value="Login" class="btn">
		</form>
	</div>
<!-- New client register -->
	<div align="center">
		<form action="newlogin.jsp" method="post">
			<table>
				<tr><td><input type="submit" value="New User? Click here!" class="btn"></td></tr>
			</table>
		</form>
	</div>
<!-- Admin login -->
	<div align="center">
		<form action="alogin.jsp" method="post">
			<table>
				<tr><td><input type="submit" value="Admin Login" class="btn"></td></tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>