package com.haitao.www.myformer.ui.ui_common.component.textview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

/**
 * 使用SpannableString设置文本的样式
 * Created by Administrator on 2018/1/8 0008.
 * 功能基本实现完全
 */

public class SpannableStringActivity extends AppCompatActivity {
    private TextView textviewSpannableString00;
    private TextView textviewSpannableString01;
    private TextView textviewSpannableString02;
    private TextView textviewSpannableString03;
    private TextView textviewSpannableString04;
    private TextView textviewSpannableString05;
    private TextView textviewSpannableString06;
    private TextView textviewSpannableString07;
    private TextView textviewSpannableString08;
    private TextView textviewSpannableString09;
    private TextView textviewSpannableString10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        findViews();
        setSpann();
    }

    private void findViews() {
        textviewSpannableString00 = (TextView) findViewById(R.id.textview_spannableString_00);
        textviewSpannableString01 = (TextView) findViewById(R.id.textview_spannableString_01);
        textviewSpannableString02 = (TextView) findViewById(R.id.textview_spannableString_02);
        textviewSpannableString03 = (TextView) findViewById(R.id.textview_spannableString_03);
        textviewSpannableString04 = (TextView) findViewById(R.id.textview_spannableString_04);
        textviewSpannableString05 = (TextView) findViewById(R.id.textview_spannableString_05);
        textviewSpannableString06 = (TextView) findViewById(R.id.textview_spannableString_06);
        textviewSpannableString07 = (TextView) findViewById(R.id.textview_spannableString_07);
        textviewSpannableString08 = (TextView) findViewById(R.id.textview_spannableString_08);
        textviewSpannableString09 = (TextView) findViewById(R.id.textview_spannableString_09);
        textviewSpannableString10 = (TextView) findViewById(R.id.textview_spannableString_10);
    }

    private void setSpann() {
        //设置文字背景
        SpannableString 中国和美国 = new SpannableString("中国和美国");
        中国和美国.setSpan(new BackgroundColorSpan(Color.parseColor("#FFD700")), 0, 中国和美国.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textviewSpannableString00.append(中国和美国);

        //设置点击事件
        SpannableString 请点击文字 = new SpannableString("请点击文字");
        请点击文字.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ToastUtils.showToast(SpannableStringActivity.this, "文字已经点击过了！");
            }
        }, 0, 请点击文字.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textviewSpannableString01.append(请点击文字);
        textviewSpannableString01.setMovementMethod(LinkMovementMethod.getInstance()); // 重要：设置文字为可点击状态

        //设置文字的颜色
        SpannableString 颜色 = new SpannableString("颜色");
        颜色.setSpan(new ForegroundColorSpan(Color.parseColor("#FF3030")), 0, 颜色.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textviewSpannableString02.append(颜色);

        //设置文字删除线效果
        SpannableString 删除线效果 = new SpannableString("删除线效果");
        删除线效果.setSpan(new StrikethroughSpan(), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textviewSpannableString03.append(删除线效果);

        //设置下划线效果
        SpannableString 下划线效果 = new SpannableString("下划线效果");
        下划线效果.setSpan(new UnderlineSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textviewSpannableString04.append(下划线效果);

        //在文字中间插入图片，这里使用ImageSpan也可以将drawable添加进去（比DynamicDrawableSpan简单），
        SpannableString 在文字中间插入图片 = new SpannableString("在文字中间插入图片，会用图片替换前面的（end-start）个文字");
        在文字中间插入图片.setSpan(new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BOTTOM) {
            // DynamicDrawableSpan.ALIGN_BASELINE表示依照基线对齐
            // DynamicDrawableSpan.ALIGN_BOTTOM表示依照底部对齐
            @Override
            public Drawable getDrawable() {
                Drawable drawable = getResources().getDrawable(R.drawable.arrow_up);
                drawable.setBounds(0, 0, 50, 50); //这里可以使用drawable.getIntrinsicWidth()来获取图片原生的尺寸
                return drawable;
            }
        }, 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textviewSpannableString05.append(在文字中间插入图片);

        //设置基于X轴的缩放   ScaleXSpan中的参数大于1表示横向扩大，小于1大于0表示缩小，等于1表示正常显示
        SpannableString 基于X轴的缩放 = new SpannableString("基于X轴的缩放");
        基于X轴的缩放.setSpan(new ScaleXSpan(3),基于X轴的缩放.length()-2,基于X轴的缩放.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textviewSpannableString06.append(基于X轴的缩放);

        //设置文字粗体和斜体
        SpannableString spannableString = new SpannableString("文字的粗体、斜体");
        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),3,spannableString.length(),Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textviewSpannableString07.append(spannableString);

        //设置文字上下标
        SpannableString spannableString1 = new SpannableString("X12=Y12");
        spannableString1.setSpan(new SubscriptSpan(),1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //设置1为下标
        spannableString1.setSpan(new AbsoluteSizeSpan(30),1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //设置下标的大小

        spannableString1.setSpan(new SuperscriptSpan(),2,3,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //设置2为上标
        spannableString1.setSpan(new AbsoluteSizeSpan(30),2,3,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //设置上标的大小

        spannableString1.setSpan(new SubscriptSpan(),5,6,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //设置1为下标
        spannableString1.setSpan(new AbsoluteSizeSpan(30),5,6,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);  //设置下标的大小

        spannableString1.setSpan(new SuperscriptSpan(),6,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置2为上标
        spannableString1.setSpan(new AbsoluteSizeSpan(30),6,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置上标的大小
        textviewSpannableString08.append(spannableString1);

        //设置文本的超链接功能
        SpannableString spannableString2 = new SpannableString("点击文字：打电话、发短信、发邮件、打开网页");
        spannableString2.setSpan(new URLSpan("tel:15339164308"),5,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new URLSpan("smsto:15339164308"),9,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new URLSpan("mailto:1164973719@qq.com"),13,16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new URLSpan("myapp://mywebsite.com/openApp"),17,21,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textviewSpannableString09.append(spannableString2);
        textviewSpannableString09.setMovementMethod(LinkMovementMethod.getInstance());  // 重要：设置文字为可点击状态

        textviewSpannableString10.append("myapp://mywebsite.com/openApp");

    }

}
