package com.professional.micromaster.photolibrary.main.event;

/**
 * Created by Roberto on 05/07/17.
 */

public class MainEvent {
    public final static int UPLOAD_INIT = 0;
    public final static int UPLOAD_COMPLETE = 1;
    public final static int UPLOAD_ERROR = 2;
    private int type;
    private String error;

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
}
