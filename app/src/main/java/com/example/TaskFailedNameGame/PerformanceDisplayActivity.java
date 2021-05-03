package com.example.TaskFailedNameGame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PerformanceDisplayActivity extends AppCompatActivity {

    private List<Button> performanceViews = new ArrayList<>();
    QuestionSet questionSet;
    int numberCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_display);
        getSupportActionBar().hide();

        Intent intent = getIntent();

        questionSet = (QuestionSet) intent.getSerializableExtra("questionSet");

        if(questionSet == null){
            questionSet = new QuestionSet();
            for(int i = 0; i < 10; i++){
                questionSet.add(new Question("Question Not Found", "", "", "", "", ""));
            }
        }
        findAllViewsByID();
        populateQuestions();
    }

    private void findAllViewsByID(){
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

    private void populateQuestions(){

        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_performance);
        tw.setText("");
        tw.setCharacterDelay(50);
        if(questionSet.getNumCorrect() < 4){
            tw.animateText("You Can Do It!");
        } else if(questionSet.getNumCorrect() < 8){
            tw.animateText("Good Job!");
        }else {
            tw.animateText("OUTSTANDING WORK!");
        }

        final TypeWriter tw2 = (TypeWriter) findViewById(R.id.typeWriter_percent);
        tw2.setText("");
        tw2.setCharacterDelay(100);
        tw2.animateText( questionSet.getNumCorrect() + "\n-\n10");

        Log.d("Performance", "number of q's: " + questionSet.getNumberOfQuestions());

        for(int i = 0; i < 10; i++){
            Question question = questionSet.getQuestion(i);
            if(question.isCorrect()){
                performanceViews.get(i).setText("Correct!\n" + question.getQuestion() + "\n\nYou Answered: " + question.getChosenAnswer());
            }else{
                performanceViews.get(i).setBackgroundColor(Color.RED);
                performanceViews.get(i).setText("Incorrect\n" + question.getQuestion() + "\n\nCorrect Answer Was: " + question.getAnswer() + "\nYou Answered: " + question.getChosenAnswer());
            }
        }
    }

    //here in case we want to do something in the future
    public void performDisplayClicked(View view) {
    }

    public void playAgainClicked(View view){
        Intent intent = new Intent(this, FormTypeActivity.class);
        startActivity(intent);
    }

    public void mainMenuClicked(View view){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
