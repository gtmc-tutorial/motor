package com.cyut.motor.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends FragmentActivity implements View.OnClickListener  {
    private TextView titleTextView,tv_showname;
    private ImageView movieBtn, tvBtn,animeBtn, varietyBtn,abcBtn;
    private ImageView btn_user,btn_chart;
    private long clickTime = 0;

    private HomeFragment homeFragment;
    private MaintenanceFragment maintenanceFragment;
    private MapFragment mapFragment;
    private HelpFragment helpFragment;
    private SettingFragment settingFragment;
    private MaintenanceAddFragment maintenanceAddFragment;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);
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

        checkPermission();
    }
    private void findById() {
        titleTextView = (TextView) this.findViewById(R.id.main_title_text);

        tv_showname= findViewById(R.id.tv_showname);
        tv_showname.setText(getIntent().getStringExtra("Email"));

        movieBtn = (ImageView)this.findViewById(R.id.home_btn);
        tvBtn = (ImageView)this.findViewById(R.id.tv_btn);
        animeBtn = (ImageView) this.findViewById(R.id.anime_btn);
        varietyBtn = (ImageView)this.findViewById(R.id.variety_btn);
        abcBtn = (ImageView)this.findViewById(R.id.setting_btn);
        btn_user = (ImageView)findViewById(R.id.btn_user);
        btn_chart = (ImageView)findViewById(R.id.btn_chart);
        btn_user.setOnClickListener(listener);
        btn_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChartActivity.class));
            }
        });

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
            case R.id.home_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("首頁");
                btn_chart.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_btn:
                if(sharedPreferences.getString("userid","").equals("")){
                    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("請登入")
                            .setContentText("需登入才能使用保養功能")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }
                            }).show();
                }else{
                    getSupportFragmentManager().beginTransaction()
                            .show(maintenanceFragment).hide(homeFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                            .commit();
                    titleTextView.setText("保養");
                    btn_chart.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.anime_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(mapFragment).hide(homeFragment).hide(maintenanceFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("位置服務");
                btn_chart.setVisibility(View.INVISIBLE);

                break;
            case R.id.variety_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("道路救援");
                btn_chart.setVisibility(View.INVISIBLE);
                break;
            case R.id.setting_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(settingFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(helpFragment).hide(maintenanceAddFragment)
                        .commit();
                titleTextView.setText("設定");
                btn_chart.setVisibility(View.INVISIBLE);
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
            String id = sharedPreferences.getString("userid","");
            if(id.equals("")){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }else{ //登入過
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("登出提醒")
                            .setContentText("是否要登出?")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sharedPreferences.edit().clear().commit();
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    sDialog.dismissWithAnimation();
                                }
            }).show();
        }
    }};


    public void chageFragment(String string){
        if(string.equals("首頁")){
            btn_chart.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .show(homeFragment).hide(maintenanceFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("首頁");
        }else if(string.equals("保養")){
            btn_chart.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .show(maintenanceFragment).hide(homeFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("保養");
        }else if(string.equals("位置服務")){
            btn_chart.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .show(mapFragment).hide(homeFragment).hide(maintenanceFragment).hide(settingFragment).hide(helpFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("位置服務");
        }else if(string.equals("道路救援")){
            btn_chart.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("道路救援");
        }else if(string.equals("設定")) {
            btn_chart.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment).hide(maintenanceAddFragment)
                    .commit();
            titleTextView.setText("設定");
        }

        else if(string.equals("新增保養")) {
            btn_chart.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .show(maintenanceAddFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceFragment).hide(settingFragment)
                    .commit();
            titleTextView.setText("保養");
        }
    }
    //打電話權限檢查
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 10);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        } else { // 如果不是back键正常响应
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次退出 無限騎機", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}