package com.aniket.model;


import com.aniket.domain.VerificationType;
import jakarta.persistence.Embeddable;



@Embeddable
public class TwoFactorAuth {

    private boolean isEnabled = false;

    private VerificationType sendTo;

    public TwoFactorAuth() {
    }

    public TwoFactorAuth(boolean isEnabled, VerificationType sendTo) {
        this.isEnabled = isEnabled;
        this.sendTo = sendTo;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public VerificationType getSendTo() {
        return sendTo;
    }

    public void setSendTo(VerificationType sendTo) {
        this.sendTo = sendTo;
    }
}
