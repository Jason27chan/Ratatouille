package com.example.jl.ratatouille.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.activity.ViewActivity;
import com.example.jl.ratatouille.model.Rat;

import java.util.List;

/**
 * Created by Jasmine on 10/8/2017.
 *
 * Adapter for the rat list RecyclerView in ListActivity
 * Displays date, address, and city in each row
 * Passes Rat object to ViewActivity for detail view
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Rat> ratList;
    private final Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView date, address, city;
        private Rat rat;

        /**
         * Creates a ViewHolder. Sets the date, address, and city to be
         * displayed.
         *
         * @param view the View to create the ViewHolder from
         */
        public ViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            address = view.findViewById(R.id.street_address);
            city = view.findViewById(R.id.city);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ViewActivity.class);
            intent.putExtra("rat", rat);
            context.startActivity(intent);
        }
    }

    /**
     * Creates a RecyclerViewAdapter.
     * @param context the context from which this activity is called
     */
    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View ratView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rat_list_row, parent, false);
        return new RecyclerViewAdapter.ViewHolder(ratView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rat rat = ratList.get(position);
        holder.date.setText(rat.getDate().toString());
        holder.address.setText(rat.getAddress());
        holder.city.setText(rat.getCity());
        holder.rat = rat;
    }
    
    /**
     * Clears the RecyclerView and adds new rat sighting data.
     *
     * @param rats the List of new rat sighting data to be displayed
     *             by the RecyclerView
    */
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
