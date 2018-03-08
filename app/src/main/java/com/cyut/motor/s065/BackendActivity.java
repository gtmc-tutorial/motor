package com.cyut.motor.s065;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.cyut.motor.R;

public class BackendActivity extends Activity {

    private Button btn_backend_user, btn_backend_warranty, btn_backend_car_dealers ,btn_backend_gas,btn_backend_battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend);

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
}
