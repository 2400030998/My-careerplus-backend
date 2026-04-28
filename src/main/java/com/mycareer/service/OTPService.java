package com.mycareer.service;

import com.mycareer.model.OTP;
import com.mycareer.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {
    
    @Autowired
    private OTPRepository otpRepository;
    
    private final Random random = new Random();
    
    private String generateOTPCode() {
        return String.format("%06d", random.nextInt(1000000));
    }
    
    // Send OTP via Email/SMS (for demo, prints to console)
    public Map<String, String> sendOTP(String email, String phone) {
        String otpCode = generateOTPCode();
        Map<String, String> response = new HashMap<>();
        
        try {
            // Save to database
            OTP otp = new OTP();
            if (email != null && !email.isEmpty()) {
                otp.setEmail(email);
            }
            if (phone != null && !phone.isEmpty()) {
                otp.setPhone(phone);
            }
            otp.setOtpCode(otpCode);
            otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
            otp.setVerified(false);
            otpRepository.save(otp);
            
            // Print to console (for demo purposes)
            System.out.println("========================================");
            System.out.println("🔐 OTP GENERATED FOR LOGIN 🔐");
            if (email != null && !email.isEmpty()) {
                System.out.println("📧 Email: " + email);
            }
            if (phone != null && !phone.isEmpty()) {
                System.out.println("📱 Phone: " + phone);
            }
            System.out.println("🔑 OTP CODE: " + otpCode);
            System.out.println("⏰ Valid for 5 minutes");
            System.out.println("========================================");
            
            response.put("success", "true");
            response.put("message", "OTP sent successfully");
            if (email != null) response.put("email", email);
            if (phone != null) response.put("phone", phone);
            response.put("otpForDemo", otpCode);
            
        } catch (Exception e) {
            response.put("error", "Failed to send OTP: " + e.getMessage());
        }
        
        return response;
    }
    
    // Verify OTP by phone
    public boolean verifyOTP(String phone, String otpCode) {
        try {
            OTP otp = otpRepository.findByPhoneAndOtpCode(phone, otpCode).orElse(null);
            
            if (otp != null && !otp.isVerified() && 
                otp.getExpiryTime().isAfter(LocalDateTime.now())) {
                otp.setVerified(true);
                otpRepository.save(otp);
                return true;
            }
        } catch (Exception e) {
            System.err.println("OTP verification error: " + e.getMessage());
        }
        return false;
    }
    
    // Verify OTP by email
    public boolean verifyOTPByEmail(String email, String otpCode) {
        try {
            OTP otp = otpRepository.findByEmailAndOtpCode(email, otpCode).orElse(null);
            
            if (otp != null && !otp.isVerified() && 
                otp.getExpiryTime().isAfter(LocalDateTime.now())) {
                otp.setVerified(true);
                otpRepository.save(otp);
                System.out.println("✅ OTP verified successfully for: " + email);
                return true;
            }
        } catch (Exception e) {
            System.err.println("OTP verification error: " + e.getMessage());
        }
        return false;
    }
}