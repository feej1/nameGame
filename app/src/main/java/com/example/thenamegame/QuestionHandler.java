package com.example.thenamegame;

import com.example.thenamegame.Retro.RetroQuestionRunner;

import java.util.List;

/**
 * @author Ian Olds
 */

public class QuestionHandler {


    private int questionNumber;
    private Question currQuestion;

    int totalRight;
    RetroQuestionRunner retroQuestionRunner;

    public QuestionHandler(){
        currQuestion = null;
        questionNumber = 0;
        totalRight = 0;
        RetroQuestionRunner.getOneNameInstance();
    }

    public boolean getNewQuestion(){
        if (!atEnd()) {
            questionNumber++;
            currQuestion = retroQuestionRunner.getOneQuestion();
            return true;
        }
        else return false;
    }

    public boolean submit(String s) {
        return getQuestionAnswer().equals(s);
    }
    private boolean atEnd(){
        return !(questionNumber <= 10);
    }


    public String getQuestionAnswer(){     //get the questions answer
      return currQuestion.getAnswer();
    }

    public String getQuestion(){     //gets the curr questions
        return currQuestion.getQuestion();
    }

    //returns list of strings!!!
    public List<String> getWrongAnswers(){   //gets the current wrong answers
        return currQuestion.getWrongAnswers();
    }

    public int getQuestionNumber() {      //gets the current question number
        return questionNumber;
    }

    public List<String> getAllAnswers(){
        List<String> allAnswers = getWrongAnswers();
        allAnswers.add(getQuestionAnswer());
        return allAnswers;
    }

}
