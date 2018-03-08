package com.cyut.motor.s065;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cyut.motor.List.Wist;
import com.cyut.motor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snake on 2018/3/8.
 */

public class WarrantyActivity extends AppCompatActivity {

    DatabaseReference databaseWarranty;

    ListView listViewArtists;
    List<Wist> warrantyList;

    Button btn_back,btn_search;
    EditText testSearch;

    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty);

        databaseWarranty = FirebaseDatabase.getInstance().getReference("Warranty");

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(listener);
        btn_search = (Button)findViewById(R.id.btn_search);

        testSearch = (EditText)findViewById(R.id.testSearch);

        listViewArtists = (ListView) findViewById(R.id.listViewArtists);
        warrantyList = new ArrayList<>();

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = testSearch.getText().toString();
                firebaseUserSearch(searchText);

            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(WarrantyActivity.this, "Started Search", Toast.LENGTH_LONG).show();
        Query firebaseSearchQuery = databaseWarranty.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseWarranty.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                warrantyList.clear();

                for (DataSnapshot wistSnapshot : dataSnapshot.getChildren()) {
                    Wist wist = wistSnapshot.getValue(Wist.class);

                    warrantyList.add(wist);
                }

                WarrantyList adapter = new WarrantyList(WarrantyActivity.this, warrantyList);
                listViewArtists.setAdapter(adapter);

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
