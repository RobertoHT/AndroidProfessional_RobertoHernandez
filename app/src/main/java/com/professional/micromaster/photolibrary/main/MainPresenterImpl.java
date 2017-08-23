package com.professional.micromaster.photolibrary.main;

import android.content.Context;

import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;
import com.professional.micromaster.photolibrary.main.event.MainEvent;
import com.professional.micromaster.photolibrary.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Roberto on 05/07/17.
 */

public class MainPresenterImpl implements MainPresenter {
    private EventBus eventBus;
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view, Context context) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        interactor = new MainInteractorImpl(context);
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
    public void uploadPhoto(String path) {
        interactor.execute(path);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        if (view != null) {
            switch (event.getType()) {
                case MainEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case MainEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case MainEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }
}
