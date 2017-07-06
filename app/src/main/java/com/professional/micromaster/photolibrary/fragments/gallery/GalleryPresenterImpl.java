package com.professional.micromaster.photolibrary.fragments.gallery;

import com.professional.micromaster.photolibrary.fragments.gallery.event.GalleryEvent;
import com.professional.micromaster.photolibrary.fragments.gallery.ui.GalleryView;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Roberto on 06/07/17.
 */

public class GalleryPresenterImpl implements GalleryPresenter {
    private EventBus eventBus;
    private GalleryView view;
    private GalleryInteractor interactor;

    public GalleryPresenterImpl(GalleryView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new GalleryInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void getPhotos() {
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(GalleryEvent event) {
        if (view != null && event.getType() == GalleryEvent.READ_EVENT) {
            if (event.getPhotoList() != null && event.getPhotoList().size() > 0) {
                view.setPhotos(event.getPhotoList());
            } else {
                view.showMessage();
            }
        }
    }
}
