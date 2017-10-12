package com.cyut.motor.s065;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;


public class ForgetPasswordActivity extends Activity {

    private Button btn_cencel,btn_home,btn_confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btn_cencel = (Button)findViewById(R.id.btn_cencel);
        btn_cencel.setOnClickListener(listener);
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(listener1);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
//        btn_confirm.setOnClickListener(listener2);

    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(ForgetPasswordActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

//    private Button.OnClickListener listener2 = new Button.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//            Intent intent = new Intent();
//            intent.setClass(ForgetPasswordActivity.this, ResetPasswordActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    };

}