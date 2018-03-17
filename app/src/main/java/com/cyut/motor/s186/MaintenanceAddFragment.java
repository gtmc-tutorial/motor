package com.cyut.motor.s186;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cyut.motor.Structure.FragmentTag;
import com.cyut.motor.R;

/**
 * Created by wubingyu on 2017/8/24.
 */

public class MaintenanceAddFragment extends Fragment implements View.OnClickListener{
    private Button oilBtn, suppliesBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_maintenance, container, false);
        oilBtn = rootView.findViewById(R.id.oil_btn);
        suppliesBtn = rootView.findViewById(R.id.Supplies_btn);
        oilBtn.setOnClickListener(this);
        suppliesBtn.setOnClickListener(this);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_content_layout,new OilFragment(), FragmentTag.OilFragment_TAG)
                .commit();

        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.oil_btn:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content_layout,new OilFragment(), FragmentTag.OilFragment_TAG)
                        .commit();
                break;
            case R.id.Supplies_btn:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content_layout,new SuppliesFragment(), FragmentTag.SUPPLIES_TAG)
                        .commit();
//                ft.replace(R.id.fragment_content,new SuppliesFragment());
                break;
            default:
                break;
        }
    }
}
