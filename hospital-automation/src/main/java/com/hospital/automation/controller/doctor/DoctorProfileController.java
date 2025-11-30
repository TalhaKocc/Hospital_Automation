package com.hospital.automation.controller.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.dto.UpdateDoctorDto;
import com.hospital.automation.model.Doctor;
import com.hospital.automation.service.DoctorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class DoctorProfileController {

	private final DoctorService doctorService;
	
	@GetMapping("/doctor/profile")
	public String showProfile(Model model,HttpSession session) {
		
		Integer doctorId = (Integer) session.getAttribute("userId");
		Doctor doctor = doctorService.getDoctorByUserId(doctorId);
		
		UpdateDoctorDto updateDoctorDto = new UpdateDoctorDto();
		updateDoctorDto.setDoctorId(doctor.getId());
		updateDoctorDto.setUserId(doctor.getUser().getId());
		updateDoctorDto.setDoctorName(doctor.getUser().getName());
		updateDoctorDto.setDoctorSurname(doctor.getUser().getSurname());
        updateDoctorDto.setDoctorEmail(doctor.getUser().getEmail());
        updateDoctorDto.setDoctorPassword(doctor.getUser().getPassword());
        updateDoctorDto.setDoctorPhoneNumber(doctor.getPhoneNumber());
        updateDoctorDto.setDoctorRoomNumber(doctor.getRoomNumber());
		
        model.addAttribute("doctorProfile",updateDoctorDto);
		model.addAttribute("doctor", doctor);
		return "doctor/profile/profile";
	}
	

	@GetMapping("/doctor/profile/update")
	public String showUpdateForm(Model model, HttpSession session) {

	    Integer doctorId = (Integer) session.getAttribute("userId");
	    Doctor doctor = doctorService.getDoctorByUserId(doctorId);

	    UpdateDoctorDto dto = new UpdateDoctorDto();
	    dto.setDoctorId(doctor.getId());
	    dto.setUserId(doctor.getUser().getId());
	    dto.setDoctorName(doctor.getUser().getName());
	    dto.setDoctorSurname(doctor.getUser().getSurname());
	    dto.setDoctorEmail(doctor.getUser().getEmail());
	    dto.setDoctorPassword(doctor.getUser().getPassword());
	    dto.setDoctorPhoneNumber(doctor.getPhoneNumber());
	    dto.setDoctorRoomNumber(doctor.getRoomNumber());

	    model.addAttribute("dto", dto);

	    return "doctor/profile/profile-update";
	}

	
	@PostMapping("/doctor/profile/update")
	public String updateProfile(UpdateDoctorDto dto) {

	    doctorService.updateDoctor(dto);

	    return "redirect:/doctor/profile?success";
	}


}
