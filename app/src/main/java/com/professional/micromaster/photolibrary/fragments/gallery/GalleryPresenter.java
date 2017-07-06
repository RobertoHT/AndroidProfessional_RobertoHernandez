package com.professional.micromaster.photolibrary.fragments.gallery;

import com.professional.micromaster.photolibrary.fragments.gallery.event.GalleryEvent;

/**
 * Created by Roberto on 06/07/17.
 */

public interface GalleryPresenter {
    void onCreate();
    void onDestroy();

    void getPhotos();
    void onEventMainThread(GalleryEvent event);
}
