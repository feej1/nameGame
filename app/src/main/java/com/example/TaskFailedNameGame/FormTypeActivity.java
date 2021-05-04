package com.example.TaskFailedNameGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.RatingBar.OnRatingBarChangeListener;

/**
 * THis menu lets the user decide what to play challenge/nameGame/Stars
 * Author Patrick Sowada
 */
public class FormTypeActivity extends AppCompatActivity {
    //So for this class I think I want to have multiple forms as Ian had mentioned up above.
    //I think there should be 2 buttons (or a slider?) for which Form a user wants to do,
    //in addition this should let the user select how many stars, and if the nameGame is multiplayer

    //Beginning work on 5 stars

    boolean click = false;
    boolean form1buttonispressed = false;
    public float difficulty = 0;
    RatingBar starDifficulty;

    private Button form1button;
    private Button form2button;
    private Button singleplayerButton;
    private Button multiplayerButton;
    private Button launchGameButton;
    private ImageButton shuffleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_type);

        form1button = (Button) findViewById(R.id.form1button);
        form2button = (Button) findViewById(R.id.form2button);
        singleplayerButton = (Button) findViewById(R.id.singleplayerButton);
        multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
        launchGameButton = (Button) findViewById(R.id.launchGameButton);
        shuffleButton = (ImageButton) findViewById(R.id.shuffleDifficultyButton);

        shuffleButton.setColorFilter(Color.parseColor("#ffffff"));

        starDifficulty = findViewById(R.id.stars);
        getSupportActionBar().hide();

        setPlayButtonEnabled(false);

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_formType);
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("CONFIGURE MATCH!");

        starDifficulty.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar stars, float v, boolean fromUser) {
                difficulty = (int) v;
                difficulty = stars.getRating();

                shuffleButton.setColorFilter(Color.parseColor("#ffffff"));

                switch ((int) difficulty) {
                    case 0:
                        setPlayButtonEnabled(false);
                        difficulty = 1;
                    case 1:
                        difficulty = 1;
                        if (click) {
                            setPlayButtonEnabled(true);
                        }
                        break;
                    case 2:
                        difficulty = 2;
                        if (click) {
                            setPlayButtonEnabled(true);
                        }
                        break;
                    case 3:
                        difficulty = 3;
                        if (click) {
                            setPlayButtonEnabled(true);
                        }
                        break;
                    case 4:
                        difficulty = 4;
                        if (click) {
                            setPlayButtonEnabled(true);
                        }
                        break;
                    case 5:
                        difficulty = 5;
                        if (click) {
                            setPlayButtonEnabled(true);
                        }
                        break;
                }
            }
        });

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
        if (difficulty >= 1) {
            setPlayButtonEnabled(true);
        }
        click = true;
        form1buttonispressed = true;
    }

    public void setUpForm2(View view) {
        form1button.setClickable(true);
        form2button.setClickable(false);
        form1button.setBackgroundColor(Color.parseColor("#2196F3"));
        form2button.setBackgroundColor(Color.parseColor("#135a91"));
        if (difficulty >= 1) {
            setPlayButtonEnabled(true);
        }
        click = true;
        form1buttonispressed = false;
    }

    public void setUpMultiplayerButton(View view) {
        singleplayerButton.setClickable(true);
        multiplayerButton.setClickable(false);
        multiplayerButton.setBackgroundColor(Color.parseColor("#135a91"));
        singleplayerButton.setBackgroundColor(Color.parseColor("#2196F3"));

    }

    public void setUpSingleplayerButton(View view) {
        singleplayerButton.setClickable(false);
        multiplayerButton.setClickable(true);
        singleplayerButton.setBackgroundColor(Color.parseColor("#135a91"));
        multiplayerButton.setBackgroundColor(Color.parseColor("#2196F3"));
    }

    public void shuffleButtonPressed(View view) {
        starDifficulty.setRating(0);
        shuffleButton.setColorFilter(Color.parseColor("#04DAC5"));
        setPlayButtonEnabled(true);
    }
}