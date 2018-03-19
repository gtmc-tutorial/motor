package com.cyut.motor.s192;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cyut.motor.R;

/**
 * Created by 何永松 on 2017/9/20.
 */

public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

//        WebViewClient mWebViewClient = new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        };
//
//        WebView webView = (WebView) findViewById(R.id.webview);
//        webView.setWebViewClient(mWebViewClient);
//        webView.loadUrl("https://www.windy.com");
//
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
    }
}
