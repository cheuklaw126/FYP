package com.mynetgear.cheuklaw126.hiit;


import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView ac, lastname;
    IOObject io;
    Global global;
    VideoView vdo;
    SQLiteDatabase db;
    ImageView pIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.CREATE_IF_NECESSARY); //Create DB file
        try{
            db.execSQL("DROP TABLE if exists videolist;");
            db.execSQL("DROP TABLE if exists exlist;");
            db.execSQL("DROP TABLE if exists noex;");
            db.execSQL("CREATE TABLE IF NOT EXISTS videolist(vid int PRIMARY KEY , vname text, vlink text,vdesc text);");    //Create tables
            db.execSQL("CREATE TABLE IF NOT EXISTS exlist(elid INTEGER PRIMARY KEY AUTOINCREMENT, uid int, vid int, lastD text, lastT text, cc text, hr text, eg text, com text);");
            db.execSQL("CREATE TABLE IF NOT EXISTS noex(noofex int);");
            db.close();

        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
        global = (Global) getApplicationContext();

        vdo = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bg2);
        vdo.setVideoURI(uri);
        vdo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        vdo.start();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView ac = (TextView) findViewById(R.id.textView_ac);
        TextView lastname = (TextView) findViewById(R.id.textView_lastName);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ac.setText(global.UserName);
        lastname.setText(global.LastName);
        pIcon = (ImageView) findViewById(R.id.pIcon);

        global.SetImage(pIcon, global.src);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.logout) {

            global = null;
            MainActivity.this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
try {
    int id = item.getItemId();

    Fragment fragment = null;
    Class currentClass;
      /* View fd  = (View)findViewById(R.id.frd);
        View indexView  = (View)findViewById(R.id.index);
        fd.setVisibility(View.INVISIBLE);
       indexView.setVisibility(View.INVISIBLE);*/
    Intent intent = new Intent();
    intent.putExtra("global", global);

    switch (id) {
        case R.id.frd:
            intent.setClass(MainActivity.this, frdActivity.class);
            break;
        case R.id.nav_gallery:

            GetExerciseHistory(global.Uid);

            intent.setClass(MainActivity.this, HistoryPage.class);
            break;
        default:
            break;
    }
    startActivity(intent);
} catch (Exception e) {
    e.printStackTrace();
}
//        try {
//            fragment = (Fragment) currentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//    FragmentManager fragmentManager = getSupportFragmentManager();
//    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
/*
        if (id == R.id.nav_camera) {
       //     indexView.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.frd) {

        } else if (id == R.id.nav_send) {

        }
        else if(id ==R.id.frd){

        }
*/
        item.setChecked(true);
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        vdo.start();
    }

    public void GetExerciseHistory(int uid){
        int vid;
        int compEx;
        String lastD, lastT, cc, hr, eg, com;
        String query = String.format("select * from exeriseHistory where uID =%s ",uid);
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        compEx=0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //open DB file


        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");
                System.out.println("jsonArray = "+jsonArray.length());
            if (jsonArray.length() > 0) {
                compEx=jsonArray.length();
                db.execSQL("INSERT INTO noex VALUES ("+compEx+");");
                System.out.println("compEx = "+compEx);
                for(int i=0; i<compEx; i++) {
                    JSONObject eh = jsonArray.getJSONObject(i);
                    lastD = eh.getString("createDate");
                    lastT = eh.getString("totTime");
                    cc = eh.getString("caloriesCal");
                    hr = eh.getString("heartRate");
                    eg = eh.getString("exGain");
                    com = eh.getString("isComplete");
                    vid = eh.getInt("vid");
                    String query111=("INSERT INTO exlist (uid, vid, lastD, lastT, cc, hr, eg, com) VALUES ("+uid+ ", "+vid+", '"+lastD+"', '"+lastT+"', '"+cc+"', '"+hr+"', '"+eg+"', '"+com+"');");
                    System.out.println("query111 = "+query111);
                    db.execSQL("INSERT INTO exlist (uid, vid, lastD, lastT, cc, hr, eg, com) VALUES ("+uid+ ", "+vid+", '"+lastD+"', '"+lastT+"', '"+cc+"', '"+hr+"', '"+eg+"', '"+com+"');");

                    GetVideo(vid);
                }
            }else{
                compEx=0;
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void GetVideo(int vid){
        String vn,link,desc;
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //open DB file
        //db.execSQL("DELETE FROM videoList");
        String queryV = String.format("select * from movie where vid =%s ",vid);

        final ArrayList<String> queryvs = new ArrayList<String>();
        queryvs.add(queryV);
        try{
            io = new IOObject("ExecuteReader", queryvs);
            io.Start();
            JSONObject vjobj = io.getReturnObject();
            JSONArray vjsonArray =io.getReturnObject().getJSONArray("data");
            JSONObject veh=vjsonArray.getJSONObject(0);

            vn = veh.getString("vname");
            link = veh.getString("link");
            desc= veh.getString("description");
            db.execSQL("INSERT INTO videolist VALUES ("+vid+" , '"+vn+"', '"+link+"', '"+desc+"');");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
