package com.example.mappe1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsAcitivty extends AppCompatActivity {

    int score;
    TextView t;
    TextView statisticsTitle;
    Button prefReturnBtn, deleteHighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("KJØRER ONCREATE");
        setContentView(R.layout.statistics_activity);
        prefReturnBtn = findViewById(R.id.returnBtn);
        deleteHighscore = findViewById(R.id.deleteHighscore);
        statisticsTitle = findViewById(R.id.statistics_title);
        prefReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });


        TextView t = findViewById(R.id.Result);
        if (t.getText().toString().length()<1)
            System.out.println(t.getText().toString().length());
            t.setText(String.valueOf(0));

        if (getIntent().hasExtra("SCORE")) {
            Intent intent = getIntent();
            score = intent.getExtras().getInt("SCORE");
            if (score > (Integer.parseInt(t.getText().toString())))
            t.setText(score + "");
        }


        if(savedInstanceState != null) {

            score=savedInstanceState.getInt("score");
            String title = savedInstanceState.getString("statisticsTitle");
            statisticsTitle = findViewById(R.id.statistics_title);
            System.out.println(savedInstanceState.getString("statisticsTitle"));
            statisticsTitle.setText(title);
            t.setText(String.valueOf(score));
            prefReturnBtn.setText(savedInstanceState.getString("returnBtn"));
            deleteHighscore.setText(savedInstanceState.getString("deleteHighScore"));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", score);
        outState.putString("statisticsTitle", statisticsTitle.getText().toString());
        outState.putString("returnBtn",prefReturnBtn.getText().toString());
        outState.putString("deleteHighScore",deleteHighscore.getText().toString());
    }



    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Valueof score: " + String.valueOf(score));
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString("HIGHSCORE",String.valueOf(score)).apply();
        System.out.println("KJØRER ONPAUSE!: " + getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("HIGHSCORE",String.valueOf("")));
    }

    @Override
    protected void onResume() {
        super.onResume();
        String sharedPreference = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("HIGHSCORE","");
        System.out.println("KJØRER ONRESUME!: " + sharedPreference + " Score: " + score);
        t = findViewById(R.id.Result);
        if (sharedPreference.length()> 0 && Integer.parseInt(sharedPreference) > score)
        score = Integer.parseInt(getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("HIGHSCORE",String.valueOf("")));
        System.out.println("Score2: " + score);
        t.setText(String.valueOf(score));
    }

    public void menu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createDialog(View view){
        System.out.println("NÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅ");
        final AlertDialog.Builder builder = new AlertDialog.Builder(StatisticsAcitivty.this);
        builder.setCancelable(true);
        builder.setTitle("Warning!");
        builder.setMessage("Are you sure you want to reset the highscore list?");

        builder.setPositiveButton("Yes, delete.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("BYTTER SCENE");
                delete_highscore();

            }
        });

        builder.setNegativeButton("No, cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public void delete_highscore(){
        score = 0;
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString("HIGHSCORE",String.valueOf(score)).apply();
        t.setText(String.valueOf(score));
    }

}


