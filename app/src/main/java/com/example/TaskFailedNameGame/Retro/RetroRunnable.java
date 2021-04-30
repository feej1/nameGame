package com.example.thenamegame.Retro;

import android.util.Log;

import com.example.thenamegame.Question;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class RetroRunnable implements Runnable {
    private Queue<Question> preparedQuestions;
    private boolean running;
    private int maxQueueSize;
    private boolean grabbingQuestion;
    Retro retroInterface;

    public RetroRunnable(int maxQueueSize){
        this.maxQueueSize = maxQueueSize;
        retroInterface = Retro.retro.create(Retro.class);
    }

    @Override
    public void run() {
        preparedQuestions = new LinkedList<>();
        running = true;
        while(running){
            if(getNumberOfPreparedQuestions() <= maxQueueSize && !grabbingQuestion) {
                grabbingQuestion = true;
                getQuestionFromDataBase();
                Log.d("RetroRunnable", "number of questions" + getNumberOfPreparedQuestions());
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
        preparedQuestions.add(new Question(q, a, makeWrongAnswers(a)));
        grabbingQuestion = false;
    }

   protected abstract List<String> makeWrongAnswers(String correct);

    public void printAllQuestions(){
        for(Question q : preparedQuestions){
            Log.d("RetroRunnable", q.getQuestion() + ", " +q.getAnswer() + ", ");
        }
    }

}
