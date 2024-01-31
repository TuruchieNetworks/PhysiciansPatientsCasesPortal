<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <!-- Your own local CSS -->
    <link rel="stylesheet" href="/css/main.css"/>
    <!-- Bootstrap JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="ISO-8859-1">
    <title>Mellow Health Portal!</title>
</head>
<body class="container-fluid p-8" style="
    <c:choose>
        <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
            background: rgba(1, 10.64, 60, 0.9);
        </c:when>
        <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
            background: rgba(11.3, 330.2, 360, 0.9);
        </c:when>
        <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
            background: rgba(1, 10.64, 60, 0.9);
        </c:when>
        <c:otherwise>
            background: rgba(0.21, 0.180, 25, 0.9); background: rgba(0.531, 0.64, 16, 0.9); 
        </c:otherwise>
    </c:choose>
    font-family: cursive;">
    <c:choose>
        <c:when test="${onePatient.id == loggedInPatient.id and onePatient.patientCases.size() > 1}">
            <h1 class="text-center border-bottom border-2" style="margin-top:5px;border-radius:5%;background: rgba(0.21, 0.180, 25, 0.9);color: brown; font-family: fantasy;font-size:28px;">
                <a class="text-decoration-none text-brown" href="/mellowHealth/patientsPortal/patients/${onePatient.id}">
                    Welcome To The Mellow Health Patients Portal<c:out value="${onePatient.patientFirstName}"/> : You Have <c:out value="${loggedInPatient.patientCases.size()} "/> Physician Visits ${currentDate}!
                </a>
            </h1>
        </c:when>
        <c:when test="${onePatient.id == loggedInPatient.id and onePatient.patientCases.size() == 1}">
            <h1 class="text-center border-bottom border-2" style="margin-top:5px;border-radius:5%;background: rgba(0.21, 0.180, 25, 0.9);color: khaki; font-family: fantasy;font-size:28px;">
                <a class="text-decoration-none text-brown" href="/mellowHealth/patientsPortal/patients/${loggedInPatient.id}">
                    Welcome To The Mellow Health Patients Portal<c:out value="${onePatient.patientFirstName}"/> : You Have Only <c:out value="${loggedInPatient.patientCases.size()} "/> Physician Visits ${currentDate}!
                </a>
            </h1>
        </c:when>
        <c:otherwise>
            <h1 class="text-center border-bottom border-2" style="margin-top:5px;border-radius:5%;background: rgba(0.21, 0.180, 25, 0.9);color:brown; font-family: fantasy;font-size:28px">
                <a class="text-decoration-none text-brown" href="/mellowHealth/hospitalDashboard/patientCases/newPatientCase">
                    Welcome To The Mellow Health Patients Portal <c:out value="${loggedInPatient.patientFirstName}"/> <c:out value="${loggedInPatient.patientLastName}"/>: Please Schedule A New Physicians Visit Today ${currentDate}!
                </a>
            </h1>
        </c:otherwise>
    </c:choose>

    <table class="table table-dark text-center" style="border-radius: 5%;">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Patient</th>
            <th scope="col">Gender</th>
            <th scope="col">Race</th>
            <th scope="col">Travel History</th>
			<c:choose>
			<c:when test="${onePatient.id == loggedInPatient.id}">
            	<th scope="col">Mellow Health Visits</th>
            </c:when>
            <c:otherwise>
            	<th scope="col">Mellow Physician Visits</th>
            </c:otherwise>
            </c:choose>
        </tr>
        </thead>
		<tbody>
		    <tr style="text-decoration:none; color:aqua">
		        <td><c:out value="${onePatient.id}" /></td>
		        <td>
		        	<a href="/mellowHealth/patientsPortal/patients/${onePatient.id}" style="text-decoration:none; color:aqua">
		        		<c:out value="${onePatient.patientFirstName}"/> <c:out value="${onePatient.patientLastName}"/>
		        	</a>
		        </td>
		        <td>
				    <a href="/mellowHealth/patientsPortal/patients/${onePatient.id}" style="text-decoration:none;
						<c:choose>
						<c:when test="${onePatient.gender == 'Male'}">
							color:silk;
						</c:when>
						<c:when test="${onePatient.gender == 'Female'}">
							color:pink;
						</c:when>
				        <c:otherwise>
			                color: rgb(21, 180, 255); color:pink;background: rgba(133, 64, 60, 0.9); 
		                </c:otherwise>
		           		</c:choose>
						">
						<c:out value="${onePatient.gender}"/>
					</a>
				</td>
		        <td>
	            	<a href="/mellowHealth/patientsPortal/patients" style="text-decoration:none;
		            	<c:choose>
			            <c:when test="${onePatient.gender == 'Female' || onePatient.race == 'Black'}">
			                color: rgb(312, 280, 31.5);
		                </c:when>
		                <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			                color: rgb(412, 580, 515);
		                </c:when>
		                <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
		                    color: rgb(412, 580, 515);
		                </c:when>
		                <c:otherwise>
		                   background: rgba(133, 64, 60, 0.9); 
		                </c:otherwise>
		           		</c:choose>
           		"><c:out value="${onePatient.race}" /></a>
				</td>
				<td>
				    <a href="/mellowHealth/patientsPortal/patients/${onePatient.id}" class="text-decoration:none;
						<c:choose>
						<c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true }">
							btn btn-outline-danger
						</c:when>
							<c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false }">
							btn btn-outline-warning
						</c:when>
				        <c:otherwise>
			                btn btn-outline-warning
		                </c:otherwise>
		           		</c:choose>
						">
						<c:choose>
							<c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true }">
					           Recently Travelled
				            </c:when>
					        <c:otherwise>
					           Not Travelled
					        </c:otherwise>
				        </c:choose>
				     </a>
				</td>
		        <c:choose>
				<c:when test="${onePatient.id == loggedInPatient.id}">
				<td class="table-dark">
					<a class= "
						<c:choose>
					        <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
				            	btn btn-warning
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			                	btn btn-primary
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
				            	btn btn-danger
				            </c:when>
					        <c:otherwise>
			 	            	btn btn-primary  
			                </c:otherwise>
						</c:choose> 
		    			"	href="/mellowHealth/patientsPortal/patients" style="text-decoration:none; 
						<c:choose>
			                <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
				                color: rgb(412, 580, 515);  background: rgba(17, 64, 130, 0.9); 
			                </c:when>
			                <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
				                color: rgb(412, 580, 515);  background: rgba(17, 64, 130, 0.9); 
			                </c:when>
			                <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
				                color: rgb(412, 580, 515);  background: rgba(17, 104, 130, 0.9); 
			                </c:when>
			                <c:otherwise>
			                    color: rgb(21, 180, 255); color:pink;background: rgba(133, 64, 60, 0.9); 
			                </c:otherwise>
		           		</c:choose>
		           			padding:5px; border-radius:5%;height:100%;" class="
		           		<c:choose>
			                <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
				            	btn btn-warning
			                </c:when>
			                <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
				            	btn btn-primary
			                </c:when>
			                <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
				            	btn btn-danger
			                </c:when>
			                <c:otherwise>
			                   	btn btn-primary  
			                </c:otherwise>
		           		</c:choose>	
		                ">
		                <div class= "btn btn-outline-primary">
			                <p style="text-decoration:none;">
				                <c:choose>
			                		<c:when test="${onePatient.patientCases.size() < 2}">
				                		<c:out value="${onePatient.patientCases.size()} Mellow Health Visit"/>
							    	</c:when>
			                		<c:otherwise>
				                		<c:out value="${onePatient.patientCases.size()} Mellow Health Visits"/>
							    	</c:otherwise>
							    </c:choose>
				                <c:choose>
			                		<c:when test="${onePatient.physiciansPatients.size() < 2}">
							    		<c:out value="- Following ${onePatient.physiciansPatients.size()} Physician"/>
							    	</c:when>
							    	<c:otherwise>
							    		<c:out value="- Following ${onePatient.physiciansPatients.size()} Physicians"/>
							    	</c:otherwise>
							    </c:choose>
						    </p>
			                <p class= "btn btn-outline-warning" style="text-decoration:none;">
			                	<c:out value="Mellow Health Patient Since ${createdAt}!"/>
			                </p>
		                </div>
		           	</a>
       			  </td>
		 		  </c:when>
				  <c:otherwise>
					  	<td class="table-dark">
					  		<a class= "
			               		<c:choose>
				                    <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						            	btn btn-warning
				                    </c:when>
				                    <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						            	btn btn-primary
				                    </c:when>
				                    <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						            	btn btn-danger
				                    </c:when>
				                    <c:otherwise>
				                    	btn btn-primary 
				                    </c:otherwise>
				               	 </c:choose>	"
				               			href="/mellowHealth/patientsPortal/patients/${onePatient.id}" style="text-decoration:none; 
								 <c:choose>
				                    <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						                color: rgb(412, 580, 515);  background: rgba(17, 64, 130, 0.9); 
				                    </c:when>
				                    <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						                color: rgb(412, 580, 515);  background: rgba(17, 64, 130, 0.9); 
				                    </c:when>
				                    <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						                color: rgb(412, 580, 515);  background: rgba(17, 104, 130, 0.9); 
				                    </c:when>
				                    <c:otherwise>
				                        color: rgb(21, 180, 255); color:pink;background: rgba(133, 64, 60, 0.9); 
				                    </c:otherwise>
				               	  </c:choose>
			               			padding:15px; border-radius:5%; width:100%; height:100%;">
					               	<p style="text-decoration:none;">
		                				<c:out value="${onePatient.patientCases.size()} Mellow Health Visits"/>- <c:out value="Following ${onePatient.physiciansPatients.size()} Physicians"/>
					               		<c:out value="${onePatient.currentChiefComplaint}"/>
							       </p>
			               		</a>
								</c:otherwise>
	            			</c:choose>
	            		</td>
				    </tr>
					</tbody>
					</table>
				<c:choose>
				<c:when test="${onePatient.patientCases.size() < 1}">
				<h1 style="width: 100%">
					<a style="
			    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(13, 114, 160, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
				             </c:when>
				             <c:otherwise>
				                  color: rgb(21, 0.180, 255); color:pink;
				             </c:otherwise>
			             </c:choose>
		              	  margin:10px 0; width: 100%; display:block; padding: 10px"  href="/mellowHealth/hospitalDashboard/patientCases/newPatientCase" class="btn btn-danger">YOU HAVE NOT YET SCHEDULED ANY VISIT, CLICK HERE TO SCHEDULE NEW VISIT!
		              </a>
		          </h1>
				</c:when>
				<c:otherwise>
					<h1 style="width: 100%">
						<a style="
				    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(13, 0.114, 0.160, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
				             </c:when>
				             <c:otherwise>
				                  color: rgb(21, 180, 255); color:pink;background: rgba(13, 114, 160, 0.9); background: rgba(0.531, 0.64, 16, 0.9); 
				             </c:otherwise>
				             </c:choose> 
			             	margin:10px 0; width: 100%; display:block; padding: 10px"  href="/mellowHealth/patientsPortal/patients" class="btn btn-success">
			             	
			             	<c:choose>
			             		<c:when test="${onePatient.patientCases.size() < 2}">
			             			<c:out value="${onePatient.patientCases.size()}"/> MELLOW PHYSICIAN VISIT
			             		</c:when>
			             		<c:otherwise>
			             			<c:out value="${onePatient.patientCases.size()}"/> MELLOW PHYSICIAN VISIT
			             		</c:otherwise>
			             	</c:choose>			             	
			             	<c:choose>
			             		<c:when test="${onePatient.physiciansPatients.size() < 2}">
			             			<c:out value="- FOLLOWING: ${onePatient.physiciansPatients.size()}"/> PRIVATE PHYISICIAN!
			             		</c:when>
			             		<c:otherwise>
			             			<c:out value="- FOLLOWING: ${onePatient.physiciansPatients.size()}"/> PRIVATE PHYISICIANS!
			             		</c:otherwise>
			             	</c:choose>
			             </a>
			         </h1>
				 </c:otherwise>
			     </c:choose>

	    	<div class="form-group"style="display:flex; justify-content:space-between;align-items:center;
				<c:choose>
	              <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			           color: rgb(412, 580, 515);  background: rgba(17, 64, 130, 0.9); 
	              </c:when>
	              <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			          color: rgb(412, 580, 515);  background: rgba(17, 0.64, 130, 0.9); 
	              </c:when>
	              <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
			          color: rgb(412, 580, 51);  background: rgba(11, 34, 73, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255); color:pink;background: rgba(133, 64, 60, 0.9); 
	             </c:otherwise>
	             </c:choose>
	             border-radius:5%;padding:5px;">
				    <a href="/mellowHealth/patientsPortal/patients" style="text-decoration:none;text-align:center; width:100%;">
				    	<h1 style="font-family:fantasy;font-size:24px;background:rgba(18, 12, 11, 0.7);border-radius:5%;padding:5px;">${onePatient.patientFirstName} Mellow Health Details: <c:out value="${onePatient.patientCases.size()} Patient Cases- Primary Physicians: ${onePatient.physiciansPatients.size()}, ${currentDate}"/>
							<form action="/mellowHealth/patientsPortal/patients/edit/${loggedInPatient.id}" method="get" style="margin:10px 0; ">
							    <input type="hidden" name="_method" value="edit">
							    <input class ="btn btn-warning" type="submit" value="Update Profile" style=" margin:10px 0; width: 100%; display:block; padding: 10px" >
							</form>
							<form action="/mellowHealth/patientsPortal/patients/delete/${loggedInPatient.id}" method="post">
							    <input type="hidden" name="_method" value="delete">
								<input class ="btn btn-danger" type="submit" value="Delete Profile" style=" margin:10px 0; width: 100%; display:block; padding: 10px">
							</form>
				    	</h1>
				    </a>
				   </div>

				<table class="table table-dark text-center" style="border-radius: 5%;margin-top:5px;">
		        <thead>
		        <tr>
		            <th scope="col">Patient Visit</th>
		            <th scope="col">Follow Up Details</th>
		        </tr>
		        </thead>
		        </table>
			<c:forEach items="${onePatient.patientCases}" var="patientCase">
	    	<div class="form-group" style="display:flex;justify-content:space-between;align-items:center;padding:10px;margin:10px;padding:10px;
				<c:choose>
	              <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			           color: rgb(512, 0.80, 191.5);  background: rgba(7, 0.64, 13.0, 0.9); 
	              </c:when>
	              <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			          color: rgb(92, 380, 15.15); background: rgba(17, 0.64, 130, 0.9); 
	              </c:when>
	              <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
			          color: rgb(114.12, 580, 51);  background: rgba(1.1, 34, 73, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255); color:pink;background: rgba(133, 64, 0.60, 0.9); 
	             </c:otherwise>
	             </c:choose>
	             border-radius:5%;padding:5px;">
	             <div class="form-group btn btn-outline-warning" style="width:100%;margin:5px 10px">Chief Complaint:
				    <a href="/mellowHealth/hospitalDashboard/patientCases/${patientCase.id}" style="text-decoration:none;color: rgb(412, 280, 5.15);text-align:center;">
					    <p class="btn btn-outline-danger" style="text-align:center;">
			      			<c:out value="${patientCase.chiefComplaint}"/>
			      		</p>
		      		</a>
		             <div class="">
					    <p class="btn btn-outline-warning"  style="text-align:center;background:rgba(0.1, 0.2, 134, 0.9);">Treatment Plan: 
					    	<a href="/mellowHealth/hospitalDashboard/patientCases/${patientCase.id}" style="text-decoration:none;color: rgb(412, 280, 5.15);text-align:center;">
			      				<c:out value="${patientCase.treatmentPlan}- ${patientCase.objectiveFindings}"/>
			      			</a>
			      		</p>
			      		<p>
					    	<a class="btn btn-outline-danger" href="/mellowHealth/hospitalDashboard/patientCases/${patientCase.id}" style="padding:5px;border-radius:5%;text-decoration:none;color: rgb(412, 280, 5.15);text-align:center;background:rgba(0.1, 0.1, 12, 0.9);width:100%;">
					    		<c:out value="${createdAt} Visit"/>
					    	</a>
					    </p>
		      	</div>
		      	</div>
	             <div class="btn btn-outline-warning" style="width:60%;margin:5px 10px;">
				    <p style="text-align:center;background: rgba(13, 64, 60, 0.9); padding:5px; border-radius:5%;">
					    <a href="/mellowHealth/hospitalDashboard/patientCases/${patientCase.id}" style="text-decoration:none; color:khaki;text-align:center;">
					    	Follow Up: <c:out value="${patientCase.followUpConsultation}"/>
					    </a>
				    </p>
				    
		             <div class="d-flex justify-content-around" style="
		        		<c:choose>
			            <c:when test="${patientCase.patient.gender == 'Male' || patientCase.patient.race == 'Black'}">
					         color: rgb(412, 580, 515);
			            </c:when>
			            <c:when test="${patientCase.patient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || patientCase.patient.patientCases.size() % 2 == 1}">
					         color: rgb(412, 580, 515); 
			            </c:when>
			            <c:when test="${patientCase.patient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || onePatient.patientCases.size() % 2 == 0}">
					     	 color: rgb(412, 580, 515);
			             </c:when>
			             <c:otherwise>
			                  color: rgb(21, 180, 255); color:pink;
			             </c:otherwise>
			             </c:choose>">
					    <p class="btn btn-outline-warning"  style="text-align:center;background:rgba(0.1, 0.2, 134, 0.9);">Physician In Care:
					    	<a href="/mellowHealth/hospitalDashboard/patientCases/${patientCase.id}" style="text-decoration:none;color: rgb(412, 280, 5.15);text-align:center;width:100%;">
			      				<c:out value="Dr. ${patientCase.physician.firstName} ${patientCase.physician.lastName}"/>
			      			</a>
			      		</p>
			      	</div>
			    		<p style="
				    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(1.3, 0.64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(0.13, 0.114, 16, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || onePatient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
				             </c:when>
				             <c:otherwise>
				                  color: rgb(21, 180, 255); color:pink;
				             </c:otherwise>
				             </c:choose>
				             border-radius:5%;padding:5px;font-weight:bold;"><a href="/mellowHealth/hospitalDashboard/patientCases/${patientCase.id}" style="text-decoration:none; 
				    			<c:choose>
				                    <c:when test="${onePatient.race.contains('AM')}">
						                color: rgb(412, 580, 515);
				                    </c:when>
				                    <c:otherwise>
				                        color: rgb(211, 180, 255);
				                    </c:otherwise>
				                </c:choose>
				             text-align:center;"><c:out value="${onePatient.race}:"/>
				            </a>
				        </p>
			    		<p style="
				    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(13, 0.64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(13, 1.14, 16, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
				             </c:when>
				             <c:otherwise>
				                  color: rgb(21, 180, 255); color:pink;
				             </c:otherwise>
				             </c:choose>
				             border-radius:5%;padding:5px;font-weight:bold;">
				             <a href="/mellowHealth/patientsPortal/patients/${onePatient.id}" style="text-decoration:none; 
				    			<c:choose>
				                    <c:when test="${onePatient.race.contains('AM')}">
						                color: rgb(412, 580, 515);
				                    </c:when>
				                    <c:otherwise>
				                        color: rgb(211, 180, 255);
				                    </c:otherwise>
				                </c:choose>
				                text-align:center;text-decoration:none;			        
				    			<c:choose>
						            <c:when test="${course.price lt 20}">
						                color:rgb(11, 180, 245);color:pink;
						            </c:when>
						            <c:otherwise>
						                color: rgb(111, 180, 255);
						            </c:otherwise>
						        </c:choose>
						        text-align:center; font-weight:bold;">
						        <c:out value="${onePatient.gender}" />
						    </a>
					    </p>
					</div>
    		</div>	
    </c:forEach>
	<div class="form-group"style="
	    		<c:choose>
	            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			         color: rgb(412, 580, 515);background: rgba(13, 0.64, 60, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			         color: rgb(412, 580, 515); background: rgba(0.130, 0.114, 0.160, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || onePatient.patientCases.size() % 2 == 0}">
			     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255);background: rgba(78, 10, 80, 0.9); background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:otherwise>
	             </c:choose>display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0">
		<h1 style=" margin: 0px 15px 0 0; width: 100%">
			<a href="/mellowHealth/hospitalDashboard/patientCases/newPatientCase" class="btn btn-primary" style="
	    		<c:choose>
	            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			         color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			         color: rgb(412, 580, 515); background: rgba(30, 0.114, 160, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || onePatient.patientCases.size() % 2 == 0}">
			     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255); color:pink;
	             </c:otherwise>
	             </c:choose>width: 100%; display:block; padding: 10px">Schedule A New Visit Today, ${dayCurrentDateTime}!
	         </a>
	    </h1>
		<h1 style=" margin: 0px 15px 0 0; width: 100%"><a href="/mellowHealth/patientsPortal/patients" class="btn btn-warning" style="
	    		<c:choose>
	            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			         color: rgb(412, 580, 515); background: rgba(130, 1.14, 160, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
			     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255); color:pink; background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:otherwise>
	             </c:choose>
	             width: 100%; display:block; padding:10px 0">Patients Portal Access ${onePatient.dateOfBirth}!</a></h1>
			    <c:choose>
			        <c:when test="${onePatient.id == loggedInPatient.id and onePatient.patientCases.size() > 1}">
			        <h1 class="text-center border-bottom border-2" style="margin-top:5px;border-radius:5%;background: rgba(0.21, 0.180, 25, 0.9);color: brown; font-family: fantasy;padding:10px;">
						<form action="/mellowHealth/patientsPortal/patients/${onePatient.id}" method="get">	
						<input type="submit" value="${onePatient.patientCases.size()}: Patient Cases- ${onePatient.physiciansPatients.size()} Private Physicians!" class="btn btn-success"style="
				    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(553, 0.114, 16, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531,64, 16, 0.9); 
				             </c:when>
				             <c:otherwise>
				                  color: rgb(21, 180, 255); color:pink;
				             </c:otherwise>
				             </c:choose>margin: 10px 0; width:100%; padding: 10px"/>
						</form>
			        </h1>
			        </c:when>
			        <c:when test="${onePatient.id == loggedInPatient.id and onePatient.patientCases.size() == 1}">
			            <h1 class="text-center" style="border-radius:5%;color: khaki;">
						<form action="/mellowHealth/patientsPortal/patients/${onePatient.id}" method="get" style="border-radius:5%;color: khaki;padding:10px; margin-top:5px;">	
						<input type="submit" value="${onePatient.patientCases.size()}: Physician Visit!" class="btn btn-success"style="
				    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(553, 0.114, 16, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531,64, 16, 0.9);
				             </c:when>
				     <c:otherwise>
							<input type="submit" value="${onePatient.patientCases.size()} Mellow Patient Case!" class="btn btn-success border-bottom border-2"style="
					    		<c:choose>
					            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
							         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
					            </c:when>
					            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
							         color: rgb(412, 580, 515); background: rgba(553, 0.114, 16, 0.9); 
					            </c:when>
					            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
							     	 color: rgb(412, 580, 515); background: rgba(0.531,64, 16, 0.9); 
					             </c:when>
					             <c:otherwise>
					                  color: rgb(21, 180, 255); color:pink;
					             </c:otherwise>
					             </c:choose>margin: 10px 0; width:100%; padding: 10px"/>
						</form>
				      </c:otherwise>
			          </c:choose>margin: 5px 0; width:100%; padding: 10px"/>
				      </form>
			          </h1>
			        </c:when>
			        <c:otherwise>
			           <h1 class="text-center" style="margin-top:5px;border-radius:5%;background: rgba(0.21, 0.180, 25, 0.9);color:brown; font-family: fantasy;">
					   <form action="/mellowHealth/patientsPortal/patients/${onePatient.id}" method="get" style=";background: rgba(13, 64, 60, 0.9);border-radius:5%;color: khaki;padding:5px; margin-top:3px;">	
						<input type="submit" value="You Have ${onePatient.patientCases.size()} Patient Cases- ${onePatient.physiciansPatients.size()} Personal Physicians!" class="btn btn-ouline-success"style="
				    		<c:choose>
				            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
						         color: rgb(412, 580, 515);background: rgba(13, 64, 60, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
						         color: rgb(412, 580, 515); background: rgba(553, 0.114, 16, 0.9); 
				            </c:when>
				            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
						     	 color: rgb(412, 580, 515); background: rgba(0.531,64, 16, 0.9); 
				             </c:when>
				             <c:otherwise>
				                  color: rgb(21, 180, 255); color:pink;
				             </c:otherwise>
				             </c:choose>margin: 10px 0; width:100%; padding: 10px"/>
					</form>
			            </h1>
			        </c:otherwise>
			    </c:choose>	
	</div>
	<div class="form-group"style="
	    		<c:choose>
	            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			         color: rgb(412, 580, 515);background: rgba(0.13, 0.64, 1.60, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			         color: rgb(412, 580, 515); background: rgba(86, 0.114, 0.160, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
			     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255);background: rgba(78, 10, 80, 0.9); color:pink; background: rgba(0.531, 0.64, 16, 0.9); 
	             </c:otherwise>
	             </c:choose>display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0">
	 	<h1 style=" margin: 10px; width: 100%"><a href="/mellowHealth/hospitalDashboard/patientCases/newPatientCase" class="btn btn-warning" style=" margin: 0 15px 0 0; width: 100%; display:block; padding: 10px;font-weight:bold;">SCHEDULE NEW PHYSICIAN VISIT!</a></h1>		
		<h1 style="width: 100%;margin:10px; "><a style=" width: 100%; display:block; padding: 10px"  href="/mellowHealth/patientsPortal/patients/${onePatient.id }" class="btn btn-primary"><c:out value="${loggedInPatient.patientFirstName}- ${loggedInPatient.patientCases.size()} Patient Cases- ${loggedInPatient.physiciansPatients.size()}: Private Physicians"/>!</a></h1>	
	</div>
	<div class="form-group"style="
	    		<c:choose>
	            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			         color: rgb(412, 580, 515);background: rgba(13, 0.64, 0.60, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			         color: rgb(412, 580, 515); background: rgba(10, 0.114, 0.160, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
			     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 0.316, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255);background: rgba(78, 10, 80, 0.9); color:pink; background: rgba(18.31, 2.64, 1.116, 0.9); 
	             </c:otherwise>
	             </c:choose>display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0">
	
		<h1 style=" margin: 10px; width: 100%"><a href="/mellowHealth/patientsPortal/logout" class="btn btn-danger" style=" margin: 0 15px 0 0; width: 100%; display:block; padding: 10px">LOGOUT HERE!</a></h1>
		<h1 style="width: 100%;margin:10px"><a style="margin:10px 0;width:100%;display:block;padding:10px"  href="/mellowHealth/physicians" class="btn btn-success">GO BACK!</a></h1>
	</div>	
	<div class="form-group"style="
	    		<c:choose>
	            <c:when test="${onePatient.gender == 'Male' || onePatient.race == 'Black'}">
			         color: rgb(412, 580, 515);background: rgba(13, 0.64, 0.60, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == true || onePatient.patientCases.size() % 2 == 1}">
			         color: rgb(412, 580, 515); background: rgba(10, 0.114, 0.160, 0.9); 
	            </c:when>
	            <c:when test="${onePatient.hasTravelledOutsideTheUnitedStatesForMoreThan30Days == false || patient.patientCases.size() % 2 == 0}">
			     	 color: rgb(412, 580, 515); background: rgba(0.531, 0.64, 0.316, 0.9); 
	             </c:when>
	             <c:otherwise>
	                  color: rgb(21, 180, 255);background: rgba(78, 10, 80, 0.9); color:pink; background: rgba(18.31, 2.64, 1.116, 0.9); 
	             </c:otherwise>
	             </c:choose>display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0">
     <h1 style="width:100%"><a style="background: rgba(68, 8, 120, 0.9); margin:10px 0; width: 100%; display:block; padding: 10px"  href="/mellowHealth/patientsPortal/patients/${onePatient.id }" class="btn btn-success">${loggedInPatient.patientFirstName }'s Patient Portal!</a></h1>
	</div>
</body>
</html>