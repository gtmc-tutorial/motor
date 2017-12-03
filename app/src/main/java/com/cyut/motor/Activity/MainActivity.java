package com.cyut.motor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyut.motor.Structure.FragmentTag;
import com.cyut.motor.s065.LoginActivity;
import com.cyut.motor.s176.HelpFragment;
import com.cyut.motor.s186.MaintenanceAddFragment;
import com.cyut.motor.s186.MaintenanceFragment;
import com.cyut.motor.s192.HomeFragment;
import com.cyut.motor.s014.MapFragment;
import com.cyut.motor.s134.SettingFragment;
import com.cyut.motor.R;
import com.firebase.client.Firebase;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends FragmentActivity implements View.OnClickListener  {
    private TextView titleTextView;
    private ImageView movieBtn, tvBtn,animeBtn, varietyBtn,abcBtn;
    private ImageView btn_user;

    private HomeFragment homeFragment;
    private MaintenanceFragment maintenanceFragment;
    private MapFragment mapFragment;
    private HelpFragment helpFragment;
    private SettingFragment settingFragment;
    private MaintenanceAddFragment maintenanceAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();

        Firebase.setAndroidContext(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main_content, homeFragment = new HomeFragment(),FragmentTag.HOME_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main_content, maintenanceFragment = new MaintenanceFragment(), FragmentTag.MAINTENANCE_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main_content, mapFragment = new MapFragment(), FragmentTag.MAP_TAG)
                .commit();
                getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main_content, helpFragment = new HelpFragment(), FragmentTag.HELP_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main_content, settingFragment = new SettingFragment(),FragmentTag.SETTING_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_main_content, maintenanceAddFragment = new MaintenanceAddFragment(),FragmentTag.MAINTENANCEADD_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                .commit();
    }
    private void findById() {
        titleTextView = (TextView) this.findViewById(R.id.main_title_text);

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

    @Override
    public void onClick(View view) {
        // TODO Auto-generated methodstub
        switch (view.getId()) {
            case R.id.movie_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("首頁");
                break;
            case R.id.tv_btn:

                if(getSharedPreferences("Data",0).getString("user_id","").equals("")){
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("請登入")
                            .setContentText("需登入才能使用保養功能")
//                            .setConfirmText("Yes,delete it!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }else{
                    getSupportFragmentManager().beginTransaction()
                            .show(maintenanceFragment).hide(homeFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                            .commit();
                    titleTextView.setText("保養");
                }
                break;
            case R.id.anime_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(mapFragment).hide(homeFragment).hide(maintenanceFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("位置服務");
                break;
            case R.id.variety_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("道路救援");
                break;
            case R.id.setting_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(settingFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(helpFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("設定");
                break;
            default:
                break;
        }
    }
    private Button.OnClickListener listener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //尚未登入
            if(getSharedPreferences("Data",0).getString("user_id","").equals("")){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }else{ //登入過

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                getSharedPreferences("Data",0).edit().putString("user_id","").commit();
            }
        }
    };

    public void chageFragment(String string){
        if(string.equals("首頁")){
            getSupportFragmentManager().beginTransaction()
                    .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("首頁");
        }else if(string.equals("保養")){
            getSupportFragmentManager().beginTransaction()
                    .show(maintenanceFragment).hide(homeFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("保養");
        }else if(string.equals("位置服務")){
            getSupportFragmentManager().beginTransaction()
                    .show(mapFragment).hide(homeFragment).hide(maintenanceFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("位置服務");
        }else if(string.equals("道路救援")){
            getSupportFragmentManager().beginTransaction()
                    .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("道路救援");
        }else if(string.equals("設定")) {
            getSupportFragmentManager().beginTransaction()
                    .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("設定");
        }

        else if(string.equals("新增保養")) {
            getSupportFragmentManager().beginTransaction()
                    .show(maintenanceAddFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment)
                    .commit();
            titleTextView.setText("設定");
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
}