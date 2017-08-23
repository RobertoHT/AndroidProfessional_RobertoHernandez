package com.professional.micromaster.photolibrary.main;

import android.content.Context;

/**
 * Created by Roberto on 05/07/17.
 */

public class MainInteractorImpl implements MainInteractor {
    private MainRepository repository;

    public MainInteractorImpl(Context context) {
        repository = new MainRepositoryImpl(context);
    }

    @Override
    public void execute(String path) {
        repository.uploadPhoto(path);
    }
}
