package com.abrarkotwal.movies.views.splash.core;


import com.abrarkotwal.movies.application.ApiBuilder.ApiClient;
import com.abrarkotwal.movies.views.splash.SplashScreenActivity;
import com.abrarkotwal.movies.utils.NetworkUtils;

import rx.Observable;

public class SplashModel {


    ApiClient api;
    SplashScreenActivity splashContext;

    public SplashModel(ApiClient api, SplashScreenActivity splashContext) {
        this.api = api;
        this.splashContext = splashContext;

    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(splashContext);
    }


    public void gotoHeroesListActivity() {
        splashContext.showHeroesListActivity();
    }
}
