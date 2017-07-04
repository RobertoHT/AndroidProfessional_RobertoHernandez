package com.professional.micromaster.photolibrary.fragments.main;

import com.professional.micromaster.photolibrary.fragments.main.event.MainScreenEvent;

/**
 * Created by Roberto on 04/07/17.
 */

public interface MainScreenPresenter {
    void onCreate();
    void onDestroy();

    void getLastPhoto();
    void imageReady();
    void imageError(String error);

    void onEventMainThread(MainScreenEvent event);
}
