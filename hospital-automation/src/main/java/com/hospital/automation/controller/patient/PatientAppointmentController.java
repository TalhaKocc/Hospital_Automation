package com.hospital.automation.controller.patient;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.dto.AppointmentCreateDto;
import com.hospital.automation.dto.ListPatientAppointmentDto;
import com.hospital.automation.service.AppointmentService;
import com.hospital.automation.service.DepartmentService;
import com.hospital.automation.service.DoctorService;
import com.hospital.automation.service.PatientService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientAppointmentController {

    private final DepartmentService departmentService;
	private final DoctorService doctorService;
	private final PatientService patientService;
	private final AppointmentService appointmentService;

	
	@GetMapping("/appointment")
	public String listAppointment(Model model,HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		Integer patientId = patientService.getPatientByUserId(userId).getId();
		
		List<ListPatientAppointmentDto> appointments = appointmentService.listPatientAppointment(patientId);
		model.addAttribute("appointments",appointments);
		
		return "patient/appointment-list";
	}

	@GetMapping("/appointment/add")
	public String addAppointmentForm(Model model) {

	    AppointmentCreateDto dto = new AppointmentCreateDto();
	    model.addAttribute("appointment", dto);

	    model.addAttribute("departments", departmentService.listAllDepartments());
	    model.addAttribute("doctors", doctorService.listAllDoctor());

	    return "patient/appointment-add";
	}

	@PostMapping("/appointment/add")
	public String addAppointmentSubmit(AppointmentCreateDto dto, HttpSession session) {

	    Integer userId = (Integer) session.getAttribute("userId");
	    Integer patientId = patientService.getPatientByUserId(userId).getId();

	    appointmentService.addAppointment(patientId, dto);

	    return "redirect:/patient/appointment";
	}

}
