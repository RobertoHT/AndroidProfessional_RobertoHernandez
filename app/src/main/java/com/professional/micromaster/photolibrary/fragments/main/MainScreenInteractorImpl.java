package com.professional.micromaster.photolibrary.fragments.main;

/**
 * Created by Roberto on 04/07/17.
 */

public class MainScreenInteractorImpl implements MainScreenInteractor {
    private MainScreenRepository repository;

    public MainScreenInteractorImpl() {
        repository = new MainScreenRepositoryImpl();
    }

    @Override
    public void execute() {
        repository.getLastPhoto();
    }
}
