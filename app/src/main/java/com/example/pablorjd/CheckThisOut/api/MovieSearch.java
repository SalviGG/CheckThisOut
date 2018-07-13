package com.example.pablorjd.CheckThisOut.api;

import com.example.pablorjd.CheckThisOut.model.MovieResults;
import com.example.pablorjd.CheckThisOut.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieSearch {

    @GET("/3/search/movie")
    Call<Movies> getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("Page") int page
    );
}
