package com.example.leebet_pc.prototype;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.nfc.*;
import android.view.View;
import android.view.WindowManager;

import android.view.animation.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    TextView beep;
    TextView boon;
    public static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        beep = (TextView) findViewById(R.id.BeepText);
        boon = (TextView) findViewById(R.id.BoonText);
        beep.getText();

        final Handler handler = new Handler();
        final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                beep.setVisibility(View.VISIBLE);
                beep.startAnimation(anim);

                boon.setVisibility(View.VISIBLE);
                boon.startAnimation(anim);
            }
        },2000);




        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);

                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);




    }

}
