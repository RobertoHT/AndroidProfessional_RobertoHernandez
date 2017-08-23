package com.professional.micromaster.photolibrary.fragments.inspect;

/**
 * Created by Roberto on 07/07/17.
 */

public class GetNextPhotoInteractorImpl implements GetNextPhotoInteractor {
    private SearchRepository repository;

    public GetNextPhotoInteractorImpl() {
        repository = new SearchRepositoryImpl();
    }

    @Override
    public void execute(String tags) {
        repository.getNextPhoto(tags);
    }
}
