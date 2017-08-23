package com.professional.micromaster.photolibrary.fragments.gallery;

import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.gallery.event.GalleryEvent;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.List;

/**
 * Created by Roberto on 06/07/17.
 */

public class GalleryRepositoryImpl implements GalleryRepository {
    private EventBus eventBus;

    public GalleryRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void getSavedPhotos() {
        FlowCursorList<Photo> storedPhotos = new FlowCursorList<Photo>(false, Photo.class);
        post(GalleryEvent.READ_EVENT, storedPhotos.getAll());
        storedPhotos.close();
    }

    private void post(int type, List<Photo> photoList) {
        GalleryEvent event = new GalleryEvent();
        event.setType(type);
        event.setPhotoList(photoList);
        eventBus.post(event);
    }
}
