package com.haitao.www.myformer.h5.h5_dome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.Log;
import com.haitao.www.myformer.utils.WebViewTool;

import static android.view.KeyEvent.KEYCODE_BACK;

public class WebViewPlatform extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.h5_layout_webview);
        webView = findViewById(R.id.where_webview);
        getBookmarkUrlList(this);
    }

    private void getBookmarkUrlList(Context context) {
        Intent intent = getIntent();
        String bookmarkUrl = intent.getStringExtra("bookmarkUrl");
        new WebViewTool(context).initWebSettings(webView, bookmarkUrl);
    }

    public WebViewPlatform() {
        Log.d("wanghaitao0202==", "构造方法");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("wanghaitao0202==", "onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //激活WebView为活跃状态，能正常执行网页的响应
        webView.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("wanghaitao0202==", "onKeyDown");
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();//让网页返回上一页而不是直接退出浏览器。
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 当webView不可见时，清空webView中的所有缓冲数据
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("wanghaitao0202==", "onStop");
        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        webView.clearFormData();
        //清除当前webview访问的历史记录,只会webview访问历史记录里的所有记录除了当前访问记录
        webView.clearHistory();
        //由于内核缓存是全局的,因此这个方法不仅仅针对webview而是针对整个应用程序.
        webView.clearCache(true);
    }

    /**
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("wanghaitao0202==", "onDestroy");
        webView.destroy();
    }
}
