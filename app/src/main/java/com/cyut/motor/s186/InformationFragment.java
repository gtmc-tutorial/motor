package com.cyut.motor.s186;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.Structure.MaintainStructure;
import com.cyut.motor.Structure.MapStructure;
import com.cyut.motor.s014.MyItem;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.maps.model.LatLng;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by aunt on 2017/8/24.
 */

public class InformationFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, null);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data" , MODE_PRIVATE);
        EditText editText =(EditText)  view.findViewById(R.id.editText);
        editText.setText(sharedPreferences.getString("score",""));
        Spinner spinner =(Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> moneyList = ArrayAdapter.createFromResource(getActivity(),
                R.array.money,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(moneyList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                Log.e("test",((TextView)view).getText().toString());

                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Add new category"))
                {
                    // do your stuff
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        Spinner spinner2 =(Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> specificationList = ArrayAdapter.createFromResource(getActivity(),
                R.array.specification,
                android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(specificationList);

        EditText dateEditText =(EditText) view.findViewById(R.id.tvDate);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);

            }
        });
        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });


        return view;
    }

    @Override
    public void onStart() {

        Firebase myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/Warranty");
        Query queryRef = myFirebaseRef.orderByChild("user_id");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Log.e("snapshot",snapshot.toString());
                snapshot.toString();
                MaintainStructure maintainStructure = snapshot.getValue(MaintainStructure.class);

//                String day = maintainStructure.day;
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e("onChildChanged","onChildChanged");
            }
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e("onChildRemoved","onChildRemoved");
            }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.e("onChildMoved","onChildMoved");
            }
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("onCancelled","onCancelled");
            }
        });




        super.onStart();
    }

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bData = new Bundle();
        bData.putInt("view", v.getId());
        EditText button = (EditText) v;
        bData.putString("date", button.getText().toString());
        newFragment.setArguments(bData);
        newFragment.show(((MainActivity)getContext()).getSupportFragmentManager(), "日期挑選器");
    }
}