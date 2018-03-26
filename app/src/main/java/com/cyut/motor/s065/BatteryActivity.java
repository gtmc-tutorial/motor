package com.cyut.motor.s065;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cyut.motor.R;
import com.cyut.motor.Structure.PlaceStructure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BatteryActivity extends AppCompatActivity {
    Button btn_back,btn_create;
    BTableAdapter.TableCell[] titles;
    ListView listView;
    BTableAdapter BTableAdapter;
    ArrayList<BTableAdapter.TableRow> table = new ArrayList<BTableAdapter.TableRow>();

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<PlaceStructure> main_arrayList = new ArrayList<>();
    private Activity activity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(listener);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(listener1);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth() / 3;
        titles = new BTableAdapter.TableCell[3];// 每行5个单元
        titles[0] = new BTableAdapter.TableCell("門市", width + 8 * 0, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.BTableAdapter.TableCell.STRING);
        titles[1] = new BTableAdapter.TableCell("地址", width + 8 * 1, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.BTableAdapter.TableCell.STRING);
        titles[2] = new BTableAdapter.TableCell("刪除", width + 8 * 2, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.BTableAdapter.TableCell.STRING);
        table.add(new BTableAdapter.TableRow(titles));

        BTableAdapter = new BTableAdapter(this, table);
        listView.setAdapter(BTableAdapter);
        listView.setOnItemClickListener(new ItemClickEvent());

//        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);
        final Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place/battery");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                PlaceStructure placeStructure = snapshot.getValue(PlaceStructure.class);
                if (placeStructure != null) {
                    main_arrayList.add(placeStructure);
                    key_array.add(snapshot.getKey());

                    ArrayList<BTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(placeStructure.name, placeStructure.add, titles));
                    for (int i = 0; i < arrayList.size(); i++) {
                        table.add(new BTableAdapter.TableRow(arrayList.get(i)));
                    }
                    BTableAdapter.notifyDataSetChanged();
                }
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0; i < key_array.size(); i++) {
                    if (key_array.get(i).equals(dataSnapshot.getKey())) {
                        key_array.remove(i);
                        table.remove(i + 1);
                        BTableAdapter.notifyDataSetChanged();
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

    public Activity getActivity() {
        return activity;
    }

    private final class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            if(arg2 != 0 ){
                new SweetAlertDialog(getActivity())
                        .setTitleText(main_arrayList.get(arg2-1).name)
                        .setContentText("店名:"+main_arrayList.get(arg2-1).name+"\n"+
                                "地址:"+ main_arrayList.get(arg2-1).add+"\n"+
                                "經度:" + main_arrayList.get(arg2-1).lat+"\n"+
                                "緯度:" +main_arrayList.get(arg2-1).lng+"\n"
                        ).show();
            }
//            Toast.makeText(getActivity(), "选中第"+String.valueOf(arg2)+"行", Toast.LENGTH_SHORT).show();
        }
    }

    private BTableAdapter.TableCell[] getTableItem(String name, String add, BTableAdapter.TableCell[] titles){
        BTableAdapter.TableCell[] cells = new BTableAdapter.TableCell[3];

        cells[0] = new BTableAdapter.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.BTableAdapter.TableCell.STRING);
        cells[1] = new BTableAdapter.TableCell(add, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.BTableAdapter.TableCell.STRING);
        cells[2] = new BTableAdapter.TableCell(R.drawable.delete,titles[2].width,RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.BTableAdapter.TableCell.IMAGE);
        return cells;
    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BatteryActivity.this, BackendActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BatteryActivity.this, Battery_createActivity.class);
            startActivity(intent);
        }
    };
}