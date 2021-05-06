package com.example.TaskFailedNameGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TaskFailedNameGame.Retro.Retro;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PerformanceDisplayActivity extends AppCompatActivity {

    private List<Button> performanceViews = new ArrayList<>();
    private QuestionSet questionSet;
    private boolean dataSent = false;
    private boolean sendingData = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_display);
        getSupportActionBar().hide();

        Intent intent = getIntent();

        dataSent = false;
        questionSet = (QuestionSet) intent.getSerializableExtra("questionSet");

        if (questionSet == null) {
            questionSet = new QuestionSet();
            for (int i = 0; i < 10; i++) {
                questionSet.add(new Question("Question Not Found", "", "", "", "", ""));
            }
        }
        findAllViewsByID();
        populateQuestions();
    }

    private void findAllViewsByID() {
        performanceViews.add(findViewById(R.id.question1));
        performanceViews.add(findViewById(R.id.question2));
        performanceViews.add(findViewById(R.id.question3));
        performanceViews.add(findViewById(R.id.question4));
        performanceViews.add(findViewById(R.id.question5));
        performanceViews.add(findViewById(R.id.question6));
        performanceViews.add(findViewById(R.id.question7));
        performanceViews.add(findViewById(R.id.question8));
        performanceViews.add(findViewById(R.id.question9));
        performanceViews.add(findViewById(R.id.question10));
    }

    private void populateQuestions() {

        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_performance);
        tw.setText("");
        tw.setCharacterDelay(100);
        if (questionSet.getNumCorrect() < 4) {
            tw.animateText("Oof...");
        } else if (questionSet.getNumCorrect() < 8) {
            tw.animateText("Nice job!");
        } else {
            tw.animateText("Outstanding!");
        }

        final TypeWriter tw2 = (TypeWriter) findViewById(R.id.typeWriter_percent);
        tw2.setText("");
        tw2.setCharacterDelay(400);
        tw2.animateText(questionSet.getNumCorrect() + " / 10");

        Log.d("Performance", "number of q's: " + questionSet.getNumberOfQuestions());

        for (int i = 0; i < 10; i++) {
            Question question = questionSet.getQuestion(i);
            if (question.isCorrect()) {
                performanceViews.get(i).setText("Correct\n\n" + question.getQuestion() + "\n\nYou Answered: " + question.getChosenAnswer());
            } else {
                performanceViews.get(i).setBackgroundColor(Color.parseColor("#E87066"));
                performanceViews.get(i).setText("Incorrect\n\n" + question.getQuestion() + "\n\nCorrect Answer: " + question.getAnswer() + "\nYou Answered: " + question.getChosenAnswer());
            }
        }
    }

    //here in case we want to do something in the future
    public void performDisplayClicked(View view) {
    }

    public void playAgainClicked(View view){
        sendDataToDB();
        Intent intent = new Intent(this, NameGameActivity.class);
        startActivity(intent);
    }

    public void mainMenuClicked(View view){
        sendDataToDB();
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    private void sendDataToDB(){
        if(!sendingData && !dataSent) {
            Retro retroInterface = Retro.retro.create(Retro.class);
            try {
                sendingData = true;
                JsonObject payload = new JsonObject();
                payload.addProperty("user", "TestUser");
                payload.addProperty("questionsAnswered", questionSet.getNumCorrect());
                Call<JsonObject> call = retroInterface.updateLeaderBoard(payload);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                        Log.d("Performance: ", "Success | " + response.message());
                        dataSent = true;
                        sendingData = false;
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("Performance", "Failed | " + t.toString());
                        sendingData = false;
                    }
                });
            } catch (Exception e) {

            }
        }
    }
}
