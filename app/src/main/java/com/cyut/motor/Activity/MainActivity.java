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
import com.cyut.motor.s192.HomeFragment;
import com.cyut.motor.s186.MaintenanceFragment;
import com.cyut.motor.s014.MapFragment;
import com.cyut.motor.s134.SettingFragment;
import com.cyut.motor.R;
import com.firebase.client.Firebase;


public class MainActivity extends FragmentActivity implements View.OnClickListener  {
    private TextView titleTextView;
    private ImageView movieBtn, tvBtn,animeBtn, varietyBtn,abcBtn,movieBtn2,tvBtn2,animeBtn2;

    private ImageView btn_user;

    private android.app.FragmentManager fm;
    private android.app.FragmentTransaction ft;
    private MaintenanceFragment mMapFragment;

    private HomeFragment homeFragment;
//    private MaintenanceFragment maintenanceFragment;
    private MaintenanceAddFragment maintenanceAddFragment;
    private MapFragment mapFragment;
    private HelpFragment helpFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();

        Firebase.setAndroidContext(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, homeFragment = new HomeFragment(),FragmentTag.HOME_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, maintenanceAddFragment = new MaintenanceAddFragment(), FragmentTag.MAINTENANCE_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, mapFragment = new MapFragment(), FragmentTag.MAP_TAG)
                .commit();
                getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, helpFragment = new HelpFragment(), FragmentTag.HELP_TAG)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, settingFragment = new SettingFragment(),FragmentTag.FRAGEMENT4)
                .commit();


        getSupportFragmentManager().beginTransaction()
                .show(homeFragment).hide(maintenanceAddFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment)
                .commit();

        // 進入系統默認為movie
//        fm = getFragmentManager();
//        ft = fm.beginTransaction();

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_content, new MapFragment())
//                .commit();
//        ft.replace(R.id.fragment_content,new MapFragment());
//        ft.commit();
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
                        .show(homeFragment).hide(maintenanceAddFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment)
                        .commit();
                titleTextView.setText("首頁");
                break;
            case R.id.tv_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(maintenanceAddFragment).hide(homeFragment).hide(mapFragment).hide(settingFragment).hide(helpFragment)
                        .commit();
                titleTextView.setText("保養");
                break;
            case R.id.anime_btn:
                Log.e("位置服務","位置服務");
                getSupportFragmentManager().beginTransaction()
                        .show(mapFragment).hide(homeFragment).hide(maintenanceAddFragment).hide(settingFragment).hide(helpFragment)
                        .commit();
                titleTextView.setText("位置服務");
                break;
            case R.id.variety_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(helpFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceAddFragment).hide(settingFragment)
                        .commit();
                titleTextView.setText("道路救援");
                break;
            case R.id.setting_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(settingFragment).hide(homeFragment).hide(mapFragment).hide(maintenanceAddFragment).hide(helpFragment)
                        .commit();
                titleTextView.setText("設定");
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
        }
    };

    public void chageFragment(){
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment).hide(maintenanceAddFragment).hide(mapFragment).hide(settingFragment).show(helpFragment)
                .commit();
    }
}