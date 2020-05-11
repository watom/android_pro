package com.haitao.www.myformer.design_pattern;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.nettys.netty.base.BaseActivity;
import com.haitao.www.myformer.utils.WebViewTool;

public class PatternContentShow extends BaseActivity {
    private WebView webView;

    @Override
    public int getContentView() {
        return R.layout.h5_layout_webview;
    }

    @Override
    public void initView() {
        webView = findViewById(R.id.where_webview);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        new WebViewTool(this, webView,"file:///android_asset/html/design_discipline.html" );
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void updateData(Message msg) {

    }
}
