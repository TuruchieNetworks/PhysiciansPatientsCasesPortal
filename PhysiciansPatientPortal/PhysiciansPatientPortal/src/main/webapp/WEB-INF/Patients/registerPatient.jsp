<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Add New Visit!</title>
</head>
<body class="container-fluid p-4"  style="background:khaki;font-family:cursive;">
	<h1 style="color:azure;border-bottosm: 2px solid aliceblue;font-weight:bold;font-size:32px;background:chocolate;padding:10px;font-family:cursive;text-align:center;">WELCOME TO MELLOW HEALTH!</h1>
	<div class="container" style=" text-align:center;">
		<div class="container">
		<div class="row">
			<div class="col">
	    	<a href="/mellowHealth/patientsPortal/register" style="width: 100%; display:block; padding:10px;color:brown;text-decoration:none;background:rgba(2.11, 24.5, 1.21, 0.9);border-radius:5%;font-weight:bold;font-size:22px;padding:10px;font-family:cursive;text-align:center;">
				<h1 style="color:azure;border-bottom: 2px solid aliceblue;background:rgba(2.11, 24.5, 1.21, 0.9);border-radius:5%;font-weight:bold;font-size:22px;padding:10px;font-family:cursive;text-align:center;">Register New Patient</h1>		
	    	</a>
	    		<form:form action="/mellowHealth/patientsPortal/process/register" method="post" modelAttribute="patient">
				    <div class="form-group">
				        <label style="padding:5px 0;">First Name</label>
				        <form:input path="patientFirstName" class="form-control" placeholder="Please Enter First Name!"/>
				        <form:errors path="patientFirstName" class="text-danger" />
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Last Name</label>
				        <form:input path="patientLastName" class="form-control" placeholder="Please Enter Last Name!"/>
				        <form:errors path="patientLastName" class="text-danger" />
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Email</label>
				        <form:input path="email" class="form-control"  placeholder="Please Enter Email Address!"/>
				        <form:errors path="email" class="text-danger" />
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Password</label>
				        <form:input path="password" class="form-control" placeholder="Please Enter Password!" />
				        <form:errors path="password" class="text-danger" />
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Confirm Password</label>
				        <form:input path="confirmPassword" class="form-control" placeholder="Please Confirm Password!" />
				        <form:errors path="confirmPassword" class="text-danger" />
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Insurance ID</label>
				        <form:input type="number" path="patientInsuranceID" class="form-control" placeholder="Please Enter Insurance ID!" />
				        <form:errors path="patientInsuranceID" class="text-danger" />
				    </div>
					<div class="form-group">
				        <label style="padding:5px 0;">Chief Complaint</label>
					    <div class="form-group">
				        	<form:errors path="currentChiefComplaint" class="text-danger" />
					    </div>
				        <form:textarea path="currentChiefComplaint" class="form-control" placeholder="Please describe reason for this visit!"></form:textarea>
				    </div>
					<div class="form-group">
				        <label style="padding:5px 0;">Past Medical History</label>
					    <div class="form-group">
				        	<form:errors path="patientPastMedicalHistory" class="text-danger" />
					    </div>
				        <form:textarea path="patientPastMedicalHistory" class="form-control" placeholder="Please Enter Past Medical History!"></form:textarea>
				    </div>
					<div class="form-group">
				        <label style="padding:5px 0;">Past Surgical History</label>
					    <div class="form-group">
				        	<form:errors path="patientPastSurgicalHistory" class="text-danger" />
					    </div>
				        <form:textarea path="patientPastSurgicalHistory" class="form-control" placeholder="Please Enter Past Surgical History!"></form:textarea>
				    </div>
					<div class="form-group">
				        <label style="padding:5px 0;">Recent Emergency Administration</label>
					    <div class="form-group">
				        	<form:errors path="patientRecentEmergency" class="text-danger" />
					    </div>
				        <form:textarea path="patientRecentEmergency" class="form-control" placeholder="Please Enter Recent Emergency Visit!"></form:textarea>
				    </div>
					<div class="form-group">
				        <label style="padding:5px 0;">Recent Hospital Administration</label>
					    <div class="form-group">
				        	<form:errors path="patientRecentAdmission" class="text-danger" />
					    </div>
				        <form:textarea path="patientRecentAdmission" class="form-control" placeholder="Please Enter Any Recent Hospitalization!"></form:textarea>
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Date Of Birth</label>
					    <div class="form-group">
					        <form:errors path="dateOfBirth" class="text-danger" />
					    </div>
				        <form:input type="date" path="dateOfBirth" style="cursor:pointer;" class="form-control" placeholder="Please Select Date Of Birth!" />
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Gender</label>	
					    <div class="form-group">
					        <form:errors path="gender" class="text-danger" />
					    </div>	    
				        <form:select path="gender" class="form-control" style="cursor:pointer">
					        <form:option value="" label="-- Select Gender --"/>
					            <form:option value="Male" label="Male"/>
					            <form:option value="Female" label="Female"/>
					            <form:option value="Others" label="Others"/>
					    </form:select>
				    </div>
				    <div class="form-group">
				        <label style="padding:5px 0;">Insurance Information</label>
					    <div class="form-group">
				        	<form:errors path="patientInsuranceID" class="text-danger"/>
					    </div>	 
				        <form:input type="number" path="patientInsuranceID" class="form-control" placeholder="Please Enter Insurance ID!" />
				    </div>
					<div class="form-group">
					    <label style="padding:5px 0;">Race</label>
					    <form:input path="race" class="form-control" placeholder="Please Enter Race!" />
					</div>
					<div class="form-group">
					    <label style="padding:5px 0;">Recreational Substance</label>
					    <div class="form-group">
					    <form:errors path="recreationalSubstance" class="text-danger"/>
					    </div>
					    <form:input path="recreationalSubstance" class="form-control" placeholder="Please Enter Recently Used Recreational Substance!" />
					</div>				
					<div class="form-group">
					    <label style="padding:5px 0;">Recent Travel History</label>
					    <div class="form-group">
					    	<form:errors path="hasTravelledOutsideTheUnitedStatesForMoreThan30Days" class="text-danger" />
					    </div>
					    <form:select path="hasTravelledOutsideTheUnitedStatesForMoreThan30Days" class="form-control" style="cursor:pointer">
					        <form:option value="" label="-- Select Yes/No --"/>
					        <form:option value= "Yes" label="Yes"/>
					        <form:option value="No" label="No"/>
					    </form:select>
					</div>
				    <h1 style="background:rgba(6.8, 0.8, 0.320, 0.9);width:100%; padding: 10px; border-radius:5%;margin:5px 0"><input type="submit" value="Create New Mellow Health Account!" class="btn btn-success" style="margin: 10px 0; width: 100%; padding: 10px;font-size:22px;"/></h1>
				</form:form>
				<a style="background:rgba(6.8, 1.8, 20, 0.9);margin:10px 0;width:100%; display:block; padding: 10px;border-radius:5%;" href="/mellowHealth/patientsPortal/login" class="btn btn-success"><h1 style="color:azure;border-bottom: 2px solid aliceblue;background:rgba(112.11, 24.5, 1, 0.9);border-radius:5%;font-weight:bold;font-size:20px;padding:10px;font-family:cursive;text-align:center;">Already Have A Mellow Health Account?</h1></a>
		 		<h1 style="background:rgba(6.8, 0.8, 2.320, 0.9);width:100%; padding: 10px; border-radius:5%;margin:5px 0" ><a style="background:rgba(48, 0.8, 12.0, 0.9);margin:10px 0;width:100%; display:block; padding: 10px; font-size:22px;" href="/mellowHealth/physicians/login" class="btn btn-success">MELLOW PHYSICIAN ACCESS!</a></h1>
			</div>
		</div>
	</div>
</body>
</html>