package com.haitao.www.myformer.h5.h5_dome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.haitao.www.myformer.R;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class H5Demo01 extends AppCompatActivity {
    private String[] content;
    private WebView webView01;
    private WebSettings webSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h5_layout_webview);
        webView01 = (WebView) findViewById(R.id.where_webview);
        initWebView("file:///android_asset/js01.html");//注意路径不同
//        initWebView("file:///android_asset/html/test01.html");//注意路径不同
//        initWebView("file:///android_asset/html/activityList.html");//注意路径不同
    }

    /**
     * 安卓原生代码
     */
    @SuppressLint("JavascriptInterface")
    private void initWebView(String url) {
        // 加载网页 H5,html,自定义浏览器，或者网页播放器
        // webView01 = new WebViewTool(this);
        // 设置WebSettings支持javascript
        webSettings = webView01.getSettings();
        //在本地浏览器的页面里面还有别的页面时，也会调用webview，而不会再次调用浏览器
        webView01.setWebViewClient(new WebViewClient());
        //设置为可调用js方法
        webSettings.setJavaScriptEnabled(true);
        //加载网络上的HTML文件
        webView01.loadUrl(url);
        webView01.addJavascriptInterface(new AndroidAndJSInterface(), "Android");

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setUseWideViewPort(true);//扩大比例的缩放

        //webView01.evaluateJavascript("",);

        webSettings.setLoadWithOverviewMode(true);

        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         *  1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小
         *  2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    class AndroidAndJSInterface {
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(H5Demo01.this, "JS代码调用原生代码", Toast.LENGTH_LONG).show();
        }
    }

}
