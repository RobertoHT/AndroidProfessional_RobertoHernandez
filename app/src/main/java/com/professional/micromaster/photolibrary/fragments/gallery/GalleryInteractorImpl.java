package com.professional.micromaster.photolibrary.fragments.gallery;

/**
 * Created by Roberto on 06/07/17.
 */

public class GalleryInteractorImpl implements GalleryInteractor {
    private GalleryRepository repository;

    public GalleryInteractorImpl() {
        repository = new GalleryRepositoryImpl();
    }

    @Override
    public void execute() {
        repository.getSavedPhotos();
    }
}
