package com.mynetgear.cheuklaw126.hiit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kenneth on 27/2/2018.
 */

public class Global implements Serializable {
    public String UserName, pw, FirstName, LastName;
    IOObject io;
    ArrayList fdList;
    public Global() {
    }

    public String LastLoginTIme;

    public Global(String userName, String pw, String firstName, String lastName, String lastLoginTIme) {
        UserName = userName;
        this.pw = pw;
        FirstName = firstName;
        LastName = lastName;
        LastLoginTIme = lastLoginTIme;

    }

    public void GetFrdList(String uid){
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
}
