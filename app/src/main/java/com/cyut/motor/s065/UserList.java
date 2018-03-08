package com.cyut.motor.s065;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cyut.motor.List.Artist;
import com.cyut.motor.R;

import java.util.List;


public class UserList extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> userList;

    public UserList(Activity context, List<Artist> userList){
        super(context, R.layout.usertextview, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int postion, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.usertextview, null , true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewEmail);

        Artist artist = userList.get(postion);

        textViewName.setText(artist.getName());
        textViewGenre.setText(artist.getEmail());

        return listViewItem;


    }
}