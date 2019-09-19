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
    public int number_of_questions_left, number_of_max_questions;
    TextView correct_answers_textview, question, start_title;
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
        question = findViewById(R.id.question);

        Intent intent = getIntent();
        number_of_questions_left
                = number_of_max_questions
                = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 5);

        new_question();
        check_saved_instance_state(savedInstanceState);
    }


    public void check_saved_instance_state(Bundle savedInstanceState){
        if(savedInstanceState != null) {
            correct_answers = savedInstanceState.getInt("correct_answers");
            correct_answers_textview = findViewById(R.id.correct_answers);
            correct_answers_textview.setText(String.valueOf(correct_answers));
            number_of_questions_left = savedInstanceState.getInt("number_of_questions_left");
            number_of_max_questions = savedInstanceState.getInt("number_of_max_questions");
            TextView remaining_questions_textview = findViewById(R.id.questions_left);
            remaining_questions_textview.setText(getString(R.string.questions_left) + number_of_questions_left);
            String text = savedInstanceState.getString("language");
            start_title.setText(text);
            Question_ID = savedInstanceState.getInt("currentQuestion");question_arraylist=savedInstanceState.getStringArrayList("questionArraylist");
            answer_arraylist=savedInstanceState.getStringArrayList("answerArraylist");
            question.setText(question_arraylist.get(Question_ID));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentScore", number_of_questions_left);
        outState.putString("language", start_title.getText().toString());
        outState.putInt("currentQuestion", Question_ID);
        outState.putInt("correct_answers",correct_answers);
        outState.putInt("number_of_questions_left", number_of_questions_left);
        outState.putInt("number_of_max_questions",number_of_max_questions);//-------------------
        outState.putStringArrayList("questionArraylist",question_arraylist);
        outState.putStringArrayList("answerArraylist", answer_arraylist);
    }




    public void pointGain(){
        correct_answers++;
        correct_answers_textview = findViewById(R.id.correct_answers);
        correct_answers_textview.setText(String.valueOf(correct_answers));
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
        if(correct_answer())
            pointGain();
        append_answer("clear_answer");
        if (number_of_questions_left > 1)
        next_question();
        else
            createDialog();

    }

    public void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StartAcitivty.this);
        builder.setCancelable(false);
        builder.setTitle(getResources().getString(R.string.winner));
        builder.setMessage(getResources().getString(R.string.complete_message));

        builder.setPositiveButton(getResources().getString(R.string.back_to_highscore), new DialogInterface.OnClickListener() {

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
        //intent.putExtra("SCORE", questions_left);
        startActivity(intent);
    }

    public boolean correct_answer(){
        return answerSelected.equals(answer_arraylist.get(Question_ID));
    }

    public void next_question(){
        TextView pointTextView = findViewById(R.id.questions_left);
        pointTextView.setText(getString(R.string.questions_left) + (number_of_questions_left - 1));
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
        TextView questions_left_textview = findViewById(R.id.questions_left);
        questions_left_textview.setText(getString(R.string.questions_left) + (number_of_questions_left));
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




















