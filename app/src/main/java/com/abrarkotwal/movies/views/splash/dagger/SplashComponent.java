package com.abrarkotwal.movies.views.splash.dagger;

import com.abrarkotwal.movies.application.ApiBuilder.AppComponent;
import com.abrarkotwal.movies.views.splash.SplashScreenActivity;

import dagger.Component;

@SplashScope
@Component(modules = {SplashContextModule.class, SplashModule.class}, dependencies = {AppComponent.class})
public interface SplashComponent {
    void inject(SplashScreenActivity activity);
}
