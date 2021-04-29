package com.example.thenamegame.Retro;

import android.util.Log;

import com.example.thenamegame.Question;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RetroRunnable implements Runnable {
    private Queue<Question> preparedQuestions;
    private boolean running;
    private int maxQueueSize;
    Retro retroInterface;

    public RetroRunnable(int maxQueueSize){
        this.maxQueueSize = maxQueueSize;
        retroInterface = Retro.retro.create(Retro.class);
    }

    @Override
    public void run() {
        running = true;
        while(running){
            if(getNumberOfPreparedQuestions() <= maxQueueSize) {
                getQuestionFromDataBase();
            }
        }
    }

    public void stop(){
        running = false;
    }

    public Question getOneQuestion(){
        try {
            return preparedQuestions.remove();
        }catch (Exception e){
            return new Question("Question Not Found", "", "", "", "", "");
        }
    }

    public int getNumberOfPreparedQuestions(){
        return preparedQuestions.size();
    }

    protected abstract void getQuestionFromDataBase();

    protected void fillLists(JsonObject obj){
        String q = obj.get("question").getAsString();
        String a = obj.get("answer").getAsString();
        //System.out.println(q);
        preparedQuestions.add(new Question(q, a, makeWrongYears(a)));
    }

    private List<String> makeWrongYears(String correct){
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

    public void printAllQuestions(){
        for(Question q : preparedQuestions){
            Log.d("RetroRunnable", q.getQuestion() + ", " +q.getAnswer() + ", ");
        }
    }
}
