package com.abrarkotwal.movies.views.splash.dagger;

import com.abrarkotwal.movies.views.splash.SplashScreenActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashContextModule {

    SplashScreenActivity splashContext;

    public SplashContextModule(SplashScreenActivity context) {
        this.splashContext = context;
    }

    @SplashScope
    @Provides
    SplashScreenActivity provideSplashContext() {
        return splashContext;
    }


}
