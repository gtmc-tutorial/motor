package com.cyut.motor.s065;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyut.motor.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoEditActivity extends AppCompatActivity {
    private EditText edit_name1, edit_add1, edit_lng1, edit_lat1;
    private Button button_cancel, button_save;
    private DatabaseReference mFirebaseDatabase;
    String mystringdata;
    Firebase myfirebase;

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

        edit_name1.setText(getIntent().getStringExtra("name"));
        edit_add1.setText(getIntent().getStringExtra("add"));
        edit_lng1.setText(getIntent().getStringExtra("lng"));
        edit_lat1.setText(getIntent().getStringExtra("lat"));
        myfirebase = new Firebase("https://motorcycle-cc0fe.firebaseio.com");

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mystringdata = edit_name1.getText().toString();
                Firebase myNewChild = myfirebase.child("name");
                myNewChild.setValue(mystringdata);
                mystringdata = edit_add1.getText().toString();
                Firebase myNewChild1 = myfirebase.child("add");
                myNewChild1.setValue(mystringdata);
                mystringdata = edit_lng1.getText().toString();
                Firebase myNewChild2 = myfirebase.child("lng");
                myNewChild2.setValue(mystringdata);
                mystringdata = edit_lat1.getText().toString();
                Firebase myNewChild3 = myfirebase.child("lat");
                myNewChild3.setValue(mystringdata);
                Toast.makeText(InfoEditActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String result = getIntent().getStringExtra("BTableAdapter");
            String result1 = getIntent().getStringExtra("CTableAdapter");
            String result2 = getIntent().getStringExtra("GTableAdapter");
            if (result != null && result.equals("BTableAdapter")) {
                startActivity(new Intent(InfoEditActivity.this, BatteryActivity.class));
            } else if (result1 != null && result1.equals("CTableAdapter")) {
                startActivity(new Intent(InfoEditActivity.this, Car_dealersActivity.class));
            } else if (result2 != null && result2.equals("GTableAdapter")) {
                startActivity(new Intent(InfoEditActivity.this, GasActivity.class));
            }
        }
    };
}

