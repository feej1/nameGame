package com.example.TaskFailedNameGame;

import android.util.Log;

import com.example.TaskFailedNameGame.Retro.RetroQuestionRunner;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ian Olds
 */

public class QuestionHandler {


    private int questionNumber;
    private Question currQuestion;
    private QuestionSet questionSet;

    int totalRight;
    RetroQuestionRunner retroQuestionRunner;

    public QuestionHandler(RetroQuestionRunner retroQuestionRunnerInstance){
        currQuestion = null;
        questionNumber = 0;
        totalRight = 0;
        retroQuestionRunner = retroQuestionRunnerInstance;
        questionSet = new QuestionSet();
    }

    public boolean getNewQuestion(){
        if (!atEnd()) {
            questionNumber++;
            if(currQuestion != null && currQuestion.answered()) {
                questionSet.add(currQuestion);
                Log.d("QuestionHandler", "added Question" + currQuestion.getQuestion()
                        + " Answer: " + currQuestion.getAnswer() + "Chosen: " + currQuestion.getChosenAnswer());
            }
            currQuestion = retroQuestionRunner.getOneQuestion();
            return true;
        }

        if(currQuestion != null && currQuestion.answered()) {
            questionSet.add(currQuestion);
            Log.d("QuestionHandler", "added Question" + currQuestion.getQuestion()
                    + " Answer: " + currQuestion.getAnswer() + "Chosen: " + currQuestion.getChosenAnswer());
        }
        return false;
    }

    public boolean submit(String s) {
        boolean correct = currQuestion.setChosenAnswer(s);
        if(correct){
            totalRight++;
        }
        return correct;
    }
    private boolean atEnd(){
        return !(questionNumber <= 9);
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

    public QuestionSet getQuestionSet(){
        return questionSet;
    }

}
