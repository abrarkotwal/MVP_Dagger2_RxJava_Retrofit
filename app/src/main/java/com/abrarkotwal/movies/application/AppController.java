package com.abrarkotwal.movies.application;

import android.app.Application;

import com.abrarkotwal.movies.application.ApiBuilder.AppComponent;
import com.abrarkotwal.movies.application.ApiBuilder.AppContextModule;
import com.abrarkotwal.movies.application.ApiBuilder.DaggerAppComponent;


import timber.log.BuildConfig;
import timber.log.Timber;

public class AppController extends Application {


    private static AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }


    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }

    public static AppComponent getNetComponent() {
        return appComponent;
    }

}
