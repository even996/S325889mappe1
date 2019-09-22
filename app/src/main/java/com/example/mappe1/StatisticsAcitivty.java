package com.example.mappe1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StatisticsAcitivty extends AppCompatActivity {
    //Denne klassen er ansvarlig for aktiviteten som viser highscoren din, altså statistikk
    //Deklarerer noen variabler her som vi kommer til å trenge senere
    int score;
    TextView t;
    TextView statisticsTitle;
    Button prefReturnBtn, deleteHighscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Sørger for å sjekke hvilket språk vi skal vise
        loadLanguage();
        //Sier at contentViewet skal være statistics_activity.xml
        setContentView(R.layout.statistics_activity);
        //Disse 3 linjene finner bare IDer til forskjellige knapper/view osv
        prefReturnBtn = findViewById(R.id.returnBtn);
        deleteHighscore = findViewById(R.id.deleteHighscore);
        statisticsTitle = findViewById(R.id.statistics_title);

        prefReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Kaller menu hver gang knappen prefReturnBtn trykkes på (Sender oss tilbake til main menu)
                menu();
            }
        });


        TextView t = findViewById(R.id.Result);
        if (t.getText().toString().length()<1)
            //Setter highscoren til verdien 0 dersom vi ikke allerede har en verdi
            t.setText(String.valueOf(0));

        if (getIntent().hasExtra("SCORE")) {
            //Sjekker om vi har lagret noen verdier på tlf, hvis vi har det så setter vi highscoren til det.
            Intent intent = getIntent();
            score = intent.getExtras().getInt("SCORE");
            if (score > (Integer.parseInt(t.getText().toString())))
            t.setText(score + "");
        }


        if(savedInstanceState != null) {
            //Sjekker om vi har lagret et instans (ved rotering av tlf), hvis vi har det så setter vi alle disse verdiene
            score=savedInstanceState.getInt("correct_answers_textview");
            String title = savedInstanceState.getString("statisticsTitle");
            statisticsTitle = findViewById(R.id.statistics_title);
            statisticsTitle.setText(title);
            t.setText(String.valueOf(score));
            prefReturnBtn.setText(savedInstanceState.getString("returnBtn"));
            deleteHighscore.setText(savedInstanceState.getString("deleteHighScore"));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Lagrer alle verdiene vi trenger når mobilen roteres
        super.onSaveInstanceState(outState);
        outState.putInt("correct_answers_textview", score);
        outState.putString("statisticsTitle", statisticsTitle.getText().toString());
        outState.putString("returnBtn",prefReturnBtn.getText().toString());
        outState.putString("deleteHighScore",deleteHighscore.getText().toString());
    }

    public void setLanguage(String country){//Metode som setter riktig språk
        Locale locale = new Locale(country);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("language", MODE_PRIVATE).edit();
        editor.putString("lang", country);
        editor.apply();

    }

    public void loadLanguage(){
        //Metode som loader språket vi har valgt
        // (tilkalles i onCreate slik at skjermen alltid oppdaterer hvilket språk vi har valgt).
        SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
        String language = prefs.getString("lang", "");
        setLanguage(language);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Lagrer scoren lokalt når man går ut av highscores
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString(
                "HIGHSCORE",String.valueOf(score)).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Henter alle variablene som er lagret lokalt på mobilen
        String sharedPreference = getSharedPreferences(
                "PREFERENCE",MODE_PRIVATE).getString("HIGHSCORE","");
        t = findViewById(R.id.Result);
        if (sharedPreference.length()> 0 && Integer.parseInt(sharedPreference) > score)
        score = Integer.parseInt(getSharedPreferences(
                "PREFERENCE",MODE_PRIVATE).getString("HIGHSCORE",String.valueOf("")));
        t.setText(String.valueOf(score));
    }

    public void menu(){
        //Skifter aktivitet til hovedmenyen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createDialog(View view){
        //Lager en dialogboks
        final AlertDialog.Builder builder = new AlertDialog.Builder(StatisticsAcitivty.this);
        builder.setCancelable(true); //Sier at dialogboksen kan kanselleres ved å trykke utenfor
        builder.setTitle(getResources().getString(R.string.warning));//Setter tittel
        builder.setMessage(getResources().getString(R.string.resetScore));//Setter beskjed

        builder.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Knapp som sletter highscoren, kaller metoden delete_highscore()
                delete_highscore();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.no),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Knapp som bare lukker dialogboksen
                dialogInterface.cancel();
            }
        });
        //Viser dialogboksen
        builder.show();
    }

    public void delete_highscore(){
        //Sletter highscoren ved å sette den til 0, og lagre den til disk, for å så oppdetere verdien på skjerm
        score = 0;
        getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putString(
                "HIGHSCORE",String.valueOf(score)).apply();
        t.setText(String.valueOf(score));
    }

}


