package com.haitao.www.myformer.ui.ui_common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


public class RoundImageView extends AppCompatImageView {
    private float width;
    private float height;
    private float radius;
    private Paint paint;
    private Matrix matrix;

    public RoundImageView(Context context) {
        super(context);
        setting();
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setting();
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setting();
    }

    private void setting() {
        paint = new Paint();
        paint.setAntiAlias(true);   //设置抗锯齿
        matrix = new Matrix();      //初始化缩放矩阵
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setShader(initBitmapShader());//将着色器设置给画笔
        canvas.drawCircle(width / 2, height / 2, radius, paint);//使用画笔在画布上画圆
    }

    private BitmapShader initBitmapShader() {
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
        matrix.setScale(scale, scale);//将图片宽高等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }
}
