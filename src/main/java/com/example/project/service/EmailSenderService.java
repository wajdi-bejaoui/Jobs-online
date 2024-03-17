package com.example.project.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true); // Set to true to indicate HTML content
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle the exception
            e.printStackTrace();
        }
//        message.setFrom("fromemail@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body, true);
//        message.setText
//        message.setSubject(subject);
//        message.
//        mailSender.send(message);
        System.out.println("Mail Send...");


    }

}

