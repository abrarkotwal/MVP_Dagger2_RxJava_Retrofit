package com.abrarkotwal.movies.views.details.core;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.abrarkotwal.movies.utils.UiUtils;
import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class MovieDetailPresenter {

    MovieDetailsView view;
    MovieDetailModel model;
    RxSchedulers rxSchedulers;
    CompositeSubscription subscriptions;
    int movieId;
    private String TAG="Abrar";
    ProgressDialog progressDialog;

    public MovieDetailPresenter(RxSchedulers schedulers, MovieDetailModel model, MovieDetailsView view, CompositeSubscription sub, int movieId) {
        this.rxSchedulers = schedulers;
        this.view = view;
        this.model = model;
        this.subscriptions = sub;
        this.movieId = movieId;
        progressDialog = UiUtils.showProgressDialog(model.context);
    }

    public void onCreate() {
        subscriptions.add(getMovieDetail());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Subscription getMovieDetail() {
        return model.isNetworkAvailable()
                .doOnNext(networkAvailable -> {
                    if (!networkAvailable) {
                        Log.d(TAG,"No Network");
                        progressDialog.dismiss();
                    }
                })
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.provideMovieDetail(movieId))
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(movies -> {
                    progressDialog.dismiss();
                    model.SetData(movies);
                }, throwable -> UiUtils.handleThrowable(throwable,model.context));
    }
}
