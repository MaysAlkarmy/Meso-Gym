package com.example.sportapp.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.Adapters.ActivityAdapter;
import com.example.sportapp.Adapters.BannerAdapter;
import com.example.sportapp.R;
import com.example.sportapp.models.Activity;
import com.example.sportapp.models.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeFragment extends Fragment {
    RecyclerView bannerRecyclerView, activityRecyclerView;
    BannerAdapter bannerAdapter;
    ActivityAdapter activityAdapter;
//    ListView listView, bannerList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        bannerRecyclerView = view.findViewById(R.id.bannerList);
        activityRecyclerView = view.findViewById(R.id.listView);

        bannerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        activityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Get Banner

        API service = RetrofitClint.getApiService();

        Call<List<Banner>> retCall = service.getBannerImage();
        retCall.enqueue(new Callback<List<Banner>>() {
            @Override

            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {

//                Log.d("response.body", response.body().get(0).getImage_url());

                bannerAdapter = new BannerAdapter(getContext(), response.body());
                bannerRecyclerView.setAdapter(bannerAdapter);

              //  Toast.makeText(getContext(), "done", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
            }
        });

        // Get Activity

        API service2 = RetrofitClint.getApiService();

        //activityRecyclerView = view.findViewById(R.id.categoryRec);

        Call<List<Activity>> retCall2 = service2.getActivity();
        retCall2.enqueue(new Callback<List<Activity>>() {
            @Override

            public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response) {


                activityAdapter = new ActivityAdapter(getContext(), response.body());
                activityRecyclerView.setAdapter(activityAdapter);

             //  Toast.makeText(getContext(), "done", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Activity>> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }


}