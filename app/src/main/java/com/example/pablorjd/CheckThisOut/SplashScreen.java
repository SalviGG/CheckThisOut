package com.example.pablorjd.CheckThisOut;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.example.pablorjd.CheckThisOut.utils.UserSession;


public class SplashScreen extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserSession userSession = new UserSession(SplashScreen.this);
                if (userSession.getUserName().isEmpty()){
                    Intent intent = new Intent(SplashScreen.this, Login.class );
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class );
                    startActivity(intent);
                }

            }
        },3000);

    }
}
