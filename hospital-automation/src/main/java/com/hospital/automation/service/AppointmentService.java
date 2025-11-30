package com.hospital.automation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.automation.dto.AppointmentCreateDto;
import com.hospital.automation.dto.ListAllAppointmentDto;
import com.hospital.automation.dto.ListDoctorAppointmentDto;
import com.hospital.automation.dto.ListPatientAppointmentDto;
import com.hospital.automation.model.Appointment;
import com.hospital.automation.model.Doctor;
import com.hospital.automation.model.Patient;
import com.hospital.automation.repository.AppointmentRepository;
import com.hospital.automation.repository.DoctorRepository;
import com.hospital.automation.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService {
	
	 private final AppointmentRepository appointmentRepository;
	 private final DoctorRepository doctorRepository;
	 private final PatientRepository patientRepository;
	 
	public List<ListPatientAppointmentDto> listPatientAppointment(Integer patientId){
		
		return appointmentRepository.findByPatientId(patientId).stream()
				.map(a -> new ListPatientAppointmentDto(
						a.getId(),
						a.getDoctor().getUser().getName() + " " + a.getDoctor().getUser().getSurname(),
						a.getDoctor().getDepartment().getName(),
						a.getAppointmentDate(),
						a.getAppointmentTime()
				))
				.toList();
	}

	public List<ListDoctorAppointmentDto> listDoctorAppointment(Integer doctorId){
		
		return appointmentRepository.findByDoctorId(doctorId).stream()
				.map(d -> new ListDoctorAppointmentDto(
						d.getId(),
						d.getPatient().getUser().getName() + " " + d.getPatient().getUser().getSurname(),
						d.getDoctor().getDepartment().getName(),
						d.getAppointmentDate(),
						d.getAppointmentTime()
				))
				.toList();
	}
	
	public void createAppointment(AppointmentCreateDto appointmentCreateDto,Integer patientId) {
		Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Hasta bulunamadı"));

        Doctor doctor = doctorRepository.findById(appointmentCreateDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doktor bulunamadı"));
        
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentCreateDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentCreateDto.getAppointmentTime());
        appointmentRepository.save(appointment);
	}

	public void deleteAppointment(Integer appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}

	public List<ListAllAppointmentDto> listAllAppointment(){
		return appointmentRepository.findAll().stream()
				.map(a -> new ListAllAppointmentDto(
						a.getId(),
						a.getPatient().getUser().getName(),
						a.getPatient().getUser().getSurname(),
						a.getPatient().getNationalId(),
						a.getPatient().getBirthDate(),
	                    a.getDoctor().getUser().getName(),
	                    a.getDoctor().getUser().getSurname(),
	                    a.getDoctor().getDepartment().getName(),
	                    a.getAppointmentDate(),
	                    a.getAppointmentTime()
					)).toList();
	}
}
