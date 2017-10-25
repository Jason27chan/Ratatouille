package com.example.jl.ratatouille.http;

import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.example.jl.ratatouille.http.WebService.FEED;

/**
 * Created by jav on 10/24/2017.
 */

public interface WebService {

    String BASE_URL = "http://192.241.145.60/";
    String FEED = "ratatouille/rat/jsonfeed.php";

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @GET(FEED)
    Call<Rat[]> rats(@QueryMap Map<String, String> options);
}
