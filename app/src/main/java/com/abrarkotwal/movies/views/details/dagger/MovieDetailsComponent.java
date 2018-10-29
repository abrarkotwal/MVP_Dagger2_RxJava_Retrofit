package com.abrarkotwal.movies.views.details.dagger;

import com.abrarkotwal.movies.application.ApiBuilder.AppComponent;
import com.abrarkotwal.movies.views.details.MovieDetailsActivity;


import dagger.Component;

@MoviesDetailScope
@Component(dependencies = {AppComponent.class} ,modules = {MovieDetailsContextModule.class})
public interface MovieDetailsComponent {
    void inject(MovieDetailsActivity context);
}
