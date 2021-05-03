package com.example.TaskFailedNameGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionSet implements Serializable {
    private ArrayList<Question> questionList;
    private int numCorrect;

    public QuestionSet(ArrayList<Question> questionList){
        this.questionList = questionList;
        numCorrect = 0;
    }

    public QuestionSet(){
        questionList = new ArrayList<>();
        numCorrect = 0;
    }

    public void add(Question question){
        questionList.add(question);
        if(question.isCorrect()){
            numCorrect++;
        }
    }

    public Question getQuestion(int i){
        return questionList.get(i);
    }

    public int getNumberOfQuestions(){
        return questionList.size();
    }

    public int getNumCorrect(){
        return numCorrect;
    }
}
