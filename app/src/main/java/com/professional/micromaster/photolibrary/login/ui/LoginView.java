package com.professional.micromaster.photolibrary.login.ui;

/**
 * Created by Roberto on 03/07/17.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleLogin();
    void loginError(String error);
    void registerError(String error);

    void navigateToMainScreen();
}
