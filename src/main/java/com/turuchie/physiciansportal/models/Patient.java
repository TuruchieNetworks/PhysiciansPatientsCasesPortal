package com.turuchie.physiciansportal.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;   

	@NotBlank(message="Patients First name cannot be blank!")
	@Size(min=2, max=150, message="Patient first name needs to be between 2 and 150 characters!")
	private String patientFirstName;

	@NotBlank(message="Patients last name cannot be blank!")
	@Size(min=2, max=150, message="Patient last name needs to be between 2 and 150 characters!")
	private String patientLastName;
    
    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotEmpty(message = "Password is required!")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    private String password;

    @Transient
    @NotEmpty(message = "Passwords Must Match!")
    @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
    private String confirmPassword;
    
    @NotNull(message = "Please enter an Insurance Card Number!")
    private BigDecimal patientInsuranceID;

	@NotBlank(message="Chief Complaints Should Be In Patients Words!")
	@Size(min=2, max=550, message="Chief Complaints needs to be between 2 and 550 characters!")
	private String currentChiefComplaint;

    @NotBlank(message = "Please Enter Past Medical History!")
    @Size(min = 3, max = 500, message = "Past Medical History Must Be Between 3 and 500 characters, Enter Null Instead!")
    private String patientPastMedicalHistory;

    @NotBlank(message = "Please Enter Past Surgical History!")
    @Size(min = 3, max = 500, message = "Invalid Past Medical History, Enter Null Instead!")
    private String patientPastSurgicalHistory;

    @NotBlank(message = "Please Enter Any Known Recent Emerency!")
    @Size(min = 3, max = 500, message = "Enter Null If Unknown!")
    private String patientRecentEmergency;

    @NotBlank(message = "Please Enter Any Recent Admission!")
    @Size(min = 3, max = 500, message = "Invalid Entry, Enter Null If Unknown!")
    private String patientRecentAdmission;

    @NotNull(message = "Please Your Date Of Birth!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Use this annotation for binding LocalDate
    private Date dateOfBirth;

    @NotBlank(message = "Please Select Gender!")
    @Size(min = 3, max = 500, message = "Gender Cannot Be Empty!")
    private String gender;
    private String race;
    

    @NotBlank(message = "Please Enter Any Recently Used Recreational Substance!")
    @Size(min = 3, max = 50, message = "Invalid Entry, Enter Null If Unkown!")
    private String recreationalSubstance;

    @NotNull(message = "Please Enter Travel History!")
    private Boolean hasTravelledOutsideTheUnitedStatesForMoreThan30Days;
    
    private String patientDiary;

    @Column(updatable = false)
    private Date createdAt;
    
    private Date updatedAt;

    @OneToMany(mappedBy = "patient")
    private List<PatientCase> patientCases;
    
    @OneToMany(mappedBy = "patient")
    private List<InsuranceInformation> patientInsuranceInformation;

    @OneToMany(mappedBy = "patient")
    private List<PhysiciansPatient> physiciansPatients;
    
    @OneToMany(mappedBy = "patient")
    private List<PatientsAddresses> patientAddresses;
    
    @OneToMany(mappedBy = "patient")
    private List<IncidentReport> incidentReports;
    
	public Patient() {
    }
	
	public List<PatientCase> getPatientCases() {
		return patientCases;
	}

	public void setPatientCases(List<PatientCase> patientCases) {
		this.patientCases = patientCases;
	}
	
	public List<PhysiciansPatient> getPhysiciansPatients() {
		return physiciansPatients;
	}

	public void setPhysiciansPatients(List<PhysiciansPatient> physiciansPatients) {
		this.physiciansPatients = physiciansPatients;
	}

	public List<InsuranceInformation> getPatientInsuranceInformation() {
		return patientInsuranceInformation;
	}

	public void setPatientInsuranceInformation(List<InsuranceInformation> patientInsuranceInformation) {
		this.patientInsuranceInformation = patientInsuranceInformation;
	}

	public List<IncidentReport> getIncidentReports() {
		return incidentReports;
	}

	public void setIncidentReports(List<IncidentReport> incidentReports) {
		this.incidentReports = incidentReports;
	}

	public BigDecimal getPatientInsuranceID() {
		return patientInsuranceID;
	}

	public void setPatientInsuranceID(BigDecimal patientInsuranceID) {
		this.patientInsuranceID = patientInsuranceID;
	}
	
	public List<PatientsAddresses> getPatientAddresses() {
		return patientAddresses;
	}

	public void setPatientAddresses(List<PatientsAddresses> patientAddresses) {
		this.patientAddresses = patientAddresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }
    
    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

	public String getCurrentChiefComplaint() {
		return currentChiefComplaint;
	}

	public void setCurrentChiefComplaint(String currentChiefComplaint) {
		this.currentChiefComplaint = currentChiefComplaint;
	}

	public String getPatientPastMedicalHistory() {
		return patientPastMedicalHistory;
	}

	public void setPatientPastMedicalHistory(String patientPastMedicalHistory) {
		this.patientPastMedicalHistory = patientPastMedicalHistory;
	}

	public String getPatientPastSurgicalHistory() {
		return patientPastSurgicalHistory;
	}

	public void setPatientPastSurgicalHistory(String patientPastSurgicalHistory) {
		this.patientPastSurgicalHistory = patientPastSurgicalHistory;
	}

	public String getPatientRecentEmergency() {
		return patientRecentEmergency;
	}

	public void setPatientRecentEmergency(String patientRecentEmergency) {
		this.patientRecentEmergency = patientRecentEmergency;
	}

	public String getPatientRecentAdmission() {
		return patientRecentAdmission;
	}

	public void setPatientRecentAdmission(String patientRecentAdmission) {
		this.patientRecentAdmission = patientRecentAdmission;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRecreationalSubstance() {
		return recreationalSubstance;
	}

	public void setRecreationalSubstance(String recreationalSubstance) {
		this.recreationalSubstance = recreationalSubstance;
	}

	public Boolean getHasTravelledOutsideTheUnitedStatesForMoreThan30Days() {
		return hasTravelledOutsideTheUnitedStatesForMoreThan30Days;
	}

	public void setHasTravelledOutsideTheUnitedStatesForMoreThan30Days(
			Boolean hasTravelledOutsideTheUnitedStatesForMoreThan30Days) {
		this.hasTravelledOutsideTheUnitedStatesForMoreThan30Days = hasTravelledOutsideTheUnitedStatesForMoreThan30Days;
	}

	public String getPatientDiary() {
		return patientDiary;
	}

	public void setPatientDiary(String patientDiary) {
		this.patientDiary = patientDiary;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist // adds the created at date and time to sql on creation 
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // add the updated at date and time when being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	
    }

}
