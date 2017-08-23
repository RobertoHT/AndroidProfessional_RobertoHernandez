package com.professional.micromaster.photolibrary.login;

import com.professional.micromaster.photolibrary.login.event.LoginEvent;

/**
 * Created by Roberto on 03/07/17.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);
}
