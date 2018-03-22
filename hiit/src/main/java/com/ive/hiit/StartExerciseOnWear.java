package com.ive.hiit;

/**
 * Created by Administrator on 2018/3/22.
 */

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

public class StartExerciseOnWear extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_exercise);

    }
}
