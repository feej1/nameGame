package com.example.thenamegame.Retro;

import com.example.thenamegame.Question;

public class RetroQuestionRunner {
    private Thread oneNameMultipleYearsThread;

    private boolean running = false;
    private RetroRunnable oneNameMultipleYearsRunnable;

    private static RetroQuestionRunner instance;

    /**
     *
     * @return The only instance of this class ever.
     */
    public static RetroQuestionRunner getInstance(){
        if (instance == null) {
            try {
                instance = new RetroQuestionRunner();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return instance;
    }

    public void start(){
        oneNameMultipleYearsThread = new Thread(oneNameMultipleYearsRunnable);
        oneNameMultipleYearsThread.start();
    }

    public Question getOneNameMultipleYearsQuestion() {
        return oneNameMultipleYearsRunnable.getOneQuestion();
    }

    private void stop(){

    }
}
