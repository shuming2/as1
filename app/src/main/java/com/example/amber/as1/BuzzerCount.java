package com.example.amber.as1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class BuzzerCount {
    private Context ctx; //used in I/O and dialog
    private String mode;    //game mode (2/3/4 players)
    private SharedPreferences pref; //I/O
    private Intent intent;  //for restart activity

    public BuzzerCount (Context c, String m, Intent i) {
        //define context, game mode, shared preference, and intent
        ctx = c;
        mode = m;
        intent = i;
        pref = ctx.getSharedPreferences("shuming2-reflex.data", Context.MODE_PRIVATE);
    }

    //create a start dialog
    public void startDialog() {
        AlertDialog builder  = new AlertDialog.Builder(ctx).create();
        builder.setCanceledOnTouchOutside(false);
        builder.setMessage("Are you ready to start?") ;
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "Start", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
        });
        //backpress finish activity
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {finish();}
        });
        builder.show();
    }

    //load value of count, add 1, and save it
    public void count(int i) {
        String key = mode + "_" + String.valueOf(i);
        pref.edit().putInt(key, load(key)+1).apply();
        dialog(i);
    }

    //load value of count(used in stat)
    public int load(String key) {return pref.getInt(key, 0);}

    //create dialog showing the winner
    protected void dialog(int i) {
        AlertDialog builder = new AlertDialog.Builder(ctx).create();
        builder.setCanceledOnTouchOutside(false);
        builder.setMessage("The winner is player" + String.valueOf(i));
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                ctx.startActivity(intent);  //restart activity
            }
        });
        //backpress finish activity
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        builder.show();
    }

    //finish activity
    protected void finish() {
        if (mode.contains("2")) ((Buzzer2) ctx).finish();
        else if (mode.contains("3")) ((Buzzer3) ctx).finish();
        else ((Buzzer4) ctx).finish();
    }
}
