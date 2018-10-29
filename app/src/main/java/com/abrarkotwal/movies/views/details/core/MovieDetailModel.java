package com.abrarkotwal.movies.views.details.core;


import com.abrarkotwal.movies.application.ApiBuilder.ApiClient;
import com.abrarkotwal.movies.application.ApiBuilder.Api;
import com.abrarkotwal.movies.models.Movies;
import com.abrarkotwal.movies.views.details.MovieDetailsActivity;
import com.abrarkotwal.movies.utils.NetworkUtils;

import rx.Observable;

public class MovieDetailModel {

    MovieDetailsActivity context;
    ApiClient api;

    public MovieDetailModel(MovieDetailsActivity context, ApiClient api) {
        this.api = api;
        this.context = context;
    }


    Observable<Movies> provideMovieDetail(int movieId) {
        return api.getMovieDetail(movieId,Api.API_KEY);
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    public void SetData(Movies movies) {
        context.SetData(movies);
    }
}
