package com.professional.micromaster.photolibrary.entities.service;

import com.professional.micromaster.photolibrary.entities.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roberto on 07/07/17.
 */

public class Photos {
    private int page;
    private int pages;
    private int perPage;
    private long total;
    private List<Photo> photo = new ArrayList<>();
}
