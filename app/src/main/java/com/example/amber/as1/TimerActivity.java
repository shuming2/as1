package com.example.amber.as1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
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
                    AlertDialog builder  = new AlertDialog.Builder(TimerActivity.this).create();
                    builder.setMessage("Stick to the button. Once it changed, click!") ;
                    builder.setCanceledOnTouchOutside(false);
                    builder.setButton(AlertDialog.BUTTON_POSITIVE,"Start", new DialogInterface.OnClickListener() {
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
                                            start[0] = System.currentTimeMillis();
                                        }
                                    });
                                }
                            }, waittime);
                            clicktime[0] = 1;
                        }
                    });
                    builder.show();

                } else if (clicktime[1] == 0) {
                    mistakedialog();

                } else {
                    double end = System.currentTimeMillis();
                    double time = (end - start[0]) / 1000;
                    saveArray("reaction10",time,10);
                    saveArray("reaction100", time, 100);
                    dialog(time);
                }

            }
        });
    }


    protected void dialog(double time) {
        AlertDialog builder  = new AlertDialog.Builder(this).create();
        builder.setTitle("Reaction Timer") ;
        builder.setMessage("Your reaction time is " + String.valueOf(time) + " seconds") ;
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

    protected void mistakedialog() {
        AlertDialog builder  = new AlertDialog.Builder(this).create();
        builder.setTitle("Warning") ;
        builder.setMessage("You need to wait before the text on the button changed to <Click now>") ;

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

    protected void saveArray(String key, double time, int size) {
        ArrayList<Double> arrlist = loadArray(key);
        if (arrlist.size()==size) {
            arrlist.remove(0);
        }
        arrlist.add(time);
        String str = "";
        for (int i=0; i<arrlist.size(); i++) {
            str += arrlist.get(i) + ",";
        }
        str = str.substring(0, str.length() -1);
        SharedPreferences.Editor editor = getSharedPreferences("shuming2-reflex.data", MODE_PRIVATE).edit();
        editor.putString(key,str).commit();
    }

    protected ArrayList loadArray(String key) {
        SharedPreferences pref = getSharedPreferences("shuming2-reflex.data", MODE_PRIVATE);
        String data = pref.getString(key, "");
        String[] strarr = data.split(",");
        ArrayList<Double> arrlist = new ArrayList<>();
        if (data.equals("")) {
            return arrlist;
        }
        for (int i=0; i<strarr.length; i++) {
            arrlist.add(Double.parseDouble(strarr[i]));
        }
        return arrlist;
    }
}