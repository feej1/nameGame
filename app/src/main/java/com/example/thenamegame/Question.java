package com.example.thenamegame;

import java.util.ArrayList;

public class Question {

    private String question;
    private String answer;
    private ArrayList<String> wrongAnswers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public String getOneWrongAnswer(int i) {
        if (i >= 0 && i < 4) {
            return wrongAnswers.get(i);
        }
        return null;
    }
    public void addOneWrongAnswer(String wrongAnswer) {
        wrongAnswers.add(wrongAnswer);
    }
}
