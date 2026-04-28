package com.mycareer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycareer.service.OTPService;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin(origins = "http://localhost:5173")
public class OTPController {

    @Autowired
    private OTPService otpService;

    // Send OTP - Step 1
    @PostMapping("/send")
    public ResponseEntity<?> sendOTP(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String phone = body.get("phone");
        
        Map<String, String> response = otpService.sendOTP(email, phone);
        
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }

    // Verify OTP - Step 2
    @PostMapping("/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String phone = body.get("phone");
        String otpCode = body.get("otpCode");
        
        boolean result;
        
        // Try to verify by email first
        if (email != null && !email.isEmpty()) {
            result = otpService.verifyOTPByEmail(email, otpCode);
        } else {
            result = otpService.verifyOTP(phone, otpCode);
        }
        
        Map<String, String> response = new HashMap<>();
        if (result) {
            response.put("message", "OTP Verified Successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Invalid OTP or OTP Expired");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    // Resend OTP
    @PostMapping("/resend")
    public ResponseEntity<?> resendOTP(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String phone = body.get("phone");
        
        Map<String, String> response = otpService.sendOTP(email, phone);
        
        if (response.containsKey("error")) {
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
}