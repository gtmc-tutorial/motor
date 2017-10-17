package com.cyut.motor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cyut.motor.Structure.FragmentTag;
import com.cyut.motor.s065.LoginActivity;
import com.cyut.motor.s186.MaintenanceAddFragment;
import com.cyut.motor.s192.HomeFragment;
import com.cyut.motor.s186.MaintenanceFragment;
import com.cyut.motor.s014.mapFragment;
import com.cyut.motor.s134.SettingFragment;
import com.cyut.motor.R;


public class MainActivity extends FragmentActivity implements View.OnClickListener  {
    private ImageView movieBtn, tvBtn,animeBtn, varietyBtn,abcBtn,movieBtn2,tvBtn2,animeBtn2;

    private ImageView btn_user;

    private android.app.FragmentManager fm;
    private android.app.FragmentTransaction ft;
    private MaintenanceFragment mMapFragment;

    private HomeFragment homeFragment;
    private MaintenanceFragment maintenanceFragment;
    private MaintenanceAddFragment maintenanceAddFragment;
    private mapFragment mapFragment;
    private SettingFragment fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, homeFragment = new HomeFragment(),FragmentTag.FRAGEMENT1)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, maintenanceFragment = new MaintenanceFragment(), FragmentTag.MAINTENANCE_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, mapFragment = new mapFragment(), FragmentTag.MAP_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, fragment4 = new SettingFragment(),FragmentTag.FRAGEMENT4)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, maintenanceAddFragment = new MaintenanceAddFragment(), FragmentTag.MAINTENANCEADD_TAG)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(fragment4).hide(maintenanceAddFragment)
                .commit();

        // 進入系統默認為movie
//        fm = getFragmentManager();
//        ft = fm.beginTransaction();

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_content, new mapFragment())
//                .commit();
//        ft.replace(R.id.fragment_content,new mapFragment());
//        ft.commit();
    }
    private void findById() {

        movieBtn = (ImageView)this.findViewById(R.id.movie_btn);
        tvBtn = (ImageView)this.findViewById(R.id.tv_btn);
        animeBtn = (ImageView) this.findViewById(R.id.anime_btn);
        varietyBtn = (ImageView)this.findViewById(R.id.variety_btn);
        abcBtn = (ImageView)this.findViewById(R.id.setting_btn);
        btn_user = (ImageView)findViewById(R.id.btn_user);
        btn_user.setOnClickListener(listener);

        movieBtn.setOnClickListener(this);
        tvBtn.setOnClickListener(this);
        animeBtn.setOnClickListener(this);
        varietyBtn.setOnClickListener(this);
        abcBtn.setOnClickListener(this);
    }

//    private void setBackgroundColorById(int btnId) {
//        for (Button btn : btnList) {
//            if (btn.getId() == btnId){
//                btn.setBackgroundColor(Color.GREEN);
//            }else {
//                btn.setBackgroundColor(Color.BLUE);
//            }
//        }
//    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated methodstub
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.movie_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(fragment4).hide(maintenanceAddFragment)
                        .commit();
                break;
            case R.id.tv_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(maintenanceFragment).hide(homeFragment).hide(mapFragment).hide(fragment4).hide(maintenanceAddFragment)
                        .commit();
                break;
            case R.id.anime_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(mapFragment).hide(homeFragment).hide(maintenanceFragment).hide(fragment4).hide(maintenanceAddFragment)
                        .commit();
                break;
            case R.id.variety_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(fragment4).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(maintenanceAddFragment)
                        .commit();
                break;
            case R.id.setting_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(fragment4).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(maintenanceAddFragment)
                        .commit();
                break;
            default:
                break;
        }

        ft.commit();
    }
    private Button.OnClickListener listener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    public void chageFragment(){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(fragment4).show(maintenanceAddFragment)
                .commit();
    }
}