package com.professional.micromaster.photolibrary.fragments.main;

import com.professional.micromaster.photolibrary.fragments.main.event.MainScreenEvent;
import com.professional.micromaster.photolibrary.fragments.main.ui.MainScreenView;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Roberto on 04/07/17.
 */

public class MainScreenPresenterImpl implements MainScreenPresenter {
    private EventBus eventBus;
    private MainScreenView view;
    private MainScreenInteractor interactor;

    public MainScreenPresenterImpl(MainScreenView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new MainScreenInteractorImpl();
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
    public void getLastPhoto() {
        if (view != null) {
            view.showProgress();
            view.hideUIElements();
            view.hideMessage();
        }
        interactor.execute();
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
            view.onGetImageError(error);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainScreenEvent event) {
        if (view != null && event.getType() == MainScreenEvent.READ_EVENT) {
            if (event.getPhoto() != null) {
                view.setPhoto(event.getPhoto());
            } else {
                view.hideProgress();
                view.showMessage();
            }
        }
    }
}
