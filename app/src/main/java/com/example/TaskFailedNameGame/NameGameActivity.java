package com.example.TaskFailedNameGame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TaskFailedNameGame.Retro.RetroQuestionRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a 10 question multiple choice
 *
 * @author Ian
 */
public class NameGameActivity extends AppCompatActivity {

    List<TextView> answerViews = new ArrayList<>();
    QuestionHandler questionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_game);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        Log.d("NameGame", "FormType is " + intent.getIntExtra("FormType", 3));
        int difficulty = intent.getIntExtra("Difficulty", 0);
        switch (intent.getIntExtra("FormType", 3)) {
            case 1:
                questionHandler = new QuestionHandler(RetroQuestionRunner.getOneNameInstance(difficulty));
                break;
            case 2:
                questionHandler = new QuestionHandler(RetroQuestionRunner.getOneYearInstance(difficulty));
                break;
            default:
                questionHandler = new QuestionHandler(RetroQuestionRunner.getOneNameInstance(difficulty));
                break;
        }

        findAllViewsByID();
        populateQuestionAndAnswers();
    }

    private void findAllViewsByID() {
        answerViews.add(findViewById(R.id.answerView1));
        answerViews.add(findViewById(R.id.answerView2));
        answerViews.add(findViewById(R.id.answerView3));
        answerViews.add(findViewById(R.id.answerView4));
        answerViews.add(findViewById(R.id.answerView5));
    }

    private void populateQuestionAndAnswers() {
        if (!questionHandler.getNewQuestion()) {
            Log.d("NameGame", "DONE numeber of questions: " + questionHandler.getQuestionNumber());
            Intent intent = new Intent(this, PerformanceDisplayActivity.class);
            intent.putExtra("questionSet", questionHandler.getQuestionSet());
            startActivity(intent);
        } else {

            // Type writer
            final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_performance);
            tw.setText("");
            tw.setCharacterDelay(50);
            tw.animateText(questionHandler.getQuestion());

            List<String> allAnswers = questionHandler.getAllAnswers();
            Collections.shuffle(allAnswers);
            for (int i = 0; i < allAnswers.size(); i++) {
                answerViews.get(i).setText(allAnswers.get(i));
            }
        }
    }

    public void questionClicked(View view) {
        String buttonText = ((Button) view).getText().toString();
        if (questionHandler.submit(buttonText)) {
            showAlertDialogWithAutoDismiss("Correct!");
        } else {
            showAlertDialogWithAutoDismiss("Wrong answer!");
        }
    }

    public void showAlertDialogWithAutoDismiss(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(NameGameActivity.this);
        builder
                .setMessage(message)
                .setCancelable(false).setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        TextView messageView = (TextView) alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
        messageView.setTextColor(Color.parseColor("#757575"));
        messageView.setTextSize(30);
        messageView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        new Handler().postDelayed(() -> {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
                populateQuestionAndAnswers();
            }
        }, 2000);
    }
}
