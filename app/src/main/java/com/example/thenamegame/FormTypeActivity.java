package com.example.thenamegame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Button;
import com.google.android.material.chip.Chip;


/**
 * THis menu lets the user decide what to play challenge/nameGame/Stars
 */
public class FormTypeActivity extends AppCompatActivity {
    //So for this class I think I want to have multiple forms as Ian had mentioned up above.
    //I think there should be 2 buttons (or a slider?) for which Form a user wants to do,
    //in addition this should let the user select how many stars, and if the nameGame is multiplayer

    RatingBar starDifficulty;
    Button form1button = (Button) findViewById(R.id.form1button);
    Button form2button = (Button) findViewById(R.id.form2button);
    Button mixformbutton = (Button) findViewById(R.id.mixformbutton);
    Button multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
    Button singleplayerButton = (Button) findViewById(R.id.singleplayerButton);
    Button launchGameButton = (Button) findViewById(R.id.launchGameButton);

    //Beginning work on 5 stars
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_type);

        starDifficulty = findViewById(R.id.stars);

        //starDifficulty.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
        //    @Override
        //    public void onStarsChanged(RatingBar stars, float v, boolean b) {
        //        int difficulty = (int) v;
        //        switch(difficulty){

        //            case 1:
        //               // starFormDifficulty1;
        //                break;
        //            case 2:
        //               // starFormDifficulty2;
        //                // two stars is achieved by getting names from 1951-1990
        //                //OR if the names used are outside the top 20 for the years in question.
        //                break;
        //            case 3:
        //              //  starFormDifficulty3;
        //                //three stars are achieved by using names from 1951-1990 AND they are outside
        //                //of the top 20
        //                //OR they are either a name from 1880-1950
        //                //OR if they are names used outside of the top 100 for the year in question
        //                break;
        //            case 4:
        //             //   starFormDifficulty4;

        //                break;
        //            case 5:
        //              //  starFormDifficulty5;
        //                break;
        //        }
        //    }
        //});

//   form1button.setOnClickListener(new View.OnClickListener(){
//           @Override
//           public void onClick(View view){
//               form1button.setClickable(false);
//           }
//       }
//
//       form2button.setOnClickListener(new View.OnClickListener(){
//           @Override
//           public void onClick(View view){
//               form2button.setClickable(false);
//           }
//       }
//   mixformbutton.setOnClickListener(new View.OnClickListener(){
//       @Override
//       public void onClick(View view){
//           mixformbutton.setClickable(false);
//       }
//   }
//   multiplayerButton.setOnClickListener(new View.OnClickListener(){
//       @Override
//       public void onClick(View view){
//           form2button.setClickable(false);
//       }
//   }
//   singleplayerButton.setOnClickListener(new View.OnClickListener(){
//       @Override
//       public void onClick(View view){
//           form2button.setClickable(false);
//       }
//   }
//
//   @Override
//   public void launchGameListener(View view){
//       launchGameButton.setOnClickListener(new View.OnClickListener();
//       Intent intent = new Intent (this, NameGameActivity.class);
//       startActivity(intent);
//   }
    }
}
