package com.example.mappe1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
    TextView score, question, start_title;
    int Question_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        question_arraylist = new ArrayList<>(Arrays.asList(questions));
        answer_arraylist = new ArrayList<>(Arrays.asList(answers));
        start_title = findViewById(R.id.start_game_title);


        Intent intent = getIntent();
        number_of_maximum_questions_selected = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 5);
        question = findViewById(R.id.question);
        new_question();
        find_all_views_by_id_and_set_on_click_listener();


        if(savedInstanceState != null) {
            points = savedInstanceState.getInt("currentScore");
            score = findViewById(R.id.score);
            score.setText(String.valueOf(points));
            TextView pointTextView = findViewById(R.id.Points);
            pointTextView.setText(getString(R.string.current_points) + points);
            String text = savedInstanceState.getString("language");
            start_title.setText(text);
            Question_ID = savedInstanceState.getInt("currentQuestion");
            number_of_maximum_questions_selected = savedInstanceState.getInt("numberOfQuestions");
            question.setText(question_arraylist.get(Question_ID));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentScore", points);
        outState.putString("language", start_title.getText().toString());
        outState.putInt("currentQuestion", Question_ID);
        outState.putInt("numberOfQuestions", number_of_maximum_questions_selected);
    }


    protected  void find_all_views_by_id_and_set_on_click_listener(){
        //Button confirmBtn = findViewById(R.id.confirm);
        //Button btn0 = findViewById(R.id.button0);
        //Button btn1 = findViewById(R.id.button1);
        //Button btn2 = findViewById(R.id.button2);
        //Button btn3 = findViewById(R.id.button3);
        //Button btn4 = findViewById(R.id.button4);
        //Button btn5 = findViewById(R.id.button5);
        //Button btn6 = findViewById(R.id.button6);
        //Button btn7 = findViewById(R.id.button7);
        //Button btn8 = findViewById(R.id.button8);
        //Button btn9 = findViewById(R.id.button9);
        //Button btnC = findViewById(R.id.buttonC);
        //btn0.setOnClickListener(this);
        //btn1.setOnClickListener(this);
        //btn2.setOnClickListener(this);
        //btn3.setOnClickListener(this);
        //btn4.setOnClickListener(this);
        //btn5.setOnClickListener(this);
        //btn6.setOnClickListener(this);
        //btn7.setOnClickListener(this);
        //btn8.setOnClickListener(this);
        //btn9.setOnClickListener(this);
        //btnC.setOnClickListener(this);
        //confirmBtn.setOnClickListener(this);
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
            append_answer("clear_answer");
        }
        answerView.setText(answerSelected);
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
            createDialog();

    }

    public void createDialog(){
        System.out.println("NÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅ");
        AlertDialog.Builder builder = new AlertDialog.Builder(StartAcitivty.this);
        builder.setCancelable(false);
        builder.setTitle("You win!");
        builder.setMessage("You've completed all the questions");

        builder.setPositiveButton("Back to main high scores", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("BYTTER SCENE");
                toMenu();

            }
        });
        builder.show();
    }

    public void toMenu(){
        Intent intent = new Intent(this,StatisticsAcitivty.class);
        intent.putExtra("SCORE", points);
        startActivity(intent);
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




















