package com.abrarkotwal.movies.views.movies.core;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.abrarkotwal.movies.R;
import com.abrarkotwal.movies.models.Movies;
import com.abrarkotwal.movies.views.movies.MoviesListActivity;
import com.abrarkotwal.movies.views.movies.adapter.MoviesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class MoviesView {

    @BindView(R.id.activity_heroes_list_recycleview)
    RecyclerView list;

    View view;
    MoviesAdapter adapter;

    public MoviesView(MoviesListActivity context ) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_movies_list, parent, true);
        ButterKnife.bind(this, view);

        adapter = new MoviesAdapter();

        list.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        list.setLayoutManager(mLayoutManager);


    }

    public Observable<Integer>  itemClicks() {
        return adapter.observeClicks();
    }

    public View view() {
        return view;
    }

    public void swapAdapter(ArrayList<Movies> movies) {
        adapter.swapAdapter(movies);
    }

}
