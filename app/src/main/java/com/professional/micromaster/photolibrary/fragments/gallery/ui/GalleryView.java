package com.professional.micromaster.photolibrary.fragments.gallery.ui;

import com.professional.micromaster.photolibrary.entities.Photo;

import java.util.List;

/**
 * Created by Roberto on 06/07/17.
 */

public interface GalleryView {
    void setPhotos(List<Photo> photos);
    void showMessage();
}
