package com.mycareer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendOTPByEmail(String toEmail, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("MyCareer+ - Login Verification Code");
        message.setText("Your OTP for login is: " + otpCode + "\n\nValid for 5 minutes.\n\nThank you,\nMyCareer+ Team");
        mailSender.send(message);
    }
}