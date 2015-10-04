package com.example.amber.as1;

import android.app.Activity;
import android.content.Intent;
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

public class BuzzerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buzzer);

        Button two_player = (Button) findViewById(R.id.twoplayer);
        Button three_player = (Button) findViewById(R.id.threeplayer);
        Button four_player = (Button) findViewById(R.id.fourplayer);

        two_player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {startActivity(new Intent(BuzzerActivity.this, Buzzer2.class));}
        });
        three_player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {startActivity(new Intent(BuzzerActivity.this, Buzzer3.class));}
        });
        four_player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {startActivity(new Intent(BuzzerActivity.this, Buzzer4.class));}
        });
    }
}
