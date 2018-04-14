package com.cyut.motor.s065;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyut.motor.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoEditActivity extends AppCompatActivity {
    private EditText edit_name1,edit_add1,edit_lng1,edit_lat1;
    private Button button_cancel,button_save;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoedit);

        edit_name1 = findViewById(R.id.edit_name1);
        edit_add1 = findViewById(R.id.edit_add1);
        edit_lng1 = findViewById(R.id.edit_lng1);
        edit_lat1 = findViewById(R.id.edit_lat1);
        button_save = findViewById(R.id.button_save);
        button_cancel = findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(listener);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name1.getText().toString();
                String add = edit_add1.getText().toString();
                String lng = edit_lng1.getText().toString();
                String lat = edit_lat1.getText().toString();

                mFirebaseDatabase.child("name").setValue(name);
                mFirebaseDatabase.child("add").setValue(add);
                mFirebaseDatabase.child("lng").setValue(lng);
                mFirebaseDatabase.child("lat").setValue(lat);
            }
        });

    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(InfoEditActivity.this, BatteryActivity.class);
            startActivity(intent);
        }
    };
}
