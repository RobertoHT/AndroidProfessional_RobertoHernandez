package com.professional.micromaster.photolibrary.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Roberto on 04/07/17.
 */

@Database(name = PhotosDatabase.NAME, version = PhotosDatabase.VERSION)
public class PhotosDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Photos";
}
