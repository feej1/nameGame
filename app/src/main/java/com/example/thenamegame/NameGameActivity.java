package com.example.thenamegame;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a 10 question multiple choice
 * @author Ian
 */
public class NameGameActivity extends AppCompatActivity {

    TextView streakText;
    TextView questionText;
    List<TextView> answerViews = new ArrayList<>();
    QuestionHandler questionHandler = new QuestionHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_game);

        streakText = findViewById(R.id.streakView);
        questionText = findViewById(R.id.questionView);
        answerViews.add(findViewById(R.id.answerView1));
        answerViews.add(findViewById(R.id.answerView2));
        answerViews.add(findViewById(R.id.answerView3));
        answerViews.add(findViewById(R.id.answerView4));
        answerViews.add(findViewById(R.id.answerView5));
    }

    private void populateQuestionAndAnswers(){
        questionText.setText(questionHandler.getQuestion());

        List<String> allAnswers = questionHandler.getAllAnswers();
        for(int i = 0; i < 5; i++){ //dont really want to hard code how many there are...

            answerViews.get(i).setText("");
        }
    }
}
