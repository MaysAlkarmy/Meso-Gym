package com.example.sportapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sportapp.R;
import com.example.sportapp.models.History;
import com.example.sportapp.models.SubActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewItem> {

    Context c;

    ArrayList<History> item;
    boolean flag= false;

    public HistoryAdapter(Context c , ArrayList<History> item) {
        this.item = item;
        this.c = c;

    }

    class viewItem extends RecyclerView.ViewHolder {
       EditText text;

        public viewItem(View itemView) {
            super(itemView);

            text=itemView.findViewById(R.id.historyText);

        }
    }


    @Override
    public viewItem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_custome, parent, false);
        return new viewItem(itemView);
    }

    @Override
    public void onBindViewHolder(viewItem holder, final int position) {

        //Glide.with(c).load(item.get(position).getBanner()).into(holder.banner);
        holder.text.setText(item.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

}

