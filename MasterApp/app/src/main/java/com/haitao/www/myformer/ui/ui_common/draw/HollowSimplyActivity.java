package com.haitao.www.myformer.ui.ui_common.draw;

import android.app.Activity;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.haitao.www.myformer.R;

/**
 * 安卓绘图
 * 步骤：
 * 1.自定义一个类，继承View
 * 2.重写onDraw方法
 * 注意：重写onDraw方法时，用到的主要API:
 * ① Canvas,它代表的是依附于指定View的画布
 * ② Paint,它代表画布Canvas上的画笔,主要用于设置绘制风格：画笔颜色、粗细，抗锯齿等风格
 * ③ Path,它代表任意多条直线连接而成的任意图形
 */
public class HollowSimplyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hollow);
        initView();
    }

    private void initView() {
        ImageView drawSample = findViewById(R.id.draw_sample);
        Button showGuide = findViewById(R.id.show_guide);
        Button hideGuide = findViewById(R.id.hide_guide);
        NewbieGuide.with(this).setLabel("指导01").alwaysShow(true).addGuidePage(
                GuidePage.newInstance().addHighLight(showGuide).setLayoutRes(R.layout.layout_text)
        ).show();
    }
}
