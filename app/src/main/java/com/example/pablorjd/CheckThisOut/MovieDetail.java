package com.example.pablorjd.CheckThisOut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetail extends AppCompatActivity {

    private String mMovieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle datos = this.getIntent().getExtras();
        mMovieTitle= datos.getString("Movie_ID");
    }
}
