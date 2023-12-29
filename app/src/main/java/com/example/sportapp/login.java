package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.models.Result;
import com.example.sportapp.models.User;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    Button login;
    TextView noAccount, forgot;
    TextInputEditText email;
    TextInputEditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        login= findViewById(R.id.loginButton);
        noAccount= findViewById(R.id.dontHaveAccount);
        email= findViewById(R.id.EmailEditText);
        password= findViewById(R.id.passEditText);
        forgot= findViewById(R.id.forgot);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(login.this, forgotPassword.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
//                Intent i= new Intent(login.this, MainActivity.class);
//                startActivity(i);
            }
        });

        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, signup.class);
                startActivity(i);
            }
        });



    }

    public void login() {

        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        API service = RetrofitClint.getApiService();

        RequestBody emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), email.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password.getText().toString());

        Call<Result> retCall = service.login(emailRequestBody, passwordRequestBody);
        retCall.enqueue(new Callback<Result>() {
            @Override

            public void onResponse(Call<Result> call, Response<Result> response) {
                pd.dismiss();
                if (response.body().getError()== false) {
                    Intent i= new Intent(login.this, MainActivity.class);
                    startActivity(i);

                    User user = new User(response.body().getUser().getId(),response.body().getUser().getName(),response.body().getUser().getEmail(),response.body().getUser().getPassword());

                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    finish();

                } else {
                    Toast.makeText(login.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                pd.dismiss();
                Log.d("failed", t.getMessage());
                Toast.makeText(login.this,"Failed to Insert Data --> "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

}