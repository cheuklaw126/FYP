package com.mynetgear.cheuklaw126.hiit;

/**
 * Created by Administrator on 2018/3/24.
 */
public class History {
    private int eid;
    private int vid;
    private int uid;
    private String c_date;
    private String complete;
    private String vname;

    public History(int eid, int vid, int uid, String c_date, String complete, String vname) {
        setEid(eid);
        setVid(vid);
        setUid(uid);
        setC_date(c_date);
        setComplete(complete);
        setVname(vname);

    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {this.vid = vid;}

    public int getUid() {return uid;}

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getVname() {return vname;}

    public void setVname(String vname) {this.vname = vname;}


}



