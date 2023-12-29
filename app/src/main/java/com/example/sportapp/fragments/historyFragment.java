package com.example.sportapp.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.Adapters.ActivityAdapter;
import com.example.sportapp.Adapters.BannerAdapter;
import com.example.sportapp.Adapters.HistoryAdapter;
import com.example.sportapp.Adapters.SubActivityAdapter;
import com.example.sportapp.R;
import com.example.sportapp.models.Activity;
import com.example.sportapp.models.Banner;
import com.example.sportapp.models.History;
import com.example.sportapp.models.SubActivity;
import com.example.sportapp.sub_activity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class historyFragment extends Fragment {


    HistoryAdapter historyAdapter;
    RecyclerView historyRec;
    ArrayList<History> arrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }





}