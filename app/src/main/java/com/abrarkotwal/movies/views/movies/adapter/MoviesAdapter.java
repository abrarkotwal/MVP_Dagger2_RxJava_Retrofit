package com.abrarkotwal.movies.views.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrarkotwal.movies.R;
import com.abrarkotwal.movies.models.Movies;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;


public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<Movies> listMovies = new ArrayList<>();


    public void swapAdapter(ArrayList<Movies> movies) {
        this.listMovies.clear();
        this.listMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movie_display, parent, false);
        return new MovieViewHolder(view ,itemClicks);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movies movies = listMovies.get(position);
        holder.bind(movies);

    }


    @Override
    public int getItemCount() {
        if (listMovies != null && listMovies.size() > 0) {
            return listMovies.size();
        } else {
            return 0;
        }
    }
}
