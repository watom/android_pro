package com.haitao.www.myformer.ui.ui_common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.TextView;

import com.haitao.www.myformer.R;

public class AcharItem extends RelativeLayout implements View.OnClickListener {
    private TextView charContent;
    private View charIndicator;

    private void findViews(View charItemView) {
    //        charContent = (TextView)charItemView.findViewById( R.id.char_content );
    //        charIndicator = (View)charItemView.findViewById( R.id.char_indicator );
    }

    public AcharItem(Context context) {
        super(context);
        View charItemView = LayoutInflater.from(context).inflate(R.layout.layout_char_item, this);
        findViews(charItemView);
    }

    public AcharItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AcharItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AcharItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View view) {

    }
}
