package com.cyut.motor.s186;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;

/**
 * Created by wubingyu on 2017/8/24.
 */

public class MaintenanceFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maintenance, container, false);
        Button nextPageBtn = (Button)rootView.findViewById(R.id.button);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getContext()).chageFragment("新增保養");
            }
        });
        return rootView;
    }


}
