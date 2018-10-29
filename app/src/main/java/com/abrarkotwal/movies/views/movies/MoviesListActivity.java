package com.abrarkotwal.movies.views.movies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abrarkotwal.movies.application.AppController;
import com.abrarkotwal.movies.utils.UiUtils;
import com.abrarkotwal.movies.views.details.MovieDetailsActivity;
import com.abrarkotwal.movies.views.movies.core.MoviesPresenter;
import com.abrarkotwal.movies.views.movies.core.MoviesView;
import com.abrarkotwal.movies.views.movies.dagger.DaggerMoviesComponent;
import com.abrarkotwal.movies.views.movies.dagger.MoviesContextModule;

import javax.inject.Inject;


public class MoviesListActivity extends AppCompatActivity {

    @Inject
    MoviesView view;
    @Inject
    MoviesPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMoviesComponent.builder()
                .appComponent(AppController.getNetComponent())
                .moviesContextModule(new MoviesContextModule(this))
                .build()
                .inject(this);
        setContentView(view.view());
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void goToHeroDetailsActivity(int id) {
        Intent in = new Intent(this, MovieDetailsActivity.class);
        in.putExtra("id", id);
        startActivity(in);

    }

}