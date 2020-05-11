package com.haitao.www.myformer.nettys.netty.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 动态注册广播接收者
 */
public class MyBroadCast {
    private MyBroadCastListener myBroadCastListener;
    private AppBroadCast appBroadCast;
    private String[] actions;
    private Context context;
    private int priority;

    public MyBroadCast(Context context, String[] actions, int priority) {
        this.context = context;
        this.actions = actions;
        this.priority = priority;
        init();
    }

    /**
     * 动态注册广播接受者
     */
    private void init() {
        appBroadCast = new AppBroadCast();

        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.setPriority(priority);
        for (String action : actions) {
            mIntentFilter.addAction(action);
        }
        context.registerReceiver(appBroadCast, mIntentFilter);

    }

    /**
     * 创建广播接收者
     */
    private class AppBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (myBroadCastListener != null) myBroadCastListener.onReceive(context, intent);
        }
    }

    public void setOnMyBroadCastListener(MyBroadCastListener myBroadCastListener) {
        this.myBroadCastListener = myBroadCastListener;
    }

    public interface MyBroadCastListener {
        void onReceive(Context context, Intent intent);
    }

    public void unregisterReceiver() {
        if (appBroadCast != null) {
            context.unregisterReceiver(appBroadCast);
            appBroadCast = null;
        }
    }
}
