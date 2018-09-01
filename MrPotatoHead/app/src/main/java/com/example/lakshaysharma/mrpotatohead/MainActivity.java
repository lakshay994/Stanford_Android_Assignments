package com.example.lakshaysharma.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int  VAR = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView[] imageView = new ImageView[VAR];
        CheckBox[] checkBox = new CheckBox[VAR];

        imageView[0] = findViewById(R.id.arms);
        imageView[1] = findViewById(R.id.ears);
        imageView[2] = findViewById(R.id.eyebrows);
        imageView[3] = findViewById(R.id.eyes);
        imageView[4] = findViewById(R.id.glasses);
        imageView[5] = findViewById(R.id.hat);
        imageView[6] = findViewById(R.id.mouth);
        imageView[7] = findViewById(R.id.mustache);
        imageView[8] = findViewById(R.id.nose);
        imageView[9] = findViewById(R.id.shoes);

        checkBox[0] = findViewById(R.id.armsCheck);
        checkBox[1] = findViewById(R.id.earsCheck);
        checkBox[2] = findViewById(R.id.eyebrowsCheck);
        checkBox[3] = findViewById(R.id.eyeCheck);
        checkBox[4] = findViewById(R.id.glassesCheck);
        checkBox[5] = findViewById(R.id.hatCheck);
        checkBox[6] = findViewById(R.id.mouthCheck);
        checkBox[7] = findViewById(R.id.mustacheCheck);
        checkBox[8] = findViewById(R.id.noseCheck);
        checkBox[9] = findViewById(R.id.shoesCheck);

        for(int i = 0; i<VAR; i++){

            final int j = i;

            checkBox[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()){
                        imageView[j].setVisibility(View.VISIBLE);
                    }
                    else {
                        imageView[j].setVisibility((View.INVISIBLE));
                    }
                }
            });

        }

    }
}
