package com.cyut.motor.s065;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.cyut.motor.R;

public class BackendActivity extends Activity {

    private Button btn_backend_user, btn_backend_supplies_oil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend);

        btn_backend_user = findViewById(R.id.btn_backend_user);
        btn_backend_user.setOnClickListener(listener);
        btn_backend_supplies_oil = findViewById(R.id.btn_backend_supplies_oil);
        btn_backend_supplies_oil.setOnClickListener(listener1);

    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this, UserActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(BackendActivity.this, UserActivity.class);
            startActivity(intent);
        }
    };
}
