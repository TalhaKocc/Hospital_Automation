package com.hospital.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.automation.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
