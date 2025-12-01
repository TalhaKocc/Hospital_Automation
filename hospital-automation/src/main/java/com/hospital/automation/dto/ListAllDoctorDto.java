package com.hospital.automation.dto;


import java.io.Serializable;
import java.math.BigDecimal;

import com.hospital.automation.model.Doctor;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListAllDoctorDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer doctorId;
	private String doctorName;
	private String doctorSurname;
	private String doctorDepartment;
	private String doctorPhoneNumber;
	private String doctorRoomNumber;
	private Doctor.Gender doctorGender;
	private BigDecimal doctorSalary;
} 
