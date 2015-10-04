package com.example.amber.as1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;

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

public class TimerManagement {
    private Context ctx;    //passed by activity for data storing and dialog building
    private double start;   //reaction timer start time
    private double time;    //timer record
    private SharedPreferences pref; //I/O


    public TimerManagement (Context c) {
        //get context value passed by activity
        ctx = c;
        pref = ctx.getSharedPreferences("shuming2-reflex.data", Context.MODE_PRIVATE);
    }

    //get the start time
    public void start() {
        start = System.currentTimeMillis();
    }

    //get the end time and calculate reaction time
    public void end() {
        time = (System.currentTimeMillis() - start) / 1000;
        save();
        dialog("Your reaction time is " + String.valueOf(time) + " seconds", "OK");
    }

    //save time records into shared preference
    public void save() {
        saveArray("reaction_10",10);
        saveArray("reaction_100",100);
        saveArray("reaction_all", -1);
    }

    //create a string storing time records and save it
    protected void saveArray(String key, int size) {
        ArrayList<Double> arrlist = loadArray(key);
        //if reaction_10/100 is full, remove record of index 0
        if (arrlist.size()==size) arrlist.remove(0);
        arrlist.add(time);
        String str = "";
        for (int i=0; i<arrlist.size(); i++) str += arrlist.get(i) + ",";
        str = str.substring(0, str.length() -1);    //remove the last ","
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,str).apply();
    }

    //load a string of time records and convert it to double arraylist(used in stat)
    public ArrayList<Double> loadArray(String key) {
        String data = pref.getString(key, "");
        String[] strarr = data.split(",");  //separate by ","
        ArrayList<Double> arrlist = new ArrayList<>();
        if (data.equals("")) return arrlist;
        for (String str : strarr) arrlist.add(Double.parseDouble(str)); //convert from string to double
        return arrlist;
    }

    //create a dialog
    protected void dialog(String message, String button) {
        AlertDialog builder  = new AlertDialog.Builder(ctx).create();
        builder.setMessage(message) ;
        builder.setCanceledOnTouchOutside(false);
        builder.setButton(AlertDialog.BUTTON_POSITIVE, button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((TimerActivity) ctx).finish();
                ctx.startActivity(new Intent(ctx, TimerActivity.class));    //restart
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ((TimerActivity)ctx).finish();
            }

        });
        builder.show();
    }
}



