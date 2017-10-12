package com.cyut.motor.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cyut.motor.R;


/**
 * Created by hipsre720 on 2017/8/17.
 */

public class Page1Fragment extends Fragment {
    int[] res = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    ImageView imageView1,imageView2,imageView3,imageView4;
    int random1,random2,random3,random4;
    int number = 4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page1, null);


                //定義View
                imageView1 = (ImageView) view.findViewById(R.id.imageView);
        imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        Button playButtom = (Button) view.findViewById(R.id.play);
        Button addButtom = (Button) view.findViewById(R.id.add);
        Button lessButtom = (Button) view.findViewById(R.id.less);

        //檢查是否先前有執行過
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("test", Context.MODE_MULTI_PROCESS);
        if(sharedPreferences.getInt("number",0) != 0){
            number = sharedPreferences.getInt("number",4);
            random1 = sharedPreferences.getInt("random1",0);
            random2 = sharedPreferences.getInt("random2",0);
            random3 = sharedPreferences.getInt("random3",0);
            random4 = sharedPreferences.getInt("random4",0);

            imageView1.setImageResource(res[random1-1]);
            imageView2.setImageResource(res[random2-1]);
            imageView3.setImageResource(res[random3-1]);
            imageView4.setImageResource(res[random4-1]);

            checkView();
        }


        playButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play();
            }
        });
        addButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(number != 4){
                    number++;
                    checkView();
                }
            }
        });
        lessButtom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(number != 1){
                    number--;
                    checkView();
                }
            }
        });
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    private void play(){
        int random1 = (int)(Math.random()* 6) + 1;
        int random2 = (int)(Math.random()* 6) + 1;
        int random3 = (int)(Math.random()* 6) + 1;
        int random4 = (int)(Math.random()* 6) + 1;

        imageView1.setImageResource(res[random1-1]);
        imageView2.setImageResource(res[random2-1]);
        imageView3.setImageResource(res[random3-1]);
        imageView4.setImageResource(res[random4-1]);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("test", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("number",number);
        editor.putInt("random1",random1);
        editor.putInt("random2",random2);
        editor.putInt("random3",random3);
        editor.putInt("random4",random4);
        editor.commit();
    }
    private void checkView(){
        if(number == 1){
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
        }else if(number == 2){
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
        }else if(number == 3){
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.INVISIBLE);
        }else if(number == 4){
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.VISIBLE);
        }
    }

}


