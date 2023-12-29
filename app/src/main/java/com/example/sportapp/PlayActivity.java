package com.example.sportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.Adapters.PlayActivityAdapter;
import com.example.sportapp.Adapters.SubActivityAdapter2;
import com.example.sportapp.models.SubActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayActivity extends AppCompatActivity {

    RecyclerView platRec;
    PlayActivityAdapter playActivityAdapter;
    Button start, reset;

    public int counter;
    TextView timer;

   //  String time= getIntent().getStringExtra("activity_time");
     //long tt= Long.parseLong(time);
     long START_TIME_IN_MILLIS = 15000;  // = 5second
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Toolbar toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        platRec= findViewById(R.id.playRec);
        platRec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        timer= findViewById(R.id.timeText);
//        start= findViewById(R.id.startButton);
//        reset= findViewById(R.id.resetButton);

        String name= getIntent().getExtras().getString("sub_name2");

//        String time= getIntent().getExtras().getString("time");
//        long tt= Long.parseLong(time);

//        String time= getIntent().getStringExtra("activity_time");
//        long tt= Long.parseLong(time);
//        long START_TIME_IN_MILLIS = tt;

        getPlayActivity(name);

//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (mTimerRunning) {
//                    pauseTimer();
//                } else {
//                    startTimer();
//                }
//            }
//        });
//
//        reset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetTimer();
//            }
//        });
//
//        updateCountDownText();
    }



    public void getPlayActivity(String name) {


        API service = RetrofitClint.getApiService();

        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), name);

        Call<List<SubActivity>> retCall = service.getPlayActivity(nameRequestBody);
        retCall.enqueue(new Callback<List<SubActivity>>() {
            @Override

            public void onResponse(Call<List<SubActivity>> call, Response<List<SubActivity>> response) {
                playActivityAdapter = new PlayActivityAdapter(PlayActivity.this, response.body());
                platRec.setAdapter(playActivityAdapter);

            }

            @Override
            public void onFailure(Call<List<SubActivity>> call, Throwable t) {

            }
        });
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                start.setText("Start");
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        start.setText("pause");
        reset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        start.setText("Start");
        reset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timer.setText(timeLeftFormatted);
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