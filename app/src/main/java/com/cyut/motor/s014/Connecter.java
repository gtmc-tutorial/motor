package com.cyut.motor.s014;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wubingyu on 2017/10/24.
 */

public class Connecter {
    private static Connecter mConnecter = null;

    public static Connecter getInstance() {
        if (mConnecter == null) {
            synchronized (Connecter.class) {
                if (mConnecter == null)
                    mConnecter = new Connecter();
            }
        }
        return mConnecter;
    }

    public synchronized String getJSONContent(String apiUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();
            Log.v("GTService", "getJSONContent Response Message :" + response.message());
            response.close();
            return jsonString;
        } catch (IOException e) {
            Log.e("e",e+"");
            e.printStackTrace();
            return null;
        }
    }
}
