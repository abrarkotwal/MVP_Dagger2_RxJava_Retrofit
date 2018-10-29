package com.abrarkotwal.movies.views.movies.dagger;

import com.abrarkotwal.movies.application.ApiBuilder.AppComponent;
import com.abrarkotwal.movies.views.movies.MoviesListActivity;

import dagger.Component;

@MoviesScope
@Component(dependencies = {AppComponent.class} , modules = {MoviesContextModule.class})
public interface MoviesComponent {

    void inject(MoviesListActivity heroesActivity);
}
