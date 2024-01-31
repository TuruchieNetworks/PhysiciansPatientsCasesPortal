package com.turuchie.physiciansportal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turuchie.physiciansportal.models.IncidentReport;
import com.turuchie.physiciansportal.repositories.IncidentReportRepository;
import com.turuchie.physiciansportal.repositories.PhysicianRepository;

@Service
public class IncidentReportService {
	
	@Autowired
	private IncidentReportRepository incidentReportRepo;
	
	@Autowired
	PhysicianRepository physicianRepo;	
	
	public IncidentReportService(IncidentReportRepository incidentReportRepo,
		PhysicianRepository physicianRepo) {
		this.incidentReportRepo = incidentReportRepo;
		this.physicianRepo = physicianRepo;
	}
	
	public IncidentReport getOne(Long id) {
		Optional<IncidentReport> incidentReport = incidentReportRepo.findById(id);
		return incidentReport.isPresent() ? incidentReport.get() : null;
	}

	public List<IncidentReport> getAll() {
		return (List<IncidentReport>) incidentReportRepo.findAll();
	}

	public IncidentReport create(IncidentReport incidentReport) {
		return incidentReportRepo.save(incidentReport);
	}

	public IncidentReport update(IncidentReport incidentReport) {
		return incidentReportRepo.save(incidentReport);
	}

	public void delete(Long id) {
		incidentReportRepo.deleteById(id);
	}

	//*************************create physician with incidentReport logic****************************
	public IncidentReport getIncidentReportByEvent(String event) {
	    Optional<IncidentReport> incidentReport = incidentReportRepo.findByEvent(event);
	    return incidentReport.orElse(null);
	}
}