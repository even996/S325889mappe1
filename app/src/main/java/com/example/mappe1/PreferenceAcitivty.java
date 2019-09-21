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
        loadLanguage();
        setContentView(R.layout.preference_activity);
        prefReturnBtn = findViewById(R.id.returnBtn);
        imageButtonNor = findViewById(R.id.imageNor);
        imageButtonGer = findViewById(R.id.imageGer);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox1.setChecked(true);
        buttonsPressed();

    }

    public void buttonsPressed(){

        prefReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
        imageButtonNor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageNumber= 1 ;
                norwergian(view);
            }
        });
        imageButtonGer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageNumber=2;
                german(view);
            }
        });

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
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_NUMBER,checked);
        //intent.putExtra(EXTRA_LANGUAGE, language);
        startActivity(intent);
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



    public void german(View view){
        language="de";
        setLanguage(language);
        recreate();
    }

    public void norwergian(View view){
        language="no";
        setLanguage(language);
        recreate();
    }


}