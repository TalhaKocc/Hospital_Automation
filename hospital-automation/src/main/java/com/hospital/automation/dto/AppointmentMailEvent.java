package com.hospital.automation.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentMailEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
    private String patientName;
    private String doctorName;
    private String departmentName;
    private String date;
    private String time;
}
