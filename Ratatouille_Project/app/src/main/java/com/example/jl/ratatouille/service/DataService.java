package com.example.jl.ratatouille.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jl.ratatouille.data.Data;
import com.example.jl.ratatouille.model.Rat;

import java.io.IOException;
import java.util.Arrays;
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

    /** Gets rats
     *
     * @param intent
     *
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        APIService apiService = APIService.retrofit.create(APIService.class);
        Map<String, String> options = (Map) intent.getSerializableExtra("options");
        Call<Rat[]> call = apiService.rats(options);
        Rat[] rats;
        try {
            rats = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "onHandleIntent: " + e.getMessage());
            return;
        }

        Data.rats = Arrays.asList(rats);
        Data.options = options;

    }

}
