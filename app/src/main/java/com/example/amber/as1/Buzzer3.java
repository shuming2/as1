package com.example.amber.as1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Amber on 15/9/29.
 */
public class Buzzer3 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buzzer3);

        AlertDialog builder  = new AlertDialog.Builder(this).create();
        builder.setCanceledOnTouchOutside(false);
        builder.setMessage("Are you ready now?") ;
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "Start", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

        final BuzzerCount result = new BuzzerCount(this, "buzzer3player");
        Button player1 = (Button) findViewById(R.id.player1);
        Button player2 = (Button) findViewById(R.id.player2);
        Button player3 = (Button) findViewById(R.id.player3);

        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.player1:
                        result.count(1);
                        dialog(1);
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
                        result.count(2);
                        dialog(2);
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
                        result.count(3);
                        dialog(3);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    protected void dialog(int i) {
        AlertDialog builder = new AlertDialog.Builder(this).create();
        builder.setCanceledOnTouchOutside(false);
        builder.setMessage("The winner is player" + String.valueOf(i));
        builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();
    }
}