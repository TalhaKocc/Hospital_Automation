package com.hospital.automation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hospital.automation.model.Department;
import com.hospital.automation.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	public List<Department> listDepartment(){
		return departmentRepository.findAll();
	}
	
	public List<String> listAllDepartmentName(){
		return departmentRepository.findAll()
				.stream()
				.map(Department::getName)
				.toList();
	}		

	public void addDepartment(Department department) {
		
		departmentRepository.save(department);
	}

	public void deleteDepartment(Integer id) {
		departmentRepository.deleteById(id);
	}

	public void updateDepartment(Department updated) {

        Department department = departmentRepository.findById(updated.getId())
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı: " + updated.getId()));

        department.setCode(updated.getCode());
        department.setName(updated.getName());
    }

	public Department getUpdateDepartment(Integer departmentId) {
		
		return departmentRepository.findById(departmentId)
				.orElseThrow(() -> new RuntimeException("Departman Bulunamadı"));
	}

	public List<Department> listAllDepartments() {
	    return departmentRepository.findAll();
	}

}
