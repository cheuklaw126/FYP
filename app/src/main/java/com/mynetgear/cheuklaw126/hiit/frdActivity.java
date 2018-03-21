package com.mynetgear.cheuklaw126.hiit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONObject;

import java.util.ArrayList;

public class frdActivity extends AppCompatActivity {
    TabHost tabHost;
    Global global;
    ArrayList<JSONObject> frdList;
    private ListView fdView,suggestView,addView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frd);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabHost = (TabHost)findViewById(R.id.tab_host);
        tabHost.setup();



        TabHost.TabSpec spec=tabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Friends");
        tabHost.addTab(spec);

        spec=tabHost.newTabSpec("Suggested Friends");
        spec.setIndicator("Suggested Friends");
        spec.setContent(R.id.tab2);
        tabHost.addTab(spec);
        spec=tabHost.newTabSpec("Friends Request");
        spec.setIndicator("Friends Request");
        spec.setContent(R.id.tab3);
        tabHost.addTab(spec);


fdView = (ListView) findViewById(R.id.fdView);
        suggestView = (ListView) findViewById(R.id.suggestView);
        addView = (ListView) findViewById(R.id.addView);

     //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    //    frdListView = (ListView) findViewById(R.id.frd);
        global = (Global) getApplicationContext();

        global.SetFrdList(global.UserName);
        frdList = global.fdList;

        fdView = (ListView) findViewById(R.id.fdView);
        suggestView = (ListView) findViewById(R.id.suggestView);
        addView = (ListView) findViewById(R.id.addView);


        fdView.setAdapter(new frdAdapter(this,frdList,global));

       // listView = (ListView) findViewById(R.id.frd);





//        listView.setAdapter(new frdAdapter(this,frdList));
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ListView listView = (ListView) parent;
//            }
//        });








    }
}
