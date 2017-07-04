package com.professional.micromaster.photolibrary;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Roberto on 03/07/17.
 */

public class PhotoLibraryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }
}
