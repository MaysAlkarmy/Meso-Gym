package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.models.Result;
import com.example.sportapp.models.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class forgotPassword extends AppCompatActivity {

    TextInputEditText email, New;
   // User user = SharedPrefManager.getInstance(forgotPassword.this).getUser();
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.oldText);
        New = findViewById(R.id.newText);
        //int id = user.getId();
        update = findViewById(R.id.updatePassword);




    }




}