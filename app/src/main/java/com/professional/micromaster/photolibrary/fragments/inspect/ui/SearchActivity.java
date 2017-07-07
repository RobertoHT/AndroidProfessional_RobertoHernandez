package com.professional.micromaster.photolibrary.fragments.inspect.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.professional.micromaster.photolibrary.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String tags = getIntent().getStringExtra(InspectFragment.KEY_TAGS);
        Log.d("TAG",tags);
    }
}
