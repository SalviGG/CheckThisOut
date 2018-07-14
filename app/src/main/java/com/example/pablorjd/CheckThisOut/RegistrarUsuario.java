package com.example.pablorjd.CheckThisOut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarUsuario extends AppCompatActivity {

    private EditText etRegUsuario;
    private EditText etRegNombre;
    private EditText etRegApellido;
    private EditText etRegPassword;
    private EditText etRegEmail;
    private Button btnRegistrarse;
    private Button btnRegLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        etRegUsuario = (EditText) findViewById(R.id.etRegUsuario);
        etRegNombre = (EditText) findViewById(R.id.etRegNombre);
        etRegApellido = (EditText) findViewById(R.id.etRegApellido);
        etRegPassword = (EditText) findViewById(R.id.etRegPassword);
        etRegEmail = (EditText) findViewById(R.id.etRegEmail);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        btnRegLogin = (Button) findViewById(R.id.btnRegLogin);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkCampos()){
                    if (validaciones().equals("")){
                        String username = etRegUsuario.getText().toString();
                        String nombre = etRegNombre.getText().toString();
                        String apellido = etRegApellido.getText().toString();
                        String password = etRegPassword.getText().toString();
                        String email = etRegEmail.getText().toString();
                        String type = "register";
                        BackgroundWorkerRegister backgroundWorkerRegister = new BackgroundWorkerRegister(RegistrarUsuario.this);
                        backgroundWorkerRegister.execute(type,username,nombre,apellido,password,email);
                    }else{
                        Toast.makeText(RegistrarUsuario.this,validaciones(),Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegistrarUsuario.this,"Hay campos sin rellenar, por favor ingrese todos los datos",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarUsuario.this,Login.class);
                RegistrarUsuario.this.startActivity(intent);
            }
        });


    }


    private boolean checkCampos(){

        int val = 0;

        if (etRegUsuario.getText().toString().length()==0){
            val += 1;
        }
        if (etRegNombre.getText().toString().length()==0){
            val += 1;
        }
        if (etRegApellido.getText().toString().length()==0){
            val += 1;
        }
        if (etRegPassword.getText().toString().length()==0){
            val += 1;
        }
        if (etRegEmail.getText().toString().length()==0){
            val += 1;
        }

        if (val == 0){
            return true;
        }


        return false;
    }

    private String validaciones(){

        String val = "";
        if (etRegUsuario.getText().toString().length()<=2){
            val += "Largo de usuario muy corto "+"\n";
        }
        if (etRegPassword.getText().toString().length()<=3){
            val += "Largo de contraseña muy corto "+"\n";
        }
        if (etRegNombre.getText().toString().length()<=2){
            val += "Largo de nombre muy corto "+"\n";
        }
        if (etRegApellido.getText().toString().length()<=3){
            val += "Largo de apellido muy corto "+"\n";
        }
        if (etRegEmail.getText().toString().length()<=6){
            val += "Largo de email muy corto "+"\n";
        }

        return val;

    }

}
