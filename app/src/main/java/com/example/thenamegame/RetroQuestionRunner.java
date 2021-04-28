package com.example.thenamegame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RetroQuestionRunner {
    private Thread thread;
    private Queue<Question> preparedQuestions;

    private static RetroQuestionRunner instance;
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

    public RetroQuestionRunner(){
        preparedQuestions = new LinkedList<>();
    }

    public void start(){

    }

    private void stop(){

    }

    public Question getOneQuestion(){
        try {
            return preparedQuestions.remove();
        }catch (Exception e){
            return new Question("Question Not FOund", "", "", "", "", "");
        }
    }
}
