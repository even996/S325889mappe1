package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button startBrn = findViewById(R.id.start_btn);
        Button statisticsBtn = findViewById(R.id.statistics_btn);
        Button preferenceBtn = findViewById(R.id.preference_btn);


        startBrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(this, StartAcitivty.class);
                nextIntent(1);

            }
        });

        statisticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(2);
            }
        });

        preferenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(3);
            }
        });
    }






    public void nextIntent(int i){

        switch (i){
            case 1:
                Intent intent = new Intent(this, StartAcitivty.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(this, StatisticsAcitivty.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this, PreferenceAcitivty.class);
                startActivity(intent3);

                default:
                    break;

        }

    }

}
