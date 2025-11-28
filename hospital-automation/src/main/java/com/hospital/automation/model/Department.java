package com.hospital.automation.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "departments")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Integer id;
	
	@Column(name = "department_code",nullable = false)
	private String code;
	
	@Column(name = "department_name",nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<Doctor> listDoctor;
}
