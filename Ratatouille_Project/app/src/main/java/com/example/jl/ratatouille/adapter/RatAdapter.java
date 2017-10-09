package com.example.jl.ratatouille.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.model.Rat;

import java.util.List;

import static android.R.attr.y;

/**
 * Created by jav on 10/8/2017.
 */

public class RatAdapter extends RecyclerView.Adapter<RatAdapter.ViewHolder> {
    private List<Rat> ratList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date, streetAddress, city;
        public ViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            streetAddress = (TextView) view.findViewById(R.id.street_address);
            city = (TextView) view.findViewById(R.id.city);
        }
    }

    public RatAdapter(List<Rat> ratList) {
        this.ratList = ratList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ratView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rat_list_row, parent, false);
        return new ViewHolder(ratView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rat rat = ratList.get(position);
        holder.date.setText(rat.getDate());
        holder.streetAddress.setText(rat.getIncidentAddress());
        holder.city.setText(rat.getCity());
    }

    @Override
    public int getItemCount() {
        return ratList.size();
    }
}
