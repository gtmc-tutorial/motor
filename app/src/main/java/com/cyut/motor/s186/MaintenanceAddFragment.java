package com.cyut.motor.s186;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cyut.motor.FragmentTag;
import com.cyut.motor.R;

/**
 * Created by wubingyu on 2017/8/24.
 */

public class MaintenanceAddFragment extends Fragment implements View.OnClickListener{
    private android.app.FragmentManager fm;
    private android.app.FragmentTransaction ft;
    private Button oilBtn, suppliesBtn;
    private OilFragment oilFragment;
    private SuppliesFragment suppliesFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_maintenance, container, false);
        oilBtn = (Button)rootView.findViewById(R.id.oil_btn);
        suppliesBtn = (Button)rootView.findViewById(R.id.Supplies_btn);
        oilBtn.setOnClickListener(this);
        suppliesBtn.setOnClickListener(this);
        // 進入系統默認為movie
//        fm = getActivity().getFragmentManager();
//        ft = fm.beginTransaction();
//
//        ft.replace(R.id.fragment_content,new OilFragment());
//        ft.commit();

//        getActivity().getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_content, oilFragment = new OilFragment(), FragmentTag.OilFragment_TAG)
//                .commit();
//
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment_content, suppliesFragment = new SuppliesFragment(), FragmentTag.SUPPLIES_TAG)
//                .commit();
        return rootView;
    }


    @Override
    public void onClick(View view) {
        fm = getActivity().getFragmentManager();
        ft = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.oil_btn:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new OilFragment(), FragmentTag.OilFragment_TAG)
                        .commit();
                break;
            case R.id.Supplies_btn:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_content,new SuppliesFragment(), FragmentTag.SUPPLIES_TAG)
                        .commit();
//                ft.replace(R.id.fragment_content,new SuppliesFragment());
                break;
            default:
                break;
        }
        // 不要忘记提交
        ft.commit();
    }
}
