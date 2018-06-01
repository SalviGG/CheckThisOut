package com.example.pablorjd.CheckThisOut.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pablorjd.CheckThisOut.BuildConfig;
import com.example.pablorjd.CheckThisOut.R;
import com.example.pablorjd.CheckThisOut.interfaces.ApiInterface;
import com.example.pablorjd.CheckThisOut.model.MovieResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = BuildConfig.ApiKey;
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";

    //private TextView tvTest;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface mInterface = retrofit.create(ApiInterface.class);
        Call<MovieResults> call = mInterface.getMovies(CATEGORY,API_KEY,LANGUAGE,PAGE);

        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results =  response.body();
                List<MovieResults.ResultsBean> listOfMovies = results.getResults();
                MovieResults.ResultsBean firstMovie = listOfMovies.get(0);
                initUI(rootView);
                //tvTest.setText(firstMovie.getTitle());
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return rootView;
    }


    private void initUI(View view){

        //tvTest = (TextView)view.findViewById(R.id.tvTest);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
    }
}
