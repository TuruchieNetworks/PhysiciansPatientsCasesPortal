package com.turuchie.physiciansportal.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patient_cases")
public class PatientCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Chief Complaint Cannot Be Blank!")
    private String chiefComplaint;

    @NotBlank(message = "Please Enter Patients Name!")
    private String patientName;

    @NotBlank(message = "Patients Medications must be between 3 and 500 characters!")
    @Size(min = 3, max = 500, message = "Please provide medication for this patient's visit, or Enter Null!")
    private String patientMedication;

    @NotBlank(message = "Patients Treatment Plan must be between 3 and 500 characters!")
    @Size(min = 3, max = 500, message = "Please provide treatment plan for this patient's visit, or Enter Pending!")
    private String treatmentPlan;

    @NotBlank(message = "Follow ups must be between 3 and 500 characters!")
    @Size(min = 3, max = 500, message = "Please provide follow-up consultations for this patient's visit!")
    private String followUpConsultation;

    @NotBlank(message = "Please Enter Past Medical History!")
    @Size(min = 3, max = 500, message = "Past Medical History Must Be Between 3 and 500 characters, Enter Null Instead!")
    private String pastMedicalHistory;

    @NotBlank(message = "Please Enter Past Surgical History!")
    @Size(min = 3, max = 500, message = "Invalid Past Medical History, Enter Null Instead!")
    private String pastSurgicalHistory;

    @NotBlank(message = "Please Describe Your Current Symptoms!")
    @Size(min = 3, max = 500, message = "Subjective Symptoms Must Be Between 3 and 500 Characters!")
    private String subjectiveSymptoms;
    
    private String objectiveFindings;

    private String differentialDiagnosis;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "physician_id")
    private Physician physician;

    public PatientCase() {
    }
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChiefComplaint() {
		return chiefComplaint;
	}

	public String getPastMedicalHistory() {
		return pastMedicalHistory;
	}
	public void setPastMedicalHistory(String pastMedicalHistory) {
		this.pastMedicalHistory = pastMedicalHistory;
	}

	public String getSubjectiveSymptoms() {
		return subjectiveSymptoms;
	}

	public void setSubjectiveSymptoms(String subjectiveSymptoms) {
		this.subjectiveSymptoms = subjectiveSymptoms;
	}

	public String getPastSurgicalHistory() {
		return pastSurgicalHistory;
	}

	public void setPastSurgicalHistory(String pastSurgicalHistory) {
		this.pastSurgicalHistory = pastSurgicalHistory;
	}

	public String getObjectiveFindings() {
		return objectiveFindings;
	}

	public void setObjectiveFindings(String objectiveFindings) {
		this.objectiveFindings = objectiveFindings;
	}

	public String getDifferentialDiagnosis() {
		return differentialDiagnosis;
	}

	public void setDifferentialDiagnosis(String differentialDiagnosis) {
		this.differentialDiagnosis = differentialDiagnosis;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getPatientMedication() {
        return patientMedication;
    }

    public void setPatientMedication(String patientMedication) {
        this.patientMedication = patientMedication;
    }

    public String getFollowUpConsultation() {
        return followUpConsultation;
    }

    public void setFollowUpConsultation(String followUpConsultation) {
        this.followUpConsultation = followUpConsultation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

	public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
