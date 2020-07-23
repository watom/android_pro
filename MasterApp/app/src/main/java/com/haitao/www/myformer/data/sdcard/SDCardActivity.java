package com.haitao.www.myformer.data.sdcard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haitao.www.myformer.R;

/**
 * 读取SD中的文件和往SD卡中写文件
 *
 *
 */
public class SDCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
    }

    private void initView() {

    }

}
