package com.abrarkotwal.movies.views.movies.core;

import com.abrarkotwal.movies.application.ApiBuilder.ApiClient;
import com.abrarkotwal.movies.application.ApiBuilder.Api;
import com.abrarkotwal.movies.models.Result;
import com.abrarkotwal.movies.utils.UiUtils;
import com.abrarkotwal.movies.views.movies.MoviesListActivity;
import com.abrarkotwal.movies.utils.NetworkUtils;

import rx.Observable;

public class MoviesModel {

    MoviesListActivity context;
    ApiClient api;

    public MoviesModel(MoviesListActivity context, ApiClient api) {
        this.api = api;
        this.context = context;
    }


    Observable<Result> provideListHeroes() {
        return api.getUpcommingMovieList(Api.API_KEY);
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    public void gotoHeroDetailsActivity(int id) {
        context.goToHeroDetailsActivity(id);
    }


}
