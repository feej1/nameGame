package com.example.TaskFailedNameGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Button;

/**
 * THis menu lets the user decide what to play challenge/nameGame/Stars
 * Author Patrick Sowada
 */
public class FormTypeActivity extends AppCompatActivity {

    boolean click = false;
    boolean form1buttonispressed = false;

    private Button form1button;
    private Button form2button;
    private Button launchGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_type);

        form1button = (Button) findViewById(R.id.form1button);
        form2button = (Button) findViewById(R.id.form2button);

        getSupportActionBar().hide();
        // Type writer



    }
}