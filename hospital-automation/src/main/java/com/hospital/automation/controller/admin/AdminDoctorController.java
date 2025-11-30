package com.hospital.automation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.dto.AddDoctorDto;
import com.hospital.automation.dto.UpdateSalaryDto;
import com.hospital.automation.model.Doctor;
import com.hospital.automation.service.DepartmentService;
import com.hospital.automation.service.DoctorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/doctors")      
public class AdminDoctorController {
	
	private final DoctorService doctorService;
	private final DepartmentService departmentService;
	
	@GetMapping
	public String listDoctors(Model model) {
		model.addAttribute("doctors",doctorService.listAllDoctor());
		return "admin/doctors/doctor-list";
	}

	@GetMapping("/add")
	public String addDoctorForm(Model model) {
		model.addAttribute("doctor",new AddDoctorDto());
		model.addAttribute("department",departmentService.listDepartment());
		return "admin/doctors/doctor-add";
	}

	@PostMapping("/add")
	public String addDoctor(@ModelAttribute AddDoctorDto addDoctorDto) {
		doctorService.addDoctor(addDoctorDto);
		return "redirect:/admin/doctors";
	}
	
	@GetMapping("/update-salary/{id}")
	public String showSalaryForm(@PathVariable Integer id,Model model) {
		
		Doctor doctor = doctorService.getDoctorById(id);
		
		UpdateSalaryDto updateSalaryDto = new UpdateSalaryDto();
		updateSalaryDto.setDoctorId(doctor.getId());
		updateSalaryDto.setSalary(doctor.getSalary());
		
		model.addAttribute("salaryDto",updateSalaryDto);
		model.addAttribute("doctor",doctor);
		
		return "admin/doctors/doctor-salary-update";
	}

	@PostMapping("/update-salary")
	public String updateSalary(@ModelAttribute("salaryDto") UpdateSalaryDto dto) {
		doctorService.updatedoctorSalary(dto.getDoctorId(), dto.getSalary());
		return "redirect:/admin/doctors";
	}

	@GetMapping("/delete/{id}")
	public String deleteDoctor(@PathVariable Integer id) {
		doctorService.deleteDoctor(id);
		return "redirect:/admin/doctors";
	}
}
