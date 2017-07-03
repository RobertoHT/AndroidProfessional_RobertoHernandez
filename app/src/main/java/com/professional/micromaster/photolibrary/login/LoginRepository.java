package com.professional.micromaster.photolibrary.login;

/**
 * Created by Roberto on 03/07/17.
 */

public interface LoginRepository {
    void checkAlreadyAuthenticated();
    void login(String email, String password);
}
