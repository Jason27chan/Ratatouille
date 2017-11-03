package com.example.jl.ratatouille.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.activity.ViewActivity;
import com.example.jl.ratatouille.data.Data;
import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.grandcentrix.tray.AppPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jav on 10/8/2017.
 *
 * Adapter for the rat list RecyclerView in ListActivity
 * Displays date, address, and city in each row
 * Passes Rat object to ViewActivity for detail view
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Rat> ratList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView date, streetAddress, city;
        public Rat rat;

        public ViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            streetAddress = view.findViewById(R.id.street_address);
            city = view.findViewById(R.id.city);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ViewActivity.class);
            //todo: add call to another method which retrieves all the rat info instead of just date, address, city
            //currently all data is loaded in ListActivity but this is inefficient because only 3 fields are displayed in the main view?
            intent.putExtra("rat", rat);
            context.startActivity(intent);
        }
    }

    /**
     * Initializes the ratList
     * @param context the activity
     */
    public RecyclerViewAdapter(Context context) {
        //this.ratList = ratList;
        this.context = context;
    }

    //inflates the views in the rat list
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ratView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rat_list_row, parent, false);
        return new RecyclerViewAdapter.ViewHolder(ratView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rat rat = ratList.get(position);
        holder.date.setText(rat.getDate().toString());
        holder.streetAddress.setText(rat.getAddress());
        holder.city.setText(rat.getCity());
        holder.rat = rat;
    }

    public void updateData(List<Rat> rats) {
        if (ratList != null) {
            ratList.clear();
            ratList.addAll(rats);
        } else {
            ratList = rats;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ratList.size();
    }

}
