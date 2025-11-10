package com.aniket.request;

public class ResetPasswordRequest {

   private String otp;
   private String password;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String otp, String password) {
        this.otp = otp;
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
