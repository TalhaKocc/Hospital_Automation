package com.hospital.automation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.service.PatientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/patients")
public class AdminPatientController {

	private final PatientService patientService;
	
	@GetMapping
	public String listPatient(Model model) {
		model.addAttribute("patients",patientService.listAllPatient());
		return "admin/patients/patient-list";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePatient(@PathVariable Integer id) {
		
		patientService.deletePatient(id);
		return "redirect:/admin/patients";
	}
}
