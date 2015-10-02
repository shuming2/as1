package com.example.amber.as1;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Amber on 15/10/1.
 */
public class MeasurementManager {
    private double start;
    private double time;
    private SharedPreferences pref;



    public MeasurementManager (Context c) {
        pref = c.getSharedPreferences("shuming2-reflex.data", Context.MODE_PRIVATE);
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void end() {
        double end = System.currentTimeMillis();
        time = (end - start) / 1000;
        save();
    }

    public double getTimeSeconds() {
        return time;
    }

    public void save() {
        saveArray("reaction_10",10);
        saveArray("reaction_100",100);
        saveArray("reaction_all",-1);
    }


    protected void saveArray(String key, int size) {
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
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,str).apply();
    }

    public ArrayList<Double> loadArray(String key) {
        String data = pref.getString(key, "");
        String[] strarr = data.split(",");
        ArrayList<Double> arrlist = new ArrayList<>();
        if (data.equals("")) return arrlist;
        for (String str : strarr) arrlist.add(Double.parseDouble(str));
        return arrlist;
    }
}
