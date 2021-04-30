package com.example.TaskFailedNameGame;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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
    boolean clicked = false;
    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_type);
        RatingBar starDifficulty = findViewById(R.id.stars);
        getSupportActionBar().hide();

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_formType);
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("CONFIGURE MATCH!");

        starDifficulty.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                int difficulty = (int) v;

                switch (difficulty) {
                    case 1:
                        difficulty = 1;
                        break;
                    case 2:
                        difficulty = 2;
                        break;
                    case 3:
                        difficulty = 3;
                        break;
                    case 4:
                        difficulty = 4;
                        break;
                    case 5:
                        difficulty = 5;
                        break;
                }
            }
        });

    }

    public void playSetUp(View view) {
        Button form1button = (Button) findViewById(R.id.form1button);
        Button form2button = (Button) findViewById(R.id.form2button);
        Button multiplayerButton = (Button) findViewById(R.id.multiplayerButton);

        if (form1button.isPressed() == true) {
            //pass on Difficulty 1-5
        } else if (form2button.isPressed() == true) {
            //pass on Difficulty 1-5
        } else {
            //mixedformbutton.isPressed() == true
            //and therefore should prep mixed form 1 and 2 questions
            //pass on Difficulty 1-5
        }

        if (multiplayerButton.isPressed() == true) {
            Intent intent = new Intent(this, ChallengeActivity.class);
            startActivity(intent);
            //pass on Difficulty 1-5
        } else {
            Intent intent = new Intent(this, NameGameActivity.class);
            if (form1button.isPressed()){
                intent.putExtra("FormType1", true);
            }
            else if(form2button.isPressed()){
                intent.putExtra("FormType1", false);
            }
            startActivity(intent);
            //pass on Difficulty 1-5

        }
    }

    public void setUpForm1(View view) {
        Button form1button = (Button) findViewById(R.id.form1button);
        Button form2button = (Button) findViewById(R.id.form2button);
        Button mixformbutton = (Button) findViewById(R.id.mixformbutton);
        form1button.setClickable(false);
        form2button.setClickable(true);
        mixformbutton.setClickable(true);
        form1button.setBackgroundColor(Color.parseColor("#135a91"));
        form2button.setBackgroundColor(Color.parseColor("#2196F3"));
        mixformbutton.setBackgroundColor(Color.parseColor("#2196F3"));
    }

    public void setUpForm2(View view) {
        Button form1button = (Button) findViewById(R.id.form1button);
        Button form2button = (Button) findViewById(R.id.form2button);
        Button mixformbutton = (Button) findViewById(R.id.mixformbutton);
        form1button.setClickable(true);
        form2button.setClickable(false);
        mixformbutton.setClickable(true);
        form1button.setBackgroundColor(Color.parseColor("#2196F3"));
        form2button.setBackgroundColor(Color.parseColor("#135a91"));
        mixformbutton.setBackgroundColor(Color.parseColor("#2196F3"));
    }

    public void setUpMixedForm(View view) {
        Button form1button = (Button) findViewById(R.id.form1button);
        Button form2button = (Button) findViewById(R.id.form2button);
        Button mixformbutton = (Button) findViewById(R.id.mixformbutton);
        form1button.setClickable(true);
        form2button.setClickable(true);
        mixformbutton.setClickable(false);
        form1button.setBackgroundColor(Color.parseColor("#2196F3"));
        form2button.setBackgroundColor(Color.parseColor("#2196F3"));
        mixformbutton.setBackgroundColor(Color.parseColor("#135a91"));
    }

    public void setUpMultiplayerButton(View view) {
        Button singleplayerButton = (Button) findViewById(R.id.singleplayerButton);
        Button multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
        singleplayerButton.setClickable(true);
        multiplayerButton.setClickable(false);
        multiplayerButton.setBackgroundColor(Color.parseColor("#135a91"));
        singleplayerButton.setBackgroundColor(Color.parseColor("#2196F3"));

    }

    public void setUpSingleplayerButton(View view) {
        Button singleplayerButton = (Button) findViewById(R.id.singleplayerButton);
        Button multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
        singleplayerButton.setClickable(false);
        multiplayerButton.setClickable(true);
        singleplayerButton.setBackgroundColor(Color.parseColor("#135a91"));
        multiplayerButton.setBackgroundColor(Color.parseColor("#2196F3"));
    }
}