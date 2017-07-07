package com.professional.micromaster.photolibrary.fragments.inspect;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 07/07/17.
 */

public class SavePhotoInteractorImpl implements SavePhotoInteractor {
    private SearchRepository repository;

    public SavePhotoInteractorImpl() {
        repository = new SearchRepositoryImpl();
    }

    @Override
    public void execute(Photo photo) {
        repository.savePhoto(photo);
    }
}
