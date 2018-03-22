package com.cyut.motor.s065;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cyut.motor.R;
import com.cyut.motor.Structure.GasStructure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class GasActivity extends AppCompatActivity {
    Button btn_back,btn_create;
    GTableAdapter.TableCell[] titles;
    ListView listView;
    GTableAdapter GTableAdapter;
    ArrayList<GTableAdapter.TableRow> table = new ArrayList<GTableAdapter.TableRow>();

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<GasStructure> main_arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(listener);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(listener1);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth()/3;
        titles = new GTableAdapter.TableCell[3];// 每行5个单元
        titles[0] = new GTableAdapter.TableCell("門市",width + 8 * 0,RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.GTableAdapter.TableCell.STRING);
        titles[1] = new GTableAdapter.TableCell("地址",width + 8 * 1,RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.GTableAdapter.TableCell.STRING);
        titles[2] = new GTableAdapter.TableCell("刪除",width + 8 * 2,RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.GTableAdapter.TableCell.STRING);
        table.add(new GTableAdapter.TableRow(titles));

        GTableAdapter = new GTableAdapter(this, table);
        listView.setAdapter(GTableAdapter);

        final SharedPreferences sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);
        final Firebase myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place/gas");
//        myFirebaseRef.add
//        Query queryRef = myFirebaseRef.orderByChild("email");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                GasStructure gasStructure = snapshot.getValue(GasStructure.class);
                if(gasStructure != null){
                    main_arrayList.add(gasStructure);
                    Log.e("key",snapshot.getKey());
                    key_array.add(snapshot.getKey());

                    ArrayList<GTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(gasStructure.name,gasStructure.add,titles));
                    for (int i = 0;i<arrayList.size();i++){
                        table.add(new GTableAdapter.TableRow(arrayList.get(i)));
                    }

                    GTableAdapter.notifyDataSetChanged();
                }
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0 ; i < key_array.size();i++){
                    if(key_array.get(i).equals(dataSnapshot.getKey())){
                        key_array.remove(i);
                        table.remove(i+1);
                        GTableAdapter.notifyDataSetChanged();

                    }
                }
                GasStructure gasStructure = dataSnapshot.getValue(GasStructure.class);
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            public void onCancelled(FirebaseError firebaseError) {

            }

        });

        final Firebase FirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/");
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0 ;i<key_array.size();i++){
                    final int finalI1 = i;
                    if(GTableAdapter.imageViews.size() != 0){
                        GTableAdapter.imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseRef.child("gas").orderByKey().equalTo(key_array.get(finalI1));
                                Query applesQuery = FirebaseRef.child("gas").orderByChild("title").equalTo("Apple");
                                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                            appleSnapshot.getRef().removeValue();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });
                            }
                        });
                    }

                }
            }
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }

    @Override
    public void onStart() {
        Log.e("Start","start");

        super.onStart();
    }

    private GTableAdapter.TableCell[] getTableItem(String name, String add, GTableAdapter.TableCell[] titles){
        GTableAdapter.TableCell[] cells = new GTableAdapter.TableCell[3];

        cells[0] = new GTableAdapter.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.GTableAdapter.TableCell.STRING);
        cells[1] = new GTableAdapter.TableCell(add, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.GTableAdapter.TableCell.STRING);
        cells[2] = new GTableAdapter.TableCell(R.drawable.delete,titles[2].width,RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.GTableAdapter.TableCell.IMAGE);
        return cells;
    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(GasActivity.this, BackendActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(GasActivity.this, Gas_createActivity.class);
            startActivity(intent);
        }
    };

}