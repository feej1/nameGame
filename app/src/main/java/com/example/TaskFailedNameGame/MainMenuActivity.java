package com.example.TaskFailedNameGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.TaskFailedNameGame.Retro.RetroDataRunner;
import com.example.TaskFailedNameGame.Retro.RetroQuestionRunner;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Ian
 */
public class MainMenuActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;

    GoogleSignInClient mGoogleSignInClient;
    private static GoogleSignInAccount account;

    private TextView signInText;
    private boolean loggedIn;
    private Button googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
        account = null;

        signInText = (TextView) findViewById(R.id.googleSignInText);
        googleButton = (Button) findViewById(R.id.googleButton);

        // Type writer
        final TypeWriter tw = (TypeWriter) findViewById(R.id.typeWriter_mainMenu);
        tw.setText("");
        tw.setCharacterDelay(400);
        tw.animateText("THE NAME GAME.");

        // starts all retro runners
        RetroQuestionRunner.getOneNameInstance(0).start();
        RetroQuestionRunner.getOneYearInstance(0).start();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("484220769255-eqreisk19guhm0bjasg8ks2adcarscfn.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);

        // add a box that shows that we are signed in
        if (account == null) {
            signInText.setText("");
            googleButton.setText("Sign in with Google");
            googleButton.setBackgroundColor(Color.parseColor("#6BB86E"));
            loggedIn = false;
        } else {
            signInText.setText("Signed in as ??? " + account.getDisplayName());
            googleButton.setText("SIGN OUT");
            googleButton.setBackgroundColor(Color.parseColor("#E87066"));
            loggedIn = true;
        }
    }

    // Click event handler for the google login button
    public void googleOnclick(View v) {
        if (loggedIn)
            signOut();
        else {
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.d("googleSignIn", account.getDisplayName() + " logged in successfully");

            // Handle log in UI
            signInText.setText("Signed in as ??? " + account.getDisplayName());
            googleButton.setText("SIGN OUT");
            googleButton.setBackgroundColor(Color.parseColor("#E87066"));
            loggedIn = true;
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("googleSignIn", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public void switchToFormType(View view) {
        Intent intent = new Intent(this, FormTypeActivity.class);
        startActivity(intent);
    }


    public void switchToLeaderBoard(View view) {
        Intent intent = new Intent(this, LeaderBoardActivity.class);
        startActivity(intent);
    }

    public static String getDisplayName() {
        if (account != null) {
            return account.getDisplayName();
        }
        return null;
    }

    public void googleSignOut() {
        signOut();
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        Log.d("googleSignIn", "Log out successful");

                        // Handle log out UI
                        signInText.setText("");
                        googleButton.setText("Sign in with Google");
                        googleButton.setBackgroundColor(Color.parseColor("#6BB86E"));
                        loggedIn = false;
                    }
                });
    }

}