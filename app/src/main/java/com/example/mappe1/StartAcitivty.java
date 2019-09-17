package com.example.mappe1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class StartAcitivty  extends AppCompatActivity implements View.OnClickListener{


    public String answerSelected = "";
    public int points = 0;
    String [] questions;
    String [] answers;
    List<String> question_arraylist, answer_arraylist;
    public int number_of_maximum_questions_selected;
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
        number_of_maximum_questions_selected = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 5);
        System.out.println("ttt" + number_of_maximum_questions_selected);
        question = findViewById(R.id.question);
        new_question();
        find_all_views_by_id_and_set_on_click_listener();

        //Sett inn foreach løkke?
        //amountOfQuestions(number_of_maximum_questions_selected, arraylist);

    }

    protected  void find_all_views_by_id_and_set_on_click_listener(){
        Button confirmBtn = findViewById(R.id.confirm);
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
    }

    public void pointGain(){
        points++;
        score = findViewById(R.id.score);
        score.setText(String.valueOf(points));
        TextView pointTextView = findViewById(R.id.Points);
        pointTextView.setText(getString(R.string.current_points) + points);
    }




    public void append_answer(String button){
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

    public int generate_new_question_ID(int number_of_questions){
            Random random_number_generator = new Random();
            return random_number_generator.nextInt(number_of_questions);
    }

    public void confirm_answer(){
        /*if(answerSelected.equals("")){
            append_answer(answerSelected);
        }*/
        if(correct_answer())
            pointGain();
        append_answer("clear_answer");
        if (number_of_maximum_questions_selected > 1)
        next_question();
        else
            System.out.println("YOU WIN");
    }

    public boolean correct_answer(){
        return answerSelected.equals(answer_arraylist.get(Question_ID));
    }

    public void next_question(){
        question_arraylist.remove(Question_ID);
        answer_arraylist.remove(Question_ID);
        number_of_maximum_questions_selected--;
        if (number_of_maximum_questions_selected>1)
            Question_ID = generate_new_question_ID(number_of_maximum_questions_selected);
        else
            Question_ID = 0;
        System.out.println("QID " + Question_ID + "Maxiumum: " + number_of_maximum_questions_selected);
        question.setText(question_arraylist.get(Question_ID));
    }

    public void new_question(){
        Question_ID = generate_new_question_ID(number_of_maximum_questions_selected);
        question.setText(question_arraylist.get(Question_ID));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button0:
                append_answer("0");
                break;
            case R.id.button1:
                append_answer("1");
                break;
            case R.id.button2:
                append_answer("2");
                break;
            case R.id.button3:
                append_answer("3");
                break;
            case R.id.button4:
                append_answer("4");
                break;
            case R.id.button5:
                append_answer("5");
                break;
            case R.id.button6:
                append_answer("6");
                break;
            case R.id.button7:
                append_answer("7");
                break;
            case R.id.button8:
                append_answer("8");
                break;
            case R.id.button9:
                append_answer("9");
                break;
            case R.id.buttonC:
                append_answer("clear_answer");
                break;
            case R.id.confirm:
               confirm_answer();
                break;
                default:
                    System.out.println("Something went horribly wrong");
                    break;

        }
    }
}




















