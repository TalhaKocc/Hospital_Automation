package com.hospital.automation.dto;

import java.time.LocalDate;

import com.hospital.automation.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPatientDto {
	
	private String patientName;
	private String patientSurname;
	private String patientEmail;
	private String patientPassword;
	private Patient.Gender patientGender;
	private String patientNationalId;
	private String patientPhoneNumber;
	private LocalDate patientBirthDate;
}
