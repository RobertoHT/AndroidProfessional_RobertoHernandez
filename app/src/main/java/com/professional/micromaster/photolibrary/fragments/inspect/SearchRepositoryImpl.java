package com.professional.micromaster.photolibrary.fragments.inspect;

import com.professional.micromaster.photolibrary.api.ImageClient;
import com.professional.micromaster.photolibrary.api.ImageService;
import com.professional.micromaster.photolibrary.entities.Photo;
import com.professional.micromaster.photolibrary.entities.service.PhotoSearchResponse;
import com.professional.micromaster.photolibrary.fragments.inspect.event.InspectEvent;
import com.professional.micromaster.photolibrary.lib.GreenRobotEventBus;
import com.professional.micromaster.photolibrary.lib.base.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roberto on 07/07/17.
 */

public class SearchRepositoryImpl implements SearchRepository {
    private EventBus eventBus;

    public SearchRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void getNextPhoto(String tags) {
        ImageClient client = new ImageClient();
        ImageService service = client.getFlickrService();

        Call<PhotoSearchResponse> call = service.search(tags, SearchRepository.PHOTO_SIZE);
        call.enqueue(new Callback<PhotoSearchResponse>() {
            @Override
            public void onResponse(Call<PhotoSearchResponse> call, Response<PhotoSearchResponse> response) {
                if (response.isSuccess()) {
                    PhotoSearchResponse searchResponse = response.body();
                    Photo photo = new Photo();
                    photo.setPhotoUrl(searchResponse.getPhotos().getPhoto().get(0).getFlickrUrl());
                    photo.setPhotoTitle(searchResponse.getPhotos().getPhoto().get(0).getTitle());
                    post(photo);
                } else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<PhotoSearchResponse> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void savePhoto(Photo photo) {
        photo.save();
        post();
    }

    private void post() {
        post(InspectEvent.SAVE_EVENT, null, null);
    }

    private void post(String error) {
        post(InspectEvent.NEXT_EVENT, error, null);
    }

    private void post(Photo photo) {
        post(InspectEvent.NEXT_EVENT, null, photo);
    }

    private void post(int type, String error, Photo photo) {
        InspectEvent event = new InspectEvent();
        event.setType(type);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }
}
