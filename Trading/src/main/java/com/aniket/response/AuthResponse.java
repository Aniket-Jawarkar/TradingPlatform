package com.aniket.response;

public class AuthResponse {
    private String jwt ;
    private Boolean status;
    private String message;
    private boolean isTwoFactorAuthEnabled;
    private String session;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTwoFactorAuthEnabled() {
        return isTwoFactorAuthEnabled;
    }

    public void setTwoFactorAuthEnabled(boolean twoFactorAuthEnabled) {
        isTwoFactorAuthEnabled = twoFactorAuthEnabled;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public AuthResponse() {
    }

    public AuthResponse(String jwt, Boolean status, String message, boolean isTwoFactorAuthEnabled, String session) {
        this.jwt = jwt;
        this.status = status;
        this.message = message;
        this.isTwoFactorAuthEnabled = isTwoFactorAuthEnabled;
        this.session = session;
    }
}
