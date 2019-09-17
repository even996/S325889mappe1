package com.example.mappe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsAcitivty extends AppCompatActivity {

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

        if (getIntent().hasExtra("SCORE")) {
            Intent intent = getIntent();
            int score = intent.getExtras().getInt("SCORE");
            TextView t = findViewById(R.id.Result);
            t.setText(score + "");
        }

    }
    public void menu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}


