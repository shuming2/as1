package com.example.amber.as1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
/*
 * Copyright (C) 2015, Shuming Zhang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class TimerActivity extends Activity {
    private Handler handler = new Handler();
    private boolean wait = false;
    private int click = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final TimerManagement manager = new TimerManagement(this);
        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!wait && click==0) {    //first click creates a confirmation dialog
                    click++;
                    AlertDialog builder = new AlertDialog.Builder(TimerActivity.this).create();
                    builder.setMessage("Stick to the button. Once it changed, click!");
                    builder.setCanceledOnTouchOutside(false);
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "Start", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            button.setText("\n    Wait    \n");
                            wait = true;
                            //change the button after waiting time
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    wait = false;
                                    button.setText("\n    Click now    \n");
                                    button.setBackgroundColor(Color.rgb(255, 0, 0));
                                    manager.start();
                                }
                            }, Math.round(Math.random() * (1990) + 10));    //random waiting time
                        }
                    });
                    builder.show();
                } else if (wait) { //click on the wait button restarts the game
                    manager.dialog("You need to wait before the text on the button changed to <Click now>", "OK");
                } else {    //click on the "click now" button shows the record
                    manager.end();
                }
            }
        });
    }
}