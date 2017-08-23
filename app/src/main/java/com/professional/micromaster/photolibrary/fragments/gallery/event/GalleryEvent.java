package com.professional.micromaster.photolibrary.fragments.gallery.event;

import com.professional.micromaster.photolibrary.entities.Photo;

import java.util.List;

/**
 * Created by Roberto on 06/07/17.
 */

public class GalleryEvent {
    public final static int READ_EVENT = 0;
    private int type;
    private List<Photo> photoList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
