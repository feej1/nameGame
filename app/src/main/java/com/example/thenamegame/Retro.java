package com.example.thenamegame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retro {

    //gets a name, the correct year, and 4 other random years
    @GET("getOneNameMultipleYears")
    Call<JsonArray> getOneNameMultipleYears();

    //gets a year, the correct name, and 4 other random names
    @GET("getOneYearMultipleNames")
    Call<JsonArray> getOneYearMultipleNames();

    /*
    Example from TechPrep
     */
//    @GET("getAllSubmissions")
//    Call<JsonArray> getSubmissions();
//
//    @Headers({"Content-Type: application/json"})
//    @POST("sendAdminResponse")
//    Call<JsonObject> sendResponse(@Body JsonObject object);
//
//    @GET("getTechnical")
//    Call<JsonObject> getTechnical();
//
//    @GET("getFlash")
//    Call<JsonObject> getFlash();
//
//    @Headers({"Content-Type: application/json"})
//    @POST("createFlashAPI")
//    Call<JsonObject> createFlash(@Body JsonObject object);
//
//
//    @Headers({"Content-Type: application/json"})
//    @POST("createTechAPI")
//    Call<JsonObject> createTech(@Body JsonObject object);
//
//    @GET("getUser")
//    Call<JsonObject> getUser(@Query("email") String email);
//
//    @Headers({"Content-Type: application/json"})
//    @POST("RankFlashAdmin")
//    Call<JsonObject> RankFlashAdmin(@Body JsonObject object);



    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static final Retrofit retro = new Retrofit.Builder()
            .baseUrl("http://ukko.d.umn.edu:8087/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
