package com.mycareer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otps")
public class OTP {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String phone;
    private String email;
    private String otpCode;
    private LocalDateTime expiryTime;
    private boolean verified;
    
    // Constructors
    public OTP() {}
    
    public OTP(Long id, String phone, String email, String otpCode, LocalDateTime expiryTime, boolean verified) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.otpCode = otpCode;
        this.expiryTime = expiryTime;
        this.verified = verified;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getOtpCode() { return otpCode; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
    public boolean isVerified() { return verified; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
    public void setExpiryTime(LocalDateTime expiryTime) { this.expiryTime = expiryTime; }
    public void setVerified(boolean verified) { this.verified = verified; }
}