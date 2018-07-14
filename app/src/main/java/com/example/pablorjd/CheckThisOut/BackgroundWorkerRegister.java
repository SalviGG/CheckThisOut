package com.example.pablorjd.CheckThisOut;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorkerRegister extends AsyncTask<String, Void, String> {

    Context context;
    public BackgroundWorkerRegister(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String checkin_url = "http://10.0.2.2/checkthisout/register.php";
        if (type.equals("register")){
            try {
                String user_name = params[1];
                String nombre = params[2];
                String apellido = params[3];
                String password = params[4];
                String email = params[5];
                URL url = new URL(checkin_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(nombre,"UTF-8")+"&"
                        +URLEncoder.encode("last_name","UTF-8")+"="+URLEncoder.encode(apellido,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line ="";
                while ((line = bufferedReader.readLine())!=null){
                    result.append(line).append("\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString().replace("\\\"", "\"").replace("\n","").replaceAll("^\"|\"$", "").trim();
            } catch (MalformedURLException e) {
                //e.printStackTrace();
                Log.d("Error outpustream", e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equals("user registered") ){
            Intent intent = new Intent(context,Login.class);
            context.startActivity(intent);
            Toast.makeText(context,"Usuario registrado correctamente",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(context,result + ". Elija otro nombre de usuario",Toast.LENGTH_LONG).show();
        }

    }
}
