package com.example.pablorjd.CheckThisOut.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApi {

    private static Retrofit retrofit = null;
    public static String BASE_URL = "https://api.themoviedb.org";

    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
