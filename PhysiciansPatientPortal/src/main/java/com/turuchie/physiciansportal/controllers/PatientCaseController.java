package com.turuchie.physiciansportal.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.turuchie.physiciansportal.models.Patient;
import com.turuchie.physiciansportal.models.PatientCase;
import com.turuchie.physiciansportal.models.Physician;
import com.turuchie.physiciansportal.services.PatientCaseService;
import com.turuchie.physiciansportal.services.PatientService;
import com.turuchie.physiciansportal.services.PhysicianService;
import com.turuchie.physiciansportal.utils.UtilInputConverter;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/mellowHealth/hospitalDashboard")
public class PatientCaseController {
	private static final String PHYSICIAN_LOGIN_PATH = "/mellowHealth/physicians/login";
	private static final String HOSPITAL_DASHBOARD_PATH = "/mellowHealth/hospitalDashboard/";
	private static final String PHYSICIAN_PATH = "/mellowHealth/physicians";
	@Autowired
	private PatientCaseService patientCaseServ;

	@Autowired
	private PhysicianService physicianServ;
	
	@Autowired
	private PatientService patientServ;

	@Autowired
	public PatientCaseController(PatientCaseService patientCaseServ,
			PhysicianService physicianServ,PatientService patientServ) {
		        this.patientServ = patientServ;
		        this.patientCaseServ = patientCaseServ;
		        this.physicianServ = physicianServ;
    }
	
	public PatientCaseController() {}

	private List<Integer> generateTimeFormatList() {
	    List<Integer> timeFormat = new ArrayList<>();
	    for (int i = 1; i <= 12; i++) {
	        timeFormat.add(i);
	    }
	    return timeFormat;
	}

	@GetMapping("/patientCases")
	public String patientCaseIndexPage(Model model, HttpSession session) {
		Long physicianId = (Long) session.getAttribute("physician_id");
	    if (physicianId == null){
	    	return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    } 
		Physician loggedInPhysician = physicianServ.getOne(physicianId);

	    if (loggedInPhysician == null) {
	        return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    }		
		model.addAttribute("loggedInPhysician", loggedInPhysician);
		model.addAttribute("allPatientCases", patientCaseServ.getAll());
		model.addAttribute("allPhysicians", physicianServ.findAll());

		// Add formatted dates to the model
		model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
		model.addAttribute("dayCurrentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
	    // Add today's date to the model
	    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
		return "PatientCases/viewAllPatientCases.jsp";
	}

	@GetMapping("/patientCases/{id}")
	public String showOnePatientCase(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long physicianId = (Long) session.getAttribute("physician_id");
	    if (physicianId == null) {
	    	return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    }
		Physician loggedInPhysician = physicianServ.getOne(physicianId);

	    if (loggedInPhysician == null) {
	        return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    }
	    PatientCase onePatientCase = patientCaseServ.getOne(id);
	    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
	    onePatientCase.getCreatedAt();
		model.addAttribute("createdAt", onePatientCase.getCreatedAt().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));		
		model.addAttribute("loggedInPhysician", loggedInPhysician);
		model.addAttribute("allPatientCases", patientCaseServ.getAll());
		model.addAttribute("patientCase", patientCaseServ.getOne(id));

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
		return "PatientCases/viewOnePatientCase.jsp";
	}

