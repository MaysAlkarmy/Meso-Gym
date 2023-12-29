package com.example.sportapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sportapp.R;
import com.example.sportapp.models.Activity;
import com.example.sportapp.models.Banner;
import com.example.sportapp.sub_activity;

import java.util.List;

import pl.droidsonroids.gif.GifOptions;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.viewItem> {

    private List<Activity> activityList;
    private boolean flag = false;

    //the context object
    private Context mCtx;

    public ActivityAdapter(Context context, List<Activity> activityList) {


        this.activityList = activityList;
        this.mCtx = context;

    }


    class viewItem extends RecyclerView.ViewHolder {


        ImageView image;
        TextView name;
        ConstraintLayout layout;


        public viewItem(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.activityIamge);
            name = itemView.findViewById(R.id.activityText);
            layout = itemView.findViewById(R.id.activityLayout);


        }


    }


    @NonNull
    @Override
    public viewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_custome, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.viewItem holder, int position) {


        Glide.with(mCtx).load("http://192.168.100.22/sportApp/" + activityList.get(position).getImage()).into(holder.image);

        //  Glide.with(getContext()).load("http://10.0.2.2/sportApp/activity/"+activityList.get(position).getImage())
        //    .into(image);
        holder.name.setText(activityList.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mCtx, sub_activity.class);
                i.putExtra("name", activityList.get(position).getName());
                mCtx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }
}

