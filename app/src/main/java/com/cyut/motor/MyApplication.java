package com.cyut.motor;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by snake on 2018/3/17.
 */

public class MyApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Previous versions of Firebase
        Firebase.setAndroidContext(this);

        //Newer version of Firebase
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
