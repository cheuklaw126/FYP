package com.ive.hiit;

/**
 * Created by Administrator on 2018/3/22.
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.wearable.input.WearableButtons;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/24.
 */

public class StartExerciseOnWear extends WearableActivity implements SensorEventListener{
    private static final String TAG = "StarExerciseOnWear";
    //private TextView mTextViewStepCount;
    //private TextView mTextViewStepDetect;
    private TextView hp;
    private long lastMillis;
    private long totalMilliseconds;

    private Handler stopwatchHandler;
    private Runnable timerThread;

    private StartClickListener startClickListener;

    private TextView timeSpent;
    private Button start;
    private Button reset;
    Context mContext;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_exercise);
        // Keep the Wear screen always on (for testing only!)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stopwatchHandler = new Handler();
        timerThread = new StopwatchRunnable();
        startClickListener = new StartClickListener();


        timeSpent = (TextView) findViewById(R.id.ex_t);
        hp = (TextView) findViewById(R.id.h_p);
        int count = WearableButtons.getButtonCount(mContext);
      if(count==1){
          StartClickListener sw = new StartClickListener();
          sw.start();
          getStepCount();
      }else if(count==2){
          StartClickListener sw = new StartClickListener();
          sw.stop();

      }

           // }
        //});
    }

    private void getStepCount() {
        SensorManager mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
       // Sensor mStepCountSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        ///Sensor mStepDetectSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //mSensorManager.registerListener(this, mStepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //mSensorManager.registerListener(this, mStepDetectSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private String currentTimeStr() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(c.getTime());
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = "" + (int)event.values[0];
            hp.setText(msg);
            Log.d(TAG, msg);
        }
        //else if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
           // String msg = "Count: " + (int)event.values[0];
           // mTextViewStepCount.setText(msg);
            //Log.d(TAG, msg);
        //}
       // else if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
           // String msg = "Detected at " + currentTimeStr();
            //mTextViewStepDetect.setText(msg);
            //Log.d(TAG, msg);
        //}
        else
            Log.d(TAG, "Unknown sensor type");
    }

    private class StartClickListener implements View.OnClickListener {

        private boolean started = false;

        @Override
        public void onClick(View view) {

            if (started) {
                stop();
                return;
            }

            if (!started) {
                start();
                return;
            }

        }

        private void start() {

            start.setText(getText(R.string.stop));
            started = true;

            lastMillis = SystemClock.uptimeMillis();

            stopwatchHandler.post(timerThread);
        }

        private void stop() {
            start.setText(getText(R.string.start));
            started = false;
            stopwatchHandler.removeCallbacks(timerThread);

        }
    }

    private class ResetClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            startClickListener.stop();
            lastMillis = 0;
            totalMilliseconds = 0;
            timeSpent.setText(TimeFormatter.getTimeFromMillis(totalMilliseconds));

        }
    }

    private class StopwatchRunnable implements Runnable {

        @Override
        public void run() {

            long currentMillis = SystemClock.uptimeMillis();
            totalMilliseconds += currentMillis - lastMillis;
            lastMillis = currentMillis;


            timeSpent.setText(TimeFormatter.getTimeFromMillis(totalMilliseconds));
            stopwatchHandler.post(timerThread);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
if (event.getRepeatCount()==0){
    if(keyCode == KeyEvent.KEYCODE_STEM_1){

        return true;
    }
}
return super.onKeyDown(keyCode, event);
    }
}