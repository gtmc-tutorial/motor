package com.cyut.motor.s186;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyut.motor.R;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by wubingyu on 2017/11/28.
 */

public class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Holder holder;
        if(v == null){
            v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, null);
            holder = new Holder();
            holder.image = (ImageView) v.findViewById(R.id.image);
            holder.text = (TextView) v.findViewById(R.id.text);

            v.setTag(holder);
        } else{
            holder = (Holder) v.getTag();
        }
        switch(position) {
            case 0:
                holder.image.setImageResource(R.drawable.cat);
                holder.text.setText("cat");
                break;
            case 1:
                holder.image.setImageResource(R.drawable.monkey);
                holder.text.setText("monkey");
                break;
            case 2:
                holder.image.setImageResource(R.drawable.panda);
                holder.text.setText("panda");
                break;
        }
        return v;
    }
    class Holder{
        ImageView image;
        TextView text;
    }
}