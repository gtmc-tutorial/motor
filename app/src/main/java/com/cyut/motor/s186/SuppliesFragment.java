package com.cyut.motor.s186;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cyut.motor.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by aunt on 2017/8/24.
 */

public class SuppliesFragment extends Fragment {

    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10;
    private android.app.FragmentManager fm;
    private android.app.FragmentTransaction ft;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplies, null);
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data" , MODE_PRIVATE);
        //定義View
        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button1.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button2 =(Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button2.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button3 =(Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button3.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button4 =(Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button4.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button5 =(Button) view.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button5.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button6 =(Button) view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button6.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button7 =(Button) view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button7.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button8 =(Button) view.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button8.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button9 =(Button) view.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button9.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });

        button10 =(Button) view.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("in","IN");
                sharedPreferences.edit().putString("score" , button10.getText().toString()).commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new InformationFragment())
                        .commit();
//                fm = getFragmentManager();
//                ft = fm.beginTransaction();
//                ft.replace(R.id.fragment_content,new InformationFragment()).commit();
            }
        });



        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(),"page1",Toast.LENGTH_LONG).show();

    }
}
