package com.example.TaskFailedNameGame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TaskFailedNameGame.Retro.Retro;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


/**
 * this class displays the leaderboards
 */
public class LeaderBoardActivity extends AppCompatActivity {

    Retro interfaceName = Retro.retro.create(Retro.class);
    List<List<String>> topUsersAndScores;
    Asynch loadTask;
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        topUsersAndScores = new ArrayList<>();
        loadTask = new Asynch();

    }


    private void makeTextviews (){
        for (int i = 3; i < topUsersAndScores.size(); i++){
            final String usr = topUsersAndScores.get(i).get(0);
            final String num = topUsersAndScores.get(i).get(1);
            final TextView view = new TextView(LeaderBoardActivity.this);
            view.setText(usr + " : " + num );
            //newBut.setBackgroundColor(ContextCompat.getColor(context, R.color.colorMaroon));
            view.setLayoutParams(layoutParams);
            //view.setTextColor(Color.parseColor("#ffffff")); //set text white
            view.setPadding(35 , 35, 35, 35);
            view.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            view.setAllCaps(false);
        }
    }

    private void fillTopThree(){
        //System.out.println("---------------------------------size ---------------     : " + topUsersAndScores.size());
        TextView first = findViewById(R.id.first);
        TextView second = findViewById(R.id.second);
        TextView third = findViewById(R.id.third);
        if(topUsersAndScores.size()>3){
             makeTextviews();
        }
        else if (topUsersAndScores.size()==3) {
            first.setText(topUsersAndScores.get(0).get(0));
            second.setText(topUsersAndScores.get(1).get(0));
            third.setText(topUsersAndScores.get(2).get(0));
        }
        else if (topUsersAndScores.size()==2){
            first.setText(topUsersAndScores.get(0).get(0));
            second.setText(topUsersAndScores.get(1).get(0));
            findViewById(R.id.third).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
        }
        else if (topUsersAndScores.size()==1){
            first.setText(topUsersAndScores.get(0).get(0));
            findViewById(R.id.second).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView9).setVisibility(View.INVISIBLE);
            findViewById(R.id.third).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
        }
        else{
            findViewById(R.id.first).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView).setVisibility(View.INVISIBLE);
            findViewById(R.id.second).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView9).setVisibility(View.INVISIBLE);
            findViewById(R.id.third).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView3).setVisibility(View.VISIBLE);
        }
    }


    private void makeViewsVis(){
        findViewById(R.id.second).setVisibility(View.VISIBLE);
        findViewById(R.id.third).setVisibility(View.VISIBLE);
        findViewById(R.id.textView8).setVisibility(View.VISIBLE);
        findViewById(R.id.textView).setVisibility(View.VISIBLE);
        findViewById(R.id.textView9).setVisibility(View.VISIBLE);
        findViewById(R.id.first).setVisibility(View.VISIBLE);
        findViewById(R.id.imageView).setVisibility(View.VISIBLE);
        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
    }

    private void makeViewsInv(){
        findViewById(R.id.second).setVisibility(View.INVISIBLE);
        findViewById(R.id.third).setVisibility(View.INVISIBLE);
        findViewById(R.id.textView8).setVisibility(View.INVISIBLE);
        findViewById(R.id.textView).setVisibility(View.INVISIBLE);
        findViewById(R.id.textView9).setVisibility(View.INVISIBLE);
        findViewById(R.id.first).setVisibility(View.INVISIBLE);
        findViewById(R.id.imageView).setVisibility(View.INVISIBLE);
    }



    private class Asynch{

        class MyRunnable implements Runnable {

            public MyRunnable() {
            }
            @Override
            public void run() {
                Call<JsonArray> call = interfaceName.getLeading();
                call.enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        if (response.body()!=null) {
                            Log.d("GETALL", response.body().toString());
                            //System.out.println(response.body().toString());
                            filltopList(response.body());
                        }
                        makeViewsVis();
                        fillTopThree();
                    } //ends overiddden method
                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.d("GETALL", "FAILED     " + t.toString());
                    }
                });   //end enqeue

            }

            private void filltopList(JsonArray array){
                int i =0 ;
                for (JsonElement e: array){
                    List<String> userAndcorrNum = new ArrayList<>();
                    String user =e.getAsJsonObject().get("user").getAsString();
                    String numberCorrect = e.getAsJsonObject().get("questionsAnswered").getAsString();
                    userAndcorrNum.add(user);
                    userAndcorrNum.add(numberCorrect);
                    topUsersAndScores.add(userAndcorrNum);
                }
            }
        }

        MyRunnable runnable = new MyRunnable();

        public Asynch(){

            makeViewsInv();
            findViewById(R.id.second).setVisibility(View.INVISIBLE);

            //FutureTask<String>
                   // futureTask1 = new FutureTask<>(runnable,"FutureTask1 is complete");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            // submit futureTask1 to ExecutorService
            //executor.submit(futureTask1);
            executor.execute(runnable);
            executor.shutdown();
        }


    }


}
