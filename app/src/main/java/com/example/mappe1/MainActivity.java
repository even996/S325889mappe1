package com.example.mappe1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.mappe1";
    public int checked;

    TextView menuTitle;
    Button startBtn, statisticsBtn, preferenceBtn;
    public String country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_main);
        menuTitle = findViewById(R.id.menu_title);


        Intent intent = getIntent();
        checked = intent.getIntExtra(PreferenceAcitivty.EXTRA_NUMBER, 5);

        System.out.println(checked);
        //country = intent.getStringExtra("language");
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

    public void setLanguage(String country){
        Locale locale = new Locale(country);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("language", MODE_PRIVATE).edit();
        editor.putString("lang", country);
        editor.apply();

    }

    public void loadLanguage(){
        SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
        String language = prefs.getString("lang", "");
        setLanguage(language);

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
                System.out.println(checked);
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
