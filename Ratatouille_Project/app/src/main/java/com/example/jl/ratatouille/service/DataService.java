package com.example.jl.ratatouille.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jl.ratatouille.data.Data;
import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.grandcentrix.tray.AppPreferences;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.example.jl.ratatouille.data.Data.rats;

/**
 * Created by jav on 10/20/2017.
 */

public class DataService extends IntentService {

    public static final String TAG = "DataService";
    public static final String DATA_SERVICE_MSG = "dataServiceMessage";
    public static final String DATA_SERVICE_PAYLOAD = "dataServicePayload";
    public static final String REQUEST_PACKAGE = "requestPackage";

    private static final String PREFERENCE_NAME = "DefaultPreferences";

    public DataService() {
        super("DataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        APIService apiService = APIService.retrofit.create(APIService.class);
        Map<String, String> options =
                (Map) intent.getSerializableExtra("options");
        Call<Rat[]> call = apiService.rats(options);
        Rat[] rats;
        try {
            rats = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onHandleIntent: " + e.getMessage());
            return;
        }
        updateRats(rats, DataService.this);
    }

    public static void updateRats(Rat[] rats, Context context) {
        final AppPreferences prefs = new AppPreferences(
                context.getApplicationContext());
        Gson gson = new Gson();
        String json = gson.toJson(rats);
        prefs.put("RATS", json);

        Intent messageIntent = new Intent(DATA_SERVICE_MSG);
        messageIntent.putExtra((DATA_SERVICE_PAYLOAD), rats);
        LocalBroadcastManager manager = LocalBroadcastManager
                .getInstance(context.getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    public static List<Rat> getRats(Context context) {
        final AppPreferences prefs = new AppPreferences(
                context.getApplicationContext());
        final String json = prefs.getString("RATS", null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Rat>>() {}.getType();

        List<Rat> ratList = gson.fromJson(json, type);
        return ratList;
    }


}
