package com.professional.micromaster.photolibrary.main;

import com.professional.micromaster.photolibrary.main.event.MainEvent;

/**
 * Created by Roberto on 05/07/17.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String path);
    void onEventMainThread(MainEvent event);
}
