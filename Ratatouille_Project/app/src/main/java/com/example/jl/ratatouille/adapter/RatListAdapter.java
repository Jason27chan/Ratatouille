package com.example.jl.ratatouille.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.activity.RatViewActivity;
import com.example.jl.ratatouille.model.Rat;

import java.util.List;

/**
 * Created by jav on 10/8/2017.
 */

public class RatListAdapter extends RecyclerView.Adapter<RatListAdapter.ViewHolder> {
    private List<Rat> ratList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView date, streetAddress, city;
        public Rat rat;

        public ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            streetAddress = (TextView) view.findViewById(R.id.street_address);
            city = (TextView) view.findViewById(R.id.city);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, RatViewActivity.class);
            intent.putExtra("rat", rat);
            context.startActivity(intent);
        }
    }

    public RatListAdapter(List<Rat> ratList, Context context) {
        this.ratList = ratList;
        this.context = context;
    }

    @Override
    public RatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ratView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rat_list_row, parent, false);
        return new RatListAdapter.ViewHolder(ratView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rat rat = ratList.get(position);
        holder.date.setText(rat.getDate());
        holder.streetAddress.setText(rat.getAddress());
        holder.city.setText(rat.getCity());
        holder.rat = rat;
    }

    @Override
    public int getItemCount() {
        return ratList.size();
    }
}
