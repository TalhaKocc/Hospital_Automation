package com.hospital.automation.dto;

import java.math.BigDecimal;

import com.hospital.automation.model.Doctor;
import com.hospital.automation.model.User;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddDoctorDto {
	private String doctorName;
	private String doctorSurname;
	private String doctorEmail;
	private String doctorPassword;
	private User.Role userRole;
	private String doctorDepartment;
	private String doctorPhoneNumber;
	private String doctorRoomNumber;
	private Doctor.Gender doctorGender;
	private BigDecimal doctorSalary;
}