<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<link href="<c:url value="./template/css/style.css" />" rel="stylesheet">
<link href="<c:url value="./template/css/inputTypeBorder.css" />" rel="stylesheet">
<script src="./template/js/registrationValidation.js"></script>

<!-- Fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">


</head>
<body >

	<div class="signup__center">
		<form:form name="signup" action="process-signup" onsubmit="return validateForm()" modelAttribute="registration"
			class="signup__form" id="form">
			<h3>Sign Up</h3>
			<p />

			<div id="nameDiv" class="input-control">
				<form:input path="name" placeholder="Full Name"
					oninput="validateFullName()" id="full_name" required="true" />
				<div class="error" id="nameError"><form:errors path="name" cssStyle="color:#ff3860"></form:errors></div>
			</div>

			<p />
			<div id="emailDiv" class="input-control">
				<form:input path="email" placeholder="email" id="email" name="email"
					oninput="validateEmail()" required="true"/>
				<div class="error" id="emailError"><form:errors path="email" cssStyle="color:#ff3860"></form:errors></div>
			</div>
			<p />

			<div id="numberDiv" class="input-control">
				<form:input path="phone" placeholder="Phone number" id="number"
					name="number" oninput="validateNumber()" required="true"/>
				<div class="error" id="numberError"><form:errors path="phone" cssStyle="color:#ff3860"></form:errors></div>
			</div>
			<p />
			<div id="genderDiv" class="input-control">
				<form:select id="Gender" path="gender" name="Gender"
					oninput="validateGender()" required="true">
					<option value="" disabled selected hidden>Choose Gender</option>
					<form:option value="Male"></form:option>
					<form:option value="Female"></form:option>

				</form:select>
				<div id="genderError" class="error"><form:errors path="gender" cssStyle="color:#ff3860"></form:errors></div>
			</div>


			<p />
			<div id="usertypeDiv" class="input-control">
				<form:select id="UserType" path="role" name="UserType"
					oninput="validateUserType()" required="true">
					<option value="" disabled selected hidden>Choose User Type</option>
					<form:option value="Job Seeker"></form:option>
					<form:option value="Employer"></form:option>

				</form:select>
				<div id="usertypeError" class="error"><form:errors path="role" cssStyle="color:#ff3860"></form:errors></div>
			</div>

			<p />



			<div id="usernameDiv" class="input-control">
				<form:input path="username" id="username" name="username"
					placeholder="Username" oninput="validateUsername()" required="true"/>
				<div id="usernameError" class="error"><form:errors path="username" cssStyle="color:#ff3860"></form:errors></div>
			</div>
			<p />
			<div id="passwordDiv" class="input-control">
				<form:password path="password" id="password" name="password"
					placeholder="Password" oninput="validatePassword()" required="true"/>
				<div id="passwordError" class="error"><form:errors path="password" cssStyle="color:#ff3860"></form:errors></div>
			</div>
			
			
			<form:button type="submit" value="Submit" id="signup" >Sign Up</form:button><br><br><br>
			<p/><p>Already have an account? &nbsp&nbsp&nbsp <a href="/Job_portal_Spring/loginForm">Log in !</a></p>
		</form:form>
		
	</div>

</body>
</html>