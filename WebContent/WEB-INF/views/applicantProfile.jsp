<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Applicant Profile</title>

    <!--bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <!-- Fonts-->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">

    <!--External CSS-->
    <link href="<c:url value="./template/css/commonProfile.css" />" rel="stylesheet">
    <link href="<c:url value="./template/css/navbar.css" />" rel="stylesheet">

<style>

    </style>

</head>




<body  style=" place-items: center;">

  <!--Navbar begins-->
  <div class="topnav">
    <a  href="/Job_portal_Spring/welcome">Home</a>
    
<sec:authorize access = 'hasAuthority("Employer")'>
<a href = "/Job_portal_Spring/postJob">Post Job Openings</a>
</sec:authorize>

<sec:authorize access = 'hasAuthority("Employer")'>
<a class="active" href = "/Job_portal_Spring/viewYourPostings">View your Job Postings</a>
</sec:authorize>


<sec:authorize access = 'hasAuthority("Job Seeker")'>
<a href = "/Job_portal_Spring/joblist?username=${userBean.username}&email=${userBean.email}">Search Jobs</a>
</sec:authorize>
    
    <form:form action = "logout" method = "POST">
	<input type = "submit" value = "logout" class="logoutButton">
</form:form> 
    
  </div>
   <!--Navbar ends-->

<!--profile Card-->
  <div class="profileCard__center">
    <div class="profileCard"
      id="home">
      <h3>Profile</h3><br>
      <output>Name : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</output><output>
       ${userBean.name}
      </output><br>
      <output>Username : &nbsp;&nbsp;&nbsp;&nbsp;</output><output>
		${userBean.username}
      </output><br>
          <output>Role : &nbsp;&nbsp;&nbsp;&nbsp;</output><output>
     ${userBean.role}
      </output><br>
      <output>Gender : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</output><output>
        ${userBean.gender }
      </output><br>
      <output>Number : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</output><output>
       ${userBean.phone}
      </output><br>
      <output>Email : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</output><output>
        ${userBean.email}
      </output><br>
    </div>
  </div>
</body>
</html>