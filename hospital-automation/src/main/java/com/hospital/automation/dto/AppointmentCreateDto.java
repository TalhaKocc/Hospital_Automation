package com.hospital.automation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateDto {
	
	private Integer departmentId;
	private Integer doctorId;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
}
