package com.hospital.automation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.automation.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	Optional<Doctor> findByUserId(Integer userId);
}
