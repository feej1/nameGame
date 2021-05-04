package com.example.TaskFailedNameGame.Retro;

import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomNamesRetro extends RetroDataRunnable {

    public RandomNamesRetro(){
        super(10, "names");
    }

    @Override
    protected void getDataFromDataBase() {
        Call<JsonObject> call = retroInterface.getRandomName();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("RetroRunnable GETALL", response.body().toString());
                //System.out.println(response.body().toString());
                addStringToQueue(response.body().get("name").getAsString());
            } //ends overiddden method

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("RetroRunnable GETALL", "FAILED     " + t.toString());

            }

        });   //end enqeue
    }
}
