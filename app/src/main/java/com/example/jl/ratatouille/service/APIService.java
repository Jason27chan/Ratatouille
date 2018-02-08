package com.example.jl.ratatouille.service;

import com.example.jl.ratatouille.model.MSG;
import com.example.jl.ratatouille.model.Rat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Class for APIService usage for Google Maps
 *
 * Created by Jasmine on 10/24/2017.
 */

public interface APIService {

    String BASE_URL = "http://192.241.145.60/";
    String FEED = "php/jsonfeed.php";
    String ADD = "php/add.php";
    String LOGIN = "php/authenticate/login.php";
    String REGISTER = "php/authenticate/register.php";

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @GET(FEED)
    Call<Rat[]> rats(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST(ADD)
    Call<MSG> addRat(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST(LOGIN)
    Call<MSG> login(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST(REGISTER)
    Call<MSG> register(@FieldMap Map<String, String> data);

}
