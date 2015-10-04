package com.example.amber.as1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

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

public class Stat {
    private Context ctx;    //receive activity context
    private SharedPreferences pref;  //for I/O

    public  Stat(Context c) {
        //define context and shared preference from activity
        ctx = c;
        pref = ctx.getSharedPreferences("shuming2-reflex.data", Context.MODE_PRIVATE);
    }

    //create a dialog showing timer stats
    public void diaTimer() {dialog("Reaction Time Statistics", timerStat());}

    //create a dialog showing buzzer stats
    public void diaBuzzer() {dialog("Buzzer Count", buzzerStat());}

    //clear all record
    public void clear() {
        //create a dialog asking for confirmation
        AlertDialog builder  = new AlertDialog.Builder(ctx).create();
        builder.setMessage("Are you sure you want to delete all the records?");
        builder.setCanceledOnTouchOutside(false);
        builder.setButton(AlertDialog.BUTTON_NEGATIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                pref.edit().clear().apply();    //clear the data file
            }
        });
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    //email stats to someone
    public void email() {
        //call sendto action
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, "STATISTICS");
        intent.putExtra(Intent.EXTRA_TEXT, timerStat()+"\n"+buzzerStat());
        intent.setType("text/plain");
        ctx.startActivity(Intent.createChooser(intent, "Email:"));
    }

    //return a string of all timer stats
    protected String timerStat() {
        return "In all reaction times\n" + timerMessage("reaction_all") +
                "\nIn last 10 reaction times\n" + timerMessage("reaction_10") +
                "\nIn last 100 reaction times\n" + timerMessage("reaction_100");
    }

    //return a string of all buzzer stats
    protected String buzzerStat() {
        return "In 2 player mode\n" + buzzerMessage(2) +
                "In 3 player mode\n" + buzzerMessage(3) +
                "In 4 player mode\n" + buzzerMessage(4);
    }

    //take timer records and return stats of different times
    protected String timerMessage(String k) {
        TimerManagement manager = new TimerManagement(ctx);
        ArrayList<Double> arrlist = manager.loadArray(k);
        if (arrlist.isEmpty()) return "No Record\n";    //no records
        return String.format("The minimum time is %.3f\nthe maximum time is %.3f\nthe average time is %.3f\nthe median time is %.3f\n",
                min(arrlist), max(arrlist), mean(arrlist), median(arrlist));
    }

    //return the minimum value of timer records
    protected double min(ArrayList<Double> arrlist) {return Collections.min(arrlist);}

    //return the maximum value of timer records
    protected double max(ArrayList<Double> arrlist) {return Collections.max(arrlist);}

    //return the average value of timer records
    protected double mean(ArrayList<Double> arrlist) {
        double total = 0;
        for (double d : arrlist) total += d;    //add all timer records
        return total / (double) arrlist.size();
    }

    //return the median value of timer records
    protected double median(ArrayList<Double> arrlist) {
        Collections.sort(arrlist);  //sort timer records
        int median_index = arrlist.size()/2;
        if (arrlist.size()%2 == 1) return arrlist.get(median_index);    //#of records is odd
        return (arrlist.get(median_index-1) + arrlist.get(median_index))/2.0;   //even
    }

    //take game mode type and return counts of different modes
    protected String buzzerMessage(int n) {
        BuzzerCount count = new BuzzerCount(ctx, "buzzer" + String.valueOf(n) + "player", null);
        String buzzer_message = "";
        for (int i = 1 ; i <= n ; i++) {
            String key = "buzzer" + String.valueOf(n) + "player_" + String.valueOf(i);
            buzzer_message += String.format("Player %d buzzes: %d\n", i, count.load(key));
        }
        return buzzer_message+"\n";
    }

    //create a dialog showing stats
    protected void dialog(String title, String message) {
        AlertDialog builder  = new AlertDialog.Builder(ctx).create();
        builder.setTitle(title);
        builder.setMessage(message) ;
        builder.setCanceledOnTouchOutside(false);
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
