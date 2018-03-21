package com.cyut.motor.s065;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.StaticMethodPack;
import com.cyut.motor.Structure.PlaceStructure;
import com.cyut.motor.Structure.UserStructure;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.HashMap;
import java.util.Map;

public class Car_createActivity extends AppCompatActivity {

    private Button button_cancel;
    private EditText edit_name1, edit_add1, edit_lat1, edit_lng1;
    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_create);
        edit_name1 = findViewById(R.id.edit_name1);
        edit_add1 = findViewById(R.id.edit_add1);
        edit_lat1 = findViewById(R.id.edit_lat1);
        edit_lng1 = findViewById(R.id.edit_lng1);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);

        button_cancel = findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(listener1);

        Button button_sure = findViewById(R.id.button_sure);
        button_sure.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(this, "機車行名稱尚未輸入", Toast.LENGTH_SHORT).show();
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

        Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place");
        String key = myFirebaseRef.child("car_dealers").push().getKey();
        PlaceStructure placeStructure = new PlaceStructure(edit_add1, edit_lat1, edit_lng1,edit_name1);
        Map<String, Object> postValues = placeStructure.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/car_dealers/" + key, postValues);
        myFirebaseRef.updateChildren(childUpdates, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Intent i = new Intent(Car_createActivity.this, Car_dealersActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Car_createActivity.this, Car_dealersActivity.class);
            startActivity(intent);
        }
    };
}