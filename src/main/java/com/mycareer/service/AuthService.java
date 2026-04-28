package com.mycareer.service;

import com.mycareer.model.User;
import com.mycareer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OTPService otpService;
    
    public Map<String, Object> login(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            response.put("error", "Invalid email or password");
            return response;
        }
        
        User user = userOpt.get();
        
        // Skip password check for dummy login
        if (!password.equals("dummy") && !user.getPassword().equals(password)) {
            response.put("error", "Invalid email or password");
            return response;
        }
        
        if (user.getStatus() != null && user.getStatus().equals("inactive")) {
            response.put("error", "Your account is inactive. Contact admin.");
            return response;
        }
        
        // Send OTP
        Map<String, String> otpResult = otpService.sendOTP(user.getEmail(), user.getPhone());
        
        response.put("success", true);
        response.put("message", "OTP sent to your email");
        response.put("email", user.getEmail());
        response.put("phone", user.getPhone());
        response.put("userId", user.getId());
        response.put("userType", user.getUserType());
        response.put("name", user.getName());
        response.put("otpForDemo", otpResult.get("otpForDemo"));
        
        return response;
    }
    
    public User verifyOTPAndLogin(String email, String otpCode) {
        boolean verified = otpService.verifyOTPByEmail(email, otpCode);
        if (verified) {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                return userOpt.get();
            }
        }
        return null;
    }
    
    public User signup(User user) {
        user.setStatus("active");
        user.setJoinDate(LocalDateTime.now());
        if (user.getUserType() == null) {
            user.setUserType("student");
        }
        return userRepository.save(user);
    }
}