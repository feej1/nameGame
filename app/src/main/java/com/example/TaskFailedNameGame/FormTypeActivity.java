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
    //So for this class I think I want to have multiple forms as Ian had mentioned up above.
    //I think there should be 2 buttons (or a slider?) for which Form a user wants to do,
    //in addition this should let the user select how many stars, and if the nameGame is multiplayer

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
        launchGameButton = (Button) findViewById(R.id.launchGameButton);

        getSupportActionBar().hide();

        setPlayButtonEnabled(false);

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_formType);
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("CONFIGURE MATCH!");
    }

    public void setPlayButtonEnabled(boolean enabled) {
        launchGameButton.setEnabled(enabled);
        if (enabled)
            launchGameButton.setBackgroundColor(Color.parseColor("#4CAF50"));
        else
            launchGameButton.setBackgroundColor(Color.parseColor("#D8D8D8"));
    }

    public void playSetUp(View view) {
        if (form1buttonispressed) {
            Intent intent = new Intent(this, NameGameActivity.class);
            intent.putExtra("FormType", 1);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, NameGameActivity.class);
            intent.putExtra("FormType", 2);
            startActivity(intent);
        }
    }

    public void setUpForm1(View view) {
        form1button.setClickable(false);
        form2button.setClickable(true);
        form1button.setBackgroundColor(Color.parseColor("#135a91"));
        form2button.setBackgroundColor(Color.parseColor("#2196F3"));
        form1buttonispressed = true;
        setPlayButtonEnabled(true);
    }

    public void setUpForm2(View view) {
        form1button.setClickable(true);
        form2button.setClickable(false);
        form1button.setBackgroundColor(Color.parseColor("#2196F3"));
        form2button.setBackgroundColor(Color.parseColor("#135a91"));
        form1buttonispressed = false;
        setPlayButtonEnabled(true);
    }
}