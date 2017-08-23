package com.professional.micromaster.photolibrary.fragments.inspect.event;

import com.professional.micromaster.photolibrary.entities.Photo;

/**
 * Created by Roberto on 07/07/17.
 */

public class InspectEvent {
    public final static int NEXT_EVENT = 0;
    public final static int SAVE_EVENT = 1;
    private int type;
    private String error;
    private Photo photo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
