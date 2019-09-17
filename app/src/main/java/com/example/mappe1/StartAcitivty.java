package com.example.mappe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class StartAcitivty  extends AppCompatActivity implements View.OnClickListener{


    public String answerSelected = "";
    public int points = 0;
    String [] questions;
    List<String> arraylist;
    public int numberOfQuestions;
    TextView score, question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        questions = getResources().getStringArray(R.array.my_array);
        List<String> arraylist = new ArrayList<>(Arrays.asList(questions));

        Intent intent = getIntent();
        numberOfQuestions = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);
        if(numberOfQuestions == 0){
            numberOfQuestions=5;
        }
        System.out.println("" + numberOfQuestions);


        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        Button btnC = findViewById(R.id.buttonC);

        Button confirmBtn = findViewById(R.id.confirm);
        question = findViewById(R.id.question);
        Collections.shuffle(arraylist);
        question.setText(arraylist.get(0));

        //Sett inn foreach løkke
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnC.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        amountOfQuestions(numberOfQuestions, arraylist);

    }

    public void pointGain(){
        points++;
        score = findViewById(R.id.score);
        score.setText(String.valueOf(points));
    }




    public void answer(String button){
        TextView answerView = findViewById(R.id.answer);
        if(button.equals("")){
            answerSelected="";
        }
        else {
            answerSelected += button;
        }
        if(answerSelected.length()>3){
            answerSelected="";
        }
        answerView.setText(answerSelected);
    }


    public void amountOfQuestions(int questions, List<String> list ){
        System.out.println(""+list.size());
        if(questions==5){
            if (list.size() > 5) {
                System.out.println(list.toString());
                list.subList(5, list.size()).clear();
                System.out.println(list.toString());
            }
        }
        System.out.println(""+list.size());
        if(questions==10){
            for (int i = list.size()-1; i>=10 ; i--) {
                list.remove(i);
            }
        }
    }

    public void confirmBtnActive(){

        if(answerSelected.equals("")){
            answer(answerSelected);
        }
        else {
            int answer = Integer.parseInt(answerSelected);
            if(answer==10){
                pointGain();
            }
            answerSelected="";
            answer(answerSelected);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button0:
                answer("0");
                Toast.makeText(this,"Button 0  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button1:
                answer("1");
                Toast.makeText(this,"Button 1  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                answer("2");
                Toast.makeText(this,"Button 2  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                answer("3");
                Toast.makeText(this,"Button 3  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                answer("4");
                Toast.makeText(this,"Button 4  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                answer("5");
                Toast.makeText(this,"Button 5  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button6:
                answer("6");
                Toast.makeText(this,"Button 6  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button7:
                answer("7");
                Toast.makeText(this,"Button 7  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button8:
                answer("8");
                Toast.makeText(this,"Button 8  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button9:
                answer("9");
                Toast.makeText(this,"Button 9  clikced", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonC:
                Toast.makeText(this,"Button C  clikced", Toast.LENGTH_SHORT).show();
                answer("");
                break;
            case R.id.confirm:
               confirmBtnActive();
                break;
                default:
                    System.out.println("Javel");
                    break;

        }
    }
}




















