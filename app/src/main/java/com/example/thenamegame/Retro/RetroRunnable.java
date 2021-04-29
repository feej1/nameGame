package com.example.thenamegame.Retro;

import android.util.Log;

import com.example.thenamegame.Question;
import com.google.gson.JsonObject;

import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetroRunnable implements Runnable {
    private Queue<Question> preparedQuestions;
    private boolean running;

    @Override
    public void run() {
        running = true;
        while(running){
            getQuestionFromDataBase();
        }
    }

    public void stop(){
        running = false;
    }

    public Question getOneQuestion(){
        try {
            return preparedQuestions.remove();
        }catch (Exception e){
            return new Question("Question Not FOund", "", "", "", "", "");
        }
    }

    public int getNumberOfPreparedQuestions(){
        return preparedQuestions.size();
    }

    protected abstract void getQuestionFromDataBase();
}
