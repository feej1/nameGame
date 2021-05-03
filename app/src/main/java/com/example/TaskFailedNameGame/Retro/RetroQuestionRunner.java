package com.example.TaskFailedNameGame.Retro;

import android.util.Log;

import com.example.TaskFailedNameGame.Question;

public class RetroQuestionRunner {
    private Thread thread;

    private boolean started = false;
    private RetroQuestionRunnable retroQuestionRunnable;

    private static RetroQuestionRunner oneNameInstance;
    private static RetroQuestionRunner oneYearInstance;

    /**
     * @param difficulty 0=any, 1-5 = 1-5 stars
     * @return The only instance that will run one name multiple years
     */
    public static RetroQuestionRunner getOneNameInstance(int difficulty){
        if (oneNameInstance == null) {
            try {
                oneNameInstance = new RetroQuestionRunner(new OneNameMultipleYearsRetro());
                System.out.println("returning a NAME RUNNER");
            } catch (Exception exc) {
                System.out.println("RUNNER FAILED TO BE CREATED--------------");
            }
        }
        return oneNameInstance;
    }

    /**
     * @param difficulty 0=any, 1-5 = 1-5 stars
     * @return The only instance that will run one name multiple years
     */
    public static RetroQuestionRunner getOneYearInstance(int difficulty){
        if (oneYearInstance == null) {
            try {
                oneYearInstance = new RetroQuestionRunner(new OneYearMultipleNamesRetro());
                System.out.println("returning a YEAR RUNNER");
            } catch (Exception exc) {
                System.out.println("RUNNER DOES NOT ESIST--------------");
            }
        }
        return oneYearInstance;
    }


    public RetroQuestionRunner(RetroQuestionRunnable retroQuestionRunnable){
        this.retroQuestionRunnable = retroQuestionRunnable;
    }

    public void start(){
        if(!started) {
            Log.d("RetroQuestionRunner", "Starting...");
            thread = new Thread(retroQuestionRunnable);
            thread.start();
            started = true;
        }else{
            Log.d("RetroQuestionRunner", "Already Started");
        }
    }

    public Question getOneQuestion() {
        return retroQuestionRunnable.getOneQuestion();
    }

    private void stop(){
        if(retroQuestionRunnable != null){
            retroQuestionRunnable.stop();
        }
        retroQuestionRunnable = null;
        thread = null;
        started = false;
    }
    public boolean isStarted(){
        return started;
    }

    public void printAllQuestions(){
        retroQuestionRunnable.printAllQuestions();
    }
}
