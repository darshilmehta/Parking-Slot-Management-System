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
	<div class="header" align="center">
		<h1 class="header-content">Welcome, ${username}!</h1>
	</div>
	<div class="image-container"></div>
	<div class="main-container">
		<div class="content" align="center">
			<h1 class="content-header">PERSONAL INFORMATION</h1>
			<h3 class="content-info">View or update your personal information</h3>
			<form action="personal_info.jsp" method="post">
				<button type="submit" class="btn"><span>Click</span></button>
			</form>
		</div>
		<div class="content" align="center">
			<h1 class="content-header">BOOK A SLOT</h1>
			<h3 class="content-info">For small period of time</h3>
			<form action="book_slot_client.jsp" method="post">
				<button type="submit" class="btn"><span>Book Here</span></button>
			</form>
			<h3 class="content-info">For 15 days and above</h3>
			<form action="book_pass_client.jsp" method="post">
				<button type="submit" class="btn"><span>Book Here</span></button>
			</form>
		</div>
		<div class="content" align="center">
			<h1 class="content-header">MY ONGOING BOOKINGS</h1>
			<h3 class="content-info">View your current bookings</h3>
			<form action="current_booking_short.jsp" method="post">
				<button type="submit" class="click-btn">Click Here (Short Term Pass)</button>
			</form>
			<form action="current_booking.jsp" method="post">
				<button type="submit" class="click-btn">Click Here (Long Term Pass)</button>
			</form>
		</div>
	</div>
</body>
</html>