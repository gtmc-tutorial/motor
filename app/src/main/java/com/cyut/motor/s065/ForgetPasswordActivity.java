package com.cyut.motor.s065;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

    private Button btn_cencel,btn_home,btn_confirm;
    EditText ed_email;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        ed_email = findViewById(R.id.ed_email);
        btn_cencel = (Button)findViewById(R.id.btn_cencel);
        btn_cencel.setOnClickListener(listener);
//        btn_home = (Button) findViewById(R.id.btn_home);
//        btn_home.setOnClickListener(listener1);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
//        btn_confirm.setOnClickListener(listener2);

        mauth = FirebaseAuth.getInstance();

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),"請輸入你的Email!" , Toast.LENGTH_SHORT).show();//done
                    return;
                }
                mauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(ForgetPasswordActivity.this, "重設失敗!", Toast.LENGTH_SHORT).show();//done
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this,"已經送出修改信件!\n請至你的Email查看!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(ForgetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

//    private Button.OnClickListener listener1 = new Button.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.setClass(ForgetPasswordActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    };

//    private Button.OnClickListener listener2 = new Button.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent();
//            intent.setClass(ForgetPasswordActivity.this, ResetPasswordActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    };

//    public static class LoginActivity extends AppCompatActivity {
//
//        private Button btn_home, btn_register;
//        private Button btn_forgetpw;
//
//        private EditText ed_email;
//        private EditText ed_password;
//        private FirebaseAuth firebaseAuth;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_login);
//
//            btn_home = (Button) findViewById(R.id.btn_home);
//            btn_home.setOnClickListener(listener);
//            btn_register = (Button) findViewById(R.id.btn_register);
//            btn_register.setOnClickListener(listener1);
//            btn_forgetpw = (Button) findViewById(R.id.btn_forgetpw);
//            btn_forgetpw.setOnClickListener(listener2);
//
//            ed_email = (EditText) findViewById(R.id.ed_email);
//            ed_password = (EditText) findViewById(R.id.ed_password);
//            firebaseAuth = FirebaseAuth.getInstance();
//
//        }
//
//        public void btnUserLogin_Click(View v) {
//            final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);
//
//            (firebaseAuth.signInWithEmailAndPassword(ed_email.getText().toString(), ed_password.getText().toString()))
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            progressDialog.dismiss();
//
//                            if (task.isSuccessful()) {
//                                Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_LONG).show();
//                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                                i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
//                                startActivity(i);
//                            } else {
//                                Log.e("登入失敗", task.getException().toString());
//                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//
//        }
//
//        private Button.OnClickListener listener = new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent = new Intent();
//                intent.setClass(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        };
//
//        private Button.OnClickListener listener1 = new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        };
//
//        private Button.OnClickListener listener2 = new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent();
//                intent.setClass(LoginActivity.this, ForgetPasswordActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        };
//
//    }
}