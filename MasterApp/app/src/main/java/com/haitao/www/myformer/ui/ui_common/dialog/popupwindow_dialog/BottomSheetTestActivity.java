package com.haitao.www.myformer.ui.ui_common.dialog.popupwindow_dialog;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.KeyBoardUtil;
import com.haitao.www.myformer.utils.ToastUtils;

import java.lang.reflect.Field;

public class BottomSheetTestActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior bottomSheetBehavior;
    private View bottomSheetLayout;
    private Button btnWechatKeyboard;
    private Button btnQqKeyboard;
    private Button btnSougoKeyboard;
    private Button btnSecurityKeyboard;
    private TextView tvEmojiSample;
    private LinearLayout parentLayout;
    private Button btn_set_mode_voice, btn_set_mode_keyboard_01, btn_smile, btn_set_mode_keyboard_02, btn_send, btn_more;
    private LinearLayout btn_press_to_speak;
    private EditText et_sendmessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_keyboard);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        parentLayout = findViewById(R.id.ll_parent);
        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        btnWechatKeyboard = (Button) findViewById(R.id.btn_wechat_keyboard);
        btnQqKeyboard = (Button) findViewById(R.id.btn_qq_keyboard);
        btnSougoKeyboard = (Button) findViewById(R.id.btn_sougo_keyboard);
        btnSecurityKeyboard = (Button) findViewById(R.id.btn_security_keyboard);
        tvEmojiSample = (TextView) findViewById(R.id.tv_emoji_sample);

    }

    private void initData() {
        int unicodeJoy = 0x1F639;
        String emojiString = getEmojiStringByUnicode(unicodeJoy);
        tvEmojiSample.setText(emojiString);
    }

    /**
     * 功能：将emoji表情显示到页面上
     * 原理：通过emoji表情的通用Unicode编码就可以实现，直接使用Character.toChars()方法将unicode编码转换为一个char数组，
     * 再将这个char数组转换成为字符串就可以直接操作了，系统会自动将其解析为表情图片，可以直接显示在textview组件当中，不需要我们做任何其他的事情。
     *
     * @param unicode emoji表情对应的unicode编码
     * @return
     */
    private String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private void initEvent() {
        btnWechatKeyboard.setOnClickListener(this);
        btnQqKeyboard.setOnClickListener(this);
        btnSougoKeyboard.setOnClickListener(this);
        btnSecurityKeyboard.setOnClickListener(this);

        //把这个底部菜单和一个BottomSheetBehavior关联起来
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        //通过BottomSheetBehavior控制bottom_sheet_layout布局的显示和隐藏
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            /**
             * 监听拖动事件
             * @param bottomSheet
             * @param newState
             */
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");//收起
//                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");//展开
//                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");//隐藏
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");//移动中
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");//移动测量中
                        break;
                }
            }

            /**
             * 监听状态变化
             * @param bottomSheet
             * @param slideOffset
             */
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnWechatKeyboard) {
            wechatKeyboard();//微信键盘的入口
        } else if (view == btnQqKeyboard) {
            ToastUtils.showToast(this, "qq键盘暂未开发");

        } else if (view == btnSougoKeyboard) {
            ToastUtils.showToast(this, "搜狗键盘暂未开发");
        } else if (view == btnSecurityKeyboard) {
            ToastUtils.showToast(this, "安全键盘暂未开发");
        } else if (view == btn_set_mode_voice) {
            btn_set_mode_voice.setVisibility(View.GONE);
            btn_set_mode_keyboard_01.setVisibility(View.VISIBLE);
            et_sendmessage.setVisibility(View.GONE);
            btn_press_to_speak.setVisibility(View.VISIBLE);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            isShowKeyboard(true);
        } else if (view == btn_set_mode_keyboard_01) {
            btn_set_mode_voice.setVisibility(View.VISIBLE);
            btn_set_mode_keyboard_01.setVisibility(View.GONE);
            btn_press_to_speak.setVisibility(View.GONE);
            et_sendmessage.setVisibility(View.VISIBLE);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            isShowKeyboard(false);

        } else if (view == btn_smile) {
            btn_smile.setVisibility(View.GONE);
            btn_set_mode_keyboard_02.setVisibility(View.VISIBLE);
            isShowKeyboard(false);
        } else if (view == btn_set_mode_keyboard_02) {
            btn_smile.setVisibility(View.VISIBLE);
            btn_set_mode_keyboard_02.setVisibility(View.GONE);
            isShowKeyboard(true);
        } else if (view == btn_send) {

        } else if (view == btn_more) {

        }
    }

    private void isShowKeyboard(boolean b) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, b ? InputMethodManager.SHOW_FORCED : InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private LinearLayout llInputEditor;
    private void wechatKeyboard() {
        TabLayout keyboardTabLayout = findViewById(R.id.keyboard_tab_layout);
        ViewPager keyboardContainerViewpager = findViewById(R.id.keyboard_container_viewpager);
        LinearLayout roundPointGroup = findViewById(R.id.dot_viewgroup);
        llInputEditor = findViewById(R.id.ll_input_editor);

        btn_set_mode_voice = findViewById(R.id.btn_set_mode_voice);
        btn_set_mode_keyboard_01 = findViewById(R.id.btn_set_mode_keyboard_01);
        btn_press_to_speak = findViewById(R.id.btn_press_to_speak);
        et_sendmessage = findViewById(R.id.et_sendmessage);
        btn_smile = findViewById(R.id.btn_smile);
        btn_set_mode_keyboard_02 = findViewById(R.id.btn_set_mode_keyboard_02);
        btn_send = findViewById(R.id.btn_send);
        btn_more = findViewById(R.id.btn_more);

        KeyboardFragmentAdapter keyboardFragmentAdapter = new KeyboardFragmentAdapter(getSupportFragmentManager(), this);
        keyboardContainerViewpager.setAdapter(keyboardFragmentAdapter);
        //绑定指示器与ViewPager,让用户点击标题切换viewpager,滑动viewpager可以切换标题
        keyboardTabLayout.setupWithViewPager(keyboardContainerViewpager);
        //设置圆点指示器
        keyboardFragmentAdapter.setupWithPagerPoint(keyboardContainerViewpager, roundPointGroup);

        //设置表情键盘的展开高度。根据软键盘的高度来确定
        //当为STATE_COLLAPSED（折叠）状态的时候bottom_sheet_layout残留的高度，默认为0px。
        int inputEditorHeight = getViewHeight(llInputEditor);

        bottomSheetBehavior.setPeekHeight(inputEditorHeight);

        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(this);
        keyBoardUtil.getSoftKeyboardHigh(this, new KeyBoardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, boolean visible) {
                setKeyboardHeight(softKeybardHeight);
            }
        });

    }

    private int height;
    private int getViewHeight(final View view) {
        int viewHeight = 0;
        view.post(new Runnable() {
            @Override
            public void run() {
                height = view.getHeight();// 获取高度
            }
        });
        return viewHeight = height;
    }

    /**
     * 由于会受到其他应用布局的影响，所以需在onResume中重新设置高度
     */
    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void setKeyboardHeight(int height) {
        LinearLayout llKeyboard = findViewById(R.id.ll_keyboard);
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) llKeyboard.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height = height;// 强制设成控件的高
        llKeyboard.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

}
