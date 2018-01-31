package com.cyut.motor.s065;

import android.app.Activity;
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
    private EditText ed_name;
    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_name = findViewById(R.id.ed_name);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);

        btn_cencel = findViewById(R.id.btn_cencel);
        btn_cencel.setOnClickListener(listener);

    }

    public void btnRegistrationUser_Click(View v) {
        //判斷網路狀況
        if (StaticMethodPack.isNetworkConnecting(this)) {
            if (ed_password.getText().toString().equals("") || ed_email.getText().toString().equals("") || ed_name.getText().toString().equals("")) {
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

//                            Log.e(“ed_id”,ed_id.getText().toString());
//                            Log.e(“ed_email”,ed_email.getText().toString());
//                            Log.e(“ed_password”,ed_password.getText().toString());

                            if (task.isSuccessful()) {
                                sharedPreferences.edit().putString("user_id", task.getResult().getUser().getUid()).commit();
                                Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_LONG).show();

                                writeNewPost(ed_name,ed_email,ed_password,
                                        ed_name.getText().toString(),
                                        ed_email.getText().toString(),
                                        ed_password.getText().toString(),
                                        sharedPreferences.getString("userid",""));
//                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
//                                startActivity(i);

//                                Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/");
//                                String key = myFirebaseRef.child("User").push().getKey();
//                                UserStructure userStructure = new UserStructure(ed_id, ed_email, ed_password);
//                                Map<String, Object> postValues = userStructure.toMap();
//
//                                Map<String, Object> childUpdates = new HashMap<>();
//                                childUpdates.put("/User/" + key, postValues);


                            } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegisterActivity.this, "此信箱已被註冊", Toast.LENGTH_LONG).show();
                                //If email already registered.

                            } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(RegisterActivity.this, "信箱格式輸入錯誤", Toast.LENGTH_LONG).show();
                                //If email are in incorret  format
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
            // TODO Auto-generated method stub
            finish();
        }
    };

    private void writeNewPost(EditText edname, EditText edEmail, EditText edPassword, String ed_name, String ed_email, String ed_password, String userId) {
        Firebase myFirebaseRef = new Firebase("https://motorcycle-cc0fe.firebaseio.com/");
        String key = myFirebaseRef.child("User").push().getKey();
        UserStructure userStructure = new UserStructure(ed_name, ed_email, ed_password,userId);
        Map<String, Object> postValues = userStructure.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/" + key, postValues);
        myFirebaseRef.updateChildren(childUpdates, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}