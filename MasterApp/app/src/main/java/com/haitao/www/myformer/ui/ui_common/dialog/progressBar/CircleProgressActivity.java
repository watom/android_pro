package com.haitao.www.myformer.ui.ui_common.dialog.progressBar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import com.haitao.www.myformer.R;

public class CircleProgressActivity extends Activity {
    private CircleProgressBar circleProgressBar; // 自定义的进度条
    private SeekBar seekbar; // 拖动条

    private int[] colors = new int[]{Color.parseColor("#27B197"), Color.parseColor("#00A6D5")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_circleprogressbar_01);

        circleProgressBar = (CircleProgressBar) findViewById(R.id.circleProgressBar);
//		circleProgressBar.setFirstColor(Color.LTGRAY);
//		circleProgressBar.setColorArray(colors); //觉得进度条颜色丑的，这里可以自行传入一个颜色渐变数组。
//		circleProgressBar.setCircleWidth(6);

        seekbar = (SeekBar) findViewById(R.id.seekbar);
        seekbar.setMax(100);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // circleProgressBar.setProgress(progress); //不使用动画
                    circleProgressBar.setProgress(progress, true); // 使用数字过渡动画
                }
            }
        });
    }
}
