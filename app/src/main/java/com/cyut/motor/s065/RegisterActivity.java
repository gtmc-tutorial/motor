package com.cyut.motor.s065;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.StaticMethodPack;
import com.cyut.motor.Structure.UserStructure;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_cencel;
    private EditText ed_email;
    private EditText ed_password;
    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);

        btn_cencel = findViewById(R.id.btn_cencel);
        btn_cencel.setOnClickListener(listener);

    }

    public void btnRegistrationUser_Click(View v) {
        //判斷網路狀況
        if (StaticMethodPack.isNetworkConnecting(this)) {
            if (ed_password.getText().toString().equals("") || ed_email.getText().toString().equals("")) {
                Toast.makeText(RegisterActivity.this, "有欄位尚未輸入", Toast.LENGTH_LONG).show();
                return;
            }
            if (ed_password.getText().toString().length() < 6 && ed_password.getText().toString().length() < 6) {
                Toast.makeText(RegisterActivity.this, "密碼需6碼以上", Toast.LENGTH_LONG).show();
                return;
            }
            //process
            final ProgressDialog progressDialog = ProgressDialog.show(RegisterActivity.this, "Please wait...", "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(ed_email.getText().toString(), ed_password.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                Log.e("1","1");
//                                if(!getIntent().getBooleanExtra("UserActivity_IN",false)){
//                                    Log.e("2","2");
//                                sharedPreferences.edit().putString("userid", task.getResult().getUser().getUid()).apply();
                                Toast.makeText(RegisterActivity.this, "註冊成功"+"\n"+"請重新登入", Toast.LENGTH_LONG).show();
                                writeNewPost(
                                        ed_email.getText().toString(),
                                        ed_password.getText().toString(),
                                        task.getResult().getUser().getUid());
                            } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegisterActivity.this, "此信箱已被註冊", Toast.LENGTH_LONG).show();

                            } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(RegisterActivity.this, "信箱格式輸入錯誤", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "請連接網路", Toast.LENGTH_SHORT);
        }
    }

    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String result = getIntent().getStringExtra("LoginActivity_IN");
            String result1 = getIntent().getStringExtra("UserActivity_IN");
            if(result != null && result.equals("LoginActivity_IN")){
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
            else if (result1 != null && result1.equals("UserActivity_IN")){
                startActivity(new Intent(RegisterActivity.this, UserActivity.class));
            }
        }
    };


    private void writeNewPost(String ed_email, String ed_password, String userId) {
        Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/");
        String key = myFirebaseRef.child("User").push().getKey();
        UserStructure userStructure = new UserStructure( ed_email, ed_password,userId);
        Map<String, Object> postValues = userStructure.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/" + key, postValues);
        myFirebaseRef.updateChildren(childUpdates, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        String result = getIntent().getStringExtra("LoginActivity_IN");
        String result1 = getIntent().getStringExtra("UserActivity_IN");
        if(result != null && result.equals("LoginActivity_IN")){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }
        else if (result1 != null && result1.equals("UserActivity_IN")){
            startActivity(new Intent(RegisterActivity.this, UserActivity.class));
        }
        super.onBackPressed();
    }

}