package com.abrarkotwal.movies.views.movies.core;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.abrarkotwal.movies.models.Movies;
import com.abrarkotwal.movies.utils.UiUtils;
import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class MoviesPresenter {

    MoviesView view;
    MoviesModel model;
    RxSchedulers rxSchedulers;
    CompositeSubscription subscriptions;
    ArrayList<Movies> movies = new ArrayList<>();
    ProgressDialog progressDialog;

    public MoviesPresenter(RxSchedulers schedulers, MoviesModel model, MoviesView view, CompositeSubscription sub) {
        this.rxSchedulers = schedulers;
        this.view = view;
        this.model = model;
        this.subscriptions = sub;
        progressDialog = UiUtils.showProgressDialog(model.context);
    }

    public void onCreate() {
        subscriptions.add(getHeroesList());
        subscriptions.add(respondToClick());
    }

    public void onDestroy() {
        subscriptions.clear();
    }


    private Subscription respondToClick() {
        return view.itemClicks().subscribe(integer -> model.gotoHeroDetailsActivity(movies.get(integer).getId()));
    }

    private Subscription getHeroesList() {

        return model.isNetworkAvailable().doOnNext(networkAvailable -> {
            if (!networkAvailable) {
                Log.d("Abrar","No Network");
                progressDialog.dismiss();
            }
        })
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.provideListHeroes())
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(heroes -> {
                    if (heroes != null) {
                        progressDialog.dismiss();
                        view.swapAdapter((ArrayList<Movies>) heroes.getResults());
                        movies = (ArrayList<Movies>) heroes.getResults();
                    }
                }, throwable -> UiUtils.handleThrowable(throwable,model.context));

    }
}
