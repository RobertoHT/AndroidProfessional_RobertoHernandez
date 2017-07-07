package com.professional.micromaster.photolibrary.entities.service;

/**
 * Created by Roberto on 07/07/17.
 */

public class Photo {
    private String id;
    private String title;
    private String server;
    private String secret;
    private int farm;

    public String getFlickrUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_b.jpg";
    }
}
