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
public class ListDoctorAppointmentDto {
	
	private Integer appointmentId;
    private String patientName;
    private String departmentName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
