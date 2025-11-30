package com.hospital.automation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/appointments")
public class AdminAppointmentController {
	
	private final AppointmentService appointmentService;
	
	@GetMapping
	public String listAppointment(Model model) {
		model.addAttribute("appointment",appointmentService.listAllAppointment());
		return "admin/appointment/appointment-list";
	}
}
