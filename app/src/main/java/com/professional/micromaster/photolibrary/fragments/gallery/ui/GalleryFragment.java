package com.professional.micromaster.photolibrary.fragments.gallery.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.professional.micromaster.photolibrary.PhotoLibraryApp;
import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.fragments.gallery.GalleryPresenter;
import com.professional.micromaster.photolibrary.fragments.gallery.GalleryPresenterImpl;
import com.professional.micromaster.photolibrary.fragments.gallery.ui.adapter.OnItemClickListener;
import com.professional.micromaster.photolibrary.fragments.gallery.ui.adapter.PhotosAdapter;
import com.professional.micromaster.photolibrary.lib.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements GalleryView, OnItemClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.txtNoPhotos)
    TextView txtNoPhotos;

    private PhotosAdapter adapter;
    private GalleryPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPresenter();
        presenter.onCreate();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        setupRecyclerView();
        presenter.getPhotos();
        return view;
    }

    private void setupPresenter() {
        presenter = new GalleryPresenterImpl(this);
    }

    private void setupAdapter() {
        PhotoLibraryApp app = (PhotoLibraryApp) getActivity().getApplication();
        adapter = new PhotosAdapter(new ArrayList<Photo>(), new GlideImageLoader(getActivity()), this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        recyclerView.setVisibility(View.VISIBLE);
        txtNoPhotos.setVisibility(View.GONE);
        adapter.setPhotos(photos);
    }

    @Override
    public void showMessage() {
        recyclerView.setVisibility(View.GONE);
        txtNoPhotos.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(Photo photo) {
        startActivity(new Intent(getActivity(), GalleryDetailActivity.class));
    }
}
