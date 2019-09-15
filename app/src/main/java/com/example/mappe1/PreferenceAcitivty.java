package com.example.mappe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PreferenceAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_activity);


        Button prefReturnBtn = findViewById(R.id.returnBtn);


       prefReturnBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               menu();
           }
       });


    }


    public void menu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}