package com.professional.micromaster.photolibrary.fragments.inspect.ui;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 07/07/17.
 */

public interface SearchView {
    public static final int SWIPE_RIGHT = 0;
    public static final int SWIPE_LEFT = 1;
    public static final int SWIPE_UP = 2;
    public static final int SWIPE_BOTTOM = 3;

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
