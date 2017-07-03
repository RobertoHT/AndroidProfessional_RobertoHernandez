package com.professional.micromaster.photolibrary.login.event;

/**
 * Created by Roberto on 03/07/17.
 */

public class LoginEvent {
    public final static int onSignInSuccess = 0;
    public final static int onSignUpSuccess = 1;
    public final static int onSignInError = 2;
    public final static int onSignUpError = 3;
    public final static int onFailedToRecoverSession = 4;

    private int eventType;
    private String errorMesage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMesage() {
        return errorMesage;
    }

    public void setErrorMesage(String errorMesage) {
        this.errorMesage = errorMesage;
    }
}
