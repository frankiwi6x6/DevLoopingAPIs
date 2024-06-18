package com.DEVLooping.userAPI.service;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com"); // Cambia esto por tu servidor SMTP
        sender.setPort(587); // Cambia esto por el puerto de tu servidor SMTP
        sender.setUsername("devloopingteam@gmail.com"); // Cambia esto por tu correo electrónico
        sender.setPassword("xnsn fpro cmut hcta"); // Cambia esto por tu contraseña

        Properties props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "*"); // Esta línea deshabilita la validación de certificados SSL/TLS

        this.mailSender = sender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("devloopingteam@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
