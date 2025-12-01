package com.hospital.automation.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.automation.dto.AddDoctorDto;
import com.hospital.automation.dto.ListAllDoctorDto;
import com.hospital.automation.dto.UpdateDoctorDto;
import com.hospital.automation.model.Department;
import com.hospital.automation.model.Doctor;
import com.hospital.automation.model.User;
import com.hospital.automation.model.User.Role;
import com.hospital.automation.repository.DepartmentRepository;
import com.hospital.automation.repository.DoctorRepository;
import com.hospital.automation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

	private final DoctorRepository doctorRepository;
	private final UserRepository userRepository;
	private final DepartmentRepository departmentRepository;
	
	@CacheEvict(value = "doctorList",allEntries = true)
	public void addDoctor(AddDoctorDto addDoctorDto) {
		
		User user = new User();
		user.setName(addDoctorDto.getDoctorName());
		user.setSurname(addDoctorDto.getDoctorSurname());
		user.setEmail(addDoctorDto.getDoctorEmail());
		user.setPassword(addDoctorDto.getDoctorPassword());
		user.setRole(Role.DOCTOR);
		
		User savedUser = userRepository.save(user);
		
		Department department = departmentRepository.findByName(addDoctorDto.getDoctorDepartment())
				.orElseThrow(() -> new RuntimeException("Departman Bulunamadı: " + addDoctorDto.getDoctorDepartment()));
	
		Doctor doctor = new Doctor();
		doctor.setUser(savedUser);
		doctor.setDepartment(department);
		doctor.setPhoneNumber(addDoctorDto.getDoctorPhoneNumber());
		doctor.setRoomNumber(addDoctorDto.getDoctorRoomNumber());
		doctor.setGender(addDoctorDto.getDoctorGender());
		doctor.setSalary(addDoctorDto.getDoctorSalary());
		
		doctorRepository.save(doctor);
	}
	
	//@Cacheable(value = "doctorList")
	public List<ListAllDoctorDto> listAllDoctor(){
		return doctorRepository.findAll().stream()
				.map(d -> new ListAllDoctorDto(
						d.getId(),
						d.getUser().getName(),
						d.getUser().getSurname(),
						d.getDepartment().getName(),
						d.getPhoneNumber(),
	                    d.getRoomNumber(),
	                    d.getGender(),
	                    d.getSalary()
					)).toList();
	}
	
	//@CacheEvict(value = "doctorList",allEntries = true)
	public void deleteDoctor(Integer doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId).
				orElseThrow(() -> new RuntimeException("Doktor Bulunamadı"));
		
		Integer userId = doctor.getUser().getId();
		
		doctorRepository.deleteById(doctorId);
		userRepository.deleteById(userId);
	}
	
	//@CacheEvict(value = "doctorList",allEntries = true)
	public void updatedoctorSalary(Integer doctorId,BigDecimal salary) {
		
		Doctor doctor = doctorRepository.findById(doctorId).
				orElseThrow(() -> new RuntimeException("Doktor Bulunamadı"));
		doctor.setSalary(salary);
	}
	
	
	public void updateDoctor(UpdateDoctorDto updateDoctorDto) {
		
		Doctor doctor = doctorRepository.findById(updateDoctorDto.getDoctorId()).
				orElseThrow(() -> new RuntimeException("Doktor Bulunamadı"));
		User user = userRepository.findById(updateDoctorDto.getUserId()).
				orElseThrow(() -> new RuntimeException("Kullanıcı Bulunamadı"));
		
		user.setName(updateDoctorDto.getDoctorName());
		user.setSurname(updateDoctorDto.getDoctorSurname());
		user.setEmail(updateDoctorDto.getDoctorEmail());
		user.setPassword(updateDoctorDto.getDoctorPassword());
		
		doctor.setPhoneNumber(updateDoctorDto.getDoctorPhoneNumber());
		doctor.setRoomNumber(updateDoctorDto.getDoctorRoomNumber());
	}

	public UpdateDoctorDto getUpdateDoctor(Integer doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));

        User user = doctor.getUser();

        UpdateDoctorDto dto = new UpdateDoctorDto();
        dto.setDoctorId(doctor.getId());
        dto.setUserId(user.getId());
        dto.setDoctorName(user.getName());
        dto.setDoctorSurname(user.getSurname());
        dto.setDoctorEmail(user.getEmail());
        dto.setDoctorPassword(user.getPassword());
        dto.setDoctorPhoneNumber(doctor.getPhoneNumber());
        dto.setDoctorRoomNumber(doctor.getRoomNumber());

        return dto;
    }

	public Doctor getDoctorById(Integer id) {
	    return doctorRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));
	}

	public Doctor getDoctorByUserId(Integer userId) {
	    return doctorRepository.findByUserId(userId)
	            .orElseThrow(() -> new RuntimeException("Doktor bulunamadı (User ID ile)."));
	}

}

