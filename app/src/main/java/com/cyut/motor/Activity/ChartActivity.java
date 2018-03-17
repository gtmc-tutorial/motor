package com.cyut.motor.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;

import com.cyut.motor.R;
import com.cyut.motor.Structure.MaintainStructure;
import com.cyut.motor.s186.TableAdapter;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import static com.cyut.motor.s186.MaintenanceFragment.main_arrayList;

/**
 * Created by wubingyu on 2018/3/17.
 */

public class ChartActivity extends DemoBase {
    private PieChart mChart1;
    private PieChart mChart2;

    private int sum_old1 = 0,sum_old2 = 0,sum_old3 = 0,sum_old4 = 0;
    private int sum_s1 = 0,sum_s2 = 0,sum_s3 = 0,sum_s4 = 0,sum_s5 = 0,sum_s6 = 0,sum_s7 = 0,sum_s8 = 0,sum_s9 = 0,sum_s10 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Firebase.setAndroidContext(this);

        for (MaintainStructure maintainStructure :main_arrayList){
            Log.e("type",maintainStructure.type);
            if(maintainStructure.type.equals("機油")){
                sum_old1 += 1;
            }else if(maintainStructure.type.equals("齒輪油")){
                sum_old2 += 1;
            }else if(maintainStructure.type.equals("汽油精")){
                sum_old3 += 1;
            }else if(maintainStructure.type.equals("機油精")){
                sum_old4 += 1;
            }else if(maintainStructure.type.equals("皮帶")){
                sum_s1 += 1;
            }else if(maintainStructure.type.equals("車殼")){
                sum_s2 += 1;
            }else if(maintainStructure.type.equals("傳動")){
                sum_s3 += 1;
            }else if(maintainStructure.type.equals("火星塞")){
                sum_s4 += 1;
            }else if(maintainStructure.type.equals("輪胎")){
                sum_s5 += 1;
            }else if(maintainStructure.type.equals("空濾")){
                sum_s6 += 1;
            }else if(maintainStructure.type.equals("煞車油")){
                sum_s7 += 1;
            }else if(maintainStructure.type.equals("噴射系統清潔")){
                sum_s8 += 1;
            }else if(maintainStructure.type.equals("車燈")){
                sum_s9 += 1;
            }else if(maintainStructure.type.equals("煞車皮")){
                sum_s10 += 1;
            }
        }

        setmChart1();
        setmChart2();
    }

    private void setData(int count, float range,PieChart chart,int type) {
        float mult = range;
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        if(type == 1){ //油類
            int sum = sum_old1+sum_old2+sum_old3+sum_old4;
            entries.add(new PieEntry((float)sum_old1/(float) sum,"機油",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_old2/(float) sum,"齒輪油",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_old3/(float) sum,"汽油精",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_old4/(float) sum,"機油精",getResources().getDrawable(R.drawable.star)));
        }else{//耗材
            int sum = sum_s1+sum_s2+sum_s3+sum_s4+sum_s5+sum_s6+sum_s7+sum_s8+sum_s9+sum_s10;
            entries.add(new PieEntry((float)sum_s1/(float) sum,"皮帶",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s2/(float) sum,"車殼",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s3/(float) sum,"傳動",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s4/(float) sum,"火星塞",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s5/(float) sum,"輪胎",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s6/(float) sum,"空濾",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s7/(float) sum,"煞車油",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s8/(float) sum,"噴射系統清潔",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s9/(float) sum,"車燈",getResources().getDrawable(R.drawable.star)));
            entries.add(new PieEntry((float)sum_s10/(float) sum,"煞車皮",getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "整體種類");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }


    private void setmChart1(){
        mChart1 = (PieChart) findViewById(R.id.chart1);
        mChart1.setUsePercentValues(true);
        mChart1.getDescription().setEnabled(false);
        mChart1.setExtraOffsets(0, 10, 40, 5);

        mChart1.setDragDecelerationFrictionCoef(0.95f);

        mChart1.setCenterTextTypeface(mTfLight);
        mChart1.setCenterText(generateCenterSpannableText1());

        mChart1.setDrawHoleEnabled(true);
        mChart1.setHoleColor(Color.WHITE);

        mChart1.setTransparentCircleColor(Color.WHITE);
        mChart1.setTransparentCircleAlpha(110);

        mChart1.setHoleRadius(58f);
        mChart1.setTransparentCircleRadius(61f);

        mChart1.setDrawCenterText(true);

        mChart1.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart1.setRotationEnabled(true);
        mChart1.setHighlightPerTapEnabled(true);

        // mChart1.setUnit(" €");
        // mChart1.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart1.setOnChartValueSelectedListener(this);

        setData(4, 100,mChart1,1);

        mChart1.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart1.spin(2000, 0, 360);

        Legend l = mChart1.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart1.setEntryLabelColor(Color.WHITE);
        mChart1.setEntryLabelTypeface(mTfRegular);
        mChart1.setEntryLabelTextSize(12f);
    }

    private void setmChart2(){
        mChart2 = (PieChart) findViewById(R.id.chart2);
        mChart2.setUsePercentValues(true);
        mChart2.getDescription().setEnabled(false);
        mChart2.setExtraOffsets(40, 10, 0, 5);

        mChart2.setDragDecelerationFrictionCoef(0.95f);

        mChart2.setCenterTextTypeface(mTfLight);
        mChart2.setCenterText(generateCenterSpannableText2());
        mChart2.setCenterTextSize(10);

        mChart2.setDrawHoleEnabled(true);
        mChart2.setHoleColor(Color.WHITE);

        mChart2.setTransparentCircleColor(Color.WHITE);
        mChart2.setTransparentCircleAlpha(110);

        mChart2.setHoleRadius(58f);
        mChart2.setTransparentCircleRadius(61f);

        mChart2.setDrawCenterText(true);

        mChart2.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart2.setRotationEnabled(true);
        mChart2.setHighlightPerTapEnabled(true);

        // mChart2.setUnit(" €");
        // mChart2.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart2.setOnChartValueSelectedListener(this);

        setData(10, 100,mChart2,2);

        mChart2.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart2.spin(2000, 0, 360);

        Legend l = mChart2.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart2.setEntryLabelColor(Color.WHITE);
        mChart2.setEntryLabelTypeface(mTfRegular);
        mChart2.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText1() {
        SpannableString s = new SpannableString("油類使用量");
//        s.setSpan(new RelativeSizeSpan(1.7f), 0, 5, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
    private SpannableString generateCenterSpannableText2() {
        SpannableString s = new SpannableString("耗材使用量");
//        s.setSpan(new RelativeSizeSpan(1.7f), 0, 5, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
}
