package com.example.thenamegame.Retro;

import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class oneNameMultipleYearsRetro extends RetroRunnable{

    public oneNameMultipleYearsRetro(){
        super(10);
    }

    @Override
    protected void getQuestionFromDataBase() {
        Call<JsonObject> call = retroInterface.getOneNameMultipleYears();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("GETALL", response.body().toString());
                //System.out.println(response.body().toString());
                fillLists(response.body());
            } //ends overiddden method

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("GETALL", "FAILED     " + t.toString());

            }

        });   //end enqeue
    }
}
