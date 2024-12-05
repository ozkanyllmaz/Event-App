package com.event_app.Event_App.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    // Rastgele 4 haneli bir kod üretir
    private String generateRandomCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000); // 1000-9999 arasında bir sayı
        return String.valueOf(code);
    }

    // Kullanıcıya özel rastgele bir kod üret ve e-posta gönder
    public String sendResetCode(String to) {
        String resetCode = generateRandomCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Code");
        message.setText("Your password reset code is: " + resetCode);

        emailSender.send(message);

        return resetCode;
    }

    // Genel bir mesaj gönderimi için metot
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}
