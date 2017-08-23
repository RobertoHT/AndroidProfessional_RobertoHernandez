package com.professional.micromaster.photolibrary.lib.base;

/**
 * Created by Roberto on 05/07/17.
 */

public interface ImageStorageFinishedListener {
    void onSuccess();
    void onError(String error);
}
