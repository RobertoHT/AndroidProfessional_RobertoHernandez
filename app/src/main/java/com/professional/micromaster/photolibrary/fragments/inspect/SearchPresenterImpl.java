package com.professional.micromaster.photolibrary.fragments.inspect;

import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.inspect.event.InspectEvent;
import com.professional.micromaster.photolibrary.fragments.inspect.ui.SearchView;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Roberto on 07/07/17.
 */

public class SearchPresenterImpl implements SearchPresenter {
    private EventBus eventBus;
    private SearchView view;
    private GetNextPhotoInteractor nextInteractor;
    private SavePhotoInteractor saveInteractor;
    private String tags;

    public SearchPresenterImpl(SearchView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.nextInteractor = new GetNextPhotoInteractorImpl();
        this.saveInteractor = new SavePhotoInteractorImpl();
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
    public void getNextPhoto(String tags) {
        this.tags = tags;
        if (view != null) {
            view.hideUIElements();
            view.showProgress();
        }
        nextInteractor.execute(this.tags);
    }

    @Override
    public void savePhoto(Photo photo, int swipe) {
        if (view != null) {
            if (swipe == SearchView.SWIPE_RIGHT) {
                view.rightAnimation();
            }
            else if (swipe == SearchView.SWIPE_LEFT) {
                view.leftAnimation();
            }
            view.hideUIElements();
            view.showProgress();
        }
        saveInteractor.execute(photo);
    }

    @Override
    public void dismissPhoto(int swipe) {
        if (view != null) {
            if (swipe == SearchView.SWIPE_UP) {
                view.upAnimation();
            }
            else if (swipe == SearchView.SWIPE_DOWN) {
                view.downAnimation();
            }
        }
        getNextPhoto(tags);
    }

    @Override
    public void imageReady() {
        if (view != null) {
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if (view != null) {
            view.onGetPhotoError(error);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(InspectEvent event) {
        if (view != null) {
            String error = event.getError();
            if (error != null) {
                view.hideProgress();
                view.onGetPhotoError(error);
            } else {
                if (event.getType() == InspectEvent.NEXT_EVENT) {
                    view.setPhoto(event.getPhoto());
                } else if (event.getType() == InspectEvent.SAVE_EVENT) {
                    view.onPhotoSaved();
                    nextInteractor.execute(tags);
                }
            }
        }
    }
}
