package com.example.pablorjd.CheckThisOut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pablorjd.CheckThisOut.api.MovieServiceDetail;
import com.example.pablorjd.CheckThisOut.model.MovieResults;
import com.example.pablorjd.CheckThisOut.utils.UserSession;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetail extends AppCompatActivity {

    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";
    private static final String BASE_URL = "https://api.themoviedb.org";
    private static int PAGE = 1;
    private static String LANGUAGE= "es-CL";
    private static String API_KEY = BuildConfig.ApiKey;
    private int MOVIE_ID;


    private TextView title;
    private TextView year;
    private TextView popularity;
    private TextView description;
    private TextView sinopsis;
    private ImageView moviePoster;

    private Button btnCheckin;

    private UserSession userSession;

    private String movieID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        userSession = new UserSession(this);

        btnCheckin = (Button)findViewById(R.id.btnCheckin);

        final Bundle datos = this.getIntent().getExtras();

        MOVIE_ID = Integer.parseInt(datos.getString("Movie_ID").replaceAll("[\\D]",""));
        title = (TextView)findViewById(R.id.movie_title_detail);
        year = (TextView)findViewById(R.id.movie_year_detail);
        popularity = (TextView)findViewById(R.id.movie_pop_detail);
        description = (TextView)findViewById(R.id.movie_desc_detail);
        sinopsis = (TextView)findViewById(R.id.movie_sinopsis_detail);
        sinopsis.setText("Sinopsis");
        moviePoster = (ImageView)findViewById(R.id.movie_poster_detail);

        movieID = datos.getString("Movie_ID");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieServiceDetail myInterface = retrofit.create(MovieServiceDetail.class);

        Call<MovieResults.ResultsBean> call =  myInterface.getMovies(MOVIE_ID,API_KEY,LANGUAGE);

        call.enqueue(new Callback<MovieResults.ResultsBean>() {
            @Override
            public void onResponse(Call<MovieResults.ResultsBean> call, Response<MovieResults.ResultsBean> response) {
                MovieResults.ResultsBean results = response.body();

                title.setText(results.getTitle());
                year.setText(results.getRelease_date()+"|"+results.getOriginal_language().toUpperCase());
                //popularity.setText(results.getPopularity());
                description.setText(results.getOverview());
                Glide.with(MovieDetail.this)
                        .load(BASE_URL_IMG+results.getPoster_path())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .crossFade()
                        .into(moviePoster);

            }

            @Override
            public void onFailure(Call<MovieResults.ResultsBean> call, Throwable t) {

            }
        });

    }

    public void onChekin(View view){
        String userId = userSession.getId();
        String type = "checkin";
        BackgroundWorkerCheckin backgroundWorkerCheckin = new BackgroundWorkerCheckin(this);
        backgroundWorkerCheckin.doInBackground(type,userId,movieID);
    }
}
