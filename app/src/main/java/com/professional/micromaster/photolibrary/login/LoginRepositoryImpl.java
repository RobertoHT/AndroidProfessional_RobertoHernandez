package com.professional.micromaster.photolibrary.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;
import com.professional.micromaster.photolibrary.login.event.LoginEvent;

/**
 * Created by Roberto on 03/07/17.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private static final String KEY_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND";
    private EventBus eventBus;

    public LoginRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void checkAlreadyAuthenticated() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            post(LoginEvent.onSignInSuccess);
        } else {
            post(LoginEvent.onFailedToRecoverSession);
        }
    }

    @Override
    public void login(final String email, final String password) {
        if (email != null && password != null) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            post(LoginEvent.onSignInSuccess, null);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof FirebaseAuthInvalidUserException
                                    && ((FirebaseAuthInvalidUserException) e).getErrorCode().equals(KEY_USER_NOT_FOUND)){
                                register(email, password);
                            } else {
                                post(LoginEvent.onSignInError, e.getMessage());
                            }
                        }
                    });
        }
    }

    private void register(final String email, final String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        login(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        post(LoginEvent.onSignUpError, e.getMessage());
                    }
                });
    }

    private void post(int type) {
        post(type, null);
    }

    private void post(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMesage(errorMessage);
        }

        eventBus.post(loginEvent);
    }
}
