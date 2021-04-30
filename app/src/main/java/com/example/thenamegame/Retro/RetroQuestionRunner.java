package com.example.thenamegame.Retro;

import android.util.Log;

import com.example.thenamegame.Question;

public class RetroQuestionRunner {
    private Thread thread;

    private boolean started = false;
    private RetroRunnable retroRunnable;

    private static RetroQuestionRunner oneNameInstance;
    private static RetroQuestionRunner oneYearInstance;

    /**
     * @return The only instance that will run one name multiple years
     */
    public static RetroQuestionRunner getOneNameInstance(){
        if (oneNameInstance == null) {
            try {
                oneNameInstance = new RetroQuestionRunner(new oneNameMultipleYearsRetro());
                System.out.println("returning a NAME RUNNER");
            } catch (Exception exc) {
                System.out.println("RUNNER DOES NOT ESIST--------------");
            }
        }
        return oneNameInstance;
    }

    /**
     * @return The only instance that will run one name multiple years
     */
    public static RetroQuestionRunner getOneYearInstance(){
        if (oneYearInstance == null) {
            try {
                oneYearInstance = new RetroQuestionRunner(new oneYearMultipleNamesRetro());
                System.out.println("returning a YEAR RUNNER");
            } catch (Exception exc) {
                System.out.println("RUNNER DOES NOT ESIST--------------");
            }
        }
        return oneYearInstance;
    }

    public RetroQuestionRunner(RetroRunnable retroRunnable){
        this.retroRunnable = retroRunnable;
    }

    public void start(){
        Log.d("RetroQuestionRunner", "Starting...");
        thread = new Thread(retroRunnable);
        thread.start();
        started = true;
    }

    public Question getOneQuestion() {
        return retroRunnable.getOneQuestion();
    }

    private void stop(){
        if(retroRunnable != null){
            retroRunnable.stop();
        }
        retroRunnable = null;
        thread = null;
        started = false;
    }
    public boolean isStarted(){
        return started;
    }

    public void printAllQuestions(){
        retroRunnable.printAllQuestions();
    }
}
