package com.professional.micromaster.photolibrary.fragments.inspect.ui;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 07/07/17.
 */

public interface InspectView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void leftAnimation();
    void rightAnimation();
    void upAnimation();
    void bottomAnimation();

    void onPhotoSaved();

    void setPhoto(Photo photo);
    void onGetPhotoError(String error);
}
