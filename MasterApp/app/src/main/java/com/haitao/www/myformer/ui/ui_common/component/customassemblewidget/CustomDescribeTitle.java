package com.haitao.www.myformer.ui.ui_common.component.customassemblewidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.haitao.www.myformer.R;

public class CustomDescribeTitle extends LinearLayout {
    private View leftLine;
    private TextView tvDescribeTitle;
    private View rightLine;

    public CustomDescribeTitle(Context context) {
        super(context);
        initView(context, null);
    }


    public CustomDescribeTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_paragraph_title, this, true);

        leftLine = (View) findViewById(R.id.left_line);
        rightLine = ( View) findViewById(R.id.right_line);
        tvDescribeTitle = (TextView) findViewById(R.id.tv_describe_title);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomDescribeTitle);
        if (attributes != null) {
            int titleBarBackGround = attributes.getResourceId(R.styleable.CustomDescribeTitle_bg_color, Color.BLUE);
            boolean lineVisibleLeft = attributes.getBoolean(R.styleable.CustomDescribeTitle_line_visible_left, true);
            boolean lineVisibleRight = attributes.getBoolean(R.styleable.CustomDescribeTitle_line_visible_right, true);
            if (lineVisibleLeft) {
                leftLine.setVisibility(View.VISIBLE);
            } else {
                leftLine.setVisibility(View.INVISIBLE);
            }
            if (lineVisibleRight) {
                rightLine.setVisibility(View.VISIBLE);
            } else {
                rightLine.setVisibility(View.INVISIBLE);
            }

            int textDrawable = attributes.getResourceId(R.styleable.CustomDescribeTitle_describe_title_drawable, -1);
            //先获取标题是否要显示图片icon
            if (textDrawable != -1) {
                tvDescribeTitle.setBackgroundResource(textDrawable);
            } else {
                //如果不是图片标题 则获取文字标题
                String text = attributes.getString(R.styleable.CustomDescribeTitle_describe_title);
                if (!TextUtils.isEmpty(text)) {
                    tvDescribeTitle.setText(text);
                }
                //获取标题显示颜色
                int textColor= attributes.getColor(R.styleable.CustomDescribeTitle_describe_title_color, Color.BLACK);
                tvDescribeTitle.setTextColor(textColor);
            }
        }
    }
}