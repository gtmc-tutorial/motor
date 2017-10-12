package com.cyut.motor.s186;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;

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
        EditText editText =  view.findViewById(R.id.editText);
        editText.setText(sharedPreferences.getString("score",""));
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> moneyList = ArrayAdapter.createFromResource(getActivity(),
                R.array.money,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(moneyList);

        Spinner spinner2 = view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> specificationList = ArrayAdapter.createFromResource(getActivity(),
                R.array.specification,
                android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(specificationList);

        EditText dateEditText = view.findViewById(R.id.tvDate);
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