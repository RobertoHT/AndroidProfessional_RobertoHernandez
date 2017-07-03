package com.professional.micromaster.photolibrary.login;

/**
 * Created by Roberto on 03/07/17.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository repository;

    public LoginInteractorImpl() {
        repository = new LoginRepositoryImpl();
    }

    @Override
    public void checkAlreadyAuthenticated() {
        repository.checkAlreadyAuthenticated();
    }

    @Override
    public void login(String email, String password) {
        repository.login(email, password);
    }
}
