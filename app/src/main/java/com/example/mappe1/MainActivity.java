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

    //Setter variabler vi trenger senere
    public static final String EXTRA_NUMBER = "com.example.mappe1";
    public int checked;
    TextView menuTitle;
    Button startBtn, statisticsBtn, preferenceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sjekker at riktig språk blir brukt
        loadLanguage();
        //Setter layout til å være activity_main.xml
        setContentView(R.layout.activity_main);
        menuTitle = findViewById(R.id.menu_title);
        Intent intent = getIntent();
        //Henter antall spørsmål brukeren har valgt, hvis de ikke finnes er 5 standard
        checked = intent.getIntExtra(PreferenceAcitivty.EXTRA_NUMBER, 5);
        startBtn = findViewById(R.id.start_btn);
        statisticsBtn = findViewById(R.id.statistics_btn);
        preferenceBtn = findViewById(R.id.preference_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hvis "start spill" blir trykket på, skifter vi aktivitet til activity_main.xml
                //Det samme gjelder for statistikk og preferanser
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
            //Sjekker om vi har lagret noen variabler, hvis vi har det, henter vi dem og setter dem
            //(Variablene lagres bare når man snur tlf), så dette er for å bevare tilstanden
            menuTitle.setText(savedInstanceState.getString("menuTitle"));
            startBtn.setText(savedInstanceState.getString("startBtn"));
            statisticsBtn.setText(savedInstanceState.getString("staticsBtn"));
            preferenceBtn.setText(savedInstanceState.getString("preferenceBtn"));
        }
    }

    public void setLanguage(String country){
        //Setter språk
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
        //Sjekker at riktig språk blir brukt, tilkalles i onCreate slik at alle strings blir oppdatert
        SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
        String language = prefs.getString("lang", "");
        setLanguage(language);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Lagrer alle verdiene vi trenger for å bevare tilstanden, når tlf snus
        outState.putString("menuTitle", menuTitle.getText().toString());
        outState.putString("startBtn", startBtn.getText().toString());
        outState.putString("staticsBtn", statisticsBtn.getText().toString());
        outState.putString("preferenceBtn", preferenceBtn.getText().toString());
    }

    public void nextIntent(int i){
        //Metode som tar inn en int for å bytte til riktig aktivitet
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
