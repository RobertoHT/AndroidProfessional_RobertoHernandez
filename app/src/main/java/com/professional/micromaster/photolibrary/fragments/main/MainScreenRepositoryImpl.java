package com.professional.micromaster.photolibrary.fragments.main;

import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.entities.Photo_Table;
import com.professional.micromaster.photolibrary.fragments.main.event.MainScreenEvent;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by Roberto on 04/07/17.
 */

public class MainScreenRepositoryImpl implements MainScreenRepository {
    private EventBus eventBus;

    public MainScreenRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void getLastPhoto() {
        Photo photo = SQLite
                .select()
                .from(Photo.class)
                .where()
                .orderBy(Photo_Table.photoId, false)
                .limit(1)
                .querySingle();

        post(MainScreenEvent.READ_EVENT, photo);
    }

    private void post(int type, Photo photo) {
        MainScreenEvent event = new MainScreenEvent();
        event.setType(type);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}
