package com.example.sportapp.Adapters;


import static java.security.AccessController.getContext;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sportapp.R;
import com.example.sportapp.models.Activity;
import com.example.sportapp.models.Banner;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.viewItem> {

    private List<Banner> bannerList;
    private boolean flag = false;

    //the context object
    private Context mCtx;

    public BannerAdapter(Context context, List<Banner> bannerList) {


        this.bannerList = bannerList;
        this.mCtx = context;

    }

    @NonNull
    @Override
    public viewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner_custome, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewItem holder, int position) {
        Glide.with(mCtx).load("http://192.168.100.22/sportApp/" + bannerList.get(position).getImage_url()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    class viewItem extends RecyclerView.ViewHolder {


        ImageView image;


        public viewItem(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.bannerImage);


        }


    }


}

