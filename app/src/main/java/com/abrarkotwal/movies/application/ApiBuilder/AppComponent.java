package com.abrarkotwal.movies.application.ApiBuilder;


import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import dagger.Component;

@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, RxModule.class, Api.class})
public interface AppComponent {

    RxSchedulers rxSchedulers();
    ApiClient apiService();
}
