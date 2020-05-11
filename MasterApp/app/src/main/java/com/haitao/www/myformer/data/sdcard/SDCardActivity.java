package com.haitao.www.myformer.data.sdcard;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.haitao.www.myformer.R;

import java.io.File;

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
