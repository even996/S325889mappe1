package com.example.mappe1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.mappe1";
    public int checked;

    TextView menuTitle;
    Button startBtn, statisticsBtn, preferenceBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuTitle = findViewById(R.id.menu_title);


        System.out.println("TEST!");


        System.out.println((getIntent().hasExtra(EXTRA_NUMBER)+ "test123"));
        Intent intent = getIntent();
        checked = intent.getIntExtra(PreferenceAcitivty.EXTRA_NUMBER, 5);
        startBtn = findViewById(R.id.start_btn);
        statisticsBtn = findViewById(R.id.statistics_btn);
        preferenceBtn = findViewById(R.id.preference_btn);






        startBtn.setOnClickListener(new View.OnClickListener() {
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

        if(savedInstanceState != null){
            System.out.println("kjører gjennom");
            menuTitle.setText(savedInstanceState.getString("menuTitle"));
            startBtn.setText(savedInstanceState.getString("startBtn"));
            statisticsBtn.setText(savedInstanceState.getString("staticsBtn"));
            preferenceBtn.setText(savedInstanceState.getString("preferenceBtn"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("menuTitle", menuTitle.getText().toString());
        outState.putString("startBtn", startBtn.getText().toString());
        outState.putString("staticsBtn", statisticsBtn.getText().toString());
        outState.putString("preferenceBtn", preferenceBtn.getText().toString());
    }

    public void nextIntent(int i){
        switch (i){
            case 1:
                Intent intent = new Intent(this, StartAcitivty.class);
                intent.putExtra(EXTRA_NUMBER, checked);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(this, StatisticsAcitivty.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this, PreferenceAcitivty.class);
                startActivity(intent3);
                break;
                default:
                    break;

        }

    }

}
