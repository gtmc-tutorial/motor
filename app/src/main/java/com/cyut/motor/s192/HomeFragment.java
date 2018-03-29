package com.cyut.motor.s192;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.cyut.motor.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * Created by hipsre720 on 2017/8/17.
 */

public class HomeFragment extends Fragment {
    private TextView textView_92,textView_95,textView_98,textView_super;
    private WebView weather;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, null);

        textView_92 = (TextView) view.findViewById(R.id.textView_92);
        textView_95 = (TextView) view.findViewById(R.id.textView_95);
        textView_98 = (TextView) view.findViewById(R.id.textView_98);
        textView_super = (TextView) view.findViewById(R.id.textView_super);
        //WebView
        weather= (WebView)view.findViewById(R.id.webview);
        weather.loadUrl("https://weather.yam.com/%E9%9C%A7%E5%B3%B0%E5%8D%80/%E8%87%BA%E4%B8%AD%E5%B8%82");
//        //设置可自由缩放网页
        weather.getSettings().setSupportZoom(true);
        weather.getSettings().setBuiltInZoomControls(true);
//
//        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
//        // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象
//        browser.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_webview);
//        WebView myWebView = (WebView) getView();
////        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.requestFocus();
//        myWebView.setWebViewClient(new MyWebViewClient());
//        myWebView.loadUrl("https://www.windy.com/24.068/120.715?temp,24.069,120.715,17,m:elhajwm");

        return view;

        }

        private void setContentView(int activity_webview) {
        }




//        button = (Button) view.findViewById(R.id.button_weather);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(),WebViewActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        //定義View
//        return view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable(){
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://new.cpc.com.tw/Home/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements elements = null;
                if(doc != null) {
                    if (doc.getElementsByClass("clearfix") != null) {
                        elements = doc.getElementsByClass("clearfix").select("dl").select("dd").select("strong");
                    }
                }
//                for (Element headline : aa) {
//                    Log.e("headline", headline.toString());
//                }
//                Log.e("finalElements",elements.size()+"");
                final Elements finalElements = elements;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(finalElements !=null){
                            textView_92.setText("92無鉛 : "+ finalElements.get(2).text());
                            textView_95.setText("95無鉛 : "+ finalElements.get(3).text());
                            textView_98.setText("98無鉛 : "+ finalElements.get(4).text());
                            textView_super.setText("柴油 : "+ finalElements.get(6).text());
                        }
                    }
                });
            }
        }).start();
    }
}


