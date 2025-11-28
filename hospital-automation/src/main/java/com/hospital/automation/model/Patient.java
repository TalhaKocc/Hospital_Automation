package com.hospital.automation.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "patients")
public class Patient {   
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "patient_gender",nullable = false)
	private Gender gender;
	
	@Column(name = "national_id",nullable = false,unique = true)
	private String nationalId;
	
	@Column(name = "phone_number",nullable = false)
	private String phoneNumber;
	
	@Column(name = "date_birth",nullable = false)
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> listAppointment;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public enum Gender{
		MALE,
		FEMALE
	}

}
