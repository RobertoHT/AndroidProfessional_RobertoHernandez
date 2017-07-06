package com.professional.micromaster.photolibrary.fragments.inspect;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.professional.micromaster.photolibrary.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InspectFragment extends Fragment {
    public static final String KEY_TAGS = "TAGS";
    @Bind(R.id.txtSearch)
    EditText txtSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspect, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnSearch)
    public void searchImages() {
        String tags = txtSearch.getText().toString();
        if (!tags.isEmpty()) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            intent.putExtra(KEY_TAGS, tags);
            startActivity(intent);
        } else {
            txtSearch.setError(getString(R.string.mainInspect_error_message));
        }
    }
}
