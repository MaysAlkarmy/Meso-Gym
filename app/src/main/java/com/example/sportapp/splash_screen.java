package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash_screen extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
//            finish();// if the user hasn't logged in finish the main and go to sign in activity
//            Intent i = new Intent(getApplicationContext(), login.class);
//            startActivity(i);
//        }
        image= findViewById(R.id.logoImage);

        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide);

        image.startAnimation(slideAnimation);
        //

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash_screen.this, login.class);
                startActivity(i);
                finish();
            }
        }, 3000);


    }
}