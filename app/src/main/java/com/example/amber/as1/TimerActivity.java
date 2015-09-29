package com.example.amber.as1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Amber on 15/9/28.
 */
public class TimerActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final Button button = (Button) findViewById(R.id.button);
        final long[] clicktime = {0,0};
        final double[] start = new double[1];
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (clicktime[0] == 0) {
                    button.setText("\n    Wait!    \n");
                    long waittime = Math.round(Math.random() * (1990) + 10);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    button.setText("\n    Click now    \n");
                                    clicktime[1] = 1;
                                    start[0] = System.currentTimeMillis();
                                }
                            });
                        }
                    }, waittime);
                    clicktime[0] = 1;
                } else if (clicktime[0] != 0 && clicktime[1] == 0) {
                    mistakedialog();

                } else {
                    double end = System.currentTimeMillis();
                    double time = (end - start[0]) / 1000;
                    dialog(time);
                }

            }
        });
    }
    protected void dialog(double time) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(TimerActivity.this);
        builder.setTitle("Reaction Timer") ;
        builder.setMessage("Your reaction time is " + String.valueOf(time) + " seconds") ;
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();
    }

    protected void mistakedialog() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(TimerActivity.this);
        builder.setTitle("Wait!") ;
        builder.setMessage("You need to wait before the text on the button changed to <Click now>") ;
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();
    }
}