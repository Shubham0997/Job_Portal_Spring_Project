
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Job</title>


<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Fonts-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">


   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>



<link href="<c:url value="./template/css/navbar.css" />" rel="stylesheet">
    <link href="<c:url value="./template/css/postJob.css" />" rel="stylesheet">
</head>
<body>



<body  style=" place-items: center;">



 <!--Navbar begins-->
  <div class="topnav">
    <a  href="/Job_portal_Spring/welcome">Home</a>
    
<sec:authorize access = 'hasAuthority("Employer")'>
<a  href = "/Job_portal_Spring/postJob">Post Job Openings</a>
</sec:authorize>

<sec:authorize access = 'hasAuthority("Employer")'>
<a class="active" href = "/Job_portal_Spring/viewYourPostings">View your Job Postings</a>
</sec:authorize>


    
    <form:form action = "logout" method = "POST">
	<input type = "submit" value = "logout" class="logoutButton">
</form:form> 
    
    
  </div>
   <!--Navbar ends-->


  
   <!--Navbar ends-->
<div class="signup__center">
		<form:form name="postJob" action="process-jobUpdating"  modelAttribute="postedJobs" method="POST" class="formPost" id="form">
			<h3>Update Job</h3>
			<p />
	
	<form:hidden path="jobid" value=" ${postedJobs.jobid}" />
								
	
			
			<div id="nameDiv" class="input-control">
				<form:input path="JobName" placeholder="Job title" required="true" value="${postedJobs.jobName}"/>
			</div>

			<div id="nameDiv" class="input-control">
				<form:input path="CompanyName" placeholder="Company Name"
					  required="true" value="${postedJobs.companyName}"/>
				
			</div>
			
						<div id="nameDiv" class="input-control">
				<form:textarea path="Description" rows = "10" cols = "30" required="true" placeholder="Description" value=" ${postedJobs.description }"></form:textarea>
			
			</div>

			
			
			<button style="color:#080710" type="submit" value="Submit" id="signup" >Update Job</button><br><br><br>
			
		</form:form>
		
	</div>


</body>
</html>