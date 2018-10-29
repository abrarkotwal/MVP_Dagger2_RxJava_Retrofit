package com.abrarkotwal.movies.application.ApiBuilder;

import com.abrarkotwal.movies.utils.rx.AppRxSchedulers;
import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;


@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
