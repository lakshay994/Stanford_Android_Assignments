package com.example.lakshaysharma.dictionaryaddedfunctionality;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_ADD_WORD = 1221;
    private static int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setHighScore();
    }

    public void playGame(View view) {

        Intent intent = new Intent(this, PlayGame.class);
        startActivity(intent);

    }

    public void addNewWord(View view) {

        Intent intent = new Intent(this, AddNewWord.class);
        startActivityForResult(intent, REQ_CODE_ADD_WORD);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String newWord = data.getStringExtra("newWord");

        if (requestCode == REQ_CODE_ADD_WORD){
            Toast.makeText(MainActivity.this, newWord + " Added!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("myprefs", MODE_PRIVATE);
        highScore = prefs.getInt("newHighScore", 0);
        setHighScore();
    }

    private void setHighScore(){

        Log.d("MainHigh", "main "+highScore);
        TextView textView = findViewById(R.id.highScore);
        textView.setText("High Score: " + highScore);

    }

    public void learnNewWords(View view) {

        Intent intent = new Intent(MainActivity.this, learn.class);
        startActivity(intent);

    }
}
