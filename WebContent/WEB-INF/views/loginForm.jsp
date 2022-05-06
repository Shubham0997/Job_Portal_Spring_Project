<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<link href="<c:url value="./template/css/style.css" />" rel="stylesheet">
<link href="<c:url value="./template/css/inputTypeBorder.css" />"
	rel="stylesheet">
<!-- Fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">

</head>
<body>



	<!-- Login form-->
	<div class="form__center">
		<form:form name="signin" action="process-login" method="post">
			<h3>Log In here</h3>
			<div id="usernameDiv" class="input-control">
				<input type="text" id="username" name="username"
					placeholder="Username" required>
			</div>
			<div id="passwordDiv" class="input-control">
				<input type="password" id="password" name="password"
					placeholder="Password" required>
			</div>

			<div class="input-control">
				<button type="submit" value="submit">Log in</button>
				<!--Authenticate user using username and password-->
				<br>
				<div id="submitError" class="error">
					<c:if test="${param.error != null}">
						<i style="color: red">Invalid username or password</i>
					</c:if>
					<c:if test="${param.logout != null}">
						<i style="color: red">You are logged out</i>
					</c:if>
				</div>
			</div>
			<br>
			<br>
			<br>
			<p>
				Don't have an account? &nbsp&nbsp&nbsp<a href="/Job_portal_Spring">Sign
					Up !</a>
			</p>
		</form:form>
	</div>
	<p />


</body>
</html>