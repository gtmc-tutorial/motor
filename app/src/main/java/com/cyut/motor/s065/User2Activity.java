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
import com.cyut.motor.Structure.UserStructure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class User2Activity extends AppCompatActivity {
    TableAdapter2.TableCell[] titles;
    ListView listView;
    TableAdapter2 tableAdapter2;
    ArrayList<TableAdapter2.TableRow> table = new ArrayList<TableAdapter2.TableRow>();
    Button btn_create;

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<UserStructure> main_arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maintenance2);
        btn_create = (Button)findViewById(R.id.btn_create);
        btn_create.setOnClickListener(listener1);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth()/3;
        titles = new TableAdapter2.TableCell[3];// 每行5个单元
        titles[0] = new TableAdapter2.TableCell("暱稱",width + 8 * 0,RelativeLayout.LayoutParams.FILL_PARENT,TableAdapter2.TableCell.STRING);
        titles[1] = new TableAdapter2.TableCell("信箱",width + 8 * 1,RelativeLayout.LayoutParams.FILL_PARENT,TableAdapter2.TableCell.STRING);
        titles[2] = new TableAdapter2.TableCell("刪除",width + 8 * 2,RelativeLayout.LayoutParams.FILL_PARENT,TableAdapter2.TableCell.STRING);
        table.add(new TableAdapter2.TableRow(titles));

        tableAdapter2 = new TableAdapter2(this, table);
        listView.setAdapter(tableAdapter2);

        final SharedPreferences sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);
        final Firebase myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/User");
//        myFirebaseRef.add
//        Query queryRef = myFirebaseRef.orderByChild("email");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                UserStructure userStructure = snapshot.getValue(UserStructure.class);
                Log.e("name",userStructure.name);
                if(userStructure != null){
                    Log.e("snapshot",snapshot+"");
                    main_arrayList.add(userStructure);
                    key_array.add(snapshot.getKey());

                    ArrayList<TableAdapter2.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(userStructure.name,userStructure.email,titles));
                    for (int i = 0;i<arrayList.size();i++){
                        table.add(new TableAdapter2.TableRow(arrayList.get(i)));
                    }

                    tableAdapter2.notifyDataSetChanged();
                }
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0 ; i < key_array.size();i++){
                    if(key_array.get(i).equals(dataSnapshot.getKey())){
                        key_array.remove(i);
                        table.remove(i+1);
                        tableAdapter2.notifyDataSetChanged();

                    }
                }
                UserStructure userStructure = dataSnapshot.getValue(UserStructure.class);
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
                    if(tableAdapter2.imageViews.size() != 0){
                        tableAdapter2.imageViews.get(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseRef.child("User").orderByKey().equalTo(key_array.get(finalI1));
                                Query applesQuery = FirebaseRef.child("User").orderByChild("title").equalTo("Apple");
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

    private TableAdapter2.TableCell[] getTableItem(String name, String email, TableAdapter2.TableCell[] titles){
        TableAdapter2.TableCell[] cells = new TableAdapter2.TableCell[3];

        cells[0] = new TableAdapter2.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, TableAdapter2.TableCell.STRING);
        cells[1] = new TableAdapter2.TableCell(email, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, TableAdapter2.TableCell.STRING);
        cells[2] = new TableAdapter2.TableCell(R.drawable.delete,titles[2].width,RelativeLayout.LayoutParams.WRAP_CONTENT,TableAdapter2.TableCell.IMAGE);
        return cells;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("UserActivity_IN",true);
            intent.setClass(User2Activity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };
}