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
        final MeasurementManager measure = new MeasurementManager(this);
        final Button button = (Button) findViewById(R.id.button);
        final long[] clicktime = {0,0};

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (clicktime[0] == 0) {
                    AlertDialog builder = new AlertDialog.Builder(TimerActivity.this).create();
                    builder.setMessage("Stick to the button. Once it changed, click!");
                    builder.setCanceledOnTouchOutside(false);
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "Start", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            button.setText("\n    Wait!    \n");
                            long waittime = Math.round(Math.random() * (1990) + 10);
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            button.setText("\n    Click now    \n");
                                            clicktime[1] = 1;
                                            measure.start();
                                        }
                                    });
                                }
                            }, waittime);
                            clicktime[0] = 1;
                        }
                    });
                    builder.show();
                } else if (clicktime[1] == 0) {
                    dialog("Warning!",
                            "You need to wait before the text on the button changed to <Click now>");
                } else {
                    measure.end();
                    dialog("Reaction Timer",
                            "Your reaction time is "+String.valueOf(measure.getTimeSeconds())+" seconds");
                }
            }
        });
    }



    protected void dialog(String title, String message) {
        AlertDialog builder  = new AlertDialog.Builder(this).create();
        builder.setTitle(title);
        builder.setMessage(message) ;
        builder.setCanceledOnTouchOutside(false);
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();
    }
}