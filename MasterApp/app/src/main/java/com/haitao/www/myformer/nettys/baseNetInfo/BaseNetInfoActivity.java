package com.haitao.www.myformer.nettys.baseNetInfo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.MyPhoneInfo;

public class BaseNetInfoActivity extends AppCompatActivity {
    private MyPhoneInfo myPhoneInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netinfo);
        TextView tvnetinfo = (TextView)findViewById(R.id.tv_phone_net_info);
        myPhoneInfo = new MyPhoneInfo(this);
        initView(tvnetinfo,getData());
    }

    private void initView(TextView tv,String str) {
        tv.setText(str);
    }

    private String getData() {
        String iccid = myPhoneInfo.getIccid();
        String nativePhoneNumber = myPhoneInfo.getNativePhoneNumber();
        String phoneInfo = myPhoneInfo.getPhoneInfo();
        String providersName = myPhoneInfo.getProvidersName();
        return iccid+nativePhoneNumber+phoneInfo+providersName;
    }
}
