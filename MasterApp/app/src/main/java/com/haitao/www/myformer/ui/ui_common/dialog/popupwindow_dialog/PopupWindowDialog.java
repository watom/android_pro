package com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.log.Log;
import com.haitao.www.myformer.utils.ToastUtils;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

public class PopupWindowDialog extends AppCompatActivity implements View.OnClickListener {
    public float alpha = 1.0f;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //设置添加屏幕的背景透明度
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = (float) msg.obj; //0.0-1.0
                    getWindow().setAttributes(lp);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    break;
            }
        }
    };
    private PopupWindow popupWindow;
    private View parentView, popupmenu, popupwindowAnimation, btnPopupwindowCustomMenu, btnBottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;
    private boolean changceTrend = false;// true增加 false减少

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow_dialog_activity);
        initView();

    }

    private void initView() {
        popupmenu = findViewById(R.id.btn_popupmenu);
        btnPopupwindowCustomMenu = findViewById(R.id.btn_popupwindow_custom_menu);
        popupwindowAnimation = findViewById(R.id.btn_popupwindow_animation);
        btnBottomSheet = findViewById(R.id.btn_BottomSheet);

    }

    private void generallyChangeUI(final boolean changceTrend, final float startAlpha) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                float alpha = startAlpha;
                if (changceTrend) {
                    //true 逐渐变成暗色
                    while (alpha > 0.5f) {
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //每次减少0.01，精度越高，变暗的效果越流畅
                        Message msg = mHandler.obtainMessage();
                        msg.what = 1;
                        alpha -= 0.01f;
                        msg.obj = alpha;
                        mHandler.sendMessage(msg);
                    }
                } else {
                    //true 逐渐变成透明 1.0透明
                    while (alpha < 1.0f) {
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //每次减少0.01，精度越高，变暗的效果越流畅
                        Message msg = mHandler.obtainMessage();
                        msg.what = 1;
                        alpha += 0.01f;
                        msg.obj = alpha;
                        if (alpha < 1.0f) mHandler.sendMessage(msg);
                    }
                }
            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_popupwindow:
                showPopupWindow();
                break;
            case R.id.btn_popupmenu:
                showPopmenu(popupmenu);
                break;
            case R.id.btn_popupwindow_animation:
                showPopupWindowAnimation(popupwindowAnimation);
                break;
            case R.id.btn_popupwindow_custom_menu:
                showCustomPopmenu(popupwindowAnimation);
                break;
            case R.id.btn_BottomSheet:
                showButtomSheet();
                break;
        }
    }

    private void showButtomSheet() {
        //在BottomSheetTestActivity中详细介绍BottomSheet的使用
        startActivity(new Intent(this, BottomSheetTestActivity.class));
    }

    /**
     * 实现动画弹出
     *
     * @param view
     */
    private void showPopupWindowAnimation(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        generallyChangeUI(true, 1.0f);
        View contentView = getLayoutInflater().inflate(R.layout.popupwindow_dialog_drawer_layout, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        //添加弹出、弹入的动画
        popupWindow.setAnimationStyle(R.style.popupwindowAnimation);
//      获得当前的父View的位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.e("控件的位置", "X = " + location[0] + "、 Y = " + location[1]);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
        //添加按键事件监听
        contentView.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    ToastUtils.showToast(PopupWindowDialog.this, "Click 相机");
                    popupWindow.dismiss();
                    generallyChangeUI(false, 0.5f);
                }
            }
        });
        contentView.findViewById(R.id.gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    ToastUtils.showToast(PopupWindowDialog.this, "Click 相册");
                    popupWindow.dismiss();
                    generallyChangeUI(false, 0.5f);
                }
            }
        });
        contentView.findViewById(R.id.savepicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    ToastUtils.showToast(PopupWindowDialog.this, "Click 保存");
                    popupWindow.dismiss();
                    generallyChangeUI(false, 0.5f);
                }
            }
        });
        contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    ToastUtils.showToast(PopupWindowDialog.this, "Click 取消");
                    popupWindow.dismiss();
                    generallyChangeUI(false, 0.5f);
                }
            }
        });
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                generallyChangeUI(false, 0.5f);
            }
        });
    }

    /**
     * popupwindow实现普通的Dialog
     */
    private void showPopupWindow() {
        parentView = getWindow().getDecorView();//得到当前界面的View对象
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_dialog_layout, null);
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);//必须设置宽和高，否则不显示任何东西
//        popupWindow.update();//更新popupWindow的状态
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));
//      popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置一个透明背景
        int[] location = new int[2];
        parentView.getLocationOnScreen(location);
        Log.e("控件的位置", "X = " + location[0] + "、 Y = " + location[1]);
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        popupWindow.setTouchable(true);
        popupWindow.getContentView().findViewById(R.id.sure_popupwindow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    ToastUtils.showToast(PopupWindowDialog.this, "确定");
                    popupWindow.dismiss();
                }
                return false;
            }
        });

        popupWindow.getContentView().findViewById(R.id.cancle_popupwindow).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    ToastUtils.showToast(PopupWindowDialog.this, "取消");
                    popupWindow.dismiss();
                }
                return false;
            }
        });
    }

    /**
     * PopupMenu实现附近menu弹出
     *
     * @param view
     */
    private void showPopmenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.e("控件的位置", "X = " + location[0] + "、 Y = " + location[1]);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.camera:
                        ToastUtils.showToast(PopupWindowDialog.this, "Click camera");
                        break;
                    case R.id.gallery:
                        ToastUtils.showToast(PopupWindowDialog.this, "Click gallery");
                        break;
                    case R.id.cancel:
                        ToastUtils.showToast(PopupWindowDialog.this, "Click cancel");
                        break;
                }
                return false;
            }
        });
        ToastUtils.showToast(PopupWindowDialog.this, "根据按钮大小自适应位置，在按钮附近弹出");
        popupMenu.show();
    }

    /**
     * PopupMenu实现附近menu弹出
     *
     * @param view
     */
    @SuppressLint("RestrictedApi")
    private void showCustomPopmenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_custom_item, popupMenu.getMenu());
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.e("控件的位置", "X = " + location[0] + "、 Y = " + location[1]);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.camera:
                        ToastUtils.showToast(PopupWindowDialog.this, "Click camera");
                        break;
                    case R.id.gallery:
                        ToastUtils.showToast(PopupWindowDialog.this, "Click gallery");
                        break;
                    case R.id.cancel:
                        ToastUtils.showToast(PopupWindowDialog.this, "Click cancel");
                        break;
                }
                return false;
            }
        });
        //使用反射，强制显示菜单图标
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper mHelper = (MenuPopupHelper) field.get(popupMenu);
            mHelper.setForceShowIcon(true);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        popupMenu.show();
    }
}
