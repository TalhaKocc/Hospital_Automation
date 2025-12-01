package com.hospital.automation.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.hospital.automation.dto.AppointmentMailEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentProducer {

    private final KafkaTemplate<String, AppointmentMailEvent> kafkaTemplate;

    public void sendMailEvent(AppointmentMailEvent event) {
        kafkaTemplate.send("appointment-mail-topic", event);
        System.out.println("MAIL EVENT SENT: " + event);
    }
}
