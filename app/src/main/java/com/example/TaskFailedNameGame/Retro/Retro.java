package com.example.TaskFailedNameGame.Retro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Retro {

    //gets a name, the correct year, and 4 other random years
    @GET("getYearQuestions")
    Call<JsonObject> getOneNameMultipleYears();

    //gets a year, the correct name, and 4 other random names
    @GET("getNameQuestions")
    Call<JsonObject> getOneYearMultipleNames();

    @GET("getOneStar")
    Call<JsonObject> getOneStar();

    @GET("getTwoStar")
    Call<JsonObject> getTwoStar();

    @GET("getThreeStar")
    Call<JsonObject> getThreeStar();

    @GET("getFourStar")
    Call<JsonObject> getFourStar();

    @GET("getFiveStar")
    Call<JsonObject> getFiveStar();

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
            .baseUrl("http://ukko.d.umn.edu:8090/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
