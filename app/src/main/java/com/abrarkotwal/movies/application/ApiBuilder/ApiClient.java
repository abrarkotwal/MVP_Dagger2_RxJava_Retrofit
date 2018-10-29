package com.abrarkotwal.movies.application.ApiBuilder;

import com.abrarkotwal.movies.models.Movies;
import com.abrarkotwal.movies.models.Result;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiClient {

    @GET("movie/upcoming")
    Observable<Result> getUpcommingMovieList(@Query("api_key") String apiKey);


    @GET("movie/{movie_id}")
    Observable<Movies> getMovieDetail(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
