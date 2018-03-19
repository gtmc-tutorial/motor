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
import android.widget.ImageView;
import android.widget.Toast;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.StaticMethodPack;
import com.facebook.CallbackManager;
import com.cyut.motor.StaticMethodPack;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.FacebookSdk;
import static org.jsoup.Connection.Method.HEAD;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private Button btn_register,btn_forgetpw;
    private ImageView btn_home;
    private EditText ed_email,ed_password,ed_name;
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private CallbackManager mCallbackManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(listener1);
        btn_forgetpw = findViewById(R.id.btn_forgetpw);
        btn_forgetpw.setOnClickListener(listener2);
        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(listener3);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_name = findViewById(R.id.ed_name);
        firebaseAuth = FirebaseAuth.getInstance();


        sharedPreferences = getSharedPreferences("GTCLOUD_Content", MODE_PRIVATE);

        //        Log.e("xxx",sharedPreferences.getString("user_id","")+"123");

        // Facebook Login
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        mCallbackManager = CallbackManager.Factory.create();
//
//        LoginButton mFacebookSignInButton = (LoginButton) findViewById(R.id.login_with_facebook);
//        mFacebookSignInButton.setReadPermissions("email", "public_profile", "user_birthday", "user_friends");
//
//        mFacebookSignInButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                firebaseAuthWithFacebook(loginResult.getAccessToken());
//            }
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//            }
//        });
//
//        // Google Sign-In
//        SignInButton signInButton = (SignInButton)  findViewById(R.id.login_with_google);
//
//        // Set click listeners
//        findViewById(R.id.login_with_google).setOnClickListener(this);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    getSharedPreferences("Data",MODE_PRIVATE).edit().putString("userid",user.getUid()).apply();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    public void btnUserLogin_Click(View v) {
        if(StaticMethodPack.isNetworkConnecting(this)){
            if(ed_password.getText().toString().equals("") || ed_email.getText().toString().equals("")){
                Toast.makeText(LoginActivity.this, "有欄位尚未輸入", Toast.LENGTH_LONG).show();
                return;
            }

            if(ed_password.getText().toString().length() <6 && ed_password.getText().toString().length() <6){
                Toast.makeText(LoginActivity.this, "密碼需6碼以上", Toast.LENGTH_LONG).show();
                return;
            }

            final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Proccessing...", true);
            (firebaseAuth.signInWithEmailAndPassword(ed_email.getText().toString(), ed_password.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                if (ed_email.getText().toString().equals("cyutmotor@gmail.com") && ed_password.getText().toString().equals("106cyutmotor")){
                                    sharedPreferences.edit().putString("userid","cyutmotor@gmail.com").apply();

                                    Toast.makeText(LoginActivity.this,  "管理者登入成功", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LoginActivity.this, BackendActivity.class);
                                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);
                                }
                                else {
                                    sharedPreferences.edit().putString("userid",task.getResult().getUser().getUid()).apply();
                                    Toast.makeText(LoginActivity.this,  "登入成功", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "請檢查email和password是否有誤", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }else{
            Toast.makeText(this,"請連接網路",Toast.LENGTH_SHORT);
        }
    }

    private Button.OnClickListener listener1 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            Intent intent = new Intent();
            intent.putExtra("LoginActivity_IN","LoginActivity_IN");
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener2 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener listener3 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
            Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        } else {
                            sharedPreferences.edit().putString("userid",task.getResult().getUser().getUid()).commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }

    private void firebaseAuthWithFacebook(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        final AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_with_google:
                signIn();
                break;
            default:
                return;
        }
    }

    private void signIn() {
        if(StaticMethodPack.isNetworkConnecting(this)) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }else
        Toast.makeText(this,"請連接網路",Toast.LENGTH_SHORT);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Toast.makeText(LoginActivity.this, "登入成功",Toast.LENGTH_SHORT).show();
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.e(TAG, "Google Sign In failed.");
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "連線失敗:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

}



