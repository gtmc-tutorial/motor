package com.cyut.motor.s186;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.Structure.MaintainStructure;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * Created by wubingyu on 2017/8/24.
 */

public class MaintenanceFragment extends Fragment {
    TableAdapter.TableCell[] titles;
    ListView listView;
    TableAdapter tableAdapter;
    ArrayList<TableAdapter.TableRow> table;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maintenance, container, false);
        Button nextPageBtn = (Button)rootView.findViewById(R.id.button);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).chageFragment("新增保養");
            }
        });
        Log.e("onCreateView","onCreateView");

        listView = (ListView) rootView.findViewById(R.id.ListView01);
        table = new ArrayList<TableAdapter.TableRow>();
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth()/3;
        titles = new TableAdapter.TableCell[3];// 每行5个单元
        titles[0] = new TableAdapter.TableCell("類型",width + 8 * 0,RelativeLayout.LayoutParams.FILL_PARENT,TableAdapter.TableCell.STRING);
        titles[1] = new TableAdapter.TableCell("時間",width + 8 * 1,RelativeLayout.LayoutParams.FILL_PARENT,TableAdapter.TableCell.STRING);
        titles[2] = new TableAdapter.TableCell("刪除",width + 8 * 2,RelativeLayout.LayoutParams.FILL_PARENT,TableAdapter.TableCell.STRING);
        table.add(new TableAdapter.TableRow(titles));

        // 每行的数据


        // 把表格的行添加到表格
//        for (int i = 0; i < 12; i++){
//            table.add(new TableAdapter.TableRow(cells));
//        }
        tableAdapter = new TableAdapter(getContext(), table);
        listView.setAdapter(tableAdapter);
        listView.setOnItemClickListener(new ItemClickEvent());


        return rootView;
    }

    @Override
    public void onStart() {
        Log.e("start","start");
        Firebase myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/Warranty");
        Query queryRef = myFirebaseRef.orderByChild("user_id");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                snapshot.toString();
                MaintainStructure maintainStructure = snapshot.getValue(MaintainStructure.class);
                String day = maintainStructure.day;

                ArrayList<TableAdapter.TableCell[]> arrayList = new ArrayList<>();
                arrayList.add(getTableItem(maintainStructure.Oil_oil,maintainStructure.day,titles));
                for (int i = 0;i<arrayList.size();i++){
                    table.add(new TableAdapter.TableRow(arrayList.get(i)));

                }
//                Log.e("day",day);
                Log.e("in","in");
                tableAdapter.notifyDataSetChanged();
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        super.onStart();
    }
    class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

            Toast.makeText(getActivity(), "选中第"+String.valueOf(arg2)+"行", Toast.LENGTH_SHORT).show();
        }
    }

    private TableAdapter.TableCell[] getTableItem(String name, String date, TableAdapter.TableCell[] titles){
        TableAdapter.TableCell[] cells = new TableAdapter.TableCell[3];

        cells[0] = new TableAdapter.TableCell(name, titles[0].width, RelativeLayout.LayoutParams.FILL_PARENT, TableAdapter.TableCell.STRING);
        cells[1] = new TableAdapter.TableCell(date, titles[1].width, RelativeLayout.LayoutParams.FILL_PARENT, TableAdapter.TableCell.STRING);

        cells[2] = new TableAdapter.TableCell(R.drawable.delete,
                titles[2].width,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                TableAdapter.TableCell.IMAGE);
        return cells;
    }
}
