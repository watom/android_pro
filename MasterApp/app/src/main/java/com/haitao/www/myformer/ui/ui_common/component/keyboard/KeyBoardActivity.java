package com.haitao.www.myformer.ui.ui_common.component.keyboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.KeyBoardUtil;

/**
 * 测量键盘高度和监听键盘变化
 * 主要原理：
 * 通过两种方式：1.按钮，2.输入框，来检测布局变化
 */
public class KeyBoardActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {
    private RelativeLayout rlKeyboard;
    private EditText inputText;
    private Button showKeyboard;
    private Button hideKeyboard;
    private TextView keyboardHighValue;
    private KeyBoardUtil keyBoardUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        findViews();
        keyBoardUtil = new KeyBoardUtil(this);
    }

    private void findViews() {
        rlKeyboard = findViewById(R.id.rl_keyboard);
        inputText = findViewById(R.id.input_text);
        showKeyboard = findViewById(R.id.show_keyboard);
        hideKeyboard = findViewById(R.id.hide_keyboard);
        keyboardHighValue = findViewById(R.id.keyboard_high_value);

        inputText.setOnFocusChangeListener(this); //第1种方式
        showKeyboard.setOnClickListener(this);    //第2种方式
        hideKeyboard.setOnClickListener(this);    //第3种方式
    }

    /**
     * 监听输入框是否获取到焦点
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        drawKeyboardHighLine(); //注册布局监听器
        if(hasFocus){
            keyBoardUtil.isKeyboardHide(false);
        }else{
            keyBoardUtil.isKeyboardHide(true);
        }
    }

    @Override
    public void onClick(View v) {
        drawKeyboardHighLine(); //注册布局监听器
        if (v == showKeyboard) {
            keyBoardUtil.isKeyboardHide(false);
        } else if (v == hideKeyboard) {
            keyBoardUtil.isKeyboardHide(true);
        }
    }

    private void drawKeyboardHighLine() {
        keyBoardUtil.getSoftKeyboardHigh(this, new KeyBoardUtil.OnSoftKeyboardChangeListener() {
            @Override
            public void onSoftKeyBoardChange(int softKeybardHeight, boolean visible) {
                drawLine(softKeybardHeight);
            }
        });
    }

    private void drawLine(int softKeybardHeight){
        keyboardHighValue.setText(Integer.toString(softKeybardHeight).concat(" px"));

        View highLine = new View(KeyBoardActivity.this);
        highLine.setBackgroundColor(getResources().getColor(R.color.dodgerblue));

        // 为highLine添加长高设置
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, softKeybardHeight);
        highLine.setLayoutParams(rlParams);
        rlParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlKeyboard.addView(highLine);
    }


}
