package com.example.TaskFailedNameGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable {

    private final String question;
    private String answer;
    private ArrayList<String> wrongAnswers;
    private String chosenAnswer;

    public Question(String question, String answer, ArrayList<String> wrongAnswers){
        this.question = question;
        this.answer = answer;
        this.wrongAnswers = wrongAnswers;
    }

    public Question(String question, String answer, String wrongAnswer1,String wrongAnswer2,String wrongAnswer3,String wrongAnswer4){
        this.question = question;
        this.answer = answer;
        this.wrongAnswers = new ArrayList<>();
        wrongAnswers.add(wrongAnswer1);
        wrongAnswers.add(wrongAnswer2);
        wrongAnswers.add(wrongAnswer3);
        wrongAnswers.add(wrongAnswer4);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public String getOneWrongAnswer(int i) {
        if (i >= 0 && i < 4) {
            return wrongAnswers.get(i);
        }
        return null;
    }

    public String getChosenAnswer(){
        return chosenAnswer;
    }

    public boolean setChosenAnswer(String chosenAnswer){
        this.chosenAnswer = chosenAnswer;
        return isCorrect();
    }

    public boolean isCorrect(){
        if(!answered()){
            return false;
        }
        return chosenAnswer.equals(answer);
    }

    public boolean answered(){
        return chosenAnswer != null;
    }
}
