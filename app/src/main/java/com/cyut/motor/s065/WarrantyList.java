package com.cyut.motor.s065;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cyut.motor.List.Wist;
import com.cyut.motor.R;

import java.util.List;


public class WarrantyList extends ArrayAdapter<Wist> {

    private Activity context;
    private List<Wist> warrantyList;

    public WarrantyList(Activity context, List<Wist> warrantyList){
        super(context, R.layout.warrantytextview, warrantyList);
        this.context = context;
        this.warrantyList = warrantyList;
    }

    @NonNull
    @Override
    public View getView(int postion, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.warrantytextview, null , true);

        TextView textViewUser_id = (TextView) listViewItem.findViewById(R.id.textViewUser_id);
        TextView textViewDay = (TextView) listViewItem.findViewById(R.id.textViewDay);
        TextView textViewLabel = (TextView) listViewItem.findViewById(R.id.textViewLabel);
        TextView textViewType = (TextView) listViewItem.findViewById(R.id.textViewType);
        TextView textViewTrip = (TextView) listViewItem.findViewById(R.id.textViewTrip);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);

        Wist wist = warrantyList.get(postion);

        textViewUser_id.setText(wist.getuser_id());
        textViewDay.setText(wist.getday());
        textViewLabel.setText(wist.getlabel());
        textViewType.setText(wist.gettype());
        textViewTrip.setText(wist.gettrip());
        textViewPrice.setText(wist.getprice());

        return listViewItem;


    }
}