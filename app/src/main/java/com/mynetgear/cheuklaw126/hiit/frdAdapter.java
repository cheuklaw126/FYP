package com.mynetgear.cheuklaw126.hiit;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kenneth on 14/3/2018.
 */

public class frdAdapter extends BaseAdapter {

    private LayoutInflater contextView;
    private ArrayList<JSONObject> frds;

    private class ViewHolder {
        TextView tvLastName;
        TextView tvUid;
        ImageView imageView;

        public ViewHolder(TextView tvLastName, TextView tvUid, ImageView imageView) {
            this.tvLastName = tvLastName;
            this.tvUid = tvUid;
            this.imageView = imageView;
        }
    }

    public frdAdapter(Context context, ArrayList<JSONObject> frds) {
        contextView = LayoutInflater.from(context);
        this.frds = frds;
    }

    @Override
    public int getCount() {
        return frds.size();
    }

    @Override
    public Object getItem(int i) {
        return frds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = contextView.inflate(R.layout.fdrow, null);
            holder = new ViewHolder((TextView) view.findViewById(R.id.LastName),
                    (TextView) view.findViewById(R.id.uid), (ImageView) view.findViewById(R.id.src));

            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        try {


            //frds.get(i).getString("");
            // Member member = members.get(i);
            holder.tvLastName.setText(String.valueOf(frds.get(i).getString("uname")));
            holder.tvUid.setText(frds.get(i).getString("lastName").toString());
            String src = frds.get(i).getString("src").toString();
//holder.imageView


        } catch (Exception ex) {

        }
        return view;
    }
}