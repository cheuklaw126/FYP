package com.ive.hiit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private static int TIME_OUT = 3000; //Time to launch the another activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Enables Always-on
        setAmbientEnabled();
        final ImageView myLayout = findViewById(R.id.wimg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, StartExerciseOnWear.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}