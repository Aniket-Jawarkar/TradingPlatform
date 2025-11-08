package com.aniket.service;

import com.aniket.domain.VerificationType;
import com.aniket.model.User;
import com.aniket.model.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user , VerificationType verificationType);
    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);
//    Boolean verifyOtp (String otp , VerificationCode verificationCode)
    void deleteVerificationCodeById(VerificationCode verificationCode);
//    Boolean verifyOtp(String otp);

}
