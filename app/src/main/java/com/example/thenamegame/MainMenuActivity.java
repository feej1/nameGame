package com.example.thenamegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Ian
 */
public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Main menu title type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_title);
        tw.setText("");
        tw.setCharacterDelay(400);
        tw.animateText("THE NAME GAME.");
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