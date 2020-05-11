package com.haitao.www.myformer.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haitao.www.myformer.R;


public class CharItem extends RelativeLayout {
    private RelativeLayout charLeftMessage;
    private View charLeftIndicator;
    private TextView charLeftContent;
    private RelativeLayout charRightMessage;
    private TextView charRightContent;
    private View charRightIndicator;
    private View charItemView;



    public CharItem(Context context) {
        super(context);
        initView(context);
    }


    public CharItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CharItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public CharItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        charItemView = LayoutInflater.from(context).inflate(R.layout.layout_char_item, this, true);
        charLeftMessage = (RelativeLayout)charItemView.findViewById( R.id.char_left_message );
        charLeftIndicator = (View)charItemView.findViewById( R.id.char_left_indicator );
        charLeftContent = (TextView)charItemView.findViewById( R.id.char_left_content );
        charRightMessage = (RelativeLayout)charItemView.findViewById( R.id.char_right_message );
        charRightContent = (TextView)charItemView.findViewById( R.id.char_right_content );
        charRightIndicator = (View)charItemView.findViewById( R.id.char_right_indicator );
    }

}
