package com.hospital.automation.dto;

import java.time.LocalDate;

import com.hospital.automation.model.Patient;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ListAllPatientDto {
	private Integer patientId;
	private String patientName;
	private String patientSurname;
	private Patient.Gender patientGender;
	private String nationalId;
	private String patientPhoneNumber;
	private LocalDate patientBirthDate;
}