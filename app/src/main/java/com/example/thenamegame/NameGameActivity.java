package com.example.thenamegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This is a 10 question multiple choice
 * @author Ian
 */
public class NameGameActivity extends AppCompatActivity {

    TextView streakText;
    TextView questionText;
    List<TextView> answerViews = new ArrayList<>();
    QuestionHandler questionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_game);
        getSupportActionBar().hide();
        questionHandler = new QuestionHandler();

        findAllViewsByID();
        populateQuestionAndAnswers();
    }

    private void findAllViewsByID(){
        answerViews.add(findViewById(R.id.answerView1));
        answerViews.add(findViewById(R.id.answerView2));
        answerViews.add(findViewById(R.id.answerView3));
        answerViews.add(findViewById(R.id.answerView4));
        answerViews.add(findViewById(R.id.answerView5));
    }

    private void populateQuestionAndAnswers(){
        if(!questionHandler.getNewQuestion()){
            Intent intent = new Intent(this, LeaderBoardActivity.class);
            startActivity(intent);
        }

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_nameGame);
        tw.setText("");
        tw.setCharacterDelay(50);
        tw.animateText(questionHandler.getQuestion());

        List<String> allAnswers = questionHandler.getAllAnswers();
        Collections.shuffle(allAnswers);
        for(int i = 0; i < 5; i++){
            answerViews.get(i).setText(allAnswers.get(i));
        }
    }

    public void questionClicked(View view){
        try {
            String buttonText = ((Button) view).getText().toString();
            questionHandler.submit(buttonText);
            populateQuestionAndAnswers();
        }catch (Exception e){

        }
    }
}
