<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookMySlot.com</title>
<link rel="icon" href="https://nameecho.com/images/blog/61.jpg" type="image">
<link rel="stylesheet" href="login.css">
</head>
<body>
<div class="header">
	<h1 class="website-name">BookMySlot.com</h1>
	<h5 class="website-tagline">We make your vehicle feel like home...</h5>
</div>
	<div align="center" class="container">
		<h1>Update Client Info</h1>
			<form action="update" method="post">
				<table>
					<tr><td>Reset Password:</td><td><input type="password" name="pass"></td></tr>
					<tr><td>Vehicle No:</td><td><input type="text" name="vno"></td></tr>
					<tr>
						<td>Vehicle Type:</td>
						<td>
							<select name="vtype" id="vtype">
								<option selected value="-1">Select vehicle type</option>
								<option value="2">2-wheeler</option>
								<option value="3">3-wheeler</option>	
								<option value="4">4-wheeler</option>
							</select>
						</td>
					</tr>
				</table>
			<input type="submit" value="Update!" class="og-btn">
		</form>
	</div>
</body>
</html>