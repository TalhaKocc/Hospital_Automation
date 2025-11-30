package com.hospital.automation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.automation.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	List<Appointment> findByPatientId(Integer patientId);
	List<Appointment> findByDoctorId(Integer doctorId);

}
