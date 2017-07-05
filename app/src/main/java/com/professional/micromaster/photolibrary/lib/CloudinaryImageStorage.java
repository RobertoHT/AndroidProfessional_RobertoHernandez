package com.professional.micromaster.photolibrary.lib;

import android.content.Context;
import android.os.AsyncTask;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;
import com.professional.micromaster.photolibrary.lib.base.ImageStorageFinishedListener;
import com.professional.micromaster.photolibrary.lib.base.ImageStorage;

import java.io.File;
import java.util.Map;

/**
 * Created by Roberto on 05/07/17.
 */

public class CloudinaryImageStorage implements ImageStorage {
    private Cloudinary cloudinary;

    public CloudinaryImageStorage(Context context) {
        this.cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }

    @Override
    public String getImageUrl(String id) {
        return cloudinary.url().generate(id);
    }

    @Override
    public void upload(final File file, final String id, final ImageStorageFinishedListener listener) {
        new AsyncTask<Void, Void, Void>() {
            boolean success = false;
            @Override
            protected Void doInBackground(Void... voids) {
                Map params = ObjectUtils.asMap("public_id", id);
                try {
                    cloudinary.uploader().upload(file, params);
                    success = true;
                } catch (Exception e) {
                    listener.onError(e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (success) {
                    listener.onSuccess();
                }
            }
        }.execute();
    }
}
