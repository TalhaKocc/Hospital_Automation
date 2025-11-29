package com.hospital.automation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.automation.model.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department> findByName(String name);

}
