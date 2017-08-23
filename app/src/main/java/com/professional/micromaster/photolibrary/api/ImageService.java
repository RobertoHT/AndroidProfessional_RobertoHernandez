package com.professional.micromaster.photolibrary.api;

import com.professional.micromaster.photolibrary.entities.service.PhotoSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Roberto on 07/07/17.
 */

public interface ImageService {
    @GET("/services/rest/?method=flickr.photos.search")
    Call<PhotoSearchResponse> search(@Query("tags") String tags, @Query("per_page") int perPage);
}
