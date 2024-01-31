package com.turuchie.physiciansportal.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.physiciansportal.models.Patient;

@Component
public class PatientUtils {
    // Helper method to set current date and time attributes
    public void setCurrentDateTimeAttributes(Model model) {
        model.addAttribute("dayCurrentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
        model.addAttribute("currentDateTime", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));

        // Formatted Current Dates
        model.addAttribute("dayCreatedAt", LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, yyyy")));
        model.addAttribute("createdAt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));

        // Additional attributes for seconds, minutes, hours, and days
        model.addAttribute("currentSecond", LocalTime.now().getSecond());
        model.addAttribute("currentMinute", LocalTime.now().getMinute());
        model.addAttribute("currentHour", LocalTime.now().getHour());
        model.addAttribute("currentDayOfYear", LocalDate.now().getDayOfYear());
    }

    // Helper method to set common patient attributes
    public void setCommonPatientAttributes(Patient existingPatient, Patient patientToEdit) {
        patientToEdit.setPatientFirstName(existingPatient.getPatientFirstName());
        patientToEdit.setPatientLastName(existingPatient.getPatientLastName());
        patientToEdit.setEmail(existingPatient.getEmail());
        patientToEdit.setPassword(existingPatient.getPassword());
        patientToEdit.setConfirmPassword(existingPatient.getConfirmPassword());
        patientToEdit.setPatientInsuranceID(existingPatient.getPatientInsuranceID());
        patientToEdit.setCurrentChiefComplaint(existingPatient.getCurrentChiefComplaint());
        patientToEdit.setPatientPastMedicalHistory(existingPatient.getPatientPastMedicalHistory());
        patientToEdit.setPatientPastSurgicalHistory(existingPatient.getPatientPastSurgicalHistory());
        patientToEdit.setPatientRecentEmergency(existingPatient.getPatientRecentEmergency());
        patientToEdit.setPatientRecentAdmission(existingPatient.getPatientRecentAdmission());
        patientToEdit.setDateOfBirth(existingPatient.getDateOfBirth());
        patientToEdit.setGender(existingPatient.getGender());
        patientToEdit.setRace(existingPatient.getRace());
        patientToEdit.setRecreationalSubstance(existingPatient.getRecreationalSubstance());
        patientToEdit.setHasTravelledOutsideTheUnitedStatesForMoreThan30Days(existingPatient.getHasTravelledOutsideTheUnitedStatesForMoreThan30Days());
    }

    // Helper method to set date-related attributes
    public void setDateAttributes(Patient patientToEdit) {
        patientToEdit.setCreatedAt(new Date());
        patientToEdit.setUpdatedAt(new Date());
    }


    // Helper method to convert Date to LocalDateTime and format dates
    public void formatAndSetDateAttributes(Model model, Date createdAtDate) {
        Instant instant = createdAtDate.toInstant();
        LocalDateTime createdAtLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy");
        DateTimeFormatter dayformatter = DateTimeFormatter.ofPattern("EEE, yyyy");

        String createdAtFormatted = createdAtLocalDateTime.format(formatter);
        String currentDateTimeFormatted = currentDateTime.format(formatter);
        String dayCreatedAtFormatted = createdAtLocalDateTime.format(dayformatter);
        String dayCurrentDateTimeFormatted = currentDateTime.format(dayformatter);

        // Set formatted date attributes in the model
        model.addAttribute("createdAtFormatted", createdAtFormatted);
        model.addAttribute("currentDateTimeFormatted", currentDateTimeFormatted);
        model.addAttribute("dayCreatedAtFormatted", dayCreatedAtFormatted);
        model.addAttribute("dayCurrentDateTimeFormatted", dayCurrentDateTimeFormatted);
    }

}
