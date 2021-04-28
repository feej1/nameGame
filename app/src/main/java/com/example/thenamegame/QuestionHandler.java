package com.example.thenamegame;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * @author Ian Olds
 */

public class QuestionHandler {

    private List<String> questions;
    private List<String> answers;
    private List<List<String>> wrongAns;
    private int currQ;
    private String currAnswer;
    int totalRight;
    Retro interfaceName = Retro.retro.create(Retro.class);

    public QuestionHandler(){
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        wrongAns = new ArrayList<>();
        currAnswer = null;
        currQ = 0;
        totalRight = 0;
        populateFields();
    }


   private void populateFields(){
       Call<JsonArray> call = interfaceName.getOneNameMultipleYears();
       call.enqueue(new Callback<JsonArray>() {
           @Override
           public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
               Log.d("GETALL", response.body().toString());
               System.out.println(response.body().toString());
           } //ends overiddden method

           @Override
           public void onFailure(Call<JsonArray> call, Throwable t) {
               Log.d("GETALL", "FAILED     " + t.toString());

           }

       });   //end enqeue
   }

    public void startQuestions(){currQ =1; }

    public boolean  submit(String s) throws Exception{
        Boolean isRight;
        if (getQuestionAnswer() == s) isRight = true;
        else isRight= false;
        return isRight;
    }
    private boolean atEnd(){
        if (currQ <= 10) return false;
        else return true;
    }

    public boolean advancedQuestion(){
        if (!atEnd()) {
            currQ++ ;
            return true;
        }
        else return false;
    }

    public String getQuestionAnswer(){     //get the questions answer
      return answers.get(currQ);
    }

    public String getQuestion(){     //gets the curr questions
        return questions.get(currQ);
    }

    //returns list of strings!!!
    public List<String> getWrongAnswer(){   //gets the current wrong answers
        return wrongAns.get(currQ);
    }

    public int getCurrQ() {      //gets the current question number
        return currQ;
    }

    public List<String> getAllAnswers(){
        List<String> allAnswers = wrongAns.get(currQ);
        allAnswers.add(allAnswers.get(currQ));
        return allAnswers;
    }

}
