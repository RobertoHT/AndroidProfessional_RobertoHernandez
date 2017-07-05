package com.professional.micromaster.photolibrary.fragments.main.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.main.MainScreenPresenter;
import com.professional.micromaster.photolibrary.fragments.main.MainScreenPresenterImpl;
import com.professional.micromaster.photolibrary.lib.GlideImageLoader;
import com.professional.micromaster.photolibrary.lib.base.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment implements MainScreenView {
    @Bind(R.id.imgPhoto)
    ImageView imgPhoto;
    @Bind(R.id.titlePhoto)
    TextView titlePhoto;
    @Bind(R.id.btnShare)
    FloatingActionButton btnShare;
    @Bind(R.id.txtNoPhotos)
    TextView txtNoPhotos;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    CoordinatorLayout container;

    private MainScreenPresenter presenter;
    private ImageLoader imageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        ButterKnife.bind(this, view);
        presenter.getLastPhoto();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupImageLoader();
        presenter = new MainScreenPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader();
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        setInputs(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        setInputs(View.GONE);
    }

    @Override
    public void showMessage() {
        txtNoPhotos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessage() {
        txtNoPhotos.setVisibility(View.GONE);
    }

    @Override
    public void setPhoto(Photo photo) {
        imageLoader.load(imgPhoto, photo.getPhotoUrl());
        if (photo.getPhotoTitle() != null && !photo.getPhotoTitle().isEmpty()) {
            titlePhoto.setText(photo.getPhotoTitle());
        }
    }

    @Override
    @OnClick(R.id.btnShare)
    public void sharePhoto() {
        Log.d("SHARE", "CLICK");
    }

    @Override
    public void onGetImageError(String error) {
        String msgError = String.format(getString(R.string.mainFragmen_msg_error), error);
        Snackbar.make(container, msgError, Snackbar.LENGTH_SHORT).show();
    }

    private void setInputs(int VISIBILITY) {
        imgPhoto.setVisibility(VISIBILITY);
        titlePhoto.setVisibility(VISIBILITY);
        btnShare.setVisibility(VISIBILITY);
    }
}
