package com.example.TaskFailedNameGame.Retro;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

public abstract class RetroDataRunnable implements Runnable {
    private Queue<String> queue;
    private boolean running;
    private final int maxQueueSize;
    private boolean grabbingData;
    Retro retroInterface;
    private final String typeForLog;

    public RetroDataRunnable(int maxQueueSize, String typeForLog){
        this.maxQueueSize = maxQueueSize;
        this.typeForLog = typeForLog;
        retroInterface = Retro.retro.create(Retro.class);
    }

    @Override
    public void run() {
        queue = new LinkedList<>();
        running = true;
        while(running){
            if(getNumberOfStrings() <= maxQueueSize && !grabbingData) {
                grabbingData = true;
                getDataFromDataBase();
                Log.d("RetroRunnable", "number of " + typeForLog + " Strings " + getNumberOfStrings());
            }
        }
    }

    public void stop(){
        running = false;
    }

    public String getOneString(){
        try {
            return queue.remove();
        }catch (Exception e){
            return "No Data";
        }
    }

    public int getNumberOfStrings(){
        if(running) {
            return queue.size();
        }else {
            return 0;
        }
    }

    protected abstract void getDataFromDataBase();

    protected void addStringToQueue(String s){
        //System.out.println(q);
        queue.add(s);
        grabbingData = false;
    }

    public void printAllData(){
        try {
            for (String s : queue) {
                Log.d("RetroRunnable", s);
            }
        }catch (Exception e){

        }
    }

}
