package com.haitao.www.myformer.ui.ui_common.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class HollowSimplyView extends View {
    public HollowSimplyView(Context context) {
        super(context);
    }

    public HollowSimplyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HollowSimplyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        hollowSimply(canvas); //简易挖空

    }

    /**
     * 简单挖空
     * @param canvas
     */
    private void hollowSimply(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        Paint paint = new Paint();  //拿到画笔对象
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, canvasWidth, canvasHeight, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(180, 180, 50, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
