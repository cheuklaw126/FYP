package com.mynetgear.cheuklaw126.hiit;


import android.content.Intent;
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
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView ac, lastname;
    Global global;
    VideoView vdo;
    ImageView pIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        pIcon = (ImageView) findViewById(R.id.pIcon);

        global.SetImage(pIcon, global.src);


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
            default:
                break;
        }
        startActivity(intent);
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
}
