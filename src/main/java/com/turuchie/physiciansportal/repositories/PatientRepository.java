package com.turuchie.physiciansportal.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turuchie.physiciansportal.models.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
	List<Patient> findAll();
    Optional<Patient> findById(Long id);
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByPassword(String password);
    Optional<Patient> findByPatientFirstName(String patientFirstName);
    Optional<Patient> findByPatientLastName(String patientLastName);
	Optional<Patient> findByPatientFirstNameContaining(String firstName);  
	Optional<Patient> findByPatientLastNameContaining(String lastName);
	Optional<Patient> findByPatientFirstNameContainingOrPatientLastNameContaining(String firstName, String lastName);
	Optional<Patient> findByPatientFirstNameContainingIgnoreCase(String partialName);
	Optional<Patient> findByPatientLastNameContainingIgnoreCase(String partialName);
	boolean existsByEmailAndIdNot(String email, Long patientId);
}