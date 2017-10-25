package com.example.jl.ratatouille.http;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by jav on 10/20/2017.
 */

public class DataService extends IntentService {

    public static final String TAG = "DataService";
    public static final String DATA_SERVICE_MSG = "dataServiceMessage";
    public static final String DATA_SERVICE_PAYLOAD = "dataServicePayload";
    public static final String REQUEST_PACKAGE = "requestPackage";

    public DataService() {
        super("DataService");
    }

    /** Adds a rat entry to database asynchronously
     *
     * @param intent Intent to add rat
     *
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        WebService webService = WebService.retrofit.create(WebService.class);
        Map<String, String> options = (Map) intent.getSerializableExtra("options");
        Call<Rat[]> call = webService.rats(options);
        Rat[] rats;
        try {
            rats = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onHandleIntent: " + e.getMessage());
            return;
        }
        Intent messageIntent = new Intent(DATA_SERVICE_MSG);
        messageIntent.putExtra((DATA_SERVICE_PAYLOAD), rats);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);

    }

}
