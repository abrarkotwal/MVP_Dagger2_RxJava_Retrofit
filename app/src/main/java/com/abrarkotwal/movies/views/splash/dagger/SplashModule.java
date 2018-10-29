package com.abrarkotwal.movies.views.splash.dagger;

import com.abrarkotwal.movies.application.ApiBuilder.ApiClient;
import com.abrarkotwal.movies.views.splash.SplashScreenActivity;
import com.abrarkotwal.movies.views.splash.core.SplashModel;
import com.abrarkotwal.movies.views.splash.core.SplashPresenter;
import com.abrarkotwal.movies.views.splash.core.SplashView;
import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;


@Module
public class SplashModule {


    @SplashScope
    @Provides
    SplashPresenter providePresenter(RxSchedulers schedulers, SplashModel model) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new SplashPresenter(model, schedulers, compositeSubscription);
    }


    @SplashScope
    @Provides
    SplashView provideSplashView(SplashScreenActivity context) {
        return new SplashView(context);
    }


    @SplashScope
    @Provides
    SplashModel provideSplashModel(ApiClient api, SplashScreenActivity ctx) {
        return new SplashModel(api, ctx);
    }

}

