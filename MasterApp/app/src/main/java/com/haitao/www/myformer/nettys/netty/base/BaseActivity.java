package com.haitao.www.myformer.nettys.netty.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.haitao.www.myformer.ui.ui_common.dialog.progressBar.LoadingDialog;
import com.haitao.www.myformer.utils.NetUtils;
import com.haitao.www.myformer.utils.Util;

import java.util.List;

/**
 * Created by watom_Thinkpad on 2017/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private LoadingDialog mProcessDialog;
    protected Handler handler;
    private MyBroadCast myBroadCast;//注册广播
    private boolean isCancerBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitleBar();
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initHandler();
        initView();
        initData(savedInstanceState);
        initEvent();
    }

    /**
     * 设置标题栏
     */
    public void setTitleBar() {
        setBaseTitle();
    }

    /**
     * 加载初始化页面
     *
     * @return
     */
    public abstract int getContentView();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData(@Nullable Bundle savedInstanceState);

    /**
     * 注册监听事务
     */
    public abstract void initEvent();

    /**
     * 获取业务数据
     */
    protected abstract void updateData(Message msg);


    protected void initHandler() {
        this.handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case BaseBusiness.START:
                        showProgressDialog();
                        break;
                    case BaseBusiness.FAILURE:
                        failureToast(msg.obj);
                        closeProgressDialog();
                        break;
                    case BaseBusiness.FAILURE_NOT_CLOSE_PROGRESS:
                        failureToast(msg.obj);
                        break;
                    case BaseBusiness.END:
                        closeProgressDialog();
                        break;
                    case BaseBusiness.RELOGIN:
                        closeProgressDialog();
                        Toast.makeText(getBaseContext(), "请重新登录", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        updateData(msg);
                        break;
                }
            }
        };

    }

    protected void setBaseTitle() {

    }

    /**
     * 可以根据需要发送消息
     */
    public void sendMsg(Handler handler, int what, Object object) {
        Message message = handler.obtainMessage();
        message.what = what;
        message.obj = object;
        handler.sendMessage(message);
    }

    /**
     * 发送广播，携带String数据
     *
     * @param mBroadCast 广播名
     * @param dataList   数据列表
     */
    public void sendBroadcast(@NonNull String mBroadCast, @NonNull List<String> dataList) {
        if (Util.isEmpty(mBroadCast) && dataList.isEmpty()) return;
        Intent intent = new Intent(mBroadCast);
        for (int i = 0; i < dataList.size(); i++) {
            intent.putExtra("MSG_" + i, dataList.get(i));
        }
        sendOrderedBroadcast(intent, null);
    }

    /**
     * 接收广播
     * @param mBroadCast  广播名称
     * @param priority  有序广播的优先级
     */
    public void receiverBroadcast(String[] mBroadCast, int priority) {
        myBroadCast = new MyBroadCast(this, mBroadCast, priority);
        myBroadCast.setOnMyBroadCastListener(new MyBroadCast.MyBroadCastListener() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().contentEquals("想要接收的广播名称")) {
                    //需要根据业务修改相关的广播名，可以写相关业务，注销广播时，直接调用
                    Toast.makeText(BaseActivity.this, "收到广播", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void showProgressDialog() {
        if (mProcessDialog == null) {
            if (getParent() != null) {
                mProcessDialog = new LoadingDialog(getParent(), isCancerBack);
            } else {
                mProcessDialog = new LoadingDialog(this, isCancerBack);
            }
            mProcessDialog.setCancelable(false);
            mProcessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (!isFinishing()) {
            mProcessDialog.show();
        }
    }


    /**
     * 获取业务数据失败提示
     */
    public void failureToast(Object object) {
        if (object != null) {
            String ts = object.toString();
            if (!TextUtils.isEmpty(ts)) {
                Toast.makeText(this, ts, Toast.LENGTH_LONG).show();
            } else {
                if (NetUtils.isNetworkAvailable(this)) {
                    Toast.makeText(this, "数据请求失败", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "网络连接失败，请重试", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (NetUtils.isNetworkAvailable(this)) {
                Toast.makeText(this, "数据请求失败", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "网络连接失败，请重试", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void closeProgressDialog() {
        if (mProcessDialog != null) {
            mProcessDialog.dismiss();
        }
    }

    public void closeBroadcastReceiver() {
        if (myBroadCast != null) {
            myBroadCast.unregisterReceiver();
            myBroadCast = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeProgressDialog();
        closeBroadcastReceiver();
    }
}
