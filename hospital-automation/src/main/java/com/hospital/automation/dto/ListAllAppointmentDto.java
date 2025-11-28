package com.hospital.automation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListAllAppointmentDto {

	private String patientName;
	private String patientSurname;
	private String patientNationalId;
	private LocalDate patientBirthDate;
	private String doctorName;
	private String doctorSurname;
	private String departmentName;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;

}
