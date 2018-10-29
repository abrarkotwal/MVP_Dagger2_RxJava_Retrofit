package com.abrarkotwal.movies.views.movies.dagger;

import com.abrarkotwal.movies.application.ApiBuilder.ApiClient;
import com.abrarkotwal.movies.views.movies.MoviesListActivity;
import com.abrarkotwal.movies.views.movies.core.MoviesModel;
import com.abrarkotwal.movies.views.movies.core.MoviesPresenter;
import com.abrarkotwal.movies.views.movies.core.MoviesView;
import com.abrarkotwal.movies.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class MoviesContextModule {

    MoviesListActivity heroesListContext;

    public MoviesContextModule(MoviesListActivity context) {
        this.heroesListContext = context;
    }



    @MoviesScope
    @Provides
    MoviesView provideView() {
        return new MoviesView(heroesListContext);
    }

    @MoviesScope
    @Provides
    MoviesPresenter providePresenter(RxSchedulers schedulers, MoviesView view, MoviesModel model) {
        CompositeSubscription subscriptions = new CompositeSubscription();
        return new MoviesPresenter(schedulers, model, view, subscriptions);
    }



    @MoviesScope
    @Provides
    MoviesListActivity provideContext() {
        return heroesListContext;
    }

    @MoviesScope
    @Provides
    MoviesModel provideModel(ApiClient api) {
        return new MoviesModel(heroesListContext, api);
    }
}
