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
import java.util.Random;


public class StartAcitivty  extends AppCompatActivity implements View.OnClickListener{


    public String answerSelected = "";
    public int points = 0;
    String [] questions;
    String [] answers;
    List<String> question_arraylist, answer_arraylist;
    public int numberOfQuestions;
    TextView score, question;
    int Question_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        question_arraylist = new ArrayList<>(Arrays.asList(questions));
        answer_arraylist = new ArrayList<>(Arrays.asList(answers));

        Intent intent = getIntent();
        numberOfQuestions = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 5);
        System.out.println("ttt" + numberOfQuestions);
        Question_ID = get_next_question(numberOfQuestions);
        question = findViewById(R.id.question);
        question.setText(question_arraylist.get(Question_ID));

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

        //Sett inn foreach løkke?
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
        //amountOfQuestions(numberOfQuestions, arraylist);

    }

    public void pointGain(){
        points++;
        score = findViewById(R.id.score);
        score.setText(String.valueOf(points));
        TextView pointTextView = findViewById(R.id.Points);
        pointTextView.setText(getString(R.string.current_points) + points);
    }




    public void answer(String button){
        TextView answerView = findViewById(R.id.answer);
        if(button.equals("clear_answer")){
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


    public void amountOfQuestions(int amount_of_questions, String[] question_array){
        System.out.println(""+question_array.length);



        /*if(questions==5){
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
        }*/
    }

    public int get_next_question(int number_of_questions){
        Random random_number_generator = new Random();
        return random_number_generator.nextInt(number_of_questions);
    }

    public void confirmBtnActive(){
        System.out.println(answerSelected + " og " + Question_ID + " og " + question_arraylist.get(Question_ID) + " og " + answer_arraylist.get(Question_ID));
        if(answerSelected.equals("")){
            answer(answerSelected);
        }
        else {
            if(answerSelected.equals(answer_arraylist.get(Question_ID))){
                pointGain();
                //question.setText(question_arraylist.get(Question_ID));
            }
            answerSelected="";
            answer(answerSelected);
        }
        Question_ID = get_next_question(numberOfQuestions);
        question.setText(question_arraylist.get(Question_ID));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button0:
                answer("0");
                break;
            case R.id.button1:
                answer("1");
                break;
            case R.id.button2:
                answer("2");
                break;
            case R.id.button3:
                answer("3");
                break;
            case R.id.button4:
                answer("4");
                break;
            case R.id.button5:
                answer("5");
                break;
            case R.id.button6:
                answer("6");
                break;
            case R.id.button7:
                answer("7");
                break;
            case R.id.button8:
                answer("8");
                break;
            case R.id.button9:
                answer("9");
                break;
            case R.id.buttonC:
                answer("clear_answer");
                break;
            case R.id.confirm:
               confirmBtnActive();
                break;
                default:
                    System.out.println("Something went horribly wrong");
                    break;

        }
    }
}




















