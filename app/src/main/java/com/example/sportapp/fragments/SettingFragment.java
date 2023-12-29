package com.example.sportapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sportapp.Adapters.HistoryAdapter;
import com.example.sportapp.R;
import com.example.sportapp.models.History;

import java.util.ArrayList;


public class SettingFragment extends Fragment {
    SwitchCompat switchCompat;
    boolean darkMode;
    SharedPreferences Preferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

       // Toolbar toolbar= view.findViewById(R.id.toolBar);
       // setSupportActionBar(toolbar);
       // toolbar.setTitle("Setting");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switchCompat = view.findViewById(R.id.swipe);
        Preferences = getContext().getSharedPreferences("Mode", Context.MODE_PRIVATE);
        darkMode = Preferences.getBoolean("darkMode", false);

        if (darkMode) {
            switchCompat.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (darkMode) {
                    switchCompat.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = Preferences.edit();
                    editor.putBoolean("darkMode", false);
                } else {
                    switchCompat.setChecked(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = Preferences.edit();
                    editor.putBoolean("darkMode", true);
                }
                editor.apply();
            }
        });



    }

}
