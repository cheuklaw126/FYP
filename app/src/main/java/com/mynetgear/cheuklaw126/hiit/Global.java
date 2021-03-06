package com.mynetgear.cheuklaw126.hiit;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kenneth on 27/2/2018.
 */

public class Global extends Application implements Serializable {

    public String UserName, pw, FirstName, LastName, src;
    public int Uid, vid, compEx;
    public String lastD, lastT, cc, hr, eg, com;
    public static String vn, link, desc;
    public static Context contextOfApplication;


    IOObject io;
    ArrayList<JSONObject> fdList, fdRequestList, nearlyByList;

    public Global() {
    }

    public String LastLoginTIme;


    public void SetImage(ImageView bmImage, String url) {
        new DownloadImageTask(bmImage).execute(url);
    }


    public Global(int uid, String userName, String pw, String firstName, String lastName, String lastLoginTIme) {
        Uid = uid;
        UserName = userName;
        this.pw = pw;
        FirstName = firstName;
        LastName = lastName;
        LastLoginTIme = lastLoginTIme;

    }

    public void SetNearlybyList(String uname) {
        if (nearlyByList != null) {
            nearlyByList.clear();
        } else {
            nearlyByList = new ArrayList<JSONObject>();
        }

        String query = String.format("SELECT pData.* ,fdList.funame FROM fdList ,pData where fdList.funame= pData.uname and fdList.uname='%s'", uname);
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            //     JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");
            if (jsonArray.length() > 0) {
                for (int a = 0; a < jsonArray.length(); a++) {
                    JSONObject data = jsonArray.getJSONObject(a);
                    nearlyByList.add(data);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public  byte[] loadFile(File file) throws IOException {
        InputStream is=null;
        try {
             is = new FileInputStream(file);
        }
        catch (Exception e)
        {

            System.out.println(e.toString());
        }
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

    public void  AcceptFrd(String frdName){
        ArrayList<String> querys = new ArrayList<String>();
        String query = String.format("delete fdRequestList where uname='%s';",this.UserName);

        querys.add(query);
        try {
            io = new IOObject("ExecuteNonQuery", querys);
            io.Start();
            JSONObject jsonObject = io.getReturnObject();
            int effectRows = jsonObject.getInt("EffectRows");
            if (effectRows == 1) {
            } else {
            }
        }
        catch (Exception ex){

        }

        querys = new ArrayList<String>();

        query = String.format("insert into fdList  values('%s','%s',GETDATE());", this.UserName.toLowerCase(), frdName.toLowerCase());
        querys.add(query);
        try {
            io = new IOObject("ExecuteNonQuery", querys);
            io.Start();
            JSONObject jsonObject = io.getReturnObject();
            int effectRows = jsonObject.getInt("EffectRows");
            if (effectRows == 1) {
            } else {
            }
        }
         catch (Exception ex){

        }

    }




    public boolean RemoveFrd(String uname,String funame) {
        String query = String.format("delete fdList where (uname='%s'  and funame='%s') or (uname='%s'  and funame='%s');", uname.toLowerCase(),funame.toLowerCase(),funame.toLowerCase(),uname.toLowerCase());
        ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteNonQuery", querys);
            io.Start();
            JSONObject jsonObject = io.getReturnObject();
            int effectRows = jsonObject.getInt("EffectRows");
            if (effectRows == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;

        }
    }


    public boolean AddFrd(String frdUname){

        String myUserName= this.UserName;
        String frdUserName = frdUname;

if(!this.ChkAccExit(frdUname))
{
    return false;
}
        String query = "";
        ArrayList<String> querys = new ArrayList<String>();

        try {

            //     JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");


            querys = new ArrayList<String>();

            query=String.format("delete fdRequestList where  uname='%s' and funame ='%s' ",frdUserName.toLowerCase(),myUserName.toLowerCase());
            querys.add(query);

                query = String.format("  insert into fdRequestList values('%s','%s')  ;",  frdUname, this.UserName);


                querys.add(query);

                io = new IOObject("ExecuteNonQuery", querys);
                io.Start();
                JSONObject jsonObject = io.getReturnObject();
                int effectRows = jsonObject.getInt("EffectRows");
                if (effectRows >0) {
                    return true;
                } else {
                    return false;
                }


        } catch (Exception ex) {
            return false;

        }



    }



    public void SetFrdList() {
        if (fdList != null) {
            fdList.clear();
        } else {
            fdList = new ArrayList<JSONObject>();
        }

        String query = String.format("SELECT pData.* ,fdList.funame  FROM fdList ,pData where fdList.funame= pData.uname and (fdList.uname='%s'    or fdList.funame='%s');", this.UserName.toLowerCase(),this.UserName.toLowerCase());
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            //     JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");
            if (jsonArray.length() > 0) {
                for (int a = 0; a < jsonArray.length(); a++) {
                    JSONObject data = jsonArray.getJSONObject(a);
                    fdList.add(data);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void SetFrdRequestList() {
        if (fdRequestList != null) {
            fdRequestList.clear();
        } else {
            fdRequestList = new ArrayList<JSONObject>();
        }

        String query = String.format("SELECT pData.*  FROM fdRequestList ,pData where fdRequestList.funame= pData.uname and fdRequestList.uname='%s'", this.UserName);
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            //     JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");
            if (jsonArray.length() > 0) {
                for (int a = 0; a < jsonArray.length(); a++) {
                    JSONObject data = jsonArray.getJSONObject(a);
                    fdRequestList.add(data);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean ChkFrdExit(String id){

        String query = String.format("select * from fdlist where (uname ='%s' and funame='%s')   or (uname ='%s' and funame='%s')  ", this.UserName,id.toLowerCase(),id.toLowerCase(),this.UserName);
         ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");

            if (jsonArray.length() > 0) {
                return true;
            } else {
                return false;
            }





        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean ChkAccExit(String id) {

        String query = String.format("select * from pData where uname ='%s'", id.toLowerCase());
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");

            if (jsonArray.length() > 0) {
                return true;
            } else {
                return false;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void GetExerciseHistory(int uid, int x) {
        int xindex;
        String query = String.format("select * from exeriseHistory where uID =%s ", uid);
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        compEx = 0;
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");

            if (jsonArray.length() > 0) {
                compEx = jsonArray.length();
                if (x != 0) {
                    xindex = x;
                } else {
                    xindex = compEx - 1;
                }
                JSONObject eh = jsonArray.getJSONObject(x);
                lastD = eh.getString("createDate");
                lastT = eh.getString("totTime");
                cc = eh.getString("caloriesCal");
                hr = eh.getString("heartRate");
                eg = eh.getString("exGain");
                com = eh.getString("isComplete");
                vid = eh.getInt("vid");

            } else {
                compEx = 0;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void GetVideo(int vid) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.mynetgear.cheuklaw126.hiit/hiitDB", null, SQLiteDatabase.OPEN_READWRITE); //open DB file
        db.execSQL("DELETE FROM videoList");
        String queryV = String.format("select * from movie where vid =%s ", vid);

        final ArrayList<String> queryvs = new ArrayList<String>();
        queryvs.add(queryV);
        try {
            io = new IOObject("ExecuteReader", queryvs);
            io.Start();
            JSONObject vjobj = io.getReturnObject();
            JSONArray vjsonArray = io.getReturnObject().getJSONArray("data");
            JSONObject veh = vjsonArray.getJSONObject(0);

            vn = veh.getString("vname");
            link = veh.getString("link");
            desc = veh.getString("description");
            db.execSQL("INSERT INTO videolist VALUES (" + vid + " , '" + vn + "', '" + link + "', '" + desc + "');");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}

