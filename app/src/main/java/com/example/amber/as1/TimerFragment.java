package com.example.amber.as1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Amber on 15/9/28.
 */
public class TimerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.timer, container, false);


        Button startButton = (Button) view.findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (view.findViewById(R.id.container) != null) {
                    if (savedInstanceState != null) {
                        return;
                    }
                    TimerStart timerstart = new TimerStart();
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, timerstart).commit();
                }
            }
        });
        return view;
    }
}