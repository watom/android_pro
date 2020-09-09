package com.haitao.www.myformer.ui.ui_common.dialog.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

public class ToastActivity extends Activity {
    Button defaultToast;
    Button myToast;
    Button myToastNotice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initView();
        initEvent();
    }

    private void initEvent() {
        defaultToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(ToastActivity.this,"默认Toast");
            }
        });
        myToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showMyToast(ToastActivity.this,"自定义Toast");
            }
        });
    }

    private void initView() {
        defaultToast = findViewById(R.id.default_toast);
        myToast = findViewById(R.id.my_toast);
        myToastNotice = findViewById(R.id.my_toast_notice);
    }
}
