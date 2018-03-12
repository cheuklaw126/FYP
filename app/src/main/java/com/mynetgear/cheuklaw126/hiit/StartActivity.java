package com.mynetgear.cheuklaw126.hiit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Timer tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(StartActivity.this  , IndexActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        }, 2000);

    }
}
