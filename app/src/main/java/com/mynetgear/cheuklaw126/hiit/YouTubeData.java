package com.mynetgear.cheuklaw126.hiit;


import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/2/23.
 */

public class YouTubeData {

    public static Context getContext() {
        //Context context = App.getContext();
        Context applicationContext = LoginActivity.getContextOfApplication();
        //Context context = getActivity();
        String vid = applicationContext.getSharedPreferences("USER", MODE_PRIVATE)
                .getString("LINK", "");
        String VID=vid;
        return applicationContext;
    }

    static String getVid(String vid){
        String VID=vid;
        return VID;
    }
}

