package com.example.sportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.Adapters.SubActivityAdapter;
import com.example.sportapp.models.Result;
import com.example.sportapp.models.SubActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sub_activity extends AppCompatActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    RecyclerView subRec;
    SubActivityAdapter sub_adapter;
    ImageView delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        drawerLayout = findViewById(R.id.drawerLayout);

        String name= getIntent().getExtras().getString("name");

        Toolbar toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        subRec= findViewById(R.id.subRec);
        subRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


      //  delete= findViewById(R.id.deleteActivity);
        SubActivity subActivity= new SubActivity();

        int id= subActivity.getId();

        getSubActivity(name);



    }


    public void getSubActivity(String name) {


        API service = RetrofitClint.getApiService();

       RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name);

        Call<List<SubActivity>> retCall = service.getSubActivity(nameRequestBody);
        retCall.enqueue(new Callback<List<com.example.sportapp.models.SubActivity>>() {
            @Override

            public void onResponse(Call<List<SubActivity>> call, Response<List<SubActivity>> response) {
                sub_adapter = new SubActivityAdapter(sub_activity.this, response.body());
                subRec.setAdapter(sub_adapter);

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