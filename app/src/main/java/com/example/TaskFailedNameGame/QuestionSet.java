package com.example.TaskFailedNameGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionSet implements Serializable {
    private ArrayList<Question> questionList;

    public QuestionSet(ArrayList<Question> questionList){
        this.questionList = questionList;
    }

    public QuestionSet(){
        questionList = new ArrayList<>();
    }

    public void add(Question question){
        questionList.add(question);
    }

    public Question getQuestion(int i){
        return questionList.get(i);
    }

    public int getNumberOfQuestions(){
        return questionList.size();
    }
}
