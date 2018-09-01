package com.example.lakshaysharma.dictionaryaddedfunctionality;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PlayGame extends AppCompatActivity {

    private static Map<String, String> dictionary;
    private static int points;
    private static int highScore;
    private MediaPlayer media;
    private static String theWord;
    private static List<String> defns;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        dictionary = new HashMap<>();
        fileActions();
        chooseWords();

        SharedPreferences prefs = getSharedPreferences("myprefs", MODE_PRIVATE);
        highScore = prefs.getInt("newHighScore", 0);
        Log.d("OnLoad", "New High Score " + highScore); //for testing sake

        setPoints();
        onListClick();

        media = MediaPlayer.create(PlayGame.this, R.raw.piano);
        media.start();

    }

    private void fileActions(){

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.grewords));
        readFile(scanner);
        try{
            Scanner scanner1 = new Scanner(openFileInput("new_words.txt"));
            readFile(scanner1);
        } catch (IOException ex){
            //oh well
        }
    }

    private void readFile(Scanner scan){

        while(scan.hasNextLine()){
            String word = scan.nextLine();
            String[] parts = word.split("\t");
            if (parts.length < 2) continue;
            dictionary.put(parts[0], parts[1]);
        }

    }

    private void chooseWords(){
        /*
            choose 5 definitions from the list
            choose the word to to be displayed
         */
        List<String> words = new ArrayList<>(dictionary.keySet());
        Random random = new Random();
        int index = random.nextInt(words.size());
        theWord = words.get(index);
        String theDefn = dictionary.get(theWord);


        //create new sublist to display
        defns = new ArrayList<>(dictionary.values());
        Collections.shuffle(defns);
        defns.remove(theDefn);
        defns = defns.subList(0, 4);
        defns.add(theDefn);
        Collections.shuffle(defns);

        setWordandList(defns, theWord);

    }

    private void onListClick(){

        final ListView list = findViewById(R.id.defnList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = list.getItemAtPosition(i).toString();
                TextView textView = findViewById(R.id.theWord);
                String currentWord = textView.getText().toString();
                String theDefn = dictionary.get(currentWord);
                if (selection.equals(theDefn)){
                    Toast.makeText(PlayGame.this, "Awesome!", Toast.LENGTH_LONG).show();
                    points++;
                    checkHighScore();
                    setPoints();
                    chooseWords();
                }
                else {
                    Toast.makeText(PlayGame.this, "Try Again", Toast.LENGTH_LONG).show();
                    chooseWords();
                }

            }
        });

    }

    private void setWordandList(List<String> subList, String word){

        //display sublist
        ListView list = findViewById(R.id.defnList);
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(defns)
        );
        list.setAdapter(adapter);

        TextView textView =  findViewById(R.id.theWord);
        textView.setText(theWord);

    }

    private void setPoints(){
        TextView textView = findViewById(R.id.points);
        textView.setText("Points: " + points);
        TextView textView1 = findViewById(R.id.highScore);
        textView1.setText("High Score: " + highScore);
    }

    private void checkHighScore(){
        if (points > highScore){
            highScore = points;

            SharedPreferences prefs = getSharedPreferences("myprefs", MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putInt("newHighScore", highScore);
            Log.d("Saved", "New HighScore Saved"); //for testing sake
            prefEditor.commit();
        }
    }

    public void addNewWord(View view) {

        Intent intent = new Intent(PlayGame.this, AddNewWord.class);
        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(media != null){
            media.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(media != null){
            media.start();
        }

        setWordandList(defns, theWord);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("points", points);


        TextView textview = findViewById(R.id.theWord);
        ListView listView = findViewById(R.id.defnList);


        //get the word and the list from the screen
        String currentWord = textview.getText().toString();
        ArrayList defns = new ArrayList<String>();
        for (int i = 0; i<adapter.getCount(); i++){
            defns.add(adapter.getItem(i));
        }

        outState.putString("theWord", currentWord);
        outState.putStringArrayList("defns", defns);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        points = savedInstanceState.getInt("points", 0);

        theWord = savedInstanceState.getString("theWord");
        defns = savedInstanceState.getStringArrayList("defns");

    }
}
