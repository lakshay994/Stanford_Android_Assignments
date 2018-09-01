package com.example.lakshaysharma.dictionarygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private static Map<String,String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionary = new HashMap<>();
        scanner();
        chooseWord();

        final ListView listView = findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = listView.getItemAtPosition(i).toString();
                TextView textView = findViewById(R.id.theWord);
                String theWord = textView.getText().toString();
                String theDefn = dictionary.get(theWord);
                if(selection.equals(theDefn)){
                    Toast.makeText(MainActivity.this, "Awesome!!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                }
                chooseWord();
            }
        });
    }

    private void scanner(){
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.grewords));
        readFile(scan);
        try {
            Scanner scan1 = new Scanner(openFileInput("new_words.txt"));
            readFile(scan1);
        } catch (IOException e){
            //do nothing
        }
    }

    private void readFile(Scanner scan){
        while (scan.hasNextLine()){
            String words = scan.nextLine();
            String[] parts = words.split("\t");
            if (parts.length < 2) continue;
            dictionary.put(parts[0], parts[1]);
        }
    }

    private void chooseWord(){
        Random random  = new Random();
        List<String> listOfWords = new ArrayList<>(dictionary.keySet());
        int index = random.nextInt(listOfWords.size());
        String theWord = listOfWords.get(index);
        String theDefn = dictionary.get(theWord);
        TextView text = findViewById(R.id.theWord);
        text.setText(theWord);

        //Pick 5 Random Numbers from the list if words
        List<String> randomWords = new ArrayList<>(dictionary.values());
        randomWords.remove(theDefn);
        Collections.shuffle(randomWords);
        randomWords = randomWords.subList(0, 4);
        randomWords.add(theDefn);
        Collections.shuffle(randomWords);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(randomWords));
        ListView list = findViewById(R.id.list);
        list.setAdapter(arrayAdapter);
    }
}
