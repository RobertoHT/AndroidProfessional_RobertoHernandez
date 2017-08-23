package com.professional.micromaster.photolibrary.lib.base;

import android.widget.ImageView;

/**
 * Created by Roberto on 04/07/17.
 */

public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
