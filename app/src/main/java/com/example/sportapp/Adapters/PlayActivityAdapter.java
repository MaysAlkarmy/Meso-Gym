package com.example.sportapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportapp.PlayActivity;
import com.example.sportapp.R;
import com.example.sportapp.fragments.historyFragment;
import com.example.sportapp.models.SubActivity;

import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

public class PlayActivityAdapter extends RecyclerView.Adapter<PlayActivityAdapter.viewItem> {

    Context c;
    List<SubActivity> subActivityList2;

    //    private static final long START_TIME_IN_MILLIS =  15000;  // = 5second
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;


    public PlayActivityAdapter(Context c, List<SubActivity> subActivityList2) {
        this.subActivityList2 = subActivityList2;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {


        TextView Name;
        ImageView Image;
        TextView timer;
        public Button start, reset;


        public viewItem(View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.activityName);
            Image = itemView.findViewById(R.id.activityImage);
            timer = itemView.findViewById(R.id.timeText);

            start = itemView.findViewById(R.id.startButton);
            reset = itemView.findViewById(R.id.ResetButton);
            String tt;


        }
    }


    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.play_activity_custome, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {
        mTimeLeftInMillis = Long.parseLong(subActivityList2.get(position).getTime());
        holder.Name.setText(subActivityList2.get(position).getName());
        holder.timer.setText(subActivityList2.get(position).getTime());
        Glide.with(c).load("http://192.168.100.22/sportApp/" + subActivityList2.get(position).getImage()).into(holder.Image);

//        Intent i = new Intent(c, PlayActivity.class);
//        i.putExtra("name_history", subActivityList2.get(position).getName());

        // long time= Integer.parseInt( subActivityList2.get(position).getTime())  ;
        // START_TIME_IN_MILLIS= Long.parseLong(subActivityList2.get(position).getTime());


        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mTimerRunning) {

                    // pauseTimer() method

                    mCountDownTimer.cancel();
                    mTimerRunning = false;
                    holder.start.setText("Start");
                    holder.reset.setVisibility(View.VISIBLE);
                    // end of pauseTimer()

                } else {

                    //startTimer()
                    mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                            mTimeLeftInMillis = millisUntilFinished;
                            // updateCountDownText()
                            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                            holder.timer.setText(timeLeftFormatted);
                            // end of updateCountDownText()
                        }

                        @Override
                        public void onFinish() {
                            mTimerRunning = false;
                            holder.start.setText("Start");
                            holder.start.setVisibility(View.INVISIBLE);
                            holder.reset.setVisibility(View.VISIBLE);


                        }
                    }.start();

                    mTimerRunning = true;
                    holder.start.setText("pause");
                    holder.reset.setVisibility(View.INVISIBLE);
                    // end of startTimer()
                }
            }
        });
        holder.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // reset()
                mTimeLeftInMillis = Long.parseLong(subActivityList2.get(position).getTime());
                // updateCountDownText()
                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                holder.timer.setText(timeLeftFormatted);
                // updateCountDownText()

                // continue of reset
                holder.reset.setVisibility(View.INVISIBLE);
                holder.start.setVisibility(View.VISIBLE);
                // end of reset()
            }
        });

        // updateCountDownText()
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        holder.timer.setText(timeLeftFormatted);
        // end of updateCountDownText


    }


    @Override
    public int getItemCount() {
        return 1;
    }


}
