package com.example.lakshaysharma.hangman;

import android.content.DialogInterface;
import android.os.WorkSource;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String[] WORDS;
    private static int chancesLeft;

    private static String theWord;
    private static StringBuffer temp;
    private static StringBuffer guessed;

    Button newGameButton;
    Button guessButton;
    TextView mainWord;
    TextView chances;
    TextView tried;
    EditText input;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WORDS = getResources().getStringArray(R.array.words);
        temp = new StringBuffer();
        guessed = new StringBuffer();

        tried = findViewById(R.id.tried);
        newGameButton = findViewById(R.id.newGameButton);
        guessButton = findViewById(R.id.tryButton);
        mainWord = findViewById(R.id.theWord);
        chances = findViewById(R.id.chancesLeft);
        image = findViewById(R.id.image);
        input = findViewById(R.id.guessText);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessMade();
            }
        });

    }

    private void start(){

        if(!(temp.length() == 0)){
            temp.delete(0, temp.length());
        }

        Toast.makeText(MainActivity.this, "1 Chance for each Letter", Toast.LENGTH_LONG).show();

        Random random = new Random();
        int index = random.nextInt(WORDS.length);
        theWord = WORDS[index];
        Log.d("WORD: ", theWord);

        for (int i = 0; i<theWord.length(); i++){
            temp.append("?");
        }

        chancesLeft = theWord.length();
        mainWord.setText(temp);
        chances.setText("Chances Left: " + chancesLeft);
        image.setImageResource(R.drawable.hangman6);
        guessed.delete(0, guessed.length());
        tried.setText("Letters Tried: ");

    }

    private void guessMade(){

        final String guessString = input.getText().toString();
        char guess = guessString.charAt(0);

        if (guessString.length() > 1 || guessString.isEmpty()){

            setAlert("Please enter one letter. \nTry Again");
            input.setText("");

        }

        else {

            guessed.append(guess + ", ");

            if (theWord.contains(guessString)){
                for (int i=0; i<theWord.length(); i++){
                    if (theWord.charAt(i) == guess){
                        temp.setCharAt(i, guess);
                    }
                }
            }
            else {
                chancesLeft--;
                chances.setText("Chances Left: "+ chancesLeft);
            }

            if (temp.toString().equals(theWord)){
                setAlert("Congratulations!! \nYou Won.");
                start();
            }

            mainWord.setText(temp);
            input.setText("");
            tried.setText("Letters Tried: [" + guessed + "]");

            if(chancesLeft == 0){
                setAlert("You are out of chances!");
                start();
            }

            int id = getResources().getIdentifier("hangman" + chancesLeft,
                    "drawable", getPackageName());
            image.setImageResource(id);
        }

    }

    private void setAlert(String message){

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.create().show();

    }

}
