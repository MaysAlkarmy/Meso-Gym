package com.example.sportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sportapp.Adapters.BannerAdapter;
import com.example.sportapp.fragments.AddFragment;
import com.example.sportapp.fragments.SettingFragment;
import com.example.sportapp.fragments.historyFragment;
import com.example.sportapp.fragments.homeFragment;
import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    public static TextView navUsername,navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
//            finish();// if the user hasn't logged in finish the main and go to sign in activity
//            Intent i = new Intent(getApplicationContext(), login.class);
//            startActivity(i);
//        }

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView= findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        navUsername =  headerView.findViewById(R.id.user_name_nav);
        navEmail = headerView.findViewById(R.id.Email_nav);


        Toolbar toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
       // toolbar.setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new homeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        setHeaderInfo();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.logout:
                        finish();
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                        break;
                    case R.id.home:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new homeFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.addActivity:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new AddFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.History:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new historyFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;

                    case R.id.setting:
                        fragmentTransaction= getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new SettingFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }

                drawerLayout.closeDrawers();
                return true;

            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setHeaderInfo() {
        String name= SharedPrefManager.getInstance(MainActivity.this).getUser().getName();
        String email= SharedPrefManager.getInstance(MainActivity.this).getUser().getEmail();

        navUsername.setText(name);
        navEmail.setText(email);


    }


}