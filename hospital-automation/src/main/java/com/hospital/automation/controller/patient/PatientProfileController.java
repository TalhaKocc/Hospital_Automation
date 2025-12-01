package com.hospital.automation.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.dto.UpdatePatientDto;
import com.hospital.automation.model.Patient;

import com.hospital.automation.service.PatientService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PatientProfileController {

	private final PatientService patientService;
	
	@GetMapping("/patient/profile")
	public String showProfile(Model model,HttpSession session) {
		Integer patientId = (Integer) session.getAttribute("userId");
		Patient patient = patientService.getPatientByUserId(patientId);
		
		UpdatePatientDto updatePatientDto = new UpdatePatientDto();
		updatePatientDto.setPatientId(patient.getId());
		updatePatientDto.setUserId(patient.getUser().getId());
		updatePatientDto.setPatientName(patient.getUser().getName());
		updatePatientDto.setPatientSurname(patient.getUser().getSurname());
		updatePatientDto.setPatientEmail(patient.getUser().getEmail());
		updatePatientDto.setPatientPassword(patient.getUser().getPassword());
		updatePatientDto.setPatientNationalId(patient.getNationalId());
		updatePatientDto.setPatientPhoneNumber(patient.getPhoneNumber());
		updatePatientDto.setPatientBirthDate(patient.getBirthDate());
		
		model.addAttribute("patientProfile",updatePatientDto);
		model.addAttribute("patient",patient);
		return "patient/profile/profile";
	}

	@GetMapping("/patient/profile/update")
	public String showUpdateForm(Model model,HttpSession session) {
			
		Integer patientId = (Integer) session.getAttribute("userId");
		Patient patient = patientService.getPatientByUserId(patientId);
		
		UpdatePatientDto updatePatientDto = new UpdatePatientDto();
		updatePatientDto.setPatientId(patient.getId());
		updatePatientDto.setUserId(patient.getUser().getId());
		updatePatientDto.setPatientName(patient.getUser().getName());
		updatePatientDto.setPatientSurname(patient.getUser().getSurname());
		updatePatientDto.setPatientEmail(patient.getUser().getEmail());
		updatePatientDto.setPatientPassword(patient.getUser().getPassword());
		updatePatientDto.setPatientNationalId(patient.getNationalId());
		updatePatientDto.setPatientPhoneNumber(patient.getPhoneNumber());
		updatePatientDto.setPatientBirthDate(patient.getBirthDate());
		
		model.addAttribute("dto",updatePatientDto);
		
		return "patient/profile/profile-update";
	}
	
	@PostMapping("/patient/profile/update")
	public String updateProfile(UpdatePatientDto dto) {
		patientService.updatePatient(dto);
		return "redirect:/patient/profile?success";
	}
}
