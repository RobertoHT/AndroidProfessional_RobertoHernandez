package com.professional.micromaster.photolibrary.lib.base;

import java.io.File;

/**
 * Created by Roberto on 05/07/17.
 */

public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}
