package com.example.amber.as1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Amber on 15/9/29.
 */
public class Buzzer4 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.buzzer4, container, false);


        Button player1 = (Button) view.findViewById(R.id.player1);
        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        Button player2 = (Button) view.findViewById(R.id.player2);
        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Button player3 = (Button) view.findViewById(R.id.player3);
        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        Button player4 = (Button) view.findViewById(R.id.player4);
        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return view;
    }
}