<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Already a User?</h2>
	<a href="login.jsp">Signin</a>

	<h2>Create an Account</h2>
	
	<form action="usersignup" method="post">
	<!-- userid is auto generated, account number to
	be assigned after the creation of account by the user -->
	Name:<input type="text" name="username"><br>
	Age:<input type="text" name="userage"><br>
	Email:<input type="text" name="useremail"><br>
	Contact:<input type="text" name="usercontact"><br>
	Password:<input type="text" name="userpassword"><br>
	Role:
	
	<select name="role" >
	<option value="manager" >Manager</option>
	<option value="customer" >Customer</option> 
	</select><br>
	
	
	Account Type:
	<select name="accounttype" >
	<option value="current" >Current</option>
	<option value="saving" >Saving</option> 
	</select><br>
	
	
	<input type="submit">
	</form>
	
</body>
</html>