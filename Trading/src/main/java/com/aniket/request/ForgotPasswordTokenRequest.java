package com.aniket.request;


import com.aniket.domain.VerificationType;

public class ForgotPasswordTokenRequest {

    private String sentTo;

    private VerificationType verificationType;

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public VerificationType getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VerificationType verificationType) {
        this.verificationType = verificationType;
    }

    public ForgotPasswordTokenRequest() {
    }

    public ForgotPasswordTokenRequest(String sentTo, VerificationType verificationType) {
        this.sentTo = sentTo;
        this.verificationType = verificationType;
    }
}
