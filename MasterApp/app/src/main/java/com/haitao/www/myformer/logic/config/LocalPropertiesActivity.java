package com.haitao.www.myformer.logic.config;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.haitao.www.myformer.R;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class LocalPropertiesActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtResult;
    private EditText etShuru;
    private Button btnCeshi01;
    private TextView tvResult;
    private Button btnCeshi02;
    private TextView tvResult02;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congfig);
        findViews();
        initData();
    }

    private void initData() {
        StringBuffer status = new StringBuffer();
        //①获取系统的Configuration对象
        Configuration cfg = getResources().getConfiguration();
        //②想查什么查什么
        status.append("屏幕密度densityDpi:" + cfg.densityDpi + "\n");
        status.append("当前用户设置的字体的缩放因子fontScale:" + cfg.fontScale + "\n");
        status.append("判断硬键盘是否可见hardKeyboardHidden:" + cfg.hardKeyboardHidden + "\n");
        status.append("获取当前关联额键盘类型keyboard:" + cfg.keyboard + "\n");
        status.append("判断当前键盘是否可用keyboardHidden:" + cfg.keyboardHidden + "\n");
        status.append("获取用户当前的语言环境locale:" + cfg.locale + "\n");
        status.append("获取移动信号的国家码mcc:" + cfg.mcc + "\n");
        status.append("获取移动信号的网络码mnc:" + cfg.mnc + "\n");
        status.append("判断系统上方向导航设备的类型navigation:" + cfg.navigation + "\n");
        status.append("navigationHidden:" + cfg.navigationHidden + "\n");
        status.append("获取系统屏幕的方向orientation:" + cfg.orientation + "\n");
        status.append("屏幕可用高screenHeightDp:" + cfg.screenHeightDp + "\n");
        status.append("屏幕可用宽screenWidthDp:" + cfg.screenWidthDp + "\n");
        status.append("screenLayout:" + cfg.screenLayout + "\n");
        status.append("smallestScreenWidthDp:" + cfg.densityDpi + "\n");
        status.append("获取系统触摸屏的触摸方式touchscreen:" + cfg.densityDpi + "\n");
        status.append("uiMode:" + cfg.densityDpi + "\n");
        txtResult.setText(status.toString());
    }

    private void findViews() {
        txtResult = (TextView) findViewById(R.id.txtResult);
        etShuru = (EditText) findViewById(R.id.et_shuru);
        btnCeshi01 = (Button) findViewById(R.id.btn_ceshi_01);
        tvResult = (TextView) findViewById(R.id.tv_result);
        btnCeshi02 = (Button) findViewById(R.id.btn_ceshi_02);
        tvResult02 = (TextView) findViewById(R.id.tv_result_02);

        btnCeshi01.setOnClickListener(this);
        btnCeshi02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCeshi01) {
            testFunction_01();
        } else if (v == btnCeshi02) {
            testFunction_02();
        }
    }

    private void testFunction_01() {
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) {
            tvResult.setText("键盘显示");
        } else {
            tvResult.setText("键盘隐藏");
        }
    }

    private void testFunction_02() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive(etShuru)) {
            tvResult02.setText("键盘显示");
        } else {
            tvResult02.setText("键盘隐藏");
        }
    }

}
