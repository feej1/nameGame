package com.example.TaskFailedNameGame.Retro;

import android.util.Log;


public class RetroDataRunner {
    private Thread thread;

    private boolean started = false;
    private RetroDataRunnable retroDataRunnable;


    private static RetroDataRunner randomNameInstance;

    /**
     * @return The only instance that will run one name multiple years
     */
    public static RetroDataRunner getRandomNameInstance(){
        if (randomNameInstance == null) {
            try {
                randomNameInstance = new RetroDataRunner(new RandomNamesRetro());
                System.out.println("returning a NAME RUNNER");
            } catch (Exception exc) {
                System.out.println("RUNNER FAILED TO BE CREATED--------------");
            }
        }
        return randomNameInstance;
    }

    public RetroDataRunner(RetroDataRunnable retroDataRunnable){
        this.retroDataRunnable = retroDataRunnable;
    }

    public void start(){
        if(!started) {
            Log.d("RetroDataRunner", "Starting...");
            thread = new Thread(retroDataRunnable);
            thread.start();
            started = true;
        }else{
            Log.d("RetroDataRunner", "Already Started");
        }
    }

    public String getOneString() {
        return retroDataRunnable.getOneString();
    }

    public int getNumberOfStrings(){
        return retroDataRunnable.getNumberOfStrings();
    }

    private void stop(){
        if(retroDataRunnable != null){
            retroDataRunnable.stop();
        }
        retroDataRunnable = null;
        thread = null;
        started = false;
    }
    public boolean isStarted(){
        return started;
    }

    public void printAllData(){
        retroDataRunnable.printAllData();
    }
}
