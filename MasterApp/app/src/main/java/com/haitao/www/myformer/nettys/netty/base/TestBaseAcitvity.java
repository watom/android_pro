package com.haitao.www.myformer.nettys.netty.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.util.ArrayList;
import java.util.List;

public class TestBaseAcitvity extends BaseActivity implements View.OnClickListener {
    private Button btnTest;
    private TextView tvTest;
    private TextView toolbar;
    private List<String> dataList;

    @Override
    public int getContentView() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        btnTest = (Button)findViewById( R.id.btn_test );
        tvTest = (TextView)findViewById( R.id.tv_test );

        btnTest.setOnClickListener( this );
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("数据= " + i);
        }
    }

    @Override
    public void initEvent() {
        receiverBroadcast(new String[] {"BROADCAST_LOGOUT"},5);
    }

    @Override
    protected void updateData(Message msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_test:
                sendBroadcast("BROADCAST_LOGOUT",dataList);
                break;
        }
    }

    @Override
    public void setTitleBar() {
    }
}
