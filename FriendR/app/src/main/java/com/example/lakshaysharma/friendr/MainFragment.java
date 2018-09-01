package com.example.lakshaysharma.friendr;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;

import static android.content.Context.MODE_PRIVATE;


public class MainFragment extends Fragment {

    private RatingBar[] RatingBars = new RatingBar[6];
    private ImageButton[] Images = new ImageButton[6];
    private Float rating;

    private final int REQ_CODE = 1234;
    private static String tag;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity = getActivity();

        RatingBars[0] = activity.findViewById(R.id.chandlerRatingBar);
        RatingBars[1] = activity.findViewById(R.id.joeyRatingBar);
        RatingBars[2] = activity.findViewById(R.id.monicaRatingBar);
        RatingBars[3] = activity.findViewById(R.id.phoebeRatingBar);
        RatingBars[4] = activity.findViewById(R.id.rachelRatingBar);
        RatingBars[5] = activity.findViewById(R.id.rossRatingBar);

        Images[0] = activity.findViewById(R.id.chandlerImage);
        Images[1] = activity.findViewById(R.id.joeyImage);
        Images[2] = activity.findViewById(R.id.monicaImage);
        Images[3] = activity.findViewById(R.id.phoebeImage);
        Images[4] = activity.findViewById(R.id.rachelImage);
        Images[5] = activity.findViewById(R.id.rossImage);

        setImageButtonListeners();

    }

    public void setImageButtonListeners(){

        for (int i=0; i<Images.length; i++){
            final int finalI = i;

            Images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tag = Images[finalI].getTag().toString();

                    Activity activity = getActivity();
                    if(getResources().getConfiguration().orientation
                            == Configuration.ORIENTATION_PORTRAIT){
                        Intent intent = new Intent(activity, Details.class);
                        intent.putExtra("Tag", tag);
                        startActivityForResult(intent, REQ_CODE);
                    }
                    else {
                        DetailsFragment details = (DetailsFragment)
                                getFragmentManager().findFragmentById(R.id.fragment_details);
                        details.startActivity(tag);
                    }
                }
            });

        }

    }

    @Override
    public void onResume() {
        super.onResume();

        Activity activity = getActivity();
        SharedPreferences prefs = activity.getSharedPreferences("MyPrefs", MODE_PRIVATE);

        for (int i=0; i<Images.length; i++ ){
            String getRating = Images[i].getTag().toString();
            rating = prefs.getFloat(getRating, 0);
            RatingBars[i].setRating(rating);
        }

    }
}
