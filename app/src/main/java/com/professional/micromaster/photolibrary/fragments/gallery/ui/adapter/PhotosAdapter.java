package com.professional.micromaster.photolibrary.fragments.gallery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.lib.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roberto on 06/07/17.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private List<Photo> photoList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public PhotosAdapter(List<Photo> photoList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.photoList = photoList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_element_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo currentPhoto = photoList.get(position);

        holder.setOnItemClickListener(currentPhoto, onItemClickListener);
        imageLoader.load(holder.imgRowPhoto, currentPhoto.getPhotoUrl());
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.photoList = photos;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgRowPhoto)
        ImageView imgRowPhoto;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Photo currentPhoto, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(currentPhoto);
                }
            });
        }
    }
}
