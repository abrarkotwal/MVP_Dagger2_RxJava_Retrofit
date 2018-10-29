package com.abrarkotwal.movies.views.details.core;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.abrarkotwal.movies.R;
import com.abrarkotwal.movies.views.details.MovieDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailsView {

    @BindView(R.id.mainLayout)
    RelativeLayout layout;

    MovieDetailsActivity activity;
    static View view;

    public MovieDetailsView(MovieDetailsActivity activity) {
        this.activity = activity;
        view = LayoutInflater.from(activity).inflate(R.layout.activity_single_movie_display, layout, true);
        ButterKnife.bind(this, view);
    }

    public static View view() {
        return view;
    }

}