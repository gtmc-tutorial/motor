package com.cyut.motor.s065;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cyut.motor.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BackendActivity extends Activity {

    private Button btn_backend_user, btn_backend_warranty, btn_backend_car_dealers ,btn_backend_gas,btn_backend_battery,btn_backend_homepage;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend);
        sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);

        btn_backend_user = findViewById(R.id.btn_backend_user);
        btn_backend_user.setOnClickListener(listener);
        btn_backend_warranty = findViewById(R.id.btn_backend_warranty);
        btn_backend_warranty.setOnClickListener(listener1);
        btn_backend_car_dealers = findViewById(R.id.btn_backend_car_dealers);
        btn_backend_car_dealers.setOnClickListener(listener2);
        btn_backend_gas = findViewById(R.id.btn_backend_gas);
        btn_backend_gas.setOnClickListener(listener3);
        btn_backend_battery = findViewById(R.id.btn_backend_battery);
        btn_backend_battery.setOnClickListener(listener4);
        btn_backend_homepage = findViewById(R.id.btn_backend_homepage);
        btn_backend_homepage.setOnClickListener(listener5);

    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this,UserActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this, WarrantyActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener2 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this, Car_dealersActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener3 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this, GasActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener4 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this, BatteryActivity.class);
            startActivity(intent);
        }
    };
    private Button.OnClickListener listener5 = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            String id = sharedPreferences.getString("userid","");
            if(id.equals("")){
                new SweetAlertDialog(BackendActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("登出提醒")
                        .setContentText("是否要登出?")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sharedPreferences.edit().clear().commit();
                                Intent intent = new Intent();
                                intent.setClass(BackendActivity.this,LoginActivity.class);
                                startActivity(intent);
                                sDialog.dismissWithAnimation();
                            }
                        }).show();
            }
        }};
}
