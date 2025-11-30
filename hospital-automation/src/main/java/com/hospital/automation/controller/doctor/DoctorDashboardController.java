package com.hospital.automation.controller.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorDashboardController {
	
	@GetMapping("/doctor/dashboard")
	public String adminDashboard() {
		return "doctor/dashboard";
	}
}
