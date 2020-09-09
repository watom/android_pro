package com.haitao.www.myformer.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

public class KeyBoardUtil {
    private static final String TAG = "KeyBoardUtil";
    private Activity activity;

    public KeyBoardUtil(Activity activity) {
        this.activity = activity;
    }

    /**
     * 显示/隐藏键盘
     *
     * @param isHide
     */
    public void isKeyboardHide(boolean isHide) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = activity.getCurrentFocus();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (!DataUtil.isEmpty(currentFocus)) {
                    if (isHide) {
                        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                    } else {
                        imm.showSoftInput(activity.getCurrentFocus(), InputMethodManager.SHOW_FORCED);
                    }
                } else {
                    Log.i(TAG, "没有获取到焦点");
                }
            }
        });
    }

    /**
     * 测量键盘高度
     *
     * @param activity
     * @param listener
     */
    public void getSoftKeyboardHigh(Activity activity, final OnSoftKeyboardChangeListener listener) {
        final View decorView = activity.getWindow().getDecorView();
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousKeyboardHeight = -1;

            /**
             * 当布局发生变化时，回调onGlobalLayout方法
             * 注意：如果在此回调方法中修改当前布局时，会出现调用回调onGlobalLayout方法，进入死循环，
             * 解决犯法：在每次修改布局后，移除此监听器
             */
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom;    //视图底边高度
                int height = decorView.getHeight(); //视图总高度
                int keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double) displayHeight / height > 0.8;
                    listener.onSoftKeyBoardChange(keyboardHeight, !hide);
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
                previousKeyboardHeight = height;
            }
        });
    }

    public interface OnSoftKeyboardChangeListener {
        void onSoftKeyBoardChange(int softKeybardHeight, boolean visible);
    }

}
