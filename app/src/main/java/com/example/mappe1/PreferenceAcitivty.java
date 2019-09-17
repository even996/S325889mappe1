package com.example.mappe1;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PreferenceAcitivty extends AppCompatActivity {


    public static final String EXTRA_NUMBER = "com.example.mappe1";
    public int checked = 5;

    public Button prefReturnBtn;
    public ImageButton imageButtonNor, imageButtonGer;
    public CheckBox checkBox1, checkBox2, checkBox3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                norwergian(view);
            }
        });

        imageButtonGer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        startActivity(intent);
    }

    public void setLanguage(String country){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(country));
        res.updateConfiguration(cf,dm);
    }

    public void german(View view){
        setLanguage("de");
        recreate();
    }

    public void norwergian(View view){
        setLanguage("no");
        recreate();
    }


}