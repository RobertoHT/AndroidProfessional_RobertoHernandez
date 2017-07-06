package com.professional.micromaster.photolibrary.entities;

import com.professional.micromaster.photolibrary.db.PhotosDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by Roberto on 04/07/17.
 */

@Table(database = PhotosDatabase.class)
public class Photo extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String KEY_PHOTO = "photoView";
    @PrimaryKey(autoincrement = true)
    private int photoId;
    @Column
    private String photoUrl;
    @Column
    private String photoTitle;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }
}
