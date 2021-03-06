package com.example.amber.as1;

import android.app.Activity;
import android.os.Bundle;
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

public class Buzzer3 extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buzzer3);

        Button player1 = (Button) findViewById(R.id.player1);
        Button player2 = (Button) findViewById(R.id.player2);
        Button player3 = (Button) findViewById(R.id.player3);
        final BuzzerCount counter = new BuzzerCount(this, "buzzer3player", getIntent());

        counter.startDialog();

        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.player1:
                        counter.count(1);
                        break;
                    default:
                        break;
                }
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.player2:
                        counter.count(2);
                        break;
                    default:
                        break;
                }
            }
        });
        player3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.player3:
                        counter.count(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}