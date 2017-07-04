package com.professional.micromaster.photolibrary.fragments.main.ui;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 04/07/17.
 */

public interface MainScreenView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();

    void setPhoto(Photo photo);
    void sharePhoto();
}
