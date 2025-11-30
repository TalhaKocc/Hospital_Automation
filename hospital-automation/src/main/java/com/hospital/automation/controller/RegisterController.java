package com.hospital.automation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hospital.automation.dto.AddPatientDto;
import com.hospital.automation.service.PatientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {

	private final PatientService patientService;
	
	@GetMapping("/register")
	public String addPatientForm(Model model) {
		model.addAttribute("patient",new AddPatientDto());
		return "/register";
	}

	@PostMapping("/register")
	public String addPatient(@ModelAttribute AddPatientDto addPatientDto) {
		patientService.addPatient(addPatientDto);
		return "redirect:/login";
	}
}
