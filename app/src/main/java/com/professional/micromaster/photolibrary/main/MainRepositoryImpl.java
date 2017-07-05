package com.professional.micromaster.photolibrary.main;

import android.content.Context;

import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.lib.CloudinaryImageStorage;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;
import com.professional.micromaster.photolibrary.lib.base.ImageStorageFinishedListener;
import com.professional.micromaster.photolibrary.lib.base.ImageStorage;
import com.professional.micromaster.photolibrary.main.event.MainEvent;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Roberto on 05/07/17.
 */

public class MainRepositoryImpl implements MainRepository {
    private EventBus eventBus;
    private ImageStorage imageStorage;

    public MainRepositoryImpl(Context context) {
        eventBus = GreenRobotEventBus.getInstance();
        imageStorage = new CloudinaryImageStorage(context);
    }

    @Override
    public void uploadPhoto(String path) {
        final String photoId = getIdPhoto();
        post(MainEvent.UPLOAD_INIT);
        imageStorage.upload(new File(path), photoId, new ImageStorageFinishedListener() {
            @Override
            public void onSuccess() {
                Photo photo = new Photo();
                String url = imageStorage.getImageUrl(photoId);
                photo.setPhotoUrl(url);
                photo.save();

                post(MainEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(MainEvent.UPLOAD_ERROR, error);
            }
        });
    }

    private String getIdPhoto() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        return String.format("%d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);
    }

    private void post(int type) {
        post(type, null);
    }

    private void post(int type, String error) {
        MainEvent event = new MainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}