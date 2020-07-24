package com.haitao.www.myformer.ui.ui_common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class ShapeView extends View {
    public Shape mCurrentShape = Shape.CIRCLE;
    private Paint mPaint;
    private Path mPath;

    public enum Shape {
        CIRCLE,
        RECTANGLE,
        TRIANGLE,
    }
    public ShapeView(Context context) {
        super(context);
        initShapeView();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initShapeView();
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initShapeView();
    }

    private void initShapeView() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public Shape getCurrentShape() {
        return mCurrentShape;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        switch (mCurrentShape) {
            case CIRCLE:
                mPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark));
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case RECTANGLE:
                mPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
                canvas.drawRect(0, 0, getRight(), getBottom(), mPaint);
                break;
            case TRIANGLE:
                mPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_green_dark));
                mPath.moveTo(getWidth() / 2, 0);
                mPath.lineTo(0, (float) (getWidth() / 2 * Math.sqrt(3)));
                mPath.lineTo(getWidth(), (float) (getWidth() / 2 * Math.sqrt(3)));
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }

    public void changeShape() {
        switch (mCurrentShape) {
            case CIRCLE:
                mCurrentShape = Shape.RECTANGLE;
                break;
            case RECTANGLE:
                mCurrentShape = Shape.TRIANGLE;
                break;
            case TRIANGLE:
                mCurrentShape = Shape.CIRCLE;
                break;
        }
        invalidate();
    }
}
