package com.cyut.motor.s065;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cyut.motor.R;
import com.cyut.motor.Structure.PlaceStructure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Car_dealersActivity extends AppCompatActivity {
    CTableAdapter.TableCell[] titles;
    ListView listView;
    CTableAdapter CTableAdapter;
    DatabaseReference myFirebaseRefRef;
    ArrayList<CTableAdapter.TableRow> table = new ArrayList<CTableAdapter.TableRow>();

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<PlaceStructure> main_arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_dealers);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth()/3;
        titles = new CTableAdapter.TableCell[3];// 每行5个单元
        titles[0] = new CTableAdapter.TableCell("門市",width + 8 * 0,RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        titles[1] = new CTableAdapter.TableCell("地址",width + 8 * 1,RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        titles[2] = new CTableAdapter.TableCell("刪除",width + 8 * 2,RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        table.add(new CTableAdapter.TableRow(titles));

        CTableAdapter = new CTableAdapter(this, table);
        listView.setAdapter(CTableAdapter);

        final SharedPreferences sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);
        final Firebase myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/Place");
//        myFirebaseRef.add
//        Query queryRef = myFirebaseRef.orderByChild("email");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                PlaceStructure placeStructure = snapshot.getValue(PlaceStructure.class);
                Log.e("name",placeStructure.name);
                if(placeStructure != null){
                    Log.e("snapshot",snapshot+"");
                    main_arrayList.add(placeStructure);
                    key_array.add(snapshot.getKey());

                    ArrayList<CTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(placeStructure.name,placeStructure.add,titles));
                    for (int i = 0;i<arrayList.size();i++){
                        table.add(new CTableAdapter.TableRow(arrayList.get(i)));
                    }

                    CTableAdapter.notifyDataSetChanged();
                }
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0 ; i < key_array.size();i++){
                    if(key_array.get(i).equals(dataSnapshot.getKey())){
                        key_array.remove(i);
                        table.remove(i+1);
                        CTableAdapter.notifyDataSetChanged();

                    }
                }
                PlaceStructure placeStructure = dataSnapshot.getValue(PlaceStructure.class);
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
                    if(CTableAdapter.imageViews.size() != 0){
                        CTableAdapter.imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseRef.child("Car_dealers").orderByKey().equalTo(key_array.get(finalI1));
                                Query applesQuery = FirebaseRef.child("Car_dealers").orderByChild("title").equalTo("Apple");
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

    private CTableAdapter.TableCell[] getTableItem(String name, String add, CTableAdapter.TableCell[] titles){
        CTableAdapter.TableCell[] cells = new CTableAdapter.TableCell[3];

        cells[0] = new CTableAdapter.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        cells[1] = new CTableAdapter.TableCell(add, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        cells[2] = new CTableAdapter.TableCell(R.drawable.delete,titles[2].width,RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.CTableAdapter.TableCell.IMAGE);
        return cells;
    }

}