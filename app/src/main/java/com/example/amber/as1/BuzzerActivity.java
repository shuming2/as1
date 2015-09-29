package com.example.amber.as1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Amber on 15/9/28.
 */
public class BuzzerActivity extends Activity {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buzzer, container, false);

        Button twoplayer = (Button) view.findViewById(R.id.twoplayer);
        twoplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerActivity.this, TimerActivity.class);
                startActivity(intent);

            }
        });
        Button threeplayer = (Button) view.findViewById(R.id.threeplayer);
        threeplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerActivity.this, TimerActivity.class);
                startActivity(intent);

            }
        });
        Button fourplayer = (Button) view.findViewById(R.id.fourplayer);
        fourplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.fourplayer:
                        Buzzer4 fragment = new Buzzer4();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.
                                beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment);
                        transaction.addToBackStack(null).commit();
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
}
