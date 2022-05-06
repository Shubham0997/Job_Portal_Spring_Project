<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job List</title>



<link href="<c:url value="./template/css/tablecss.css" />" rel="stylesheet">
<link href="<c:url value="./template/css/postJob.css" />" rel="stylesheet">
<link href="<c:url value="./template/css/navbar.css" />" rel="stylesheet">

<!-- Fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">

</head>
<body>
	
 <!--Navbar begins-->
  <div class="topnav">
    <a href="/Job_portal_Spring/welcome">Home</a>
    
<sec:authorize access = 'hasAuthority("Employer")'>
<a href = "/Job_portal_Spring/postJob">Post Job Openings</a>
</sec:authorize>

<sec:authorize access = 'hasAuthority("Employer")'>
<a  href = "/Job_portal_Spring/viewYourPostings">View your Job Postings</a>
</sec:authorize>


<sec:authorize access = 'hasAuthority("Job Seeker")'>
<a  class="active"href = "/Job_portal_Spring/joblist">Search Jobs</a>
</sec:authorize>

<sec:authorize access = 'hasAuthority("Job Seeker")'>
<a href = "/Job_portal_Spring/myAppliedJobs">My Aplications</a>
</sec:authorize>
    
    <form:form action = "logout" method = "POST">
	<input type = "submit" value = "logout" class="logoutButton">
</form:form> 
    
  </div>
   <!--Navbar ends-->



<main>
  <table>
    <thead>
      <tr>
        <th>
          Job Title
        </th>
        <th>
         Company Name
        </th>
        <th>
          Job Description
         </th>
                 <th>
          Employer's Email
         </th>
        <th>
          Action
        </th>
      </tr>
    </thead>
    
    <tbody>
    
    
    <c:forEach items="${listJobs}" var="jobs">
      <tr>
        <td data-title='Provider Name'>
         ${jobs.jobName}
        </td>
        <td data-title='E-mail'>
          ${jobs.companyName}
        </td>
        <td data-title='E-mail'>
        ${jobs.description}
        </td>
          <td data-title='E-mail'>
        ${jobs.employerEmail}
        </td>
        <td class='select'>
         <sec:authorize access='hasAuthority("Job Seeker")'>
				
					<a class="button" href="/Job_portal_Spring/applyJob?username=${username}&email=${email}&id=${jobs.jobid}&jobName=${jobs.jobName}">Apply for Job</a>
				

				</sec:authorize>
        </td>
      </tr>
     </c:forEach>
     
     
    </tbody>
  </table>
 
</main>



</body>
</html>