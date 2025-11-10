package com.aniket.service;

import com.aniket.domain.VerificationType;
import com.aniket.model.ForgotPasswordToken;
import com.aniket.model.User;

public interface ForgotPasswordService {

        ForgotPasswordToken createToken(User user ,
                                        String id,
                                        String otp,
                                        VerificationType verificationType,
                                        String sendTo);

        ForgotPasswordToken findById(String id);

        ForgotPasswordToken findByUser(Long userId);
        void deleteToken(ForgotPasswordToken token);

}
