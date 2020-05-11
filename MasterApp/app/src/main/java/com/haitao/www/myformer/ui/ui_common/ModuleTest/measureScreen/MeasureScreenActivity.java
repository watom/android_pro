package com.haitao.www.myformer.ui.ui_common.ModuleTest.measureScreen;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.haitao.www.myformer.R;


/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class MeasureScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MeasureScreenActivity";
    private TextView tvMeterScreen01;
    private TextView tvMeterScreen02;
    private TextView tvMeterScreen03;
    private TextView tvMeterScreen04;
    private TextView tvMeterScreen05;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_screen);
        tvMeterScreen01 = findViewById(R.id.tv_meter_screen_01);
        tvMeterScreen02 = findViewById(R.id.tv_meter_screen_02);
        tvMeterScreen03 = findViewById(R.id.tv_meter_screen_03);
        tvMeterScreen04 = findViewById(R.id.tv_meter_screen_04);
        tvMeterScreen05 = findViewById(R.id.tv_meter_screen_05);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_measurescreen:
                measurescreen();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void measurescreen() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        tvMeterScreen01.setText("屏幕尺寸:\n" + metric.toString());

        double screenWidth02 = point.x / metric.xdpi * 2.54;     // 屏幕实际宽度(cm)=x轴上的总实际像素数目/实际密度*2.54
        double screenHeight02 = point.y / metric.ydpi * 2.54;     // 屏幕实际高度(cm)=y轴上的总实际像素数目/实际密度*2.54
        tvMeterScreen02.setText("屏幕分辨率:\n real size\n  " + screenWidth02 + " cm\n屏幕实际高度:\n  " + screenHeight02 + " cm");

        tvMeterScreen03.setText("屏幕分辨率为(px):" + point.x+ " * " + point.y);

        double x = Math.pow(point.x / metric.xdpi, 2);//x的平方
        double y = Math.pow(point.y / metric.ydpi, 2);//y的平方
        double diagonal = Math.sqrt(x + y);//x+y开方，得到对角线上像素点的数目
        tvMeterScreen04.setText("屏幕的物理尺寸（对角线）:\n" + diagonal + " 英寸");

        float densityDpi = metric.densityDpi;//系统密度
        double screenWidth01 = metric.widthPixels / metric.xdpi * 2.54;     // 屏幕宽度=像素*密度
        double screenHeight01 = metric.heightPixels / metric.ydpi * 2.54;     // 屏幕高度=像素*密度
        tvMeterScreen05.setText("not necessarily\ndensityDpi=" + densityDpi + "\n屏幕宽度:" + screenWidth01 + "\n屏幕高度:" + screenHeight01);
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
