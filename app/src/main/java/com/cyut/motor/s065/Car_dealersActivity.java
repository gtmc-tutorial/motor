package com.cyut.motor.s065;

import android.content.Intent;
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

public class Car_dealersActivity extends AppCompatActivity {
    Button btn_create, btn_back;
    CTableAdapter.TableCell[] titles;
    ListView listView;
    CTableAdapter CTableAdapter;
    ArrayList<CTableAdapter.TableRow> table = new ArrayList<CTableAdapter.TableRow>();

    public static ArrayList<String> key_array = new ArrayList<>();
    public static ArrayList<PlaceStructure> main_arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_dealers);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(listener1);
        btn_create = findViewById(R.id.btn_create);
        btn_create.setOnClickListener(listener);

        listView = findViewById(R.id.ListView01);
        int width = getWindowManager().getDefaultDisplay().getWidth() / 4;
        titles = new CTableAdapter.TableCell[4];// 每行5个单元
        titles[0] = new CTableAdapter.TableCell("門市", width + 8 * 0, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        titles[1] = new CTableAdapter.TableCell("地址", width + 8 * 1, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        titles[2] = new CTableAdapter.TableCell("刪除", width + 8 * 2, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        titles[3] = new CTableAdapter.TableCell("修改", width + 8 * 2, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        table.add(new CTableAdapter.TableRow(titles));

        CTableAdapter = new CTableAdapter(this, table);
        listView.setAdapter(CTableAdapter);
        listView.setOnItemClickListener(onItemClickListener);

        final Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place/car_dealers");
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                PlaceStructure placeStructure = snapshot.getValue(PlaceStructure.class);
                if (placeStructure != null) {
                    main_arrayList.add(placeStructure);
                    key_array.add(snapshot.getKey());

                    ArrayList<CTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                    arrayList.add(getTableItem(placeStructure.name, placeStructure.add, titles));
                    for (int i = 0; i < arrayList.size(); i++) {
                        table.add(new CTableAdapter.TableRow(arrayList.get(i)));
                    }
                    CTableAdapter.notifyDataSetChanged();
                }
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for (int i = 0; i < key_array.size(); i++) {
                    if (key_array.get(i).equals(dataSnapshot.getKey())) {
                        key_array.remove(i);
                        table.remove(i + 1);
                        CTableAdapter.notifyDataSetChanged();
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
            if (arg2 != 0) {
                new SweetAlertDialog(Car_dealersActivity.this)
                        .setTitleText("機車行資訊")
                        .setContentText("店名：" + main_arrayList.get(arg2 - 1).name + "\n" +
                                "地址：" + main_arrayList.get(arg2 - 1).add + "\n" +
                                "經度：" + main_arrayList.get(arg2 - 1).lng + "\n" +
                                "緯度：" + main_arrayList.get(arg2 - 1).lat + "\n")
                        .show();
            }
        }
    };

    private CTableAdapter.TableCell[] getTableItem(String name, String add, CTableAdapter.TableCell[] titles) {
        CTableAdapter.TableCell[] cells = new CTableAdapter.TableCell[4];

        cells[0] = new CTableAdapter.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        cells[1] = new CTableAdapter.TableCell(add, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, com.cyut.motor.s065.CTableAdapter.TableCell.STRING);
        cells[2] = new CTableAdapter.TableCell(R.drawable.delete, titles[2].width, RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.CTableAdapter.TableCell.IMAGE);
        cells[3] = new CTableAdapter.TableCell(R.drawable.update, titles[3].width, RelativeLayout.LayoutParams.WRAP_CONTENT, com.cyut.motor.s065.CTableAdapter.TableCell.IMAGE_EDIT);
        return cells;
    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Car_dealersActivity.this, Car_createActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Car_dealersActivity.this, BackendActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Car_dealersActivity.this, BackendActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}