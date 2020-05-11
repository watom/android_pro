package com.haitao.www.myformer.ui.ui_common.component.textview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Administrator on 2018/6/27 0027.
 */

public class TextWatcherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    class MyTextWatcher implements TextWatcher{

        /**
         * 该方法调用是在文本没有被改变，但将要被改变的时候调用.
         * 在当前文本s中，从start位置开始之后的count个字符（即将）要被after个字符替换掉
         * @param s  文本改变之前的内容
         * @param start  文本开始改变时的起点位置，从0开始计算
         * @param count  要被改变的文本字数，即将要被替代的选中文本字数
         * @param after  改变后添加的文本字数，即替代选中文本后的文本字数
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        /**
         *  该方法调用是在文本被改变时，改变的结果已经可以显示时调用.
         *  在当前文本s中，从start位置开始之后的before个字符（已经）被count个字符替换掉了
         * @param s  文本改变之后的内容
         * @param start   文本开始改变时的起点位置，从0开始计算
         * @param before  要被改变的文本字数，即已经被替代的选中文本字数
         * @param count   改变后添加的文本字数，即替代选中文本后的文本字数
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        /**
         *  该方法是在文本改变结束后调用.
         *  在执行完beforeTextChanged、onTextChanged两个方法后才会被调用，此时的文本s为最终显示给用户看到的文本
         * @param s  改变后的最终文本
         */
        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
