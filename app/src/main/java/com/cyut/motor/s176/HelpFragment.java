package com.cyut.motor.s176;

import android.content.res.Resources;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cyut.motor.R;
import com.cyut.motor.s176.pickview.CharacterPickerView;
import com.cyut.motor.s176.pickview.OnOptionChangedListener;


/**
 * Created by aunt on 2017/8/24.
 */

public class HelpFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout layout = new RelativeLayout(getActivity());

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2Px(250));
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        CharacterPickerView pickerView = new CharacterPickerView(getActivity());
        layout.addView(pickerView, layoutParams);

        //初始化选项数据
        OptionsWindowHelper.setPickerData(pickerView);
        //初始化选中项
        //pickerView.setCurrentItems(1, 2, 3);

        //设置监听事件
        pickerView.setOnOptionChangedListener(new OnOptionChangedListener() {
            @Override
            public void onOptionChanged(int option1, int option2, int option3) {
                Log.e("test", option1 + "," + option2 + "," + option3);
            }
        });

        View view = layout;

//        final String[] lunch = {"基隆市", "台北市", "新北市", "桃園市", "桃園縣", "新竹市", "新竹縣", "苗栗縣", "台中市", "彰化縣", "南投縣", "雲林縣", "嘉義市", "嘉義縣", "台南市", "高雄市", "屏東縣", "台東縣", "花蓮縣", "宜蘭縣"};
//        ArrayAdapter<String> lunchList = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_spinner_dropdown_item,
//                lunch);
//        Spinner spinner =(Spinner) view.findViewById(R.id.spinner);
//        spinner.setAdapter(lunchList);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(getActivity(), "你選的是" + lunch[position], Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        Spinner spinner1 = (Spinner)view.findViewById(R.id.spinner1);
//        final String[] dinner = {"燒肉飯", "魯肉飯", "排骨飯", "湯餃", "肉羹麵"};
//        ArrayAdapter<String> dinnerList = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_spinner_dropdown_item,
//                dinner);
//        spinner1.setAdapter(dinnerList);
//
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(getActivity(), "你選的是" + dinner[position], Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        return view;
    }



    public static int dp2Px(float dp) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


}