package com.example.sportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.Adapters.SubActivityAdapter2;
import com.example.sportapp.models.SubActivity;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sub_activity2 extends AppCompatActivity {

    RecyclerView subRec2;
    SubActivityAdapter2 subActivityAdapter2;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        String name= getIntent().getExtras().getString("sub_name");

        Toolbar toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(name);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subRec2= findViewById(R.id.sub2Rec);
        subRec2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        getSubActivity2(name);

    }

    public void getSubActivity2(String name) {


        API service = RetrofitClint.getApiService();

        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name);

        Call<List<SubActivity>> retCall = service.getSubActivity2(nameRequestBody);
        retCall.enqueue(new Callback<List<SubActivity>>() {
            @Override

            public void onResponse(Call<List<SubActivity>> call, Response<List<SubActivity>> response) {
                subActivityAdapter2 = new SubActivityAdapter2(sub_activity2.this, response.body());
                subRec2.setAdapter(subActivityAdapter2);
                //Toast.makeText(sub_activity2.this, "done", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<SubActivity>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}