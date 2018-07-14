package com.example.pablorjd.CheckThisOut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    TextView tvRegistrarse;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvRegistrarse = (TextView)findViewById(R.id.tvRegistrarse);

        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,RegistrarUsuario.class);
                Login.this.startActivity(intent);
            }
        });



    }

    public void onLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String type = "login";
        BackgroundWorkerLogin backgroundWorkerLogin = new BackgroundWorkerLogin(this);
        backgroundWorkerLogin.execute(type,username,password);
        etUsername.setText("");
        etPassword.setText("");
    }
}
