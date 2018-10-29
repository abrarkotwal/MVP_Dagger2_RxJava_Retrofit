package com.abrarkotwal.movies.views.movies.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abrarkotwal.movies.R;
import com.abrarkotwal.movies.models.Movies;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;


public class MovieViewHolder extends ViewHolder {

    private View view;

    @BindView(R.id.moviePoster)
    ImageView moviePoster;
    @BindView(R.id.movieTitle)
    TextView movieTitle;
    @BindView(R.id.movieRating)
    TextView movieRating;
    @BindView(R.id.movieReleaseDate)
    TextView movieReleaseDate;
    @BindView(R.id.movieOverView)
    TextView movieOverView;

    public MovieViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    @SuppressLint("SetTextI18n")
    void bind(Movies movies) {
        Glide.with(view.getContext()).load("http://image.tmdb.org/t/p/w500/"+movies.getPoster_path()).into(moviePoster);

        movieTitle.setText(TextUtils.isEmpty(movies.getTitle()) ? "missing title" : movies.getTitle());
        movieRating.setText("Rating:- "+movies.getVote_average()+"("+movies.getVote_count()+")");
        movieReleaseDate.setText(TextUtils.isEmpty(movies.getRelease_date()) ? "missing text" : "Release On:- "+movies.getRelease_date());
        movieOverView.setText(movies.getOverview());
    }

}
