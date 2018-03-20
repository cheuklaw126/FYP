package com.mynetgear.cheuklaw126.hiit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2018/3/15.
 */

public class VideoList {
    SQLiteDatabase db;
   public String LINK;
    public String DESC;
    public  String getDESC() {
        return DESC;
    }



    public String getLINK() {
        return LINK;
    }


    public void setLINK(String LINK) {
        this.LINK = LINK;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }

    public VideoList() {

        System.out.println("Enter to get db videolist!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //Create DB file
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                System.out.println("Table Name=> "+c.getString(0));
                c.moveToNext();
            }
        }
        Cursor cc = db.rawQuery("SELECT COUNT() from videolist;", null);
        if(cc.getCount()>0){
            System.out.println(">0 = "+cc.getCount());
        }
        Cursor cursor = db.rawQuery("SELECT * from videolist;", null);

        if (cursor.getCount() > 0) {
            System.out.println("Sucess get data from videolist!!!!!!!!!!!!!! INSIDE VIDEOLIST!!!!");
            System.out.println("Count = " + cursor.getCount());
        } else {
            System.out.println("No data get from videolist!!!!!!!!!!!!!!INSIDE VIDEOLIST!!!!");
            System.out.println("Count = " + cursor.getCount());
        }
        while (cursor.moveToNext()) {
             LINK = cursor.getString(cursor.getColumnIndex("vlink"));
            DESC = cursor.getString(cursor.getColumnIndex("vdesc"));
            setLINK(LINK);
            setDESC(DESC);

System.out.println("inside videolist " + LINK+"  "+DESC);

        }
    }
}
