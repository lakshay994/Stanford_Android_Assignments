package com.example.lakshaysharma.dictionaryaddedfunctionality;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.DisconnectCause;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class learn extends AppCompatActivity {

    private static Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        dictionary = new HashMap<>();
        readFile();
        displayWords();
        onWordClick();

    }

    private void readFile(){

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.grewords));
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("\t");
            dictionary.put(parts[0], parts[1]);
        }
    }

    private void displayWords(){

        ListView listView = findViewById(R.id.learningList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(learn.this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet()));
        listView.setAdapter(arrayAdapter);

    }

    private void onWordClick(){

        ListView list = findViewById(R.id.learningList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String theWord = adapterView.getItemAtPosition(i).toString();
                String defn = dictionary.get(theWord);
                AlertDialog.Builder alert = new AlertDialog.Builder(learn.this);
                alert.setTitle("Definition");
                alert.setMessage(defn);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                });
                alert.create().show();
            }
        });

    }

}
