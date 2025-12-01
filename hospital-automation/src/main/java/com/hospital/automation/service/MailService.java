package com.hospital.automation.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendAppointmentMail(
            String to,
            String patientName,
            String doctorName,
            String department,
            String date,
            String time
    ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Randevu Oluşturma Bildirimi");

        String body =
                "Sayın " + patientName + ",\n\n" +
                "Randevu işleminiz başarıyla gerçekleştirilmiştir.\n\n" +
                "➡ Doktor: " + doctorName + "\n" +
                "➡ Bölüm: " + department + "\n" +
                "➡ Tarih: " + date + "\n" +
                "➡ Saat: " + time + "\n\n" +
                "Sağlıklı günler dileriz.\n" +
                "Hospital Automation";

        message.setText(body);

        mailSender.send(message);

        System.out.println("MAIL SENT TO: " + to);
    }
}
