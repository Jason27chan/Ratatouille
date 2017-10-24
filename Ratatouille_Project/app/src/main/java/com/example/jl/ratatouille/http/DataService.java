package com.example.jl.ratatouille.http;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

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

        RequestPackage requestPackage = intent.getParcelableExtra(REQUEST_PACKAGE);

        String response;
        try {
            response = HttpHelper.downloadFromFeed(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (response != null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Rat[] rats = gson.fromJson(response, Rat[].class);

            Intent messageIntent = new Intent(DATA_SERVICE_MSG);
            messageIntent.putExtra((DATA_SERVICE_PAYLOAD), rats);
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
            manager.sendBroadcast(messageIntent);
        }

    }

}
