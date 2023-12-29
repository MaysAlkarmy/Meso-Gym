package com.example.sportapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sportapp.models.User;



public class SharedPrefManager {

    private static final String SharedPrefName= "storeSharedPref";
    private static final String Key_id="keyId";
    private static final String Key_userName="keyUserName";
    private static final String Key_email="keyEmail";
    private static final String Key_pass="keyPass";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx= context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(mInstance == null){
            mInstance= new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(Key_id, user.getId());
        editor.putString(Key_userName, user.getName());
        editor.putString(Key_email, user.getEmail());
        editor.putString(Key_pass, user.getPassword());

        editor.apply();

    }

    public User getUser(){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        return new User(
          sharedPreferences.getInt(Key_id, 0),
          sharedPreferences.getString(Key_userName,null),
          sharedPreferences.getString(Key_email,null),
          sharedPreferences.getString(Key_pass,null)


        );

    }

    public void userUpdate(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Key_id, user.getId());
        editor.putString(Key_userName, user.getName());
        editor.putString(Key_email, user.getEmail());
        editor.putString(Key_pass, user.getPassword());

        editor.apply();
    }

//    public void passwordUpdate(User user) {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(Key_email, user.getEmail());
//        editor.putString(Key_pass, user.getPassword());
//
//        editor.apply();
//    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);

        return  sharedPreferences.getString(Key_userName,null) != null;
        // if the value != null it will return true --> this mean the user have benn logged in,
        // if the value = null it will return false --> this mean the user have not benn logged in,

    }

    public void logout(){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent i = new Intent(mCtx, login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(i);


    }
}
