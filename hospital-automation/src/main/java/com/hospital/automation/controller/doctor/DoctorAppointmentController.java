package com.hospital.automation.controller.doctor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.automation.dto.ListDoctorAppointmentDto;
import com.hospital.automation.service.AppointmentService;
import com.hospital.automation.service.DoctorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorAppointmentController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String listAppointments(Model model, HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        Integer doctorId = doctorService.getDoctorByUserId(userId).getId();

        List<ListDoctorAppointmentDto> appointments = appointmentService.listDoctorAppointment(doctorId);

        model.addAttribute("appointments", appointments);

        return "doctor/appointment-list";
    }
}
	
	
