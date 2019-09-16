package com.example.mappe1;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PreferenceAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_activity);
        Button prefReturnBtn = findViewById(R.id.returnBtn);
        ImageButton imageButtonNor = findViewById(R.id.imageNor);
        ImageButton imageButtonGer = findViewById(R.id.imageGer);


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

    }



    public void menu(){
        Intent intent = new Intent(this, MainActivity.class);
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