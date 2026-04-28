package com.mycareer.repository;

import com.mycareer.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByPhoneAndOtpCode(String phone, String otpCode);
    Optional<OTP> findByEmailAndOtpCode(String email, String otpCode);
}