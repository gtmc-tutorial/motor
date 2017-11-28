package com.cyut.motor.s134;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;

import java.util.Calendar;

/**
 * Created by hipsre720 on 2017/8/17.
 */

public class SettingFragment extends Fragment {
    Button notice;
    Button stole;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_setting, null);

        stole=(Button)view.findViewById(R.id.stole);
        notice=(Button)view.findViewById(R.id.notice);
        long time=System.currentTimeMillis();
        final Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int mYear=mCalendar.get(Calendar.YEAR);
        int mMonth=mCalendar.get(Calendar.MONTH);
        int mDate=mCalendar.get(Calendar.DATE);
        int date=mYear*365+mMonth*30+mDate;
        Log.e("date",date+"");
        SharedPreferences dateSharedPreferences = getActivity().getSharedPreferences("date_", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = dateSharedPreferences.edit();
        editor.putString("date",String.valueOf(date));
        editor.commit();
        SharedPreferences iSharedPreferences = getActivity().getSharedPreferences("test",Activity.MODE_PRIVATE);
        String ii = iSharedPreferences.getString("test","");
        Log.e("test",ii+"");

        notice.setOnClickListener(new Button.OnClickListener(){
            public void onClick (View v){
                new AlertDialog.Builder(getActivity()).setTitle("請選擇題醒時間").setSingleChoiceItems(
                        new String[]{"1週","2週","3週","1個月"},0,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.e("i",i+"");
                                SharedPreferences mySharedPreferences= getActivity().getSharedPreferences("data_",Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mySharedPreferences.edit();
                                editor.putString("name",String.valueOf(i));
                                editor.commit();
                                if(i==0) {
                                    //                                    //Start polling service
                                    System.out.println("Start polling service...");
                                    PollingUtils.startPollingService(getActivity(), 5, PollingService.class, PollingService.ACTION);
                                }
                                else if(i==1){
                                    //Start polling service
                                    System.out.println("Start polling service...");
                                    PollingUtils.startPollingService(getActivity(), 1209600, PollingService.class, PollingService.ACTION);
                                }
                                else if(i==2){
                                    //Start polling service
                                    System.out.println("Start polling service...");
                                    PollingUtils.startPollingService(getActivity(), 1814400, PollingService.class, PollingService.ACTION);

                                }
                                else if(i==3){
                                    //Start polling service
                                    System.out.println("Start polling service...");
                                    PollingUtils.startPollingService(getActivity(), 2419200, PollingService.class, PollingService.ACTION);
                                }
                            }
                            public void Onclick(DialogInterface dialog , int which){
                                dialog.dismiss();
                            }
                        }
                ).setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //定義時間格式
                    }
                }).setNegativeButton("取消",null).show();

            }});
        stole.setOnClickListener(new View.OnClickListener() {

//            protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                setContentView(R.layout.dialog_setting);
//                Spinner spinner = (Spinner)findViewById(R.id.spinner);
//                ArrayAdapter<CharSequence> kind = ArrayAdapter.createFromResource(getActivity(),
//                        R.array.kind,
//                        android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(kind);
//            }

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view = (RelativeLayout) inflater.inflate(R.layout.dialog_setting,null);

                builder.setTitle("請輸入查詢的種類與車牌")
                        .setView(view)
                        .setPositiveButton("確定",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){

                            }
                        }
                 ).setNegativeButton("取消",null);
                Spinner spinner = (Spinner) view.findViewById(R.id.dialog_spinner);
                ArrayAdapter<CharSequence> kind = ArrayAdapter.createFromResource(getActivity(),
                        R.array.kind,
                        android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(kind);
                AlertDialog dialog =builder.create();
                dialog.show();
            }
        });


        return view;

    }
}
