package com.example.thenamegame.Retro;

import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class oneYearMultipleNamesRetro extends RetroRunnable{

    public oneYearMultipleNamesRetro(){
        super(10);
    }

    @Override
    protected void getQuestionFromDataBase() {
        Call<JsonObject> call = retroInterface.getOneYearMultipleNames();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("RetroRunnable GETALL", response.body().toString());
                //System.out.println(response.body().toString());
                fillLists(response.body());
            } //ends overiddden method

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("RetroRunnable GETALL", "FAILED     " + t.toString());

            }

        });   //end enqeue
    }

    /**
     * THis method must be overwitten for each question type
     * @param correct the correct answer
     * @return a list of 4 other incorrect answers
     */
    @Override
    protected List<String> makeWrongAnswers(String correct) {
        return new ArrayList<>();
    }
}