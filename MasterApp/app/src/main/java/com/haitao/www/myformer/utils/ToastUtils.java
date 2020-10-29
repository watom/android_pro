package com.haitao.www.myformer.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haitao.www.myformer.R;

public class ToastUtils extends Toast {

    public static Toast toast;

    public ToastUtils(Context context) {
        super(context);
    }

    /**
     * 显示提示信息--通过字符串
     *
     * @param mContext 上下文
     * @param content  字符串
     */
    public static void showToast(Context mContext, String content) {
        showMyToast(mContext,content);
        Log.i(mContext.toString(), content);
    }

    /**
     * 显示提示信息--通过id
     *
     * @param mContext 上下文
     * @param id       字符串id
     */
    public static void showToast(Context mContext, int id) {
        toast = Toast.makeText(mContext, id, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(id);
        toast.show();
    }

    /**
     * 显示操作失败的提示
     *
     * @param mContext 上下文
     * @param result   结果
     */
    public static void showFailToast(Context mContext, int result) {
        toast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        if (result == -3) {
            toast.setText("网络连接失败");
        } else if (result == -2) {
            toast.setText("访问超时");
        } else {
            toast.setText("系统临时维护，请稍候尝试!\\n给您带来的不便敬请谅解!");
        }
        toast.show();
    }

    /**
     * 自定义提示
     *
     * @param mContext 上下文
     * @param message   结果
     */
    public static void showMyToast(Context mContext, String message) {
        View toastRoot = LayoutInflater.from(mContext).inflate(R.layout.layout_my_toast, null);
        Toast toast = new Toast(mContext);
        toast.setView(toastRoot);
        TextView tv = (TextView) toastRoot.findViewById(R.id.toast_notice);
        tv.setText(message);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
