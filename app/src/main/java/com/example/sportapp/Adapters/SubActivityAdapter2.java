package com.example.sportapp.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.sportapp.models.SubActivity;

import java.util.List;

public class SubActivityAdapter2 extends RecyclerView.Adapter<SubActivityAdapter2.viewItem> {

    Context c;
    List<SubActivity> subActivityList2;

    public SubActivityAdapter2(Context c, List<SubActivity> subActivityList2) {
        this.subActivityList2 = subActivityList2;
        this.c = c;
    }

    class viewItem extends RecyclerView.ViewHolder {


        TextView Name;
        ImageView Image;
        TextView time;
        TextView describe;
        Button start;



        public viewItem(View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.activityName);
            Image=itemView.findViewById(R.id.activityImage);
            describe= itemView.findViewById(R.id.describe);
            time=itemView.findViewById(R.id.timeText);
            start= itemView.findViewById(R.id.startButton);


        }
    }


    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_2_custome, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {

        holder.Name.setText(subActivityList2.get(position).getName());
        holder.describe.setText(subActivityList2.get(position).getDescribe_activity());
        holder.time.setText( subActivityList2.get(position).getTime());
        Glide.with(c).load("http://192.168.100.22/sportApp/" + subActivityList2.get(position).getImage()).into(holder.Image);

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= subActivityList2.get(position).getName();
               // String timer= subActivityList2.get(position).getTime();
                Intent i = new Intent(c, PlayActivity.class);
                i.putExtra("sub_name2", name);
                i.putExtra("activity_name", name);
                c.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return 1;
    }


}
