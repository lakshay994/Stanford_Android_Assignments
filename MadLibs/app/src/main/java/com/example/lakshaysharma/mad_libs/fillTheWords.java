package com.example.lakshaysharma.mad_libs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class fillTheWords extends AppCompatActivity {

    private static ArrayList<String> FILLINS;
    private static ArrayList<String> USERINPUTS;
    private static int NEXTCLUE;
    private static String tag;

    EditText blank;
    TextView wordsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_the_words);

        Intent intent = getIntent();
        tag = intent.getStringExtra("Tag");

        FILLINS = new ArrayList<>();
        USERINPUTS =  new ArrayList<>();
        NEXTCLUE = 0;

        blank = findViewById(R.id.clues);
        wordsLeft = findViewById(R.id.wordsLeft);

        readFileForClues(tag);
        displayClues();

    }

    private void readFileForClues(String tag){

        int fileID = getResources().getIdentifier(tag, "raw", getPackageName());
        Scanner scanner = new Scanner(getResources().openRawResource(fileID));
        while (scanner.hasNext()){
            String word = scanner.next();
            if((word.contains("<"))){
                word = word.replace("<", "");
                word = word.replace(">", "");
                FILLINS.add(word);
            }
        }
    }

    private void displayClues(){

        if (NEXTCLUE == FILLINS.size()){
            buildTheStory(tag);
        }
        else {
            blank.setText("");
            blank.setHint("Enter a " + FILLINS.get(NEXTCLUE));

            wordsLeft.setText("Words Left: " + (FILLINS.size() - NEXTCLUE));

        }

    }

    public void onOK(View view) {

        String input = blank.getText().toString();
        if (input.length() < 2){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(fillTheWords.this);
            alertBuilder.setMessage("Please enter a valid word");
            alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                   //do nothing
                }
            });
            alertBuilder.create().show();
        }
        else{
            USERINPUTS.add(NEXTCLUE, input);
            NEXTCLUE++;
            displayClues();
        }

    }

    private void buildTheStory(String tag){

        int i = 0;

        int fileID = getResources().getIdentifier(tag, "raw", getPackageName());
        Scanner scanner = new Scanner(getResources().openRawResource(fileID));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext() && i<FILLINS.size()){
            String word = scanner.next();
            if(word.equals("<" + FILLINS.get(i) + ">")){
                word = word.replace(word, USERINPUTS.get(i));
                i++;
            }
            stringBuilder.append(word + " ");
        }
        String story = stringBuilder.toString();

        Intent intent = new Intent(fillTheWords.this, theStory.class);
        intent.putExtra("Story", story);
        startActivity(intent);

    }

}
