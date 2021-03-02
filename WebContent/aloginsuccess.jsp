<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<h1 class="header-content">Welcome Admin!</h1>
	</div>
	<div class="image-container"></div>
	<div class="main-container">
		<div class="content" align="center">
			<h1 class="content-header">CHANGE SLOT RATES</h1>
			<h3 class="content-info">Change rates of any slot for reservation</h3>
			<form action="change_rate_short.jsp" method="post">
				<button type="submit" class="click-btn">Change rate for short time slots</button>
			</form>
			<form action="change_rate_pass.jsp" method="post">
				<button type="submit" class="click-btn">Change rate for long term passes</button>
			</form>
		</div>
		<div class="content" align="center">
			<h1 class="content-header">MAKE A BOOKING</h1>
			<h3 class="content-info">Book a slot for a client</h3>
			<form action="book_slot_admin.jsp" method="post">
				<table>
					<tr>
						<td>Enter client username:</td>
						<td><input type="text" name="client_username" class="input"></td>
					</tr>
				</table>
				<button type="submit" class="btn"><span>Click</span></button>
			</form>
		</div>
		<div class="content" align="center">
			<h1 class="content-header">VIEW CLIENT INFORMATION</h1>
			<h3 class="content-info">Get user information and details regarding their bookings</h3>
			<form action="view_client_info_admin.jsp" method="post">
				<table>
					<tr>
						<td>Enter client username:</td>
						<td><input type="text" name="client_username" class="input"></td>
					</tr>
				</table>
				<button type="submit" class="btn"><span>Click</span></button>
			</form>
		</div>
	</div>
</body>
</html>