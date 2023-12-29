package com.example.sportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.models.Result;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {

    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText confirmPass;
    Button signup;
    TextView haveAccount;
    String userName, userEmail, userPass, userconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name= findViewById(R.id.nameEditText);
        email= findViewById(R.id.emailEditText);
        password= findViewById(R.id.passEditText);
        confirmPass= findViewById(R.id.confirmPassEditText);
        signup= findViewById(R.id.signUpButton);
        haveAccount= findViewById(R.id.HaveAccount);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertUser();

            }
        });


    }

    private void insertUser(){

        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        API service = RetrofitClint.getApiService();


        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name.getText().toString());
        RequestBody emailRequestBody = RequestBody.create(MediaType.parse("text/plain"), email.getText().toString());
        RequestBody passwordRequestBody = RequestBody.create(MediaType.parse("text/plain"), password.getText().toString());

        Call<Result> retCall = service.SignUp(nameRequestBody,emailRequestBody, passwordRequestBody);
        retCall.enqueue(new Callback<Result>() {
            @Override

            public void onResponse(Call<Result> call, Response<Result> response) {
                pd.dismiss();
                if (response.body().getError()==false) {

                  //  Toast.makeText(signup.this, "done ", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(signup.this, MainActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(signup.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                pd.dismiss();
            }
        });

    }


    Boolean userNameValidate(){
        userName=name.getText().toString().trim();
        if(userName.isEmpty()){
            name.setError("this field shouldn't be empty");
            return false;
        }
        else {
            name.setEnabled(false);
            return true;
        }
    }

    boolean userEmailValidate(){
        userEmail=email.getText().toString().trim();
        if(userEmail.isEmpty()){
            email.setError("this field shouldn't be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("email not valid");
            return false;
        }
        return true;
    }

    boolean passwordValidate(){
        userPass=password.getText().toString().trim();
        if(userPass.isEmpty()){
            password.setError("this field shouldn't be empty");
            return false;
        }
        else if(userPass.length()<=8){
            password.setError("the password should be 8 digit or more");

        }
        else if(!userPass.matches("^(?=.*[_.()$&@]).*$")){
            password.setError("the password should contain special character");
        }
        else {
            password.setEnabled(false);
            return true;
        }
        return true;
    }

//    boolean confirmPass(){
//        userPass=password.getText().toString().trim();
//        userconfirm= confirmPass.getText().toString().trim();
//
//        if(userPass.equals(userconfirm)){
//            confirmPass.setError("password not match");
//            return false;
//        }
//        else
//            confirmPass.setEnabled(false);
//            return true;
//
//    }
}