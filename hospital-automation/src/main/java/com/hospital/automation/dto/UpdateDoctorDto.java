package com.hospital.automation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateDoctorDto {
	private Integer doctorId;
	private String doctorName;
	private String doctorSurname;
	private String doctorEmail;
	private String doctorPassword;
	private String doctorPhoneNumber;
	private String doctorRoomNumber;
	private Integer userId;
}