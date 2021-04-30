package com.example.TaskFailedNameGame.Retro;

import android.util.Log;

import com.example.TaskFailedNameGame.Question;

public class RetroQuestionRunner {
    private Thread thread;

    private boolean started = false;
    private RetroRunnable retroRunnable;

    private static RetroQuestionRunner instance;

    /**
     *
     * @return The only instance that will run one name multiple years
     */
    public static RetroQuestionRunner getOneNameInstance(){
        if (instance == null) {
            try {
                instance = new RetroQuestionRunner(new oneNameMultipleYearsRetro());
                System.out.println("returning a RUNNER");
            } catch (Exception exc) {
                System.out.println("RUNNER DOES NOT ESIST--------------");
            }
        }
        return instance;
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
