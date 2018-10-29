package com.abrarkotwal.movies.views.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrarkotwal.movies.R;
import com.abrarkotwal.movies.application.AppController;
import com.abrarkotwal.movies.models.Movies;
import com.abrarkotwal.movies.views.details.core.MovieDetailsView;

import com.abrarkotwal.movies.views.details.core.MovieDetailPresenter;

import com.abrarkotwal.movies.views.details.dagger.DaggerMovieDetailsComponent;
import com.abrarkotwal.movies.views.details.dagger.MovieDetailsContextModule;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailsActivity extends AppCompatActivity {
    @Inject
    MovieDetailsView view;
    @Inject
    MovieDetailPresenter presenter;
    @BindView(R.id.singleMoviePoster)
    ImageView singleMoviePoster;
    @BindView(R.id.singleTitle)
    TextView singleTitle;
    @BindView(R.id.singleOverview)
    TextView singleOverview;
    @BindView(R.id.singleRating)
    TextView singleRating;
    @BindView(R.id.singleReleasedOn)
    TextView singleReleasedOn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int movieId = getIntent().getIntExtra("id", 0);

        DaggerMovieDetailsComponent.builder()
                .appComponent(AppController.getNetComponent())
                .movieDetailsContextModule(new MovieDetailsContextModule(this, movieId))
                .build()
                .inject(this);

        setContentView(view.view());
        presenter.onCreate();

        ButterKnife.bind(this,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @SuppressLint("SetTextI18n")
    public void SetData(Movies movies) {
        getSupportActionBar().setTitle(movies.getTitle());
        singleMoviePoster.setVisibility(View.VISIBLE);
        Glide.with(this).load("http://image.tmdb.org/t/p/w500/" + movies.getPoster_path()).into(singleMoviePoster);
        singleTitle.setText(movies.getTitle());
        singleRating.setText("Rating:- " + movies.getVote_average() + "(" + movies.getVote_count() + ")");
        singleReleasedOn.setText(TextUtils.isEmpty(movies.getRelease_date()) ? "missing text" : "Release On:- "+movies.getRelease_date());
        singleOverview.setText(movies.getOverview());
    }
}
