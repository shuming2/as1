package com.example.amber.as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button timerButton = (Button) findViewById(R.id.timer);
        Button buzzerButton = (Button) findViewById(R.id.buzzer);
        Button statButton = (Button) findViewById(R.id.stat);

        timerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
        buzzerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuzzerActivity.class);
                startActivity(intent);
            }
        });
       statButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
               startActivity(intent);
           }
       });
    }
}