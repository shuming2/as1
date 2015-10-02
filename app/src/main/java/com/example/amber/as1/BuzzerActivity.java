package com.example.amber.as1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Amber on 15/9/28.
 */
public class BuzzerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buzzer);

        Button twoplayer = (Button) findViewById(R.id.twoplayer);
        Button threeplayer = (Button) findViewById(R.id.threeplayer);
        Button fourplayer = (Button) findViewById(R.id.fourplayer);

        twoplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerActivity.this, Buzzer2.class);
                startActivity(intent);
            }
        });
        threeplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerActivity.this, Buzzer3.class);
                startActivity(intent);
            }
        });
        fourplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(BuzzerActivity.this, Buzzer4.class);
                startActivity(intent);
            }
        });
    }
}
