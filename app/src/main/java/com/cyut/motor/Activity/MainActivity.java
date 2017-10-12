package com.cyut.motor.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cyut.motor.Fragment.Page1Fragment;
import com.cyut.motor.Fragment.Page2Fragment;
import com.cyut.motor.Fragment.mapFragment;
import com.cyut.motor.Fragment.Page4Fragment;
import com.cyut.motor.Fragment.Page6Fragment;
import com.cyut.motor.R;
import com.cyut.motor.s065.LoginActivity;


public class MainActivity extends FragmentActivity implements View.OnClickListener  {
    private ImageView movieBtn, tvBtn,animeBtn, varietyBtn,abcBtn,movieBtn2,tvBtn2,animeBtn2;

    private ImageView btn_user;

    private android.app.FragmentManager fm;
    private android.app.FragmentTransaction ft;
    private Page2Fragment mMapFragment;
    private Page6Fragment mMapFragment6;

    private Page1Fragment fragment1;
    private Page2Fragment fragment2;
    private mapFragment fragment3;
    private Page4Fragment fragment4;
    //  add 8787
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, fragment1 = new Page1Fragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, fragment2 = new Page2Fragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, fragment3 = new mapFragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, fragment4 = new Page4Fragment())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .show(fragment1).hide(fragment2).hide(fragment3).hide(fragment4)
                .commit();

        // 進入系統默認為movie
//        fm = getFragmentManager();
//        ft = fm.beginTransaction();

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_content, new mapFragment())
//                .commit();
//        ft.replace(R.id.fragment_content,new mapFragment());
//        ft.commit();
    }
    private void findById() {

        movieBtn = (ImageView)this.findViewById(R.id.movie_btn);
        tvBtn = (ImageView)this.findViewById(R.id.tv_btn);
        animeBtn = (ImageView) this.findViewById(R.id.anime_btn);
        varietyBtn = (ImageView)this.findViewById(R.id.variety_btn);
        abcBtn = (ImageView)this.findViewById(R.id.abc_btn);
        btn_user = (ImageView)findViewById(R.id.btn_user);
        btn_user.setOnClickListener(listener);

        movieBtn.setOnClickListener(this);
        tvBtn.setOnClickListener(this);
        animeBtn.setOnClickListener(this);
        varietyBtn.setOnClickListener(this);
        abcBtn.setOnClickListener(this);
    }

//    private void setBackgroundColorById(int btnId) {
//        for (Button btn : btnList) {
//            if (btn.getId() == btnId){
//                btn.setBackgroundColor(Color.GREEN);
//            }else {
//                btn.setBackgroundColor(Color.BLUE);
//            }
//        }
//    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated methodstub
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.movie_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(fragment1).hide(fragment2).hide(fragment3).hide(fragment4)
                        .commit();
                break;
            case R.id.tv_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(fragment2).hide(fragment1).hide(fragment3).hide(fragment4)
                        .commit();
                break;
            case R.id.anime_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(fragment3).hide(fragment1).hide(fragment2).hide(fragment4)
                        .commit();
                break;
            case R.id.variety_btn:
                getSupportFragmentManager().beginTransaction()
                        .show(fragment4).hide(fragment1).hide(fragment3).hide(fragment2)
                        .commit();
                break;

            default:
                break;
        }

        ft.commit();
    }
    private Button.OnClickListener listener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
}