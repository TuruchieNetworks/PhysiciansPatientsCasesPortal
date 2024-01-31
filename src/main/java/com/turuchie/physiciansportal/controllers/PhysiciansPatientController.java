package com.turuchie.physiciansportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turuchie.physiciansportal.models.Patient;
import com.turuchie.physiciansportal.models.Physician;
import com.turuchie.physiciansportal.models.PhysiciansPatient;
import com.turuchie.physiciansportal.services.PatientCaseService;
import com.turuchie.physiciansportal.services.PatientService;
import com.turuchie.physiciansportal.services.PhysicianService;
import com.turuchie.physiciansportal.services.PhysiciansPatientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mellowHealth")
public class PhysiciansPatientController {
	@Autowired
	private PhysicianService physicianServ;

	@Autowired
	private PatientCaseService patientCaseServ;

	@Autowired
	private PatientService patientServ;
	
	@Autowired
	private PhysiciansPatientService physiciansPatientServ;

	@Autowired
	public PhysiciansPatientController(PatientCaseService patientCaseServ,
		PatientService PatientServ,
		PatientCaseService PatientCaseServ,
		PhysicianService physicianServ,
		PhysiciansPatientService physiciansPatientServ) {
        this.patientCaseServ = patientCaseServ;
        this.physicianServ = physicianServ;
        this.physiciansPatientServ = physiciansPatientServ;
	}
		
	public PhysiciansPatientController() {}

	// Helper method to validate physician and get physician id
    @SuppressWarnings("unused")
	private Long validatePhysicianAndGetId(HttpSession session) {
       Long physicianId = (Long) session.getAttribute("physician_id");
	   return physicianId;
	}

    @SuppressWarnings("unused")
	private Long validatePatientAndGetId(HttpSession session) {
       Long patientId = (Long) session.getAttribute("physician_id");
	   return patientId;
	}

	// Helper method to set physician attributes in the model
	private Physician setPhysicianAttributes(Model model, Long physicianId) {
	    Physician loggedInPhysician = physicianServ.getOne(physicianId);
	    model.addAttribute("loggedInPhysician", loggedInPhysician);
	    return loggedInPhysician;
	}

	// Helper method to set physician attributes in the model
	private Patient setPatientAttributes(Model model, Long patientId) {
	    Patient loggedInPatient = patientServ.getOne(patientId);
	    model.addAttribute("loggedInPatient", loggedInPatient);
		    return loggedInPatient;
		}

	@GetMapping("/physiciansPatients")
	public String showAllPhysicians(@ModelAttribute("physiciansPatient") PhysiciansPatient physiciansPatient, Model model, HttpSession session) {
		Long physicianId = (Long) session.getAttribute("physician_id");
		Long patientId = (Long) session.getAttribute("patient_id");

	    if (physicianId == null){
	    	return "redirect:/mellowHealth/physicians/login";
	    }

		Physician loggedInPhysician = setPhysicianAttributes(model, physicianId);
		Patient loggedInPatient = setPatientAttributes(model, patientId);

		model.addAttribute("loggedInPatient", loggedInPatient);
		model.addAttribute("loggedInPhysician", loggedInPhysician);
		model.addAttribute("allPhysicians", physicianServ.findAll());
		model.addAttribute("allPatientCases", patientCaseServ.getAll());
		return "Physicians/viewAllPhysicians.jsp";
	}

}
