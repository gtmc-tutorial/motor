package com.cyut.motor.s065;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;


public class WelcomeActivity extends AppCompatActivity {

    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(listener);
    }

    private Button.OnClickListener listener = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}

