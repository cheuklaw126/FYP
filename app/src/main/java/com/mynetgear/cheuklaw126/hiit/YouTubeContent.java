package com.mynetgear.cheuklaw126.hiit;

//import com.mynetgear.cheuklaw126.hiit.ExercisePage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class YouTubeContent {
    //static String d="";
    //Context applicationContext = LoginActivity.getContextOfApplication();

    /**
     * An array of YouTube videos
     */
    public static List<YouTubeVideo> ITEMS = new ArrayList<>();

    // public String getVid(){
    // static Context applicationContext = LoginActivity.getContextOfApplication();
    //  //Context context = getActivity();
    // static String vid = applicationContext.getSharedPreferences("USER", MODE_PRIVATE)
            //.getString("LINK","");
    //public  static String svid = vid;

   // public void setLink(String videolink){
      //  this.vl=videolink;
   // }

   // public String getLink(){
       // return this.vl;
   // }
    // getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    //String vID = getSharedPreferences("user",MODE_PRIVATE)
    //.getString("VID", "");

    //io = new IOObject("ExecuteReader", queryvs);
    //  io.Start();
    // JSONObject vjobj = io.getReturnObject();
    //JSONArray vjsonArray =io.getReturnObject().getJSONArray("data");
    //JSONObject veh=vjsonArray.getJSONObject(0);
    //String vn = veh.getString("vname");
    // return vid;
    // }
    /**
     * A map of YouTube videos, by ID.
     */
    public static Map<String, YouTubeVideo> ITEM_MAP = new HashMap<>();
    //YouTubeData v = new YouTubeData();
    //static String vL = v.getVid();
   // static YouTubeContent gvl= new YouTubeContent();
     static Context applicationContext = LoginActivity.getContextOfApplication();
     //Context context = getActivity();
    static String vid = applicationContext.getSharedPreferences("user", MODE_PRIVATE)
    .getString("LINK","");
    static String desc = applicationContext.getSharedPreferences("user", MODE_PRIVATE)
            .getString("DESC","");

    //public  static String svid = vid;

   // static ExercisePage gvl = new ExercisePage();
    ///gvl.setLink(vid);
  //static String vid = gvl.putLink();

     //static String vid = gvl.getLink();
    //static String vl=gvl.putLink();
    //static String vl="ieyzL5OaPZk";
    //static  {
    //YouTubeVideo ytv=new YouTubeVideo("ieyzL5OaPZk", "15 Minute HIIT Metabolism Booster - Total Body and Abs HIIT Workout");
    static {
        addItem(new YouTubeVideo(vid, desc));
    }

    private static void addItem(final YouTubeVideo item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
   // public void putData(){
        //String gd=d;
        //setData(gd);
       //System.out.println("d = " +getData());
   // }
   // public void setData(String d){
     //   this.d=d;
   // }
   // public String getData(){
    //    System.out.println(this.d);
   //     return this.d;
  //  }
    /**
     * A POJO representing a YouTube video
     */
    public static class YouTubeVideo {
        public String id;
        public String title;

        public YouTubeVideo(String id, String content) {
            this.id = id;
            this.title = content;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
