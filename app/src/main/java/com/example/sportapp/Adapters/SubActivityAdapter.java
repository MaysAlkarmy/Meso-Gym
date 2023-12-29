package com.example.sportapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sportapp.API.API;
import com.example.sportapp.API.RetrofitClint;
import com.example.sportapp.R;
import com.example.sportapp.sub_activity;
import com.example.sportapp.sub_activity2;
import com.example.sportapp.models.SubActivity;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubActivityAdapter extends RecyclerView.Adapter<SubActivityAdapter.viewItem> {

    Context c;
    List<SubActivity> subActivityList;

    public SubActivityAdapter(Context c, List<SubActivity> subActivityList) {
        this.subActivityList = subActivityList;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {

        ImageView Image, delete;
        TextView Name;
        TextView time;
        ConstraintLayout layout;


        public viewItem(View itemView) {
            super(itemView);


           // Image=itemView.findViewById(R.id.sub_activityImage);
            Name=itemView.findViewById(R.id.sub_activityText);
            time=itemView.findViewById(R.id.sub_activityTime);
            layout= itemView.findViewById(R.id.subLayOut);
            delete= itemView.findViewById(R.id.deleteActivity);



        }
    }


    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_activity_custome, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {

        int timer= Integer.parseInt( subActivityList.get(position).getTime() );

      //  holder.time.setText(timer);
        holder.Name.setText(subActivityList.get(position).getName());
        holder.time.setText( subActivityList.get(position).getTime());
       // Glide.with(c).load("http://192.168.100.22/sportApp/" + subActivityList.get(position).getImage()).into(holder.Image);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= subActivityList.get(position).getName();
                Intent i = new Intent(c, sub_activity2.class);
                i.putExtra("sub_name", name);
                c.startActivity(i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id= subActivityList.get(position).getId();



            API service = RetrofitClint.getApiService();
             RequestBody idRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));

            Call<List<SubActivity>> retCall = service.deleteActivity(idRequestBody);
            retCall.enqueue(new Callback<List<SubActivity>>() {
                @Override

                public void onResponse(Call<List<SubActivity>> call, Response<List<SubActivity>> response) {
                    Toast.makeText(c, "deleted", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<List<SubActivity>> call, Throwable t) {
                    Toast.makeText(c, "failed", Toast.LENGTH_LONG).show();
                    Log.d("failed ", t.getMessage());
                }
            });

            }
        });
    }



    @Override
    public int getItemCount() {
        return subActivityList.size();
    }


}
