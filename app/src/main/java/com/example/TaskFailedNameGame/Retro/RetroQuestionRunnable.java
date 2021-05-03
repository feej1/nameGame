package com.example.TaskFailedNameGame.Retro;

import android.util.Log;

import com.example.TaskFailedNameGame.Question;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class RetroQuestionRunnable implements Runnable {

    private final int maxQueueSize;
    private final String typeForLog;

    protected Queue<Question> queue;
    protected Retro retroInterface;

    private boolean running;
    private boolean grabbingQuestion;

    //null if not used.
    private RetroDataRunner optionalDataRunner;

    public RetroQuestionRunnable(int maxQueueSize,String typeForLog){
        this.maxQueueSize = maxQueueSize;
        this.typeForLog = typeForLog;
        retroInterface = Retro.retro.create(Retro.class);
    }

    public RetroQuestionRunnable(int maxQueueSize, RetroDataRunner optionalDataRunner ,String typeForLog){
        this.maxQueueSize = maxQueueSize;
        this.typeForLog = typeForLog;
        this.optionalDataRunner = optionalDataRunner;

        if(!optionalDataRunner.isStarted()){
            optionalDataRunner.start();
        }

        retroInterface = Retro.retro.create(Retro.class);

    }

    @Override
    public void run() {
        queue = new LinkedList<>();
        running = true;
        while(running){
            if(getNumberOfPreparedQuestions() <= maxQueueSize && !grabbingQuestion && areQuestionsAvailable()) {
                grabbingQuestion = true;
                getQuestionFromDataBase();
                Log.d("RetroRunnable", "number of "+ typeForLog +" questions " + getNumberOfPreparedQuestions());
            }
        }
    }

    private boolean areQuestionsAvailable(){
        if(optionalDataRunner != null){
            return optionalDataRunner.getNumberOfStrings() > 8;
        }
        return true;
    }

    public void stop(){
        running = false;
    }

    public Question getOneQuestion(){
        try {
            return queue.remove();
        }catch (Exception e){
            return new Question("Question Not Found", "", "", "", "", "");
        }
    }

    public int getNumberOfPreparedQuestions(){
        return queue.size();
    }

    protected abstract void getQuestionFromDataBase();

    protected void fillLists(JsonObject obj){
        String q = obj.get("question").getAsString();
        String a = obj.get("answer").getAsString();
        //System.out.println(q);
        queue.add(new Question(q, a, makeWrongAnswers(a)));
        grabbingQuestion = false;
    }

   protected abstract ArrayList<String> makeWrongAnswers(String correct);

    public void printAllQuestions(){
        for(Question q : queue){
            Log.d("RetroRunnable", q.getQuestion() + ", " +q.getAnswer() + ", ");
        }
    }

}
