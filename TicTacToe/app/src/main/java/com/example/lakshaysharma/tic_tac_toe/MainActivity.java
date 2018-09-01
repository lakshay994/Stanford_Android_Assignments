package com.example.lakshaysharma.tic_tac_toe;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean action = false;
    private Button[][] button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = new Button[3][3];
        button[0][0] = findViewById(R.id.top_left);
        button[0][1] = findViewById(R.id.top_middle);
        button[0][2] = findViewById(R.id.top_right);
        button[1][0] = findViewById(R.id.middle_left);
        button[1][1] = findViewById(R.id.middle_middle);
        button[1][2] = findViewById(R.id.middle_right);
        button[2][0] = findViewById(R.id.bottom_left);
        button[2][1] = findViewById(R.id.bottom_middle);
        button[2][2] = findViewById(R.id.bottom_right);

    }

    public void takeAction(View view) {

        action = !action;

        Button button = (Button) view;
        if (action){
            button.setText("X");
            decideWinner("X");
        }
        else {
            button.setText("O");
            decideWinner("O");
        }

    }

    public void decideWinner(String player){

        boolean winner = decision();
        if(winner){

            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("**WINNER**");
            alert.setMessage("Player \"" + player + "\" Won!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //do nothing
                }
            });
            alert.create().show();
            Button button = findViewById(R.id.clear);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearGame();
                }
            });
        }

    }

    public void clearGame() {

        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i< table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j<row.getChildCount(); j++){
                Button button = (Button) row.getChildAt(j);
                button.setText("");
            }
        }

    }

    public boolean decision(){

        String[][] buttonText = new String[3][3];

        for (int i = 0; i<3; i++){

            for (int j = 0; j<3; j++){

                buttonText[i][j] = button[i][j].getText().toString();

            }

        }

        for (int i = 0; i<3; i++){

            if(buttonText[i][0].equals(buttonText[i][1]) &&
                    buttonText[i][0].equals(buttonText[i][2]) &&
                    !"".equals(buttonText[i][0])){
                return true;
            }

            if (buttonText[0][i].equals(buttonText[1][i]) &&
                    buttonText[0][i].equals(buttonText[2][i]) &&
                    !"".equals(buttonText[0][i])){
                return true;
            }

        }

        if (buttonText[0][0].equals(buttonText[1][1]) &&
                buttonText[0][0].equals(buttonText[2][2]) &&
                !"".equals(buttonText[0][0])){
            return true;
        }

        if (buttonText[0][2].equals(buttonText[1][1]) &&
                buttonText[0][2].equals(buttonText[2][0]) &&
                !"".equals(buttonText[0][2])){
            return true;
        }

        return false;

    }
}
