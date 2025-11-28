package com.hospital.automation.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "doctors")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Integer id;
	
	@Column(name = "phone_number",nullable = false)
	private String phoneNumber;
	
	@Column(name = "room_number",nullable = false)
	private String roomNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender",nullable = false)
	private Gender gender;
	
	@Column(name = "salary",nullable = false)
	private BigDecimal salary;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> listAppointment;
	
	public enum Gender {
		MALE,
		FEMALE
	}	
	
}	