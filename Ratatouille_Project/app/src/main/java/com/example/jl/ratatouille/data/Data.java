package com.example.jl.ratatouille.data;

import android.content.Context;
import android.content.Intent;

import com.example.jl.ratatouille.model.Rat;
import com.example.jl.ratatouille.service.DataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jav on 11/3/2017.
 */

public class Data {
    public static List<Rat> rats = new ArrayList<>();
    public static Map<String, String> options = new HashMap<>();

    private Data() {}

    public static List<Rat> getInstance() {
        return rats;
    }

    /**
     * Updates the data based on last filter parameters
     * @param context the context from which this method is called
     */
    public static void updateData(Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.putExtra("options", (HashMap) options);
        context.startService(intent);
    }
}
