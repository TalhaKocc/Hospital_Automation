package com.hospital.automation.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientDashboard {

	@GetMapping("/patient/dashboard")
	public String adminDashboard() {
		return "patient/dashboard";
	}
}
