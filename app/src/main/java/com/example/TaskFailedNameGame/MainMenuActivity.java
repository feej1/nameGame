package com.example.TaskFailedNameGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.TaskFailedNameGame.Retro.RetroDataRunner;
import com.example.TaskFailedNameGame.Retro.RetroQuestionRunner;

/**
 * @author Ian
 */
public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_mainMenu);
        tw.setText("");
        tw.setCharacterDelay(400);
        tw.animateText("THE NAME GAME.");

        // starts all retro runners
        RetroQuestionRunner.getOneNameInstance().start();
        RetroQuestionRunner.getOneYearInstance().start();
    }


    public void switchToFormType(View view){
        Intent intent = new Intent(this, FormTypeActivity.class);
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