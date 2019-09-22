package com.example.mappe1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PreferenceAcitivty extends AppCompatActivity {

    //Setter variabler vi trenger senere
    public static final String EXTRA_NUMBER = "com.example.mappe1";
    public static final String EXTRA_LANGUAGE = "com.example.mappe1";
    public int checked = 5;

    public Button prefReturnBtn;
    public ImageButton imageButtonNor, imageButtonGer;
    public CheckBox checkBox1, checkBox2, checkBox3;
    public int languageNumber;
    public String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sørger for å laste inn riktig språk
        loadLanguage();
        //Setter contentViewet til å være preference_activity.xml
        setContentView(R.layout.preference_activity);
        //Finner knapper o.l ved å finne IDen dems i res folder
        prefReturnBtn = findViewById(R.id.returnBtn);
        imageButtonNor = findViewById(R.id.imageNor);
        imageButtonGer = findViewById(R.id.imageGer);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        //Sier at i utgangspunktet så er 5 spørsmål valgt
        checkBox1.setChecked(true);
        //Tilkaller metoden som setter clickListener på alle knappene
        // (Kunne brukt onClick i XML slik vi har gjort alle andre steder,
        // men ville vise vi hadde kompetanse til å gjøre det uten.
        buttonsPressed();
    }

    public void buttonsPressed(){
        //Setter onClickListener på alle knappene, slik at de gjør noe dersom de blir trykket på
        prefReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Skifter view til hovedmenyen
                menu();
            }
        });
        imageButtonNor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setter språket til norsk
                languageNumber= 1 ;
                norwergian(view);
            }
        });
        imageButtonGer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setter språket til tysk
                languageNumber=2;
                german(view);
            }
        });

        //Alle de knappene under velger bare riktig antall spørsmål.
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(true);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checked=5;
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(false);
                checkBox2.setChecked(true);
                checkBox3.setChecked(false);
                checked=10;
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(true);
                checked = 25;
            }
        });
    }

    public void menu(){
        //Bytter aktivitet til hovedmeny
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_NUMBER,checked);
        startActivity(intent);
    }



    public void setLanguage(String country){
        //Setter riktig språk
        Locale locale = new Locale(country);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("language", MODE_PRIVATE).edit();
        editor.putString("lang", country);
        editor.apply();

    }

    public void loadLanguage(){
        //Sjekker at riktig språk blir brukt (kalles i onCreate for å alltid oppdatere riktig strings)
        SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
        String language = prefs.getString("lang", "");
        setLanguage(language);

    }

    public void german(View view){
        //Setter språket til tysk
        language="de";
        setLanguage(language);
        recreate();
    }

    public void norwergian(View view){
        //Setter språket til norsk
        language="no";
        setLanguage(language);
        recreate();
    }


}