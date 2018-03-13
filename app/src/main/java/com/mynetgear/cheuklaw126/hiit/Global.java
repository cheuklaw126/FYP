package com.mynetgear.cheuklaw126.hiit;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kenneth on 27/2/2018.
 */

public class Global extends Application implements Serializable  {
    public String UserName, pw, FirstName, LastName,src;
    public int Uid;
    IOObject io;
    ArrayList<JSONObject> fdList;
    public Global() {
    }

    public String LastLoginTIme;

public void SetImage(ImageView bmImage,String url){
    new DownloadImageTask(bmImage).execute(url);
}



    public Global(int uid, String userName, String pw, String firstName, String lastName, String lastLoginTIme) {
        Uid=uid;
        UserName = userName;
        this.pw = pw;
        FirstName = firstName;
        LastName = lastName;
        LastLoginTIme = lastLoginTIme;

    }

    public void SetFrdList(String uid){
fdList.clear();
        String query = String.format("SELECT * FROM fdList join pData on fdList.uid = pData.uid where uid='%s'",uid);
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");

            for (int a=0;a<jsonArray.length();a++){
                JSONObject data=     jsonArray.getJSONObject(a);
                fdList.add(data);
            }
            }
            catch (Exception ex){

            }
    }


    public boolean ChkAccExit(String id) {

        String query = String.format("select * from pData where uname ='%s'", id);
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

    public void GetExerciseHistory(int uid){

        String query = String.format("select * from exeriseHistory where uID =%s ",uid);
        final ArrayList<String> querys = new ArrayList<String>();
        querys.add(query);
        try {
            io = new IOObject("ExecuteReader", querys);
            io.Start();
            JSONObject jobj = io.getReturnObject();
            JSONArray jsonArray = io.getReturnObject().getJSONArray("data");
            JSONObject eh=jsonArray.getJSONObject(0);
            String lastD = eh.getString("createDate");
            String lastT= eh.getString("totTime");
            String cc = eh.getString("caloriesCal");
            String hr = eh.getString("heartRate");
            String eg = eh.getString("exGain");
            String com = eh.getString("isComplete");
            String vid = eh.getString("vid");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void GetVideo(int vid){

        String queryV = String.format("select * from movie where vid =%s ",vid);

        final ArrayList<String> queryvs = new ArrayList<String>();
        queryvs.add(queryV);
        try{
        io = new IOObject("ExecuteReader", queryvs);
        io.Start();
        JSONObject vjobj = io.getReturnObject();
        JSONArray vjsonArray =io.getReturnObject().getJSONArray("data");
        JSONObject veh=vjsonArray.getJSONObject(0);
        String vn = veh.getString("vname");
        String link = veh.getString("link");
        String desc= veh.getString("description");
        }
        catch (Exception ex){
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
