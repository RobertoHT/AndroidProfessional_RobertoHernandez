package com.professional.micromaster.photolibrary.main.ui;

/**
 * Created by Roberto on 05/07/17.
 */

public interface MainView {
    void takePhoto();
    void shareClick();
    void logoutClick();

    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
