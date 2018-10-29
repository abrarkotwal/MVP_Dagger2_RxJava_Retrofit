package com.abrarkotwal.movies.application.ApiBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class Api {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY   = "b7cd3340a794e5a2f35e3abb820b497f";

    @AppScope
    @Provides
    ApiClient provideApiService(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();

        return  retrofit.create(ApiClient.class);
    }
}
