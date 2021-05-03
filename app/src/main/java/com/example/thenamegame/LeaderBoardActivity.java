package com.example.thenamegame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

/**
 * this class displays the leaderboards
 */
public class LeaderBoardActivity extends AppCompatActivity {

    Retro interfaceName = Retro.retro.create(Retro.class);
    List<List<String>> topUsersAndScores;
    Asynch loadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        topUsersAndScores = new ArrayList<>();
        loadTask = new Asynch();
       // fillTopThree();
    }


    private void fillTopThree(){
        TextView first = findViewById(R.id.first);
        TextView second = findViewById(R.id.second);
        TextView third = findViewById(R.id.third);
        //System.out.println(topUsersAndScores.isEmpty() + "-------------------------------------------------------------------------");
        first.setText(topUsersAndScores.get(0).get(0));
        second.setText(topUsersAndScores.get(1).get(0));
        third.setText(topUsersAndScores.get(2).get(0));
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
                        fillTopThree();
                        makeViewsVis();
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
