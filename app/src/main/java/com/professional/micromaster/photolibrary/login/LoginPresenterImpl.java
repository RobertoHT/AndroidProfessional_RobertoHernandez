package com.professional.micromaster.photolibrary.login;

import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;
import com.professional.micromaster.photolibrary.login.event.LoginEvent;
import com.professional.micromaster.photolibrary.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Roberto on 03/07/17.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }

        interactor.checkAlreadyAuthenticated();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (view != null) {
            view.disableInputs();
            view.showProgress();
        }

        interactor.login(email, password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMesage());
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMesage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onSignInSuccess() {
        if (view != null) {
            view.navigateToMainScreen();
        }
    }

    private void onSignInError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
            view.registerError(error);
        }
    }

    private void onFailedToRecoverSession() {
        if (view != null) {
            view.hideProgress();
            view.enableInputs();
        }
    }
}
