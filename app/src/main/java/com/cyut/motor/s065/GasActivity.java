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
    Firebase myFirebaseRef;
    public static ArrayList<String> key_array = new ArrayList<>();

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

        myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/place/gas");
        myFirebaseRef.addChildEventListener(childEventListener);
    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot snapshot, String s) {
            GasStructure gasStructure = snapshot.getValue(GasStructure.class);
            if(gasStructure != null){
                key_array.add(snapshot.getKey());

                ArrayList<GTableAdapter.TableCell[]> arrayList = new ArrayList<>();
                arrayList.add(getTableItem(gasStructure.name,gasStructure.add,titles));
                for (int i = 0;i<arrayList.size();i++){
                    table.add(new GTableAdapter.TableRow(arrayList.get(i)));
                }

                GTableAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            for (int i = 0 ; i < key_array.size();i++){
                if(key_array.get(i).equals(dataSnapshot.getKey())){
                    key_array.remove(i);
                    table.remove(i+1);
                    GTableAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };

    @Override
    public void onStart() {
        super.onStart();
        key_array = new ArrayList<>();
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