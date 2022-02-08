<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href="${pageContext.request.contextPath}/form.css" rel="stylesheet" type="text/css">
		<title>Login</title>
	</head>
	<body>
		<div class="container">
			<h2>Login Here!</h2>
			<form action="<%= request.getContextPath() %>/register" method="post" name="formregister">
				<table> 
					<tr>
						<td><h3 style= "color: red">${Errormessage}</h3></td>
					</tr>
					<tr>
						<td><label>User Name:</label></td>
						<td><input onkeydown="return /[a-z]/i.test(event.key)" name="username" id="userName" required></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" id="password" required></td>
					</tr>
				</table>
				<div class="btn-register">
					<button type="submit" name="submit" value="login">login</button>
				</div>
				<div class="btn-register">
					<a href="employeeregister.jsp"> New user click Here! </a>
				</div>
			</form>
		</div>
	</body>
</html>