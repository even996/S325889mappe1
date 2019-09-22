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
    //Setter variabler som vi kommer til å trenge senere
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
        //Setter contentViewet til å bruke start_acitivity.xml
        setContentView(R.layout.start_activity);
        //Henter alle ressursene og IDene som vi trenger, brukes for å øke lesbarhet i kode
        getAllResources();
        Intent intent = getIntent();
        //Henter antall spørsmål valgt, finnes ikke dette settes standerd til 5
        number_of_questions_left = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 5);
        //Genererer ett nytt spørsmål (Henter tilfeldig fra array)
        new_question();
        //Sjekker om vi har noen lagrede variabler å hente (ved rotering av tlf trengs dette).
        check_saved_instance_state(savedInstanceState);
    }

    public void getAllResources(){
        //Henter alle ressurser vi trenger
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        question_arraylist = new ArrayList<>(Arrays.asList(questions));
        answer_arraylist = new ArrayList<>(Arrays.asList(answers));
        correct_answers_textview = findViewById(R.id.correct_answers);
        question = findViewById(R.id.question);
        answerView = findViewById(R.id.answer);
        wrong_answers_textview = findViewById(R.id.wrong_answers);
    }

    public void check_saved_instance_state(Bundle savedInstanceState){
        if(savedInstanceState != null) {
            //Dersom vi har lagret noen variabler når vi snur tlf, hentes disse og settes
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
        //Lagrer variabler vi trenger, når telefonen roteres
        outState.putInt("currentQuestion", Question_ID);
        outState.putInt("correct_answers",correct_answers);
        outState.putInt("number_of_questions_left", number_of_questions_left);
        outState.putInt("wrong_answers", wrong_answers);
        outState.putStringArrayList("questionArraylist",question_arraylist);
        outState.putStringArrayList("answerArraylist", answer_arraylist);
    }

    public void gain_correct_answers(){
        //Øker antall riktige svar, og oppdaterer skjerm
        correct_answers++;
        correct_answers_textview.setText(String.valueOf(correct_answers));
    }

    public void gain_wrong_answers(){
        //Øker antall feil svar, og oppdaterer skjerm
        wrong_answers++;
        wrong_answers_textview.setText(String.valueOf(wrong_answers));
    }

    public void append_answer(String button){
        //Håndterer avgitt svar, appenderer svaret når 2-3 trykkes blir svaret 23
        //Brukes også til å fjerne hva som står i svaret ved neste spørsmål
        if(button.equals("clear_answer")){
            answerSelected="";
        }
        else {
            //Denne som appenderer (Legger til et tall i strengen som er lagret, ikke overkjører)
            answerSelected += button;
        }
        if(answerSelected.length()>3){
            //Dersom brukeren skriver inn mer enn 3 tall, slettes alle 3 (Ingen svar er over 999)
            append_answer("clear_answer");
        }
        //Viser hva brukeren har svart
        answerView.setText(answerSelected);
    }

    public int generate_new_question_ID(int number_of_questions){
        //Genererer en ID som brukes til å trekke spørsmål i array
        //Tallet som genereres er mellom 0 og antall spørsmål man har valgt (eller har igjen).
        Random random_number_generator = new Random();
        return random_number_generator.nextInt(number_of_questions);
    }

    public void confirm_answer(){
        //Denne sier seg selv
        if(correct_answer())
            gain_correct_answers();
        else
            gain_wrong_answers();
        append_answer("clear_answer");
        if (number_of_questions_left > 1)
            next_question();
            //Dersom det er flere spørsmål igjen, går vi videre til neste spørsmål.
        else
            createDialog("StatisticsActivity");
            //Hvis det ikke er flere spøsmål igjen, kommer en dialogboks opp som sier spillet er ferdig
    }

    public void createDialog(final String name_of_activity){
        //Lager en dialogboks med tittel, beskjed, antall knapper osv basert på navnet til aktiviteten vi skal sendes til
        //Denne metoden ble laget for å ikke skrive duplikat kode, slik at vi kun trenger en dialogboks metode.
        String title = "";
        String message = "";
        String positive_button_text = "";
        String negative_button_text = "";
        boolean negative_button = false;
        boolean cancelable = true;
        switch (name_of_activity){
            case "StatisticsActivity":
                //Hvis vi skal inn på statisticsActivity, settes variablene til dette
                cancelable = false;
                title = getResources().getString(R.string.winner);
                message = getResources().getString(R.string.winner_Text);
                positive_button_text = "Ok";
                break;
            case "MainActivity":
                //Hvis vi skal inn til MainActivity, settes variablene til dette
                cancelable = true;
                title = getResources().getString(R.string.quit);
                message = getResources().getString(R.string.quit_message);
                positive_button_text = getResources().getString(R.string.yes);
                negative_button_text = getResources().getString(R.string.no);
                negative_button = true;
                break;
                default:
                    System.out.println("Something went wrong");
                    break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(StartAcitivty.this);
        //Setter variablene til dialogboksen til riktige verdier, som ble valgt ovenfor.
        builder.setCancelable(cancelable);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positive_button_text,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Bytter aktivitet til navnet på aktiviten som blir sendt inn
                change_activity(name_of_activity);
            }
        });
        if (negative_button) {
            builder.setNegativeButton(negative_button_text,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Lukker dialogboksen
                            dialogInterface.cancel();
                        }
                    });
        }
        builder.show();
    }

    @Override
    public void onBackPressed() {
        //Dersom tilbakeknappen trykkes på midt i spillet, lager vi en dialogboks som sender deg
        //til hovedmenyen (MainActivity) dersom man trykker ja.
        createDialog("MainActivity");
    }
    
    public void change_activity(String name_of_activity){
        //Bytter intent basert på hvilken aktivitet man vil inn på, brukes for å redusere kopi av kode.
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
        //Hvis svaret til brukeren er likt svaret i arrayet med korresponderende ID,
        //får brukeren riktig svar, hvis ikke får brukeren feil svar.
        return answerSelected.equals(answer_arraylist.get(Question_ID));
    }

    public void next_question(){
        //Lager nytt spørsmål, oppdaterer alle verdier og sletter forrige spørsmål fra arrayene
        wrong_answers_textview.setText(String.valueOf(wrong_answers));
        question_arraylist.remove(Question_ID);
        answer_arraylist.remove(Question_ID);
        number_of_questions_left--;
        if (number_of_questions_left >1)
            Question_ID = generate_new_question_ID(number_of_questions_left);
            //Hvis det er flere enn 2 spørsmål igjen?, lag nytt spørsmål
        else
            Question_ID = 0;
            //Hvis ikke, sett ID til 0 og vis det siste spørsmålet
        question.setText(question_arraylist.get(Question_ID));
        //Vis spørsmålet som er hentet
    }

    public void new_question(){
        //Lager det første spørsmålet
        Question_ID = generate_new_question_ID(number_of_questions_left);
        question.setText(question_arraylist.get(Question_ID));
        wrong_answers_textview.setText(String.valueOf(wrong_answers));
    }

    @Override
    public void onClick(View view) {
        //Sender inn hva brukeren har trykket på, 0-9, = eller C.
        //Disse verdiene sendes inn i append_answer
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