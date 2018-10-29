package com.abrarkotwal.movies.models;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Result {

    @Expose
    private List<Movies> results;

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }


}
