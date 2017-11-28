package com.cyut.motor.s192;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyut.motor.Activity.MainActivity;
import com.cyut.motor.R;
import com.cyut.motor.s192.WebViewActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * Created by hipsre720 on 2017/8/17.
 */

public class HomeFragment extends Fragment {
    Button button;
    private TextView textView_92,textView_95,textView_98,textView_super;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        textView_92 = (TextView) view.findViewById(R.id.textView_92);
        textView_95 = (TextView) view.findViewById(R.id.textView_95);
        textView_98 = (TextView) view.findViewById(R.id.textView_98);
        textView_super = (TextView) view.findViewById(R.id.textView_super);

        button = (Button) view.findViewById(R.id.button_weather);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),WebViewActivity.class);
                startActivity(intent);
            }
        });
                //定義View
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable(){
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://new.cpc.com.tw/Home/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements elements = null;
                if(doc != null)
                    if(doc.getElementsByClass("clearfix")!=null)
                        elements = doc.getElementsByClass("clearfix").select("dl").select("dd").select("strong");
//                for (Element headline : elements) {
//                    Log.e("headline", headline.toString());
//                }

                final Elements finalElements = elements;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(finalElements !=null){
                            textView_92.setText("92無鉛 : "+ finalElements.get(2).text());
                            textView_95.setText("95無鉛 : "+ finalElements.get(3).text());
                            textView_98.setText("98無鉛 : "+ finalElements.get(4).text());
                            textView_super.setText("超級柴油 : "+ finalElements.get(6).text());
                        }

                    }
                });

            }
        }).start();

    }

}


