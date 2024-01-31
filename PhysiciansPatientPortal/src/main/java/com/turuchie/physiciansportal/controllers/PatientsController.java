package com.turuchie.physiciansportal.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turuchie.physiciansportal.models.LoginPatient;
import com.turuchie.physiciansportal.models.Patient;
import com.turuchie.physiciansportal.models.PhysiciansPatient;
import com.turuchie.physiciansportal.services.PatientService;
import com.turuchie.physiciansportal.services.PhysicianService;
import com.turuchie.physiciansportal.services.PhysiciansPatientService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/mellowHealth/patientsPortal")
public class PatientsController {

		@Autowired
		private PatientService patientServ;

		@Autowired
		private PhysicianService physicianServ;
		
		@Autowired
		private PhysiciansPatientService physiciansPatientServ;
	
		public PatientsController() {
			// TODO Auto-generated constructor stub
		}
	
		@GetMapping("/patients/{id}")
		public String showOnePatient(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long patientId = (Long) session.getAttribute("patient_id");
	    if (patientId == null){
	    	return "redirect:/mellowHealth/patientsPortal/login";
	    } 
	    
		Patient loggedInPatient = patientServ.getOne(patientId);
		Patient onePatient = patientServ.getOne(id);
		if (onePatient == null) {
	        return "redirect:/mellowHealth/patientsPortal/login";
	    }

		// Add formatted dates to the model
		model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
		model.addAttribute("dayCurrentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
	    // Add today's date to the model
	    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		// Additional attributes for seconds, minutes, hours, and days
		model.addAttribute("currentSecond", LocalTime.now().getSecond());
		model.addAttribute("currentMinute", LocalTime.now().getMinute());
		model.addAttribute("currentHour", LocalTime.now().getHour());
		model.addAttribute("currentDayOfYear", LocalDate.now().getDayOfYear());
		model.addAttribute("loggedInPatient", loggedInPatient);
		model.addAttribute("onePatient", onePatient);
		model.addAttribute("allPhysicians", physicianServ.findAll());
		return "/Patients/viewOnePatient.jsp";
		}

		@GetMapping("/patients")
		public String showAllPatients(@ModelAttribute("physiciansPatient") 
		PhysiciansPatient physiciansPatient,
		Model model, HttpSession session) {
		Long patientId = (Long) session.getAttribute("patient_id");
	    if (patientId == null){
	    	return "redirect:/mellowHealth/patientsPortal/login";
	    } 

		// Add formatted dates to the model
		model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
		model.addAttribute("dayCurrentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
	    // Add today's date to the model
	    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		// Additional attributes for seconds, minutes, hours, and days
		model.addAttribute("currentSecond", LocalTime.now().getSecond());
		model.addAttribute("currentMinute", LocalTime.now().getMinute());
		model.addAttribute("currentHour", LocalTime.now().getHour());
		model.addAttribute("currentDayOfYear", LocalDate.now().getDayOfYear());
	    
		Patient loggedInPatient = patientServ.getOne(patientId);
		model.addAttribute("physiciansPatient", new PhysiciansPatient());
		model.addAttribute("loggedInPatient", loggedInPatient);
		model.addAttribute("allPatients", patientServ.findAll());
		model.addAttribute("allPhysicians", physicianServ.findAll());
		return "Patients/viewAllPatients.jsp";
		}

		@PostMapping("/hospitalDashboard/process/physiciansPatients/newPhysiciansPatient")
		public String followPhysician(@ModelAttribute("physiciansPatient")
			PhysiciansPatient newPhysiciansPatient, Model model, HttpSession session) {
		    // Get patient ID from the session
		    Long patientId = (Long) session.getAttribute("patient_id");

		    // Get physician ID from the newPhysiciansPatient
		    Long physicianId = newPhysiciansPatient.getPhysician().getId();

		    // Check if the relationship already exists
		    boolean isRelationshipExists = physiciansPatientServ.isRelationshipExists(patientId, physicianId);

		    if (isRelationshipExists) {
		        // Relationship already exists, handle accordingly (e.g., show a message)
		        // You may redirect to a different page or display an error message
		        return "redirect:/mellowHealth/patientsPortal/patients?alreadyFollowing=true";
		    }

		    // If the relationship doesn't exist, create it
		    physiciansPatientServ.create(newPhysiciansPatient);
	        return "redirect:/mellowHealth/patientsPortal/patients?alreadyFollowing=true";
		}

		@GetMapping("/login")
		public String defaultLoginRegistration(@ModelAttribute("patient") Patient patient, @ModelAttribute("loginPatient") LoginPatient loginPatient) {
			return "/Patients/LoginPatient.jsp";
		}	
		
		@GetMapping("/register")
		public String regPatient(@ModelAttribute("patient") Patient patient) {
			
			return "/Patients/registerPatient.jsp";
		}
		
		@PostMapping("/process/register")
		public String processRegister(@Valid @ModelAttribute("patient") Patient patient,
			BindingResult result, Model model, HttpSession session) {
			// reject if email is taken
		if(patientServ.getOne(patient.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Patient Already Exits!");
		}
			// Check if email is already registered
		Patient existingPatient = patientServ.getOne(patient.getEmail());
		if (existingPatient != null) {
		    result.rejectValue("email", "Unique", "Patient with this email already exists!");
		}
		    

		//reject if passwords dont match
		if(!patient.getPassword().equals(patient.getConfirmPassword())) {
			result.rejectValue("password", "Match", "Passwords Must Match!");
		}

		//redirect if errors
		if(result.hasErrors() || result.hasFieldErrors("dateOfBirth")) {
			model.addAttribute("loginPatient", new LoginPatient());
			return "Patients/registerPatient.jsp";
		}

		Patient newPatient = patientServ.registerPatient(patient);
		session.setAttribute("patient_id", newPatient.getId());
		
		return "redirect:/mellowHealth/patientsPortal/patients";
	}
	
		@PostMapping("/process/login")
		public String processLoginPatient(@Valid @ModelAttribute("loginPatient") LoginPatient loginPatient,
			BindingResult result, Model model, HttpSession session) {
			Patient loggingPatient = patientServ.login(loginPatient, result);
			if (loggingPatient == null || result.hasErrors()) {
			    model.addAttribute("patient", new Patient());
			    return "Patients/LoginPatient.jsp";
			}
	
			session.setAttribute("patient_id", loggingPatient.getId());
			return "redirect:/mellowHealth/patientsPortal/patients";
		}
		
		@GetMapping("/patients/edit/{id}")
		public String editPatient(@PathVariable("id") Long id, Model model) {
			// Add formatted dates to the model
			model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
			model.addAttribute("dayCurrentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
		    // Add today's date to the model
		    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
			model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
			// Additional attributes for seconds, minutes, hours, and days
			model.addAttribute("currentSecond", LocalTime.now().getSecond());
			model.addAttribute("currentMinute", LocalTime.now().getMinute());
			model.addAttribute("currentHour", LocalTime.now().getHour());
			model.addAttribute("currentDayOfYear", LocalDate.now().getDayOfYear());
			model.addAttribute("patient", patientServ.getOne(id));
			model.addAttribute("confirmPatientPassword", patientServ.getOne(id).getPassword());
			return "Patients/editPatient.jsp";
		}

		@PatchMapping("/process/patients/editPatient/{id}")
		public String processEditPatient(@PathVariable Long id,
		    @Valid @ModelAttribute("patient") Patient updatedPatient,
		    Model model, HttpSession session, BindingResult result) {
		    Long patientId = (Long) session.getAttribute("patient_id");

		    if (result.hasErrors()) {
		        // Add necessary model attributes for rendering the form with validation errors
		        if (patientId != null) {
		            List<Integer> timeFormat = new ArrayList<>();
		            for (int i = 1; i <= 12; i++) {
		                timeFormat.add(i);
		            }

		            // Add other necessary attributes to the model
		            model.addAttribute("timeFormat", timeFormat);
		            model.addAttribute("loggedInPatient", patientServ.getOne(patientId));
					model.addAttribute("patient", patientServ.getOne(id));
					model.addAttribute("confirmPatientPassword", patientServ.getOne(id).getPassword());

		            // Return the view with the model attributes
		            return "Patients/editPatient.jsp";
		        } else {
		            // Handle the case where patientId is null (redirect to login, show an error, etc.)
		            return "redirect:/mellowHealth/patientPortal/patients";
		        }
		    } else {
		        // Retrieve the existing patient from the database
		        Patient existingPatient = patientServ.getOne(id);

		        // Check if email is already registered by another patient
		        Patient patientWithEmail = patientServ.getOne(updatedPatient.getEmail());
		        if (patientWithEmail != null && !patientWithEmail.getId().equals(id)) {
		            result.rejectValue("email", "Unique", "Patient with this email already exists!");
		        }

		        // Exclude password fields from validation if not being changed
		        if (updatedPatient.getPassword().isEmpty() && updatedPatient.getConfirmPassword().isEmpty()) {
		            // Password fields are not being changed, so exclude them from validation
		            result.popNestedPath();
		            result.popNestedPath();
		        } else {
		            // Password fields are being changed, validate them
		            if (!updatedPatient.getPassword().equals(updatedPatient.getConfirmPassword())) {
		                result.rejectValue("password", "Match", "Passwords Must Match!");
		            }
		        }

		        // Redirect if errors
		        if (result.hasErrors()) {
		            model.addAttribute("patient", existingPatient);
					model.addAttribute("patient", patientServ.getOne(id));
					model.addAttribute("confirmPatientPassword", patientServ.getOne(id).getPassword());
		            return "Patients/editPatient.jsp";
		        }

		        // Update fields other than password
		        existingPatient.setEmail(updatedPatient.getEmail());
		        // Update other fields as needed

		        // Check if the password is being updated
		        if (!updatedPatient.getPassword().isEmpty()) {
		            // Hash the new password using BCrypt
		            String hashedPassword = BCrypt.hashpw(updatedPatient.getPassword(), BCrypt.gensalt());
		            existingPatient.setPassword(hashedPassword);
		        }

		        if (updatedPatient.getPassword().isEmpty() && updatedPatient.getConfirmPassword().isEmpty()) {
		            // Password fields are not being changed, so exclude them from validation
		            result.popNestedPath();
		            result.popNestedPath();
		        }

		        // Save the updated patient in the database
		        patientServ.update(existingPatient);

		        return "redirect:/mellowHealth/hospitalDashboard/patients/" + patientId;
		    }
		}

		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/mellowHealth/patientsPortal/login";
		}
}