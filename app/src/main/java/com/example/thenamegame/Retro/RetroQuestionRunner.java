package com.example.thenamegame.Retro;

import com.example.thenamegame.Question;

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
            } catch (Exception exc) {
            }
        }
        return instance;
    }

    public RetroQuestionRunner(RetroRunnable retroRunnable){
        this.retroRunnable = retroRunnable;
    }

    public void start(){

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
