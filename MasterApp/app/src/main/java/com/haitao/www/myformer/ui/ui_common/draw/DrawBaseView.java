package com.haitao.www.myformer.ui.ui_common.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.haitao.www.myformer.R;

/**
 * 安卓绘图
 * 步骤：
 * 1.自定义一个类，继承View
 * 2.重写onDraw方法
 * 注意：重写onDraw方法时，用到的主要API:
 * ① Canvas,它代表的是依附于指定View的画布
 * ② Paint,它代表画布Canvas上的画笔,主要用于设置绘制风格：画笔颜色、粗细，抗锯齿等风格
 * ③ Path,它代表任意多条直线连接而成的任意图形
 */
public class DrawBaseView extends View {
    public DrawBaseView(Context context) {
        super(context);
    }

    public DrawBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY); //1.初始化画布。画布绘成灰色
        Paint paint = getPaint();//2.初始化画笔

        //画圆形
        drawYuanXing(canvas, paint);
        //绘制三角形
        drawSanJiaoXing(canvas, paint);
        //画圆角矩形
        drawYuanJiaoJuXing(canvas, paint);
        //画点
        drawDot(canvas, paint);
        //画贝塞尔曲线
        drawBesselLine(canvas, paint);
        //画图片
        drawImage(canvas, paint);


    }

    /**
     * 初始化画笔
     */
    private Paint getPaint() {
        Paint paint = new Paint();  //拿到画笔对象
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE); //笔尖颜色
        paint.setStyle(Paint.Style.FILL_AND_STROKE); //有3种填充风格：1.边框,2.实心,3.边框和实心
        paint.setStrokeWidth(3);
        return paint;
    }

    private void drawYuanXing(Canvas canvas, Paint paint) {
        canvas.drawCircle(40, 40, 30, paint);
    }

    private void drawSanJiaoXing(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(10, 340);
        path.lineTo(70, 340);
        path.lineTo(40, 290);
        path.close();  //封闭
        canvas.drawPath(path, paint);
    }

    private void drawYuanJiaoJuXing(Canvas canvas, Paint paint) {
        RectF rectF = new RectF(10, 200, 70, 230);//设置个新的长方形
        canvas.drawRoundRect(rectF, 15, 15, paint);
    }

    private void drawDot(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("画点：", 10, 390, paint);
        canvas.drawPoint(60, 390, paint);//画一个点
        canvas.drawPoints(new float[]{60, 400, 65, 400, 70, 400}, paint);//画多个点
    }

    private void drawImage(Canvas canvas, Paint paint) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lunbotu_03);
        canvas.drawBitmap(bitmap, 250, 360, paint);
    }

    private void drawBesselLine(Canvas canvas, Paint paint) {
        canvas.drawText("画贝塞尔曲线:", 10, 310, paint);
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        Path path2 = new Path();
        path2.moveTo(100, 320);//设置Path的起点
        path2.quadTo(150, 310, 170, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, paint);//画出贝塞尔曲线
    }
}
