package com.example.amber.as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Amber on 15/9/29.
 */
public class StatisticsActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        Button timer = (Button) findViewById(R.id.timer);
        Button buzzer = (Button) findViewById(R.id.buzzer);
        Button clear = (Button) findViewById(R.id.clear);
        Button email = (Button) findViewById(R.id.email);

        timer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTimerStat();
            }
        });
        buzzer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showBuzzerCount();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clear();
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void showTimerStat() {

    }

    protected void showBuzzerCount() {

    }

    protected void clear() {

    }
}
