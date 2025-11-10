package com.aniket.controller;


import com.aniket.request.ForgotPasswordTokenRequest;
import com.aniket.domain.VerificationType;
import com.aniket.model.ForgotPasswordToken;
import com.aniket.model.User;
import com.aniket.model.VerificationCode;
import com.aniket.request.ResetPasswordRequest;
import com.aniket.response.ApiResponse;
import com.aniket.response.AuthResponse;
import com.aniket.service.EmailService;
import com.aniket.service.ForgotPasswordService;
import com.aniket.service.UserService;
import com.aniket.service.VerificationCodeService;
import com.aniket.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;


    private String jwt;
    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(
            @RequestHeader("Authorization") String jwt,
            @PathVariable VerificationType verificationType) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());

        if(verificationCode == null){
            verificationCode = verificationCodeService.sendVerificationCode(user, verificationType);

        }

        if(verificationType.equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());

        }



        return new ResponseEntity<>("verification code sent succuessfully", HttpStatus.OK);

    }


    @PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<User> enableTwoFactorAuthentication(
            @PathVariable String otp ,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByUser(user.getId());

        String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL) ?
                verificationCode.getEmail() : verificationCode.getMobile();

        boolean isVerified = verificationCode.getOtp().equals(otp);

        if(isVerified){
            User updatedUser = userService.enableTwoFactorAuthentication(verificationCode.getVerificationType(),
                    sendTo, user);
            verificationCodeService.deleteVerificationCodeById(verificationCode);

            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        }

        throw  new Exception("wrong otp");


    }






    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendForgotPasswordOtp(

            @RequestBody ForgotPasswordTokenRequest req) throws Exception {


        User user = userService.findUserByEmail(req.getSentTo());

        String otp = OtpUtils.generateOTP();

        UUID uuid = UUID.randomUUID();

        String id = uuid.toString();

        ForgotPasswordToken token =forgotPasswordService.findByUser(user.getId());

        if(token == null){
            token = forgotPasswordService.createToken(user , id , otp, req.getVerificationType()  ,req.getSentTo() );
        }

        if(req.getVerificationType().equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(
                    user.getEmail(),
                    token.getOtp());
        }


        AuthResponse response = new AuthResponse();

        response.setSession(token.getId());
        response.setSession("Password reset otp sent successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);

    }




    @PatchMapping("/auth/users/reset-password/verify-otp/")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String id,
            @RequestBody ResetPasswordRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
//        User user = userService.findUserProfileByJwt(jwt);

        ForgotPasswordToken forgotPasswordToken = forgotPasswordService.findById(id);


        Boolean isVerified = forgotPasswordToken.getOtp().equals((req.getOtp()));

        if(isVerified){
            userService.updatePassword(forgotPasswordToken.getUser(), req.getPassword());
            ApiResponse res = new ApiResponse();

            res.setMessage("Password update succesfully");

            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }

        throw  new Exception("Wrong otp");
    }


}
