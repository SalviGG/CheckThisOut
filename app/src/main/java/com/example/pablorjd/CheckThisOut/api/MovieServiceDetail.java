package com.example.pablorjd.CheckThisOut.api;

import com.example.pablorjd.CheckThisOut.model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServiceDetail {

    @GET("/3/movie/{movie_id}")
    Call<MovieResults.ResultsBean> getMovies(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_Key,
            @Query("language") String language
    );

}
