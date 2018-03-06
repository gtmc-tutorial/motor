package com.cyut.motor.s065;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.cyut.motor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WarrantyActivity extends AppCompatActivity {
    private Button btn_backend_back;


    private ListView listView;
    private void _findViews(){listView = findViewById(R.id.list_backend_user);    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty);

        btn_backend_back = findViewById(R.id.btn_backend_back);
        btn_backend_back.setOnClickListener(listener);

        _findViews();
        _setDBdata();
    }

    private void _setDBdata(){
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);
        FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
        DatabaseReference dbRef_user = fbDB.getReference("Warranty");
        dbRef_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren() ){
                    adapter.add(ds.child("day").getValue().toString());
                    adapter.add(ds.child("label").getValue().toString());
                    adapter.add(ds.child("type").getValue().toString());
                    adapter.add(ds.child("trip").getValue().toString());
                    adapter.add(ds.child("price").getValue().toString());
                    adapter.add(ds.child("user_id").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(WarrantyActivity.this, BackendActivity.class);
            startActivity(intent);
        }
    };
}
