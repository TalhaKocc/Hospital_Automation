package com.hospital.automation.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role",nullable = false)
	private Role role;
	
	
	@OneToOne(mappedBy = "user")
	private Patient patient;
	
	@OneToOne(mappedBy = "user")
	private Doctor doctor;
	
	
	public enum Role{
		ADMIN,
		DOCTOR,
		PATIENT
	}
	
}