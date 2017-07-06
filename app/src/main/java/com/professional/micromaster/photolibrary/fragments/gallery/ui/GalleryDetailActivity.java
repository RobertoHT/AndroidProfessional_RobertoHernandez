package com.professional.micromaster.photolibrary.fragments.gallery.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.lib.GlideImageLoader;
import com.professional.micromaster.photolibrary.lib.base.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryDetailActivity extends AppCompatActivity {
    @Bind(R.id.imgPhoto)
    ImageView imgPhoto;
    @Bind(R.id.titlePhoto)
    TextView titlePhoto;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    RelativeLayout container;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        ButterKnife.bind(this);
        Photo photo = (Photo) getIntent().getSerializableExtra(Photo.KEY_PHOTO);
        setupImageLoader();
        setViews(photo);
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                Snackbar.make(container, e.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    private void setViews(Photo photo) {
        if (photo != null) {
            imageLoader.load(imgPhoto, photo.getPhotoUrl());
            if (photo.getPhotoTitle() != null && !photo.getPhotoTitle().isEmpty()) {
                titlePhoto.setText(photo.getPhotoTitle());
            }
        }
    }
}
