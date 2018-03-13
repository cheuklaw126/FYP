package com.mynetgear.cheuklaw126.hiit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONObject;

import java.util.ArrayList;

public class frdActivity extends AppCompatActivity {
    Global global;
    ListView frdListView;
    ArrayList<JSONObject> frdList;
    private ListView listView;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frd);
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

        frdListView = (ListView) findViewById(R.id.frdList);
        global = (Global) getIntent().getSerializableExtra("global");

        global.SetFrdList(global.UserName);
        frdList = global.fdList;
        listView = (ListView) findViewById(R.id.ListFrd);




        adapter = new SimpleAdapter(this, getData(),
                R.layout.activity_frd,
                new String[] { "title", "info", "img" },
                new int[] {	R.id.title, R.id.info, R.id.img });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
            }
        });
      







    }
}
