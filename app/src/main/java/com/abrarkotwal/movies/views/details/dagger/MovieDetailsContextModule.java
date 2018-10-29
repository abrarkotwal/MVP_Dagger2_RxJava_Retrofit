package com.abrarkotwal.movies.views.details.dagger;

import com.abrarkotwal.movies.application.ApiBuilder.ApiClient;
import com.abrarkotwal.movies.views.details.MovieDetailsActivity;
import com.abrarkotwal.movies.views.details.core.MovieDetailModel;
import com.abrarkotwal.movies.views.details.core.MovieDetailsView;
import com.abrarkotwal.movies.views.details.core.MovieDetailPresenter;

import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;


@Module
public class MovieDetailsContextModule {

    MovieDetailsActivity detailsContext;
    int movieId;

    public MovieDetailsContextModule(MovieDetailsActivity context, int movieId) {
        this.detailsContext = context;
        this.movieId = movieId;
    }

    @Provides
    MovieDetailsView provideView() {
        return  new MovieDetailsView(detailsContext);
    }

    @MoviesDetailScope
    @Provides
    MovieDetailPresenter providePresenter(RxSchedulers schedulers, MovieDetailsView view, MovieDetailModel model) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new MovieDetailPresenter(schedulers, model, view, subscriptions,movieId);
    }



    @MoviesDetailScope
    @Provides
    MovieDetailsActivity provideContext() {
        return detailsContext;
    }

    @MoviesDetailScope
    @Provides
    MovieDetailModel provideModel(ApiClient api) {
        return new MovieDetailModel(detailsContext, api);
    }
}

