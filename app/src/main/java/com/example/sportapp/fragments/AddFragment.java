package com.example.sportapp.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.Adapters.ActivityAdapter;
import com.example.sportapp.Adapters.BannerAdapter;
import com.example.sportapp.MainActivity;
import com.example.sportapp.R;
import com.example.sportapp.models.Activity;
import com.example.sportapp.models.Banner;
import com.example.sportapp.models.Result;
import com.example.sportapp.models.SubActivity;
import com.example.sportapp.signup;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddFragment extends Fragment {

  // declaration

    EditText name, time , describe, main_activity;
    Button addButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // code
        name= view.findViewById(R.id.addName);
        time= view.findViewById(R.id.addTime);
        describe= view.findViewById(R.id.addDescribe);
        addButton= view.findViewById(R.id.addButton);
        main_activity= view.findViewById(R.id.addMain);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertActivity();
            }
        });



    }



    private void insertActivity(){

        ProgressDialog pd = new ProgressDialog(getContext());
        pd.show();
        API service = RetrofitClint.getApiService();


        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name.getText().toString());
        RequestBody timeRequestBody = RequestBody.create(MediaType.parse("text/plain"), time.getText().toString());
        RequestBody mainRequestBody = RequestBody.create(MediaType.parse("text/plain"), main_activity.getText().toString().toUpperCase());
        RequestBody describeRequestBody = RequestBody.create(MediaType.parse("text/plain"), describe.getText().toString());

        Call<SubActivity> retCall = service.addActivity(nameRequestBody,timeRequestBody, mainRequestBody,describeRequestBody );
        retCall.enqueue(new Callback<SubActivity>() {
            @Override

            public void onResponse(Call<SubActivity> call, Response<SubActivity> response) {
                pd.dismiss();
                if (response.body().getError()==true) {

                    Toast.makeText(getContext(), "done ", Toast.LENGTH_LONG).show();
//                    Intent i= new Intent(getContext(), MainActivity.class);
//                    startActivity(i);

                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SubActivity> call, Throwable t) {
                pd.dismiss();
            }
        });

    }

}