package com.example.mappe1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class StartAcitivty  extends AppCompatActivity implements View.OnClickListener{

    public String answerSelected = "";
    public int correct_answers = 0;
    String [] questions;
    String [] answers;
    ArrayList <String> question_arraylist, answer_arraylist;
    public int number_of_questions_left, wrong_answers, Question_ID;
    TextView correct_answers_textview, question, start_title,answerView, wrong_answers_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        getAllResources();
        Intent intent = getIntent();
        number_of_questions_left = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 5);
        new_question();
        check_saved_instance_state(savedInstanceState);
    }

    public void getAllResources(){
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        question_arraylist = new ArrayList<>(Arrays.asList(questions));
        answer_arraylist = new ArrayList<>(Arrays.asList(answers));
        correct_answers_textview = findViewById(R.id.correct_answers);
        start_title = findViewById(R.id.start_game_title);
        question = findViewById(R.id.question);
        answerView = findViewById(R.id.answer);
        wrong_answers_textview = findViewById(R.id.wrong_answers);
    }

    public void check_saved_instance_state(Bundle savedInstanceState){
        if(savedInstanceState != null) {
            correct_answers = savedInstanceState.getInt("correct_answers");
            number_of_questions_left = savedInstanceState.getInt("number_of_questions_left");
            wrong_answers = savedInstanceState.getInt("wrong_answers");
            Question_ID = savedInstanceState.getInt("currentQuestion");
            question_arraylist=savedInstanceState.getStringArrayList("questionArraylist");
            answer_arraylist=savedInstanceState.getStringArrayList("answerArraylist");
            correct_answers_textview.setText(String.valueOf(correct_answers));
            question.setText(question_arraylist.get(Question_ID));
            wrong_answers_textview.setText(String.valueOf(wrong_answers));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("currentQuestion", Question_ID);
        outState.putInt("correct_answers",correct_answers);
        outState.putInt("number_of_questions_left", number_of_questions_left);
        outState.putInt("wrong_answers", wrong_answers);
        outState.putStringArrayList("questionArraylist",question_arraylist);
        outState.putStringArrayList("answerArraylist", answer_arraylist);
    }

    public void gain_correct_answers(){
        correct_answers++;
        correct_answers_textview.setText(String.valueOf(correct_answers));
    }

    public void gain_wrong_answers(){
        wrong_answers++;
        wrong_answers_textview.setText(String.valueOf(wrong_answers));
    }

    public void append_answer(String button){
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
        if(correct_answer())
            gain_correct_answers();
        else
            gain_wrong_answers();
        append_answer("clear_answer");
        if (number_of_questions_left > 1)
        next_question();
        else
            createDialog("StatisticsActivity");
    }

    public void createDialog(final String name_of_activity){
        String title = "";
        String message = "";
        String positive_button_text = "";
        String negative_button_text = "";
        boolean negative_button = false;
        boolean cancelable = true;
        switch (name_of_activity){
            case "StatisticsActivity":
                cancelable = false;
                title = "You've won!";
                message = "Continue to see highscores";
                positive_button_text = "Ok";
                break;
            case "MainActivity":
                cancelable = true;
                title = "Exit?";
                message = "Are you sure you want to exit?";
                positive_button_text = "Yes";
                negative_button_text = "No";
                negative_button = true;
                break;
                default:
                    System.out.println("Something went wrong");
                    break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(StartAcitivty.this);
        builder.setCancelable(cancelable);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positive_button_text,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                change_activity(name_of_activity);
            }
        });
        if (negative_button) {
            builder.setNegativeButton(negative_button_text,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
        }
        builder.show();
    }

    @Override
    public void onBackPressed() {
        createDialog("MainActivity");
    }
    
    public void change_activity(String name_of_activity){
        Intent intent = new Intent(this,MainActivity.class);
        switch (name_of_activity) {
            case "StatisticsActivity":
                intent = new Intent(this,StatisticsAcitivty.class);
                intent.putExtra("SCORE", correct_answers);
                break;
            case "MainActivity":
                intent = new Intent(this,MainActivity.class);
                break;
        }
        startActivity(intent);
    }

    public boolean correct_answer(){
        return answerSelected.equals(answer_arraylist.get(Question_ID));
    }

    public void next_question(){
        wrong_answers_textview.setText(String.valueOf(wrong_answers));
        question_arraylist.remove(Question_ID);
        answer_arraylist.remove(Question_ID);
        number_of_questions_left--;
        if (number_of_questions_left >1)
            Question_ID = generate_new_question_ID(number_of_questions_left);
        else
            Question_ID = 0;
        question.setText(question_arraylist.get(Question_ID));
    }

    public void new_question(){
        Question_ID = generate_new_question_ID(number_of_questions_left);
        question.setText(question_arraylist.get(Question_ID));
        wrong_answers_textview.setText(String.valueOf(wrong_answers));
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