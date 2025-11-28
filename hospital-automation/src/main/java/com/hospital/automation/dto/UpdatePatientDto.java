package com.hospital.automation.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientDto {
	private Integer patientId;
	private String patientName;
	private String patientSurname;
	private String patientEmail;
	private String patientPassword;
	private String patientNationalId;
	private String patientPhoneNumber;
	private LocalDate patientBirthDate;
	private Integer userId;
}
