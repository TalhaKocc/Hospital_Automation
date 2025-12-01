package com.hospital.automation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.automation.dto.AddPatientDto;
import com.hospital.automation.dto.ListAllPatientDto;
import com.hospital.automation.dto.UpdatePatientDto;
import com.hospital.automation.model.Patient;
import com.hospital.automation.model.User;
import com.hospital.automation.model.User.Role;
import com.hospital.automation.repository.PatientRepository;
import com.hospital.automation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {

	private final UserRepository userRepository;
	private final PatientRepository patientRepository;

	
	public void addPatient(AddPatientDto addPatientDto) {
		
		User user = new User();
		user.setName(addPatientDto.getPatientName());
		user.setSurname(addPatientDto.getPatientSurname());
		user.setEmail(addPatientDto.getPatientEmail());
		user.setPassword(addPatientDto.getPatientPassword());
		user.setRole(Role.PATIENT);
		User savedUser = userRepository.save(user);
		
		Patient patient = new Patient();
		patient.setUser(savedUser);
		patient.setGender(addPatientDto.getPatientGender());
		patient.setNationalId(addPatientDto.getPatientNationalId());
		patient.setPhoneNumber(addPatientDto.getPatientPhoneNumber());
		patient.setBirthDate(addPatientDto.getPatientBirthDate());
		patientRepository.save(patient);
	}
	
	public List<ListAllPatientDto> listAllPatient(){
		return patientRepository.findAll().stream()
			    .map(p -> new ListAllPatientDto(
			    		p.getId(),
			    		p.getUser().getName(),
			    		p.getUser().getSurname(),
			    		p.getGender(),
			    		p.getNationalId(),
			    		p.getPhoneNumber(),
			    		p.getBirthDate()
			    		)).toList();
	}
	
	public void deletePatient(Integer patientId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Hasta Bulunamadı"));
		
		Integer userId = patient.getUser().getId();
		patientRepository.deleteById(patientId);
		userRepository.deleteById(userId);
	}
	
	public void updatePatient(UpdatePatientDto updatePatientDto) {
		Patient patient = patientRepository.findById(updatePatientDto.getPatientId())
				.orElseThrow(() -> new RuntimeException("Hasta Bulunamadı"));
		User user = userRepository.findById(updatePatientDto.getUserId()).
				orElseThrow(() -> new RuntimeException("Kullanıcı Bulunamadı"));
		
		user.setName(updatePatientDto.getPatientName());
		user.setSurname(updatePatientDto.getPatientSurname());
		user.setEmail(updatePatientDto.getPatientEmail());
		user.setPassword(updatePatientDto.getPatientPassword());
		
		patient.setNationalId(updatePatientDto.getPatientNationalId());
		patient.setPhoneNumber(updatePatientDto.getPatientPhoneNumber());
		patient.setBirthDate(updatePatientDto.getPatientBirthDate());
	}
	
	public UpdatePatientDto getUpdatePatient(Integer patientId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Hasta Bulunamadı"));
		User user = patient.getUser();
		
		UpdatePatientDto updatePatientDto = new UpdatePatientDto();
		updatePatientDto.setPatientId(patient.getId());
		updatePatientDto.setUserId(user.getId());
		updatePatientDto.setPatientName(user.getName());
		updatePatientDto.setPatientSurname(user.getSurname());
		updatePatientDto.setPatientEmail(user.getEmail());
		updatePatientDto.setPatientPassword(user.getPassword());
		updatePatientDto.setPatientNationalId(patient.getNationalId());
		updatePatientDto.setPatientPhoneNumber(patient.getPhoneNumber());
		updatePatientDto.setPatientBirthDate(patient.getBirthDate());
		
		return updatePatientDto;
	}

	public Patient getPatientById(Integer id) {
	    return patientRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Hasta bulunamadı"));
	}

	public Patient getPatientByUserId(Integer userId) {
	    return patientRepository.findByUserId(userId)
	            .orElseThrow(() -> new RuntimeException("Hasta  bulunamadı (User ID ile)."));
	}
}
