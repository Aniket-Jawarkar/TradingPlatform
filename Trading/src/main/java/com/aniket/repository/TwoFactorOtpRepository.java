package com.aniket.repository;

import com.aniket.model.TwoFactorOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository< TwoFactorOTP, String > {
    TwoFactorOTP findByUserId(String userId);

    TwoFactorOTP findByUserId(Long userId);
}
