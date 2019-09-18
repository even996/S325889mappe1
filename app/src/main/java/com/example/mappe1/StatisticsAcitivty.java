package com.example.mappe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsAcitivty extends AppCompatActivity {
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        Button prefReturnBtn = findViewById(R.id.returnBtn);
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

    }

    @Override
    protected void onPause() {
        super.onPause();
        TextView t = findViewById(R.id.Result);
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString("HIGHSCORE",String.valueOf(score));
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView t = findViewById(R.id.Result);
        //if (getSharedPreferences("REFERENCE",MODE_PRIVATE).getString("HIGHSCORE","").length()>0)
        t.setText(getSharedPreferences("REFERENCE",MODE_PRIVATE).getString("HIGHSCORE",String.valueOf("")));
    }

    public void menu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}


