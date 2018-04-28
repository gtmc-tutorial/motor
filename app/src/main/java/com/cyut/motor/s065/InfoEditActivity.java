package com.cyut.motor.s065;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyut.motor.R;
import com.cyut.motor.Structure.PlaceStructure;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;


public class InfoEditActivity extends AppCompatActivity {
    private EditText edit_name1, edit_add1, edit_lng1, edit_lat1;
    private Button button_cancel, button_save;
    Firebase myfirebase;
    String key2;

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
        key2 = getIntent().getStringExtra("key");
        myfirebase = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place");

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost(
                        edit_name1.getText().toString(),
                        edit_add1.getText().toString(),
                        edit_lat1.getText().toString(),
                        edit_lng1.getText().toString());
            }
        });
    }

    private void writeNewPost(String edit_name1, String edit_add1, String edit_lat1, String edit_lng1) {
        if (edit_name1.equals("")){
            Toast.makeText(this, "名稱尚未輸入", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edit_add1.equals("")) {
            Toast.makeText(this, "地址尚未輸入", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edit_lat1.equals("")) {
            Toast.makeText(this, "經度尚未輸入", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edit_lng1.equals("")) {
            Toast.makeText(this, "緯度尚未輸入", Toast.LENGTH_SHORT).show();
            return;
        }
        Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place/battery");
        String key = myFirebaseRef.child("battery").push().getKey();
        PlaceStructure placeStructure = new PlaceStructure(edit_add1, edit_lat1, edit_lng1,edit_name1);
        Map<String, Object> postValues = placeStructure.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/battery/" + key, postValues);
        myFirebaseRef.updateChildren(childUpdates, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Intent i = new Intent(InfoEditActivity.this, BatteryActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
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