	@GetMapping("/patientCases/newPatientCase")
	public String createPatientCase(
	        @ModelAttribute("patientCase") PatientCase patientCase,
	        @RequestParam(value = "searchedPatientName", required = false) String searchedPatientName,
	        Model model,
	        HttpSession session) {
	    Long physicianId = (Long) session.getAttribute("physician_id");
		Long patientId = (Long) session.getAttribute("patient_id");
	    if (physicianId == null || patientId == null) {
	        return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    }

	    String trimmedSearchTerm = searchedPatientName != null ? searchedPatientName.trim() : null;

	    if (trimmedSearchTerm != null && !trimmedSearchTerm.isEmpty()) {
	        // If a non-empty search value is provided
	        Patient matchedPatientFirstName = patientServ.getOnePatientFirstName(trimmedSearchTerm.toLowerCase());
	        Patient matchedPatientFullName = patientServ.getOnePatientByFullName(trimmedSearchTerm.toLowerCase());
	        Patient matchedPatients = patientServ.getPatientsByPartialName(trimmedSearchTerm.toLowerCase());
	        List<String> matchedSearchedPatients = patientServ.getAllPatientFullNames(trimmedSearchTerm.toLowerCase());
	        model.addAttribute("matchedPatients", matchedPatients);
	        model.addAttribute("matchedSearchedPatients", matchedSearchedPatients);
	        model.addAttribute("matchedPatientFirstName", matchedPatientFirstName);
	        model.addAttribute("matchedPatientFullName", matchedPatientFullName);
	    }

	    Physician loggedInPhysician = physicianServ.getOne(physicianId);
	    model.addAttribute("loggedInPhysician", loggedInPhysician);
	    model.addAttribute("timeFormat", generateTimeFormatList());
	    model.addAttribute("allPatients", patientServ.findAll());
	    model.addAttribute("allPhysicians", physicianServ.findAll());

		LocalDateTime createdAtDate = patientCase.getCreatedAt();

		// Convert Date to LocalDateTime
		Instant instant = createdAtDate.toInstant(null);
		LocalDateTime createdAtLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		// Use now() to get the current date and time
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format the LocalDateTime objects
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy");
		DateTimeFormatter dayformatter = DateTimeFormatter.ofPattern("EEE, yyyy");
		String createdAtFormatted = createdAtLocalDateTime.format(formatter);
		String currentDateTimeFormatted = currentDateTime.format(formatter);
		String dayCreatedAtFormatted = createdAtLocalDateTime.format(dayformatter);
		String dayCurrentDateTimeFormatted = currentDateTime.format(dayformatter);

		// Add formatted dates to the model
		model.addAttribute("createdAt", createdAtFormatted);
		model.addAttribute("currentDateTime", currentDateTimeFormatted);
		model.addAttribute("dayCreatedAt", dayCreatedAtFormatted);
		model.addAttribute("dayCurrentDateTime", dayCurrentDateTimeFormatted);

		// Add formatted dates to the model
		model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
	    // Add today's date to the model
		model.addAttribute("dayCurrentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
	    // Add today's date to the model
	    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
	    patientCase.getCreatedAt();
		model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));

