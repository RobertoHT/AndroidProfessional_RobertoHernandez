package com.professional.micromaster.photolibrary.fragments.inspect.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.inspect.SearchPresenter;
import com.professional.micromaster.photolibrary.fragments.inspect.SearchPresenterImpl;
import com.professional.micromaster.photolibrary.fragments.inspect.ui.swipe.OnSwipeTouchListener;
import com.professional.micromaster.photolibrary.fragments.inspect.ui.swipe.SwipeGestureListener;
import com.professional.micromaster.photolibrary.lib.GlideImageLoader;
import com.professional.micromaster.photolibrary.lib.base.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchView, SwipeGestureListener {
    @Bind(R.id.imgPhoto)
    ImageView imgPhoto;
    @Bind(R.id.titlePhoto)
    TextView titlePhoto;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.container)
    RelativeLayout container;

    private Photo currentPhoto;
    private ImageLoader imageLoader;
    private SearchPresenter presenter;
    private String tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        tags = getIntent().getStringExtra(InspectFragment.KEY_TAGS);
        setupPresenter();
        setupImageLoader();
        setupGestureDetection();

        presenter.onCreate();
        presenter.getNextPhoto(tags);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupPresenter() {
        presenter = new SearchPresenterImpl(this);
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
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

    private void setupGestureDetection() {
        OnSwipeTouchListener listener = new OnSwipeTouchListener(this, this);
        imgPhoto.setOnTouchListener(listener);
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
        titlePhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        titlePhoto.setVisibility(View.GONE);
    }

    @Override
    public void onSwipeRight() {
        if (currentPhoto != null) {
            presenter.savePhoto(currentPhoto, SearchView.SWIPE_RIGHT);
        }
    }

    @Override
    public void onSwipeLeft() {
        if (currentPhoto != null) {
            presenter.savePhoto(currentPhoto, SearchView.SWIPE_LEFT);
        }
    }

    @Override
    public void onSwipeUp() {
        presenter.dismissPhoto(SearchView.SWIPE_UP);
    }

    @Override
    public void onSwipeDown() {
        presenter.dismissPhoto(SearchView.SWIPE_DOWN);
    }

    @Override
    public void leftAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        anim.setAnimationListener(getAnimationListener());
        imgPhoto.startAnimation(anim);
    }

    @Override
    public void rightAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.right_animation);
        anim.setAnimationListener(getAnimationListener());
        imgPhoto.startAnimation(anim);
    }

    @Override
    public void upAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.up_animation);
        anim.setAnimationListener(getAnimationListener());
        imgPhoto.startAnimation(anim);
    }

    @Override
    public void downAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.down_animation);
        anim.setAnimationListener(getAnimationListener());
        imgPhoto.startAnimation(anim);
    }

    private Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        };
    }

    private void clearImage() {
        imgPhoto.setImageResource(0);
    }

    @Override
    public void onPhotoSaved() {
        Snackbar.make(container, R.string.search_notice_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPhoto(Photo photo) {
        this.currentPhoto = photo;
        imageLoader.load(imgPhoto, photo.getPhotoUrl());
        titlePhoto.setText(photo.getPhotoTitle());
    }

    @Override
    public void onGetPhotoError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }
}
