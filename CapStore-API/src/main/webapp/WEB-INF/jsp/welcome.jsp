<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
img {
    opacity: 0.65;
   
}
.centered {
    position: absolute;
    top: 10%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: black;
    
}
button1:hover {
    opacity: 0.8;
}
button2:hover {
    opacity: 0.8;
}
button3:hover {
    opacity: 0.8;
}
.signmerchant{
position: absolute;
    top: 10%;
    left: 50%;
    display: inline-block;
    transform: translate(-50%, -50%);
    padding: 10px 20px;
    color: black;
    
}

.signcustomer{
position: absolute;
    top: 10%;
    left: 80%;
    display: inline-block;
    transform: translate(-50%, -50%);
    padding: 10px 20px;
    color: black;
    
}

.button1 {
    background-color: gray
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 2px 2px;
    cursor: pointer;
    position: absolute;
    top: 40%;
    left: 20%;
    transform: translate(-50%, -50%);
    color: black;
  }
.button2 {
    background-color: gray
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 2px 2px;
    cursor: pointer;
    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: black;
}
.button3 {
    background-color: gray
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    margin: 2px 2px;
    cursor: pointer;
    position: absolute;
    top: 40%;
    left: 80%;
    transform: translate(-50%, -50%);
    color: black;
}
</style>
<body>
<img src="../images/welcomeimg.jpg" alt="Snow" style="width:100%;"/>
	
	<a href="/MerchantsignUpPage" class="signmerchant" >New User? Merchant Sign Up</a>
	<a href="/CustomersignUpPage" class="signcustomer" >New User? Customer Sign Up</a>
	
	<form action="/adminlogin">
	<input type="submit" class="button1" value="Administrator Login"></form>
	<form action="/merchantlogin">
	<input type="submit" class="button2" value="Merchant Login"></form>
	<form action="/customerlogin">
	<input type="submit" class="button3" value="Customer Login">
	</form>
</body>
</html>