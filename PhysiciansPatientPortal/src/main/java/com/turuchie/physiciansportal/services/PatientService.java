package com.turuchie.physiciansportal.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.turuchie.physiciansportal.models.LoginPatient;
import com.turuchie.physiciansportal.models.Patient;
import com.turuchie.physiciansportal.models.PatientCase;
import com.turuchie.physiciansportal.repositories.PatientRepository;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepo;

	public PatientService() {}
	
	public Iterable<Patient> findAll() {
	    return patientRepo.findAll();
	}

	public Patient registerPatient (Patient registeringPatient) {
		String hashed = BCrypt.hashpw(registeringPatient.getPassword(), BCrypt.gensalt());
		registeringPatient.setPassword(hashed);
		return patientRepo.save(registeringPatient);
	}
	
	public Patient getOne(String email) {
		Optional<Patient> patient = patientRepo.findByEmail(email);
		return patient.isPresent() ? patient.get() : null;
	}

	public Patient getOne(Long id) {
		Optional<Patient> patient = patientRepo.findById(id);
		return patient.isPresent() ? patient.get() : null;
	}

    public List<String> getAllPatientFullNames(String trimmedSearchTerm) {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream()
                .map(patient -> patient.getPatientFirstName() + " " + patient.getPatientLastName())
                .collect(Collectors.toList());
    }

    public Patient getOneByEmail(String email) {
        return patientRepo.findByEmail(email).orElse(null);
    }
	
	public Patient getOnePatientFirstName(String patientFirstName) {
		Optional<Patient> patient = patientRepo.findByPatientFirstName(patientFirstName);
		return patient.isPresent() ? patient.get() : null;
	}
	
	public Patient getOnePatientLastName(String patientLastName) {
		Optional<Patient> patient = patientRepo.findByPatientFirstName(patientLastName);
		return patient.isPresent() ? patient.get() : null;
	}

	public Patient getOnePatientByFullName(String fullName) {
	    String[] nameParts = StringUtils.split(fullName, ' ');

	    if (nameParts != null && nameParts.length > 1) {
	        // If the name contains a space, assume it's a full name
	        String firstName = nameParts[0].toLowerCase();
	        String lastName = nameParts[1].toLowerCase();
	        return patientRepo.findByPatientFirstNameContainingOrPatientLastNameContaining(firstName, lastName).orElse(null);
	    } else {
	        // If there's no space, treat it as a single name
	        String firstName = fullName.toLowerCase();
	        return patientRepo.findByPatientFirstName(firstName).orElse(null);
	    }
	}
	
	public Optional<Patient> searchPatientsByFullName(String trimmedSearchFirstName) {
	 return patientRepo.findByPatientFirstNameContainingIgnoreCase(trimmedSearchFirstName);
	 }

    public Patient getPatientsByPartialName(String partialName) {
        return patientRepo.findByPatientFirstNameContainingIgnoreCase(partialName)
                .orElseGet(() -> patientRepo.findByPatientLastNameContainingIgnoreCase(partialName).orElse(null));
    }

	public Patient login(LoginPatient loginPatient, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		
		Patient existingPatient = getOne(loginPatient.getEmail());
		if(existingPatient == null) {
			result.rejectValue("email", "Unique" ,"Invalid Login Credentials");
			return null;
		}
		if(!BCrypt.checkpw(loginPatient.getPassword(), existingPatient.getPassword())) {
			result.rejectValue("password", "Matches" ,"Invalid Login Credentials");
			return null;
		}
		return existingPatient;
	}

	public Patient update(Patient patient) {
		return patientRepo.save(patient);
	}

}
