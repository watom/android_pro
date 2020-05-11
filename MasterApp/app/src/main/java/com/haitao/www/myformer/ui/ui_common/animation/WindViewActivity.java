package com.haitao.www.myformer.ui.ui_common.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.ahmadnemati.wind.WindView;
import com.github.ahmadnemati.wind.enums.TrendType;
import com.haitao.www.myformer.R;

/**
 * 开源：https://github.com/AhmadNemati/WindView
 * 注意：还需要在build.gradle中添加 maven { url "https://jitpack.io" }
 * Created by Administrator on 2018/11/21 0010.
 */

public class WindViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_wind_main);
        initView();
    }

    private void initView() {
        WindView windView= (WindView) findViewById(R.id.windview);
        windView.setPressure(30);
        windView.setPressureUnit("in Hg");
        windView.setWindSpeed(8);
        windView.setWindSpeedUnit(" km/h");
        windView.setTrendType(TrendType.UP);
        windView.start();
        //缺少设置风速和其他的设置界面，计划使用Bottomsheet实现
    }
}
