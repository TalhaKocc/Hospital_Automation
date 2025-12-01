package com.hospital.automation.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.hospital.automation.dto.AppointmentMailEvent;
import com.hospital.automation.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentMailConsumer {

    private final MailService mailService;

    @KafkaListener(topics = "appointment-mail-topic", groupId = "mail-group")
    public void consume(AppointmentMailEvent event) {

        System.out.println("MAIL EVENT RECEIVED: " + event);

        mailService.sendAppointmentMail(
                event.getEmail(),
                event.getPatientName(),
                event.getDoctorName(),
                event.getDepartmentName(),
                event.getDate(),
                event.getTime()
        );
    }
}
