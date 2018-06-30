package com.example.pablorjd.CheckThisOut.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserSession {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public UserSession(Context context) {

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor =  preferences.edit();

    }

    public void setId(String id){
        editor.putString("id",id).commit();
    }

    public String getId(){
        String username = preferences.getString("id","");
        return username;
    }

    public void setUserName(String userName){
        editor.putString("username",userName).commit();
    }

    public String getUserName(){
        String username = preferences.getString("username","");
        return username;
    }

    public void setName(String name){
        editor.putString("name",name).commit();
    }

    public String getName(){
        String name = preferences.getString("name","");
        return name;
    }

    public void setLatName(String userName){
        editor.putString("username",userName).commit();
    }

    public String getLastName(){
        String lastName = preferences.getString("Lastname","");
        return lastName;
    }

    public void setPassword(String password){
        editor.putString("password",password).commit();
    }

    public String getPassword(){
        String password = preferences.getString("password","");
        return password;
    }

    public void setEmail(String email){
        editor.putString("email",email).commit();
    }

    public String getEmail(){
        String email = preferences.getString("email","");
        return email;
    }


    public void cleanSharedPreferences(){
        preferences.edit().clear().commit();
    }

    public void setAllSharedPreferences(String id, String userName, String email, String password, String name, String lastName ){
        setId(id);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setName(name);
        setLatName(lastName);

    }

}
