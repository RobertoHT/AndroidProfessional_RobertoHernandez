package com.professional.micromaster.photolibrary.fragments.inspect;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 07/07/17.
 */

public interface SearchRepository {
    public final static int PHOTO_SIZE = 10;

    void getNextPhoto(String tags);
    void savePhoto(Photo photo);
}
