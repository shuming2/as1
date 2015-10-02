package com.example.amber.as1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Amber on 15/10/1.
 */
public class BuzzerCount {
    private String mode;
    private SharedPreferences pref;

    public BuzzerCount (Context c, String m) {
        mode = m;
        pref = c.getSharedPreferences("shuming2-reflex.data", Context.MODE_PRIVATE);
    }

    public void count(int i) {
        String key = mode + "_" + String.valueOf(i);
        pref.edit().putInt(key, load(key)+1).apply();
    }
    public int load(String key) {
        return pref.getInt(key, 0);
    }
}
