package com.haitao.www.myformer.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog.BottomSheetTestActivity;

import java.lang.reflect.Field;

/**
 * 获取键盘的高度
 */
public class KeyboardHelpUtils {

//    private Context context;
//
//    public KeyboardHelpUtils(Context context) {
//        this.context = context;
//    }
//
//    /**
//     * 获取键盘的高
//     */
//    private int getSoftInputHeight() {
//        int realKeyboardHeight = 0;
//        final TextView tv_input_attribute = (TextView) findViewById(R.id.tv_input_attribute);
//        final View myLayout = getWindow().getDecorView();
//        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            private int statusBarHeight;
//
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                // 使用最外层布局填充，进行测算计算
//                parentLayout.getWindowVisibleDisplayFrame(r);
//                int screenHeight = myLayout.getRootView().getHeight();
//                int heightDiff = screenHeight - (r.bottom - r.top);
//                if (heightDiff > 100) {
//                    // 如果超过100个像素，它可能是一个键盘。获取状态栏的高度
//                    statusBarHeight = 0;
//                }
//                try {
//                    Class<?> c = Class.forName("com.android.internal.R$dimen");
//                    Object obj = c.newInstance();
//                    Field field = c.getField("status_bar_height");
//                    int x = Integer.parseInt(field.get(obj).toString());
//                    statusBarHeight = BottomSheetTestActivity.this.getResources().getDimensionPixelSize(x);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                height = heightDiff - statusBarHeight;
//                tv_input_attribute.append("keyboard height(单位像素) = " + height + "\r\n");
//            }
//        });
////        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
////        List<InputMethodInfo> inputMethodList = imm.getInputMethodList();
////        for (int i = 0; i < inputMethodList.size(); i++) {
////            tv_input_attribute.append(inputMethodList.get(i).toString());
////        }
//        realKeyboardHeight = height;
//        return realKeyboardHeight;
//    }
//
//    private int getSupportSoftInputHeight() {
//        Rect r = new Rect();
//        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
//        int screenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
//        int softInputHeight = screenHeight - r.bottom;
//        if (Build.VERSION.SDK_INT >= 20) {
//            // When SDK Level >= 20 (Android L),
//            // the softInputHeight will contain the height of softButtonsBar (if has)
//            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
//        }
//        if (softInputHeight < 0) {
//            Log.w("EmotionInputDetector", "Warning: value of softInputHeight is below zero!");
//        }
//        if (softInputHeight > 0) {
//            PrefUtils.putInt("softInputHeight", softInputHeight,this);
//        }
//        return softInputHeight;
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    private int getSoftButtonsBarHeight() {
//        DisplayMetrics metrics = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int usableHeight = metrics.heightPixels;
//        this.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
//        int realHeight = metrics.heightPixels;
//        if (realHeight > usableHeight) {
//            return realHeight - usableHeight;
//        } else {
//            return 0;
//        }
//    }
//
//    private int getSoftInputHeight2() {
//        Rect r = new Rect();
//        //decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
//        //通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
//        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
//        //获取屏幕的高度
//        int screenHeight = this.getWindow().getDecorView().getRootView().getHeight();
//        //计算软件盘的高度
//        int softInputHeight = screenHeight - r.bottom;
//        return softInputHeight;
//    }
}
