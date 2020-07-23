package com.haitao.www.myformer.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by wanghaitao on 2018/9/30 0011.
 * 注意：
 * 1、添加网络权限
 * <uses-permission android:name="android.permission.INTERNET" />
 * 2、加载网页 H5,html,自定义浏览器，或者网页播放器
 * WebViewTool webView = new WebViewTool(this);
 */
public class WebViewTool {
    private Context context;
    private PackageManager packageManager;
    private WebView webView;
    private ProgressDialog waitingDialog;

    public WebViewTool(Context context) {
        this.context = context;
    }

    public WebViewTool(Context context, WebView webViewObj, String url) {
        this.context = context;
        this.webView = webViewObj;
        initWebSettings(webViewObj, url);

        //激活WebView为活跃状态，能正常执行网页的响应
        webViewObj.onResume();

        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause(),
        // 通过onPause()动作通知内核暂停所有的动作，比如DOM的解析、JavaScript执行等
        webViewObj.onPause();

        //当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview
        //它会暂停所有webview的布局显示、解析、延时，从而降低CPU功耗
        webViewObj.pauseTimers();

        //恢复pauseTimers状态
        webViewObj.resumeTimers();
    }

    public void initWebSettings(WebView webViewObj, String url) {
        // 设置WebSettings支持javascript
        WebSettings webSettings = webViewObj.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //加载网络上的HTML文件
        webViewObj.loadUrl(url);
        //在本地浏览器的页面里面有页面时不调用系统浏览器，会调用webview，即调用自己的内置的webkit浏览器
        WebViewClient webViewClient = initWebViewClient(webViewObj,url);
        //当一个新的url即将加载到当前的WebView中时，给主机应用程序一个接管控件的机会。
        webViewObj.setWebViewClient(webViewClient);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口

        //自适应屏幕
        webSettings.setUseWideViewPort(true);//扩大比例的缩放
        webSettings.setLoadsImagesAutomatically(true);//支持自动加载图片,将图片调整到适合webview的大小
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         *  1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小
         *  2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        //缩放操作
        webSettings.setSupportZoom(true); // 前提条件：支持缩放
        webSettings.setBuiltInZoomControls(true);//设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false);//隐藏原生的缩放控件

        //其他操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//关闭webview中缓存
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //将提供的Java对象注入到此WebView中。可以让JS通过android的这个字段调用这个java对象中的方法
        webViewObj.addJavascriptInterface(new androidLoginInterface(), "Android");
    }

    public WebViewClient initWebViewClient(WebView webViewObj, String url){
        return new WebViewClient(){
            /**
             * 重写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Log.d("wanghaitao010123==", "shouldOverrideUrlLoading");
                Log.d("wanghaitao010123==", view.toString());
                Log.d("wanghaitao010123==", request.getUrl());
                Log.d("wanghaitao010123==", request.getRequestHeaders());
                return super.shouldOverrideUrlLoading(view, request);
            }

            /**
             * 开始载入页面时调用此方法
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //设定加载开始的操作
                Log.d("wanghaitao01011234==", "开始载入页面时调用此方法");
                Log.d("wanghaitao01011234==", view.toString());
                Log.d("wanghaitao01011234==", url);
                startLoading();
            }

            /**
             * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
             * @param view
             * @param url
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //设定加载资源的操作
                Log.d("wanghaitao0101==", "在加载页面资源时会调用");
            }

            /**
             * 在页面加载结束时调用此方法
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("wanghaitao0101==", "在页面加载结束时调用此方法");
                endLoading();
            }

            /**
             * 加载页面的服务器出现错误时（如404）调用
             * @param view
             * @param request
             * @param error
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.d("wanghaitao0101==", "加载页面的服务器出现错误时");
            }

            /**
             * 处理https请求
             * @param view
             * @param handler
             * @param error
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                //webView默认是不处理https请求的，页面显示空白
                Log.d("wanghaitao0101==", "处理https请求");
                // handler.cancel(); //表示挂起连接，为默认方式
                // handler.handleMessage(null); //可做其他处理
                handler.proceed(); //表示等待证书响应
            }
        };
    }

    class androidLoginInterface {
        @JavascriptInterface  //这里的JavascriptInterface注解很重要
        public void showToast() {
            Toast.makeText(context, "js调用安卓代码", Toast.LENGTH_LONG).show();
        }
    }

    public void openLocalApp(String packageName, String appName) {
        packageManager = context.getPackageManager();
        if (checkPackInfo(packageName)) {//检查是否有要打开的app
            Intent intent = packageManager.getLaunchIntentForPackage(packageName);
            if (packageName.equals("com.alibaba.android.rimet")) {
                //打开钉钉考勤界面
            }
            //Intent intent = new Intent();
            //intent.setClassName("com.alibaba.android.rimet", "com.alibaba.android.rimet.lightapp.runtime.activity.CommonWebViewActivity");
            context.startActivity(intent);
        } else {
            ToastUtils.showToast(context, "手机未安装" + appName + "软件，正前往手机市场...");
            launchAppMarket(packageName, appName);//跳转到应用市场
        }
    }

    public void launchAppMarket(String packageName, String appName) {
        String model = Build.BRAND;
        String appmarket = null;
        if (model.contains("huawei") || model.equalsIgnoreCase("huawei")) {
            appmarket = "com.huawei.appmarket";
        } else if (model.contains("xiaomi")) {
            appmarket = "com.xiaomi.market";
        } else {
            ToastUtils.showToast(context, "请在应用市场上下载" + appName);
            return;
        }
        try {
            if (TextUtils.isEmpty(packageName)) return;

            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(appmarket)) {
                intent.setPackage(appmarket);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查包是否存在。
     */
    public boolean checkPackInfo(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    private void startLoading() {
        waitingDialog = new ProgressDialog(context);
        waitingDialog.setTitle("业精于勤，荒于嬉！");
        //更改此ProgressDialog的不确定模式。在不确定模式下，进程将被忽略，对话框将显示无限的动画。
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(true);//点击物理返回键时，设置Dialog是否能被取消
        waitingDialog.setCanceledOnTouchOutside(false);//点击Dialog的outSide时，设置Dialog是否能被取消
        waitingDialog.show();
    }
    private void endLoading(){
        waitingDialog.cancel();
    }
}
