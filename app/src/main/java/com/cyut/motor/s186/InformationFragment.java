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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.Structure.MaintainStructure;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by aunt on 2017/8/24.
 */

public class InformationFragment extends Fragment {
    String type ;
    String lable;
    int price = 0;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_information, null);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data" , MODE_PRIVATE);
        EditText editText =(EditText)  view.findViewById(R.id.editText);
        editText.setText(sharedPreferences.getString("score",""));
        Spinner spinner =(Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> moneyList = ArrayAdapter.createFromResource(getActivity(),
                R.array.money,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(moneyList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                if(position != 0)
                    price = Integer.parseInt(parent.getItemAtPosition(position).toString());
                else
                    price = 0;
            }
            public void onNothingSelected(AdapterView<?> parent)
            {}
        });

        Spinner spinner2 =(Spinner) view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> specificationList = ArrayAdapter.createFromResource(getActivity(),R.array.specification,android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(specificationList);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lable = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        final TextView dateEditText =(TextView) view.findViewById(R.id.tvDate);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        Button button = (Button) view.findViewById(R.id.button_sure);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String date = dateEditText.getText().toString();
                String labele = ((EditText)view.findViewById(R.id.editText)).getText().toString();
//                String labele = ((TextView)view.findViewById(R.id.TextView1)).getText().toString();
                writeNewPost(date,lable,price,
                        ((EditText)view.findViewById(R.id.editText4)).getText().toString(),
                        ((EditText) view.findViewById(R.id.editText)).getText().toString(),
                        getActivity().getSharedPreferences("Data",0).getString("user_id",""));
            }
        });

        return view;
    }

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle bData = new Bundle();
        bData.putInt("view", v.getId());
        TextView button = (TextView) v;
        bData.putString("date", button.getText().toString());
        newFragment.setArguments(bData);
        newFragment.show(((MainActivity)getContext()).getSupportFragmentManager(), "日期挑選器");
    }

    private void writeNewPost(String day, String lable, int price, String trip,String type,String userId) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously

        Firebase myFirebaseRef  = new Firebase("https://motorcycle-cc0fe.firebaseio.com/");
        String key = myFirebaseRef.child("Warranty").push().getKey();
        MaintainStructure maintainStructure = new MaintainStructure(day, lable, price, trip,type,userId);
        Map<String, Object> postValues = maintainStructure.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Warranty/" + key, postValues);
        myFirebaseRef.updateChildren(childUpdates, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {

            }
        });

    }
}