package com.professional.micromaster.photolibrary.fragments.inspect;

import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.inspect.event.InspectEvent;

/**
 * Created by Roberto on 07/07/17.
 */

public interface SearchPresenter {
    void onCreate();
    void onDestroy();

    void getNextPhoto(String tags);
    void savePhoto(Photo photo, int swipe);
    void dismissPhoto(int swipe);
    void imageReady();
    void imageError(String error);

    void onEventMainThread(InspectEvent event);
}
