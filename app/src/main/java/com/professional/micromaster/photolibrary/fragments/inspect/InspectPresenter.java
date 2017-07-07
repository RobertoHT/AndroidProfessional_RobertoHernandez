package com.professional.micromaster.photolibrary.fragments.inspect;

import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.inspect.event.InspectEvent;

/**
 * Created by Roberto on 07/07/17.
 */

public interface InspectPresenter {
    void onCreate();
    void onDestroy();

    void dismissPhoto();
    void getNextPhoto();
    void savePhoto(Photo photo);
    void imageReady();
    void imageError(String error);

    void onEventMainThread(InspectEvent event);
}
