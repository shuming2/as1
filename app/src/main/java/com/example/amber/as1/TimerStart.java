package com.example.amber.as1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Amber on 15/9/28.
 */
public class TimerStart extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timerstart, container, false);

        Button clickButton = (Button) view.findViewById(R.id.click);
        clickButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });



        return view;
    }


    public void start(){
        TextView textView = (TextView) getView().findViewById(R.id.text);
        textView.setText("Click now!");
    }

}