package com.mynetgear.cheuklaw126.hiit;


        import android.app.FragmentTransaction;
        import android.content.SharedPreferences;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.ListFragment;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.mynetgear.cheuklaw126.hiit.LoginActivity;
        import com.mynetgear.cheuklaw126.hiit.YouTubeContent;
        import com.mynetgear.cheuklaw126.hiit.YouTubeFragment;
        import com.google.android.youtube.player.YouTubeIntents;
        import com.google.android.youtube.player.YouTubeStandalonePlayer;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.util.ArrayList;


public class HistoryPage extends AppCompatActivity {
    //Global global;

    int getx=0;
    int x=0;
    int vid,uid,noex;
    String lastD,lastT,cc,eg,com,hr,vn;
    private int showvideo=0;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);
        if(getIntent().hasExtra("x")){
          getx=getIntent().getIntExtra("x",0);
           System.out.println(" getx = "+getx);
        }
        final Button previousVideo = (Button) findViewById(R.id.previousVideo);
        Button nextVideo = (Button) findViewById(R.id.nextVideo);
        ///global = (Global) getIntent().getSerializableExtra("global");
        db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file

        Cursor cursor1 = db.rawQuery("SELECT * from noex;", null);
        while(cursor1.moveToNext()){
            noex=cursor1.getInt(cursor1.getColumnIndex("noofex"));
            System.out.println("in while noex = "+noex);
        }
        if(getx==0) {
            System.out.println(" noex = "+noex);
            getExData(noex);
        }else{
            getExData(getx);
        }
        previousVideo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (noex>0){
                ListFragment listFragment= new VideoListFragment();
                
               previousVideo.setVisibility(View.VISIBLE);
               previousVideo.setClickable(true);
            //x=global.compEx-1;
                x=--noex;
            System.out.println("x = "+x);

                getExData(x);

               Intent intent = new Intent();
               intent.putExtra("x", x);
               intent.setClass(HistoryPage.this, HistoryPage.class);
             listFragment.getListView().invalidateViews();
                startActivity(intent);
            }
                // TODO Auto-generated method stub

            }
        });
    }



    private void getExData(int noex) {

        int getnoex=noex;
      // global.GetExerciseHistory(global.Uid,x);
       // global.GetVideo(global.vid);
        System.out.println("Enter to get db exlist!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file

        Cursor cursor = db.rawQuery("SELECT * from exlist WHERE elid="+getnoex+";", null);

        if (cursor.getCount() > 0) {
            System.out.println("Sucess get data from exlist!!!!! INSIDE exLIST!!!!");
            System.out.println("Count = " + cursor.getCount());

        while (cursor.moveToNext()) {
            vid = cursor.getInt(cursor.getColumnIndex("vid"));
            uid = cursor.getInt(cursor.getColumnIndex("uid"));
            lastD = cursor.getString(cursor.getColumnIndex("lastD"));
            lastT = cursor.getString(cursor.getColumnIndex("lastT"));
            cc = cursor.getString(cursor.getColumnIndex("cc"));
            com = cursor.getString(cursor.getColumnIndex("com"));
            eg = cursor.getString(cursor.getColumnIndex("eg"));
            hr = cursor.getString(cursor.getColumnIndex("hr"));
            //System.out.println("inside videolist " + LINK+"  "+DESC);
            db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file

            Cursor cursor1 = db.rawQuery("SELECT * from videolist where vid="+vid+";", null);
            while(cursor1.moveToNext()){
                vn=cursor1.getString(cursor1.getColumnIndex("vname"));
            }
            TextView showTEXT = (TextView) findViewById(R.id.lastDT);
            showTEXT.setText(lastD);

            TextView complete = (TextView) findViewById(R.id.comYN);
            complete.setText(com);

            TextView exN = (TextView) findViewById(R.id.exName);
            exN.setText(vn);

            TextView doT = (TextView) findViewById(R.id.dotime);
            doT.setText(lastT);

            TextView ccal = (TextView) findViewById(R.id.CalBurn);
            ccal.setText(cc);

            TextView shr = (TextView) findViewById(R.id.heartRate);
            shr.setText(hr);

            TextView exG = (TextView) findViewById(R.id.exGain);
            exG.setText(eg);

        }}else{
            Toast.makeText(getApplicationContext(), "You havn't exercise recond !", Toast.LENGTH_LONG).show();
           finish();
            onBackPressed();
        }
           }


}

