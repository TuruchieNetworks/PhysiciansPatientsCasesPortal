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
<link rel="stylesheet" href="/css/styles.css"/>
<!-- Local JavaScript section -->
<script src="<c:url value='/scripts.js'/>"></script>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<meta charset="ISO-8859-1">
<title>Add New Visit!</title>
</head>
<body class="container-fluid p-6"  style="background:khaki;font-family:cursive;">
	<h1 style="color:azure;border-bottosm: 2px solid aliceblue;font-weight:bold;font-size:32px;background:chocolate;padding:10px;font-family:cursive;text-align:center;">WELCOME TO MELLOW HEALTH!</h1>
	<div class="container-fluid p-6" style=" text-align:center;">
		<a style="width: 100%; display:block; padding:0px;color:brown;text-decoration:none;" href="/mellowHealth/physicians/${loggedInPhysician.id }">
			<h1 style="color:azure;border-bottom:2px solid aliceblue;background:rgba(2.11, 24.5, 121, 0.9);border-radius:5%;font-weight:bold;font-size:22px;padding:10px;font-family:cursive;text-align:center;">Register A New Patient Visit Today, <c:out value="${createdAt} Dr. ${loggedInPhysician.firstName}"/>!</h1>
		</a>
		<div class="row">
				<form action="/mellowHealth/hospitalDashboard/patientCases/newPatientCase">
				    <label style="padding:5px 10px">Search Patient Name</label>
					<input type="text" name="searchedPatientName"/>
					<input class="btn btn-outline-primary" type="submit" value="Search Patient"/>
				</form>	
					<p>${matchedPatientFullName.patientFirstName} ${matchedPatientFullName.patientLastName}</p>	
							            
			<div class="col">
				<form:form action="/mellowHealth/hospitalDashboard/process/patientCases/createNewPatientCase" method="POST" modelAttribute="patientCase">
				<!-- Add this block to display global errors -->
				<!--form:errors path="*" cssClass="text-danger"/!-->
					<div class="form-group">
					    <label style="padding:10px 0">Select Registered Patients</label>
						<form:select path="patient.id" class="form-control" style="cursor:pointer" id="patientSelect" onchange="updateSelectedPatient('patientSelect', 'patientName', 'selectedPatientDiv')">
						    <form:option value="" label="-- Select Patient --"/>
						    <c:forEach var="patient" items="${allPatients}">
						        <form:option value="${patient.id}" label="${patient.patientFirstName} ${patient.patientLastName}"/>	
						    </c:forEach>
						</form:select>
					</div>
					<div class="form-group">
					    <label style="padding:5px 0 10px 0">Patient Details</label>
					    <div class="form-group">
					        <form:errors path="patientName" class="text-danger" />
					    </div>
					    <div class="form-group" id="selectedPatientDiv" style="font-weight:bold; margin-top: 5px;"></div>
					    <form:input path="patientName" placeholder="Please Enter Full Name!" style="width:100%; padding:8px; border-radius:5%;" />
					</div>

					<div class="form-group">
				        <label style="padding:5px 0 10px 0">Chief Complaint</label>
					    <div class="form-group">
				        	<form:errors path="chiefComplaint" class="text-danger" />
					    </div>
				        <form:textarea path="chiefComplaint" class="form-control" placeholder="Please enter the chief complaint for this visit!"></form:textarea>
				    </div>

				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Subjective Symptoms</label>
					    <div class="form-group">
				        	<form:errors path="subjectiveSymptoms" class="text-danger" />
					    </div>
				        <form:textarea path="subjectiveSymptoms" class="form-control" placeholder="Please enter subjective symptoms for this visit!"></form:textarea>
				    </div>
				
				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Objective Findings</label>
				        <form:textarea path="objectiveFindings" class="form-control" placeholder="Please enter objective findings for this visit!"></form:textarea>
				    </div>
				
				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Past Medical History</label>
				        <form:textarea path="pastMedicalHistory" class="form-control" placeholder="Please Enter Past Medical History!"></form:textarea>
				    </div>
				
				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Past Surgical History</label>
				        <form:textarea path="pastSurgicalHistory" class="form-control" placeholder="Please enter objective findings for this visit!"></form:textarea>
				    </div>

				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Current Medications</label>
					    <div class="form-group">
				        	<form:errors path="patientMedication" class="text-danger" />
					    </div>
				        <form:textarea path="patientMedication" class="form-control" placeholder="Please enter patient medication for this visit!"></form:textarea>
				    </div>

					<div class="form-group">
					    <label style="padding:10px 0">Select Physician</label>
					    <form:select path="physician.id" class="form-control" style="cursor:pointer;">
					        <form:option value="" label="-- Select Physician --" />
					        <c:forEach var="physician" items="${allPhysicians}">
					            <form:option value="${physician.id}" label="Dr. ${physician.firstName} ${physician.lastName}" />
					        </c:forEach>
					    </form:select>
					    <form:errors path="physician.id" class="text-danger" />
					</div>

				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Differential Diagnosis</label>
					    <div class="form-group">
				        	<form:errors path="differentialDiagnosis" class="text-danger" />
					    </div>
				        <form:textarea path="differentialDiagnosis" class="form-control" placeholder="Please enter differential diagnosis for this visit!"></form:textarea>
				    </div>


				    <div class="form-group">
				        <label style="padding:5px 0 10px 0">Follow-Up Consultation</label>
					    <div class="form-group">
				        	<form:errors path="followUpConsultation" class="text-danger" />
					    </div>
				        <form:textarea path="followUpConsultation" class="form-control" placeholder="Please enter follow-up consultation details for this visit!"></form:textarea>
				    </div>

		            <div class="form-group">
		                <label style="padding:10px 0">Treatment Plan</label>
					    <div class="form-group">
				        	<form:errors path="treatmentPlan" class="text-danger" />
					    </div>
		                <form:textarea path="treatmentPlan" class="form-control" rows="5" maxlength="500" placeholder="Please tell us something about this patientCase!"></form:textarea>
		            </div>
				    <input type="submit" value="Add New Case" class="btn btn-success" style="margin: 10px 0; width: 100%; padding: 10px;"/>
				</form:form>
				<h1 style="width:100%;"><a style=" margin:10px 0;width:100%;display:block; padding:10px" href="/mellowHealth/physicians" class="btn btn-warning">CANCEL!</a></h1>
			</div>
		</div>
	</div>
	
</body>
</html>