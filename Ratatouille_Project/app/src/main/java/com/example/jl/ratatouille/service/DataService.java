package com.example.jl.ratatouille.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.grandcentrix.tray.AppPreferences;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * /**
 * Gets Rats from database based on filter queries.
 * Updates Rats in shared preferences.
 * Returns a List of Rats from shared preferences.
 *
 * Created by jav on 10/20/2017.
 */

public class DataService extends IntentService {

    private static final String TAG = "DataService";
    public static final String DATA_SERVICE_MSG = "dataServiceMessage";
    //public static final String DATA_SERVICE_PAYLOAD = "dataServicePayload";
    //public static final String REQUEST_PACKAGE = "requestPackage";

    public static final String SHARED_RATS = "rats";
    private static final String SHARED_OPTIONS = "options";

    /**
     * default constructor for DataService()
     */
    public DataService() {
        super("DataService");
    }

    /**
     * Performs a Retrofit call to obtain Rats based on filter
     * queries that are passed in through the intent.
     *
     * @param intent the intent which starts this service
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        APIService apiService = APIService.retrofit.create(APIService.class);
        Map<String, String> options =
                (Map<String, String>) intent.getSerializableExtra("options");
        Call<Rat[]> call = apiService.rats(options);
        Rat[] rats;
        try {
            rats = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onHandleIntent: " + e.getMessage());
            return;
        }
        updatePreferences(rats, options, DataService.this);
    }

    /**
     * Updates the Rats in shared preferences.
     * Updates the most-recent filter options in shared preferences.
     *
     * @param rats an array of Rats obtained from a Retrofit call
     * @param context the context from which this method is called
     * @param options a hash map of options that are string mapped to string
     */
    private static void updatePreferences(
            Rat[] rats, Map<String, String> options, Context context) {
        final AppPreferences prefs = new AppPreferences(
                context.getApplicationContext());
        Gson gson = new Gson();
        String jsonRats = gson.toJson(rats);
        String jsonOptions = gson.toJson(options);
        prefs.put(SHARED_RATS, jsonRats);
        prefs.put(SHARED_OPTIONS, jsonOptions);
        Intent messageIntent = new Intent(DATA_SERVICE_MSG);
        LocalBroadcastManager manager = LocalBroadcastManager
                .getInstance(context.getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    /**
     * Returns the Rats contained in shared preferences.
     * Rats are accessible to all classes.
     *
     * @param context the context from which this method is called
     * @return a list of the Rats in shared preferences
     */
    public static List<Rat> getSharedRats(Context context) {
        final AppPreferences prefs = new AppPreferences(
                context.getApplicationContext());
        final String json = prefs.getString(SHARED_RATS, null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Rat>>() {}.getType();
        List<Rat> ratList = gson.fromJson(json, type);
        return ratList;
    }

    /**
     * Returns the last inputted filter options.
     * Accessible to all classes.
     *
     * @param context the context from which this method is called
     * @return a Map of the filter options
     */
    public static Map<String, String> getSharedOptions(Context context) {
        final AppPreferences prefs = new AppPreferences(
                context.getApplicationContext());
        final String json = prefs.getString(SHARED_OPTIONS, null);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> optionsTree = gson.fromJson(json, type);
        Map<String, String> options = new HashMap<>();
        if (optionsTree != null) {
            for (String k : optionsTree.keySet()) {
                options.put(k, optionsTree.get(k));
            }
        }
        return options;
    }


}
