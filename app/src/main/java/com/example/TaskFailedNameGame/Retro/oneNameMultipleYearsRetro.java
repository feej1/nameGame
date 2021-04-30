package com.example.TaskFailedNameGame.Retro;

import android.util.Log;

import com.google.gson.JsonObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        List<String> toPutinWorngAnswers= new ArrayList<>();
        for (int i =0; i<4; i++) {
            int wrongYear = random.nextInt(127) + 1880; // makes a random int between 0 and 126 (range of years) then adds min year to get 1880-2007
            String strWrongYear = String.valueOf(wrongYear);
            if (!toPutinWorngAnswers.contains(strWrongYear) && correct!=strWrongYear)
                toPutinWorngAnswers.add(strWrongYear);
        }
        return toPutinWorngAnswers;
    }
}
