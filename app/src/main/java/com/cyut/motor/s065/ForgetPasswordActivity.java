package com.cyut.motor.s065;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends Activity {

    private Button btn_cencel,btn_confirm;
    EditText ed_email;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ed_email = findViewById(R.id.ed_email);
        btn_cencel = findViewById(R.id.btn_cencel);
        btn_cencel.setOnClickListener(listener);
        btn_confirm = findViewById(R.id.btn_confirm);

        auth = FirebaseAuth.getInstance();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"請輸入你的Email!" , Toast.LENGTH_SHORT).show();//done
                    return;
                }
                final ProgressDialog progressDialog = ProgressDialog.show(ForgetPasswordActivity.this, "Please wait...", "Processing...", true);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (!task.isSuccessful()) {
                            Toast.makeText(ForgetPasswordActivity.this, "此信箱尚未被註冊！", Toast.LENGTH_SHORT).show();//done
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this,"已經送出修改信件!\n請至您輸入的信箱進行修改密碼!",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            Intent intent = new Intent();
            intent.putExtra("ForgetPasswordActivity_IN","ForgetPasswordActivity_IN");
            intent.setClass(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
        super.onBackPressed();
    }
}