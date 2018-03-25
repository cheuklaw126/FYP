package com.mynetgear.cheuklaw126.hiit;


        import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//  import com.mynetgear.cheuklaw126.hiit.YouTubeFragment;


public class HistoryPage extends AppCompatActivity {
    //Global global;

    int getx=0;
    int x=0;
    int vid,uid,noex, getnoex,noofex;
    String lastD,lastT,cc,eg,com,hr,vn;
    private int showvideo=0;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);
        System.out.println(" getx = "+getx);
        try{
        if(getIntent().hasExtra("x")) {
            getx = getIntent().getIntExtra("x", 0);
            System.out.println(" getx = " + getx);
        }
        final Button previousVideo = (Button) findViewById(R.id.previousVideo);
        Button nextVideo = (Button) findViewById(R.id.nextVideo);
        ///global = (Global) getIntent().getSerializableExtra("global");
        db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file
        System.out.println("SELECT * from noex;");
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
            //ArrayAdapter la= new ArrayAdapter<String>(getActivity(),android.R.layout.);
        previousVideo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (noex>0){

                //ArrayAdapter la= new ArrayAdapter<String>(getActivity());
                //ListFragment listFragment= new VideoListFragment();
                
               previousVideo.setVisibility(View.VISIBLE);
               previousVideo.setClickable(true);
            //x=global.compEx-1;
                x=--noex;
            System.out.println("x = "+x);

                getExData(x);

               Intent intent = new Intent();
               intent.putExtra("x", x);
               intent.setClass(HistoryPage.this, HistoryPage.class);
               try {

                   getFragmentManager().popBackStack();
                   //la.notifyDataSetChanged();
                   //listFragment.getListView().invalidateViews();
                   startActivity(intent);
                   //VideoListFragment vlf = new VideoListFragment();
            }catch(Exception e) {
                    e.printStackTrace();
                }
            }
                // TODO Auto-generated method stub

            }
        });
    }catch(Exception e) {
        e.printStackTrace();
    }}



    private void getExData(int noex) {

        getnoex=noex;
        System.out.println(" in getEXDATA noex = "+getnoex);
      // global.GetExerciseHistory(global.Uid,x);
       // global.GetVideo(global.vid);
        System.out.println("Enter to get db exlist!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file
        try{

        Cursor cursor=db.rawQuery("SELECT * from noex;",null);
        System.out.println("SELECT * from noex;");
        while(cursor.moveToNext()) {
            noofex = cursor.getInt(cursor.getColumnIndex("noofex"));
        }
        System.out.println("SELECT * from exlist WHERE elid="+getnoex+";");
        Cursor cursor1= db.rawQuery("SELECT * from exlist WHERE elid="+getnoex+";", null);

        if (cursor1.getCount() > 0) {
            System.out.println("Sucess get data from exlist!!!!! INSIDE exLIST!!!!");
            System.out.println("Count = " + cursor1.getCount());

        while (cursor1.moveToNext()) {
            vid = cursor1.getInt(cursor1.getColumnIndex("vid"));
            uid = cursor1.getInt(cursor1.getColumnIndex("uid"));
            lastD = cursor1.getString(cursor1.getColumnIndex("lastD"));
            lastT = cursor1.getString(cursor1.getColumnIndex("lastT"));
            cc = cursor1.getString(cursor1.getColumnIndex("cc"));
            com = cursor1.getString(cursor1.getColumnIndex("com"));
            eg = cursor1.getString(cursor1.getColumnIndex("eg"));
            hr = cursor1.getString(cursor1.getColumnIndex("hr"));
            //System.out.println("inside videolist " + LINK+"  "+DESC);
            db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file
            System.out.println("SELECT * from videolist where vid="+vid+";");
            Cursor cursor2 = db.rawQuery("SELECT * from videolist where vid="+vid+";", null);
            while(cursor2.moveToNext()){
                vn=cursor2.getString(cursor2.getColumnIndex("vname"));
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
            System.out.println("UPDATE  noex set getvid="+vid+" WHERE noofex="+noofex+";");
            db.execSQL("UPDATE  noex set getvid="+vid+" WHERE noofex="+noofex+";");
            System.out.println("DELETE from videolist WHERE vid !="+vid+";");
       //db.execSQL("DELETE from videolist WHERE vid !="+vid+";");
        }}else{
            Toast.makeText(getApplicationContext(), "You havn't exercise recond !", Toast.LENGTH_LONG).show();
           finish();
            onBackPressed();
        }
          }catch(Exception e) {
        e.printStackTrace();
    }


}}

