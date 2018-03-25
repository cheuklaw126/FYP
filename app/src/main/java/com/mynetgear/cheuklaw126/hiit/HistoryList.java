package com.mynetgear.cheuklaw126.hiit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/24.
 */

public class HistoryList extends  AppCompatActivity {

    private ArrayList<History> historys;
    private ListView lvHistory;
    public String staff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_list);
        staff=getIntent().getStringExtra("staffname");
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(staff);
        setupData();

        lvHistory = (ListView) findViewById(R.id.hList);
        lvHistory.setAdapter(new HistoryListAdapter(this, historys));
        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                History m = historys.get(i);
                String eid=Integer.toString(m.getEid());

                System.out.println("getEid = " +eid);
                Intent intent = new Intent();
                intent.putExtra("eid", eid);
                intent.setClass(HistoryList.this, HistoryPage.class);
                startActivity(intent);
            }
        });
    }


   
    public void setupData(){
        orders = new ArrayList<Order>();
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.ive.assignment/assignmentDB", null, SQLiteDatabase.OPEN_READWRITE); //open DB file
            Cursor cursor = db.rawQuery("Select j.*, c1.comName as c1comName, c1.comAddress as c1comAddress, c1.comPhone as c1comPhone, c2.comName as c2comName ,c2.comAddress as c2comAddress ,c2.comPhone as c2comPhone from Job as j \n" +
                    "join Company as c1 on j.comFrom=c1.comNo join Company as c2 on j.comTo=c2.comNo;", null);

            while (cursor.moveToNext()) {
                orders.add(new Order(
                        cursor.getInt(cursor.getColumnIndex("jobNo")),
                        cursor.getString(cursor.getColumnIndex("comFrom")),
                        cursor.getString(cursor.getColumnIndex("comTo")),
                        cursor.getString(cursor.getColumnIndex("status")),
                        cursor.getString(cursor.getColumnIndex("orderDateTime")),
                        cursor.getString(cursor.getColumnIndex("pickupDateTime")),
                        cursor.getString(cursor.getColumnIndex("deliveryDateTime")),
                        cursor.getString(cursor.getColumnIndex("c1comName")),
                        cursor.getString(cursor.getColumnIndex("c2comName")),
                        cursor.getString(cursor.getColumnIndex("c1comAddress")),
                        cursor.getString(cursor.getColumnIndex("c2comAddress")),
                        cursor.getString(cursor.getColumnIndex("c1comPhone")),
                        cursor.getString(cursor.getColumnIndex("c2comPhone"))
                ));
            }

            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
