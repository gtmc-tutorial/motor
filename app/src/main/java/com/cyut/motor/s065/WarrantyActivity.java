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
import com.cyut.motor.Structure.MaintainStructure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class WarrantyActivity extends AppCompatActivity {
    Button btn_back;
    WTableAdapter.TableCell[] titles;
    ListView listView;
    WTableAdapter WTableAdapter;
    ArrayList<WTableAdapter.TableRow> table = new ArrayList<WTableAdapter.TableRow>();

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<MaintainStructure> main_arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(listener);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth() / 3;
        titles = new WTableAdapter.TableCell[3];// 每行5个单元
        titles[0] = new WTableAdapter.TableCell("日期", width + 8 * 0, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.WTableAdapter.TableCell.STRING);
        titles[1] = new WTableAdapter.TableCell("類型", width + 8 * 1, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.WTableAdapter.TableCell.STRING);
        titles[2] = new WTableAdapter.TableCell("刪除", width + 8 * 2, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.WTableAdapter.TableCell.STRING);
        table.add(new WTableAdapter.TableRow(titles));

        WTableAdapter = new WTableAdapter(this, table);
        listView.setAdapter(WTableAdapter);
        listView.setOnItemClickListener(onItemClickListener);

        final Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/Warranty");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                MaintainStructure maintainStructure = snapshot.getValue(MaintainStructure.class);
                if (maintainStructure != null) {
                    main_arrayList.add(maintainStructure);
                    key_array.add(snapshot.getKey());

                    ArrayList<WTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(maintainStructure.day, maintainStructure.type, titles));
                    for (int i = 0; i < arrayList.size(); i++) {
                        table.add(new WTableAdapter.TableRow(arrayList.get(i)));
                    }
                    WTableAdapter.notifyDataSetChanged();
                }
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0; i < key_array.size(); i++) {
                    if (key_array.get(i).equals(dataSnapshot.getKey())) {
                        key_array.remove(i);
                        table.remove(i + 1);
                        WTableAdapter.notifyDataSetChanged();
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
        super.onStart();
        key_array = new ArrayList<>();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int arg2, long id) {
            if(arg2 != 0 ){
                new SweetAlertDialog(WarrantyActivity.this)
                        .setTitleText("保養紀錄資訊")
                        .setContentText("使用者：" +main_arrayList.get(arg2-1).user_id+"\n"+
                                "日期："+ main_arrayList.get(arg2-1).day+"\n"+
                                "廠牌："+ main_arrayList.get(arg2-1).label+"\n"+
                                "類型："+ main_arrayList.get(arg2-1).type+"\n"+
                                "價錢："+ main_arrayList.get(arg2-1).price+"\n"+
                                "公里數："+ main_arrayList.get(arg2-1).trip+"\n")
                        .show();
            }
        }
    };

    private WTableAdapter.TableCell[] getTableItem(String day, String type, WTableAdapter.TableCell[] titles) {
        WTableAdapter.TableCell[] cells = new WTableAdapter.TableCell[3];

        cells[0] = new WTableAdapter.TableCell(day, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.WTableAdapter.TableCell.STRING);
        cells[1] = new WTableAdapter.TableCell(type, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.WTableAdapter.TableCell.STRING);
        cells[2] = new WTableAdapter.TableCell(R.drawable.delete, titles[2].width, RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.WTableAdapter.TableCell.IMAGE);
        return cells;
    }

    @Override
    public void onResume() {
        super.onResume();
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