	    return "/PatientCases/createPatientCase.jsp";
	}

	@PostMapping("/process/patientCases/createNewPatientCase")
	public String processCreatePatientCase(Model model, @Valid @ModelAttribute("patientCase") PatientCase newPatientCase,	    
	    BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
	    Long physicianId = (Long) session.getAttribute("physician_id");
		    // Redirect to login if physicianId is null
		    if (physicianId == null) {
		        return "redirect:" + PHYSICIAN_LOGIN_PATH;
		    }

		    // Add necessary model attributes for rendering the form with validation errors
		    if (result.hasErrors()) {
		        Physician loggedInPhysician = physicianServ.getOne(physicianId);
			    model.addAttribute("loggedInPhysician", loggedInPhysician);
			    model.addAttribute("timeFormat", generateTimeFormatList());
			    model.addAttribute("allPatients", patientServ.findAll());
			    model.addAttribute("allPhysicians", physicianServ.findAll());
			    redirectAttributes.addFlashAttribute("failureMessage", "Validation Failed While Creating Patient's Case!");
		        return "/PatientCases/createPatientCase.jsp";
		    }

	    // Continue processing if there are no validation errors
		//	patientCase.setPhysician(physicianServ.getOne(physicianId));
	    patientCaseServ.create(newPatientCase);
	    // Add flash attribute for success message
	    redirectAttributes.addFlashAttribute("successMessage", "Patient case created successfully!");
	    
	    // Redirect to the physician's page after successful patientCase creation
	    return "redirect:" + PHYSICIAN_PATH +"/"+ physicianId;
	}

	@GetMapping("/patientCases/edit/{id}")
	public String editPatientCase(@PathVariable("id") Long id,
		@ModelAttribute("inputCollector") UtilInputConverter inputCollector,
		Model model, HttpSession session) {
	    Long physicianId = (Long) session.getAttribute("physician_id");
	    if (physicianId == null) {
	        return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    }
	    model.addAttribute("allPatients", patientServ.findAll());
	    model.addAttribute("allPhysicians", physicianServ.findAll());
	    model.addAttribute("timeFormat", generateTimeFormatList());
	    
	    Physician loggedInPhysician = physicianServ.getOne(physicianId);
	    PatientCase patientCaseToEdit = patientCaseServ.getOne(id);

		model.addAttribute("loggedInPhysician", loggedInPhysician);
	    // Check if the logged-in physician is associated with the patientCase
	    if (patientCaseToEdit.getPhysician().getId().equals(physicianId)) {

			// Add formatted dates to the model
			model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
			model.addAttribute("dayCurrentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
		    // Add today's date to the model
		    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
			model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
	        model.addAttribute("patientCase", patientCaseToEdit);
	        return "PatientCases/editPatientCase.jsp";
	    } else {
	        return "redirect:" + HOSPITAL_DASHBOARD_PATH + "/patientCases";
	    }
	}

	@PatchMapping("/process/patientCases/edit/{id}")
	public String processEditPatientCase(@Valid @ModelAttribute("patientCase") PatientCase patientCase,
		Model model, HttpSession session, BindingResult result) {
	    if (result.hasErrors()) {
	        // Add necessary model attributes for rendering the form with validation errors
	        Long physicianId = (Long) session.getAttribute("physician_id");
	        if (physicianId != null) {
	            List<Integer> timeFormat = new ArrayList<>();
	            for (int i = 1; i <= 12; i++) {
	                timeFormat.add(i);
	            }

	            // Add other necessary attributes to the model
	            model.addAttribute("timeFormat", timeFormat);
	            model.addAttribute("loggedInPhysician", physicianServ.getOne(physicianId));

				// Add formatted dates to the model
				model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
				model.addAttribute("dayCurrentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
			    // Add today's date to the model
			    model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
				model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));

	            // Return the view with the model attributes
	            return "PatientCases/editPatientCase.jsp";
	        } else {
	            // Handle the case where physicianId is null (redirect to login, show an error, etc.)
	            return "redirect:" + PHYSICIAN_LOGIN_PATH;
	        }
	    } else {
	        // Validation passed, update the patientCase
	        patientCaseServ.update(patientCase);
	        return "redirect:/mellowHealth/hospitalDashboard/patientCases/{id}";
	    }
	}
	
	@DeleteMapping("/patientCases/delete/{id}")
	public String deletePatientCase(@PathVariable("id") Long id, HttpSession session) {
	    Long physicianId = (Long) session.getAttribute("physician_id");

	    // Redirect to login if physicianId is null
	    if (physicianId == null) {
	        return "redirect:" + PHYSICIAN_LOGIN_PATH;
	    }

	    PatientCase patientCaseToDelete = patientCaseServ.getOne(id);

	    // Check if the logged-in physician is the owner of the patientCase
	    if (patientCaseToDelete != null && patientCaseToDelete.getPhysician().getId().equals(physicianId)) {
	        patientCaseServ.delete(id);
	    } else {
	        // Redirect to the pmellowHealth/hysician'spage if the physician is not the owner
	        return "redirect:/mellowHealth/physicians";
	    }

	    // Redirect to the pmellowHealth/hysician'spage after successful deletion
	    return "redirect:" + PHYSICIAN_PATH + "/" + physicianId;
	}


}