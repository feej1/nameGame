package com.example.TaskFailedNameGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.TaskFailedNameGame.Retro.RetroDataRunner;
import com.example.TaskFailedNameGame.Retro.RetroQuestionRunner;

/**
 * @author Ian
 */
public class MainMenuActivity extends AppCompatActivity {

    boolean click = false;
    boolean form1buttonispressed = false;

    private Button form1button;
    private Button form2button;
    private Button launchGameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();

    //   form1button = (Button) findViewById(R.id.form1button);
    //   form2button = (Button) findViewById(R.id.form2button);

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_mainMenu);
        tw.setText("");
        tw.setCharacterDelay(400);
        tw.animateText("THE NAME GAME.");

        // starts all retro runners
        RetroQuestionRunner.getOneNameInstance(0).start();
        RetroQuestionRunner.getOneYearInstance(0).start();
    }


    public void setUpForm1(View view) {
        Intent intent = new Intent(this, NameGameActivity.class);
        intent.putExtra("FormType", 1);
        startActivity(intent);
    }

    public void setUpForm2(View view) {
        Intent intent = new Intent(this, NameGameActivity.class);
        intent.putExtra("FormType", 2);
        startActivity(intent);
    }

    public void switchToLeaderBoard(View view){
        Intent intent = new Intent(this, LeaderBoardActivity.class);
        startActivity(intent);
    }


    public void switchToGoogleLogin(View view){
        Intent intent = new Intent(this, GoogleLoginActivity.class);
        startActivity(intent);
    }
}