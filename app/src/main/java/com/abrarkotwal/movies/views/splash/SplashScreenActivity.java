package com.abrarkotwal.movies.views.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abrarkotwal.movies.application.AppController;
import com.abrarkotwal.movies.views.movies.MoviesListActivity;
import com.abrarkotwal.movies.views.splash.core.SplashPresenter;
import com.abrarkotwal.movies.views.splash.core.SplashView;
import com.abrarkotwal.movies.views.splash.dagger.DaggerSplashComponent;
import com.abrarkotwal.movies.views.splash.dagger.SplashContextModule;

import javax.inject.Inject;


public class SplashScreenActivity extends AppCompatActivity {


    @Inject
    SplashView view;
    @Inject
    SplashPresenter splashPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSplashComponent.builder()
                .appComponent(AppController.getNetComponent())
                .splashContextModule(new SplashContextModule(this))
                .build()
                .inject(this);

        setContentView(view.constructView());
        splashPresenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
    }

    public void showHeroesListActivity() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreenActivity.this, MoviesListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }

}
