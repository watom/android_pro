package com.watom.draggridviewlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;

import com.watom.draggridviewlib.DragGridView;

public class LinearBox extends LinearLayout {
    public LinearBox(Context context) {
        super(context);
    }

    public LinearBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mGridViewIndex = -1;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child instanceof DragGridView) {
                DragGridView dragGridView = (DragGridView) child;
                if (dragGridView.isLongPressMode() || dragGridView.isTouchMode()) {
                    setChildrenDrawingOrderEnabled(true);
                    mGridViewIndex = i;
                }
                return;
            }
        }
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int index = i;
        if (mGridViewIndex != -1) {
            if (i == mGridViewIndex) {
                index = childCount - 1;
            } else if (i == childCount - 1) {
                index = mGridViewIndex;
            }
        }
        return index;
    }
}
