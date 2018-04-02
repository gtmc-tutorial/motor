package com.cyut.motor.s065;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserActivity extends AppCompatActivity {
    UTableAdapter.TableCell[] titles;
    ListView listView;
    UTableAdapter UTableAdapter;
    ArrayList<UTableAdapter.TableRow> table = new ArrayList<UTableAdapter.TableRow>();
    Button btn_create,btn_back;

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<UserStructure> main_arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_create.setOnClickListener(listener1);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(listener);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth() / 3;
        titles = new UTableAdapter.TableCell[3];// 每行5个单元
        titles[0] = new UTableAdapter.TableCell("暱稱", width + 8 * 0, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.UTableAdapter.TableCell.STRING);
        titles[1] = new UTableAdapter.TableCell("信箱", width + 8 * 1, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.UTableAdapter.TableCell.STRING);
        titles[2] = new UTableAdapter.TableCell("刪除", width + 8 * 2, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.UTableAdapter.TableCell.STRING);
        table.add(new UTableAdapter.TableRow(titles));

        UTableAdapter = new UTableAdapter(this, table);
        listView.setAdapter(UTableAdapter);
        listView.setOnItemClickListener(onItemClickListener);

        final Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/User");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                UserStructure userStructure = snapshot.getValue(UserStructure.class);
                Log.e("name", userStructure.name);
                if (userStructure != null) {
                    Log.e("snapshot", snapshot + "");
                    main_arrayList.add(userStructure);
                    key_array.add(snapshot.getKey());

                    ArrayList<UTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(userStructure.name, userStructure.email, titles));
                    for (int i = 0; i < arrayList.size(); i++) {
                        table.add(new UTableAdapter.TableRow(arrayList.get(i)));
                    }
                    UTableAdapter.notifyDataSetChanged();
                }
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0; i < key_array.size(); i++) {
                    if (key_array.get(i).equals(dataSnapshot.getKey())) {
                        key_array.remove(i);
                        table.remove(i + 1);
                        UTableAdapter.notifyDataSetChanged();
                    }
                }
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    @Override
    public void onStart() {
        key_array = new ArrayList<>();
        super.onStart();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int arg2, long id) {
            if(arg2 != 0 ){
                new SweetAlertDialog(UserActivity.this)
                        .setTitleText("使用者資訊")
                        .setContentText("使用者：" +main_arrayList.get(arg2-1).user_id+"\n"+
                                "暱稱：" + main_arrayList.get(arg2-1).name+"\n"+
                                "信箱：" + main_arrayList.get(arg2-1).email+"\n")
                        .show();
            }
        }
    };

    private UTableAdapter.TableCell[] getTableItem(String name, String email, UTableAdapter.TableCell[] titles){
        UTableAdapter.TableCell[] cells = new UTableAdapter.TableCell[3];

        cells[0] = new UTableAdapter.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.UTableAdapter.TableCell.STRING);
        cells[1] = new UTableAdapter.TableCell(email, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.UTableAdapter.TableCell.STRING);
        cells[2] = new UTableAdapter.TableCell(R.drawable.delete,titles[2].width,RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.UTableAdapter.TableCell.IMAGE);
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
            intent.setClass(UserActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("UserActivity_IN",true);
            intent.setClass(UserActivity.this, BackendActivity.class);
            startActivity(intent);
        }
    };
}