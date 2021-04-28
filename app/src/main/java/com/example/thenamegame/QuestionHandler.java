package com.example.thenamegame;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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


    private void fillLists(JsonObject obj){
            String q = obj.get("question").getAsString();
            String a = obj.get("answer").getAsString();
            System.out.println(q);
            questions.add(q);
            answers.add(a);
    }

   private void populateFields(){
        for(int i = 0; i<10; i++) {
            Call<JsonObject> call = interfaceName.getOneNameMultipleYears();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d("GETALL", response.body().toString());
                    //System.out.println(response.body().toString());
                    fillLists(response.body());
                } //ends overiddden method

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("GETALL", "FAILED     " + t.toString());

                }

            });   //end enqeue
        }
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


}
