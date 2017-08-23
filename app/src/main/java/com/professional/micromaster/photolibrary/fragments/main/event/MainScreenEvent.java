package com.professional.micromaster.photolibrary.fragments.main.event;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 04/07/17.
 */

public class MainScreenEvent {
    public final static int READ_EVENT = 0;

    private int type;
    private Photo photo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
