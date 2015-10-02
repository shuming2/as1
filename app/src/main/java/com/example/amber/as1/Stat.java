package com.example.amber.as1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Amber on 15/10/1.
 */
public class Stat {
    Context ctx;
    SharedPreferences pref;

    public  Stat(Context c) {
        ctx = c;
        pref = ctx.getSharedPreferences("shuming2-reflex.data", Context.MODE_PRIVATE);
    }
    public void diaTimer() {
        dialog("Reaction Time Statistics", showTimer());
    }
    public void diaBuzzer() {
        dialog("Buzzer Count", showBuzzer());
    }


    public void clear() {
        AlertDialog builder  = new AlertDialog.Builder(ctx).create();
        builder.setMessage("Are you sure you want to delete all the records?");
        builder.setCanceledOnTouchOutside(false);
        builder.setButton(AlertDialog.BUTTON_NEGATIVE, "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                pref.edit().clear().apply();
            }
        });
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void email() {
        try {
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(
                    ctx.openFileOutput("statistics.txt", Context.MODE_PRIVATE)));
            bw.write(showTimer()+"\n");
            bw.write(showBuzzer());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File stat = new File("/data/data/com.example.amber.as1/files/statistics.txt");
        Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_SUBJECT, "statistics.txt");
            intent.putExtra(Intent.EXTRA_TEXT, "statistics.txt");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" +
                    stat.getAbsolutePath()));
            intent.setType("text/plain");

        ctx.startActivity(Intent.createChooser(intent, "Email:"));
    }

    protected String showTimer() {
        return "In all reaction times\n" + timerMessage("reaction_all") +
                "\nIn last 10 reaction times\n" + timerMessage("reaction_10") +
                "\nIn last 100 reaction times\n" + timerMessage("reaction_100");
    }

    protected String showBuzzer() {
        return "In 2 player mode\n" + buzzerMessage(2) +
                "In 3 player mode\n" + buzzerMessage(3) +
                "In 4 player mode\n" + buzzerMessage(4);
    }

    protected String timerMessage(String k) {
        MeasurementManager manager = new MeasurementManager(ctx);
        ArrayList<Double> arrlist = manager.loadArray(k);
        if (arrlist.isEmpty()) return "No Record\n";
        return String.format("The minimum time is %.3f\nthe maximum time is %.3f\nthe average time is %.3f\nthe median time is %.3f\n",
                min(arrlist), max(arrlist), mean(arrlist), median(arrlist));
    }

    protected double min(ArrayList<Double> arrlist) {
        return Collections.min(arrlist);
    }

    protected double max(ArrayList<Double> arrlist) {
        return Collections.max(arrlist);
    }

    protected double mean(ArrayList<Double> arrlist) {
        double total = 0;
        for (double d : arrlist) total += d;
        return total / (double) arrlist.size();
    }

    protected double median(ArrayList<Double> arrlist) {
        Collections.sort(arrlist);
        int median_index = arrlist.size()/2;
        if (arrlist.size()%2 == 1) return arrlist.get(median_index);
        else return (arrlist.get(median_index-1) + arrlist.get(median_index))/2.0;
    }

    protected String buzzerMessage(int n) {
        String mode = "buzzer" + String.valueOf(n) + "player";
        String key;
        BuzzerCount count = new BuzzerCount(ctx, mode);
        String buzzer_message = "";
        for (int i = 1 ; i <= n ; i++) {
            key = mode + "_" + String.valueOf(i);
            buzzer_message += String.format("Player %d buzzes: %d\n", i, count.load(key));
        }
        return buzzer_message+"\n";
    }

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
